package de.oneaxis.apollon.connect.application.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MusicianRequest {
    public final String playedInstrument;

    @JsonCreator
    public MusicianRequest(@JsonProperty String playedInstrument) {
        this.playedInstrument = playedInstrument;
    }
}
