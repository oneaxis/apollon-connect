package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.ddd.conceptual.Repository;

@Repository
public interface MusicianRepository {
    Musician findById(MusicianId id);

    Musician save(Musician musician);

    Musician update(Musician musician);
}
