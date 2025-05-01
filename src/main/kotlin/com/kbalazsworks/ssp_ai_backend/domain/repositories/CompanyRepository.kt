package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.domain.entities.Company
import org.springframework.stereotype.Repository

@Repository
class CompanyRepository(jooqService: JooqService) : AbstractCrudRepository<Company>(jooqService) {
    override fun getEntityClass() = Company::class.java
}
