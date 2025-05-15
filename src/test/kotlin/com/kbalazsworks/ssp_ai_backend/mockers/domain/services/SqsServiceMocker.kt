package com.kbalazsworks.ssp_ai_backend.mockers.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services.SqsService
import io.mockk.every
import io.mockk.mockk
import software.amazon.awssdk.services.sqs.model.SendMessageResponse
import java.util.concurrent.CompletableFuture

class SqsServiceMocker {
    private val mock = mockk<SqsService>()

    fun withDefaultResponse() = apply {
        val response = SendMessageResponse.builder().messageId("1234").build()

        every { mock.sendMessage(any()) } returns CompletableFuture.completedFuture(response)
    }

    fun create() = mock
}
