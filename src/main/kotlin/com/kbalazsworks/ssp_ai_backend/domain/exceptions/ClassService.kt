package com.kbalazsworks.ssp_ai_backend.domain.exceptions

import com.kbalazsworks.ssp_ai_backend.domain.repositories.orm.OrmConstants
import org.jooq.impl.TableImpl
import org.jooq.impl.UpdatableRecordImpl
import org.slf4j.LoggerFactory
import java.lang.reflect.Field

class ClassService {
    companion object {
        private val logger = LoggerFactory.getLogger(ClassService::class.java)

        fun getTableName(clazz: Class<*>): TableImpl<UpdatableRecordImpl<*>> {
            val field: Field?
            try {
                field = clazz.getDeclaredField(OrmConstants.TABLE_NAME)
            } catch (e: NoSuchFieldException) {
                logger.info("Table name field missing in entity: {}", OrmConstants.TABLE_NAME)

                throw NoSuchFieldException("Table name field missing in entity: ${OrmConstants.TABLE_NAME}")
            }

            field.isAccessible = true

            @Suppress("UNCHECKED_CAST")
            return field.get(null) as TableImpl<UpdatableRecordImpl<*>>
        }
    }
}
