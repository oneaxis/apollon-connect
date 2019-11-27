package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.band.BandRepositoryImpl;
import de.oneaxis.apollon.connect.application.instrument.InstrumentRepositoryImpl;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("musicians")
class MusicianController {

    private final MusicianRepositoryImpl musicianRepository;
    private final BandRepositoryImpl bandRepository;
    private final InstrumentRepositoryImpl instrumentRepository;

    MusicianController(MusicianRepositoryImpl musicianRepository, BandRepositoryImpl bandRepository, InstrumentRepositoryImpl instrumentRepository) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
        this.instrumentRepository = instrumentRepository;
    }

    @GetMapping("new")
    Musician createNewMusician() {
        MusicianId musicianId = new MusicianId(ObjectId.get().toString());
        return this.musicianRepository.save(Musician.builder().id(musicianId).build());
    }

    @GetMapping("{id}")
    Musician getMusician(@PathVariable String id) {
        MusicianId musicianId = new MusicianId(id);
        return this.musicianRepository.findById(musicianId).orElseThrow();
    }

    @DeleteMapping("{id}/bands/{bandId}")
    Musician leaveBand(@PathVariable String id, @PathVariable String bandId) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        Band band = this.bandRepository.findById(new BandId(bandId)).orElseThrow();

        musician.leaveBand(band.getId());
        return this.musicianRepository.save(musician);
    }

    @PostMapping("{id}/bands")
    Musician joinBand(@PathVariable String id, @RequestBody String bandId) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        Band band = this.bandRepository.findById(new BandId(bandId)).orElseThrow();

        musician.joinBand(band.getId());
        return this.musicianRepository.save(musician);
    }

    @DeleteMapping("{id}/instruments/{instrumentId}")
    Musician unassignInstrument(@PathVariable String id, @PathVariable String instrumentId) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        Instrument instrument = this.instrumentRepository.findById(new InstrumentId(instrumentId)).orElseThrow();

        musician.unassignInstrument(instrument.getId());
        return this.musicianRepository.save(musician);
    }

    @PostMapping("{id}/instruments")
    Musician assignInstrument(@PathVariable String id, @RequestBody String instrumentId) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        Instrument instrument = this.instrumentRepository.findById(new InstrumentId(instrumentId)).orElseThrow();

        musician.assignInstrument(instrument.getId());
        return this.musicianRepository.save(musician);
    }


    @GetMapping
    Set<Musician> getAllMusicians() {
        return new HashSet<>(this.musicianRepository.findAll());
    }
}
