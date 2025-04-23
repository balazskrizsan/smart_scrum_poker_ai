package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.domain.exceptions.ClassService
import com.kbalazsworks.ssp_ai_backend.domain.repositories.orm.OrmException
import org.springframework.stereotype.Repository

@Repository
abstract class AbstractCrudRepository<T>(private val jooqService: JooqService) : AbstractRepository(jooqService) {
    fun ormSave(entity: T): T {
        val nonNullEntityClass = entity!!::class.java

        val record = jooqService.getDslContext().newRecord(ClassService.getTableName(nonNullEntityClass), entity)
        record.store()

        return record.into(nonNullEntityClass) as T
    }

    fun ormFetchOne(entityClass: Class<T>): T {
        return jooqService.getDslContext().selectFrom(ClassService.getTableName(entityClass)).fetchOneInto(entityClass)
            ?: throw OrmException("Entity not found")
    }
}
