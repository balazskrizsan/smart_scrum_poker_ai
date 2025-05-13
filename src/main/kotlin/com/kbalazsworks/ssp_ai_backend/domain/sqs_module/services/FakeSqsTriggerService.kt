package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services

import com.kbalazsworks.ssp_ai_backend.common.extensions.onFailure
import com.kbalazsworks.ssp_ai_backend.common.extensions.onSuccess
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FakeSqsTriggerService(private val sqsService: SqsService) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun sendMessage() {
        val payload = """
        {
          "message": "test message",
          "config": {
            "retryCount": 3,
            "priority": "HIGH",
            "notifyEmail": "example@something.com",
            "timestamp": "2025-05-12T10:15:00Z"
          }
        }
        """.trimIndent()

        sqsService.sendMessage(payload)
            .onSuccess { logger.info("SQS message sent: {}", it) }
            .onFailure { logger.error("Error sending SQS message: {}", it.message, it) }
    }
}
