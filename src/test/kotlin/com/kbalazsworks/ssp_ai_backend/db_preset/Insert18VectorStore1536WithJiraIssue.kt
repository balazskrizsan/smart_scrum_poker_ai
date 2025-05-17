package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.VECTOR_STORE_1536
import com.kbalazsworks.ssp_ai_backend.fakers.domain.ai_module.entities.VectorStoreXFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.PresetRunnerService
import org.springframework.stereotype.Component

@Component
class Insert18VectorStore1536WithJiraIssue(
    private val presetRunnerService: PresetRunnerService,
    private val jooqService: JooqService
) : IInsert {
    override fun runParent()  {
        presetRunnerService.run(Insert1VectorModel::class.java)
        presetRunnerService.run(Insert18JiraIssue::class.java)
    }

    override fun run() {
        val vectorStoreXList = listOf(
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue101().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue102().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue103().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue104().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue105().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue106().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue107().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue108().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue109().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue110().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue111().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue112().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue113().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue114().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue115().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue116().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue117().build(),
            VectorStoreXFakeBuilder().withRealEmbeddedJiraIssue118().build(),
        )

        for (vectorStoreX in vectorStoreXList) {
            jooqService.getDslContext()
                .insertInto(VECTOR_STORE_1536)
                .set(VECTOR_STORE_1536.QUESTION_ID, vectorStoreX.questionId)
                .set(VECTOR_STORE_1536.JIRA_ISSUE_ID, vectorStoreX.jiraIssueId)
                .set(VECTOR_STORE_1536.VECTOR_MODEL_ID, vectorStoreX.vectorModelId)
                .set(VECTOR_STORE_1536.EMBEDDING, toPgVectorField(vectorStoreX.embedding))
                .execute()
        }
    }
}
