package de.oneaxis.apollon.connect.application.band;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.band.BandName;
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
        BandId bandId = BandId.builder().value(ObjectId.get().toString()).build();
        return this.bandRepository.save(Band.builder().id(bandId).build());
    }

    @GetMapping
    Set<Band> getAllBands() {
        return new HashSet<>(this.bandRepository.findAll());
    }

    @GetMapping("{id}")
    Band getBand(@PathVariable String id) {
        BandId bandId = BandId.builder().value(id).build();
        return this.bandRepository.findById(bandId).orElseThrow();
    }

    @PostMapping("{id}/name")
    Band updateName(@PathVariable String id, @RequestBody String name) {
        Band band = this.bandRepository.findById(BandId.builder().value(id).build()).orElseThrow();
        band.setName(BandName.builder().value(name).build());
        return this.bandRepository.save(band);
    }
}
