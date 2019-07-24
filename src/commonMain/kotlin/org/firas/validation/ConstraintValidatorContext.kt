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
 * Provides contextual data and operation when applying a given constraint validator.
 *
 * At least one [ConstraintViolation] must be defined (either the default one,
 * of if the default `ConstraintViolation` is disabled, a custom one).
 *
 * @author Emmanuel Bernard
 * @author Guillaume Smet
 * @author Wu Yuping
 */
interface ConstraintValidatorContext {

    /**
     * @return the current un-interpolated default message
     */
    @JsName("getDefaultConstraintMessageTemplate")
    fun getDefaultConstraintMessageTemplate(): String

    /**
     * Returns a constraint violation builder building a violation report
     * allowing to optionally associate it to a sub path.
     * The violation message will be interpolated.
     *
     *
     * To create the [ConstraintViolation], one must call either one of
     * the `addConstraintViolation()` methods available in one of the
     * interfaces of the fluent API.
     * If another method is called after `addConstraintViolation()` on
     * `ConstraintViolationBuilder` or any of its associated nested interfaces
     * an `IllegalStateException` is raised.
     *
     *
     * If [ConstraintValidator#isValid(Object, ConstraintValidatorContext)] returns
     * `false`, a `ConstraintViolation` object will be built per constraint
     * violation report including the default one (unless
     * [#disableDefaultConstraintViolation()] has been called).
     *
     *
     * `ConstraintViolation` objects generated from such a call
     * contain the same contextual information (root bean, path and so on) unless
     * the path has been overridden.
     *
     *
     * To create a different `ConstraintViolation`, a new constraint violation builder
     * has to be retrieved from `ConstraintValidatorContext`
     *
     * Here are a few usage examples:
     * <pre>
     * //assuming the following domain model
     * class User {
     *     fun getAddresses(): Map&lt;String,Address&gt; { ... }
     * }
     *
     * class Address {
     *     fun getStreet(): String { ... }
     *     fun getCountry(): Country { ... }
     * }
     *
     * class Country {
     *     fun getName(): String { ... }
     * }
     *
     * //From a property-level constraint on User.addresses
     * //Build a constraint violation on the default path - i.e. the "addresses" property
     * context.buildConstraintViolationWithTemplate( "this detail is wrong" )
     *             .addConstraintViolation();
     *
     * //From a class level constraint on Address
     * //Build a constraint violation on the default path + "street"
     * //i.e. the street property of Address
     * context.buildConstraintViolationWithTemplate( "this detail is wrong" )
     *             .addPropertyNode( "street" )
     *             .addConstraintViolation();
     *
     * //From a property-level constraint on  User.addresses
     * //Build a constraint violation on the default path + the bean stored
     * //under the "home" key in the map
     * context.buildConstraintViolationWithTemplate( "Incorrect home address" )
     *             .addBeanNode()
     *                 .inContainer( Map.class, 1 )
     *                 .inIterable().atKey( "home" )
     *             .addConstraintViolation();
     *
     * //From a class level constraint on User
     * //Build a constraint violation on the default path + addresses["home"].country.name
     * //i.e. property "country.name" on the object stored under "home" in the map
     * context.buildConstraintViolationWithTemplate( "this detail is wrong" )
     *             .addPropertyNode( "addresses" )
     *             .addPropertyNode( "country" )
     *                 .inContainer( Map.class, 1 )
     *                 .inIterable().atKey( "home" )
     *             .addPropertyNode( "name" )
     *             .addConstraintViolation();
     *
     * //From a class level constraint on User
     * //Build a constraint violation on the default path + addresses["home"].&lt;map key&gt;
     * //i.e. a container element constraint violation for the map key
     * context.buildConstraintViolationWithTemplate( "the map key is invalid" )
     *             .addPropertyNode( "addresses" )
     *             .addContainerElementNode( "&lt;map key&gt;", Map.class, 0 )
     *                 .inIterable().atKey( "invalid" )
     *             .addConstraintViolation();
     * </pre>
     * <p>
     * Cross-parameter constraints on a method can create a node specific
     * to a particular parameter if required. Let's explore a few examples:
     * <pre>
     * //Cross-parameter constraint on method
     * //createUser(String password, String passwordRepeat)
     * //Build a constraint violation on the default path + "passwordRepeat"
     * context.buildConstraintViolationWithTemplate("Passwords do not match")
     *             .addParameterNode(1)
     *             .addConstraintViolation();
     *
     * //Cross-parameter constraint on a method
     * //mergeAddresses(Map&lt;String,Address&gt; addresses,
     * //        Map&lt;String,Address&gt; otherAddresses)
     * //Build a constraint violation on the default path + "otherAddresses["home"]
     * //i.e. the Address bean hosted in the "home" key of the "otherAddresses" map parameter
     * context.buildConstraintViolationWithTemplate(
     *         "Map entry home present in both and does not match")
     *             .addParameterNode(1)
     *             .addBeanNode()
     *                 .inContainer( Map.class, 1 )
     *                 .inIterable().atKey("home")
     *             .addConstraintViolation();
     *
     * //Cross-parameter constraint on a method
     * //mergeAddresses(Map&lt;String,Address&gt; addresses,
     * //        Map&lt;String,Address&gt; otherAddresses)
     * //Build a constraint violation on the default path + "otherAddresses["home"].city
     * //i.e. on the "city" property of the Address bean hosted in
     * //the "home" key of the "otherAddresses" map
     * context.buildConstraintViolationWithTemplate(
     *         "Map entry home present in both but city does not match")
     *             .addParameterNode(1)
     *             .addPropertyNode("city")
     *                 .inContainer( Map.class, 1 )
     *                 .inIterable().atKey("home")
     *             .addConstraintViolation();
     * </pre>
     *
     * @param messageTemplate new un-interpolated constraint message
     * @return returns a constraint violation builder
     */
    @JsName("buildConstraintViolationWithTemplate")
    fun buildConstraintViolationWithTemplate(messageTemplate: String): ConstraintViolationBuilder

