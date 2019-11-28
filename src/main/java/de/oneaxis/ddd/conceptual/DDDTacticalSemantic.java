package de.oneaxis.ddd.conceptual;

import java.lang.annotation.*;

/**
 * A convenience annotation to declare an element as a tactical semantic element for usage in
 * a domain driven design scope.
 */
@Documented
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.SOURCE)
@interface DDDTacticalSemantic {

}
