package com.kbalazsworks.ssp_ai_backend.mockers.domain.sqs_module

import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.SqsModuleFacade
import io.mockk.every
import io.mockk.mockk
import software.amazon.awssdk.services.sqs.model.SendMessageResponse
import java.util.concurrent.CompletableFuture

class SqsModuleFacadeMocker {
    private val mock = mockk<SqsModuleFacade>()

    fun withDefaultResponse() = apply {
        val response = SendMessageResponse.builder().messageId("1234").build()

        every { mock.sendMessage(any()) } returns CompletableFuture.completedFuture(response)
    }

    fun create() = mock
}
