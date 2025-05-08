package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraBoard
import com.kbalazsworks.ssp_ai_backend.domain.repositories.JiraBoardRepository
import org.springframework.stereotype.Service

@Service
class JiraBoardService(private val jiraBoardRepository: JiraBoardRepository) {
    fun save(jiraBoard: JiraBoard) = jiraBoardRepository._save(jiraBoard)
}
