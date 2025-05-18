package com.kbalazsworks.ssp_ai_backend.domain.ai_module.factories

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.EmbeddingConfig
import com.openai.models.embeddings.EmbeddingModel

class EmbeddingConfigFactory {
    fun create(embeddingModel: EmbeddingModel, rawText: String, questionId: Long?, jiraIssueId: Long?) =
        EmbeddingConfig(embeddingModel, rawText, questionId, jiraIssueId)
}
