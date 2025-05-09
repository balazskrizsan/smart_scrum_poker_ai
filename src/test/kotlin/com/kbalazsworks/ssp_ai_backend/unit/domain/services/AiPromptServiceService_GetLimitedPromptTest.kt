package com.kbalazsworks.ssp_ai_backend.unit.domain.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.domain.exceptions.PromptException
import com.kbalazsworks.ssp_ai_backend.domain.services.AiPromptServiceService
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.JiraIssueSimilarity
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraIssueEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

@Suppress("ClassName")
class AiPromptServiceService_GetLimitedPromptTest : AbstractTest() {
    @Test
    fun sendingMultipleJiraIssues_calculatesCorrectly() {
        // Arrange
        val tested = listOf(
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue101().build()),
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue102().build()),
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue103().build()),
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue104().build()),
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue105().build()),
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue106().build()),
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue107().build()),
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue108().build()),
            JiraIssueSimilarity(0f, JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue109().build()),
        )

        val expected = issue101openAiCompatibleText + "\n\n" +
                issue102openAiCompatibleText + "\n\n" +
                issue103openAiCompatibleText + "\n\n" +
                issue104openAiCompatibleText + "\n\n" +
                issue105openAiCompatibleText + "\n\n" +
                issue106openAiCompatibleText

        // Act
        val actual = createInstance(AiPromptServiceService::class.java).getLimitedPrompt(tested);

        // Assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun sendingSingleJiraIssue_throwsLengthException() {
        // Arrange
        val tested = listOf(
            JiraIssueSimilarity(
                0f,
                JiraIssueEmbeddingFakeBuilder()
                    .withRealEmbeddedJiraIssue101()
                    .withOpenaiCompatibleText("qwe")
                    .build()
            )
        )

        val expectedExceptionMessage = "Prompt is too short"

        // Act - Assert
        assertThatThrownBy {
            createInstance(AiPromptServiceService::class.java).getLimitedPrompt(tested)
        }
            .isInstanceOf(PromptException::class.java)
            .hasMessageContaining(expectedExceptionMessage)
    }
}
