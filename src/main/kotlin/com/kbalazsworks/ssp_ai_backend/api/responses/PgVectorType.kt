package com.kbalazsworks.ssp_ai_backend.api.responses

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.usertype.UserType
import java.io.Serializable
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

class PgVectorType : UserType<List<Float>> {

    override fun getSqlType(): Int = Types.OTHER

    override fun returnedClass(): Class<List<Float>> = List::class.java as Class<List<Float>>

    override fun equals(x: List<Float>?, y: List<Float>?): Boolean = x == y

    override fun hashCode(x: List<Float>?): Int = x?.hashCode() ?: 0

    override fun nullSafeGet(
        rs: ResultSet,
        position: Int,
        session: SharedSessionContractImplementor?,
        owner: Any?
    ): List<Float>? {
        val value = rs.getString(position) ?: return null
        return value
            .removePrefix("[")
            .removeSuffix("]")
            .split(",")
            .map { it.trim().toFloat() }
    }

    override fun nullSafeSet(
        st: PreparedStatement,
        value: List<Float>?,
        index: Int,
        session: SharedSessionContractImplementor?
    ) {
        if (value == null) {
            st.setNull(index, Types.OTHER)
        } else {
            val vectorStr = value.joinToString(prefix = "[", postfix = "]")
            st.setObject(index, vectorStr, Types.OTHER)
        }
    }

    override fun isMutable(): Boolean = true

    override fun assemble(cached: Serializable?, owner: Any?): List<Float>? = (cached as? List<Float>)?.toList()

    override fun disassemble(value: List<Float>?): Serializable? = value?.toList() as Serializable?

    override fun deepCopy(value: List<Float>?): List<Float>? = value?.toList()
}
