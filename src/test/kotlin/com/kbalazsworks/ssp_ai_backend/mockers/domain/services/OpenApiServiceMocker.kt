package com.kbalazsworks.ssp_ai_backend.mockers.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.services.OpenAiService
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.EmbeddingConfig
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects.CreateEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import com.kbalazsworks.ssp_ai_backend.helpers.JiraHelper
import com.openai.models.embeddings.EmbeddingModel
import io.mockk.every
import io.mockk.mockk

class OpenApiServiceMocker {
    private val mock = mockk<OpenAiService>()

    fun mockCreateEmbedding(): OpenApiServiceMocker {
        val expectedCalledEmbeddingConfig = EmbeddingConfig(EmbeddingModel.TEXT_EMBEDDING_3_SMALL)

        every {
            mock.createEmbedding(
                eq(expectedCalledEmbeddingConfig),
                CreateEmbeddingFakeBuilder().rawJson(JiraHelper.shortMockJiraIssueText).build()
            )
        } returns EmbeddingHelper.cached1536VectorResponse

        return this
    }

    fun create() = mock
}
