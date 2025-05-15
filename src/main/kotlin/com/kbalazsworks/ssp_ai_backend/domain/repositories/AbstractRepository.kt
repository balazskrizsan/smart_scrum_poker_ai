package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.pgvector.PGvector
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository

@Repository
abstract class AbstractRepository(private val jooqService: JooqService) {
    fun getCtx() = jooqService.getDslContext()

    fun toPgVectorField(embedding: PGvector?) = DSL.field("?::vector", PGvector::class.java, embedding?.toString())
}
