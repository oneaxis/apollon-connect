package de.oneaxis.apollon.connect.model.instrument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.ddd.conceptual.Entity;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Instrument {
    private final InstrumentId id;
    private String name;

    @JsonCreator
    @Builder
    public Instrument(@JsonProperty("id") InstrumentId id) {
        this.id = Objects.requireNonNull(id);
    }
}
