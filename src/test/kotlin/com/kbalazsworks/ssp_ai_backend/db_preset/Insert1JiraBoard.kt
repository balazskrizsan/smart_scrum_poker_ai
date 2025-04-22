package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.db.tables.JiraBoards
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.JiraBoardFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import org.jooq.DSLContext
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class Insert1JiraBoard : IInsert {
    private lateinit var dslContext: DSLContext
    private lateinit var applicationContext: ApplicationContext

    override fun setDi(dslContext: DSLContext, applicationContext: ApplicationContext) {
        this.dslContext = dslContext
        this.applicationContext = applicationContext
    }

    override fun runParent() {
        val instance: IInsert = applicationContext.getBean(Insert1Company::class.java)

        instance.setDi(dslContext, applicationContext)

        instance.runParent()
        instance.run()
    }

    override fun run() {
        dslContext.newRecord(JiraBoards.JIRA_BOARDS, JiraBoardFakeBuilder().build()).store()
    }
}
