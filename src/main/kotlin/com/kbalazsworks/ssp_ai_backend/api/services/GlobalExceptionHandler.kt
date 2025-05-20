package com.kbalazsworks.ssp_ai_backend.api.services

import com.kbalazsworks.ssp_ai_backend.common.value_objects.ResponseData
import org.jooq.exception.IntegrityConstraintViolationException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {
    companion object {
        private val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(value = [Exception::class])
    @ResponseBody
    fun simpleErrorHandler(ex: Exception): ResponseEntity<ResponseData<String>> {
        logger.error("Global exception handler error: ${ex.message}", ex)

        return ResponseEntityBuilder<String>()
            .data(getMessage(ex))
            .statusCode(HttpStatus.BAD_REQUEST)
            .errorCode(getErrorCode(ex))
            .build()
    }

    private fun getErrorCode(ex: Exception) = when (ex) {
        is MethodArgumentNotValidException -> 2

        is HttpMessageNotReadableException -> 3

        is IntegrityConstraintViolationException -> 4

        else -> 1
    }

    private fun getMessage(ex: Exception) = when (ex) {
        is MethodArgumentNotValidException -> ex
            .bindingResult
            .fieldErrors
            .joinToString(", ") { "${it.field}: ${it.defaultMessage}" }

        is HttpMessageNotReadableException -> "Invalid JSON format"

        is IntegrityConstraintViolationException -> "Error occurred"

        else -> "Unknown error"
    }
}
