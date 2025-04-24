package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraBoard
import org.springframework.stereotype.Repository

@Repository
class JiraBoardRepository(jooqService: JooqService) : AbstractCrudRepository<JiraBoard>(jooqService)
