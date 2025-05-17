package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.strategies.sqs_received_message_embedder_strategies

import com.kbalazsworks.ssp_ai_backend.domain.question_module.QuestionModuleFacade
import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.value_objects.QueueRequest
import org.springframework.stereotype.Service

@Service
class QuestionEmbedderStrategy (private val questionModuleFacade: QuestionModuleFacade) {
    fun execute(parsed: QueueRequest<Long>) {
        questionModuleFacade.embedQuestion(parsed.data)
    }
}
