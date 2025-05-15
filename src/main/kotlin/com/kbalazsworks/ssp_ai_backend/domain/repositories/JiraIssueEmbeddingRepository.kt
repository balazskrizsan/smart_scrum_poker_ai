package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_ISSUES
import com.kbalazsworks.ssp_ai_backend.db.tables.references.QUESTIONS
import com.kbalazsworks.ssp_ai_backend.db.tables.references.VECTOR_STORE_1536
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.exceptions.JiraTicketEmbeddingException
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.JiraIssueSimilarity
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository

@Repository
class JiraIssueEmbeddingRepository(jooqService: JooqService) : AbstractRepository(jooqService) {
    fun save(jiraIssueEmbedding: JiraIssueEmbedding) = getCtx()
        .insertInto(JIRA_ISSUES)
        .set(JIRA_ISSUES.JIRA_SPRINT_ID, jiraIssueEmbedding.jiraSprintId)
        .set(JIRA_ISSUES.RAW_JSON, jiraIssueEmbedding.rawJson)
        .set(JIRA_ISSUES.OPENAI_COMPATIBLE_TEXT, jiraIssueEmbedding.openaiCompatibleText)
        .set(JIRA_ISSUES.CREATED_AT, jiraIssueEmbedding.createdAt)
        .returning()
        .fetchOneInto(JiraIssueEmbedding::class.java)
        ?: throw JiraTicketEmbeddingException("JiraTicketEmbedding creation failed.")

    fun similaritySearch(askAi: AskAi): List<JiraIssueSimilarity> = getCtx()
        .select(JIRA_ISSUES.asterisk(), embedding1536comparator(askAi.questionId))
        .from(JIRA_ISSUES)
        .leftJoin(VECTOR_STORE_1536).on(VECTOR_STORE_1536.JIRA_ISSUE_ID.eq(JIRA_ISSUES.ID))
        .where(JIRA_ISSUES.JIRA_SPRINT_ID.eq(askAi.jiraSprintId))
        .orderBy(DSL.field("similarity").asc())
        .limit(10)
        .fetch()
        .mapNotNull { record ->
            val embedding = record.into(JiraIssueEmbedding::class.java)
            val similarity = record.get("similarity", Float::class.java)
            JiraIssueSimilarity(similarity, embedding)
        }

    private fun embedding1536comparator(conditionId: Long) = DSL
        .field(
            "(${VECTOR_STORE_1536.EMBEDDING.name}" + " <=> " + "(" + getSubQuery(conditionId) + ")" + ")",
            Float::class.java,
            conditionId
        ).`as`("similarity")

    private fun getSubQuery(conditionId: Long) = getCtx()
        .select(VECTOR_STORE_1536.EMBEDDING)
        .from(QUESTIONS)
        .leftJoin(VECTOR_STORE_1536).on(VECTOR_STORE_1536.QUESTION_ID.eq(QUESTIONS.ID))
        .where(QUESTIONS.ID.eq(conditionId))
        .sql
}
