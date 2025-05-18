package com.kbalazsworks.ssp_ai_backend.domain.ai_module.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.VECTOR_STORE_1536
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.entities.VectorStoreX
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.exceptions.VectorStoreXException
import com.kbalazsworks.ssp_ai_backend.domain.common_module.repositories.AbstractRepository
import org.springframework.stereotype.Repository

@Repository
class VectorStore1536Repository(private val jooqService: JooqService) : AbstractRepository(jooqService) {
    fun save(vectorStoreX: VectorStoreX) = jooqService.getDslContext()
        .insertInto(VECTOR_STORE_1536)
        .set(VECTOR_STORE_1536.QUESTION_ID, vectorStoreX.questionId)
        .set(VECTOR_STORE_1536.JIRA_ISSUE_ID, vectorStoreX.jiraIssueId)
        .set(VECTOR_STORE_1536.VECTOR_MODEL_ID, vectorStoreX.vectorModelId)
        .set(VECTOR_STORE_1536.EMBEDDING, toPgVectorField(vectorStoreX.embedding))
        .returning()
        .fetchOneInto(VectorStoreX::class.java)
        ?: throw VectorStoreXException("VectorStoreX creation failed.")

    fun findByPk(questionId: Long?, jiraIssueId: Long?, vectorModelId: Long) = jooqService.getDslContext()
        .selectFrom(VECTOR_STORE_1536)
        .where(
            VECTOR_STORE_1536.QUESTION_ID.eq(questionId)
                .and(VECTOR_STORE_1536.QUESTION_ID.eq(questionId))
                .and(VECTOR_STORE_1536.VECTOR_MODEL_ID.eq(vectorModelId))
        )
        .fetchOneInto(VectorStoreX::class.java)
        ?: throw VectorStoreXException("VectorStoreX not found.")
}
