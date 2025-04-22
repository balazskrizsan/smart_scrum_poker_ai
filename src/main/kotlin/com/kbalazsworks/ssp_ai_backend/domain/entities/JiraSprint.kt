package com.kbalazsworks.ssp_ai_backend.domain.entities

import java.time.LocalDateTime

data class JiraSprint (
    val id: Long?,
    val jiraBoardId: Long,
    val name: String,
    val createdAt: LocalDateTime,
)
