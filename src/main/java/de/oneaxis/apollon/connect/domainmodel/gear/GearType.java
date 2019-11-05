package de.oneaxis.apollon.connect.domainmodel.gear;

import de.oneaxis.ddd.sharedkernel.ValueObject;

@ValueObject
public class GearType {
    public final String value;

    public GearType(String value) {
        this.value = value;
    }
}
