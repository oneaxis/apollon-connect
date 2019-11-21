package de.oneaxis.apollon.connect.application.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.application.band.BandRest;
import de.oneaxis.apollon.connect.application.instrument.InstrumentRest;
import de.oneaxis.apollon.connect.model.musician.BandSearch;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class MusicianRest {
    @NotEmpty
    public final String id;
    public final Set<InstrumentRest> instruments = new HashSet<>();
    public final Set<BandSearch> bandSearches = new HashSet<>();
    public final Set<BandRest> bands = new HashSet<>();

    @JsonCreator
    public MusicianRest(@JsonProperty("id") String id,
                        @JsonProperty("instruments") Set<InstrumentRest> instruments,
                        @JsonProperty("bands") Set<BandRest> bands,
                        @JsonProperty("bandSearches") Set<BandSearch> bandSearches) {
        this.id = id;
        if (Objects.nonNull(instruments) && !instruments.isEmpty())
            this.instruments.addAll(instruments);
        if (Objects.nonNull(bands) && !bands.isEmpty())
            this.bands.addAll(bands);
        if (Objects.nonNull(bandSearches) && !bandSearches.isEmpty())
            this.bandSearches.addAll(bandSearches);
    }
}
