/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraintvalidators.notempty

import org.firas.validation.ConstraintValidator
import org.firas.validation.ConstraintValidatorContext
import org.firas.validation.constraints.NotEmpty

/**
 * Check that the array is not null and not empty.
 *
 * @author Guillaume Smet
 * @author Wu Yuping
 */
class NotEmptyValidatorForArray: ConstraintValidator<NotEmpty, Array<*>?> {

    override fun initialize(constraintAnnotation: NotEmpty) {
    }

    /**
     * Checks the array is not `null` and not empty.
     *
     * @param array the array to validate
     * @param constraintValidatorContext context in which the constraint is evaluated
     * @return returns `true` if the array is not `null` and the array is not empty
     */
    override fun isValid(value: Array<*>?, context: ConstraintValidatorContext): Boolean {
        return null != value && value.size > 0
    }
}