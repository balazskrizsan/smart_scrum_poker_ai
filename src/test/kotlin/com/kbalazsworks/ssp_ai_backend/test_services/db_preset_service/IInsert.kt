package com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service

import org.jooq.DSLContext

interface IInsert {

    fun setDslContext(dslContext: DSLContext)

    fun runParent()

    fun run()
}
