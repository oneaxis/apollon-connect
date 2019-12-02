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

import static de.oneaxis.apollon.connect.application.RestEndpoints.*;

public class ApollonConnectAPIClient extends TestRestTemplate {

    private final int serverPort;

    ApollonConnectAPIClient(int serverPort) {
        this.serverPort = serverPort;
    }

    public Musician getNewMusician() {
        return this.getForObject(route(MUSICIANS, NEW), Musician.class);
    }

    public Band getNewBand() {
        return this.getForObject(route(BANDS, NEW), Band.class);
    }

    public Instrument getNewInstrument() {
        return this.getForObject(route(INSTRUMENTS, NEW), Instrument.class);
    }

    public Musician getMusician(MusicianId musicianId) {
        return this.getForObject(route(MUSICIANS, musicianId.getValue()), Musician.class);
    }

    public Instrument updateInstrumentName(InstrumentId instrumentId, String name) {
        return this.postForObject(route(INSTRUMENTS, instrumentId.getValue(), NAME),
                name, Instrument.class);
    }

    public Musician joinBand(MusicianId musicianId, BandId bandId) {
        return this.postForObject(route(MUSICIANS, musicianId.getValue(), BANDS),
                bandId.getValue(), Musician.class);
    }

    public Musician leaveBand(MusicianId musicianId, BandId bandId) {
        RequestEntity requestEntity = new RequestEntity(HttpMethod.DELETE,
                URI.create(route(MUSICIANS, musicianId.getValue(), BANDS, bandId.getValue())));
        return this.exchange(requestEntity, Musician.class).getBody();
    }

    public Musician assignInstrument(MusicianId musicianId, InstrumentId instrumentId) {
        return this.postForObject(route(MUSICIANS, musicianId.getValue(), INSTRUMENTS),
                instrumentId.getValue(), Musician.class);
    }

    public Musician unassignInstrument(MusicianId musicianId, InstrumentId instrumentId) {
        RequestEntity requestEntity = new RequestEntity(HttpMethod.DELETE,
                URI.create(route(MUSICIANS, musicianId.getValue(), INSTRUMENTS, instrumentId.getValue())));
        return this.exchange(requestEntity, Musician.class).getBody();
    }

    public Musician startBandSearch(MusicianId musicianId, BandSearch bandSearch) {
        return this.postForObject(route(MUSICIANS, musicianId.getValue(), BAND_SEARCHES), bandSearch, Musician.class);
    }

    @SuppressWarnings("unchecked")
    public Musician stopBandSearch(MusicianId musicianId, BandSearch bandSearch) {
        RequestEntity<BandSearch> requestEntity = new RequestEntity(bandSearch, HttpMethod.DELETE,
                URI.create(route(MUSICIANS, musicianId.getValue(), BAND_SEARCHES)));
        return this.exchange(requestEntity, Musician.class).getBody();
    }

    public Band startMusicianSearch(BandId bandId, MusicianSearch musicianSearch) {
        return this.postForObject(route(BANDS, bandId.getValue(), MUSICIAN_SEARCHES),
                musicianSearch, Band.class);
    }

    @SuppressWarnings("unchecked")
    public Musician stopMusicianSearch(BandId bandId, MusicianSearch musicianSearch) {
        RequestEntity<BandSearch> requestEntity = new RequestEntity(musicianSearch, HttpMethod.DELETE,
                URI.create(route(BANDS, bandId.getValue(), MUSICIAN_SEARCHES)));
        return this.exchange(requestEntity, Musician.class).getBody();
    }

    @SuppressWarnings("unchecked")
    public Set<Musician> matchMusicianSearch(MusicianSearch musicianSearch) {
        return (Set<Musician>) this.postForObject(route(MATCHES, MUSICIAN_SEARCHES),
                musicianSearch, Set.class);
    }

    @SuppressWarnings("unchecked")
    public Set<Band> matchBandSearch(BandSearch bandSearch) {
        return (Set<Band>) this.postForObject(route(MATCHES, BAND_SEARCHES),
                bandSearch, Set.class);
    }

    private String route(String... urlSegments) {
        StringBuilder concatenatedUrlSegments = new StringBuilder();
        for (String urlSegment : urlSegments)
            concatenatedUrlSegments.append("/").append(urlSegment);

        return String.format("http://localhost:%d%s", serverPort, concatenatedUrlSegments);
    }
}
