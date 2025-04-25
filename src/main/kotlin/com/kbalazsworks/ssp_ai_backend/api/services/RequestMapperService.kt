package com.kbalazsworks.ssp_ai_backend.api.services

import com.kbalazsworks.ssp_ai_backend.api.requests.EmbeddingRequest
import com.kbalazsworks.ssp_ai_backend.api.requests.company.PostRequest
import com.kbalazsworks.ssp_ai_backend.common.value_objects.State
import com.kbalazsworks.ssp_ai_backend.domain.entities.Company
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateEmbedding

class RequestMapperService {
    companion object {
        fun map(request: EmbeddingRequest) = CreateEmbedding(request.jiraSprintId, request.jiraIssueJson)
        fun map(state: State, request: PostRequest) = Company(null, request.name, state.localDateTime)
    }
}
