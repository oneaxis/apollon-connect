package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.band.BandRepositoryImpl;
import de.oneaxis.apollon.connect.application.gear.GearRepositoryImpl;
import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.apollon.connect.model.SearchLocationAlreadyAssignedException;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.gear.Gear;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianFactory;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicianService {

    private final MusicianRepositoryImpl musicianRepository;
    private final BandRepositoryImpl bandRepository;
    private final GearRepositoryImpl gearRepository;

    MusicianService(MusicianRepositoryImpl musicianRepository, BandRepositoryImpl bandRepository, GearRepositoryImpl gearRepository) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
        this.gearRepository = gearRepository;
    }

    List<MusicianResponse> getAll() {
        return musicianRepository.findAll().stream()
                .map(this::createWithChildren)
                .collect(Collectors.toList());
    }

    private MusicianResponse createWithChildren(Musician musician) {
        List<Band> bands = musician.bands.stream()
                .map(id -> bandRepository.findById(id).orElseThrow())
                .collect(Collectors.toList());

        List<Gear> gearList = musician.gearSet.stream()
                .map(id -> gearRepository.findById(id).orElseThrow())
                .collect(Collectors.toList());

        return new MusicianResponse(musician, bands, gearList);
    }

    MusicianResponse getById(String id) {
        Musician musician = musicianRepository.findById(new MusicianId(id)).orElseThrow();
        return createWithChildren(musician);
    }

    MusicianResponse createNew() {
        Musician musician = musicianRepository.save(MusicianFactory.createBlank());
        return createWithChildren(musician);
    }

    MusicianResponse addSearchLocation(String id, SearchLocation searchLocation) {
        Musician musician = musicianRepository.findById(new MusicianId(id)).orElseThrow();
        try {
            musician.addSearchLocation(searchLocation.postalCode);
            return createWithChildren(musicianRepository.save(musician));
        } catch (SearchLocationAlreadyAssignedException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    void deleteById(String id) {
        musicianRepository.deleteById(new MusicianId(id));
    }
}

