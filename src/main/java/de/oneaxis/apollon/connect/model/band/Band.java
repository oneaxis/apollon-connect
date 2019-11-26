package de.oneaxis.apollon.connect.model.band;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.ddd.conceptual.Aggregate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Aggregate
@Getter
@Setter
public class Band {
    private final BandId id;
    private BandName name;
    @Singular
    private Set<MusicianSearch> musicianSearches = new HashSet<>();

    @Builder
    @JsonCreator
    public Band(@JsonProperty("id") BandId id) {
        this.id = Objects.requireNonNull(id);
    }
}
