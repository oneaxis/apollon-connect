package de.oneaxis.apollon.connect.domainmodel.musician;

import de.oneaxis.ddd.shared.AbstractEntityId;
import de.oneaxis.ddd.shared.ValueObject;
import org.bson.types.ObjectId;

@ValueObject
public class MusicianId extends AbstractEntityId {
    protected MusicianId(ObjectId value) {
        super(value);
    }
}