    /**
     * [ConstraintViolation] builder allowing to optionally associate
     * the violation report to a sub path.
     *
     *
     * To create the `ConstraintViolation`, one must call either one of
     * the `addConstraintViolation()` methods available in one of the
     * interfaces of the fluent API.
     *
     *
     * If another method is called after `addConstraintViolation()` on
     * `ConstraintViolationBuilder` or any of its associated objects
     * an `IllegalStateException` is raised.
     */
    interface ConstraintViolationBuilder {

        /**
         * Adds a property node to the path the [ConstraintViolation]
         * will be associated to.
         *
         *
         * `name` describes a single property. In particular,
         * dot (.) is not allowed.
         *
         * @param name property name
         * @return a builder representing node `name`
         * @throws IllegalArgumentException if the name is null
         *
         * @since 1.1
         */
        @JsName("addPropertyNode")
        fun addPropertyNode(name: String): NodeBuilderCustomizableContext

        /**
         * Adds a bean node (class-level) to the path the [ConstraintViolation]
         * will be associated to.
         * Note that bean nodes are always leaf nodes.
         *
         * @return a builder representing the bean node
         *
         * @since 1.1
         */
        @JsName("addBeanNode")
        fun addBeanNode(): LeafNodeBuilderCustomizableContext

        /**
         * Adds a container element node to the path the [ConstraintViolation]
         * will be associated to.
         *
         * @param name the node name
         * @param containerType the type of the container
         * @param typeArgumentIndex the index of the type argument
         * @return a builder representing the container element node
         * @throws IllegalArgumentException if the index is not valid
         *
         * @since 2.0
         */
        @JsName("addContainerElementNode")
        fun addContainerElementNode(name: String,
                                    containerType: KClass<*>,
                                    typeArgumentIndex: Int): ContainerElementNodeBuilderCustomizableContext

        /**
         * Adds a method parameter node to the path the [ConstraintViolation]
         * will be associated to.
         * The parameter index must be valid (i.e. within the boundaries of the method
         * parameter indexes). May only be called from within cross-parameter validators.
         *
         * @param index the parameter index
         * @return a builder representing the index-th parameter node
         * @throws IllegalArgumentException if the index is not valid
         *
         * @since 1.1
         */
        @JsName("addParameterNode")
        fun addParameterNode(index: Int): NodeBuilderDefinedContext

        /**
         * Adds the new [ConstraintViolation] to be generated if the
         * constraint validator marks the value as invalid.
         *
         *
         * Methods of this `ConstraintViolationBuilder` instance and its nested
         * objects throw `IllegalStateException` from now on.
         *
         * @return the `ConstraintValidatorContext` instance the
         * `ConstraintViolationBuilder` comes from
         */
        @JsName("addConstraintViolation")
        fun addConstraintViolation(): ConstraintValidatorContext

