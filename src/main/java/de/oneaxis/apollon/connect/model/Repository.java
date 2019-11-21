package de.oneaxis.apollon.connect.model;

import java.util.Optional;

@de.oneaxis.ddd.conceptual.Repository
public interface Repository<Model, Id> {
    Optional<Model> findById(Id id);

    Model save(Model model);

    void deleteById(Id id);
}
