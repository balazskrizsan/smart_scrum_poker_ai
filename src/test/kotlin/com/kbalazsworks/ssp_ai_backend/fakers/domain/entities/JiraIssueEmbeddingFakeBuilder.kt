package com.kbalazsworks.ssp_ai_backend.fakers.domain.entities

import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import com.kbalazsworks.ssp_ai_backend.helpers.JiraHelper
import java.time.LocalDateTime

class JiraIssueEmbeddingFakeBuilder {
    companion object {
        const val DEFAULT_ID = 100000L
        const val DEFAULT_JIRA_SPRINT_ID = JiraSprintFakeBuilder.DEFAULT_ID
        val DEFAULT_RAW_JSON = JiraHelper.shortMockJiraIssueJson
        val DEFAULT_OPENAI_COMPATIBLE_TEXT = JiraHelper.shortMockJiraIssueText
        val DEFAULT_EMBEDDING_1536 = EmbeddingHelper.cached1536AsVector
        val DEFAULT_EMBEDDING_3072 = null
        val DEFAULT_CREATED_AT: LocalDateTime = DateTimeHelper.defaultLocalDateTime
    }

    private var id: Long? = DEFAULT_ID
    private var jiraSprintId = DEFAULT_JIRA_SPRINT_ID
    private var rawJson = DEFAULT_RAW_JSON
    private var openaiCompatibleText = DEFAULT_OPENAI_COMPATIBLE_TEXT
    private var embedding1536 = DEFAULT_EMBEDDING_1536
    private var embedding3072 = DEFAULT_EMBEDDING_3072
    private var createdAt: LocalDateTime = DEFAULT_CREATED_AT

    fun id(id: Long?) = apply { this.id = id }

    fun build() = JiraIssueEmbedding(
        id,
        jiraSprintId,
        rawJson,
        openaiCompatibleText,
        embedding1536,
        embedding3072,
        createdAt
    )
}
