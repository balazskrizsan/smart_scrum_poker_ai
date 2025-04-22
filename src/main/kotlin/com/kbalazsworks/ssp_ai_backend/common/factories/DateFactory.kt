package com.kbalazsworks.ssp_ai_backend.common.factories

import org.springframework.stereotype.Component
import java.util.*

@Component
class DateFactory {
    fun create() = Date()

    fun create(date: Long) = Date(date)
}
