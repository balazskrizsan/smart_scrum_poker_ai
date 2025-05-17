package com.kbalazsworks.ssp_ai_backend.integration.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1JiraBoard
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraSprintRepository
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.SprintService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@Suppress("ClassName")
class SprintService_SaveTest : AbstractTest() {
    @Autowired
    private lateinit var jiraSprintRepository: JiraSprintRepository

    @Test
    @SqlPreset(presets = [Insert1JiraBoard::class])
    fun validJiraSprint_savesToDb() {
        // Arrange
        val tested = JiraSprintFakeBuilder().build()
        val expected = JiraSprintFakeBuilder().build()

        // Act
        createInstance(SprintService::class.java).save(tested)

        // Assert
        val actual = jiraSprintRepository._fetchOne()
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected)
    }
}
