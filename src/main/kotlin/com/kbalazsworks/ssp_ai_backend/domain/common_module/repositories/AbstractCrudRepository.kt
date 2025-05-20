package com.kbalazsworks.ssp_ai_backend.domain.common_module.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.jooq_orm.exceptions.OrmException
import com.kbalazsworks.ssp_ai_backend.jooq_orm.value_objects.TableMeta
import org.jooq.TableField
import org.jooq.UpdatableRecord
import org.jooq.impl.TableImpl
import org.springframework.stereotype.Repository

@Repository
@Suppress("FunctionName")
abstract class AbstractCrudRepository<ENTITY, TABLE_TYPE : TableImpl<RECORD_TYPE>, RECORD_TYPE : UpdatableRecord<*>>
    (jooqService: JooqService) : AbstractRepository(jooqService) {
    abstract val tableMeta: TableMeta<ENTITY, TABLE_TYPE>

    fun _save(entity: ENTITY): ENTITY = getCtx()
        .newRecord(tableMeta.table, entity).also { it.store() }
        .into(tableMeta.entityClass)

    fun _fetchOne(): ENTITY = getCtx()
        .selectFrom(tableMeta.table)
        .limit(1)
        .fetchOneInto(tableMeta.entityClass)
        ?: throwNotFound()

    fun _getOneById(id: Long): ENTITY = getCtx()
        .selectFrom(tableMeta.table)
        .where(getIdField().eq(id))
        .fetchOneInto(tableMeta.entityClass)
        ?: throwNotFound()

    fun _delete(id: Long) = getCtx()
        .deleteFrom(tableMeta.table)
        .where(getIdField().eq(id))
        .execute()

    private fun getIdField() = tableMeta.table.primaryKey?.fields?.first() as TableField<RECORD_TYPE, Any?>

    private fun throwNotFound(): Nothing = throw OrmException("Record not found")
}
