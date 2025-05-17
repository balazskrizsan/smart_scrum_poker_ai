package com.kbalazsworks.ssp_ai_backend.integration.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1Company
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories.JiraBoardRepository
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.BoardService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraBoardFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@Suppress("ClassName")
class BoardService_SaveTest : AbstractTest() {
    @Autowired
    private lateinit var jiraBoardRepository: JiraBoardRepository

    @Test
    @SqlPreset(presets = [Insert1Company::class])
    fun validJiraBoard_savesToDb() {
        // Arrange
        val tested = JiraBoardFakeBuilder().build()
        val expected = JiraBoardFakeBuilder().build()

        // Act
        createInstance(BoardService::class.java).save(tested)

        // Assert
        val actual = jiraBoardRepository._fetchOne()
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected)
    }
}
