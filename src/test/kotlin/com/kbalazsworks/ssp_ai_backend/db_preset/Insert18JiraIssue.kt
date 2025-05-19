package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_ISSUES
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraIssueFakeBuilder
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
        val realJiraIssues = listOf(
            JiraIssueFakeBuilder().withRealJiraIssue101().build(),
            JiraIssueFakeBuilder().withRealJiraIssue102().build(),
            JiraIssueFakeBuilder().withRealJiraIssue103().build(),
            JiraIssueFakeBuilder().withRealJiraIssue104().build(),
            JiraIssueFakeBuilder().withRealJiraIssue105().build(),
            JiraIssueFakeBuilder().withRealJiraIssue106().build(),
            JiraIssueFakeBuilder().withRealJiraIssue107().build(),
            JiraIssueFakeBuilder().withRealJiraIssue108().build(),
            JiraIssueFakeBuilder().withRealJiraIssue109().build(),
            JiraIssueFakeBuilder().withRealJiraIssue110().build(),
            JiraIssueFakeBuilder().withRealJiraIssue111().build(),
            JiraIssueFakeBuilder().withRealJiraIssue112().build(),
            JiraIssueFakeBuilder().withRealJiraIssue113().build(),
            JiraIssueFakeBuilder().withRealJiraIssue114().build(),
            JiraIssueFakeBuilder().withRealJiraIssue115().build(),
            JiraIssueFakeBuilder().withRealJiraIssue116().build(),
            JiraIssueFakeBuilder().withRealJiraIssue117().build(),
            JiraIssueFakeBuilder().withRealJiraIssue118().build(),
        )

        for (issue in realJiraIssues) {
            jooqService.getDslContext()
                .insertInto(JIRA_ISSUES)
                .set(JIRA_ISSUES.ID, issue.id)
                .set(JIRA_ISSUES.JIRA_SPRINT_ID, issue.jiraSprintId)
                .set(JIRA_ISSUES.RAW_JSON, issue.rawJson)
                .set(JIRA_ISSUES.OPENAI_COMPATIBLE_TEXT, issue.openaiCompatibleText)
                .set(JIRA_ISSUES.CREATED_AT, issue.createdAt)
                .execute()
        }
    }
}
