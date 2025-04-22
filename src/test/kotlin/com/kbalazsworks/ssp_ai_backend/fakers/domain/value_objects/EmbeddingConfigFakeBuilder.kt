package com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects

import com.kbalazsworks.ssp_ai_backend.domain.value_objects.EmbeddingConfig
import com.openai.models.embeddings.EmbeddingModel

class EmbeddingConfigFakeBuilder {
    companion object {
        val DEFAULT_EMBEDDING_MODEL = EmbeddingModel.TEXT_EMBEDDING_3_SMALL
    }

    private var embeddingModel: EmbeddingModel = DEFAULT_EMBEDDING_MODEL

    fun build() = EmbeddingConfig(embeddingModel)
}
