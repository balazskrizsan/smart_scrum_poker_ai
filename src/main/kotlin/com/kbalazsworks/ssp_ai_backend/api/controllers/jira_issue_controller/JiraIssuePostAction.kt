package com.kbalazsworks.ssp_ai_backend.api.controllers.jira_issue_controller

import com.kbalazsworks.ssp_ai_backend.api.requests.jira_issue.JiraIssuePostRequest
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.JiraIssueEmbeddingService
import com.kbalazsworks.ssp_ai_backend.api.services.RequestMapperService
import com.kbalazsworks.ssp_ai_backend.api.services.ResponseEntityBuilder
import com.kbalazsworks.ssp_ai_backend.common.value_objects.ResponseData
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/jira-issue")
class JiraIssuePostAction(private val jiraIssueEmbeddingService: JiraIssueEmbeddingService) {
    companion object {
        private val logger = LoggerFactory.getLogger(JiraIssuePostAction::class.java)
    }

    @PostMapping(consumes = ["application/json"], produces = ["application/json"])
    fun post(@Valid @RequestBody request: JiraIssuePostRequest): ResponseEntity<ResponseData<String>> {
        logger.info("Api call: POST:{}", "/api/store")

        jiraIssueEmbeddingService.createEmbedding(RequestMapperService.map(request))

        return ResponseEntityBuilder<String>().data("").build()
    }
}
