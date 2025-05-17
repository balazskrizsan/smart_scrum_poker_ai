package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraSprints
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraSprintFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.PresetRunnerService
import org.springframework.stereotype.Component

@Component
class Insert1JiraSprint(
    private val presetRunnerService: PresetRunnerService,
    private val jooqService: JooqService
) : IInsert {
    override fun runParent() = presetRunnerService.run(Insert1JiraBoard::class.java)

    override fun run() {
        jooqService.getDslContext().newRecord(JiraSprints.JIRA_SPRINTS, JiraSprintFakeBuilder().build()).store()
    }
}

