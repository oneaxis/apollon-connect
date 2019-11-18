package de.oneaxis.apollon.connect.application.instrument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class InstrumentRequest {

    public final String id;
    @NotEmpty
    public final String name;

    @JsonCreator
    public InstrumentRequest(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
