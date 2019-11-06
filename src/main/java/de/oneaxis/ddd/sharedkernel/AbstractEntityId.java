package de.oneaxis.ddd.sharedkernel;

import org.bson.types.ObjectId;

import java.util.Objects;

public abstract class AbstractEntityId {
    public final ObjectId value;

    protected AbstractEntityId(ObjectId value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntityId that = (AbstractEntityId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
