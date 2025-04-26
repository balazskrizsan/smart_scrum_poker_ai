package com.kbalazsworks.ssp_ai_backend.fakers.domain.entities

import com.kbalazsworks.ssp_ai_backend.domain.entities.Question
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import com.pgvector.PGvector
import java.time.LocalDateTime

class QuestionFakeBuilder {
    companion object {
        const val DEFAULT_ID = 100400L
        const val DEFAULT_QUESTION = "Default question text"
        val DEFAULT_EMBEDDING_1536: PGvector? = EmbeddingHelper.cached1536AsVector
        val DEFAULT_EMBEDDING_3072: PGvector? = null
        val DEFAULT_CREATED_AT: LocalDateTime = DateTimeHelper.defaultLocalDateTime
    }

    private var id: Long? = DEFAULT_ID
    private var question: String = DEFAULT_QUESTION
    private var embedding1536: PGvector? = DEFAULT_EMBEDDING_1536
    private var embedding3072: PGvector? = DEFAULT_EMBEDDING_3072
    private var createdAt: LocalDateTime = DEFAULT_CREATED_AT

    fun withId(id: Long?) = apply { this.id = id }
    fun withQuestion(question: String) = apply { this.question = question }
    fun withEmbedding1536(embedding: PGvector?) = apply { this.embedding1536 = embedding }
    fun withEmbedding3072(embedding: PGvector?) = apply { this.embedding3072 = embedding }
    fun withCreatedAt(createdAt: LocalDateTime) = apply { this.createdAt = createdAt }

    fun build() = Question(id, question, embedding1536, embedding3072, createdAt)
}
