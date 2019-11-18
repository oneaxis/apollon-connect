package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.apollon.connect.model.Location;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.ddd.conceptual.AggregateRoot;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AggregateRoot
public class Musician {
    public final MusicianId id;
    public final Set<InstrumentId> instruments;
    public final Set<BandId> bands = new HashSet<>();
    public final Set<BandSearch> bandSearches = new HashSet<>();

    public Musician(MusicianId id, Set<InstrumentId> instruments) throws MusicianWithoutInstrumentException {
        this.id = Objects.requireNonNull(id);

        if (instruments == null || instruments.isEmpty()) throw new MusicianWithoutInstrumentException();
        this.instruments = instruments;
    }

    public void startNewBandSearch(Location location) {
        BandSearch bandSearch = new BandSearch(Objects.requireNonNull(location));
        this.bandSearches.add(bandSearch);
    }

    public void stopBandSearch(BandSearch bandSearch) {
        this.bandSearches.remove(Objects.requireNonNull(bandSearch));
    }

    public void addBand(BandId id) {
        this.bands.add(Objects.requireNonNull(id));
    }
}
