package de.oneaxis.apollon.connect.domainmodel.musician;

import de.oneaxis.apollon.connect.domainmodel.band.BandId;
import de.oneaxis.apollon.connect.domainmodel.gear.GearId;
import de.oneaxis.apollon.connect.domainmodel.SearchLocation;
import de.oneaxis.ddd.sharedkernel.AggregateRoot;

import java.util.List;

@AggregateRoot
public class Musician {
    public final MusicianId id;
    public final List<BandId> bands;
    public final List<GearId> gearList;
    private SearchLocation searchLocation;

    Musician(MusicianId id, SearchLocation searchLocation, List<BandId> bands, List<GearId> gearList) {
        this.id = id;
        this.searchLocation = searchLocation;
        this.bands = bands;
        this.gearList = gearList;
    }

    public SearchLocation searchLocation() {
        return searchLocation;
    }

    public void stopSearching() {
        searchLocation = null;
    }

    public void newSearchLocation(String postalCode, String country) {
        //TODO: dispose search location change event to event handler
        searchLocation = new SearchLocation(postalCode, country);
    }
}
