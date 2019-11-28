package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.apollon.connect.model.Repository;

import java.util.Set;

public interface MusicianRepository extends Repository<Musician, MusicianId> {
    Set<Musician> findByBandSearches(BandSearch bandSearch);
}
