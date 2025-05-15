package com.kbalazsworks.ssp_ai_backend.fakers.domain.entities

import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import com.kbalazsworks.ssp_ai_backend.helpers.JiraHelper
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.*
import java.time.LocalDateTime

class JiraIssueEmbeddingFakeBuilder {
    companion object {
        const val DEFAULT_ID = 100000L
        const val DEFAULT_ID101 = 100101L
        const val DEFAULT_ID102 = 100102L
        const val DEFAULT_ID103 = 100103L
        const val DEFAULT_ID104 = 100104L
        const val DEFAULT_ID105 = 100105L
        const val DEFAULT_ID106 = 100106L
        const val DEFAULT_ID107 = 100107L
        const val DEFAULT_ID108 = 100108L
        const val DEFAULT_ID109 = 100109L
        const val DEFAULT_ID110 = 100110L
        const val DEFAULT_ID111 = 100111L
        const val DEFAULT_ID112 = 100112L
        const val DEFAULT_ID113 = 100113L
        const val DEFAULT_ID114 = 100114L
        const val DEFAULT_ID115 = 100115L
        const val DEFAULT_ID116 = 100116L
        const val DEFAULT_ID117 = 100117L
        const val DEFAULT_ID118 = 100118L
        const val DEFAULT_JIRA_SPRINT_ID = JiraSprintFakeBuilder.DEFAULT_ID
        val DEFAULT_RAW_JSON = JiraHelper.shortMockJiraIssueJson
        val DEFAULT_OPENAI_COMPATIBLE_TEXT = JiraHelper.shortMockJiraIssueText
        val DEFAULT_CREATED_AT: LocalDateTime = DateTimeHelper.defaultLocalDateTime
    }

    private var id: Long? = DEFAULT_ID
    private var jiraSprintId = DEFAULT_JIRA_SPRINT_ID
    private var rawJson = DEFAULT_RAW_JSON
    private var openaiCompatibleText = DEFAULT_OPENAI_COMPATIBLE_TEXT
    private var createdAt: LocalDateTime = DEFAULT_CREATED_AT

    fun build() = JiraIssueEmbedding(
        id,
        jiraSprintId,
        rawJson,
        openaiCompatibleText,
        createdAt
    )

    fun id(id: Long?) = apply { this.id = id }
    fun withOpenaiCompatibleText(openaiCompatibleText: String) =
        apply { this.openaiCompatibleText = openaiCompatibleText }

    fun withRealEmbeddedJiraIssue101() = apply {
        id = 100101
        rawJson = issue101prettyJson
        openaiCompatibleText = issue101openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue102() = apply {
        id = 100102
        rawJson = issue102prettyJson
        openaiCompatibleText = issue102openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue103() = apply {
        id = 100103
        rawJson = issue103prettyJson
        openaiCompatibleText = issue103openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue104() = apply {
        id = 100104
        rawJson = issue104prettyJson
        openaiCompatibleText = issue104openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue105() = apply {
        id = 100105
        rawJson = issue105prettyJson
        openaiCompatibleText = issue105openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue106() = apply {
        id = 100106
        rawJson = issue106prettyJson
        openaiCompatibleText = issue106openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue107() = apply {
        id = 100107
        rawJson = issue107prettyJson
        openaiCompatibleText = issue107openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue108() = apply {
        id = 100108
        rawJson = issue108prettyJson
        openaiCompatibleText = issue108openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue109() = apply {
        id = 100109
        rawJson = issue109prettyJson
        openaiCompatibleText = issue109openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue110() = apply {
        id = 100110
        rawJson = issue110prettyJson
        openaiCompatibleText = issue110openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue111() = apply {
        id = 100111
        rawJson = issue111prettyJson
        openaiCompatibleText = issue111openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue112() = apply {
        id = 100112
        rawJson = issue112prettyJson
        openaiCompatibleText = issue112openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue113() = apply {
        id = 100113
        rawJson = issue113prettyJson
        openaiCompatibleText = issue113openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue114() = apply {
        id = 100114
        rawJson = issue114prettyJson
        openaiCompatibleText = issue114openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue115() = apply {
        id = 100115
        rawJson = issue115prettyJson
        openaiCompatibleText = issue115openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue116() = apply {
        id = 100116
        rawJson = issue116prettyJson
        openaiCompatibleText = issue116openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue117() = apply {
        id = 100117
        rawJson = issue117prettyJson
        openaiCompatibleText = issue117openAiCompatibleText
    }

    fun withRealEmbeddedJiraIssue118() = apply {
        id = 100118
        rawJson = issue118prettyJson
        openaiCompatibleText = issue118openAiCompatibleText
    }
}
