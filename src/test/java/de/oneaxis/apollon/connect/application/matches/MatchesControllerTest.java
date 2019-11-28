package de.oneaxis.apollon.connect.application.matches;

import de.oneaxis.apollon.connect.application.AbstractControllerTest;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.MusicianSearch;
import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.musician.BandSearch;
import de.oneaxis.apollon.connect.model.musician.Musician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class MatchesControllerTest extends AbstractControllerTest {

    private Band bandSearchingForKeyboardPlayer, bandSearchingForGuitarPlayer;
    private Musician guitarMusician, keyboardMusician, allroundMusician;
    private Instrument guitar, keyboard;
    private final String SEARCH_POSTAL_CODE = "12345";

    @BeforeEach
    void initTestParams() {
        createTestInstruments();
        createTestMusicians();
        createTestBands();
    }

    @Test
    void Test0_GivenExistingMusicianSearches_ShouldMatchWithValidMusician() {
        MusicianSearch keyboardPlayerSearch = bandSearchingForKeyboardPlayer.getMusicianSearches().stream()
                .findFirst().orElseThrow();
        Set matchingKeyboardMusicians = apiClient.matchMusicianSearch(keyboardPlayerSearch);
        assertThat(matchingKeyboardMusicians).hasSize(2);

        MusicianSearch guitarPlayerSearch = bandSearchingForGuitarPlayer.getMusicianSearches().stream()
                .findFirst().orElseThrow();
        Set matchingGuitarMusicians = apiClient.matchMusicianSearch(guitarPlayerSearch);
        assertThat(matchingGuitarMusicians).hasSize(2);
    }

    @Test
    void Test1_GivenExistingBandSearches_ShouldMatchWithValidBand() {
        BandSearch bandNeedingGuitarSearch = guitarMusician.getBandSearches().stream()
                .findFirst().orElseThrow();
        Set matchingGuitarBands = apiClient.matchBandSearch(bandNeedingGuitarSearch);
        assertThat(matchingGuitarBands).hasSize(1);

        BandSearch bandNeedingKeyboardSearch = keyboardMusician.getBandSearches().stream()
                .findFirst().orElseThrow();
        Set matchingKeyboardBands = apiClient.matchBandSearch(bandNeedingKeyboardSearch);
        assertThat(matchingKeyboardBands).hasSize(1);
    }

    private void createTestBands() {
        bandSearchingForKeyboardPlayer = apiClient.getNewBand();
        bandSearchingForKeyboardPlayer = apiClient.startMusicianSearch(
                bandSearchingForKeyboardPlayer.getId(),
                MusicianSearch.builder().postalCode(SEARCH_POSTAL_CODE).instrumentId(keyboard.getId()).build()
        );

        bandSearchingForGuitarPlayer = apiClient.getNewBand();
        bandSearchingForGuitarPlayer = apiClient.startMusicianSearch(
                bandSearchingForKeyboardPlayer.getId(),
                MusicianSearch.builder().postalCode(SEARCH_POSTAL_CODE).instrumentId(guitar.getId()).build()
        );
    }

    private void createTestInstruments() {
        guitar = apiClient.getNewInstrument();
        guitar = apiClient.updateInstrumentName(guitar.getId(), "Guitar");
        keyboard = apiClient.getNewInstrument();
        keyboard = apiClient.updateInstrumentName(keyboard.getId(), "Keyboard");
    }

    private void createTestMusicians() {
        allroundMusician = apiClient.getNewMusician();
        allroundMusician = apiClient.startBandSearch(
                allroundMusician.getId(),
                BandSearch.builder().postalCode(SEARCH_POSTAL_CODE).instrumentId(keyboard.getId()).build()
        );
        allroundMusician = apiClient.startBandSearch(
                allroundMusician.getId(),
                BandSearch.builder().postalCode(SEARCH_POSTAL_CODE).instrumentId(guitar.getId()).build()
        );

        guitarMusician = apiClient.getNewMusician();
        guitarMusician = apiClient.startBandSearch(
                guitarMusician.getId(),
                BandSearch.builder().postalCode(SEARCH_POSTAL_CODE).instrumentId(guitar.getId()).build()
        );

        keyboardMusician = apiClient.getNewMusician();
        keyboardMusician = apiClient.startBandSearch(
                keyboardMusician.getId(),
                BandSearch.builder().postalCode(SEARCH_POSTAL_CODE).instrumentId(keyboard.getId()).build()
        );
    }
}
