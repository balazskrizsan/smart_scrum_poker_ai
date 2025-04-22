package com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects

import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateEmbedding
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraTicketEmbeddingFakeBuilder

class CreateEmbeddingFakeBuilder {
    companion object {
        const val DEFAULT_JIRA_SPRINT_ID = JiraSprintFakeBuilder.DEFAULT_ID
        const val DEFAULT_RAW_JSON = JiraTicketEmbeddingFakeBuilder.DEFAULT_RAW_JSON
    }

    private var jiraSprintId = DEFAULT_JIRA_SPRINT_ID
    private var rawJson = DEFAULT_RAW_JSON

    fun build() = CreateEmbedding(jiraSprintId, rawJson)
}
