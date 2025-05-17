package com.kbalazsworks.ssp_ai_backend.api.controllers.jira_sprint

import com.kbalazsworks.ssp_ai_backend.api.requests.jira_sprint.JiraSprintPostRequest
import com.kbalazsworks.ssp_ai_backend.api.services.RequestMapperService
import com.kbalazsworks.ssp_ai_backend.api.services.ResponseEntityBuilder
import com.kbalazsworks.ssp_ai_backend.common.services.StateService
import com.kbalazsworks.ssp_ai_backend.common.value_objects.ResponseData
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.SprintService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/jira-sprint")
class JiraSprintPostAction(
    private val sprintService: SprintService,
    private val stateService: StateService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(JiraSprintPostAction::class.java)
    }

    @PostMapping(consumes = ["application/json"], produces = ["application/json"])
    fun post(@Valid @RequestBody request: JiraSprintPostRequest): ResponseEntity<ResponseData<String>> {
        logger.info("Api call: POST:{}", "/api/v1/jira-board")

        sprintService.save(RequestMapperService.map(stateService.getSnapshot(), request))

        return ResponseEntityBuilder<String>().data("").build()
    }
}
