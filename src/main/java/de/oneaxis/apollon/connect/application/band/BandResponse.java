package de.oneaxis.apollon.connect.application.band;

import de.oneaxis.apollon.connect.application.SearchLocationResponse;
import de.oneaxis.apollon.connect.application.musician.MusicianResponse;

import java.util.Set;

public class BandResponse {
    final String id;
    final String name;
    final Set<MusicianResponse> musicians;
    final Set<SearchLocationResponse> searchLocations;

    public BandResponse(String id, String name, Set<MusicianResponse> musicians,
                        Set<SearchLocationResponse> searchLocations) {
        this.id = id;
        this.name = name;
        this.musicians = musicians;
        this.searchLocations = searchLocations;
    }
}
