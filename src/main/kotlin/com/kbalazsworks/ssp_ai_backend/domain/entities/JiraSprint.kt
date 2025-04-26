package com.kbalazsworks.ssp_ai_backend.domain.entities

import com.kbalazsworks.ssp_ai_backend.db.tables.JiraSprints
import java.time.LocalDateTime

data class JiraSprint(val id: Long?, val jiraBoardId: Long, val name: String, val createdAt: LocalDateTime) {
    companion object {
        val DB_TABLE = JiraSprints.JIRA_SPRINTS
    }
}
