/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraintvalidators.time.future

import org.firas.datetime.Instant

/**
 * Check that the `org.firas.datetime.Instant` passed to be validated is in the
 * future.
 *
 * @author Wu Yuping
 */
class FutureValidatorForInstant: AbstractFutureValidator<Instant>() {

    override fun getNow(): Instant {
        return Instant.now()
    }
}