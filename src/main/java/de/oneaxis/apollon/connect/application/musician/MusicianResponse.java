package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.gear.Gear;
import de.oneaxis.apollon.connect.model.musician.Musician;

import java.util.List;

public class MusicianResponse extends Musician {
    public final String id;
    public final List<Band> bands;
    public final List<Gear> gearList;

    MusicianResponse(Musician musician, List<Band> bands, List<Gear> gearList) {
        super(musician.id, musician.searchLocations, musician.bands, musician.gearSet);
        this.id = musician.id.value;
        this.bands = bands;
        this.gearList = gearList;
    }
}
