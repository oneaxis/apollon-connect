package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.apollon.connect.model.SearchLocationAlreadyAssigned;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianFactory;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("musicians")
class MusicianController {

    private final MusicianRepositoryImpl musicianRepository;

    MusicianController(MusicianRepositoryImpl musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @GetMapping
    List getAll() {
        return musicianRepository.findAll();
    }

    @GetMapping("{id}")
    Musician getById(@PathVariable MusicianId id) {
        return musicianRepository.findById(id).orElseThrow();
    }

    @PostMapping
    Musician createNew() {
        return musicianRepository.save(MusicianFactory.createBlank());
    }

    @PostMapping("{id}/searchLocations")
    Musician addSearchLocation(@PathVariable MusicianId id, @RequestBody SearchLocation searchLocation) throws SearchLocationAlreadyAssigned {
        Musician musician = musicianRepository.findById(id).orElseThrow();
        musician.addSearchLocation(searchLocation.postalCode);
        return musicianRepository.save(musician);
    }

    @DeleteMapping("{id}")
    void remove(@PathVariable MusicianId id) {
        musicianRepository.deleteById(id);
    }
}
