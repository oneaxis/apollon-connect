package de.oneaxis.apollon.connect.domainmodel.band;

import de.oneaxis.ddd.sharedkernel.ValueObject;

@ValueObject
public class BandName {
    public final String value;

    public BandName(String value) {
        this.value = value;
    }
}
