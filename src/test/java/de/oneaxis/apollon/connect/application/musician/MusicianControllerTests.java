package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
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
    void Test0_ShouldCreateNewMusician() {
        testMusician = this.restTemplate.getForObject(musiciansEndpoint("new"), Musician.class);
        assertThat(testMusician.getId().getValue()).isNotEmpty();
    }

    @Test
    void Test1_GivenExistingMusician_ShouldJoinNewBand() {
        Band band = this.restTemplate.getForObject(bandsEndpoint("new"), Band.class);
        assertThat(band.getId().getValue()).isNotEmpty();

        String joinBandEndpoint = musiciansEndpoint(String.format("%s/bands", testMusician.getId().getValue()));
        testMusician = this.restTemplate.postForObject(joinBandEndpoint, band.getId().getValue(), Musician.class);
        assertThat(testMusician.getBands()).containsOnlyOnce(band.getId());
    }

    @Test
    void Test2_GivenExistingMusicianWithBand_ShouldLeaveNewBand() {
        // Use successful test as precondition
        Test1_GivenExistingMusician_ShouldJoinNewBand();
        assertThat(testMusician.getBands().toArray()).hasSize(1);

        BandId bandId = (BandId) testMusician.getBands().toArray()[0];
        String leaveBandEndpoint = musiciansEndpoint(String.format("%s/bands/%s", testMusician.getId().getValue(), bandId.getValue()));
        this.restTemplate.delete(leaveBandEndpoint);

        testMusician = this.restTemplate.getForObject(musiciansEndpoint(testMusician.getId().getValue()), Musician.class);
        assertThat(testMusician.getBands()).doesNotContain(bandId);
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
