package de.oneaxis.apollon.connect.application;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class ApollonConnectAPIClient extends TestRestTemplate {

    private final String MUSICIANS_ENDPOINT = "musicians";
    private final String INSTRUMENTS_ENDPOINT = "instruments";
    private final String BANDS_ENDPOINT = "bands";
    private final String NEW_OBJECT_ENDPOINT = "new";

    private final int serverPort;

    public ApollonConnectAPIClient(int serverPort) {
        this.serverPort = serverPort;
    }

    public Musician getNewMusician() {
        return this.getForObject(route(MUSICIANS_ENDPOINT, NEW_OBJECT_ENDPOINT), Musician.class);
    }

    public Musician getMusician(MusicianId musicianId) {
        return this.getForObject(route(MUSICIANS_ENDPOINT, musicianId.getValue()), Musician.class);
    }

    public Musician joinBand(MusicianId musicianId, BandId bandId) {
        return this.postForObject(route(MUSICIANS_ENDPOINT, musicianId.getValue(), BANDS_ENDPOINT),
                bandId.getValue(), Musician.class);
    }

    public void leaveBand(MusicianId musicianId, BandId bandId) {
        this.delete(route(MUSICIANS_ENDPOINT, musicianId.getValue(), BANDS_ENDPOINT, bandId.getValue()));
    }

    public Musician assignInstrument(MusicianId musicianId, InstrumentId instrumentId) {
        return this.postForObject(route(MUSICIANS_ENDPOINT, musicianId.getValue(), INSTRUMENTS_ENDPOINT),
                instrumentId.getValue(), Musician.class);
    }

    public void unassignInstrument(MusicianId musicianId, InstrumentId instrumentId) {
        this.delete(route(MUSICIANS_ENDPOINT, musicianId.getValue(), INSTRUMENTS_ENDPOINT, instrumentId.getValue()));
    }

    public Band getNewBand() {
        return this.getForObject(route(BANDS_ENDPOINT, NEW_OBJECT_ENDPOINT), Band.class);
    }

    public Instrument getNewInstrument() {
        return this.getForObject(route(INSTRUMENTS_ENDPOINT, NEW_OBJECT_ENDPOINT), Instrument.class);
    }

    private String route(String... urlSegments) {
        StringBuilder concatenatedUrlSegments = new StringBuilder();
        for (String urlSegment : urlSegments)
            concatenatedUrlSegments.append("/").append(urlSegment);

        return String.format("http://localhost:%d%s", serverPort, concatenatedUrlSegments);
    }
}
