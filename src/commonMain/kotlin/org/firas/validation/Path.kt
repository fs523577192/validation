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
import kotlin.reflect.KClass

/**
 * Represents the navigation path from an object to another
 * in an object graph.
 * Each path element is represented by a {@code Node}.
 *
 *
 * The path corresponds to the succession of nodes
 * in the order they are returned by the {@code Iterator}.
 *
 * @author Emmanuel Bernard
 * @author Gunnar Morling
 * @author Guillaume Smet
 * @author Wu Yuping
 */
interface Path: Iterable<Path.Node> {

    /**
     * Represents an element of a navigation path.
     */
    interface Node {
        /**
         * @return the index the node is placed in if contained in an array, a {@code List}
         *         or any other container supporting indexed access, {@code null} otherwise
         */
        @JsName("getIndex")
        fun getIndex(): Int

        /**
         * @return the key the node is placed in if contained in a {@code Map} or any
         *         other container supporting keyed access, {@code null} otherwise
         */
        @JsName("getKey")
        fun getKey(): Any?

        /**
         * @return {@code true} if the node represents an object contained in
         * a multi-valued container such as {@code Iterable} or {@code Map} or an array,
         * {@code false} otherwise
         */
        @JsName("isInIterable")
        fun isInIterable(): Boolean
    }

    /**
     * Node representing a method.
     *
     * @since 1.1
     */
    interface MethodNode: Node {
        /**
         * @return the list of parameter types
         */
        val parameterTypes: List<KClass<*>>
    }

    /**
     * Node representing a constructor.
     *
     * @since 1.1
     */
    interface ConstructorNode: Node {
        /**
         * @return the list of parameter types
         */
        val parameterTypes: List<KClass<*>>
    }

    /**
     * Node representing the return value of a method or constructor.
     *
     * @since 1.1
     */
    interface ReturnValueNode: Node

    /**
     * Node representing a parameter of a method or constructor.
     *
     * @since 1.1
     */
    interface ParameterNode: Node {
        /**
         * @return the parameter index in the method or constructor definition
         */
        val parameterIndex: Int
    }

    /**
     * Node representing a bean.
     *
     * @since 1.1
     */
    interface BeanNode : Node {
        /**
         * @return the type of the container the node is placed in, if contained in a
         * container type such as `Optional`, `List` or `Map`,
         * `null` otherwise
         *
         * @since 2.0
         */
        val containerClass: KClass<*>

        /**
         * @return the index of the type argument affected by the violated constraint, if
         * contained in a generic container type such as `Optional`, `List` or
         * `Map`.
         *
         * @since 2.0
         */
        val typeArgumentIndex: Int
    }

    /**
     * Node representing a property.
     *
     * @since 1.1
     */
    interface PropertyNode : Node {
        /**
         * @return the type of the container the node is placed in, if contained in a
         * container type such as `Optional`, `List` or `Map`,
         * `null` otherwise
         *
         * @since 2.0
         */
        val containerClass: KClass<*>

        /**
         * @return the index of the type argument affected by the violated constraint, if
         * contained in a generic container type such as `Optional`, `List` or
         * `Map`, `null` otherwise
         *
         * @since 2.0
         */
        val typeArgumentIndex: Int
    }

    /**
     * Node representing an element in a generic container such as `Optional`,
     * `List` or `Map`.
     *
     * @since 2.0
     */
    interface ContainerElementNode : Node {
        /**
         * @return the type of the container the node is placed in
         */
        val containerClass: KClass<*>

        /**
         * @return the index of the type argument affected by the violated constraint
         */
        val typeArgumentIndex: Int
    }
}