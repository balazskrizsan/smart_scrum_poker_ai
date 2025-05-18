package com.kbalazsworks.ssp_ai_backend.fakers.domain.ai_module.value_objects

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.EmbeddingConfig
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.JiraHelper
import com.openai.models.embeddings.EmbeddingModel

class EmbeddingConfigFakeBuilder {
    companion object {
        val DEFAULT_EMBEDDING_MODEL = EmbeddingModel.TEXT_EMBEDDING_3_SMALL
        val DEFAULT_RAW_TEXT = JiraHelper.shortMockJiraIssueText
        val QUESTION_ID: Long? = QuestionFakeBuilder.DEFAULT_ID
        val JIRA_ISSUE_ID: Long? = null
    }

    private var embeddingModel: EmbeddingModel = DEFAULT_EMBEDDING_MODEL
    private var rawText = DEFAULT_RAW_TEXT
    private var questionId = QUESTION_ID
    private var jiraIssueId = JIRA_ISSUE_ID

    fun rawText(rawText: String) = apply { this.rawText = rawText }

    fun build() = EmbeddingConfig(embeddingModel, rawText, questionId, jiraIssueId)
}
