package com.kbalazsworks.ssp_ai_backend.api.controllers.company

import com.kbalazsworks.ssp_ai_backend.api.requests.company.CompanyPostRequest
import com.kbalazsworks.ssp_ai_backend.api.services.RequestMapperService
import com.kbalazsworks.ssp_ai_backend.api.builders.ResponseEntityBuilder
import com.kbalazsworks.ssp_ai_backend.common.services.StateService
import com.kbalazsworks.ssp_ai_backend.common.value_objects.ResponseData
import com.kbalazsworks.ssp_ai_backend.domain.company_module.services.CompanyService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/company")
class CompanyPostAction(
    private val companyService: CompanyService,
    private val stateService: StateService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(CompanyPostAction::class.java)
    }

    @PostMapping(consumes = ["application/json"], produces = ["application/json"])
    fun post(@Valid @RequestBody request: CompanyPostRequest): ResponseEntity<ResponseData<String>> {
        logger.info("Api call: POST:{}", "/api/v1/company")

        companyService.save(RequestMapperService.map(stateService.getSnapshot(), request))

        return ResponseEntityBuilder<String>().data("").build()
    }
}
