package com.kbalazsworks.ssp_ai_backend.domain.ai_module.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.JiraIssue
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
        builder.appendLine("Ticket-ID: ${issue.id}")
        builder.appendLine("Summary: ${issue.fields.summary}")
        builder.appendLine("Description: ${issue.fields.description}")
        builder.appendLine("Type: ${issue.fields.issuetype.name}")
        builder.appendLine("Priority: ${issue.fields.priority.name}")
        builder.appendLine("Current status: ${issue.fields.status.name}")
        builder.appendLine("Current assignee: ${issue.fields.assignee.displayName}")
        builder.appendLine("Reported by: ${issue.fields.reporter.displayName}")
        builder.appendLine("Created: ${issue.fields.created}")
        builder.appendLine("Last updated: ${issue.fields.updated}")
        builder.appendLine("Story Points: ${issue.fields.customfield_10016}")
        builder.appendLine("Watcher count: ${issue.fields.watcher.watchCount}")
        builder.appendLine("Is watching: ${issue.fields.watcher.isWatching}")
        if (issue.fields.attachment.isNotEmpty()) {
            builder.appendLine("Attachments:")
            issue.fields.attachment.forEach { att ->
                builder.appendLine("- ${att.filename} (${att.mimeType}, ${att.size} bytes), uploaded by ${att.author.displayName} at ${att.created}")
            }
        }
        if (issue.fields.comment.isNotEmpty()) {
            builder.appendLine("Comments:")
            issue.fields.comment.forEach { comment ->
                builder.appendLine("- ${comment.author.displayName} at ${comment.created}: ${comment.body}")
            }
        }
        if (issue.fields.issuelinks.isNotEmpty()) {
            builder.appendLine("Issue Links:")
            issue.fields.issuelinks.forEach { link ->
                builder.appendLine("- ${link.type.name}: Linked to ${link.outwardIssue.key} (Status: ${link.outwardIssue.fields.status.name})")
            }
        }
        if (issue.fields.subTasks.isNotEmpty()) {
            builder.appendLine("Sub-Tasks:")
            issue.fields.subTasks.forEach { subTask ->
                builder.appendLine("- ${subTask.type.name}: ${subTask.outwardIssue.key} (Status: ${subTask.outwardIssue.fields.status.name})")
            }
        }
        if (issue.fields.worklog.isNotEmpty()) {
            builder.appendLine("Worklogs:")
            issue.fields.worklog.forEach { worklog ->
                builder.appendLine("- ${worklog.author.displayName} logged ${worklog.timeSpent} at ${worklog.started}: ${worklog.comment}")
            }
        }
        issue.fields.timetracking?.let { tt ->
            builder.appendLine("Time Tracking:")
            builder.appendLine("- Original Estimate: ${tt.originalEstimate}")
            builder.appendLine("- Remaining Estimate: ${tt.remainingEstimate}")
            builder.appendLine("- Time Spent: ${tt.timeSpent}")
        }
        if (issue.changelog.histories.isNotEmpty()) {
            builder.appendLine("History:")
            issue.changelog.histories.forEach { history ->
                val changes = history.items.joinToString("; ") { item ->
                    "${item.field}: '${item.fromString ?: "-"}' → '${item.toString ?: "-"}'"
                }
                if (changes.isNotBlank()) {
                    builder.appendLine("- ${history.created} by ${history.author.displayName}: $changes")
                }
            }
        }

        return builder.trimEnd().toString()
    }
}
