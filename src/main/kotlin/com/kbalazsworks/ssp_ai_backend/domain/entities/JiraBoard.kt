package com.kbalazsworks.ssp_ai_backend.domain.entities

import java.time.LocalDateTime

data class JiraBoard (
    val id: Long?,
    val companyId: Long,
    val name: String,
    val createdAt: LocalDateTime,
)
