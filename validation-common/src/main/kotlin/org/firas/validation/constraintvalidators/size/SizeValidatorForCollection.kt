package org.firas.validation.constraintvalidators.size

import org.firas.validation.ConstraintValidator
import org.firas.validation.ConstraintValidatorContext
import org.firas.validation.constraints.Size

/**
 *
 * @author Wu Yuping
 * @version 1.0.0
 * @since 1.0.0
 */
class SizeValidatorForCollection: ConstraintValidator<Size, Collection<*>?> {

    private var min = 0
    private var max = 0

    override fun initialize(constraintAnnotation: Size) {
        this.min = constraintAnnotation.min
        this.max = constraintAnnotation.max
        validateParameters()
    }

    /**
     * Checks the number of entries in a collection.
     *
     * @param collection the collection to validate
     * @param constraintValidatorContext context in which the constraint is evaluated
     *
     * @return `true` if the collection is `null` or the number of entries in
     *         `collection` is between the specified `min` and `max` values (inclusive),
     *         `false` otherwise.
     */
    override fun isValid(value: Collection<*>?, context: ConstraintValidatorContext): Boolean {
        return null == value || value.size in min .. max
    }

    private fun validateParameters() {
        if (min < 0) {
            throw IllegalArgumentException("\"min\" cannot be negative")
        }
        if (max < 0) {
            throw IllegalArgumentException("\"max\" cannot be negative")
        }
        if (max < min) {
            throw IllegalArgumentException("\"max\" cannot be less than \"min\"")
        }
    }
}