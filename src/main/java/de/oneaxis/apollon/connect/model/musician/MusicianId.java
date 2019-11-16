package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.ddd.conceptual.AbstractEntityId;
import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
public class MusicianId extends AbstractEntityId {
    public MusicianId(String value) {
        super(value);
    }
}
