package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.apollon.connect.model.Location;
import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
public class BandSearch {
    public final Location location;

    BandSearch(Location location) {
        this.location = location;
    }
}
