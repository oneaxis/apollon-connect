package de.oneaxis.apollon.connect.application.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.application.instrument.InstrumentRequest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MusicianRequest {
    public final Set<InstrumentRequest> instruments;

    @JsonCreator
    public MusicianRequest(@JsonProperty("instruments") Set<InstrumentRequest> instruments) {
        this.instruments = Optional.of(instruments).orElse(new HashSet<>());
    }
}
