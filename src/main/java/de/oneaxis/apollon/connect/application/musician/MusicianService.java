package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.band.BandRepositoryImpl;
import de.oneaxis.apollon.connect.application.band.BandRest;
import de.oneaxis.apollon.connect.application.instrument.InstrumentRepositoryImpl;
import de.oneaxis.apollon.connect.application.instrument.InstrumentRest;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class MusicianService {

    private final MusicianRepositoryImpl musicianRepository;
    private final BandRepositoryImpl bandRepository;
    private final InstrumentRepositoryImpl instrumentRepository;

    MusicianService(MusicianRepositoryImpl musicianRepository, BandRepositoryImpl bandRepository,
                    InstrumentRepositoryImpl instrumentRepository) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
        this.instrumentRepository = instrumentRepository;
    }

    MusicianRest createNewMusician() {
        Musician musician = this.musicianRepository.save(new Musician());
        return this.fromMusician(musician);
    }

    MusicianRest saveMusician(MusicianRest musicianRest) {

        Set<InstrumentId> instruments = musicianRest.instruments.stream()
                .map(instrumentRest -> new InstrumentId(instrumentRest.id))
                .map(instrumentId -> this.instrumentRepository.findById(instrumentId).orElseThrow())
                .map(instrument -> instrument.id)
                .collect(Collectors.toSet());

        Set<BandId> bands = musicianRest.bands.stream()
                .map(bandRest -> new BandId(bandRest.id))
                .map(bandId -> this.bandRepository.findById(bandId).orElseThrow())
                .map(band -> band.id)
                .collect(Collectors.toSet());

        Musician musician = new Musician(new MusicianId(musicianRest.id), instruments, bands, musicianRest.bandSearches);

        return this.fromMusician(this.musicianRepository.save(musician));
    }

    Set<MusicianRest> getAllMusicians() {
        return this.musicianRepository.findAll().stream()
                .map(this::fromMusician)
                .collect(Collectors.toSet());
    }

    private MusicianRest fromMusician(Musician musician) {

        Set<InstrumentRest> instruments = musician.instruments.stream()
                .map(instrumentId -> this.instrumentRepository.findById(instrumentId).orElseThrow())
                .map(InstrumentRest::fromInstrument)
                .collect(Collectors.toSet());

        Set<BandRest> bands = musician.bands.stream()
                .map(bandId -> this.bandRepository.findById(bandId).orElseThrow())
                .map(BandRest::fromBand)
                .collect(Collectors.toSet());

        return new MusicianRest(musician.id.value, instruments, bands, musician.bandSearches);
    }
}

