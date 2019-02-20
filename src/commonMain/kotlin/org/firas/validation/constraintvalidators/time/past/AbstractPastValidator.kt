package org.firas.validation.constraintvalidators.time.past

import org.firas.validation.ConstraintValidatorContext
import org.firas.validation.constraints.Past
import org.firas.validation.constraintvalidators.time.AbstractTimeValidator

/**
 *
 * @author Wu Yuping
 */
abstract class AbstractPastValidator<T: Comparable<T>>: AbstractTimeValidator<Past, T>() {

    override fun initialize(constraintAnnotation: Past) {
        this.includePresent = constraintAnnotation.includePresent
    }

    override fun isValid(value: T, context: ConstraintValidatorContext): Boolean {
        val result = value.compareTo(getNow())
        return if (includePresent) result <= 0 else result < 0
    }
}