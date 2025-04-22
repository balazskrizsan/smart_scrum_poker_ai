package com.kbalazsworks.ssp_ai_backend.fakers.domain.entities

import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraSprint
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import java.time.LocalDateTime

class JiraSprintFakeBuilder {
    companion object {
        const val DEFAULT_ID = 100100L
        const val DEFAULT_JIRA_BOARD_ID = JiraBoardFakeBuilder.DEFAULT_ID
        const val DEFAULT_NAME = "Default Sprint"
        val DEFAULT_CREATED_AT: LocalDateTime = DateTimeHelper.defaultLocalDateTime
    }

    private var id: Long? = DEFAULT_ID
    private var jiraBoardId: Long = DEFAULT_JIRA_BOARD_ID
    private var name: String = DEFAULT_NAME
    private var createdAt: LocalDateTime = DEFAULT_CREATED_AT

    fun id(id: Long?) = apply { this.id = id }

    fun build() = JiraSprint(id, jiraBoardId, name, createdAt)
}
