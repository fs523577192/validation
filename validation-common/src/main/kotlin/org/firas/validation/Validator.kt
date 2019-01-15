/*
 * Migrated from javax.validation:validation-api:2.0.1.Final by Wu Yuping
 *
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation

import kotlin.reflect.KClass

/**
 * Validates bean instances. Implementations of this interface must be thread-safe.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 * @author Gunnar Morling
 * @author Wu Yuping
 */
interface Validator<T: Any> {

    /**
     * Validates all constraints on `object`.
     *
     * @param object object to validate
     * @param groups the group or list of groups targeted for validation (defaults to
     *        [Default])
     * @param <T> the type of the object to validate
     * @return constraint violations or an empty set if none
     * @throws IllegalArgumentException if object is `null`
     *         or if `null` is passed to the varargs groups
     * @throws ValidationException if a non recoverable error happens
     *         during the validation process
     */
    fun validate(obj: T?, groups: KClass<T>): Set<ConstraintViolation<T>>

    /**
     * Validates all constraints placed on the property of `object`
     * named `propertyName`.
     *
     * @param object object to validate
     * @param propertyName property to validate (i.e. field and getter constraints)
     * @param groups the group or list of groups targeted for validation (defaults to
     *        [Default])
     * @param <T> the type of the object to validate
     * @return constraint violations or an empty set if none
     * @throws IllegalArgumentException if `object` is `null`,
     *         if `propertyName` is `null`, empty or not a valid object property
     *         or if `null` is passed to the varargs groups
     * @throws ValidationException if a non recoverable error happens
     *         during the validation process
     */
    fun validateProperty(obj: T?, propertyName: String, groups: KClass<T>): Set<ConstraintViolation<T>>

    /**
     * Validates all constraints placed on the property named `propertyName`
     * of the class `beanType` would the property value be `value`.
     *
     *
     * [ConstraintViolation] objects return `null` for
     * [ConstraintViolation#getRootBean()] and
     * [ConstraintViolation#getLeafBean()].
     *
     * @param beanType the bean type
     * @param propertyName property to validate
     * @param value property value to validate
     * @param groups the group or list of groups targeted for validation (defaults to
     *        [Default]).
     * @param <T> the type of the object to validate
     * @return constraint violations or an empty set if none
     * @throws IllegalArgumentException if `beanType` is `null`,
     *         if `propertyName` is `null`, empty or not a valid object property
     *         or if `null` is passed to the varargs groups
     * @throws ValidationException if a non recoverable error happens
     *         during the validation process
     */
    fun validateValue(beanType: KClass<T>, propertyName: String, value: Any?, groups: KClass<T>):
            Set<ConstraintViolation<T>>
}