package com.kbalazsworks.ssp_ai_backend.mockers.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.services.OpenAiService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.ai_module.value_objects.EmbeddingConfigFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk

class OpenAiServiceMocker {
    private val mock = mockk<OpenAiService>()

    fun mockCreateEmbedding(): OpenAiServiceMocker {
        val expectedCalledEmbeddingConfig = EmbeddingConfigFakeBuilder().build()

        every { mock.createAndSaveEmbedding(eq(expectedCalledEmbeddingConfig)) } just Runs

        return this
    }

    fun mockCreateQuestionEmbedding(): OpenAiServiceMocker {
        val expectedCalledEmbeddingConfig = EmbeddingConfigFakeBuilder()
            .rawText(QuestionFakeBuilder.DEFAULT_QUESTION)
            .build()

        every { mock.createAndSaveEmbedding(eq(expectedCalledEmbeddingConfig)) } just Runs

        return this
    }

    fun create() = mock
}
