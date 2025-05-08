package com.kbalazsworks.ssp_ai_backend.jooq_orm.value_objects

data class TableMeta<ENTITY, TABLE_TYPE>(
    val entityClass: Class<ENTITY>,
    val table: TABLE_TYPE,
)
