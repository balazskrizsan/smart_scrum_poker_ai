package com.kbalazsworks.ssp_ai_backend.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.exceptions.PromptException
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.value_objects.JiraIssueSimilarity
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.PromptConfig
import org.springframework.stereotype.Service

@Service
class AiPromptServiceService {
    companion object {
        const val TEXT_TO_TOKEN_RATIO = 4 // This is a rough estimation(~0.75 words per token for English text)
        const val TICKET_SEPARATOR = "\n\n"
        const val MIN_TOKEN_LIMIT = 100
        val promptConfig = PromptConfig(4096)
    }

    fun getLimitedPrompt(jiraIssueSimilarities: List<JiraIssueSimilarity>) = buildString {
        for ((index, similarity) in jiraIssueSimilarities.withIndex()) {
            val text = similarity.jiraIssueEmbedding.openaiCompatibleText
            if (getEstimatedTokenNumber(this.toString() + text) > promptConfig.tokenLimit) {
                break
            }
            append(text)
            if (index < jiraIssueSimilarities.lastIndex) {
                append(TICKET_SEPARATOR)
            }

            this.toString()
        }
    }
        .removeSuffix(TICKET_SEPARATOR)
        .also { if (getEstimatedTokenNumber(it) < MIN_TOKEN_LIMIT) throw PromptException("Prompt is too short") }

    private fun getEstimatedTokenNumber(text: String) = text.length / TEXT_TO_TOKEN_RATIO
}
