package de.oneaxis.apollon.connect.model.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.model.Location;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.ddd.conceptual.Aggregate;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Aggregate
@Getter
@Setter
public class Musician {
    private final MusicianId id;
    @Singular
    private Set<InstrumentId> instruments = new HashSet<>();
    @Singular
    private Set<BandId> bands = new HashSet<>();
    @Singular
    private Set<BandSearch> bandSearches = new HashSet<>();

    @Builder
    @JsonCreator
    public Musician(@JsonProperty("id") MusicianId id) {
        this.id = id;
    }

    public void startBandSearch(Location location) {
        BandSearch bandSearch = new BandSearch(Objects.requireNonNull(location));
        this.bandSearches.add(bandSearch);
    }

    public void stopBandSearch(BandSearch bandSearch) {
        this.bandSearches.remove(Objects.requireNonNull(bandSearch));
    }

    public void leaveBand(BandId bandId) {
        this.bands.remove(Objects.requireNonNull(bandId));
    }

    public void joinBand(BandId bandId) {
        this.bands.add(Objects.requireNonNull(bandId));
    }
}


