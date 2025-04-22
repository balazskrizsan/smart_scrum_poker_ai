package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.db.tables.JiraBoards
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraBoardFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class Insert1JiraBoard : IInsert {
    private lateinit var dslContext: DSLContext

    override fun setDslContext(dslContext: DSLContext) {
        this.dslContext = dslContext
    }

    override fun runParent() {
        TODO("Not yet implemented")
    }

    override fun run() {
        dslContext.newRecord(JiraBoards.JIRA_BOARDS, JiraBoardFakeBuilder().build()).store()
    }
}
