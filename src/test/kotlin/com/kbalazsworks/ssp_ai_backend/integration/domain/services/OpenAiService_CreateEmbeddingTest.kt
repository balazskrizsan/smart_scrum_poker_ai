package com.kbalazsworks.ssp_ai_backend.integration.domain.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.domain.services.OpenAiService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects.CreateEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects.EmbeddingConfigFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import com.kbalazsworks.ssp_ai_backend.mockers.domain.configurations.OpenAIClientMocker
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OpenAiService_CreateEmbeddingTest : AbstractTest() {
    @Test
    @DisplayName("Valid config / Calls OpenAi API Mock")
    fun validConfig_callsOpenAiApiMock() {
        // Arrange
        val testedEmbeddingConfig = EmbeddingConfigFakeBuilder().build()
        val testedCreateEmbedding = CreateEmbeddingFakeBuilder().build()

        val expected = EmbeddingHelper.cached1536VectorResponse

        val openAIClientMock = OpenAIClientMocker().mockEmbeddingsCreate1536().create()

        // Act
        val openAiService = createInstance(OpenAiService::class.java, openAIClientMock)
        val actual = openAiService.createEmbedding(testedEmbeddingConfig, testedCreateEmbedding)

        // Assert
        assertThat(actual).isEqualTo(expected)
    }
}
