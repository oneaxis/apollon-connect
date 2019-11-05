package de.oneaxis.apollon.connect.domainmodel.musician;

import de.oneaxis.apollon.connect.domainmodel.band.BandId;
import de.oneaxis.apollon.connect.domainmodel.gear.GearId;
import de.oneaxis.apollon.connect.domainmodel.SearchLocation;
import de.oneaxis.ddd.sharedkernel.AggregateRoot;

import java.util.List;

@AggregateRoot
public class Musician {
    public final MusicianId id;
    private SearchLocation searchLocation;
    public final List<BandId> bands;
    public final List<GearId> gearList;

    public Musician(MusicianId id, SearchLocation searchLocation, List<BandId> bands, List<GearId> gearList) {
        this.id = id;
        this.searchLocation = searchLocation;
        this.bands = bands;
        this.gearList = gearList;
    }

    public void changeSearchLocation(SearchLocation searchLocation) {
        //TODO: dispose search location change event to event handler
        searchLocation = new SearchLocation(searchLocation.postalCode, searchLocation.country);
    }
}
