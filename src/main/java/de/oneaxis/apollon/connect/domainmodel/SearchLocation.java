package de.oneaxis.apollon.connect.domainmodel;

import de.oneaxis.ddd.shared.ValueObject;

@ValueObject
public class SearchLocation {
    public final String postalCode;
    public final String country;

    public SearchLocation(String postalCode, String country) {
        this.postalCode = postalCode;
        this.country = country;
    }
}
