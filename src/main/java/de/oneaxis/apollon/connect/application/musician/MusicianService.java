package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.band.BandRepositoryImpl;
import de.oneaxis.apollon.connect.application.gear.GearRepositoryImpl;
import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.apollon.connect.model.SearchLocationAlreadyAssignedException;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.gear.Gear;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianFactory;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
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
                .map(this::createFromMusician)
                .collect(Collectors.toList());
    }

    MusicianResponse getById(String id) {
        Musician musician = musicianRepository.findById(new MusicianId(id)).orElseThrow();
        return createFromMusician(musician);
    }

    MusicianResponse createNew() {
        Musician musician = musicianRepository.save(MusicianFactory.createBlank());
        return createFromMusician(musician);
    }

    private MusicianResponse createFromMusician(Musician musician) {
        Set<Band> bands = musician.bands.stream()
                .map(bandRepository::findById)
                .map(Optional::orElseThrow)
                .collect(Collectors.toSet());

        Set<Gear> gearList = musician.gearSet.stream()
                .map(gearRepository::findById)
                .map(Optional::orElseThrow)
                .collect(Collectors.toSet());

        return new MusicianResponse(musician.id.value, gearList, bands, musician.searchLocations);
    }

    MusicianResponse addSearchLocation(String id, SearchLocation searchLocation) {
        Musician musician = musicianRepository.findById(new MusicianId(id)).orElseThrow();
        try {
            musician.addSearchLocation(searchLocation.postalCode);
            return createFromMusician(musicianRepository.save(musician));
        } catch (SearchLocationAlreadyAssignedException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }


    }

    void deleteById(String id) {
        musicianRepository.deleteById(new MusicianId(id));
    }
}

