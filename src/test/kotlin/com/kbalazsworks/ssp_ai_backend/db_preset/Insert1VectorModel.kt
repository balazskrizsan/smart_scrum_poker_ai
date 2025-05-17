package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.references.VECTOR_MODELS
import com.kbalazsworks.ssp_ai_backend.fakers.domain.ai_module.entities.VectorModelFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.PresetRunnerService
import org.springframework.stereotype.Component

@Component
class Insert1VectorModel(
    private val presetRunnerService: PresetRunnerService,
    private val jooqService: JooqService
) : IInsert {
    override fun runParent() {}

    override fun run() {
        jooqService.getDslContext().newRecord(VECTOR_MODELS, VectorModelFakeBuilder().build()).store()
    }
}
