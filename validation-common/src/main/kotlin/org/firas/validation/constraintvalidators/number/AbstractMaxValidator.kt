/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraintvalidators.number

import org.firas.validation.ConstraintValidator
import org.firas.validation.ConstraintValidatorContext
import org.firas.validation.constraints.Max

/**
 * Check that the number being validated is less than or equal to the maximum
 * value specified.
 *
 * @author Alaa Nassef
 * @author Hardy Ferentschik
 * @author Xavier Sosnovsky
 * @author Marko Bekhta
 * @author Wu Yuping
 */
abstract class AbstractMaxValidator<T>: ConstraintValidator<Max, T> {

    protected var maxValue: Long = 0

    override fun initialize(constraintAnnotation: Max) {
        this.maxValue = constraintAnnotation.value
    }

    override fun isValid(value: T, context: ConstraintValidatorContext): Boolean {
        // null values are valid
        if (null == value) {
            return true
        }
        return compare(value) <= 0
    }

    abstract fun compare(number: T): Int
}