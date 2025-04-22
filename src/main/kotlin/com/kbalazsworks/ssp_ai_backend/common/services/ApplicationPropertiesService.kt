package com.kbalazsworks.ssp_ai_backend.common.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ApplicationPropertiesService {
    @Value("\${server.env}") lateinit var serverEnv: String

    @Value("\${openai.api-key}") lateinit var openaiApiKey: String
    @Value("\${openai.organization}") lateinit var openaiOrganization: String
    @Value("\${openai.project}") lateinit var openaiProject: String


    @Value("\${spring.datasource.url}") lateinit var springDatasourceUrl: String
    @Value("\${spring.datasource.username}") lateinit var springDatasourceUsername: String
    @Value("\${spring.datasource.password}") lateinit var springDatasourcePassword: String
    @Value("\${spring.datasource.driver-class-name}") lateinit var springDatasourceDriverClassName: String
}
