package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraBoards
import com.kbalazsworks.ssp_ai_backend.fakers.domain.jira_module.entites.JiraBoardFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.PresetRunnerService
import org.springframework.stereotype.Component

@Component
class Insert1JiraBoard(
    private val presetRunnerService: PresetRunnerService,
    private val jooqService: JooqService
) : IInsert {
    override fun runParent() = presetRunnerService.run(Insert1Company::class.java)

    override fun run() {
        jooqService.getDslContext().newRecord(JiraBoards.JIRA_BOARDS, JiraBoardFakeBuilder().build()).store()
    }
}
