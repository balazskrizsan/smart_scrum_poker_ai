package com.kbalazsworks.ssp_ai_backend.api.services

import com.kbalazsworks.ssp_ai_backend.api.requests.ask_ai.AskAiPostRequest
import com.kbalazsworks.ssp_ai_backend.api.requests.company.CompanyPostRequest
import com.kbalazsworks.ssp_ai_backend.api.requests.jira_board.JiraBoardPostRequest
import com.kbalazsworks.ssp_ai_backend.api.requests.jira_issue.JiraIssuePostRequest
import com.kbalazsworks.ssp_ai_backend.api.requests.jira_sprint.JiraSprintPostRequest
import com.kbalazsworks.ssp_ai_backend.api.requests.question.QuestionPostRequest
import com.kbalazsworks.ssp_ai_backend.common.value_objects.State
import com.kbalazsworks.ssp_ai_backend.domain.entities.Company
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraBoard
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraSprint
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateJiraIssueEmbedding
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateQuestionEmbedding

class RequestMapperService {
    companion object {
        fun map(request: JiraIssuePostRequest) = CreateJiraIssueEmbedding(request.jiraSprintId, request.jiraIssueJson)

        fun map(state: State, request: CompanyPostRequest) = Company(null, request.name, state.localDateTime)

        fun map(state: State, request: JiraBoardPostRequest) =
            JiraBoard(null, request.companyId, request.name, state.localDateTime)

        fun map(state: State, request: JiraSprintPostRequest) =
            JiraSprint(null, request.jiraBoardId, request.name, state.localDateTime)

        fun map(state: State, request: QuestionPostRequest) = CreateQuestionEmbedding(request.question)

        fun map(request: AskAiPostRequest) = AskAi(request.questionId, request.jiraSprintId)
    }
}
