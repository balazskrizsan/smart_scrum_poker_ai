package com.kbalazsworks.ssp_ai_backend.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraIssue
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraIssueRepository
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.value_objects.CreateJiraIssue
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.pgvector.PGvector
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class IssueEmbeddingService(
    private val jiraIssueRepository: JiraIssueRepository,
    private val issueToTextFormatterService: IssueToTextFormatterService,
    private val localDateTimeFactory: LocalDateTimeFactory
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun createEmbedding(createJiraIssue: CreateJiraIssue) {
        logger.info("Create embedding")

        val formatterService = issueToTextFormatterService.format(createJiraIssue.issueJson)

        val jiraIssue = jiraIssueRepository.save(
            JiraIssue(
                null,
                createJiraIssue.jiraSprintId,
                createJiraIssue.issueJson,
                formatterService,
                localDateTimeFactory.create()
            )
        )
        logger.info("Embedding saved; id#{}", jiraIssue.id)
        // @todo: add to queue
    }

    private fun mapEmbeddingResult(embeddingResult: CreateEmbeddingResponse): PGvector =
        PGvector(embeddingResult.data().first().embedding().toDoubleArray().map { it.toFloat() })

    fun similaritySearch(askAi: AskAi) = jiraIssueRepository.similaritySearch(askAi)
}
