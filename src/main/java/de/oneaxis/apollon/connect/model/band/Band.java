package de.oneaxis.apollon.connect.model.band;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.ddd.conceptual.Aggregate;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Aggregate
@Getter
@Setter
public class Band {
    private final BandId id;
    private String name;
    private Set<MusicianSearch> musicianSearches = new HashSet<>();

    @JsonCreator
    @Builder
    public Band(@JsonProperty("id") BandId id) {
        this.id = Objects.requireNonNull(id);
    }
}
