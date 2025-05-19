package com.kbalazsworks.ssp_ai_backend.integration.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert18VectorStore1536WithJiraIssue
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1VectorStore1536WithQuestion
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.IssueEmbeddingService
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.value_objects.JiraIssueSimilarity
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraIssueFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("ClassName")
class IssueEmbeddingService_SimilaritySearchTest : AbstractTest() {
    @Test
    @SqlPreset(presets = [Insert18VectorStore1536WithJiraIssue::class, Insert1VectorStore1536WithQuestion::class])
    fun similaritySearchQuestion() {
        // Arrange
        val testedAskSprint = AskAi(QuestionFakeBuilder.DEFAULT_ID, JiraSprintFakeBuilder.DEFAULT_ID)

        val expected = listOf(
            JiraIssueSimilarity(0.5433148f, JiraIssueFakeBuilder().withRealJiraIssue107().build()),
            JiraIssueSimilarity(0.6087894f, JiraIssueFakeBuilder().withRealJiraIssue111().build()),
            JiraIssueSimilarity(0.61891216f, JiraIssueFakeBuilder().withRealJiraIssue108().build()),
            JiraIssueSimilarity(0.66989034f, JiraIssueFakeBuilder().withRealJiraIssue116().build()),
            JiraIssueSimilarity(0.67223513f, JiraIssueFakeBuilder().withRealJiraIssue118().build()),
            JiraIssueSimilarity(0.6725439f, JiraIssueFakeBuilder().withRealJiraIssue101().build()),
            JiraIssueSimilarity(0.6741869f, JiraIssueFakeBuilder().withRealJiraIssue104().build()),
            JiraIssueSimilarity(0.67602473f, JiraIssueFakeBuilder().withRealJiraIssue113().build()),
            JiraIssueSimilarity(0.6897124f, JiraIssueFakeBuilder().withRealJiraIssue103().build()),
            JiraIssueSimilarity(0.6941108f, JiraIssueFakeBuilder().withRealJiraIssue117().build()),
        )

        // Act
        val actual = createInstance(IssueEmbeddingService::class.java).similaritySearch(testedAskSprint)

        // Assert
        assertThat(actual).isEqualTo(expected)
    }
}
