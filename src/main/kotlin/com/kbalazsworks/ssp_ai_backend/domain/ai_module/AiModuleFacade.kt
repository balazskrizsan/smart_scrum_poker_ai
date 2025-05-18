package com.kbalazsworks.ssp_ai_backend.domain.ai_module

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.factories.EmbeddingConfigFactory
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.services.OpenAiService
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.EmbeddingConfig
import org.springframework.stereotype.Component

@Component
class AiModuleFacade(private val openAiService: OpenAiService) {
    fun createAndSaveEmbedding(embeddingConfig: EmbeddingConfig) = openAiService.createAndSaveEmbedding(embeddingConfig)

    fun getEmbeddingConfigFactory() = EmbeddingConfigFactory()
}
