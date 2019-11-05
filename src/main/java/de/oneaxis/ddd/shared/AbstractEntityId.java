package de.oneaxis.ddd.shared;

import org.bson.types.ObjectId;

public abstract class AbstractEntityId {
    public final ObjectId value;

    protected AbstractEntityId(ObjectId value) {
        this.value = value;
    }
}
