/*
 * Migrated from javax.validation:validation-api:2.0.1.Final by Wu Yuping
 *
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraints

import org.firas.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Size(
    /**
     * the error message template
     */
    val message: String = "{org.firas.validation.constraints.Size.message}",

    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = arrayOf(),

    val payload: Array<KClass<out Payload>> = arrayOf(),

    /**
     * value the element must be greater or equal to
     */
    val min: Int,

    /**
     * value the element must be less or equal to
     */
    val max: Int
)