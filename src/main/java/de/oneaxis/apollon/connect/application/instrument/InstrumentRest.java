package de.oneaxis.apollon.connect.application.instrument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class InstrumentRest {

    @NotEmpty
    public final String id;
    @NotEmpty
    public final String name;

    @JsonCreator
    public InstrumentRest(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public static InstrumentRest fromInstrument(de.oneaxis.apollon.connect.model.instrument.Instrument instrument) {
        return new InstrumentRest(instrument.id.value, instrument.name);
    }
}
