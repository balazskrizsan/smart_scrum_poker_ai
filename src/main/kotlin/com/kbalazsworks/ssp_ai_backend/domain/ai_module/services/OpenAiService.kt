package com.kbalazsworks.ssp_ai_backend.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.EmbeddingConfig
import com.openai.client.OpenAIClient
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.openai.models.embeddings.EmbeddingCreateParams
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OpenAiService(private val openAIClient: OpenAIClient) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun createEmbedding(embeddingConfig: EmbeddingConfig): CreateEmbeddingResponse {
        logger.info(
            "OpenAi called; mbeddingModel#{}",
            embeddingConfig.embeddingModel
        )

        val params = EmbeddingCreateParams.builder()
            .input(embeddingConfig.rawText)
            .model(embeddingConfig.embeddingModel)
            .build()

        return openAIClient.embeddings().create(params)
    }
}
