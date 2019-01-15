/*
 * Migrated from org.hibernate.validator:hibernate-validator:6.0.14.Final by Wu Yuping
 *
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.firas.validation.messageinterpolation.util

import kotlin.text.Regex

/**
 * Utilities used for message interpolation.
 *
 * @author Guillaume Smet
 * @author Wu Yuping
 */
class InterpolationHelper private constructor() {
    companion object {
        val BEGIN_TERM = '{'
        val END_TERM = '}'
        val EL_DESIGNATOR = '$'
        val ESCAPE_CHARACTER = '\\'

        private val ESCAPE_MESSAGE_PARAMETER_PATTERN = Regex("([\\$ESCAPE_CHARACTER$BEGIN_TERM$END_TERM$EL_DESIGNATOR])")

        fun escapeMessageParameter(messageParameter: String?): String? {
            return if (messageParameter == null) {
                null
            } else ESCAPE_MESSAGE_PARAMETER_PATTERN.replace(messageParameter,
                    Regex.escapeReplacement(ESCAPE_CHARACTER.toString()) + "$1")
        }
    }
}