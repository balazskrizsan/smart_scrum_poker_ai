package com.kbalazsworks.ssp_ai_backend.domain.question_module.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.Questions
import com.kbalazsworks.ssp_ai_backend.db.tables.records.QuestionsRecord
import com.kbalazsworks.ssp_ai_backend.domain.common_module.repositories.AbstractCrudRepository
import com.kbalazsworks.ssp_ai_backend.domain.question_module.entities.Question
import com.kbalazsworks.ssp_ai_backend.jooq_orm.value_objects.TableMeta
import org.springframework.stereotype.Repository

typealias QuestionGenerics = AbstractCrudRepository<Question, Questions, QuestionsRecord>

@Repository
class QuestionRepository(private val jooqService: JooqService) : QuestionGenerics(jooqService) {
    override val tableMeta = TableMeta(Question::class.java, Questions.QUESTIONS)
}
