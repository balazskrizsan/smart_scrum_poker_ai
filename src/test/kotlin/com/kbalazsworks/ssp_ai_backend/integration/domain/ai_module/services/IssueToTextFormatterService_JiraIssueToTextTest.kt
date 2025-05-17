package com.kbalazsworks.ssp_ai_backend.integration.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.IssueToTextFormatterService
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue101openAiCompatibleText
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.issue101prettyJson
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("ClassName")
class IssueToTextFormatterService_formatTest : AbstractTest() {
    @Test
    fun complexJiraTicketJson_returnsFormattedText() {
        // Arrange
        val tested = issue101prettyJson
        val expected = issue101openAiCompatibleText

        // Act
        val actual = createInstance(IssueToTextFormatterService::class.java).format(tested)

        // Assert
        assertThat(actual).isEqualTo(expected)
    }
}
