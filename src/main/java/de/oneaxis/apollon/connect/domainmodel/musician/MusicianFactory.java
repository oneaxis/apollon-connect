package de.oneaxis.apollon.connect.domainmodel.musician;

import de.oneaxis.apollon.connect.domainmodel.band.BandId;
import de.oneaxis.apollon.connect.domainmodel.gear.GearId;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class MusicianFactory {

    public static Musician createBlank() {

        MusicianId id = new MusicianId(new ObjectId());

        return new Musician(id, null, new ArrayList<BandId>(), new ArrayList<GearId>());
    }
}
