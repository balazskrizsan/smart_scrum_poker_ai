package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_TICKET_EMBEDDINGS
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.exceptions.JiraTicketEmbeddingException
import com.pgvector.PGvector
import org.jooq.impl.DSL
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class JiraIssueEmbeddingRepository(private val jooqService: JooqService) : AbstractRepository(jooqService) {
    companion object {
        private val logger = LoggerFactory.getLogger(JiraIssueEmbeddingRepository::class.java)
    }

    fun save(jiraIssueEmbedding: JiraIssueEmbedding): JiraIssueEmbedding {
        val entity: JiraIssueEmbedding? = jooqService.getDslContext()
            .insertInto(JIRA_TICKET_EMBEDDINGS)
            .set(JIRA_TICKET_EMBEDDINGS.JIRA_SPRINT_ID, jiraIssueEmbedding.jiraSprintId)
            .set(JIRA_TICKET_EMBEDDINGS.RAW_JSON, jiraIssueEmbedding.rawJson)
            .set(JIRA_TICKET_EMBEDDINGS.OPENAI_COMPATIBLE_TEXT, jiraIssueEmbedding.openaiCompatibleText)
            .set(
                JIRA_TICKET_EMBEDDINGS.EMBEDDING1536,
                DSL.field("?::vector", PGvector::class.java, jiraIssueEmbedding.embedding1536?.toString())
            )
            .set(
                JIRA_TICKET_EMBEDDINGS.EMBEDDING3072,
                DSL.field("?::vector", PGvector::class.java, jiraIssueEmbedding.embedding3072?.toString())
            )
            .set(JIRA_TICKET_EMBEDDINGS.CREATED_AT, jiraIssueEmbedding.createdAt)
            .returning()
            .fetchOneInto(JiraIssueEmbedding::class.java)

        return entity ?: throw JiraTicketEmbeddingException("JiraTicketEmbedding creation failed.")
    }
}
