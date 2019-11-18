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

    @PostMapping
    MusicianResponse createNewMusician(@RequestBody MusicianRequest musicianRequest) {
        return this.musicianService.createNewMusician(musicianRequest);
    }

    @GetMapping
    Set<MusicianResponse> getAllMusicians() {
        return this.musicianService.getAllMusicians();
    }
}
