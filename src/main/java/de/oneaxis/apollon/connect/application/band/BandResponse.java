package de.oneaxis.apollon.connect.application.band;

import de.oneaxis.apollon.connect.model.band.Band;

public class BandResponse extends Band {
    public final String id;

    public BandResponse(Band band) {
        super(band.id, band.name, band.musicians, band.searchLocations);
        this.id = band.id.value;
    }
}
