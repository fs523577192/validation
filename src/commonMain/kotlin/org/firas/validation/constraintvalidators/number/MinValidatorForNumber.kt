/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraintvalidators.number

/**
 * Check that the number being validated is greater than or equal to the minimum
 * value specified.
 *
 * @author Marko Bekhta
 * @author Wu Yuping
 */
class MinValidatorForNumber: AbstractMinValidator<Number?>() {

    override fun compare(number: Number?): Int {
        return NumberComparatorHelper.compare(number!!, this.minValue)
    }
}