package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraTicketEmbeddings
import org.springframework.stereotype.Repository

@Repository
abstract class AbstractRepository(private val jooqService: JooqService) {
    val jiraTicketEmbeddingTable = JiraTicketEmbeddings.JIRA_TICKET_EMBEDDINGS
}
