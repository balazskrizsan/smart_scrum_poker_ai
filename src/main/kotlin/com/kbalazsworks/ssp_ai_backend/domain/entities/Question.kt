package com.kbalazsworks.ssp_ai_backend.domain.entities

import com.kbalazsworks.ssp_ai_backend.db.tables.Questions
import com.pgvector.PGvector
import java.time.LocalDateTime

data class Question(
    val id: Long?,
    val question: String,
    val embedding1536: PGvector?,
    val embedding3072: PGvector?,
    val createdAt: LocalDateTime,
) {
    companion object {
        val TABLE_NAME = Questions.QUESTIONS
    }
}
