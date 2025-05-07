package com.kbalazsworks.ssp_ai_backend.helpers

class JiraHelper {
    companion object {
        val shortMockJiraIssueJson = """
        {
          "id": "10001",
          "key": "PROJ-123"
        }
        """
        val shortMockJiraIssueText = """
        Ticket: PROJ-123
        Ticket-ID: 10001
        """
    }
}
