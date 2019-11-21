package de.oneaxis.apollon.connect.model.instrument;

import de.oneaxis.ddd.conceptual.Entity;
import org.bson.types.ObjectId;

import java.util.Objects;

@Entity
public class Instrument {
    public final InstrumentId id;
    public final String name;

    public Instrument(String name) throws InstrumentWithoutNameException {
        this.id = new InstrumentId(ObjectId.get().toString());
        this.name = name;
        validate();
    }

    public Instrument(InstrumentId id, String name) throws InstrumentWithoutNameException {
        this.id = id;
        this.name = name;
        validate();
    }

    void validate() throws InstrumentWithoutNameException {
        if (Objects.isNull(this.name) || this.name.isEmpty()) throw new InstrumentWithoutNameException();
    }
}
