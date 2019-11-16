package de.oneaxis.ddd.conceptual;

/**
 * In DDD terminology, a service is an object that implements some logic without holding any state. Evans distinguishes
 * between model services, which encapsulate model logic, and application services, which provide technical functionality,
 * such as user authentication or sending an SMS message. Domain services are often used to model behavior that spans
 * multiple entities.
 */
@DDDTacticalSemantic
public @interface Service {

}
