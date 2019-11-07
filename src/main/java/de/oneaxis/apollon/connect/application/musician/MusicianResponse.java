package de.oneaxis.apollon.connect.application.musician;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.apollon.connect.model.band.Band;
import de.oneaxis.apollon.connect.model.gear.Gear;

import java.util.Set;

public class MusicianResponse {
    final String id;
    final Set<Gear> gearList;
    final Set<Band> bands;
    final Set<SearchLocation> searchLocations;

    @JsonCreator
    MusicianResponse(@JsonProperty String id, @JsonProperty Set<Gear> gearList,
                     @JsonProperty Set<Band> bands, @JsonProperty Set<SearchLocation> searchLocations) {
        this.id = id;
        this.gearList = gearList;
        this.bands = bands;
        this.searchLocations = searchLocations;
    }
}
