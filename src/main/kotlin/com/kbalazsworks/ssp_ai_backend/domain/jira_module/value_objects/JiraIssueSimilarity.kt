package com.kbalazsworks.ssp_ai_backend.domain.jira_module.value_objects

import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraIssue

data class JiraIssueSimilarity(val similarity: Float, val jiraIssue: JiraIssue)
