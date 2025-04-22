package com.kbalazsworks.ssp_ai_backend.services

import com.kbalazsworks.ssp_ai_backend.api.requests.EmbeddingRequest
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.CreateEmbedding

class RequestMapperService {
    companion object {
        fun map(request: EmbeddingRequest): CreateEmbedding {
            return CreateEmbedding(request.jiraSprintId, request.ticketJson)
        }
    }
}
