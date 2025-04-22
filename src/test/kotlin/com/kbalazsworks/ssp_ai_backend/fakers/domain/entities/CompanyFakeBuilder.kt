package com.kbalazsworks.ssp_ai_backend.fakers.domain.entities

import com.kbalazsworks.ssp_ai_backend.domain.entities.Company
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import java.time.LocalDateTime

class CompanyFakeBuilder {
    companion object {
        const val DEFAULT_ID = 100300L
        const val DEFAULT_NAME = "Fake Company Inc."
        val DEFAULT_CREATED_AT: LocalDateTime = DateTimeHelper.defaultLocalDateTime
    }

    private var id: Long? = DEFAULT_ID
    private var name: String = DEFAULT_NAME
    private var createdAt = DEFAULT_CREATED_AT

    fun build() = Company(id, name, createdAt)
}
