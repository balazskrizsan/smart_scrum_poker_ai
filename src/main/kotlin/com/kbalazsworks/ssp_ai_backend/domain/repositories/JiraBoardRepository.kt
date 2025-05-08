package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraBoards
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraBoards.Companion.JIRA_BOARDS
import com.kbalazsworks.ssp_ai_backend.db.tables.records.JiraBoardsRecord
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraBoard
import com.kbalazsworks.ssp_ai_backend.jooq_orm.value_objects.TableMeta
import org.springframework.stereotype.Repository

typealias JiraBoardGenerics = AbstractCrudRepository<JiraBoard, JiraBoards, JiraBoardsRecord>

@Repository
class JiraBoardRepository(jooqService: JooqService) : JiraBoardGenerics(jooqService) {
    override var tableMeta = TableMeta(JiraBoard::class.java, JIRA_BOARDS)
}
