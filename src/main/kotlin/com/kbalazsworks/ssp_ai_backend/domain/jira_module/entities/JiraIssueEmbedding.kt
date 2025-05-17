package com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities

import java.time.LocalDateTime

data class JiraIssueEmbedding(
    val id: Long?,
    val jiraSprintId: Long,
    val rawJson: String,
    val openaiCompatibleText: String,
    val createdAt: LocalDateTime,
)
