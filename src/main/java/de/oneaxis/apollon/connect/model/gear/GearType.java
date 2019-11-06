package de.oneaxis.apollon.connect.model.gear;

import de.oneaxis.ddd.sharedkernel.ValueObject;

@ValueObject
public class GearType {
    public final String value;

    public GearType(String value) {
        this.value = value;
    }
}
