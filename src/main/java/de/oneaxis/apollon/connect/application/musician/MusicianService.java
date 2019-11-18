package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.instrument.InstrumentResponse;
import de.oneaxis.apollon.connect.application.instrument.InstrumentService;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import de.oneaxis.apollon.connect.model.musician.MusicianWithoutInstrumentException;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class MusicianService {

    private final MusicianRepositoryImpl musicianRepository;
    private final InstrumentService instrumentService;

    MusicianService(MusicianRepositoryImpl musicianRepository, InstrumentService instrumentService) {
        this.musicianRepository = musicianRepository;
        this.instrumentService = instrumentService;
    }

    MusicianResponse createNewMusician(MusicianRequest musicianRequest) {
        try {
            MusicianId musicianId = new MusicianId(new ObjectId().toString());

            Set<InstrumentId> instruments = musicianRequest.instruments.stream()
                    .map(this.instrumentService::getInstrumentFromRequest)
                    .map(instrument -> instrument.id)
                    .collect(Collectors.toSet());

            Musician musician = this.musicianRepository.save(new Musician(musicianId, instruments));

            return this.fromMusician(musician);
        } catch (MusicianWithoutInstrumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    Set<MusicianResponse> getAllMusicians() {
        return this.musicianRepository.findAll().stream()
                .map(this::fromMusician)
                .collect(Collectors.toSet());
    }

    private MusicianResponse fromMusician(Musician musician) {

        Set<InstrumentResponse> instruments = musician.instruments.stream()
                .map(this.instrumentService::getById)
                .collect(Collectors.toSet());

        return new MusicianResponse(musician.id.value, instruments);
    }
}

