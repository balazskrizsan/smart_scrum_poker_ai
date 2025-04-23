package com.kbalazsworks.ssp_ai_backend.domain.entities

import com.kbalazsworks.ssp_ai_backend.db.tables.JiraBoards
import java.time.LocalDateTime

data class JiraBoard(
    val id: Long?,
    val companyId: Long,
    val name: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        val TABLE_NAME = JiraBoards.JIRA_BOARDS
    }
}

