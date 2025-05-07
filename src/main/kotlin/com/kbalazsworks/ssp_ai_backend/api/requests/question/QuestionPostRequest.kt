package com.kbalazsworks.ssp_ai_backend.api.requests.question

import jakarta.validation.constraints.Size

data class QuestionPostRequest(
    @field:Size(min = 2, message = "'question' must be at least 2 characters long")
    val question: String,
)
