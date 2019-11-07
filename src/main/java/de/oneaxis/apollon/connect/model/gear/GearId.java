package de.oneaxis.apollon.connect.model.gear;

import de.oneaxis.ddd.sharedkernel.AbstractEntityId;
import de.oneaxis.ddd.sharedkernel.ValueObject;

@ValueObject
public class GearId extends AbstractEntityId {
    public GearId(String value) {
        super(value);
    }
}
