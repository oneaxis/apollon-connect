package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.band.BandRepositoryImpl;
import de.oneaxis.apollon.connect.application.instrument.InstrumentRepositoryImpl;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.instrument.Instrument;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.musician.BandSearch;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import static de.oneaxis.apollon.connect.application.RestEndpoints.*;

@RestController
@RequestMapping(MUSICIANS)
class MusicianController {

    private final MusicianRepositoryImpl musicianRepository;
    private final BandRepositoryImpl bandRepository;
    private final InstrumentRepositoryImpl instrumentRepository;

    MusicianController(MusicianRepositoryImpl musicianRepository, BandRepositoryImpl bandRepository,
                       InstrumentRepositoryImpl instrumentRepository) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
        this.instrumentRepository = instrumentRepository;
    }

    @GetMapping(NEW)
    Musician createNewMusician() {
        MusicianId musicianId = new MusicianId(ObjectId.get().toString());
        return this.musicianRepository.save(Musician.builder().id(musicianId).build());
    }

    @GetMapping("{id}")
    Musician getMusician(@PathVariable String id) {
        MusicianId musicianId = new MusicianId(id);
        return this.musicianRepository.findById(musicianId).orElseThrow();
    }

    @DeleteMapping("{id}/" + BANDS + "/{bandId}")
    Musician leaveBand(@PathVariable String id, @PathVariable String bandId) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        Band band = this.bandRepository.findById(new BandId(bandId)).orElseThrow();

        musician.leaveBand(band.getId());
        return this.musicianRepository.save(musician);
    }

    @PostMapping("{id}/" + BANDS)
    Musician joinBand(@PathVariable String id, @RequestBody String bandId) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        Band band = this.bandRepository.findById(new BandId(bandId)).orElseThrow();

        musician.joinBand(band.getId());
        return this.musicianRepository.save(musician);
    }

    @DeleteMapping("{id}/" + INSTRUMENTS + "/{instrumentId}")
    Musician unassignInstrument(@PathVariable String id, @PathVariable String instrumentId) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        Instrument instrument = this.instrumentRepository.findById(new InstrumentId(instrumentId)).orElseThrow();

        musician.unassignInstrument(instrument.getId());
        return this.musicianRepository.save(musician);
    }

    @PostMapping("{id}/" + INSTRUMENTS)
    Musician assignInstrument(@PathVariable String id, @RequestBody String instrumentId) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        Instrument instrument = this.instrumentRepository.findById(new InstrumentId(instrumentId)).orElseThrow();

        musician.assignInstrument(instrument.getId());
        return this.musicianRepository.save(musician);
    }

    @DeleteMapping("{id}/" + BAND_SEARCHES)
    Musician stopBandSearch(@PathVariable String id, @RequestBody BandSearch bandSearch) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        musician.stopBandSearch(bandSearch);
        return this.musicianRepository.save(musician);
    }

    @PostMapping("{id}/" + BAND_SEARCHES)
    Musician startBandSearch(@PathVariable String id, @RequestBody BandSearch bandSearch) {
        Musician musician = this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
        musician.startBandSearch(bandSearch);
        return this.musicianRepository.save(musician);
    }
}
