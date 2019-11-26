package de.oneaxis.apollon.connect.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.ddd.conceptual.ValueObject;
import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@ValueObject
@Value
public class Location {
    private final String postalCode;

    @Builder
    @JsonCreator
    public Location(@JsonProperty("postalCode") String postalCode) {
        this.postalCode = postalCode;
    }
}
