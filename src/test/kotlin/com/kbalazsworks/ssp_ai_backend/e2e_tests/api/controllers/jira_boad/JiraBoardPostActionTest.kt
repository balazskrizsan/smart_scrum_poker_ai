package com.kbalazsworks.ssp_ai_backend.e2e_tests.api.controllers.jira_boad

import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1Company
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraBoardRepository
import com.kbalazsworks.ssp_ai_backend.fakers.domain.company_module.entities.CompanyFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPresetExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
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
class JiraBoardPostActionTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jiraBordRepository: JiraBoardRepository

    @Test
    @SqlPreset(presets = [Insert1Company::class])
    fun creatingNewCompany_returns200() {
        // Arrange
        val testedUrl = "/api/v1/jira-board"
        val testedRequest = """{"companyId": ${CompanyFakeBuilder.DEFAULT_ID}, "name": "tested board name"}"""

        val expectedStatus = status().isOk()
        val expectedData = """{"data":"","success":true,"errorCode":0,"requestId":"1"}"""
        val expectedJiraBoardName = "tested board name"
        val expectedJiraBoardCompanyId = CompanyFakeBuilder.DEFAULT_ID

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

        val actual = jiraBordRepository._fetchOne()
        assertAll(
            { assertThat(actual.name).isEqualTo(expectedJiraBoardName) },
            { assertThat(actual.companyId).isEqualTo(expectedJiraBoardCompanyId) },
        )
    }

    @Test
    @SqlPreset
    fun creatingJiraBoardWithoutCompany_returns400() {
        // Arrange
        val testedUrl = "/api/v1/jira-board"
        val testedRequest = """{"companyId": 123, "name": "tested board name"}"""

        val expectedStatus = status().isBadRequest()
        val expectedData = """{"data":"Error occurred","success":false,"errorCode":4,"requestId":"1"}"""

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

    @Test
    @SqlPreset
    fun creatingWrongJiraBoard_returns400() {
        // Arrange
        val testedUrl = "/api/v1/jira-board"
        val testedRequest = """{"companyId": -1, "name": ""}"""

        val expectedStatus = status().isBadRequest()
        val expectedData = """{"data":"name: 'name' must be at least 2 characters long, companyId: 'companyId' must be positive","success":false,"errorCode":2,"requestId":"1"}"""

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

    @Test
    @SqlPreset
    fun creatingJiraBoardWithInvalidJson_returns400() {
        // Arrange
        val testedUrl = "/api/v1/jira-board"
        val testedRequest = """{"}"""

        val expectedStatus = status().isBadRequest()
        val expectedData = """{"data":"Invalid JSON format","success":false,"errorCode":3,"requestId":"1"}"""

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
