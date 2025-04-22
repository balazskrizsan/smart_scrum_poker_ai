package com.kbalazsworks.ssp_ai_backend.fakers.domain.entities

import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraTicketEmbedding
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import java.time.LocalDateTime

class JiraTicketEmbeddingFakeBuilder {
    companion object {
        const val DEFAULT_ID = 100000L
        const val DEFAULT_JIRA_SPRINT_ID = JiraSprintFakeBuilder.DEFAULT_ID
        const val DEFAULT_RAW_JSON = "Default text for embedding"
        val DEFAULT_EMBEDDING_1536 = EmbeddingHelper.cached1536AsVector
        val DEFAULT_EMBEDDING_3072 = null
        val DEFAULT_CREATED_AT: LocalDateTime = DateTimeHelper.defaultLocalDateTime
    }

    private var id: Long? = DEFAULT_ID
    private var jiraSprintId = DEFAULT_JIRA_SPRINT_ID
    private var rawJson = DEFAULT_RAW_JSON
    private var embedding1536 = DEFAULT_EMBEDDING_1536
    private var embedding3072 = DEFAULT_EMBEDDING_3072
    private var createdAt: LocalDateTime = DEFAULT_CREATED_AT

    fun id(id: Long?) = apply { this.id = id }

    fun build() = JiraTicketEmbedding(id, jiraSprintId, rawJson, embedding1536, embedding3072, createdAt)
}
