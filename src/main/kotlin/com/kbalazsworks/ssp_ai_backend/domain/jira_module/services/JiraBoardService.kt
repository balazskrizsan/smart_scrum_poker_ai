package com.kbalazsworks.ssp_ai_backend.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraBoard
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraBoardRepository
import org.springframework.stereotype.Service

@Service
class JiraBoardService(private val jiraBoardRepository: JiraBoardRepository) {
    fun save(jiraBoard: JiraBoard) = jiraBoardRepository._save(jiraBoard)
}
