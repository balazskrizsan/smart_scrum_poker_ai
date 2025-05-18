package com.kbalazsworks.ssp_ai_backend.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.entities.VectorStoreX
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.repositories.VectorStore1536Repository
import org.springframework.stereotype.Service

@Service
class VectorStoreService(private val vectorStore1536Repository: VectorStore1536Repository) {
    fun save(vectorStoreX: VectorStoreX) = vectorStore1536Repository.save(vectorStoreX)
}
