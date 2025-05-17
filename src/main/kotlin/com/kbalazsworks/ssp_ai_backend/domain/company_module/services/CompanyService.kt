package com.kbalazsworks.ssp_ai_backend.domain.company_module.services

import com.kbalazsworks.ssp_ai_backend.domain.company_module.entities.Company
import com.kbalazsworks.ssp_ai_backend.domain.company_module.repositories.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(private val companyRepository: CompanyRepository) {
    fun save(company: Company) = companyRepository._save(company)
}
