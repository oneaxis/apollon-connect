package de.oneaxis.apollon.connect.model.band;

import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import de.oneaxis.ddd.sharedkernel.AggregateRoot;

import java.util.Set;

@AggregateRoot
public class Band {
    public final BandId id;
    public final BandName name;
    public final Set<MusicianId> musicians;
    public final Set<SearchLocation> searchLocations;

    public Band(BandId id, BandName name, Set<MusicianId> musicians, Set<SearchLocation> searchLocations) {
        this.id = id;
        this.name = name;
        this.musicians = musicians;
        this.searchLocations = searchLocations;
    }
}
