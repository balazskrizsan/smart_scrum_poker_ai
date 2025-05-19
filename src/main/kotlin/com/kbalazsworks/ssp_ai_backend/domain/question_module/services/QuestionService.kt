package com.kbalazsworks.ssp_ai_backend.domain.question_module.services

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.AiModuleFacade
import com.kbalazsworks.ssp_ai_backend.domain.common_module.constants.SqsMessages.Companion.QUESTION_QUEUE_REQUEST
import com.kbalazsworks.ssp_ai_backend.domain.question_module.entities.Question
import com.kbalazsworks.ssp_ai_backend.domain.question_module.repositories.QuestionRepository
import com.kbalazsworks.ssp_ai_backend.domain.question_module.value_objects.CreateQuestionEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.SqsModuleFacade
import com.openai.models.embeddings.EmbeddingModel
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val aiModuleFacade: AiModuleFacade,
    private val sqsModuleFacade: SqsModuleFacade,
    private val questionRepository: QuestionRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun createQuestion(createQuestionEmbedding: CreateQuestionEmbedding) {
        logger.info("Create question: {}", createQuestionEmbedding.question)

        val question = questionRepository._save(
            Question(null, createQuestionEmbedding.question, localDateTimeFactory.create())
        )

        sqsModuleFacade.sendMessage(QUESTION_QUEUE_REQUEST.format(question.id))
    }

    fun get(id: Long) = questionRepository._getOneById(id)

    fun embedQuestion(questionId: Long) {
        val question = questionRepository._getOneById(questionId)

        aiModuleFacade.createAndSaveEmbedding(
            aiModuleFacade.getEmbeddingConfigFactory().create(
                EmbeddingModel.TEXT_EMBEDDING_3_SMALL, question.question, question.id, null
            )
        )
    }
}
