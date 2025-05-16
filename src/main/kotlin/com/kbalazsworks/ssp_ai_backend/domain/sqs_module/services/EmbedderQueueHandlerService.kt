package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kbalazsworks.ssp_ai_backend.domain.common_module.constants.SqsMessages.Companion.JIRA_ISSUE_REQUEST_ID
import com.kbalazsworks.ssp_ai_backend.domain.common_module.constants.SqsMessages.Companion.QUESTION_REQUEST_ID
import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.strategies.sqs_message_receiver.JiraIssueEmbedderStrategy
import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.strategies.sqs_message_receiver.QuestionEmbedderStrategy
import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.value_objects.QueueRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmbedderQueueHandlerService(
    private val questionEmbedderStrategy: QuestionEmbedderStrategy,
    private val jiraIssueEmbedderStrategy: JiraIssueEmbedderStrategy
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val objectMapper: ObjectMapper = jacksonObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
        private val queueRequestLongType by lazy { object : TypeReference<QueueRequest<Long>>() {} }
    }

    fun handle(message: String) {
        val type = objectMapper.readTree(message).get("type")?.asText()

        logger.info("Embedding type: {}", message)

        when (type) {
            QUESTION_REQUEST_ID ->
                questionEmbedderStrategy.execute(objectMapper.readValue(message, queueRequestLongType))

            JIRA_ISSUE_REQUEST_ID ->
                jiraIssueEmbedderStrategy.execute(objectMapper.readValue(message, queueRequestLongType))

            else -> logger.error("Unknown strategy type: {}", type)
        }
    }
}
