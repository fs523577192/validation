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
 * Marks an annotation as being a Bean Validation constraint.
 *
 *
 * A given constraint annotation must be annotated by a {@code @Constraint}
 * annotation which refers to its list of constraint validation implementations.
 *
 *
 * Each constraint annotation must host the following attributes:
 * <ul>
 *     <li>`val message: String = [...]` which should default to an error
 *     message key made of the fully-qualified class name of the constraint followed by
 *     `.message`. For example `"{com.acme.constraints.NotSafe.message}"`</li>
 *     <li>`val groups: Array<KClass<?>> = arrayOf()` for user to customize the targeted
 *     groups</li>
 *     <li>`val payload: Array<KClass<? extends Payload>> = arrayOf()` for
 *     extensibility purposes</li>
 * </ul>
 *
 *
 * When building a constraint that is both generic and cross-parameter, the constraint
 * annotation must host the {@code validationAppliesTo()} property.
 * A constraint is generic if it targets the annotated element and is cross-parameter if
 * it targets the array of parameters of a method or constructor.
 * <pre>
 *     ConstraintTarget validationAppliesTo() default ConstraintTarget.IMPLICIT;
 * </pre>
 * This property allows the constraint user to choose whether the constraint
 * targets the return type of the executable or its array of parameters.
 *
 * A constraint is both generic and cross-parameter if
 * <ul>
 *     <li>two kinds of `ConstraintValidator`s are attached to the
 *     constraint, one targeting [ValidationTarget#ANNOTATED_ELEMENT]
 *     and one targeting [ValidationTarget#PARAMETERS],</li>
 *     <li>or if a `ConstraintValidator` targets both
 *     `ANNOTATED_ELEMENT` and `PARAMETERS`.</li>
 * </ul>
 *
 * Such dual constraints are rare. See [SupportedValidationTarget] for more info.
 *
 *
 * Here is an example of constraint definition:
 * <pre>
 * &#64;Documented
 * &#64;Constraint(validatedBy = OrderNumberValidator.class)
 * &#64;Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
 * &#64;Retention(RUNTIME)
 * annotation class OrderNumber(
 *     val message: String = "{com.acme.constraint.OrderNumber.message}",
 *     val groups: Array&lt;KClass&lt;?&gt;&gt; = arrayOf(),
 *     val payload: Array&lt;KClass&lt;? extends Payload&gt;&gt; = arrayOf()
 * )
 * </pre>
 *
 * @author Emmanuel Bernard
 * @author Gavin King
 * @author Hardy Ferentschik
 * @author Wu Yuping
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Constraint<T: ConstraintValidator<*, *>>(
        /**
         * [ConstraintValidator] classes implementing the constraint. The given classes
         * must reference distinct target types for a given [ValidationTarget]. If two
         * `ConstraintValidator`s refer to the same type, an exception will occur.
         *
         *
         * At most one `ConstraintValidator` targeting the array of parameters of
         * methods or constructors (aka cross-parameter) is accepted. If two or more
         * are present, an exception will occur.
         *
         *
         * @return array of `ConstraintValidator` classes implementing the constraint
         */
        val validatedBy: Array<kotlin.reflect.KClass<T>>
)
