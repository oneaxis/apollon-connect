package de.oneaxis.apollon.connect.application.match;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandRepository;
import de.oneaxis.apollon.connect.model.band.MusicianSearch;
import de.oneaxis.apollon.connect.model.match.MatchService;
import de.oneaxis.apollon.connect.model.musician.BandSearch;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static de.oneaxis.apollon.connect.application.RestEndpoints.*;

@RestController
@RequestMapping(MATCHES)
public class MatchController {

    private final MatchService matchService;

    public MatchController(BandRepository bandRepository, MusicianRepository musicianRepository) {
        this.matchService = new MatchService(bandRepository, musicianRepository);
    }

    @PostMapping(BAND_SEARCHES)
    Set<Band> matchBandSearch(@RequestBody BandSearch bandSearch) {
        return this.matchService.matchBandSearchWithBands(bandSearch);
    }

    @PostMapping(MUSICIAN_SEARCHES)
    Set<Musician> matchMusicianSearch(@RequestBody MusicianSearch musicianSearch) {
        return this.matchService.matchMusicianSearchWithMusicians(musicianSearch);
    }
}
