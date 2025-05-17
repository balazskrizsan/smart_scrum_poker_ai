package com.kbalazsworks.ssp_ai_backend.mockers.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.services.OpenAiService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.ai_module.value_objects.EmbeddingConfigFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import io.mockk.every
import io.mockk.mockk

class OpenAiServiceMocker {
    private val mock = mockk<OpenAiService>()

    fun mockCreateEmbedding(): OpenAiServiceMocker {
        val expectedCalledEmbeddingConfig = EmbeddingConfigFakeBuilder().build()

        every { mock.createEmbedding(eq(expectedCalledEmbeddingConfig)) } returns EmbeddingHelper.cached1536VectorResponse

        return this
    }

    fun mockCreateQuestionEmbedding(): OpenAiServiceMocker {
        val expectedCalledEmbeddingConfig = EmbeddingConfigFakeBuilder()
            .rawText(QuestionFakeBuilder.DEFAULT_QUESTION)
            .build()

        every { mock.createEmbedding(eq(expectedCalledEmbeddingConfig)) } returns EmbeddingHelper.cached1536VectorResponse

        return this
    }

    fun create() = mock
}
