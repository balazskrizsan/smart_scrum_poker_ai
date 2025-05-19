package com.kbalazsworks.ssp_ai_backend.api.listeners.sqs_listeners

import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService.Companion.AWS_SQS_LISTENER_ENABLED
import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services.EmbedderQueueHandlerService
import io.awspring.cloud.sqs.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = [AWS_SQS_LISTENER_ENABLED])
class SqsEmbedderListener(private val embedderQueueHandlerService: EmbedderQueueHandlerService) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @SqsListener(
        queueNames = ["\${${ApplicationPropertiesService.AWS_SQS_QUEUE_NAME}}"],
        maxMessagesPerPoll = "\${${ApplicationPropertiesService.AWS_SQS_LISTENER_MAX_MESSAGES_PER_POLL}}"
    )
    fun receiveMessage(message: String) {
        logger.info("SQS Message received: {}", message)

        embedderQueueHandlerService.handle(message)
    }
}
