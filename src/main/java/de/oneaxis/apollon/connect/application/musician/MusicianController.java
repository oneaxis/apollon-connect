package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.model.SearchLocation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("musicians")
class MusicianController {

    private final MusicianService musicianService;

    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

    @GetMapping
    List<MusicianResponse> getAll() {
        return musicianService.getAll();
    }

    @GetMapping("{id}")
    MusicianResponse getById(@PathVariable String id) {
        return musicianService.getById(id);
    }

    @PostMapping
    MusicianResponse createNew() {
        return musicianService.createNew();
    }

    @PostMapping("{id}/searchLocations")
    MusicianResponse addSearchLocation(@PathVariable String id, @RequestBody SearchLocation searchLocation) {
        return musicianService.addSearchLocation(id, searchLocation);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable String id) {
        musicianService.deleteById(id);
    }
}
