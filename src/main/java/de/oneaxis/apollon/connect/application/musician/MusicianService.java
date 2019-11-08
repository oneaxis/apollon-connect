package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.SearchLocationResponse;
import de.oneaxis.apollon.connect.application.band.BandRepositoryImpl;
import de.oneaxis.apollon.connect.application.band.BandResponse;
import de.oneaxis.apollon.connect.application.gear.GearRepositoryImpl;
import de.oneaxis.apollon.connect.application.gear.GearResponse;
import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.apollon.connect.model.SearchLocationAlreadyAssignedException;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.gear.Gear;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianFactory;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    private BandResponse createFromBand(Band band) {
        Set<MusicianResponse> musicians = band.musicians.stream()
                .map(musicianRepository::findById)
                .map(Optional::orElseThrow)
                .map(this::createFromMusician)
                .collect(Collectors.toSet());

        Set<SearchLocationResponse> searchLocations = band.searchLocations.stream()
                .map(this::createFromSearchLocation)
                .collect(Collectors.toSet());

        return new BandResponse(band.id.value, band.name.value, musicians, searchLocations);
    }

    private SearchLocationResponse createFromSearchLocation(SearchLocation searchLocation) {
        return new SearchLocationResponse(searchLocation.postalCode);
    }

    private GearResponse createFromGear(Gear gear) {
        return new GearResponse(gear.id.value, gear.type.value);
    }

    private MusicianResponse createFromMusician(Musician musician) {
        Set<BandResponse> bands = musician.bands.stream()
                .map(bandRepository::findById)
                .map(Optional::orElseThrow)
                .map(this::createFromBand)
                .collect(Collectors.toSet());

        Set<GearResponse> gearList = musician.gearSet.stream()
                .map(gearRepository::findById)
                .map(Optional::orElseThrow)
                .map(this::createFromGear)
                .collect(Collectors.toSet());

        return new MusicianResponse(musician.id.value, gearList, bands, musician.searchLocations);
    }
}

