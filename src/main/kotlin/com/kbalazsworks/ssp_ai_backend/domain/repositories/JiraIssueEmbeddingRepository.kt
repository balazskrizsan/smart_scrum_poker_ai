package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_TICKET_EMBEDDINGS
import com.kbalazsworks.ssp_ai_backend.db.tables.references.QUESTIONS
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.exceptions.JiraTicketEmbeddingException
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.JiraIssueSimilarity
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository

@Repository
class JiraIssueEmbeddingRepository(jooqService: JooqService) : AbstractRepository(jooqService) {
    fun save(jiraIssueEmbedding: JiraIssueEmbedding) = getCtx()
        .insertInto(jiraTicketEmbeddingTable)
        .set(JIRA_TICKET_EMBEDDINGS.JIRA_SPRINT_ID, jiraIssueEmbedding.jiraSprintId)
        .set(JIRA_TICKET_EMBEDDINGS.RAW_JSON, jiraIssueEmbedding.rawJson)
        .set(JIRA_TICKET_EMBEDDINGS.OPENAI_COMPATIBLE_TEXT, jiraIssueEmbedding.openaiCompatibleText)
        .set(JIRA_TICKET_EMBEDDINGS.EMBEDDING1536, toPgVectorField(jiraIssueEmbedding.embedding1536))
        .set(JIRA_TICKET_EMBEDDINGS.EMBEDDING3072, toPgVectorField(jiraIssueEmbedding.embedding3072))
        .set(JIRA_TICKET_EMBEDDINGS.CREATED_AT, jiraIssueEmbedding.createdAt)
        .returning()
        .fetchOneInto(JiraIssueEmbedding::class.java)
        ?: throw JiraTicketEmbeddingException("JiraTicketEmbedding creation failed.")

    fun similaritySearch(askAi: AskAi) = getCtx()
        .select(JIRA_TICKET_EMBEDDINGS.asterisk(), embedding1536comparator(askAi.questionId))
        .from(JIRA_TICKET_EMBEDDINGS)
        .where(JIRA_TICKET_EMBEDDINGS.JIRA_SPRINT_ID.eq(askAi.jiraSprintId))
        .orderBy(DSL.field("similarity").asc())
        .limit(10)
        .fetch()
        .mapNotNull { record ->
            val embedding = record.into(JiraIssueEmbedding::class.java)
            val similarity: Float = record.get("similarity", Float::class.java)
            JiraIssueSimilarity(similarity, embedding)
        }

    private fun embedding1536comparator(conditionId: Long) = DSL
        .field(
            "(${JIRA_TICKET_EMBEDDINGS.EMBEDDING1536.name}" +
                    " <=> " +
                    "(SELECT ${QUESTIONS.EMBEDDING1536.name} FROM ${QUESTIONS.name} WHERE id = {0}))",
            Float::class.java,
            conditionId
        ).`as`("similarity")
}
