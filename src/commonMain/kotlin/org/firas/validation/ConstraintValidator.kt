/*
 * Migrated from javax.validation:validation-api:2.0.1.Final by Wu Yuping
 *
 * Bean Validation API
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation

import kotlin.js.JsName

/**
 * Defines the logic to validate a given constraint `A`
 * for a given object type `T`.
 * <p>
 * Implementations must comply to the following restriction:
 * <ul>
 *     <li>`T` must resolve to a non parameterized type</li>
 *     <li>or generic parameters of `T` must be unbounded
 *     wildcard types</li>
 * </ul>
 * <p>
 * The annotation [SupportedValidationTarget] can be put on a
 * `ConstraintValidator` implementation to mark it as supporting
 * cross-parameter constraints. Check out [SupportedValidationTarget]
 * and [Constraint] for more information.
 *
 * @param <A> the annotation type handled by an implementation
 * @param <T> the target type supported by an implementation
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 * @author Wu Yuping
 */
interface ConstraintValidator<A: Annotation, T> {

    /**
     * Initializes the validator in preparation for
     * [#isValid(Object, ConstraintValidatorContext)] calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @JsName("initialize")
    fun initialize(constraintAnnotation: A)

    /**
     * Implements the validation logic.
     * The state of `value` must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value object to validate
     * @param context context in which the constraint is evaluated
     *
     * @return `false` if `value` does not pass the constraint
     */
    @JsName("isValid")
    fun isValid(value: T, context: ConstraintValidatorContext): Boolean
}
