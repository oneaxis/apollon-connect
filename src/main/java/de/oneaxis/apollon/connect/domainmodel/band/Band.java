package de.oneaxis.apollon.connect.domainmodel.band;

import de.oneaxis.apollon.connect.domainmodel.musician.MusicianId;
import de.oneaxis.ddd.sharedkernel.AggregateRoot;

import java.util.List;

@AggregateRoot
public class Band {
    public final BandId id;
    public final BandName name;
    public final List<MusicianId> musicians;

    public Band(BandId id, BandName name, List<MusicianId> musicians) {
        this.id = id;
        this.name = name;
        this.musicians = musicians;
    }
}
