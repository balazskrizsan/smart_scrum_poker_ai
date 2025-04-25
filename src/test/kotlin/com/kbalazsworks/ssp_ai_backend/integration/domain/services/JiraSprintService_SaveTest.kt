package com.kbalazsworks.ssp_ai_backend.integration.domain.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1JiraBoard
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraSprint
import com.kbalazsworks.ssp_ai_backend.domain.repositories.JiraSprintRepository
import com.kbalazsworks.ssp_ai_backend.domain.services.JiraSprintService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class JiraSprintService_SaveTest : AbstractTest() {
    @Autowired
    private lateinit var jiraSprintRepository: JiraSprintRepository

    @Test
    @SqlPreset(presets = [Insert1JiraBoard::class])
    fun validJiraSprint_savesToDb() {
        // Arrange
        val tested = JiraSprintFakeBuilder().build()
        val expected = JiraSprintFakeBuilder().build()

        // Act
        createInstance(JiraSprintService::class.java).save(tested)

        // Assert
        val actual = jiraSprintRepository.ormFetchOne(JiraSprint::class.java)
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected)
    }
}
