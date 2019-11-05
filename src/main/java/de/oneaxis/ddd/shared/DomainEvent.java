package de.oneaxis.ddd.shared;

/**
 * Domain events can be used to notify other parts of the system when something happens. As the name suggests,
 * domain events should mean something within the domain. For example, "a record was inserted into a table" is not a
 * domain event. "A delivery was cancelled" is a domain event. Domain events are especially relevant in a microservices
 * architecture. Because microservices are distributed and don't share data stores, domain events provide a way for
 * microservices to coordinate with each other.
 */
@DDDTacticalElement
public @interface DomainEvent {

}
