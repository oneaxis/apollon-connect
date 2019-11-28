package de.oneaxis.apollon.connect.model.band;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.model.instrument.InstrumentId;
import de.oneaxis.ddd.conceptual.ValueObject;
import lombok.Builder;
import lombok.Value;

@ValueObject
@Value
public class MusicianSearch {
    private String postalCode;
    private InstrumentId instrumentId;

    @Builder
    @JsonCreator
    public MusicianSearch(@JsonProperty("postalCode") String postalCode,
                          @JsonProperty("instrumentId") InstrumentId instrumentId) {
        this.postalCode = postalCode;
        this.instrumentId = instrumentId;
    }
}
