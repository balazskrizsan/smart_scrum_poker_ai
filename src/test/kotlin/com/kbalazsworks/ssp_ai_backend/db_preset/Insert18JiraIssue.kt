package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_TICKET_EMBEDDINGS
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraIssueEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.PresetRunnerService
import org.springframework.stereotype.Component

@Component
class Insert18JiraIssue(
    private val presetRunnerService: PresetRunnerService,
    private val jooqService: JooqService
) : IInsert {
    override fun runParent() = presetRunnerService.run(Insert1JiraSprint::class.java)

    override fun run() {
        val realEmbeddedJiraIssues = listOf(
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue101().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue102().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue103().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue104().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue105().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue106().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue107().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue108().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue109().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue110().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue111().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue112().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue113().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue114().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue115().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue116().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue117().build(),
            JiraIssueEmbeddingFakeBuilder().withRealEmbeddedJiraIssue118().build(),
        )

        for (jiraIssueEmbedding in realEmbeddedJiraIssues) {
            jooqService.getDslContext()
                .insertInto(JIRA_TICKET_EMBEDDINGS)
                .set(JIRA_TICKET_EMBEDDINGS.ID, jiraIssueEmbedding.id)
                .set(JIRA_TICKET_EMBEDDINGS.JIRA_SPRINT_ID, jiraIssueEmbedding.jiraSprintId)
                .set(JIRA_TICKET_EMBEDDINGS.RAW_JSON, jiraIssueEmbedding.rawJson)
                .set(JIRA_TICKET_EMBEDDINGS.OPENAI_COMPATIBLE_TEXT, jiraIssueEmbedding.openaiCompatibleText)
                .set(JIRA_TICKET_EMBEDDINGS.EMBEDDING1536, toPgVectorField(jiraIssueEmbedding.embedding1536))
                .set(JIRA_TICKET_EMBEDDINGS.CREATED_AT, jiraIssueEmbedding.createdAt)
                .execute()
        }
    }
}

