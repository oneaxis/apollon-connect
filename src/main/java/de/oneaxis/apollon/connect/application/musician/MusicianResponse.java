package de.oneaxis.apollon.connect.application.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.application.instrument.InstrumentResponse;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class MusicianResponse {
    @NotEmpty
    public final String id;
    @Size
    public final Set<InstrumentResponse> instruments;

    @JsonCreator
    public MusicianResponse(@JsonProperty("id") String id,
                            @JsonProperty("instruments") Set<InstrumentResponse> instruments) {
        this.id = id;
        this.instruments = Optional.of(instruments).orElse(new HashSet<>());
    }
}
