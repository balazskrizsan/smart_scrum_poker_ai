package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.value_objects.JiraIssueSimilarity
import org.springframework.stereotype.Service

@Service
class AiPromptServiceService {
    // @todo: limit by chars
    fun getLimitedPrompt(jiraIssueSimilarities: List<JiraIssueSimilarity>) = jiraIssueSimilarities
        .subList(0, 3)
        .joinToString("\n\n") { i -> i.jiraIssueEmbedding.openaiCompatibleText }
}
