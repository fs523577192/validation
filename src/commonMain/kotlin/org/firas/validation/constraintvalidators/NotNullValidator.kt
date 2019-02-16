/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraintvalidators

import org.firas.validation.ConstraintValidator
import org.firas.validation.constraints.NotNull
import org.firas.validation.ConstraintValidatorContext

/**
 * Validate that the object is not `null`.
 *
 * @author Emmanuel Bernard
 * @author Wu Yuping
 */
class NotNullValidator: ConstraintValidator<NotNull, Any?> {

    override fun initialize(constraintAnnotation: NotNull) {
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        return value != null
    }
}