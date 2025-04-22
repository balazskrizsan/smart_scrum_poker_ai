package com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service

import org.jooq.DSLContext
import org.springframework.context.ApplicationContext

interface IInsert {

    fun setDi(dslContext: DSLContext, applicationContext: ApplicationContext)

    fun runParent()

    fun run()
}
