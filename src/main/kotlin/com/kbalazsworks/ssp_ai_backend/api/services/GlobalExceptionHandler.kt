package com.kbalazsworks.ssp_ai_backend.api.services

import com.kbalazsworks.ssp_ai_backend.common.value_objects.ResponseData
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [Exception::class])
    @ResponseBody
    fun simpleErrorHandler(ex: Exception): ResponseEntity<ResponseData<String>> {
        logger.error("Global exception handler error: ${ex.message}", ex)

        return ResponseEntityBuilder<String>()
            .data(ex.message!!)
            .statusCode(HttpStatus.BAD_REQUEST)
            .errorCode(1001)
            .build()
    }
}
