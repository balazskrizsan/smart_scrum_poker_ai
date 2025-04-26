package com.kbalazsworks.ssp_ai_backend.mockers.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.services.OpenAiService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects.EmbeddingConfigFakeBuilder
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import io.mockk.every
import io.mockk.mockk

class OpenApiServiceMocker {
    private val mock = mockk<OpenAiService>()

    fun mockCreateEmbedding(): OpenApiServiceMocker {
        val expectedCalledEmbeddingConfig = EmbeddingConfigFakeBuilder().build()

        every { mock.createEmbedding(eq(expectedCalledEmbeddingConfig)) } returns EmbeddingHelper.cached1536VectorResponse

        return this
    }

    fun create() = mock
}
