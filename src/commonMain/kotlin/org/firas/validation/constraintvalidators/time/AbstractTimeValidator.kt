package org.firas.validation.constraintvalidators.time

import org.firas.validation.ConstraintValidator

/**
 *
 * @author Wu Yuping
 */
abstract class AbstractTimeValidator<A: Annotation, T: Comparable<T>>: ConstraintValidator<A, T> {

    protected var includePresent: Boolean = false

    abstract fun getNow(): T
}