        /**
         * Represents a node whose context is known
         * (i.e. index, key and isInIterable)
         * and that is a leaf node (i.e. no subnode can be added).
         *
         * @since 1.1
         */
        interface LeafNodeBuilderDefinedContext {

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator marks the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }

        /**
         * Represents a node whose context is
         * configurable (i.e. index, key and isInIterable)
         * and that is a leaf node (i.e. no subnode can be added).
         *
         * @since 1.1
         */
        interface LeafNodeBuilderCustomizableContext {

            /**
             * Marks the node as being in an iterable, e.g. array, `Iterable` or a
             * `Map`.
             *
             * @return a builder representing iterable details
             */
            @JsName("inIterable")
            fun inIterable(): LeafNodeContextBuilder

            /**
             * Marks the node as being in a container such as a `List`, `Map` or
             * `Optional`.
             *
             * @param containerClass the type of the container
             * @param typeArgumentIndex type index of the concerned type argument
             * @return a builder representing the current node
             * @throws IllegalArgumentException if the index is not valid
             *
             * @since 2.0
             */
            @JsName("inContainer")
            fun inContainer(containerClass: KClass<*>,
                            typeArgumentIndex: Int): LeafNodeBuilderCustomizableContext

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator mark the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }

        /**
         * Represents refinement choices for a node which is
         * in an iterable, e.g. array, `Iterable` or `Map`.
         *
         *
         * If the iterable is an indexed collection or a map,
         * the index or the key should be set.
         *
         *
         * The node is a leaf node (i.e. no subnode can be added).
         *
         * @since 1.1
         */
        interface LeafNodeContextBuilder {

            /**
             * Defines the key the object is into the `Map`.
             *
             * @param key map key
             * @return a builder representing the current node
             */
            @JsName("atKey")
            fun atKey(key: Any): LeafNodeBuilderDefinedContext

            /**
             * Defines the index the object is into the `List` or array
             *
             * @param index index
             * @return a builder representing the current node
             */
            @JsName("atIndex")
            fun atIndex(index: Int): LeafNodeBuilderDefinedContext

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator mark the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }

        /**
         * Represents a node whose context is known
         * (i.e. index, key and isInIterable)
         * and that is not necessarily a leaf node (i.e. subnodes can
         * be added).
         */
        interface NodeBuilderDefinedContext {

            /**
             * Adds a property node to the path the [ConstraintViolation]
             * will be associated to.
             *
             *
             * `name` describes a single property. In particular,
             * dot (.) is not allowed.
             *
             * @param name property name
             * @return a builder representing node `name`
             * @throws IllegalArgumentException if the name is null
             *
             * @since 1.1
             */
            @JsName("addPropertyNode")
            fun addPropertyNode(name: String): NodeBuilderCustomizableContext

            /**
             * Adds a bean node (class-level) to the path the [ConstraintViolation]
             * will be associated to.
             * Note that bean nodes are always leaf nodes.
             *
             * @return a builder representing the bean node
             *
             * @since 1.1
             */
            @JsName("addBeanNode")
            fun addBeanNode(): LeafNodeBuilderCustomizableContext

            /**
             * Adds a container element node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * @param name the node name
             * @param containerType the type of the container
             * @param typeArgumentIndex the index of the type argument
             * @return a builder representing the container element node
             * @throws IllegalArgumentException if the index is not valid
             *
             * @since 2.0
             */
            @JsName("addContainerElementNode")
            fun addContainerElementNode(
                    name: String,
                    containerType: KClass<*>,
                    typeArgumentIndex: Int): ContainerElementNodeBuilderCustomizableContext

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator marks the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }

        /**
         * Represents a node whose context is
         * configurable (i.e. index, key and isInIterable)
         * and that is not necessarily a leaf node (i.e. subnodes can
         * be added).
         */
        interface NodeBuilderCustomizableContext {

            /**
             * Marks the node as being in an iterable, e.g. array, `Iterable` or a
             * `Map`.
             *
             * @return a builder representing iterable details
             */
            @JsName("inIterable")
            fun inIterable(): NodeContextBuilder

            /**
             * Marks the node as being in a container such as a `List`, `Map` or
             * `Optional`.
             *
             * @param containerClass the type of the container
             * @param typeArgumentIndex type index of the concerned type argument
             * @return a builder representing the current node
             * @throws IllegalArgumentException if the index is not valid
             *
             * @since 2.0
             */
            @JsName("inContainer")
            fun inContainer(containerClass: KClass<*>,
                            typeArgumentIndex: Int): NodeBuilderCustomizableContext

