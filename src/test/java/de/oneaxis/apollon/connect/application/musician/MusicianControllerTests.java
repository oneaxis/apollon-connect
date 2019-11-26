package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.musician.Musician;
import org.bson.types.ObjectId;
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

    private static Musician sharedTestMusician;

    @Test
    void Test0_ShouldCreateNewMusician() {
        Musician musician = this.restTemplate.getForObject(musiciansEndpoint("new"), Musician.class);
        assertThat(musician.getId()).isNotNull();
        sharedTestMusician = musician;
    }

    @Test
    void Test1_GivenExistingMusician_ShouldAddNewBand() {
        Band band = this.restTemplate.getForObject(bandsEndpoint("new"), Band.class);
        assertThat(band.getId().getValue()).isNotEmpty();

        String joinBandEndpoint = musiciansEndpoint(String.format("%s/bands", sharedTestMusician.getId()));
        sharedTestMusician = this.restTemplate.postForObject(joinBandEndpoint, band.getId().getValue(), Musician.class);
        assertThat(sharedTestMusician.getBands()).containsOnlyOnce(band.getId());
    }

    private String musiciansEndpoint(String uri) {
        String musiciansEndpointTemplate = "http://localhost:%s/musicians/%s";
        return String.format(musiciansEndpointTemplate, randomServerPort, uri != null ? uri : "");
    }

    private String bandsEndpoint(String uri) {
        String bandsEndpointTemplate = "http://localhost:%s/bands";
        return String.format(bandsEndpointTemplate, randomServerPort, uri != null ? uri : "");
    }
}
