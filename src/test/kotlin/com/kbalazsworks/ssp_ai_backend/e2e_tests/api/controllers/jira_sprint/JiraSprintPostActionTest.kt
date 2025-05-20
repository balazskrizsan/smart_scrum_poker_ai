package com.kbalazsworks.ssp_ai_backend.e2e_tests.api.controllers.jira_sprint

import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1JiraBoard
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraSprintRepository
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraBoardFakeBuilder
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
class JiraSprintPostActionTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jiraSprintRepository: JiraSprintRepository

    @Test
    @SqlPreset(presets = [Insert1JiraBoard::class])
    fun creatingNewJiraSprint_returns200() {
        // Arrange
        val testedUrl = "/api/v1/jira-sprint"
        val testedRequest = """{"jiraBoardId": ${JiraBoardFakeBuilder.DEFAULT_ID}, "name": "tested board name"}"""

        val expectedStatus = status().isOk()
        val expectedData = """{"data":"","success":true,"errorCode":0,"requestId":"1"}"""
        val expectedJiraSprintName = "tested board name"
        val expectedJiraSprintJiraBoardId = JiraBoardFakeBuilder.DEFAULT_ID

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

        val actual = jiraSprintRepository._fetchOne()
        assertAll(
            { assertThat(actual.name).isEqualTo(expectedJiraSprintName) },
            { assertThat(actual.jiraBoardId).isEqualTo(expectedJiraSprintJiraBoardId) },
        )
    }

    @Test
    @SqlPreset
    fun creatingJiraSprintWithoutJiraBoard_returns400() {
        // Arrange
        val testedUrl = "/api/v1/jira-sprint"
        val testedRequest = """{"jiraBoardId": 123, "name": "tested jira name"}"""

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
        val testedUrl = "/api/v1/jira-sprint"
        val testedRequest = """{"companyId": -1, "name": ""}"""

        val expectedStatus = status().isBadRequest()
        val expectedData =
            """{"data":"jiraBoardId: 'jiraBoardId' must be positive, name: 'name' must be at least 2 characters long","success":false,"errorCode":2,"requestId":"1"}"""

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
        val testedUrl = "/api/v1/jira-sprint"
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
