package com.kbalazsworks.ssp_ai_backend.common.factories

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class LocalDateTimeFactory(private val dateFactory: DateFactory) {
    fun create(): LocalDateTime = dateFactory.create().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
}
