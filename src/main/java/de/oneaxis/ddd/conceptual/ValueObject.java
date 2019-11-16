package de.oneaxis.ddd.conceptual;


/**
 * A value object has no identity. It is defined only by the values of its attributes. Value objects are also immutable.
 * To update a value object, you always create a new instance to replace the old one. Value objects can have methods
 * that encapsulate model logic, but those methods should have no side-effects on the object's state. Typical examples
 * of value objects include colors, dates and times, and currency values.
 */
@DDDTacticalSemantic
public @interface ValueObject {
}
