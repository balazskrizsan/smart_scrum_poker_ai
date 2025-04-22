package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.Companies
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.CompanyFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.PresetRunnerService
import org.springframework.stereotype.Component

@Component
class Insert1Company(
    private val presetRunnerService: PresetRunnerService,
    private val jooqService: JooqService
) : IInsert {
    override fun runParent() {}

    override fun run() {
        jooqService.getDslContext().newRecord(Companies.COMPANIES, CompanyFakeBuilder().build()).store()
    }
}
