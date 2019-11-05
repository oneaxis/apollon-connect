package de.oneaxis.apollon.connect.domain;

import de.oneaxis.ddd.shared.AggregateRoot;

import java.util.List;

@AggregateRoot
class Band {
    private final BandId id;
    private final List<MusicianId> musicians;
}
