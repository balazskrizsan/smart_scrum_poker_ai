package com.kbalazsworks.ssp_ai_backend.integration.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert18VectorStore1536WithJiraIssue
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1VectorStore1536WithQuestion
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.JiraIssueEmbeddingService
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.value_objects.JiraIssueSimilarity
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraIssueEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JiraIssueEmbeddingService_SimilaritySearchTest : AbstractTest() {
    @Test
    @SqlPreset(presets = [Insert18VectorStore1536WithJiraIssue::class, Insert1VectorStore1536WithQuestion::class])
    fun similaritySearchQuestion() {
        // Arrange
        val testedAskSprint = AskAi(QuestionFakeBuilder.DEFAULT_ID, JiraSprintFakeBuilder.DEFAULT_ID)

        val expected = listOf(
            JiraIssueSimilarity(0.5433148f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue107().build()),
            JiraIssueSimilarity(0.6087894f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue111().build()),
            JiraIssueSimilarity(0.61891216f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue108().build()),
            JiraIssueSimilarity(0.66989034f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue116().build()),
            JiraIssueSimilarity(0.67223513f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue118().build()),
            JiraIssueSimilarity(0.6725439f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue101().build()),
            JiraIssueSimilarity(0.6741869f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue104().build()),
            JiraIssueSimilarity(0.67602473f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue113().build()),
            JiraIssueSimilarity(0.6897124f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue103().build()),
            JiraIssueSimilarity(0.6941108f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue117().build()),
        )

        // Act
        val actual = createInstance(JiraIssueEmbeddingService::class.java).similaritySearch(testedAskSprint)

        // Assert
        assertThat(actual).isEqualTo(expected)
    }
}
