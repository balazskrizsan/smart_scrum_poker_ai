package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.value_objects

data class QueueRequest<T>(val type: String, val data: T)
