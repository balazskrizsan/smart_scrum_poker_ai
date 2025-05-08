package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.Questions
import com.kbalazsworks.ssp_ai_backend.db.tables.Questions.Companion.QUESTIONS
import com.kbalazsworks.ssp_ai_backend.db.tables.records.QuestionsRecord
import com.kbalazsworks.ssp_ai_backend.domain.entities.Question
import com.kbalazsworks.ssp_ai_backend.domain.exceptions.QuestionEmbeddingException
import com.kbalazsworks.ssp_ai_backend.jooq_orm.value_objects.TableMeta
import org.springframework.stereotype.Repository

typealias QuestionGenerics = AbstractCrudRepository<Question, Questions, QuestionsRecord>

@Repository
class QuestionRepository(private val jooqService: JooqService) : QuestionGenerics(jooqService) {
    override val tableMeta = TableMeta(Question::class.java, QUESTIONS)

    fun save(question: Question) = jooqService.getDslContext()
        .insertInto(QUESTIONS)
        .set(QUESTIONS.QUESTION, question.question)
        .set(QUESTIONS.EMBEDDING1536, toPgVectorField(question.embedding1536))
        .set(QUESTIONS.EMBEDDING3072, toPgVectorField(question.embedding3072))
        .set(QUESTIONS.CREATED_AT, question.createdAt)
        .returning()
        .fetchOneInto(Question::class.java)
        ?: throw QuestionEmbeddingException("Question creation failed.")
}
