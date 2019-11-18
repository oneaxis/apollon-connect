package de.oneaxis.apollon.connect.application.instrument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.model.instrument.Instrument;

import javax.validation.constraints.NotEmpty;

public class InstrumentResponse {

    @NotEmpty
    public final String id;
    @NotEmpty
    public final String name;

    @JsonCreator
    public InstrumentResponse(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public static InstrumentResponse fromInstrument(Instrument instrument) {
        return new InstrumentResponse(instrument.id.value, instrument.name);
    }
}
