package com.kbalazsworks.ssp_ai_backend.api.requests.jira_board

import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class JiraBoardPostRequest(
    @field:Positive(message = "'companyId' must be positive")
    val companyId: Long,

    @field:Size(min = 2, message = "'name' must be at least 2 characters long")
    val name: String,
)
