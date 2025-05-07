package com.kbalazsworks.ssp_ai_backend.fakers.domain.entities

import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import com.kbalazsworks.ssp_ai_backend.helpers.JiraHelper
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue101openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue101openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue101prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue102openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue102openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue102prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue103openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue103openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue103prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue104openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue104openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue104prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue105openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue105openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue105prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue106openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue106openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue106prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue107openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue107openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue107prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue108openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue108openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue108prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue109openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue109openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue109prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue110openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue110openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue110prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue111openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue111openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue111prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue112openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue112openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue112prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue113openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue113openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue113prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue114openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue114openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue114prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue115openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue115openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue115prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue116openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue116openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue116prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue117openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue117openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue117prettyJson
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue118openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue118openAiEmbeddedPgVector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue118prettyJson
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

    fun build() = JiraIssueEmbedding(
        id,
        jiraSprintId,
        rawJson,
        openaiCompatibleText,
        embedding1536,
        embedding3072,
        createdAt
    )

    fun id(id: Long?) = apply { this.id = id }

    fun withRealEmbeddedJiraIssue101() = apply {
        id = 100101
        rawJson = issue101prettyJson
        openaiCompatibleText = issue101openAiCompatibleText
        embedding1536 = issue101openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue102() = apply {
        id = 100102
        rawJson = issue102prettyJson
        openaiCompatibleText = issue102openAiCompatibleText
        embedding1536 = issue102openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue103() = apply {
        id = 100103
        rawJson = issue103prettyJson
        openaiCompatibleText = issue103openAiCompatibleText
        embedding1536 = issue103openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue104() = apply {
        id = 100104
        rawJson = issue104prettyJson
        openaiCompatibleText = issue104openAiCompatibleText
        embedding1536 = issue104openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue105() = apply {
        id = 100105
        rawJson = issue105prettyJson
        openaiCompatibleText = issue105openAiCompatibleText
        embedding1536 = issue105openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue106() = apply {
        id = 100106
        rawJson = issue106prettyJson
        openaiCompatibleText = issue106openAiCompatibleText
        embedding1536 = issue106openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue107() = apply {
        id = 100107
        rawJson = issue107prettyJson
        openaiCompatibleText = issue107openAiCompatibleText
        embedding1536 = issue107openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue108() = apply {
        id = 100108
        rawJson = issue108prettyJson
        openaiCompatibleText = issue108openAiCompatibleText
        embedding1536 = issue108openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue109() = apply {
        id = 100109
        rawJson = issue109prettyJson
        openaiCompatibleText = issue109openAiCompatibleText
        embedding1536 = issue109openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue110() = apply {
        id = 100110
        rawJson = issue110prettyJson
        openaiCompatibleText = issue110openAiCompatibleText
        embedding1536 = issue110openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue111() = apply {
        id = 100111
        rawJson = issue111prettyJson
        openaiCompatibleText = issue111openAiCompatibleText
        embedding1536 = issue111openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue112() = apply {
        id = 100112
        rawJson = issue112prettyJson
        openaiCompatibleText = issue112openAiCompatibleText
        embedding1536 = issue112openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue113() = apply {
        id = 100113
        rawJson = issue113prettyJson
        openaiCompatibleText = issue113openAiCompatibleText
        embedding1536 = issue113openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue114() = apply {
        id = 100114
        rawJson = issue114prettyJson
        openaiCompatibleText = issue114openAiCompatibleText
        embedding1536 = issue114openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue115() = apply {
        id = 100115
        rawJson = issue115prettyJson
        openaiCompatibleText = issue115openAiCompatibleText
        embedding1536 = issue115openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue116() = apply {
        id = 100116
        rawJson = issue116prettyJson
        openaiCompatibleText = issue116openAiCompatibleText
        embedding1536 = issue116openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue117() = apply {
        id = 100117
        rawJson = issue117prettyJson
        openaiCompatibleText = issue117openAiCompatibleText
        embedding1536 = issue117openAiEmbeddedPgVector
    }

    fun withRealEmbeddedJiraIssue118() = apply {
        id = 100118
        rawJson = issue118prettyJson
        openaiCompatibleText = issue118openAiCompatibleText
        embedding1536 = issue118openAiEmbeddedPgVector
    }
}
