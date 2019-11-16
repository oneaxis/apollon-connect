package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.ddd.conceptual.AggregateRoot;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AggregateRoot
class Musician {
    private final MusicianId id;
    private final Set<PlayedInstrument> playedInstruments;
    private Set<BandId> bands = new HashSet<>();
    private Set<BandSearch> bandSearches = new HashSet<>();

    Musician(MusicianId id, Set<PlayedInstrument> playedInstruments) throws MusicianWithoutInstrumentException {
        this.id = Objects.requireNonNull(id);
        this.playedInstruments = playedInstruments;

        if (playedInstruments == null || playedInstruments.isEmpty()) throw new MusicianWithoutInstrumentException();
    }

    public void setBands(Set<BandId> bands) {
        this.bands = bands;
    }

    public void setBandSearches(Set<BandSearch> bandSearches) {
        this.bandSearches = bandSearches;
    }

    public MusicianId getId() {
        return id;
    }

    public Set<PlayedInstrument> getPlayedInstruments() {
        return playedInstruments;
    }

    public Set<BandId> getBands() {
        return bands;
    }

    public Set<BandSearch> getBandSearches() {
        return bandSearches;
    }

    public void startNewBandSearch(SearchLocation searchLocation) {
        BandSearch bandSearch = new BandSearch(Objects.requireNonNull(searchLocation));
        this.bandSearches.add(bandSearch);
    }

    public void addBand(BandId id) {
        this.bands.add(Objects.requireNonNull(id));
    }
}
