package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.Companies
import com.kbalazsworks.ssp_ai_backend.db.tables.Companies.Companion.COMPANIES
import com.kbalazsworks.ssp_ai_backend.db.tables.records.CompaniesRecord
import com.kbalazsworks.ssp_ai_backend.domain.entities.Company
import com.kbalazsworks.ssp_ai_backend.jooq_orm.value_objects.TableMeta
import org.springframework.stereotype.Repository

typealias CompanyGenerics = AbstractCrudRepository<Company, Companies, CompaniesRecord>

@Repository
class CompanyRepository(jooqService: JooqService) : CompanyGenerics(jooqService) {
    override val tableMeta = TableMeta(Company::class.java, COMPANIES)
}
