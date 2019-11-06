package de.oneaxis.ddd.sharedkernel;

/**
 * Domain events can be used to notify other parts of the system when something happens. As the type suggests,
 * model events should mean something within the model. For example, "a record was inserted into a table" is not a
 * model event. "A delivery was cancelled" is a model event. Domain events are especially relevant in a microservices
 * architecture. Because microservices are distributed and don't share data stores, model events provide a way for
 * microservices to coordinate with each other.
 */
@DDDTacticalSemantic
public @interface Event {

}
