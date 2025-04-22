package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_TICKET_EMBEDDINGS
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraTicketEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.exceptions.JiraTicketEmbeddingException
import com.pgvector.PGvector
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class JiraTicketEmbeddingRepository : AbstractRepository() {
    companion object {
        private val logger = LoggerFactory.getLogger(JiraTicketEmbeddingRepository::class.java)
    }

    fun save(jiraTicketEmbedding: JiraTicketEmbedding): JiraTicketEmbedding {
        val entity: JiraTicketEmbedding? = getDSLContext()
            .insertInto(JIRA_TICKET_EMBEDDINGS)
            .set(JIRA_TICKET_EMBEDDINGS.JIRA_SPRINT_ID, jiraTicketEmbedding.jiraSprintId)
            .set(JIRA_TICKET_EMBEDDINGS.RAW_JSON, jiraTicketEmbedding.rawJson)
            .set(
                JIRA_TICKET_EMBEDDINGS.EMBEDDING1536,
                DSL.field("?::vector", PGvector::class.java, jiraTicketEmbedding.embedding1536?.toString())
            )
            .set(
                JIRA_TICKET_EMBEDDINGS.EMBEDDING3072,
                DSL.field("?::vector", PGvector::class.java, jiraTicketEmbedding.embedding3072?.toString())
            )
            .set(JIRA_TICKET_EMBEDDINGS.CREATED_AT, jiraTicketEmbedding.createdAt)
            .returning()
            .fetchOneInto(JiraTicketEmbedding::class.java)

        return entity ?: throw JiraTicketEmbeddingException("JiraTicketEmbedding creation failed.")
    }
}
