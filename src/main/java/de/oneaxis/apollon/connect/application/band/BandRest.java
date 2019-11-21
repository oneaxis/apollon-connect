package de.oneaxis.apollon.connect.application.band;

import de.oneaxis.apollon.connect.model.band.Band;

public class BandRest {
    public final String id;

    public BandRest(String id) {
        this.id = id;
    }

    public static BandRest fromBand(Band band) {
        return new BandRest(band.id.value);
    }
}
