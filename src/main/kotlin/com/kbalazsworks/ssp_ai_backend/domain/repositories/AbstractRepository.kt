package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraTicketEmbeddings
import org.springframework.beans.factory.annotation.Autowired


abstract class AbstractRepository {
    val jiraTicketEmbeddingTable = JiraTicketEmbeddings.JIRA_TICKET_EMBEDDINGS

    private var jooqService: JooqService? = null

    @Autowired
    fun setJooqService(jooqService: JooqService) {
        this.jooqService = jooqService
    }

    protected fun getDSLContext() = jooqService?.getDslContext()!!
}
