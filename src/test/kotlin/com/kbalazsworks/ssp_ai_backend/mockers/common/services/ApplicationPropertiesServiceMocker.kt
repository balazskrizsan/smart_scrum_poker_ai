package com.kbalazsworks.ssp_ai_backend.mockers.common.services

import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
import io.mockk.every
import io.mockk.mockk

class ApplicationPropertiesServiceMocker {
    companion object {
        private val defaultValues = listOf(
            Triple("serverEnv", ApplicationPropertiesService::serverEnv, "MockTestEnv"),
            Triple("openaiApiKey", ApplicationPropertiesService::openaiApiKey, "MockOpenaiApiKey"),
            Triple("openaiOrganization", ApplicationPropertiesService::openaiOrganization, "MockOpenaiOrganization"),
            Triple("openaiProject", ApplicationPropertiesService::openaiProject, "MockOpenaiProject")
        )
    }

    private val mock = mockk<ApplicationPropertiesService>()

    fun setDefaultValues(): ApplicationPropertiesServiceMocker {
        defaultValues.forEach { (_, propRef, value) -> every { propRef.get(mock) } returns value }

        return this
    }

    fun create() = mock
}
