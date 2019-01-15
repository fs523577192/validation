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
 * Describes a constraint violation. This object exposes the constraint
 * violation context as well as the message describing the violation.
 *
 * @param <T> the type of the root bean
 *
 * @author Emmanuel Bernard
 * @author Wu Yuping
 */
interface ConstraintViolation<T: Any> {

    fun getInvalidValue(): Any?

    /**
     * @return the interpolated error message for this constraint violation
     */
    fun getMessage(): String

    /**
     * @return the non-interpolated error message for this constraint violation
     */
    fun getMessageTemplate(): String

    /**
     * Returns the root bean being validated. For method validation, returns
     * the object the method is executed on.
     * <p>
     * Returns `null` when:
     * <ul>
     *     <li>the `ConstraintViolation` is returned after calling
     *     [Validator#validateValue(KClass, String, Any, KClass[])]</li>
     *     <li>the `ConstraintViolation` is returned after validating a
     *     constructor.</li>
     * </ul>
     *
     * @return the validated object, the object hosting the validated element or `null`
     */
    fun getRootBean(): T?

    /**
     * Returns the class of the root bean being validated.
     * For method validation, this is the object class the
     * method is executed on.
     * For constructor validation, this is the class the constructor
     * is declared on.
     *
     * @return the class of the root bean or of the object hosting the validated element
     */
    fun getRootBeanClass(): KClass<T>
}
