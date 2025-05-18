package com.kbalazsworks.ssp_ai_backend.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.entities.VectorStoreX
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.EmbeddingConfig
import com.openai.client.OpenAIClient
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.openai.models.embeddings.EmbeddingCreateParams
import com.pgvector.PGvector
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OpenAiService(
    private val openAIClient: OpenAIClient,
    private val vectorStoreService: VectorStoreService,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun createAndSaveEmbedding(embeddingConfig: EmbeddingConfig) {
        logger.info("OpenAi called; embeddingModel#{}", embeddingConfig.embeddingModel)

        val params = EmbeddingCreateParams.builder()
            .input(embeddingConfig.rawText)
            .model(embeddingConfig.embeddingModel)
            .build()

        val embeddingResult = openAIClient.embeddings().create(params)

        vectorStoreService.save(VectorStoreX(
            embeddingConfig.questionId,
            embeddingConfig.jiraIssueId,
            1L,
            convertEmbeddingResultToPgVector(embeddingResult),
        ))
    }

    fun convertEmbeddingResultToPgVector(embeddingResult: CreateEmbeddingResponse): PGvector =
        PGvector(embeddingResult.data().first().embedding().toDoubleArray().map { it.toFloat() })
}
