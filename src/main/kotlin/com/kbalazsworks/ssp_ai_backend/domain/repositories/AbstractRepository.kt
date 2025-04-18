package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraTicketEmbeddings
import com.pgvector.PGvector
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository

@Repository
abstract class AbstractRepository(private val jooqService: JooqService) {
    val jiraTicketEmbeddingTable = JiraTicketEmbeddings.JIRA_TICKET_EMBEDDINGS

    fun getCtx() = jooqService.getDslContext()

    fun toPgVectorField(embedding: PGvector?) = DSL.field("?::vector", PGvector::class.java, embedding?.toString())
}
