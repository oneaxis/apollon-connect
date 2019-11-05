package de.oneaxis.apollon.connect.domain;

import de.oneaxis.ddd.shared.AggregateRoot;

import java.util.List;

@AggregateRoot
class Musician {
    private final MusicianId id;
    private final Name name;
    private final List<BandId> bands;
    private final List<GearId> gearList;
}
