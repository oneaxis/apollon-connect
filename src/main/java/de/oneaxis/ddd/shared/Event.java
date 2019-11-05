package de.oneaxis.ddd.shared;

/**
 * Domain events can be used to notify other parts of the system when something happens. As the type suggests,
 * domainmodel events should mean something within the domainmodel. For example, "a record was inserted into a table" is not a
 * domainmodel event. "A delivery was cancelled" is a domainmodel event. Domain events are especially relevant in a microservices
 * architecture. Because microservices are distributed and don't share data stores, domainmodel events provide a way for
 * microservices to coordinate with each other.
 */
@DDDTacticalSemantic
public @interface Event {

}
