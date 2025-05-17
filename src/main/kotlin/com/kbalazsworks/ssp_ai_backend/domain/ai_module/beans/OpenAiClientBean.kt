package com.kbalazsworks.ssp_ai_backend.domain.ai_module.beans

import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
import com.openai.client.okhttp.OpenAIOkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAiClientBean(private val appProps: ApplicationPropertiesService) {
    @Bean
    fun openAIClient() = OpenAIOkHttpClient.builder()
        .apiKey(appProps.openaiApiKey)
        .organization(appProps.openaiOrganization)
        .project(appProps.openaiProject)
        .build()
}
