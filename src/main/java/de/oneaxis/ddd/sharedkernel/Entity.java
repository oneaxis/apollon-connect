package de.oneaxis.ddd.sharedkernel;

/**
 * An entity is an object with a unique identity that persists over time. For example, in a banking application,
 * customers and accounts would be entities.
 *
 * An entity has a unique identifier in the system, which can be used to look up or retrieve the entity.
 * That doesn't mean the identifier is always exposed directly to users. It could be a GUID or a primary key in a database.
 * An identity may span multiple bounded contexts, and may endure beyond the lifetime of the application.
 * For example, bank account numbers or government-issued IDs are not tied to the lifetime of a particular application.
 * The attributes of an entity may change over time. For example, a person's type or address might change, but they are
 * still the same person.
 * An entity can hold references to other entities.
 */
@DDDTacticalSemantic
public @interface Entity {

}
