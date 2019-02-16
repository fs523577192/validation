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
 * Check that the length of a character sequence is between min and max.
 *
 * @author Emmanuel Bernard
 * @author Gavin King
 * @author Hardy Ferentschik
 * @author Wu Yuping
 */
class SizeValidatorForCharSequence: ConstraintValidator<Size, CharSequence?> {

    private var min = 0
    private var max = 0

    override fun initialize(constraintAnnotation: Size) {
        this.min = constraintAnnotation.min
        this.max = constraintAnnotation.max
        validateParameters()
    }

    /**
     * Checks the length of the specified character sequence (e.g. string).
     *
     * @param charSequence The character sequence to validate.
     * @param constraintValidatorContext context in which the constraint is evaluated.
     *
     * @return Returns `true` if the string is `null` or the length of `charSequence` between the specified
     *         `min` and `max` values (inclusive), `false` otherwise.
     */
    override fun isValid(value: CharSequence?, context: ConstraintValidatorContext): Boolean {
        return null == value || value.length in min .. max
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