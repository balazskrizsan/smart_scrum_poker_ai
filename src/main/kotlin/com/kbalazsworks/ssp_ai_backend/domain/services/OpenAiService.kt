package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.EmbeddingConfig
import com.openai.client.OpenAIClient
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.openai.models.embeddings.EmbeddingCreateParams
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OpenAiService(private val openAIClient: OpenAIClient) {
    companion object {
        private val logger = LoggerFactory.getLogger(EmbeddingService::class.java)
    }

    fun createEmbedding(embeddingConfig: EmbeddingConfig, createEmbedding: CreateEmbedding): CreateEmbeddingResponse {
        logger.info(
            "OpenAi called; jiraSprintId#{}; embeddingModel#{}",
            createEmbedding.jiraSprintId,
            embeddingConfig.embeddingModel
        )

        val params = EmbeddingCreateParams.builder()
            .input(createEmbedding.data)
            .model(embeddingConfig.embeddingModel)
            .build()

        return openAIClient.embeddings().create(params)
    }
}
