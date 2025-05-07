package com.kbalazsworks.ssp_ai_backend.domain.value_objects

import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraIssueEmbedding

data class JiraIssueSimilarity(val similarity: Float, val jiraIssueEmbedding: JiraIssueEmbedding)
