package com.kbalazsworks.ssp_ai_backend.api.controllers.jira_board

import com.kbalazsworks.ssp_ai_backend.api.requests.jira_board.JiraBoardPostRequest
import com.kbalazsworks.ssp_ai_backend.api.services.RequestMapperService
import com.kbalazsworks.ssp_ai_backend.api.services.ResponseEntityBuilder
import com.kbalazsworks.ssp_ai_backend.common.services.StateService
import com.kbalazsworks.ssp_ai_backend.common.value_objects.ResponseData
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.BoardService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/jira-board")
class JiraBoardPostAction(
    private val boardService: BoardService,
    private val stateService: StateService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(JiraBoardPostAction::class.java)
    }

    @PostMapping(consumes = ["application/json"], produces = ["application/json"])
    fun post(@Valid @RequestBody request: JiraBoardPostRequest): ResponseEntity<ResponseData<String>> {
        logger.info("Api call: POST:{}", "/api/v1/jira-board")

        boardService.save(RequestMapperService.map(stateService.getSnapshot(), request))

        return ResponseEntityBuilder<String>().data("").build()
    }
}
