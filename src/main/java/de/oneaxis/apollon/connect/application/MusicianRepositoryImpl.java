package de.oneaxis.apollon.connect.application;

import de.oneaxis.apollon.connect.domainmodel.musician.Musician;
import de.oneaxis.apollon.connect.domainmodel.musician.MusicianId;
import de.oneaxis.apollon.connect.domainmodel.musician.MusicianRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicianRepositoryImpl extends MusicianRepository, MongoRepository<Musician, MusicianId> {
}
