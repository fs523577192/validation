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
 * The annotated {@code CharSequence} must match the specified regular expression.
 * The regular expression follows the Java regular expression conventions
 * see {@link java.util.regex.Pattern}.
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 *
 * @author Emmanuel Bernard
 * @author Wu Yuping
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Pattern(
        /**
         * the error message template
         */
        val message: String = "{org.firas.validation.constraints.Pattern.message}",

        /**
         * the groups the constraint belongs to
         */
        val groups: Array<KClass<*>> = arrayOf(),

        // TODO: val payload: Array<KClass<*: Payload>> = [],

        /**
         * the regular expression to match
         */
        val regexp: String,

        /**
         * array of `Flag`s considered when resolving the regular expression
         */
        val flags: Array<kotlin.text.RegexOption> = arrayOf()
)