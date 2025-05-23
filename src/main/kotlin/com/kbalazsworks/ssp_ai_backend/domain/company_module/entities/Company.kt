package com.kbalazsworks.ssp_ai_backend.domain.company_module.entities

import java.time.LocalDateTime

data class Company(val id: Long? = null, val name: String, val createdAt: LocalDateTime)
