package com.kbalazsworks.ssp_ai_backend.integration.domain.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.domain.services.OpenAiFormatterService
import com.kbalazsworks.ssp_ai_backend.helpers.JiraHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OpenAiFormatterService_JiraIssueToText : AbstractTest() {
    @Test
    @DisplayName("Complex Jira Ticket JSON / Returns formatted text")
    fun complexJiraTicketJson_returnsFormattedText() {
        // Arrange
        val testedJson = JiraHelper.detailedJiraIssueJson

        val expected = JiraHelper.detailedJiraIssueText

        // Act
        val actual = createInstance(OpenAiFormatterService::class.java).jiraIssueToText(testedJson)

        // Assert
        assertThat(actual).isEqualTo(expected)
    }
}
