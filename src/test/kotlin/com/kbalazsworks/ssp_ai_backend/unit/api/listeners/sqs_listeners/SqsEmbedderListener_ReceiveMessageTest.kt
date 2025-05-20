package com.kbalazsworks.ssp_ai_backend.unit.api.listeners.sqs_listeners

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.api.listeners.sqs.SqsEmbedderListener
import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
import com.kbalazsworks.ssp_ai_backend.mockers.domain.sqs_module.EmbedderQueueHandlerServiceMocker
import io.awspring.cloud.sqs.annotation.SqsListener
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@Suppress("ClassName")
class SqsEmbedderListener_ReceiveMessageTest : AbstractTest() {
    @Test
    fun callingQueueHandler_called() {
        // Arrange
        val testedMessage = "test message";
        val embedderQueueHandlerServiceMock = EmbedderQueueHandlerServiceMocker()
            .validateReceiveMessageCalledWith("test message")
            .create()

        // Act - In arrange
        createInstance(SqsEmbedderListener::class.java, embedderQueueHandlerServiceMock).receiveMessage(testedMessage)
    }

    @Test
    fun validateSqsListenerRequiredAnnotation_exists() {
        // Arrange
        val expectedQueueNames = listOf("\${${ApplicationPropertiesService.AWS_SQS_QUEUE_NAME}}")
        val expectedMaxMessagesPerPoll = "\${${ApplicationPropertiesService.AWS_SQS_LISTENER_MAX_MESSAGES_PER_POLL}}"

        // Act
        val method = SqsEmbedderListener::class.java.getDeclaredMethod("receiveMessage", String::class.java)
        val annotation = method.getAnnotation(SqsListener::class.java)

        val actualQueueNames = annotation.queueNames.toList()
        val actualMaxMessagesPerPoll = annotation.maxMessagesPerPoll

        // Assert
        assertAll(
            { assertThat(expectedQueueNames).isEqualTo(actualQueueNames) },
            { assertThat(expectedMaxMessagesPerPoll).isEqualTo(actualMaxMessagesPerPoll) },
        )
    }
}
