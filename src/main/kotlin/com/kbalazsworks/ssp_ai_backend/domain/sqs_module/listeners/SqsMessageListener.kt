package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.listeners

import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
import io.awspring.cloud.sqs.annotation.SqsListener
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SqsMessageListener {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @SqsListener(
        queueNames = ["\${${ApplicationPropertiesService.AWS_SQS_QUEUE_NAME}}"],
        maxMessagesPerPoll = "\${${ApplicationPropertiesService.AWS_SQS_LISTENER_MAX_MESSAGES_PER_POLL}}"
    )
    fun receiveMessage(message: Any?) {
        logger.info("SqsListener message received: {}", message)
    }
}
