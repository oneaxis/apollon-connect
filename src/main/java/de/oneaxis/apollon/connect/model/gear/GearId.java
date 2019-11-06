package de.oneaxis.apollon.connect.model.gear;

import de.oneaxis.ddd.sharedkernel.AbstractEntityId;
import de.oneaxis.ddd.sharedkernel.ValueObject;
import org.bson.types.ObjectId;

@ValueObject
public class GearId extends AbstractEntityId {
    protected GearId(ObjectId value) {
        super(value);
    }
}
