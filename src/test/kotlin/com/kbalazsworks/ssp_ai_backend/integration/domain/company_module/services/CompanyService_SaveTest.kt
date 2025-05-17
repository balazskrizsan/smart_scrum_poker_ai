package com.kbalazsworks.ssp_ai_backend.integration.domain.company_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.domain.company_module.repositories.CompanyRepository
import com.kbalazsworks.ssp_ai_backend.domain.company_module.services.CompanyService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.company_module.entities.CompanyFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CompanyService_SaveTest : AbstractTest() {
    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @Test
    @SqlPreset
    fun validCompany_savesToDb() {
        // Arrange
        val tested = CompanyFakeBuilder().build()
        val expected = CompanyFakeBuilder().build()

        // Act
        createInstance(CompanyService::class.java).save(tested)

        // Assert
        val actual = companyRepository._fetchOne()
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected)
    }
}
