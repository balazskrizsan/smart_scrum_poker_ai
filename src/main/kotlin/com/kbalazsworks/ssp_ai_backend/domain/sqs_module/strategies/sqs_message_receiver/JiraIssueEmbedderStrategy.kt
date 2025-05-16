package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.strategies.sqs_message_receiver

import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.value_objects.QueueRequest
import org.springframework.stereotype.Service

@Service
class JiraIssueEmbedderStrategy {
    fun execute(parsed: QueueRequest<Long>) {

    }
}
