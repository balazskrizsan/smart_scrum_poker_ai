package com.kbalazsworks.ssp_ai_backend.domain.common_module.constants

class SqsMessages {
    companion object {
        const val QUESTION_REQUEST_ID = "question_embedder"
        const val QUESTION_QUEUE_REQUEST = """{"type": "question_embedder", "data": %d}"""

        const val JIRA_ISSUE_REQUEST_ID = "jira_issue_embedder"
    }
}
