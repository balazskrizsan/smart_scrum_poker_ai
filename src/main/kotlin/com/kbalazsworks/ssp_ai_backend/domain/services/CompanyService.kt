package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.entities.Company
import com.kbalazsworks.ssp_ai_backend.domain.repositories.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(private val companyRepository: CompanyRepository) {
    fun save(company: Company) = companyRepository._save(company)
}
