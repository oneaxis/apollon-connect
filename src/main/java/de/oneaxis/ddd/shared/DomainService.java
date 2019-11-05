package de.oneaxis.ddd.shared;

/**
 * In DDD terminology, a service is an object that implements some logic without holding any state. Evans distinguishes
 * between domain services, which encapsulate domain logic, and application services, which provide technical functionality,
 * such as user authentication or sending an SMS message. Domain services are often used to model behavior that spans
 * multiple entities.
 */
@DDDTacticalElement
public @interface DomainService {

}
