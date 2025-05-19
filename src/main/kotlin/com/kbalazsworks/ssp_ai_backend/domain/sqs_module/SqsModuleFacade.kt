package com.kbalazsworks.ssp_ai_backend.domain.sqs_module

import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services.SqsService
import org.springframework.stereotype.Component

@Component
class SqsModuleFacade (private val sqsService: SqsService) {
    fun sendMessage(payload: String) = sqsService.sendMessage(payload)
}
