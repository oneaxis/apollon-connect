package de.oneaxis.apollon.connect.model.instrument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.ddd.conceptual.ValueObject;
import lombok.Value;

@ValueObject
@Value
public class InstrumentId {
    private final String value;

    @JsonCreator
    public InstrumentId(@JsonProperty("value") String value) {
        this.value = value;
    }
}
