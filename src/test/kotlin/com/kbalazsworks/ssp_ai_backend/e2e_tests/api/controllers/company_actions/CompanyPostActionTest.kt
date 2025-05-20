package com.kbalazsworks.ssp_ai_backend.e2e_tests.api.controllers.company_actions

import com.kbalazsworks.ssp_ai_backend.domain.company_module.repositories.CompanyRepository
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPresetExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SqlPresetExtension::class)
class CompanyPostActionTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @Test
    @SqlPreset
    fun creatingNewCompany_returns200() {
        // Arrange
        val testedUrl = "/api/v1/company"
        val testedRequest = """{"name": "tested company name"}"""

        val expectedStatus = status().isOk()
        val expectedData = """{"data":"","success":true,"errorCode":0,"requestId":"1"}"""
        val expectedCompanyName = "tested company name"

        // Act  - Assert
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(testedUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(testedRequest)
            )
            .andExpect(expectedStatus)
            .andExpect(content().string(expectedData))

        val actual = companyRepository._fetchOne()
        assertThat(actual.name).isEqualTo(expectedCompanyName)
    }

    @Test
    @SqlPreset(truncateAfter = false)
    fun creatingWrongCompany_returns400() {
        // Arrange
        val testedUrl = "/api/v1/company"
        val testedRequest = """{"name": ""}"""

        val expectedStatus = status().isBadRequest()
        val expectedData =
            """{"data":"name: Name must be at least 2 characters long","success":false,"errorCode":2,"requestId":"1"}"""

        // Act  - Assert
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post(testedUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(testedRequest)
            )
            .andExpect(expectedStatus)
            .andExpect(content().string(expectedData))
    }
}
