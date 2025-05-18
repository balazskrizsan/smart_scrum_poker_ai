package com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects

import com.openai.models.embeddings.EmbeddingModel

data class EmbeddingConfig(
    val embeddingModel: EmbeddingModel,
    val rawText: String,
    val questionId: Long?,
    val jiraIssueId: Long?,
)
