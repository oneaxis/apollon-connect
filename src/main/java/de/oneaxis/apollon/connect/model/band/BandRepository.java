package de.oneaxis.apollon.connect.model.band;


import de.oneaxis.apollon.connect.model.Repository;

import java.util.Set;

public interface BandRepository extends Repository<Band, BandId> {
    Set<Band> findByMusicianSearches(MusicianSearch musicianSearch);
}
