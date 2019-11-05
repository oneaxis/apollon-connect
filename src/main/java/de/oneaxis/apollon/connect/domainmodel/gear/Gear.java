package de.oneaxis.apollon.connect.domainmodel.gear;

import de.oneaxis.ddd.sharedkernel.Entity;

@Entity
public class Gear {

    public final GearType type;
    public final GearId id;

    public Gear(GearType type, GearId id) {
        this.type = type;
        this.id = id;
    }
}
