package com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites

import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraBoard
import com.kbalazsworks.ssp_ai_backend.fakers.domain.company_module.entities.CompanyFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import java.time.LocalDateTime

class JiraBoardFakeBuilder {
    companion object {
        const val DEFAULT_ID = 100200L
        const val DEFAULT_COMPANY_ID = CompanyFakeBuilder.DEFAULT_ID
        const val DEFAULT_NAME = "Default name for jira board"
        val DEFAULT_CREATED_AT: LocalDateTime = DateTimeHelper.defaultLocalDateTime
    }

    private var id = DEFAULT_ID
    private var companyId = DEFAULT_COMPANY_ID
    private var name = DEFAULT_NAME
    private var createdAt = DEFAULT_CREATED_AT

    fun build() = JiraBoard(id, companyId, name, createdAt)
}