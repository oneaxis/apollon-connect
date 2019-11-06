package de.oneaxis.apollon.connect.model.band;

import de.oneaxis.ddd.sharedkernel.AbstractEntityId;
import de.oneaxis.ddd.sharedkernel.ValueObject;
import org.bson.types.ObjectId;

@ValueObject
public class BandId extends AbstractEntityId {
    protected BandId(ObjectId value) {
        super(value);
    }
}
