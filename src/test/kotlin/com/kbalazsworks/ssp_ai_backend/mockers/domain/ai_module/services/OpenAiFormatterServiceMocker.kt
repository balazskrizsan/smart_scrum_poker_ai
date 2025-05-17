package com.kbalazsworks.ssp_ai_backend.mockers.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.IssueToTextFormatterService
import com.kbalazsworks.ssp_ai_backend.helpers.JiraHelper
import io.mockk.every
import io.mockk.mockk

class OpenAiFormatterServiceMocker {
    private val mock = mockk<IssueToTextFormatterService>()

    fun mockJiraIssueToText(): OpenAiFormatterServiceMocker {
        every { mock.format(JiraHelper.shortMockJiraIssueJson) } returns JiraHelper.shortMockJiraIssueText

        return this
    }

    fun create() = mock
}
