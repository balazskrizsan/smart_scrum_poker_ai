package com.kbalazsworks.ssp_ai_backend.domain.repositories

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraSprints
import com.kbalazsworks.ssp_ai_backend.db.tables.records.JiraSprintsRecord
import com.kbalazsworks.ssp_ai_backend.db.tables.references.JIRA_SPRINTS
import com.kbalazsworks.ssp_ai_backend.domain.entities.JiraSprint
import com.kbalazsworks.ssp_ai_backend.jooq_orm.value_objects.TableMeta
import org.springframework.stereotype.Repository

typealias JiraSprintGenerics = AbstractCrudRepository<JiraSprint, JiraSprints, JiraSprintsRecord>

@Repository
class JiraSprintRepository(jooqService: JooqService) : JiraSprintGenerics(jooqService) {
    override val tableMeta = TableMeta(JiraSprint::class.java, JIRA_SPRINTS)
}
