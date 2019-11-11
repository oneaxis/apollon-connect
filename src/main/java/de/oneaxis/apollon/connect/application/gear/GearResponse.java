package de.oneaxis.apollon.connect.application.gear;

import de.oneaxis.apollon.connect.model.gear.Gear;

public class GearResponse extends Gear {

    public final String id;

    public GearResponse(Gear gear) {
        super(gear.type, gear.id);
        this.id = gear.id.value;
    }
}
