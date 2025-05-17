package com.kbalazsworks.ssp_ai_backend.domain.question_module

import com.kbalazsworks.ssp_ai_backend.domain.question_module.services.QuestionService
import org.springframework.stereotype.Service

@Service
class QuestionModuleFacade(private val questionService: QuestionService) {
    fun embedQuestion(questionId: Long) = questionService.embedQuestion(questionId)
}
