package de.oneaxis.apollon.connect.application;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.band.MusicianSearch;
import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.musician.BandSearch;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import java.net.URI;
import java.util.Set;

public class ApollonConnectAPIClient extends TestRestTemplate {

    private final String MATCHES_ENDPOINT = "matches";
    private final String MUSICIANSEARCHES_ENDPOINT = "musicianSearches";
    private final String MUSICIANSEARCH_ENDPOINT = "musicianSearch";
    private final String MUSICIANS_ENDPOINT = "musicians";
    private final String INSTRUMENTS_ENDPOINT = "instruments";
    private final String BANDS_ENDPOINT = "bands";
    private final String BANDSEARCHES_ENDPOINT = "bandSearches";
    private final String BANDSEARCH_ENDPOINT = "bandSearch";
    private final String NEW_OBJECT_ENDPOINT = "new";
    private final String NAME_ENDPOINT = "name";

    private final int serverPort;

    public ApollonConnectAPIClient(int serverPort) {
        this.serverPort = serverPort;
    }

    public Musician getNewMusician() {
        return this.getForObject(route(MUSICIANS_ENDPOINT, NEW_OBJECT_ENDPOINT), Musician.class);
    }

    public Band getNewBand() {
        return this.getForObject(route(BANDS_ENDPOINT, NEW_OBJECT_ENDPOINT), Band.class);
    }

    public Instrument getNewInstrument() {
        return this.getForObject(route(INSTRUMENTS_ENDPOINT, NEW_OBJECT_ENDPOINT), Instrument.class);
    }

    public Musician getMusician(MusicianId musicianId) {
        return this.getForObject(route(MUSICIANS_ENDPOINT, musicianId.getValue()), Musician.class);
    }

    public Instrument updateInstrumentName(InstrumentId instrumentId, String name) {
        return this.postForObject(route(INSTRUMENTS_ENDPOINT, instrumentId.getValue(), NAME_ENDPOINT),
                name, Instrument.class);
    }

    public Musician joinBand(MusicianId musicianId, BandId bandId) {
        return this.postForObject(route(MUSICIANS_ENDPOINT, musicianId.getValue(), BANDS_ENDPOINT),
                bandId.getValue(), Musician.class);
    }

    public Musician leaveBand(MusicianId musicianId, BandId bandId) {
        RequestEntity requestEntity = new RequestEntity(HttpMethod.DELETE,
                URI.create(route(MUSICIANS_ENDPOINT, musicianId.getValue(), BANDS_ENDPOINT, bandId.getValue())));
        return this.exchange(requestEntity, Musician.class).getBody();
    }

    public Musician assignInstrument(MusicianId musicianId, InstrumentId instrumentId) {
        return this.postForObject(route(MUSICIANS_ENDPOINT, musicianId.getValue(), INSTRUMENTS_ENDPOINT),
                instrumentId.getValue(), Musician.class);
    }

    public Musician unassignInstrument(MusicianId musicianId, InstrumentId instrumentId) {
        RequestEntity requestEntity = new RequestEntity(HttpMethod.DELETE,
                URI.create(route(MUSICIANS_ENDPOINT, musicianId.getValue(), INSTRUMENTS_ENDPOINT, instrumentId.getValue())));
        return this.exchange(requestEntity, Musician.class).getBody();
    }

    public Musician startBandSearch(MusicianId musicianId, BandSearch bandSearch) {
        return this.postForObject(route(MUSICIANS_ENDPOINT, musicianId.getValue(), BANDSEARCHES_ENDPOINT), bandSearch, Musician.class);
    }

    public Musician stopBandSearch(MusicianId musicianId, BandSearch bandSearch) {
        RequestEntity<BandSearch> requestEntity = new RequestEntity(bandSearch, HttpMethod.DELETE,
                URI.create(route(MUSICIANS_ENDPOINT, musicianId.getValue(), BANDSEARCHES_ENDPOINT)));
        return this.exchange(requestEntity, Musician.class).getBody();
    }

    private String route(String... urlSegments) {
        StringBuilder concatenatedUrlSegments = new StringBuilder();
        for (String urlSegment : urlSegments)
            concatenatedUrlSegments.append("/").append(urlSegment);

        return String.format("http://localhost:%d%s", serverPort, concatenatedUrlSegments);
    }

    public Band startMusicianSearch(BandId bandId, MusicianSearch musicianSearch) {
        return this.postForObject(route(BANDS_ENDPOINT, bandId.getValue(), MUSICIANSEARCHES_ENDPOINT),
                musicianSearch, Band.class);
    }

    public Musician stopMusicianSearch(BandId bandId, MusicianSearch musicianSearch) {
        RequestEntity<BandSearch> requestEntity = new RequestEntity(musicianSearch, HttpMethod.DELETE,
                URI.create(route(BANDS_ENDPOINT, bandId.getValue(), MUSICIANSEARCHES_ENDPOINT)));
        return this.exchange(requestEntity, Musician.class).getBody();
    }

    public Set<Musician> matchMusicianSearch(MusicianSearch musicianSearch) {
        return (Set<Musician>) this.postForObject(route(MATCHES_ENDPOINT, MUSICIANSEARCH_ENDPOINT),
                musicianSearch, Set.class);
    }

    public Set<Band> matchBandSearch(BandSearch bandSearch) {
        return (Set<Band>) this.postForObject(route(MATCHES_ENDPOINT, BANDSEARCH_ENDPOINT),
                bandSearch, Set.class);
    }
}
