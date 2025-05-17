package com.kbalazsworks.ssp_ai_backend.integration.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.services.OpenAiFormatterService
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue101openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue101prettyJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OpenAiFormatterService_JiraIssueToTextTest : AbstractTest() {
    @Test
    fun complexJiraTicketJson_returnsFormattedText() {
        // Arrange
        val tested = issue101prettyJson
        val expected = issue101openAiCompatibleText

        // Act
        val actual = createInstance(OpenAiFormatterService::class.java).jiraIssueToText(tested)

        // Assert
        assertThat(actual).isEqualTo(expected)
    }
}
