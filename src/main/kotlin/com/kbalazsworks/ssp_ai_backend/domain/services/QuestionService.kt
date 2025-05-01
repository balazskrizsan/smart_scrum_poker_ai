package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.domain.entities.Question
import com.kbalazsworks.ssp_ai_backend.domain.repositories.QuestionRepository
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateQuestionEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.EmbeddingConfig
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.openai.models.embeddings.EmbeddingModel
import com.pgvector.PGvector
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class QuestionService(
    private val openAiService: OpenAiService,
    private val questionRepository: QuestionRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun createQuestionEmbedding(createQuestionEmbedding: CreateQuestionEmbedding) {
        logger.info("Create question embedding")

        val config = EmbeddingConfig(EmbeddingModel.TEXT_EMBEDDING_3_SMALL, createQuestionEmbedding.question)

        val result = openAiService.createEmbedding(config)

        questionRepository.save(Question(
            null,
            createQuestionEmbedding.question,
            mapEmbeddingResult(result),
            null,
            localDateTimeFactory.create()
        ))
    }

    fun get(id: Long) = questionRepository._getOneById(id)

    private fun mapEmbeddingResult(embeddingResult: CreateEmbeddingResponse): PGvector =
        PGvector(embeddingResult.data().first().embedding().toDoubleArray().map { it.toFloat() })
}
