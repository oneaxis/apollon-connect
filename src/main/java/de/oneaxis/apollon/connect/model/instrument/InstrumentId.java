package de.oneaxis.apollon.connect.model.instrument;

import de.oneaxis.ddd.conceptual.AbstractEntityId;
import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
public class InstrumentId extends AbstractEntityId {

    public InstrumentId(String value) {
        super(value);
    }
}
