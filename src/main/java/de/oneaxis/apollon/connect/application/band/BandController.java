package de.oneaxis.apollon.connect.application.band;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("bands")
class BandController {

    private final BandRepositoryImpl bandRepository;

    BandController(BandRepositoryImpl bandRepository) {
        this.bandRepository = bandRepository;
    }

    @GetMapping("new")
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

    @PostMapping("{id}/name")
    Band updateName(@PathVariable String id, @RequestBody String name) {
        Band band = this.bandRepository.findById(new BandId(id)).orElseThrow();
        band.setName(name);
        return this.bandRepository.save(band);
    }
}
