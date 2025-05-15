package com.kbalazsworks.ssp_ai_backend.domain.entities

import java.time.LocalDateTime

data class Question(
    val id: Long?,
    val question: String,
    val createdAt: LocalDateTime,
)
