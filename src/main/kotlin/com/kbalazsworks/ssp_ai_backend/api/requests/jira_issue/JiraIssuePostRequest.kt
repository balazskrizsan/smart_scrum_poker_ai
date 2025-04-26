package com.kbalazsworks.ssp_ai_backend.api.requests.jira_issue

import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class JiraIssuePostRequest (
    @field:Size(min = 20, message = "ticketJson must be at least 20 characters long")
    val jiraIssueJson: String,

    @field:Positive(message = "jiraSprintId must be positive")
    val jiraSprintId: Long,
)
