package com.kbalazsworks.ssp_ai_backend.domain.ai_module.entities

import com.pgvector.PGvector

data class VectorStoreX(
    val questionId: Long?,
    val jiraIssueId: Long?,
    val vectorModelId: Long,
    val embedding: PGvector
)
