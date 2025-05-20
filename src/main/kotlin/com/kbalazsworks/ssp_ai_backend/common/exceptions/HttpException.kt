package com.kbalazsworks.ssp_ai_backend.common.exceptions

class HttpException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)
