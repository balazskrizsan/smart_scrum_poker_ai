package com.kbalazsworks.ssp_ai_backend.domain.entities

import com.kbalazsworks.ssp_ai_backend.db.tables.JiraTicketEmbeddings
import com.pgvector.PGvector
import java.time.LocalDateTime

data class JiraIssueEmbedding(
    val id: Long?,
    val jiraSprintId: Long,
    val rawJson: String,
    val openaiCompatibleText: String,
    val embedding1536: PGvector?,
    val embedding3072: PGvector?,
    val createdAt: LocalDateTime,
) {
    companion object {
        val DB_TABLE = JiraTicketEmbeddings.JIRA_TICKET_EMBEDDINGS
    }
}

