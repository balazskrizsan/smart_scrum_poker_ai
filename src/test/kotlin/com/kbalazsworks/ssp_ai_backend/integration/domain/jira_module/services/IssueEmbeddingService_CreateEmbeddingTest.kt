package com.kbalazsworks.ssp_ai_backend.integration.domain.jira_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_ISSUES
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1JiraSprint
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.IssueEmbeddingService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraIssueEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.value_objects.CreateJiraIssueEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.mockers.common.factories.LocalDateTimeFactoryMocker
import com.kbalazsworks.ssp_ai_backend.mockers.domain.ai_module.services.OpenAiFormatterServiceMocker
import com.kbalazsworks.ssp_ai_backend.mockers.domain.ai_module.services.OpenAiServiceMocker
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("ClassName")
class IssueEmbeddingService_CreateEmbeddingTest : AbstractTest() {
    @Test
    @SqlPreset(presets = [Insert1JiraSprint::class])
    fun validEmbedding_savedToDb() {
        // Arrange
        val tested = CreateJiraIssueEmbeddingFakeBuilder().build()

        val excepted = JiraIssueEmbeddingFakeBuilder().build()

        val openApiServiceMock = OpenAiServiceMocker().mockCreateEmbedding().create()
        val localDateTimeFactoryMock = LocalDateTimeFactoryMocker().setDefaultValues().create()
        val openAiFormatterServiceMock = OpenAiFormatterServiceMocker().mockJiraIssueToText().create()

        // Act
        val mocks = listOf(openApiServiceMock, localDateTimeFactoryMock, openAiFormatterServiceMock)
        createInstance(IssueEmbeddingService::class.java, mocks).createEmbedding(tested)

        // Assert
        val actual = getDSLContext().selectFrom(JIRA_ISSUES).fetchOneInto(JiraIssueEmbedding::class.java)

        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(excepted)
    }
}
