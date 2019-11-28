package de.oneaxis.apollon.connect.model.matches;

import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.band.BandRepository;
import de.oneaxis.apollon.connect.model.band.MusicianSearch;
import de.oneaxis.apollon.connect.model.musician.BandSearch;
import de.oneaxis.apollon.connect.model.musician.Musician;
import de.oneaxis.apollon.connect.model.musician.MusicianRepository;
import de.oneaxis.ddd.conceptual.Service;

import java.util.Set;

@Service
public class MatcherService {

    private final BandRepository bandRepository;
    private final MusicianRepository musicianRepository;

    public MatcherService(BandRepository bandRepository, MusicianRepository musicianRepository) {
        this.bandRepository = bandRepository;
        this.musicianRepository = musicianRepository;
    }

    public Set<Band> matchBandSearchWithBands(BandSearch bandSearch) {
        MusicianSearch musicianSearch = MusicianSearch.builder()
                .postalCode(bandSearch.getPostalCode())
                .instrumentId(bandSearch.getInstrumentId())
                .build();

        return this.bandRepository.findByMusicianSearches(musicianSearch);
    }

    public Set<Musician> matchMusicianSearchWithMusicians(MusicianSearch musicianSearch) {
        BandSearch bandSearch = BandSearch.builder()
                .postalCode(musicianSearch.getPostalCode())
                .instrumentId(musicianSearch.getInstrumentId())
                .build();

        return this.musicianRepository.findByBandSearches(bandSearch);
    }
}
