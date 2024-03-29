package de.oneaxis.apollon.connect.application.instrument;

import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static de.oneaxis.apollon.connect.application.RestEndpoints.*;

@RestController
@RequestMapping(INSTRUMENTS)
class InstrumentController {

    private final InstrumentRepositoryImpl instrumentRepository;

    InstrumentController(InstrumentRepositoryImpl instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @GetMapping(NEW)
    Instrument createNewInstrument() {
        InstrumentId instrumentId = new InstrumentId(ObjectId.get().toString());
        return this.instrumentRepository.save(Instrument.builder().id(instrumentId).build());
    }

    @GetMapping
    Set<Instrument> getAllInstruments() {
        return new HashSet<>(this.instrumentRepository.findAll());
    }

    @GetMapping("{id}")
    Instrument getInstrument(@PathVariable String id) {
        InstrumentId instrumentId = new InstrumentId(id);
        return this.instrumentRepository.findById(instrumentId).orElseThrow();
    }

    @PostMapping("{id}/" + NAME)
    Instrument updateInstrumentName(@PathVariable String id, @RequestBody String name) {
        Instrument instrument = this.instrumentRepository.findById(new InstrumentId(id)).orElseThrow();
        instrument.setName(name);
        return this.instrumentRepository.save(instrument);
    }
}
