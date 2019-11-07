package de.oneaxis.apollon.connect.application.gear;

import de.oneaxis.apollon.connect.model.gear.Gear;
import de.oneaxis.apollon.connect.model.gear.GearId;
import de.oneaxis.apollon.connect.model.gear.GearRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GearRepositoryImpl extends GearRepository, MongoRepository<Gear, GearId> {
}
