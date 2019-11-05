package de.oneaxis.ddd.shared;


/**
 * A value object has no identity. It is defined only by the values of its attributes. Value objects are also immutable.
 * To update a value object, you always create a new instance to replace the old one. Value objects can have methods
 * that encapsulate domain logic, but those methods should have no side-effects on the object's state. Typical examples
 * of value objects include colors, dates and times, and currency values.
 */
@DDDTacticalElement
public @interface ValueObject {
}
