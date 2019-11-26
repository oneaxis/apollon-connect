package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.band.BandRepositoryImpl;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianId;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("musicians")
class MusicianController {

    private final MusicianRepositoryImpl musicianRepository;
    private final BandRepositoryImpl bandRepository;

    MusicianController(MusicianRepositoryImpl musicianRepository, BandRepositoryImpl bandRepository) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
    }

    @GetMapping("new")
    Musician createNewMusician() {
        MusicianId musicianId = MusicianId.builder().value(ObjectId.get().toString()).build();
        return this.musicianRepository.save(Musician.builder().id(musicianId).build());
    }

    @GetMapping("{id}")
    Musician getMusician(@PathVariable String id) {
        MusicianId musicianId = MusicianId.builder().value(id).build();
        return this.musicianRepository.findById(musicianId).orElseThrow();
    }

    @DeleteMapping("{id}/bands/{bandId}")
    Musician leaveBand(@PathVariable String id, @PathVariable String bandId) {
        Musician musician = this.musicianRepository.findById(MusicianId.builder().value(id).build()).orElseThrow();
        Band band = this.bandRepository.findById(BandId.builder().value(bandId).build()).orElseThrow();

        musician.leaveBand(band.getId());
        return this.musicianRepository.save(musician);
    }

    @PostMapping("{id}/bands")
    Musician joinBand(@PathVariable String id, @RequestBody String bandId) {
        Musician musician = this.musicianRepository.findById(MusicianId.builder().value(id).build()).orElseThrow();
        Band band = this.bandRepository.findById(BandId.builder().value(bandId).build()).orElseThrow();

        musician.joinBand(band.getId());
        return this.musicianRepository.save(musician);
    }


    @GetMapping
    Set<Musician> getAllMusicians() {
        return new HashSet<>(this.musicianRepository.findAll());
    }
}
