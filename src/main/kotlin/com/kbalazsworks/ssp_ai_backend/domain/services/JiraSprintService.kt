package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraSprint
import com.kbalazsworks.ssp_ai_backend.domain.repositories.JiraSprintRepository
import org.springframework.stereotype.Service

@Service
class JiraSprintService(private val jiraSprintRepository: JiraSprintRepository) {
    fun save(jiraSprint: JiraSprint) = jiraSprintRepository.ormSave(jiraSprint)
}
