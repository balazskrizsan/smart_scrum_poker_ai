package com.kbalazsworks.ssp_ai_backend.api.requests.company

import jakarta.validation.constraints.Size

data class CompanyPostRequest(
    @field:Size(min = 2, message = "Name must be at least 2 characters long")
    val name: String,
)
