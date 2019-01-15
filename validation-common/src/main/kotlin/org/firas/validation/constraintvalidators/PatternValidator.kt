/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.constraintvalidators

import kotlin.text.Regex
import kotlin.text.RegexOption

import org.firas.validation.ConstraintValidator
import org.firas.validation.ConstraintValidatorContext
import org.firas.validation.constraints.Pattern
import org.firas.validation.messageinterpolation.util.InterpolationHelper

/**
 * @author Hardy Ferentschik
 * @author Wu Yuping
 */
open class PatternValidator: ConstraintValidator<Pattern, CharSequence?> {

    private var pattern: Regex? = null
    private var escapedRegexp: String? = null

    override fun initialize(constraintAnnotation: Pattern) {
        try {
            val flags = HashSet<RegexOption>(if (constraintAnnotation.flags.size > 0) constraintAnnotation.flags.size else 1)
            for (regexOption in constraintAnnotation.flags) {
                flags.add(regexOption)
            }
            this.pattern = Regex(constraintAnnotation.regexp, flags)
        } catch (ex: Exception) {
        }
        this.escapedRegexp = InterpolationHelper.escapeMessageParameter(constraintAnnotation.regexp)
    }

    override fun isValid(value: CharSequence?, context: ConstraintValidatorContext): Boolean {
        if (null == value) {
            return true
        }
        // if (context is xxx) {
        //     context.unwrap(xxx).addMessageParameter("regexp", escapedRegex)
        // }
        return pattern!!.matches(value)
    }
}