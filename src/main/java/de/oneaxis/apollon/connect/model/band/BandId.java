package de.oneaxis.apollon.connect.model.band;

import de.oneaxis.ddd.conceptual.AbstractEntityId;
import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
public class BandId extends AbstractEntityId {
    public BandId(String value) {
        super(value);
    }
}
