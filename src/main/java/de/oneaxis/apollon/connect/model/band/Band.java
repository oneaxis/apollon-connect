package de.oneaxis.apollon.connect.model.band;

import de.oneaxis.ddd.conceptual.AggregateRoot;

import java.util.Objects;
import java.util.Set;

@AggregateRoot
class Band {
    final BandId id;
    final BandName name;
    final Set<MusicianSearch> musicianSearches;

    Band(BandId id, BandName name, Set<MusicianSearch> musicianSearches) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.musicianSearches = musicianSearches;
    }
}
