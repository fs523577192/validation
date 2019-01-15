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
 * The annotated element must not be `null` nor empty. Supported types are:
 * <ul>
 * <li>`CharSequence` (length of character sequence is evaluated)</li>
 * <li>`Collection` (collection size is evaluated)</li>
 * <li>`Map` (map size is evaluated)</li>
 * <li>Array (array length is evaluated)</li>
 * </ul>
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 * @author Wu Yuping
 *
 * @since 2.0
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class NotEmpty(
        /**
         * the error message template
         */
        val message: String = "{org.firas.constraints.NotEmpty.message}",

        /**
         * the groups the constraint belongs to
         */
        val groups: Array<KClass<*>> = []

        // TODO: val payload: Array<KClass<*: Payload>> = [],
)
