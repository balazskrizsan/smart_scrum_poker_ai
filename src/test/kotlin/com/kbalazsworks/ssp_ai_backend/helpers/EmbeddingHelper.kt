package com.kbalazsworks.ssp_ai_backend.helpers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.openai.models.embeddings.CreateEmbeddingResponse
import com.pgvector.PGvector

class EmbeddingHelper {
    companion object {
        val cached1536AsFloatArray: FloatArray by lazy {
            val random = java.util.Random(42L)
            FloatArray(1536) { random.nextFloat() }
        }
        val cached3072AsFloatArray: FloatArray by lazy {
            val random = java.util.Random(42L)
            FloatArray(3072) { random.nextFloat() }
        }
        val cached1536AsVector = PGvector(cached1536AsFloatArray)
        val cached3072AsVector = PGvector(cached3072AsFloatArray)
        val cached1536VectorResponse: CreateEmbeddingResponse by lazy {
            val mockJson = RESPONSE_TEMPLATE.trimIndent().replace(
                "__EMBEDDING__",
                cached1536AsFloatArray.joinToString(prefix = "[", postfix = "]", separator = ", ") { it.toString() }
            )

            jacksonObjectMapper().readValue(mockJson)
        }
        val cached3072VectorResponse: CreateEmbeddingResponse by lazy {
            val mockJson = RESPONSE_TEMPLATE.trimIndent().replace(
                "__EMBEDDING__",
                cached3072AsFloatArray.joinToString(prefix = "[", postfix = "]", separator = ", ") { it.toString() }
            )

            jacksonObjectMapper().readValue(mockJson)
        }
        private const val RESPONSE_TEMPLATE = """
                {
                  "data": [
                    {
                      "index": 0,
                      "embedding": __EMBEDDING__
                    }
                  ],
                  "model": "text-embedding-3-small",
                  "usage": {
                    "promptTokens": 10,
                    "totalTokens": 15
                  },
                  "additionalProperties": {}
                }
            """
    }
}
