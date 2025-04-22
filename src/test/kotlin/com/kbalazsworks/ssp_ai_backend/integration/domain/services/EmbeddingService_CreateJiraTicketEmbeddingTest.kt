package com.kbalazsworks.ssp_ai_backend.integration.domain.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1JiraSprint
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraTicketEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.services.EmbeddingService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraTicketEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects.CreateEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.mockers.common.factories.LocalDateTimeFactoryMocker
import com.kbalazsworks.ssp_ai_backend.mockers.domain.services.OpenApiServiceMocker
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPresetExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(SqlPresetExtension::class)
class EmbeddingService_CreateJiraTicketEmbeddingTest : AbstractTest() {
    @Test
    @DisplayName("Valid embedding / Saved to db")
    @SqlPreset(presets = [Insert1JiraSprint::class])
    fun validEmbedding_savedToDb() {
        // Arrange
        val testedCreateEmbedding = CreateEmbeddingFakeBuilder().build()

        val exceptedEmbedding = JiraTicketEmbeddingFakeBuilder().id(1).build()

        val openApiServiceMock = OpenApiServiceMocker().mockCreateEmbedding().create()
        val localDateTimeFactoryMock = LocalDateTimeFactoryMocker().setDefaultValues().create()

        // Act
        val embeddingService = createInstance(
            EmbeddingService::class.java,
            listOf(openApiServiceMock, localDateTimeFactoryMock)
        )
        embeddingService.createEmbedding(testedCreateEmbedding)

        // Assert
        val actual = getDSLContext().selectFrom(jiraTicketEmbeddingsTable).fetchOneInto(JiraTicketEmbedding::class.java)

        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(exceptedEmbedding)
    }
}
