package de.oneaxis.ddd.sharedkernel;

/**
 * An aggregate defines a consistency boundary around one or more entities. Exactly one entity in an aggregate is the
 * root. Lookup is done using the root entity's identifier. Any other entities in the aggregate are children of the root,
 * and are referenced by following pointers from the root.
 */
@Entity
public @interface AggregateRoot {

}
