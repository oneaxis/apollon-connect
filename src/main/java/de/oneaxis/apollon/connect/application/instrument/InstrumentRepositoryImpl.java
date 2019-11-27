package de.oneaxis.apollon.connect.application.instrument;

import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.instrument.InstrumentRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepositoryImpl extends InstrumentRepository, MongoRepository<Instrument, InstrumentId> {

}
