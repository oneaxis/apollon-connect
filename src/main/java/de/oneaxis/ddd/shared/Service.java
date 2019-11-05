package de.oneaxis.ddd.shared;

/**
 * In DDD terminology, a service is an object that implements some logic without holding any state. Evans distinguishes
 * between domainmodel services, which encapsulate domainmodel logic, and application services, which provide technical functionality,
 * such as user authentication or sending an SMS message. Domain services are often used to domainmodel behavior that spans
 * multiple entities.
 */
@DDDTacticalSemantic
public @interface Service {

}
