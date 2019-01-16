/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraintvalidators.size

import org.firas.validation.ConstraintValidator
import org.firas.validation.ConstraintValidatorContext
import org.firas.validation.constraints.Size

/**
 * Check that a map's size is between min and max.
 *
 * @author Hardy Ferentschik
 * @author Wu Yuping
 */
class SizeValidatorForMap: ConstraintValidator<Size, Map<*, *>?> {

    private var min = 0
    private var max = 0

    override fun initialize(constraintAnnotation: Size) {
        this.min = constraintAnnotation.min
        this.max = constraintAnnotation.max
        validateParameters()
    }

    /**
     * Checks the number of entries in a map.
     *
     * @param map The map to validate.
     * @param constraintValidatorContext context in which the constraint is evaluated.
     *
     * @return Returns `true` if the map is `null` or the number of entries in `map`
     *         is between the specified `min` and `max` values (inclusive),
     *         `false` otherwise.
     */
    override fun isValid(value: Map<*, *>?, context: ConstraintValidatorContext): Boolean {
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