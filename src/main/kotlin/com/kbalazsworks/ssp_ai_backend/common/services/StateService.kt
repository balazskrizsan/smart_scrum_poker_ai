package com.kbalazsworks.ssp_ai_backend.common.services

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.common.value_objects.State
import org.springframework.stereotype.Service

@Service
class StateService(private val localDateTimeFactory: LocalDateTimeFactory) {
    fun getSnapshot() = State(localDateTimeFactory.create())
}
