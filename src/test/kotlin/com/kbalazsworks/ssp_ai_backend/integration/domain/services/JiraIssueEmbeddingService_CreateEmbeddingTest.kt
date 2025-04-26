package com.kbalazsworks.ssp_ai_backend.integration.domain.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1JiraSprint
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.services.JiraIssueEmbeddingService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraIssueEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects.CreateJiraIssueEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.mockers.common.factories.LocalDateTimeFactoryMocker
import com.kbalazsworks.ssp_ai_backend.mockers.domain.services.OpenAiFormatterServiceMocker
import com.kbalazsworks.ssp_ai_backend.mockers.domain.services.OpenApiServiceMocker
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JiraIssueEmbeddingService_CreateEmbeddingTest : AbstractTest() {
    @Test
    @SqlPreset(presets = [Insert1JiraSprint::class])
    fun validEmbedding_savedToDb() {
        // Arrange
        val tested = CreateJiraIssueEmbeddingFakeBuilder().build()

        val excepted = JiraIssueEmbeddingFakeBuilder().build()

        val openApiServiceMock = OpenApiServiceMocker().mockCreateEmbedding().create()
        val localDateTimeFactoryMock = LocalDateTimeFactoryMocker().setDefaultValues().create()
        val openAiFormatterServiceMock = OpenAiFormatterServiceMocker().mockJiraIssueToText().create()

        // Act
        val mocks = listOf(openApiServiceMock, localDateTimeFactoryMock, openAiFormatterServiceMock)
        createInstance(JiraIssueEmbeddingService::class.java, mocks).createEmbedding(tested)

        // Assert
        val actual = getDSLContext().selectFrom(jiraIssueEmbeddingsTable).fetchOneInto(JiraIssueEmbedding::class.java)

        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(excepted)
    }
}
