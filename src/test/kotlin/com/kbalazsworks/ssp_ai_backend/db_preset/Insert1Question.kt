package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.QUESTIONS
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.PresetRunnerService
import org.springframework.stereotype.Component

@Component
class Insert1Question(
    private val presetRunnerService: PresetRunnerService,
    private val jooqService: JooqService
) : IInsert {
    override fun runParent() {}

    override fun run() {
        val question = QuestionFakeBuilder().withRealEmbeddedQuestion1().build()
        jooqService.getDslContext().insertInto(QUESTIONS)
            .set(QUESTIONS.ID, question.id)
            .set(QUESTIONS.QUESTION, question.question)
            .set(QUESTIONS.CREATED_AT, question.createdAt)
            .execute()
    }
}

