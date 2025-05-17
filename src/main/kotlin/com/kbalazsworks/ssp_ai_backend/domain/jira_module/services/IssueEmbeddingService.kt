package com.kbalazsworks.ssp_ai_backend.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraIssueEmbeddingRepository
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.value_objects.CreateJiraIssueEmbedding
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.pgvector.PGvector
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class IssueEmbeddingService(
    private val jiraIssueEmbeddingRepository: JiraIssueEmbeddingRepository,
    private val issueToTextFormatterService: IssueToTextFormatterService,
    private val localDateTimeFactory: LocalDateTimeFactory
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun createEmbedding(createJiraIssueEmbedding: CreateJiraIssueEmbedding) {
        logger.info("Create embedding")

        val formatterService = issueToTextFormatterService.format(createJiraIssueEmbedding.issueJson)

        val jiraIssueEmbedding = jiraIssueEmbeddingRepository.save(
            JiraIssueEmbedding(
                null,
                createJiraIssueEmbedding.jiraSprintId,
                createJiraIssueEmbedding.issueJson,
                formatterService,
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
