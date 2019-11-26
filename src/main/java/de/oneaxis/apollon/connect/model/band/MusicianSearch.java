package de.oneaxis.apollon.connect.model.band;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.model.Location;
import de.oneaxis.ddd.conceptual.ValueObject;
import lombok.Builder;
import lombok.Value;

@ValueObject
@Value
public class MusicianSearch {
    private final Location location;

    @Builder
    @JsonCreator
    public MusicianSearch(@JsonProperty("location") Location location) {
        this.location = location;
    }
}
