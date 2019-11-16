package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.band.BandRepositoryImpl;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import de.oneaxis.apollon.connect.model.musician.MusicianWithoutInstrumentException;
import de.oneaxis.apollon.connect.model.musician.PlayedInstrument;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class MusicianService {

    private final MusicianRepositoryImpl musicianRepository;
    private final BandRepositoryImpl bandRepository;

    MusicianService(MusicianRepositoryImpl musicianRepository, BandRepositoryImpl bandRepository) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
    }

    MusicianResponse createNewMusician(MusicianRequest musicianRequest) {
        try {
            MusicianId musicianId = new MusicianId(new ObjectId().toString());
            PlayedInstrument playedInstrument = new PlayedInstrument(musicianRequest.playedInstrument);
            Musician musician = new Musician(musicianId, Set.of(playedInstrument));
            return MusicianResponse.fromMusician(this.musicianRepository.save(musician));
        } catch (MusicianWithoutInstrumentException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    Set<MusicianResponse> getAllMusicians() {
        return this.musicianRepository.findAll().stream()
                .map(MusicianResponse::fromMusician)
                .collect(Collectors.toSet());
    }
}

