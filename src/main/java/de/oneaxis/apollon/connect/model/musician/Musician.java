package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.apollon.connect.model.Location;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.ddd.conceptual.AggregateRoot;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AggregateRoot
public class Musician {
    public final MusicianId id;
    public final Set<InstrumentId> instruments = new HashSet<>();
    public final Set<BandId> bands = new HashSet<>();
    public final Set<BandSearch> bandSearches = new HashSet<>();

    public Musician() {
        this.id = new MusicianId(ObjectId.get().toString());
    }

    public Musician(MusicianId id, Set<InstrumentId> instruments, Set<BandId> bands, Set<BandSearch> bandSearches) {
        this.id = Objects.requireNonNull(id);
        if (Objects.nonNull(instruments) && !instruments.isEmpty())
            this.instruments.addAll(instruments);
        if (Objects.nonNull(bands) && !bands.isEmpty())
            this.bands.addAll(bands);
        if (Objects.nonNull(bandSearches) && !bandSearches.isEmpty())
            this.bandSearches.addAll(bandSearches);
    }

    public void startBandSearch(Location location) {
        BandSearch bandSearch = new BandSearch(Objects.requireNonNull(location));
        this.bandSearches.add(bandSearch);
    }

    public void stopBandSearch(BandSearch bandSearch) {
        this.bandSearches.remove(Objects.requireNonNull(bandSearch));
    }

    public void leaveBand(BandId bandId) {
        this.bands.add(Objects.requireNonNull(bandId));
    }

    public void joinBand(BandId bandId) {
        this.bands.remove(Objects.requireNonNull(bandId));
    }
}
