package de.oneaxis.apollon.connect.application.matches;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandRepository;
import de.oneaxis.apollon.connect.model.band.MusicianSearch;
import de.oneaxis.apollon.connect.model.matches.MatcherService;
import de.oneaxis.apollon.connect.model.musician.BandSearch;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("matches")
public class MatchesController {

    private final MatcherService matcherService;

    public MatchesController(BandRepository bandRepository, MusicianRepository musicianRepository) {
        this.matcherService = new MatcherService(bandRepository, musicianRepository);
    }

    @PostMapping("bandSearch")
    Set<Band> matchBandSearch(@RequestBody BandSearch bandSearch) {
        return this.matcherService.matchBandSearchWithBands(bandSearch);
    }

    @PostMapping("musicianSearch")
    Set<Musician> matchMusicianSearch(@RequestBody MusicianSearch musicianSearch) {
        return this.matcherService.matchMusicianSearchWithMusicians(musicianSearch);
    }
}