            /**
             * Adds a property node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * `name` describes a single property. In particular,
             * dot (.) is not allowed.
             *
             * @param name property name
             * @return a builder representing node `name`
             * @throws IllegalArgumentException if the name is null
             *
             * @since 1.1
             */
            @JsName("addPropertyNode")
            fun addPropertyNode(name: String): NodeBuilderCustomizableContext

            /**
             * Adds a bean node (class-level) to the path the [ConstraintViolation]
             * will be associated to.
             * Note that bean nodes are always leaf nodes.
             *
             * @return a builder representing the bean node
             *
             * @since 1.1
             */
            @JsName("addBeanNode")
            fun addBeanNode(): LeafNodeBuilderCustomizableContext

            /**
             * Adds a container element node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * @param name the node name
             * @param containerType the type of the container
             * @param typeArgumentIndex the index of the type argument
             * @return a builder representing the container element node
             * @throws IllegalArgumentException if the index is not valid
             *
             * @since 2.0
             */
            @JsName("addContainerElementNode")
            fun addContainerElementNode(
                    name: String,
                    containerType: KClass<*>,
                    typeArgumentIndex: Int): ContainerElementNodeBuilderCustomizableContext

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator mark the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }

        /**
         * Represents refinement choices for a node which is
         * in an iterable, e.g. array, `Iterable` or `Map`.
         *
         *
         * If the iterable is an indexed collection or a map,
         * the index or the key should be set.
         *
         *
         * The node is not necessarily a leaf node (i.e. subnodes can
         * be added).
         */
        interface NodeContextBuilder {

            /**
             * Defines the key the object is into the `Map`.
             *
             * @param key map key
             * @return a builder representing the current node
             */
            @JsName("atKey")
            fun atKey(key: Any): NodeBuilderDefinedContext

            /**
             * Defines the index the object is into the `List` or array.
             *
             * @param index index
             * @return a builder representing the current node
             */
            @JsName("atIndex")
            fun atIndex(index: Int): NodeBuilderDefinedContext

            /**
             * Adds a property node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * `name` describes a single property. In particular,
             * dot (.) is not allowed.
             *
             * @param name property name
             * @return a builder representing node `name`
             * @throws IllegalArgumentException if the name is null
             *
             * @since 1.1
             */
            @JsName("addPropertyNode")
            fun addPropertyNode(name: String): NodeBuilderCustomizableContext

            /**
             * Adds a bean node (class-level) to the path the [ConstraintViolation]
             * will be associated to.
             *
             *
             * Note that bean nodes are always leaf nodes.
             *
             * @return a builder representing the bean node
             *
             * @since 1.1
             */
            @JsName("addBeanNode")
            fun addBeanNode(): LeafNodeBuilderCustomizableContext

            /**
             * Adds a container element node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * @param name the node name
             * @param containerType the type of the container
             * @param typeArgumentIndex the index of the type argument
             * @return a builder representing the container element node
             * @throws IllegalArgumentException if the index is not valid
             *
             * @since 2.0
             */
            @JsName("addContainerElementNode")
            fun addContainerElementNode(
                    name: String,
                    containerType: KClass<*>,
                    typeArgumentIndex: Int): ContainerElementNodeBuilderCustomizableContext

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator mark the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }

        /**
         * Represents a container element node whose context is known
         * (i.e. index, key and isInIterable)
         * and that is not necessarily a leaf node (i.e. subnodes can
         * be added).
         *
         * @since 2.0
         */
        interface ContainerElementNodeBuilderDefinedContext {

            /**
             * Adds a property node to the path the [ConstraintViolation]
             * will be associated to.
             *
             *
             * `name` describes a single property. In particular,
             * dot (.) is not allowed.
             *
             * @param name property name
             * @return a builder representing node `name`
             * @throws IllegalArgumentException if the name is null
             */
            @JsName("addPropertyNode")
            fun addPropertyNode(name: String): NodeBuilderCustomizableContext

            /**
             * Adds a bean node (class-level) to the path the [ConstraintViolation]
             * will be associated to.
             * Note that bean nodes are always leaf nodes.
             *
             * @return a builder representing the bean node
             */
            @JsName("addBeanNode")
            fun addBeanNode(): LeafNodeBuilderCustomizableContext

