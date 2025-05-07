package com.kbalazsworks.ssp_ai_backend.domain.value_objects

data class JiraIssue(
    val id: String,
    val key: String,
    val self: String,
    val fields: Fields,
    val changelog: Changelog
)

data class Fields(
    val summary: String,
    val description: String,
    val issuetype: NameField,
    val priority: NameField,
    val status: NameField,
    val assignee: User,
    val reporter: User,
    val created: String,
    val updated: String,
    val customfield_10016: Int,
    val watcher: Watcher,
    val attachment: List<Attachment>,
    val comment: List<Comment>,
    val issuelinks: List<IssueLink>,
    val subTasks: List<SubTask>,
    val worklog: List<Worklog>,
    val timetracking: TimeTracking?
)

data class NameField(
    val name: String
)

data class User(
    val accountId: String?,
    val displayName: String
)

data class Watcher(
    val isWatching: Boolean,
    val self: String,
    val watchCount: Int
)

data class Attachment(
    val author: User,
    val content: String,
    val created: String,
    val filename: String,
    val id: Long,
    val mimeType: String,
    val self: String,
    val size: Int
)

data class Comment(
    val author: User,
    val body: String,
    val created: String
)

data class IssueLink(
    val id: String,
    val outwardIssue: OutwardIssue,
    val type: LinkType
)

data class OutwardIssue(
    val key: String,
    val self: String,
    val fields: StatusField
)

data class StatusField(
    val status: NameField
)

data class LinkType(
    val name: String,
    val outward: String,
    val inward: String
)

data class SubTask(
    val id: String,
    val outwardIssue: OutwardIssue,
    val type: SubTaskType
)

data class SubTaskType(
    val name: String
)

data class Worklog(
    val author: User,
    val comment: String,
    val started: String,
    val timeSpent: String,
    val timeSpentSeconds: Int
)

data class TimeTracking(
    val originalEstimate: String,
    val remainingEstimate: String,
    val timeSpent: String,
    val timeSpentSeconds: Int
)

data class Changelog(
    val startAt: Int,
    val maxResults: Int,
    val total: Int,
    val histories: List<History>
)

data class History(
    val id: String,
    val created: String,
    val author: Author,
    val items: List<ChangeItem>
)

data class Author(
    val accountId: String,
    val displayName: String
)

data class ChangeItem(
    val field: String,
    val fieldtype: String,
    val from: String,
    val fromString: String,
    val to: String,
    val toString: String
)
