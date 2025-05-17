package com.kbalazsworks.ssp_ai_backend.mockers.domain.sqs_module

import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services.EmbedderQueueHandlerService
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk

class EmbedderQueueHandlerServiceMocker {
    private val mock = mockk<EmbedderQueueHandlerService>()

    fun validateReceiveMessageCalledWith(expectedMessage: String) = apply {
        every { mock.handle(eq(expectedMessage)) } just Runs
    }

    fun create() = mock
}
