package de.oneaxis.apollon.connect.application.instrument;

import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class InstrumentService {

    final private InstrumentRepositoryImpl instrumentRepository;

    InstrumentService(InstrumentRepositoryImpl instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    InstrumentResponse saveInstrument(InstrumentRequest instrumentRequest) {
        InstrumentId instrumentId = new InstrumentId(ObjectId.get().toString());

        Instrument instrument = this.instrumentRepository.save(
                new Instrument(instrumentId, instrumentRequest.name)
        );

        return InstrumentResponse.fromInstrument(instrument);
    }

    public Instrument getInstrumentFromRequest(InstrumentRequest instrumentRequest) {
        InstrumentId instrumentId = new InstrumentId(instrumentRequest.id);
        return this.instrumentRepository.findById(instrumentId).orElseThrow();
    }

    public InstrumentResponse getById(InstrumentId instrumentId) {
        return InstrumentResponse.fromInstrument(this.instrumentRepository.findById(instrumentId).orElseThrow());
    }
}
