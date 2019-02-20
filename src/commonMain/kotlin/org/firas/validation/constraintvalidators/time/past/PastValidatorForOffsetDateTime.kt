/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraintvalidators.time.past

import org.firas.datetime.OffsetDateTime

/**
 * Check that the `org.firas.datetime.OffsetDateTime` passed to be validated is in the
 * past.
 *
 * @author Wu Yuping
 */
class PastValidatorForOffsetDateTime: AbstractPastValidator<OffsetDateTime>() {

    override fun getNow(): OffsetDateTime {
        return OffsetDateTime.now()
    }
}