package de.oneaxis.apollon.connect.domainmodel.gear;

import de.oneaxis.ddd.shared.ValueObject;

@ValueObject
public class GearType {
    public final String value;

    public GearType(String value) {
        this.value = value;
    }
}
