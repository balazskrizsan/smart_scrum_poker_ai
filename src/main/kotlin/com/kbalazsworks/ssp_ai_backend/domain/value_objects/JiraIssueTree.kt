package com.kbalazsworks.ssp_ai_backend.domain.value_objects

data class JiraIssue(
    val id: String,
    val key: String,
    val fields: Fields,
    val changelog: Changelog?
)

data class Fields(
    val summary: String?,
    val description: String?,
    val status: Status?,
    val issuetype: IssueType?,
    val assignee: User?,
    val reporter: User?,
    val created: String?,
    val updated: String?,
    val priority: Priority?
)

data class Status(val name: String?)
data class IssueType(val name: String?)
data class User(val displayName: String?)
data class Priority(val name: String?)

data class Changelog(
    val histories: List<ChangeHistory>
)

data class ChangeHistory(
    val created: String,
    val items: List<ChangeItem>
)

data class ChangeItem(
    val field: String,
    val fromString: String?,
    val toString: String?
)
