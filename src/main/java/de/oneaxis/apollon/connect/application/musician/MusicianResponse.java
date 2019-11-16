package de.oneaxis.apollon.connect.application.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.model.musician.Musician;


public class MusicianResponse {
    public final String id;

    @JsonCreator
    public MusicianResponse(@JsonProperty String id) {
        this.id = id;
    }

    static MusicianResponse fromMusician(Musician musician) {
        return new MusicianResponse(musician.getId().value);
    }
}
