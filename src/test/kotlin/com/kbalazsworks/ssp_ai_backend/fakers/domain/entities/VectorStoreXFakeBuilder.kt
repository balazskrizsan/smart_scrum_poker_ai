package com.kbalazsworks.ssp_ai_backend.fakers.domain.entities

import com.kbalazsworks.ssp_ai_backend.domain.entities.VectorStoreX
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper.Companion.question1PGvector
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.*

class VectorStoreXFakeBuilder {
    companion object {
        val DEFAULT_QUESTION_ID: Long? = null
        val DEFAULT_JIRA_ISSUE_ID: Long? = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID
        val DEFAULT_VECTOR_MODEL_ID = VectorModelFakeBuilder.DEFAULT_ID
        val DEFAULT_EMBEDDING_1536 = EmbeddingHelper.cached1536AsVector
    }

    private var questionId = DEFAULT_QUESTION_ID
    private var jiraIssueId = DEFAULT_JIRA_ISSUE_ID
    private var vectorModelId = DEFAULT_VECTOR_MODEL_ID!!
    private var embedding = DEFAULT_EMBEDDING_1536

    fun build() = VectorStoreX(questionId, jiraIssueId, vectorModelId, embedding)

    fun withRealEmbeddedQuestion() = apply {
        embedding = question1PGvector
        questionId = QuestionFakeBuilder.DEFAULT_ID
        jiraIssueId = null
    }

    fun withRealEmbeddedJiraIssue101() = apply {
        embedding = issue101openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID101
    }

    fun withRealEmbeddedJiraIssue102() = apply {
        embedding = issue102openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID102
    }

    fun withRealEmbeddedJiraIssue103() = apply {
        embedding = issue103openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID103
    }

    fun withRealEmbeddedJiraIssue104() = apply {
        embedding = issue104openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID104
    }

    fun withRealEmbeddedJiraIssue105() = apply {
        embedding = issue105openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID105
    }

    fun withRealEmbeddedJiraIssue106() = apply {
        embedding = issue106openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID106
    }

    fun withRealEmbeddedJiraIssue107() = apply {
        embedding = issue107openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID107
    }

    fun withRealEmbeddedJiraIssue108() = apply {
        embedding = issue108openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID108
    }

    fun withRealEmbeddedJiraIssue109() = apply {
        embedding = issue109openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID109
    }

    fun withRealEmbeddedJiraIssue110() = apply {
        embedding = issue110openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID110
    }

    fun withRealEmbeddedJiraIssue111() = apply {
        embedding = issue111openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID111
    }

    fun withRealEmbeddedJiraIssue112() = apply {
        embedding = issue112openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID112
    }

    fun withRealEmbeddedJiraIssue113() = apply {
        embedding = issue113openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID113
    }

    fun withRealEmbeddedJiraIssue114() = apply {
        embedding = issue114openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID114
    }

    fun withRealEmbeddedJiraIssue115() = apply {
        embedding = issue115openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID115
    }

    fun withRealEmbeddedJiraIssue116() = apply {
        embedding = issue116openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID116
    }

    fun withRealEmbeddedJiraIssue117() = apply {
        embedding = issue117openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID117
    }

    fun withRealEmbeddedJiraIssue118() = apply {
        embedding = issue118openAiEmbeddedPgVector
        jiraIssueId = JiraIssueEmbeddingFakeBuilder.DEFAULT_ID118
    }
}
