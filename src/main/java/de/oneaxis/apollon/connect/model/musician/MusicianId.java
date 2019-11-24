package de.oneaxis.apollon.connect.model.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.ddd.conceptual.AbstractEntityId;
import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
public class MusicianId extends AbstractEntityId {

    @JsonCreator
    public MusicianId(@JsonProperty("value") String value) {
        super(value);
    }
}
