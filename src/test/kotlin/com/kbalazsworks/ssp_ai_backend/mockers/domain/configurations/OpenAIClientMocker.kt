package com.kbalazsworks.ssp_ai_backend.mockers.domain.configurations

import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import com.openai.client.OpenAIClient
import com.openai.services.blocking.EmbeddingService
import io.mockk.every
import io.mockk.mockk

class OpenAIClientMocker {
    private val mock = mockk<OpenAIClient>()
    private val embeddingServiceMock = mockk<EmbeddingService>()

    private fun mockEmbeddingServiceCreate() {
        every { embeddingServiceMock.create(any()) } returns EmbeddingHelper.cached1536VectorResponse
    }

    fun mockEmbeddingsCreate1536(): OpenAIClientMocker {
        mockEmbeddingServiceCreate()

        every { mock.embeddings() } returns embeddingServiceMock

        return this
    }

    fun create() = mock
}
