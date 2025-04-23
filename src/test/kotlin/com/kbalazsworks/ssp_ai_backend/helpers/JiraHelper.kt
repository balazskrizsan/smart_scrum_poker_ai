package com.kbalazsworks.ssp_ai_backend.helpers

class JiraHelper {
    companion object {
        val detailedJiraIssueJson = """
        {
          "id": "10001",
          "key": "PROJ-123",
          "fields": {
            "summary": "API integráció fejlesztése",
            "description": "Külső rendszerhez való kapcsolódás implementálása.",
            "status": {
              "name": "Done"
            },
            "issuetype": {
              "name": "Story"
            },
            "assignee": {
              "displayName": "Kovács Béla"
            },
            "reporter": {
              "displayName": "Fejlesztő Fanni"
            },
            "created": "2025-04-15T08:00:00.000+0000",
            "updated": "2025-04-22T14:30:00.000+0000",
            "priority": {
              "name": "Medium"
            }
          },
          "changelog": {
            "histories": [
              {
                "created": "2025-04-15T09:00:00.000+0000",
                "items": [
                  {
                    "field": "status",
                    "fromString": null,
                    "toString": "To Do"
                  }
                ]
              },
              {
                "created": "2025-04-16T10:00:00.000+0000",
                "items": [
                  {
                    "field": "status",
                    "fromString": "To Do",
                    "toString": "In Progress"
                  },
                  {
                    "field": "assignee",
                    "fromString": null,
                    "toString": "Nagy Júlia"
                  }
                ]
              },
              {
                "created": "2025-04-17T15:00:00.000+0000",
                "items": [
                  {
                    "field": "status",
                    "fromString": "In Progress",
                    "toString": "Blocked"
                  }
                ]
              },
              {
                "created": "2025-04-18T08:30:00.000+0000",
                "items": [
                  {
                    "field": "assignee",
                    "fromString": "Nagy Júlia",
                    "toString": "Kovács Béla"
                  },
                  {
                    "field": "status",
                    "fromString": "Blocked",
                    "toString": "In Progress"
                  }
                ]
              },
              {
                "created": "2025-04-19T11:00:00.000+0000",
                "items": [
                  {
                    "field": "status",
                    "fromString": "In Progress",
                    "toString": "Blocked"
                  }
                ]
              },
              {
                "created": "2025-04-20T13:45:00.000+0000",
                "items": [
                  {
                    "field": "status",
                    "fromString": "Blocked",
                    "toString": "Code Review"
                  }
                ]
              },
              {
                "created": "2025-04-21T14:00:00.000+0000",
                "items": [
                  {
                    "field": "status",
                    "fromString": "Code Review",
                    "toString": "Testing"
                  }
                ]
              },
              {
                "created": "2025-04-22T14:30:00.000+0000",
                "items": [
                  {
                    "field": "status",
                    "fromString": "Testing",
                    "toString": "Done"
                  }
                ]
              }
            ]
          }
        }
    """.trimIndent()
    val detailedJiraIssueText = """
        Ticket: PROJ-123
        Ticket-ID: 10001
        Summary: API integráció fejlesztése
        Description: Külső rendszerhez való kapcsolódás implementálása.
        Type: Story
        Priority: Medium
        Current status: Done
        Current assignee: Kovács Béla
        Reported by: Fejlesztő Fanni
        Created: 2025-04-15T08:00:00.000+0000
        Last updated: 2025-04-22T14:30:00.000+0000
        History:
        - 2025-04-15T09:00:00.000+0000: status: '-' → 'To Do'
        - 2025-04-16T10:00:00.000+0000: status: 'To Do' → 'In Progress'; assignee: '-' → 'Nagy Júlia'
        - 2025-04-17T15:00:00.000+0000: status: 'In Progress' → 'Blocked'
        - 2025-04-18T08:30:00.000+0000: assignee: 'Nagy Júlia' → 'Kovács Béla'; status: 'Blocked' → 'In Progress'
        - 2025-04-19T11:00:00.000+0000: status: 'In Progress' → 'Blocked'
        - 2025-04-20T13:45:00.000+0000: status: 'Blocked' → 'Code Review'
        - 2025-04-21T14:00:00.000+0000: status: 'Code Review' → 'Testing'
        - 2025-04-22T14:30:00.000+0000: status: 'Testing' → 'Done'
        """.trimIndent().trimEnd()
    }
}
