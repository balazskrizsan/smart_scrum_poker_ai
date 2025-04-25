package com.kbalazsworks.ssp_ai_backend.common.value_objects

data class ResponseData<T>(val data: T, val success: Boolean, val errorCode: Int, val requestId: String)
