package de.oneaxis.apollon.connect.application.instrument;

import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.instrument.InstrumentRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InstrumentRepositoryImpl extends InstrumentRepository, MongoRepository<Instrument, InstrumentId> {
    Optional<Instrument> findByName(String instrumentName);
}
