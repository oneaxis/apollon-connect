package de.oneaxis.apollon.connect.model.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.ddd.conceptual.ValueObject;
import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@ValueObject
@Value
public class BandSearch {
    private String postalCode;
    private InstrumentId instrumentId;

    @Builder
    @JsonCreator
    public BandSearch(@JsonProperty("postalCode") String postalCode,
                      @JsonProperty("instrumentId") InstrumentId instrumentId) {
        this.postalCode = Objects.requireNonNull(postalCode);
        this.instrumentId = instrumentId;
    }
}
