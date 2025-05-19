package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services

import com.kbalazsworks.ssp_ai_backend.common.extensions.onFailure
import com.kbalazsworks.ssp_ai_backend.common.extensions.onSuccess
import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import software.amazon.awssdk.services.sqs.model.SendMessageResponse
import java.util.concurrent.CompletableFuture

@Service
class SqsService(
    private val sqsAsyncClient: SqsAsyncClient,
    private val appProps: ApplicationPropertiesService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    // @todo: unit test
    fun sendMessage(payload: String): CompletableFuture<SendMessageResponse> = sqsAsyncClient
        .sendMessage(
            SendMessageRequest.builder()
                .queueUrl(appProps.awsSqsQueueUrl)
                .messageBody(payload)
                .build()
        )
        .onSuccess { logger.info("SQS message sent: {}", it) }
        .onFailure { logger.error("Error sending SQS message: {}", it.message, it) }
}
