package com.kbalazsworks.ssp_ai_backend.domain.common_module.constants

class SqsMessages {
    companion object {
        const val QUESTION_QUEUE_REQUEST = """{"type": "question_embedder", "id: %d}"""
    }
}
