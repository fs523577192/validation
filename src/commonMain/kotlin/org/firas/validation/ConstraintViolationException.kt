/*
 * Migrated from javax.validation:validation-api:2.0.1.Final by Wu Yuping
 *
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation

/**
 * Reports the result of constraint violations.
 *
 * @author Emmanuel Bernard
 * @author Gunnar Morling
 * @author Guillaume Smet
 * @author Wu Yuping
 */
class ConstraintViolationException: ValidationException {

    private val constraintViolations: Set<ConstraintViolation<*>>?

    constructor(message: String,
                constraintViolations: Set<ConstraintViolation<*>>?): super(message) {
        this.constraintViolations =
                if (null == constraintViolations) null else HashSet(constraintViolations)
    }

    constructor(constraintViolations: Set<ConstraintViolation<*>>?) {
        this.constraintViolations =
                if (null == constraintViolations) null else HashSet(constraintViolations)
    }
}