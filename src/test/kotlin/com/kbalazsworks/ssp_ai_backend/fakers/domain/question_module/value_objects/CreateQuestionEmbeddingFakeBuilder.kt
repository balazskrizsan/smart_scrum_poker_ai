package com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.value_objects

import com.kbalazsworks.ssp_ai_backend.domain.question_module.value_objects.CreateQuestionEmbedding
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites.QuestionFakeBuilder

class CreateQuestionEmbeddingFakeBuilder {
    companion object {
        val DEFAULT_RAW_QUESTION = QuestionFakeBuilder.DEFAULT_QUESTION
    }

    private var rawQuestion = DEFAULT_RAW_QUESTION

    fun rawQuestion(rawQuestion: String) = apply { this.rawQuestion = rawQuestion }

    fun build() = CreateQuestionEmbedding(rawQuestion)
}
