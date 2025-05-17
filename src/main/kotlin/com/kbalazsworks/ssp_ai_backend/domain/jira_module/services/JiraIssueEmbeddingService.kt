package com.kbalazsworks.ssp_ai_backend.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraIssueEmbeddingRepository
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.services.OpenAiFormatterService
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.services.OpenAiService
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.value_objects.CreateJiraIssueEmbedding
import com.openai.models.embeddings.CreateEmbeddingResponse
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

        val jiraIssueEmbedding = jiraIssueEmbeddingRepository.save(
            JiraIssueEmbedding(
                null,
                createJiraIssueEmbedding.jiraSprintId,
                createJiraIssueEmbedding.issueJson,
                openAiFormattedData,
                localDateTimeFactory.create()
            )
        )
        logger.info("Embedding saved; id#{}", jiraIssueEmbedding.id)
        // @todo: add to queue
    }

    private fun mapEmbeddingResult(embeddingResult: CreateEmbeddingResponse): PGvector =
        PGvector(embeddingResult.data().first().embedding().toDoubleArray().map { it.toFloat() })

    fun similaritySearch(askAi: AskAi) = jiraIssueEmbeddingRepository.similaritySearch(askAi)
}
