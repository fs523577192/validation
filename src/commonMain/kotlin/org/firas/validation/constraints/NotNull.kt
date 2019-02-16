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
 * The annotated element must not be `null`.
 * Accepts any type.
 *
 * @author Emmanuel Bernard
 * @author Wu Yuping
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class NotNull(

    val message: String = "{org.firas.validation.constraints.NotNull.message}",

    val groups: Array<KClass<*>> = arrayOf()
)
