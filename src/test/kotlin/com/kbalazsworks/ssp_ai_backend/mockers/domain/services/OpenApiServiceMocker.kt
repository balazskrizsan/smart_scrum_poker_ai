package com.kbalazsworks.ssp_ai_backend.mockers.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.services.OpenApiService
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import io.mockk.every
import io.mockk.mockk

class OpenApiServiceMocker {
    private val mock = mockk<OpenApiService>()

    fun mockCreateEmbedding(): OpenApiServiceMocker {
        every { mock.createEmbedding(any(), any()) } returns EmbeddingHelper.cached1536VectorResponse

        return this
    }

    fun create() = mock
}
