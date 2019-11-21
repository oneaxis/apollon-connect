package de.oneaxis.apollon.connect.application.musician;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("musicians")
class MusicianController {

    private final MusicianService musicianService;

    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

    @GetMapping("new")
    MusicianRest createNewMusician() {
        return this.musicianService.createNewMusician();
    }

    @PutMapping
    MusicianRest saveMusician(@RequestBody MusicianRest musicianRest) {
        return this.musicianService.saveMusician(musicianRest);
    }

    @GetMapping
    Set<MusicianRest> getAllMusicians() {
        return this.musicianService.getAllMusicians();
    }
}
