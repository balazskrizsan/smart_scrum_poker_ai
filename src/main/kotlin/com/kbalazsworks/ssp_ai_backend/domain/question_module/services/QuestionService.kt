package com.kbalazsworks.ssp_ai_backend.domain.question_module.services

import com.kbalazsworks.ssp_ai_backend.common.extensions.onFailure
import com.kbalazsworks.ssp_ai_backend.common.extensions.onSuccess
import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.domain.common_module.constants.SqsMessages.Companion.QUESTION_QUEUE_REQUEST
import com.kbalazsworks.ssp_ai_backend.domain.question_module.entities.Question
import com.kbalazsworks.ssp_ai_backend.domain.question_module.repositories.QuestionRepository
import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services.SqsService
import com.kbalazsworks.ssp_ai_backend.domain.question_module.value_objects.CreateQuestionEmbedding
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.pgvector.PGvector
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val sqsService: SqsService,
    private val questionRepository: QuestionRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun createQuestion(createQuestionEmbedding: CreateQuestionEmbedding) {
        logger.info("Create question: {}", createQuestionEmbedding.question)

        val question = questionRepository.save(
            Question(null, createQuestionEmbedding.question, localDateTimeFactory.create())
        )

        sqsService.sendMessage(QUESTION_QUEUE_REQUEST.format(question.id))
            .onSuccess { logger.info("SQS message sent: {}", it) }
            .onFailure { logger.error("Error sending SQS message: {}", it.message, it) }
    }

    fun get(id: Long) = questionRepository._getOneById(id)

    private fun mapEmbeddingResult(embeddingResult: CreateEmbeddingResponse): PGvector =
        PGvector(embeddingResult.data().first().embedding().toDoubleArray().map { it.toFloat() })

    fun embedQuestion(questionId: Long) {

    }
}
