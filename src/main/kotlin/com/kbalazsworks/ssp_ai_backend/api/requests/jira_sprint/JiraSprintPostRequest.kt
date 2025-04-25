package com.kbalazsworks.ssp_ai_backend.api.requests.jira_sprint

import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class JiraSprintPostRequest(
    @field:Positive(message = "'jiraBoardId' must be positive")
    val jiraBoardId: Long,

    @field:Size(min = 2, message = "'name' must be at least 2 characters long")
    val name: String,
)
