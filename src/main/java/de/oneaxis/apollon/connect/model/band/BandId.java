package de.oneaxis.apollon.connect.model.band;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.ddd.conceptual.ValueObject;
import lombok.Builder;
import lombok.Value;

@ValueObject
@Value
public class BandId {
    private final String value;

    @Builder
    @JsonCreator
    public BandId(@JsonProperty("value") String value) {
        this.value = value;
    }
}
