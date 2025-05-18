package com.kbalazsworks.ssp_ai_backend.fakers.domain.ai_module.entities

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.entities.VectorModel

class VectorModelFakeBuilder {
    companion object {
        val DEFAULT_ID: Long? = 1L // will be 100500L after company/board configs are available
        val DEFAULT_NAME: String = "DEFAULT embedded model name"
    }

    private var id: Long? = DEFAULT_ID
    private var name: String = DEFAULT_NAME

    fun build() = VectorModel(id, name)
}
