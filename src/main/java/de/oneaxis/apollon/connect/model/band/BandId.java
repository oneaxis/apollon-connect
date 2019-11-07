package de.oneaxis.apollon.connect.model.band;

import de.oneaxis.ddd.sharedkernel.AbstractEntityId;
import de.oneaxis.ddd.sharedkernel.ValueObject;

@ValueObject
public class BandId extends AbstractEntityId {
    public BandId(String value) {
        super(value);
    }
}
