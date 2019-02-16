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
 * The annotated element must be an instant, date or time in the future.
 *
 * *Now* is defined by the {@link ClockProvider} attached to the {@link Validator} or
 * {@link ValidatorFactory}. The default `clockProvider` defines the current time
 * according to the virtual machine, applying the current default time zone if needed.
 *
 * Supported types are:
 *
 *  * `org.firas.time.Date`
 *  * `org.firas.time.Instant`
 *  * `org.firas.time.LocalDate`
 *  * `org.firas.time.LocalDateTime`
 *  * `org.firas.time.LocalTime`
 *  * `org.firas.time.MonthDay`
 *  * `org.firas.time.OffsetDateTime`
 *  * `org.firas.time.OffsetTime`
 *  * `org.firas.time.Year`
 *  * `org.firas.time.YearMonth`
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
annotation class Future(

    val includePresent: Boolean = false,

    val message: String = "{org.firas.validation.constraints.Future.message}",

    val groups: Array<KClass<*>> = arrayOf()
)
