package com.kbalazsworks.ssp_ai_backend.api.controllers.ask_ai_controller

import com.kbalazsworks.ssp_ai_backend.api.requests.ask_ai.AskAiPostRequest
import com.kbalazsworks.ssp_ai_backend.api.services.RequestMapperService
import com.kbalazsworks.ssp_ai_backend.domain.services.AskAiService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/v1/ask-ai")
class AskAiGetAction(private val askAiService: AskAiService) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @GetMapping(consumes = ["application/json"], produces = ["application/json"])
    fun get(@Valid @RequestBody request: AskAiPostRequest): Flux<String> {
        logger.info("Flux api call: GET:{}", "/api/v1/ask-ai")

        return askAiService.askSprint(RequestMapperService.map(request))
    }
}
