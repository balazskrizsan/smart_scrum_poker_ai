package com.kbalazsworks.ssp_ai_backend.integration.domain.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert18JiraIssue
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1Question
import com.kbalazsworks.ssp_ai_backend.domain.services.JiraIssueEmbeddingService
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.JiraIssueSimilarity
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraIssueEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JiraIssueEmbeddingService_AskAiTest : AbstractTest() {

    @Test
    @SqlPreset(presets = [Insert1Question::class, Insert18JiraIssue::class], truncateAfter = false)
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
