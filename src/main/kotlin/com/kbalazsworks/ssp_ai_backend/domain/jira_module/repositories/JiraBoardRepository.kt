package com.kbalazsworks.ssp_ai_backend.domain.jira_module.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraBoards
import com.kbalazsworks.ssp_ai_backend.db.tables.records.JiraBoardsRecord
import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_BOARDS
import com.kbalazsworks.ssp_ai_backend.domain.common_module.repositories.AbstractCrudRepository
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.entities.JiraBoard
import com.kbalazsworks.ssp_ai_backend.jooq_orm.value_objects.TableMeta
import org.springframework.stereotype.Repository

typealias JiraBoardGenerics = AbstractCrudRepository<JiraBoard, JiraBoards, JiraBoardsRecord>

@Repository
class JiraBoardRepository(jooqService: JooqService) : JiraBoardGenerics(jooqService) {
    override var tableMeta = TableMeta(JiraBoard::class.java, JIRA_BOARDS)
}
