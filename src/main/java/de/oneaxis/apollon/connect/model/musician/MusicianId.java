package de.oneaxis.apollon.connect.model.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.ddd.conceptual.ValueObject;
import lombok.*;

@ValueObject
@Value
public class MusicianId {
    private String value;

    @JsonCreator
    public MusicianId(@JsonProperty("value") String value) {
        this.value = value;
    }
}
