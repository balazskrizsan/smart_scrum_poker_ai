package com.kbalazsworks.ssp_ai_backend.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraSprint
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraSprintRepository
import org.springframework.stereotype.Service

@Service
class SprintService(private val jiraSprintRepository: JiraSprintRepository) {
    fun save(jiraSprint: JiraSprint) = jiraSprintRepository._save(jiraSprint)
}
