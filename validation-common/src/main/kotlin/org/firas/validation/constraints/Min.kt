/*
 * Migrated from javax.validation:validation-api:2.0.1.Final by Wu Yuping
 *
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraints

import kotlin.reflect.KClass

/**
 * The annotated element must be a number whose value must be higher or
 * equal to the specified minimum.
 *
 *
 * Supported types are:
 * <ul>
 *     <li>`BigDecimal`</li>
 *     <li>`BigInteger`</li>
 *     <li>`byte`, `short`, `int`, `long`, and their respective
 *     wrappers</li>
 * </ul>
 * Note that `double` and `float` are not supported due to rounding errors
 * (some providers might provide some approximative support).
 *
 *
 * `null` elements are considered valid.
 *
 * @author Emmanuel Bernard
 * @author Wu Yuping
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Min(
        /**
         * the error message template
         */
        val message: String = "{org.firas.validation.constraints.Min.message}",

        /**
         * the groups the constraint belongs to
         */
        val groups: Array<KClass<*>> = [],

        // TODO: val payload: Array<KClass<*: Payload>> = [],

        /**
         * @return value the element must be higher or equal to
         */
        val value: Long
)