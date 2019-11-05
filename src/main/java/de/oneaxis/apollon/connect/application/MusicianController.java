package de.oneaxis.apollon.connect.application;

import de.oneaxis.apollon.connect.domainmodel.musician.Musician;
import de.oneaxis.apollon.connect.domainmodel.musician.MusicianFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("musicians")
public class MusicianController {

    private final MusicianRepositoryImpl musicianRepository;

    public MusicianController(MusicianRepositoryImpl musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @PostMapping
    Musician addMusician() {
        return musicianRepository.save(MusicianFactory.createBlank());
    }

    @GetMapping
    List getAll() {
        return musicianRepository.findAll();
    }
}
