package com.kbalazsworks.ssp_ai_backend.common.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ApplicationPropertiesService {
    companion object{
        const val SCHEDULERS_FAKE_TICKET_SCHEDULER_ENABLED = "schedulers.fake-ticket-scheduler.enabled"
        const val AWS_SQS_LISTENER_ENABLED = "aws.sqs.listener.enabled"
        const val AWS_SQS_QUEUE_NAME = "aws.sqs.queue-name"
        const val AWS_SQS_LISTENER_MAX_MESSAGES_PER_POLL = "aws.sqs.listener.max-messages-per-poll"
    }
    @Value("\${server.env}") lateinit var serverEnv: String

    @Value("\${openai.api-key}") lateinit var openaiApiKey: String
    @Value("\${openai.organization}") lateinit var openaiOrganization: String
    @Value("\${openai.project}") lateinit var openaiProject: String


    @Value("\${spring.datasource.url}") lateinit var springDatasourceUrl: String
    @Value("\${spring.datasource.username}") lateinit var springDatasourceUsername: String
    @Value("\${spring.datasource.password}") lateinit var springDatasourcePassword: String
    @Value("\${spring.datasource.driver-class-name}") lateinit var springDatasourceDriverClassName: String

    @Value("\${$SCHEDULERS_FAKE_TICKET_SCHEDULER_ENABLED}") lateinit var schedulersFakeTicketSchedulerEnabled: String

    @Value("\${$AWS_SQS_LISTENER_ENABLED}")  lateinit var awsSqsListenerEnabled: String
    @Value("\${aws.sqs.queue-url}") lateinit var awsSqsQueueUrl: String
    @Value("\${aws.sqs.queue-name}") lateinit var awsSqsQueueName: String
    @Value("\${aws.sqs.acces-key-id}") lateinit var awsSqsAccessKeyId: String
    @Value("\${aws.sqs.secret-access-key}") lateinit var awsSqsSecretAccessKey: String
    @Value("\${$AWS_SQS_LISTENER_MAX_MESSAGES_PER_POLL}") lateinit var awsSqsListenerMaxMessagesPerPoll: String
}
