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
 * @author Marko Bekhta
 * @author Wu Yuping
 */
class NumberComparatorHelper private constructor() {

    companion object {
        /*
        fun compare(number: BigDecimal, value: Long): Int {
            return number.compareTo(BigDecimal.valueOf(value))
        }

        fun compare(number: BigInteger, value: Long): Int {
            return number.compareTo(BigInteger.valueOf(value))
        }
        */

        fun compare(number: Long, value: Long): Int {
            return number.compareTo(value)
        }

        fun compare(number: Number, value: Long): Int {
            return number.toLong().compareTo(value)
        }
    }
}