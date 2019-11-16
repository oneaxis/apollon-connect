package de.oneaxis.apollon.connect.model.musician;

import de.oneaxis.apollon.connect.model.SearchLocation;
import de.oneaxis.ddd.conceptual.ValueObject;

@ValueObject
class BandSearch {
    final SearchLocation searchLocation;

    BandSearch(SearchLocation searchLocation) {
        this.searchLocation = searchLocation;
    }
}
