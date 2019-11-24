package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("musicians")
class MusicianController {

    private final MusicianRepositoryImpl musicianRepository;

    MusicianController(MusicianRepositoryImpl musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @GetMapping("new")
    Musician createNewMusician() {
        return this.musicianRepository.save(new Musician());
    }

    @GetMapping("{id}")
    Musician getMusician(@PathVariable String id) {
        return this.musicianRepository.findById(new MusicianId(id)).orElseThrow();
    }

    @PutMapping
    Musician saveMusician(@RequestBody Musician musician) {
        if (!this.musicianRepository.existsById(musician.id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return this.musicianRepository.save(musician);
    }

    @GetMapping
    Set<Musician> getAllMusicians() {
        return new HashSet<>(this.musicianRepository.findAll());
    }
}
