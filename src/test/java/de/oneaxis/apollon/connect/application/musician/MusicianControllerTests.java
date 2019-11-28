package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.ApollonConnectAPIClient;
import de.oneaxis.apollon.connect.application.AbstractControllerTest;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.musician.BandSearch;
import de.oneaxis.apollon.connect.model.musician.Musician;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class MusicianControllerTests extends AbstractControllerTest {

    private static Musician testMusician;

    @Test
    void Test0_ShouldCreateNewMusician() {
        testMusician = this.apiClient.getNewMusician();
        assertThat(testMusician.getId().getValue()).isNotEmpty();
    }

    @Test
    void Test1_GivenExistingMusician_ShouldJoinNewBand() {
        Band band = this.apiClient.getNewBand();
        assertThat(band.getId().getValue()).isNotEmpty();

        testMusician = this.apiClient.joinBand(testMusician.getId(), band.getId());
        assertThat(testMusician.getBands()).containsOnlyOnce(band.getId());
    }

    @Test
    void Test2_GivenExistingMusicianWithBand_ShouldLeaveNewBand() {
        BandId bandId = (BandId) testMusician.getBands().toArray()[0];
        testMusician = this.apiClient.leaveBand(testMusician.getId(), bandId);
        assertThat(testMusician.getBands()).doesNotContain(bandId);
    }

    @Test
    void Test3_GivenExistingMusician_ShouldAssignInstrument() {
        Instrument instrument = this.apiClient.getNewInstrument();
        assertThat(instrument.getId().getValue()).isNotEmpty();

        testMusician = this.apiClient.assignInstrument(testMusician.getId(), instrument.getId());
        assertThat(testMusician.getInstruments()).containsOnlyOnce(instrument.getId());
    }

    @Test
    void Test4_GivenExistingMusicianWithInstrument_ShouldUnassignInstrument() {
        InstrumentId instrumentId = (InstrumentId) testMusician.getInstruments().toArray()[0];
        testMusician = this.apiClient.unassignInstrument(testMusician.getId(), instrumentId);
        assertThat(testMusician.getInstruments()).doesNotContain(instrumentId);
    }

    @Test
    void Test5_GivenExistingMusician_ShouldStartBandSearch() {
        BandSearch bandSearch = BandSearch.builder().postalCode("12345").build();
        testMusician = this.apiClient.startBandSearch(testMusician.getId(), bandSearch);
        assertThat(testMusician.getBandSearches()).containsOnlyOnce(bandSearch);
    }

    @Test
    void Test6_GivenExistingMusicianWithBandSearch_ShouldStartBandSearch() {
        BandSearch bandSearch = (BandSearch) testMusician.getBandSearches().toArray()[0];
        this.apiClient.stopBandSearch(testMusician.getId(), bandSearch);

        testMusician = this.apiClient.getMusician(testMusician.getId());
        assertThat(testMusician.getBandSearches()).doesNotContain(bandSearch);
    }
}
