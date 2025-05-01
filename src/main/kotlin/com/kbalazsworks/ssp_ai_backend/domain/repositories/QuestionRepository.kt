package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.QUESTIONS
import com.kbalazsworks.ssp_ai_backend.domain.entities.Question
import com.kbalazsworks.ssp_ai_backend.domain.exceptions.QuestionEmbeddingException
import org.springframework.stereotype.Repository

@Repository
class QuestionRepository(private val jooqService: JooqService) : AbstractCrudRepository<Question>(jooqService) {
    override fun getEntityClass() = Question::class.java

    companion object {
        private val table = Question.DB_TABLE;
    }

    fun save(question: Question): Question {
        val entity: Question? = jooqService.getDslContext().insertInto(table)
            .set(QUESTIONS.QUESTION, question.question)
            .set(QUESTIONS.EMBEDDING1536, toPgVectorField(question.embedding1536))
            .set(QUESTIONS.EMBEDDING3072, toPgVectorField(question.embedding3072))
            .set(QUESTIONS.CREATED_AT, question.createdAt)
            .returning()
            .fetchOneInto(Question::class.java)

        return entity ?: throw QuestionEmbeddingException("JiraTicketEmbedding creation failed.")
    }
}
