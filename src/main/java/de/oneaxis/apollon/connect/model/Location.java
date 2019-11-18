package de.oneaxis.apollon.connect.model;

import de.oneaxis.ddd.conceptual.ValueObject;

import java.util.Objects;

@ValueObject
public class Location {
    public final String postalCode;

    public Location(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return postalCode.equals(that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode);
    }
}
