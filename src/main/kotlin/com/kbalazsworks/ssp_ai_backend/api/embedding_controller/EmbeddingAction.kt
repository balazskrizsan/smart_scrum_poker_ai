package com.kbalazsworks.ssp_ai_backend.api.embedding_controller

import com.kbalazsworks.ssp_ai_backend.api.requests.EmbeddingRequest
import com.kbalazsworks.ssp_ai_backend.domain.services.EmbeddingService
import com.kbalazsworks.ssp_ai_backend.api.services.RequestMapperService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/embedding")
class EmbeddingAction(private val embeddingService: EmbeddingService) {
    companion object {
        private val logger = LoggerFactory.getLogger(EmbeddingAction::class.java)
    }

    @PostMapping(consumes = ["application/json"], produces = ["application/json"])
    fun store(@Valid @RequestBody request: EmbeddingRequest): ResponseEntity<String> {
        logger.info("Api call: POST:{}", "/api/store")

        embeddingService.createEmbedding(RequestMapperService.map(request))

        return ResponseEntity.ok("")
    }
}
