package de.oneaxis.ddd.conceptual;

/**
 * Repositories are more coarse-grained than DAOs by providing control of an entire {@link Aggregate} often hiding a
 * lot of internal state from the client.
 */
@DDDTacticalSemantic
public @interface Repository {

}
