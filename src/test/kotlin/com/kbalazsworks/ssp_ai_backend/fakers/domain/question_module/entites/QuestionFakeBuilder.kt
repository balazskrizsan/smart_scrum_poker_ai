package com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites

import com.kbalazsworks.ssp_ai_backend.domain.question_module.entities.Question
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import com.kbalazsworks.ssp_ai_backend.helpers.EmbeddingHelper
import java.time.LocalDateTime

class QuestionFakeBuilder {
    companion object {
        const val DEFAULT_ID = 100400L
        const val DEFAULT_QUESTION = "Default question text"
        val DEFAULT_CREATED_AT: LocalDateTime = DateTimeHelper.defaultLocalDateTime
    }

    private var id: Long? = DEFAULT_ID
    private var question: String = DEFAULT_QUESTION
    private var createdAt: LocalDateTime = DEFAULT_CREATED_AT

    fun withRealEmbeddedQuestion1() = apply {
        question = EmbeddingHelper.question1text
    }

    fun build() = Question(id, question, createdAt)
}
