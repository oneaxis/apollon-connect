package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.ddd.sharedkernel.AbstractEntityId;
import de.oneaxis.ddd.sharedkernel.ValueObject;
import org.bson.types.ObjectId;

@ValueObject
public class MusicianId extends AbstractEntityId {
    protected MusicianId(ObjectId value) {
        super(value);
    }
}
