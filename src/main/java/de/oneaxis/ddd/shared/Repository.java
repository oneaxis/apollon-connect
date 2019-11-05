package de.oneaxis.ddd.shared;

/**
 * Repositories are more coarse-grained than DAOs by providing control of an entire {@link AggregateRoot} often hiding a
 * lot of internal state from the client.
 */
@DDDTacticalSemantic
public @interface Repository {

}
