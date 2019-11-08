package de.oneaxis.apollon.connect.application.musician;

import de.oneaxis.apollon.connect.application.band.BandResponse;
import de.oneaxis.apollon.connect.application.gear.GearResponse;
import de.oneaxis.apollon.connect.model.SearchLocation;

import java.util.Set;

public class MusicianResponse {
    public final String id;
    public final Set<GearResponse> gearList;
    public final Set<BandResponse> bands;
    public final Set<SearchLocation> searchLocations;

    public MusicianResponse(String id, Set<GearResponse> gearList,
                     Set<BandResponse> bands, Set<SearchLocation> searchLocations) {
        this.id = id;
        this.gearList = gearList;
        this.bands = bands;
        this.searchLocations = searchLocations;
    }
}
