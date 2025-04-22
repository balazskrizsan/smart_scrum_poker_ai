package com.kbalazsworks.ssp_ai_backend.db_preset

import com.kbalazsworks.ssp_ai_backend.db.tables.Companies
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.CompanyFakeBuilder
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.IInsert
import org.jooq.DSLContext
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class Insert1Company : IInsert {
    private lateinit var dslContext: DSLContext

    override fun setDi(dslContext: DSLContext, applicationContext: ApplicationContext) {
        this.dslContext = dslContext
    }

    override fun runParent() {
        // top level
    }

    override fun run() {
        dslContext.newRecord(Companies.COMPANIES, CompanyFakeBuilder().build()).store()
    }
}
