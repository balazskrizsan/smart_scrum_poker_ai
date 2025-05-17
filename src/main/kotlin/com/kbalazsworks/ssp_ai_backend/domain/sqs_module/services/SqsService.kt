package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services

import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
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
    // @todo: unit test
    fun sendMessage(payload: String): CompletableFuture<SendMessageResponse> = sqsAsyncClient.sendMessage(
        SendMessageRequest.builder()
            .queueUrl(appProps.awsSqsQueueUrl)
            .messageBody(payload)
            .build()
    )
}
