package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.musician.Musician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusicianControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int randomServerPort;

    private Musician testMusician;

    @BeforeEach
    void ShouldCreateNewMusician() {
        testMusician = this.restTemplate.getForObject(musiciansEndpoint("new"), Musician.class);
        assertThat(testMusician.getId().getValue()).isNotEmpty();
    }

    @Test
    void GivenExistingMusician_ShouldAddNewBand() {
        Band band = this.restTemplate.getForObject(bandsEndpoint("new"), Band.class);
        assertThat(band.getId().getValue()).isNotEmpty();

        String joinBandEndpoint = musiciansEndpoint(String.format("%s/bands", testMusician.getId().getValue()));
        testMusician = this.restTemplate.postForObject(joinBandEndpoint, band.getId().getValue(), Musician.class);
        assertThat(testMusician.getBands()).containsOnlyOnce(band.getId());
    }

    private String musiciansEndpoint(String uri) {
        String musiciansEndpointTemplate = "http://localhost:%s/musicians/%s";
        return String.format(musiciansEndpointTemplate, randomServerPort, uri != null ? uri : "");
    }

    private String bandsEndpoint(String uri) {
        String bandsEndpointTemplate = "http://localhost:%s/bands/%s";
        return String.format(bandsEndpointTemplate, randomServerPort, uri != null ? uri : "");
    }
}
