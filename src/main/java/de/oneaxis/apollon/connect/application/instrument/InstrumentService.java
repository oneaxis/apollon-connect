package de.oneaxis.apollon.connect.application.instrument;

import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentWithoutNameException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InstrumentService {

    final private InstrumentRepositoryImpl instrumentRepository;

    InstrumentService(InstrumentRepositoryImpl instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    InstrumentRest saveInstrument(InstrumentRest instrumentRest) {
        try {
            Instrument instrument = this.instrumentRepository.save(new Instrument(instrumentRest.name));
            return InstrumentRest.fromInstrument(instrument);
        } catch (InstrumentWithoutNameException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
