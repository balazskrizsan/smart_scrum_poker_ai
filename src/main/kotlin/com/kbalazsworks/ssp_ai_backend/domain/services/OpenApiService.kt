package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.EmbeddingConfig
import com.openai.client.okhttp.OpenAIOkHttpClient
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.openai.models.embeddings.EmbeddingCreateParams
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OpenApiService (private val appProps: ApplicationPropertiesService) {
    companion object {
        private val logger = LoggerFactory.getLogger(EmbeddingService::class.java)
    }

    fun createEmbedding(embeddingConfig: EmbeddingConfig, createEmbedding: CreateEmbedding): CreateEmbeddingResponse {
        val client = OpenAIOkHttpClient.builder()
            .apiKey(appProps.openaiApiKey)
            .organization(appProps.openaiOrganization)
            .project(appProps.openaiProject)
            .build()

        val params = EmbeddingCreateParams.builder()
            .input(createEmbedding.data)
            .model(embeddingConfig.embeddingModel)
            .build()

        return client.embeddings().create(params)
    }
}
