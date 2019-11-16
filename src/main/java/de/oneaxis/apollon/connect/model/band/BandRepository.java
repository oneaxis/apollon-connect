package de.oneaxis.apollon.connect.model.band;

import de.oneaxis.ddd.conceptual.Repository;

@Repository
public interface BandRepository {
    Band findById(BandId bandId);

    Band save(Band band);

    Band update(Band band);
}
