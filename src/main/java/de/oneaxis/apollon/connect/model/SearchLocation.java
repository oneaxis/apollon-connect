package de.oneaxis.apollon.connect.model;

import de.oneaxis.ddd.sharedkernel.ValueObject;

import java.util.Objects;

@ValueObject
public class SearchLocation {
    public final String postalCode;

    public SearchLocation(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchLocation that = (SearchLocation) o;
        return postalCode.equals(that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode);
    }
}
