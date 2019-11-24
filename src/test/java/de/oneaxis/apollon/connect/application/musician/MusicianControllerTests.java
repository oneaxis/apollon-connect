package de.oneaxis.apollon.connect.application.musician;

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
        Musician musician = this.restTemplate.getForObject(musiciansEndpoint() + "/new", Musician.class);
        assertThat(musician.id).isNotNull();
        sharedTestMusician = musician;
    }

    @Test
    void Test1_GivenExistingMusician_ShouldAddNewBand() {
        BandId bandId = new BandId(ObjectId.get().toString());
        sharedTestMusician.bands.add(bandId);
        this.restTemplate.put(musiciansEndpoint(), sharedTestMusician);
    }

    @Test
    void Test2_GivenExistingMusician_ShoudlGetMusicianWithBand() {
        Musician musician = this.restTemplate.getForObject(musiciansEndpoint() + "/" + sharedTestMusician.id.value,
                Musician.class);
        assertThat(musician.id).isEqualToComparingFieldByField(sharedTestMusician.id);
        assertThat(musician.bands).isEqualTo(sharedTestMusician.bands);
    }


//    @Test
//    @Order(1)
//    public void ShouldCreateNewMusician() {
//        sharedTestMusician = restTemplate.getForObject(musiciansEndpoint() + "/new", MusicianRest.class);
//        isValid(sharedTestMusician);
//    }
//
//    @Test
//    @Order(2)
//    public void ShouldAssignInstrument() throws InstrumentWithoutNameException {
//        InstrumentRest instrumentRest = createTestInstrument();
//        sharedTestMusician.instruments.add(instrumentRest);
//
//        HttpEntity<MusicianRest> entity = new HttpEntity<>(sharedTestMusician);
//        sharedTestMusician = restTemplate.exchange(musiciansEndpoint(), HttpMethod.PUT, entity, MusicianRest.class).getBody();
//        isValid(sharedTestMusician);
//    }

    void ShouldStartBandSearch() {

    }


    void ShouldRemoveInstrument() {

    }

    //    private InstrumentRest createTestInstrument() throws InstrumentWithoutNameException {
//        String instrumentsUrl = String.format("http://localhost:%s/instruments", randomServerPort);
//        InstrumentRest instumentRest = new InstrumentRest(null, "Guitar");
//        InstrumentRest instrumentRest = restTemplate.postForObject(instrumentsUrl, instumentRest, InstrumentRest.class);
//        assertThat(instrumentRest.id.value).isNotEmpty();
//        return instrumentRest;
//    }
//
    private String musiciansEndpoint() {
        String musiciansEndpointTemplate = "http://localhost:%s/musicians";
        return String.format(musiciansEndpointTemplate, randomServerPort);
    }
//
//    private void isValid(MusicianRest musicianRest) {
//        assertThat(musicianRest).isNotNull();
//        assertThat(musicianRest.id).isNotNull();
//        assertThat(musicianRest.instruments).isNotNull();
//        assertThat(musicianRest.bands).isNotNull();
//        assertThat(musicianRest.bandSearches).isNotNull();
//    }
}
