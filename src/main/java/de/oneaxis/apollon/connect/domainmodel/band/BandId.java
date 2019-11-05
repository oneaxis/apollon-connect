package de.oneaxis.apollon.connect.domainmodel.band;

import de.oneaxis.ddd.shared.AbstractEntityId;
import de.oneaxis.ddd.shared.ValueObject;
import org.bson.types.ObjectId;

@ValueObject
public class BandId extends AbstractEntityId {
    protected BandId(ObjectId value) {
        super(value);
    }
}
