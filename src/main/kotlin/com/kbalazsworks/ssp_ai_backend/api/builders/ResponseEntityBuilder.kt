package com.kbalazsworks.ssp_ai_backend.api.builders

import com.kbalazsworks.ssp_ai_backend.api.exceptions.ApiException
import com.kbalazsworks.ssp_ai_backend.common.value_objects.ResponseData
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseEntityBuilder<T> {
    private var data: T? = null;
    private var errorCode = 0;
    private var statusCode = HttpStatus.OK;
    private var headers = HttpHeaders();

    fun data(data: T) = apply { this.data = data }
    fun errorCode(errorCode: Int) = apply { this.errorCode = errorCode }
    fun statusCode(statusCode: HttpStatus) = apply { this.statusCode = statusCode }
    fun headers(headers: HttpHeaders) = apply { this.headers = headers }

    fun build(): ResponseEntity<ResponseData<T>> {
        val success = errorCode == 0;

        if (errorCode > 0 && statusCode == HttpStatus.OK) {
            throw ApiException("Status code setup is needed for error response");
        }

        val nonNullData: T = data ?: throw IllegalStateException("Data must not be null")

        val responseData = ResponseData<T>(nonNullData, success, errorCode, "1");

        return ResponseEntity<ResponseData<T>>(responseData, headers, statusCode);
    }
}
