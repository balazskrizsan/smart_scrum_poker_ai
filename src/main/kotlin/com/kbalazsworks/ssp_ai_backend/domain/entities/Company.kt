package com.kbalazsworks.ssp_ai_backend.domain.entities

import com.kbalazsworks.ssp_ai_backend.db.tables.Companies
import java.time.LocalDateTime

data class Company(val id: Long? = null, val name: String, val createdAt: LocalDateTime) {
    companion object {
        val DB_TABLE = Companies.COMPANIES
    }
}
