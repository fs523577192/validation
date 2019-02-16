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
 * Base exception of all Bean Validation "unexpected" problems.
 *
 * @author Emmanuel Bernard
 * @author Wu Yuping
 */
open class ValidationException: RuntimeException {

    constructor(): super()

    constructor(message: String): super(message)

    constructor(cause: Throwable): super(cause)

    constructor(message: String, cause: Throwable): super(message, cause)
}