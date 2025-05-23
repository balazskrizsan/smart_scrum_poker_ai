/*
 * This file is generated by jOOQ.
 */
package com.kbalazsworks.ssp_ai_backend.db


import com.kbalazsworks.ssp_ai_backend.db.tables.Companies
import com.kbalazsworks.ssp_ai_backend.db.tables.FlywaySchemaHistory
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraBoards
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraIssues
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraSprints
import com.kbalazsworks.ssp_ai_backend.db.tables.Questions
import com.kbalazsworks.ssp_ai_backend.db.tables.VectorModels
import com.kbalazsworks.ssp_ai_backend.db.tables.VectorStore_1536
import com.kbalazsworks.ssp_ai_backend.db.tables.VectorStore_3072

import kotlin.collections.List

import org.jooq.Catalog
import org.jooq.Table
import org.jooq.impl.SchemaImpl


/**
 * This class is generated by jOOQ.
 */
@Suppress("UNCHECKED_CAST")
open class Public : SchemaImpl("public", DefaultCatalog.DEFAULT_CATALOG) {
    companion object {

        /**
         * The reference instance of <code>public</code>
         */
        val PUBLIC: Public = Public()
    }

    /**
     * The table <code>public.companies</code>.
     */
    val COMPANIES: Companies get() = Companies.COMPANIES

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    val FLYWAY_SCHEMA_HISTORY: FlywaySchemaHistory get() = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY

    /**
     * The table <code>public.jira_boards</code>.
     */
    val JIRA_BOARDS: JiraBoards get() = JiraBoards.JIRA_BOARDS

    /**
     * The table <code>public.jira_issues</code>.
     */
    val JIRA_ISSUES: JiraIssues get() = JiraIssues.JIRA_ISSUES

    /**
     * The table <code>public.jira_sprints</code>.
     */
    val JIRA_SPRINTS: JiraSprints get() = JiraSprints.JIRA_SPRINTS

    /**
     * The table <code>public.questions</code>.
     */
    val QUESTIONS: Questions get() = Questions.QUESTIONS

    /**
     * The table <code>public.vector_models</code>.
     */
    val VECTOR_MODELS: VectorModels get() = VectorModels.VECTOR_MODELS

    /**
     * The table <code>public.vector_store_1536</code>.
     */
    val VECTOR_STORE_1536: VectorStore_1536 get() = VectorStore_1536.VECTOR_STORE_1536

    /**
     * The table <code>public.vector_store_3072</code>.
     */
    val VECTOR_STORE_3072: VectorStore_3072 get() = VectorStore_3072.VECTOR_STORE_3072

    override fun getCatalog(): Catalog = DefaultCatalog.DEFAULT_CATALOG

    override fun getTables(): List<Table<*>> = listOf(
        Companies.COMPANIES,
        FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
        JiraBoards.JIRA_BOARDS,
        JiraIssues.JIRA_ISSUES,
        JiraSprints.JIRA_SPRINTS,
        Questions.QUESTIONS,
        VectorModels.VECTOR_MODELS,
        VectorStore_1536.VECTOR_STORE_1536,
        VectorStore_3072.VECTOR_STORE_3072
    )
}
