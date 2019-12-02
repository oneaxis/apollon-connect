package de.oneaxis.apollon.connect.application.band;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.band.MusicianSearch;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static de.oneaxis.apollon.connect.application.RestEndpoints.*;

@RestController
@RequestMapping(BANDS)
public class BandController {

    private final BandRepositoryImpl bandRepository;

    BandController(BandRepositoryImpl bandRepository) {
        this.bandRepository = bandRepository;
    }

    @GetMapping(NEW)
    Band createNewBand() {
        BandId bandId = new BandId(ObjectId.get().toString());
        return this.bandRepository.save(Band.builder().id(bandId).build());
    }

    @GetMapping
    Set<Band> getAllBands() {
        return new HashSet<>(this.bandRepository.findAll());
    }

    @GetMapping("{id}")
    Band getBand(@PathVariable String id) {
        BandId bandId = new BandId(id);
        return this.bandRepository.findById(bandId).orElseThrow();
    }

    @PostMapping("{id}/" + NEW)
    Band updateName(@PathVariable String id, @RequestBody String name) {
        Band band = this.bandRepository.findById(new BandId(id)).orElseThrow();
        band.setName(name);
        return this.bandRepository.save(band);
    }

    @DeleteMapping("{id}/" + MUSICIAN_SEARCHES)
    Band stopMusicianSearch(@PathVariable String id, @RequestBody MusicianSearch musicianSearch) {
        Band band = this.bandRepository.findById(new BandId(id)).orElseThrow();
        band.stopMusicianSearch(musicianSearch);
        return this.bandRepository.save(band);
    }

    @PostMapping("{id}/" + MUSICIAN_SEARCHES)
    Band startMusicianSearch(@PathVariable String id, @RequestBody MusicianSearch musicianSearch) {
        Band band = this.bandRepository.findById(new BandId(id)).orElseThrow();
        band.startMusicianSearch(musicianSearch);
        return this.bandRepository.save(band);
    }
}
