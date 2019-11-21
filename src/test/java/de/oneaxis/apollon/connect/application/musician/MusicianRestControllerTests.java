package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.instrument.InstrumentRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusicianRestControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    private static MusicianRest sharedTestMusician;

    @Test
    @Order(1)
    void ShouldCreateNewMusician() {
        sharedTestMusician = restTemplate.getForObject(musiciansEndpoint() + "/new", MusicianRest.class);
        isValid(sharedTestMusician);
    }

    @Test
    @Order(2)
    void ShouldAssignInstrument() {
        InstrumentRest instrumentRest = createTestInstrument();
        sharedTestMusician.instruments.add(instrumentRest);

        HttpEntity<MusicianRest> entity = new HttpEntity<>(sharedTestMusician);
        sharedTestMusician = restTemplate.exchange(musiciansEndpoint(), HttpMethod.PUT, entity, MusicianRest.class).getBody();
        isValid(sharedTestMusician);
    }

    void ShouldStartBandSearch() {

    }



    void ShouldRemoveInstrument() {

    }

    private InstrumentRest createTestInstrument() {
        String instrumentsUrl = String.format("http://localhost:%s/instruments", randomServerPort);
        InstrumentRest instumentRest = new InstrumentRest(null, "Guitar");
        InstrumentRest instrumentRest = restTemplate.postForObject(instrumentsUrl, instumentRest, InstrumentRest.class);
        assertThat(instrumentRest.id).isNotEmpty();
        return new InstrumentRest(instrumentRest.id, instrumentRest.name);
    }

    private String musiciansEndpoint() {
        String musiciansEndpointTemplate = "http://localhost:%s/musicians";
        return String.format(musiciansEndpointTemplate, randomServerPort);
    }

    private void isValid(MusicianRest musicianRest) {
        assertThat(musicianRest).isNotNull();
        assertThat(musicianRest.id).isNotNull();
        assertThat(musicianRest.instruments).isNotNull();
        assertThat(musicianRest.bands).isNotNull();
        assertThat(musicianRest.bandSearches).isNotNull();
    }
}
