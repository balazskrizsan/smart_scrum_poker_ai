package com.kbalazsworks.ssp_ai_backend.integration.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1Question
import com.kbalazsworks.ssp_ai_backend.db_preset.Insert1VectorModel
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.repositories.VectorStore1536Repository
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.services.OpenAiService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.ai_module.entities.VectorModelFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.ai_module.value_objects.EmbeddingConfigFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import com.kbalazsworks.ssp_ai_backend.mockers.domain.ai_module.beans.OpenAIClientMocker
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class OpenAiService_CreateAndSaveEmbeddingTest : AbstractTest() {
    @Autowired
    lateinit var vectorStore1536Repository: VectorStore1536Repository

    @Test
    @SqlPreset(presets = [Insert1VectorModel::class, Insert1Question::class])
    fun validConfig_callsOpenAiApiMock() {
        // Arrange
        val testedEmbeddingConfig = EmbeddingConfigFakeBuilder().build()
        val expected = EmbeddingHelper.cached1536AsVector
        val openAIClientMock = OpenAIClientMocker().mockEmbeddingsCreate1536().create()

        // Act
        createInstance(OpenAiService::class.java, openAIClientMock).createAndSaveEmbedding(testedEmbeddingConfig)

        // Assert
        val actual = vectorStore1536Repository.findByPk(
            testedEmbeddingConfig.questionId,
            testedEmbeddingConfig.jiraIssueId,
            VectorModelFakeBuilder.DEFAULT_ID!!
        )

        assertThat(actual.embedding).isEqualTo(expected)
    }
}
