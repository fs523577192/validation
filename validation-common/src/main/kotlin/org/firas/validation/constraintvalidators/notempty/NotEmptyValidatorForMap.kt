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
 * Check that the map is not null and not empty.
 *
 * @author Guillaume Smet
 * @author Wu Yuping
 */
class NotEmptyValidatorForMap<K, V>: ConstraintValidator<NotEmpty, Map<K, V>?> {

    override fun initialize(constraintAnnotation: NotEmpty) {
    }

    /**
     * Checks the map is not `null` and not empty.
     *
     * @param map the map to validate
     * @param constraintValidatorContext context in which the constraint is evaluated
     * @return returns `true` if the map is not `null` and the map is not empty
     */
    override fun isValid(value: Map<K, V>?, context: ConstraintValidatorContext): Boolean {
        return null != value && !value.isEmpty()
    }
}