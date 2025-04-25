package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraSprint
import org.springframework.stereotype.Repository

@Repository
class JiraSprintRepository(jooqService: JooqService) : AbstractCrudRepository<JiraSprint>(jooqService)
