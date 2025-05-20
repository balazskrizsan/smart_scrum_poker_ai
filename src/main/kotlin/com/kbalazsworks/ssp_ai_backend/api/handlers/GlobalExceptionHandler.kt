package com.kbalazsworks.ssp_ai_backend.api.handlers

import com.kbalazsworks.ssp_ai_backend.api.builders.ResponseEntityBuilder
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

        val exceptionDetails = getDetails(ex)

        return ResponseEntityBuilder<String>()
            .data(exceptionDetails.message)
            .statusCode(HttpStatus.BAD_REQUEST)
            .errorCode(exceptionDetails.errorCode)
            .build()
    }

    private fun getDetails(ex: Exception) = when (ex) {
        is MethodArgumentNotValidException -> ExceptionDetails(getMethodArgumentNotValidExceptionMessage(ex), 2)
        is HttpMessageNotReadableException -> ExceptionDetails("Invalid JSON format", 3)
        is IntegrityConstraintViolationException -> ExceptionDetails("Error occurred", 4)
        else -> ExceptionDetails("Unknown error", 1)
    }

    private data class ExceptionDetails(val message: String, val errorCode: Int)

    private fun getMethodArgumentNotValidExceptionMessage(ex: MethodArgumentNotValidException) = ex
        .bindingResult
        .fieldErrors
        .joinToString(", ") { "${it.field}: ${it.defaultMessage}" }
}
