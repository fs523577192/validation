package org.firas.validation.constraintvalidators.time.future

import org.firas.validation.ConstraintValidatorContext
import org.firas.validation.constraints.Future
import org.firas.validation.constraintvalidators.time.AbstractTimeValidator

/**
 *
 * @author Wu Yuping
 */
abstract class AbstractFutureValidator<T: Comparable<T>>: AbstractTimeValidator<Future, T>() {

    override fun initialize(constraintAnnotation: Future) {
        this.includePresent = constraintAnnotation.includePresent
    }

    override fun isValid(value: T, context: ConstraintValidatorContext): Boolean {
        val result = value.compareTo(getNow())
        return if (includePresent) result >= 0 else result > 0
    }
}