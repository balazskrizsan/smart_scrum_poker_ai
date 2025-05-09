/*
 * This file is generated by jOOQ.
 */
package com.kbalazsworks.ssp_ai_backend.db.routines


import com.kbalazsworks.ssp_ai_backend.db.Public

import org.jooq.Field
import org.jooq.Parameter
import org.jooq.impl.AbstractRoutine
import org.jooq.impl.DefaultDataType
import org.jooq.impl.Internal
import org.jooq.impl.SQLDataType


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class VectorToFloat4 : AbstractRoutine<Array<Float?>>("vector_to_float4", Public.PUBLIC, SQLDataType.REAL.array()) {
    companion object {

        /**
         * The parameter <code>public.vector_to_float4.RETURN_VALUE</code>.
         */
        val RETURN_VALUE: Parameter<Array<Float?>?> = Internal.createParameter("RETURN_VALUE", SQLDataType.REAL.array(), false, false)
        @Deprecated(message = "Unknown data type. If this is a qualified, user-defined type, it may have been excluded from code generation. If this is a built-in type, you can define an explicit org.jooq.Binding to specify how this type should be handled. Deprecation can be turned off using <deprecationOnUnknownTypes/> in your code generator configuration.")
        val _1: Parameter<Any?> = Internal.createParameter("_1", DefaultDataType.getDefaultDataType("\"public\".\"vector\""), false, true)

        /**
         * The parameter <code>public.vector_to_float4._2</code>.
         */
        val _2: Parameter<Int?> = Internal.createParameter("_2", SQLDataType.INTEGER, false, true)

        /**
         * The parameter <code>public.vector_to_float4._3</code>.
         */
        val _3: Parameter<Boolean?> = Internal.createParameter("_3", SQLDataType.BOOLEAN, false, true)
    }

    init {
        returnParameter = VectorToFloat4.RETURN_VALUE
        addInParameter(VectorToFloat4._1)
        addInParameter(VectorToFloat4._2)
        addInParameter(VectorToFloat4._3)
    }

    /**
     * Set the <code>_1</code> parameter IN value to the routine
     */
    fun set__1(value: Any?): Unit = setValue(VectorToFloat4._1, value)

    /**
     * Set the <code>_1</code> parameter to the function to be used with a
     * {@link org.jooq.Select} statement
     */
    fun set__1(field: Field<Any?>): Unit {
        setField(VectorToFloat4._1, field)
    }

    /**
     * Set the <code>_2</code> parameter IN value to the routine
     */
    fun set__2(value: Int?): Unit = setValue(VectorToFloat4._2, value)

    /**
     * Set the <code>_2</code> parameter to the function to be used with a
     * {@link org.jooq.Select} statement
     */
    fun set__2(field: Field<Int?>): Unit {
        setField(VectorToFloat4._2, field)
    }

    /**
     * Set the <code>_3</code> parameter IN value to the routine
     */
    fun set__3(value: Boolean?): Unit = setValue(VectorToFloat4._3, value)

    /**
     * Set the <code>_3</code> parameter to the function to be used with a
     * {@link org.jooq.Select} statement
     */
    fun set__3(field: Field<Boolean?>): Unit {
        setField(VectorToFloat4._3, field)
    }
}
