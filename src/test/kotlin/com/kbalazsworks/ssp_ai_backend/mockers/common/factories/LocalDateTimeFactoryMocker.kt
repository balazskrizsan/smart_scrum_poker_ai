package com.kbalazsworks.ssp_ai_backend.mockers.common.factories

import com.kbalazsworks.ssp_ai_backend.common.factories.LocalDateTimeFactory
import com.kbalazsworks.ssp_ai_backend.helpers.DateTimeHelper
import io.mockk.every
import io.mockk.mockk

class LocalDateTimeFactoryMocker {
    private val localDateTimeFactoryMock = mockk<LocalDateTimeFactory>()

    fun setDefaultValues(): LocalDateTimeFactoryMocker {
        every { localDateTimeFactoryMock.create() } returns DateTimeHelper.defaultLocalDateTime

        return this
    }

    fun create() = localDateTimeFactoryMock
}
