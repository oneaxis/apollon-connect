package de.oneaxis.apollon.connect.model.band;

import de.oneaxis.ddd.conceptual.AggregateRoot;

import java.util.Objects;
import java.util.Set;

@AggregateRoot
public class Band {
    public final BandId id;
    public final BandName name;
    public final Set<MusicianSearch> musicianSearches;

    public Band(BandId id, BandName name, Set<MusicianSearch> musicianSearches) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.musicianSearches = musicianSearches;
    }
}
