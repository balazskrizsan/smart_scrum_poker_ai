package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.VECTOR_STORE_1536
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.VectorStoreXFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.PresetRunnerService
import org.springframework.stereotype.Component

@Component
class Insert1VectorStore1536WithQuestion(
    private val presetRunnerService: PresetRunnerService,
    private val jooqService: JooqService
) : IInsert {
    override fun runParent() = presetRunnerService.run(Insert1Question::class.java)

    override fun run() {
        val vectorStoreX = VectorStoreXFakeBuilder().withRealEmbeddedQuestion().build()

        jooqService.getDslContext()
            .insertInto(VECTOR_STORE_1536)
            .set(VECTOR_STORE_1536.QUESTION_ID, vectorStoreX.questionId)
            .set(VECTOR_STORE_1536.JIRA_ISSUE_ID, vectorStoreX.jiraIssueId)
            .set(VECTOR_STORE_1536.VECTOR_MODEL_ID, vectorStoreX.vectorModelId)
            .set(VECTOR_STORE_1536.EMBEDDING, toPgVectorField(vectorStoreX.embedding))
            .execute()
    }
}
