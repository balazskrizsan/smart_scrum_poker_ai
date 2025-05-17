package com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.value_objects

import com.kbalazsworks.ssp_ai_backend.domain.jira_module.value_objects.CreateJiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraIssueEmbeddingFakeBuilder

class CreateJiraIssueEmbeddingFakeBuilder {
    companion object {
        const val DEFAULT_JIRA_SPRINT_ID = JiraSprintFakeBuilder.DEFAULT_ID
        val DEFAULT_RAW_JSON = JiraIssueEmbeddingFakeBuilder.DEFAULT_RAW_JSON
    }

    private var jiraSprintId = DEFAULT_JIRA_SPRINT_ID
    private var rawJson = DEFAULT_RAW_JSON

    fun rawJson(rawJson: String) = apply { this.rawJson = rawJson }

    fun build() = CreateJiraIssueEmbedding(jiraSprintId, rawJson)
}
