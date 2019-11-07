package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.ddd.sharedkernel.AbstractEntityId;
import de.oneaxis.ddd.sharedkernel.ValueObject;

@ValueObject
public class MusicianId extends AbstractEntityId {
    public MusicianId(String value) {
        super(value);
    }
}
