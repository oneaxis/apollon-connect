package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.instrument.InstrumentRequest;
import de.oneaxis.apollon.connect.application.instrument.InstrumentResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusicianControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    private InstrumentRequest instrumentRequest;

    @BeforeEach
    void initTestInstances() {
        createTestInstrument();
    }

    @Test
    void ShouldCreateNewMusician() {
        MusicianRequest musicianRequest = new MusicianRequest(Set.of(this.instrumentRequest));
        String musiciansUrl = String.format("http://localhost:%s/musicians", randomServerPort);
        MusicianResponse response = restTemplate.postForObject(musiciansUrl, musicianRequest, MusicianResponse.class);
        assertThat(response).isNotNull();
        assertThat(response.id).isNotNull();
        assertThat(response.instruments).isNotNull();
    }

    private void createTestInstrument() {
        String instrumentsUrl = String.format("http://localhost:%s/instruments", randomServerPort);
        InstrumentRequest instrumentRequest = new InstrumentRequest(null, "Guitar");
        InstrumentResponse instrumentResponse = restTemplate.postForObject(instrumentsUrl, instrumentRequest, InstrumentResponse.class);
        assertThat(instrumentResponse.id).isNotEmpty();
        this.instrumentRequest = new InstrumentRequest(instrumentResponse.id, instrumentResponse.name);
    }
}