            /**
             * Adds a container element node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * @param name the node name
             * @param containerType the type of the container
             * @param typeArgumentIndex the index of the type argument
             * @return a builder representing the container element node
             * @throws IllegalArgumentException if the index is not valid
             */
            @JsName("addContainerElementNode")
            fun addContainerElementNode(
                    name: String,
                    containerType: KClass<*>,
                    typeArgumentIndex: Int): ContainerElementNodeBuilderCustomizableContext

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator marks the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }

        /**
         * Represents a container element node whose context is
         * configurable (i.e. index, key and isInIterable)
         * and that is not necessarily a leaf node (i.e. subnodes can
         * be added).
         *
         * @since 2.0
         */
        interface ContainerElementNodeBuilderCustomizableContext {

            /**
             * Marks the node as being in an iterable, e.g. array, `Iterable` or a
             * `Map`.
             *
             * @return a builder representing iterable details
             */
            @JsName("inIterable")
            fun inIterable(): ContainerElementNodeContextBuilder

            /**
             * Adds a property node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * `name` describes a single property. In particular,
             * dot (.) is not allowed.
             *
             * @param name property name
             * @return a builder representing node `name`
             * @throws IllegalArgumentException if the name is null
             */
            @JsName("addPropertyNode")
            fun addPropertyNode(name: String): NodeBuilderCustomizableContext

            /**
             * Adds a bean node (class-level) to the path the [ConstraintViolation]
             * will be associated to.
             *
             *
             * Note that bean nodes are always leaf nodes.
             *
             * @return a builder representing the bean node
             */
            @JsName("addBeanNode")
            fun addBeanNode(): LeafNodeBuilderCustomizableContext

            /**
             * Adds a container element node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * @param name the node name
             * @param containerType the type of the container
             * @param typeArgumentIndex the index of the type argument
             * @return a builder representing the container element node
             * @throws IllegalArgumentException if the index is not valid
             */
            @JsName("addContainerElementNode")
            fun addContainerElementNode(
                    name: String,
                    containerType: KClass<*>,
                    typeArgumentIndex: Int): ContainerElementNodeBuilderCustomizableContext

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator mark the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }

        /**
         * Represents refinement choices for a container element node.
         *
         *
         * If the container is an indexed collection or a map,
         * the index or the key should be set.
         *
         *
         * The node is not necessarily a leaf node (i.e. subnodes can
         * be added).
         *
         * @since 2.0
         */
        interface ContainerElementNodeContextBuilder {

            /**
             * Defines the key the object is into the `Map`.
             *
             * @param key map key
             * @return a builder representing the current node
             */
            @JsName("atKey")
            fun atKey(key: Any): ContainerElementNodeBuilderDefinedContext

            /**
             * Defines the index the object is into the `List` or array.
             *
             * @param index index
             * @return a builder representing the current node
             */
            @JsName("atIndex")
            fun atIndex(index: Int): ContainerElementNodeBuilderDefinedContext

            /**
             * Adds a property node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * `name` describes a single property. In particular,
             * dot (.) is not allowed.
             *
             * @param name property name
             * @return a builder representing node `name`
             * @throws IllegalArgumentException if the name is null
             */
            @JsName("addPropertyNode")
            fun addPropertyNode(name: String): NodeBuilderCustomizableContext

            /**
             * Adds a bean node (class-level) to the path the [ConstraintViolation]
             * will be associated to.
             *
             *
             * Note that bean nodes are always leaf nodes.
             *
             * @return a builder representing the bean node
             */
            @JsName("addBeanNode")
            fun addBeanNode(): LeafNodeBuilderCustomizableContext

            /**
             * Adds a container element node to the path the [ConstraintViolation]
             * will be associated to.
             *
             * @param name the node name
             * @param containerType the type of the container
             * @param typeArgumentIndex the index of the type argument
             * @return a builder representing the container element node
             * @throws IllegalArgumentException if the index is not valid
             */
            @JsName("addContainerElementNode")
            fun addContainerElementNode(
                    name: String,
                    containerType: KClass<*>,
                    typeArgumentIndex: Int): ContainerElementNodeBuilderCustomizableContext

            /**
             * Adds the new [ConstraintViolation] to be generated if the
             * constraint validator mark the value as invalid.
             *
             *
             * Methods of the `ConstraintViolationBuilder` instance this object
             * comes from and the constraint violation builder nested
             * objects throw `IllegalStateException` after this call.
             *
             * @return `ConstraintValidatorContext` instance the
             * `ConstraintViolationBuilder` comes from
             */
            @JsName("addConstraintViolation")
            fun addConstraintViolation(): ConstraintValidatorContext
        }
    }
}