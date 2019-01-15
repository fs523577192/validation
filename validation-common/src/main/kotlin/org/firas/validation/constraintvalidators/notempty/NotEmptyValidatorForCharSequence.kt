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
 * Check that the character sequence is not null and its length is strictly superior to 0.
 *
 * @author Guillaume Smet
 * @author Wu Yuping
 */
class NotEmptyValidatorForCharSequence: ConstraintValidator<NotEmpty, CharSequence?> {

    override fun initialize(constraintAnnotation: NotEmpty) {
    }

    /**
     * Checks the character sequence is not `null` and not empty.
     *
     * @param charSequence the character sequence to validate
     * @param constraintValidatorContext context in which the constraint is evaluated
     * @return returns `true` if the character sequence is not `null` and not empty.
     */
    override fun isValid(value: CharSequence?, context: ConstraintValidatorContext): Boolean {
        return null != value && value.length > 0
    }
}
