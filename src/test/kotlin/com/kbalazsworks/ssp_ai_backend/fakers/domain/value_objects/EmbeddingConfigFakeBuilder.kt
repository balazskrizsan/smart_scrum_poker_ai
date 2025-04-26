package com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects

import com.kbalazsworks.ssp_ai_backend.domain.value_objects.EmbeddingConfig
import com.kbalazsworks.ssp_ai_backend.helpers.JiraHelper
import com.openai.models.embeddings.EmbeddingModel

class EmbeddingConfigFakeBuilder {
    companion object {
        val DEFAULT_EMBEDDING_MODEL = EmbeddingModel.TEXT_EMBEDDING_3_SMALL
        val DEFAULT_RAW_TEXT = JiraHelper.shortMockJiraIssueText
    }

    private var embeddingModel: EmbeddingModel = DEFAULT_EMBEDDING_MODEL
    private var rawText = DEFAULT_RAW_TEXT

    fun build() = EmbeddingConfig(embeddingModel, rawText)
}
