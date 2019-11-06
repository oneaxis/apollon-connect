package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import de.oneaxis.apollon.connect.model.musician.MusicianRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicianRepositoryImpl extends MusicianRepository, MongoRepository<Musician, MusicianId> {
}
