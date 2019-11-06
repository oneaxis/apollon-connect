package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.apollon.connect.model.SearchLocationAlreadyAssigned;
import de.oneaxis.apollon.connect.model.band.BandId;
import de.oneaxis.apollon.connect.model.gear.GearId;
import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.ddd.sharedkernel.AggregateRoot;

import java.util.Set;

@AggregateRoot
public class Musician {
    public final MusicianId id;
    public final Set<BandId> bands;
    public final Set<GearId> gearSet;
    public final Set<SearchLocation> searchLocations;

    Musician(MusicianId id, Set<SearchLocation> searchLocations, Set<BandId> bands, Set<GearId> gearSet) {
        this.id = id;
        this.searchLocations = searchLocations;
        this.bands = bands;
        this.gearSet = gearSet;
    }

    public void stopAllSearches() {
        searchLocations.clear();
    }

    public void stopSearchOn(SearchLocation searchLocation) {
        searchLocations.remove(searchLocation);
    }

    public void addSearchLocation(String postalCode) throws SearchLocationAlreadyAssigned {
        if (!searchLocations.add(new SearchLocation(postalCode))) throw new SearchLocationAlreadyAssigned();
    }
}
