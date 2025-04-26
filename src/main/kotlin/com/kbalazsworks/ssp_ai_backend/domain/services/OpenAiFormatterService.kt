package com.kbalazsworks.ssp_ai_backend.domain.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.JiraIssue
import org.springframework.stereotype.Service

@Service
class OpenAiFormatterService {
    companion object {
        private val objectMapper: ObjectMapper = jacksonObjectMapper()
    }

    fun jiraIssueToText(jsonText: String): String {
        val issue: JiraIssue = objectMapper.readValue(jsonText)
        val builder = StringBuilder()

        builder.appendLine("Ticket: ${issue.key}")
        issue.id.let { builder.appendLine("Ticket-ID: $it") }
        issue.fields.summary?.let { builder.appendLine("Summary: $it") }
        issue.fields.description?.let { builder.appendLine("Description: $it") }
        issue.fields.issuetype?.name?.let { builder.appendLine("Type: $it") }
        issue.fields.priority?.name?.let { builder.appendLine("Priority: $it") }
        issue.fields.status?.name?.let { builder.appendLine("Current status: $it") }
        issue.fields.assignee?.displayName?.let { builder.appendLine("Current assignee: $it") }
        issue.fields.reporter?.displayName?.let { builder.appendLine("Reported by: $it") }
        issue.fields.created?.let { builder.appendLine("Created: $it") }
        issue.fields.updated?.let { builder.appendLine("Last updated: $it") }
        issue.changelog?.let {
            builder.appendLine("History:")
            it.histories.sortedBy { h -> h.created }.forEach { history ->
                val changes = history.items.filter { item -> item.field in listOf("status", "assignee") }
                    .joinToString("; ") { item ->
                        "${item.field}: '${item.fromString ?: "-"}' → '${item.toString ?: "-"}'"
                    }
                if (changes.isNotBlank()) {
                    builder.appendLine("- ${history.created}: $changes")
                }
            }
        }

        return builder.trimEnd().toString()
    }
}
