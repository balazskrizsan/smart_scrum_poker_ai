package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraTicketEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.repositories.JiraTicketEmbeddingRepository
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.EmbeddingConfig
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.openai.models.embeddings.EmbeddingModel
import com.pgvector.PGvector
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmbeddingService(
    private val jiraTicketEmbeddingRepository: JiraTicketEmbeddingRepository,
    private val openAiService: OpenAiService,
    private val localDateTimeFactory: LocalDateTimeFactory
) {
    companion object {
        private val logger = LoggerFactory.getLogger(EmbeddingService::class.java)
    }

    fun createEmbedding(createEmbedding: CreateEmbedding) {
        logger.info("Create embedding")

        val tempEmbeddingConfig = EmbeddingConfig(EmbeddingModel.TEXT_EMBEDDING_3_SMALL)
        val embeddingResult = openAiService.createEmbedding(tempEmbeddingConfig, createEmbedding)

        val jiraTicketEmbedding = jiraTicketEmbeddingRepository.save(
            JiraTicketEmbedding(
                null,
                createEmbedding.jiraSprintId,
                createEmbedding.data,
                mapEmbeddingResult(embeddingResult),
                null,
                localDateTimeFactory.create()
            )
        )
        logger.info("Embedding saved; id#{}", jiraTicketEmbedding.id)
    }

    private fun mapEmbeddingResult(embeddingResult: CreateEmbeddingResponse): PGvector =
        PGvector(embeddingResult.data().first().embedding().toDoubleArray().map { it.toFloat() })
}
