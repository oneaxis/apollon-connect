package de.oneaxis.apollon.connect.model.band;

import de.oneaxis.apollon.connect.model.Location;
import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
public class MusicianSearch {
    public final Location location;

    public MusicianSearch(Location location) {
        this.location = location;
    }
}
