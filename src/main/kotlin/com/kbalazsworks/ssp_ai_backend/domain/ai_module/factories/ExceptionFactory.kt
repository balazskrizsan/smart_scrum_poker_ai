package com.kbalazsworks.ssp_ai_backend.domain.ai_module.factories

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.exceptions.PromptHttpException

class ExceptionFactory {
    companion object {
        fun createPrompt(message: String) = PromptHttpException(message)
    }
}
