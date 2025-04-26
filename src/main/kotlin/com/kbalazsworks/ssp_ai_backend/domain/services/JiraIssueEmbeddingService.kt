package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.repositories.JiraIssueEmbeddingRepository
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateJiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.EmbeddingConfig
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.openai.models.embeddings.EmbeddingModel
import com.pgvector.PGvector
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class JiraIssueEmbeddingService(
    private val jiraIssueEmbeddingRepository: JiraIssueEmbeddingRepository,
    private val openAiService: OpenAiService,
    private val openAiFormatterService: OpenAiFormatterService,
    private val localDateTimeFactory: LocalDateTimeFactory
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun createEmbedding(createJiraIssueEmbedding: CreateJiraIssueEmbedding) {
        logger.info("Create embedding")

        val openAiFormattedData = openAiFormatterService.jiraIssueToText(createJiraIssueEmbedding.issueJson)
        val embeddingResult = openAiService.createEmbedding(
            EmbeddingConfig(EmbeddingModel.TEXT_EMBEDDING_3_SMALL, openAiFormattedData)
        )

        val jiraIssueEmbedding = jiraIssueEmbeddingRepository.save(
            JiraIssueEmbedding(
                null,
                createJiraIssueEmbedding.jiraSprintId,
                createJiraIssueEmbedding.issueJson,
                openAiFormattedData,
                mapEmbeddingResult(embeddingResult),
                null,
                localDateTimeFactory.create()
            )
        )
        logger.info("Embedding saved; id#{}", jiraIssueEmbedding.id)
    }

    private fun mapEmbeddingResult(embeddingResult: CreateEmbeddingResponse): PGvector =
        PGvector(embeddingResult.data().first().embedding().toDoubleArray().map { it.toFloat() })
}
