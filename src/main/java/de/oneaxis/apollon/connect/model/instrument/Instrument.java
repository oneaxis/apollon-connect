package de.oneaxis.apollon.connect.model.instrument;

import de.oneaxis.ddd.conceptual.Entity;

@Entity
public class Instrument {
    public final InstrumentId id;
    public final String name;

    public Instrument(InstrumentId id, String name) {
        this.id = id;
        this.name = name;
    }
}
