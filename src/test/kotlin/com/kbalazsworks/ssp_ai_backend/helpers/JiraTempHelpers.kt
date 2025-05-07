package com.kbalazsworks.ssp_ai_backend.helpers

import com.kbalazsworks.ssp_ai_backend.domain.value_objects.Attachment
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.Author
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.IssueLink
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.LinkType
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.NameField
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.OutwardIssue
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.StatusField
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.SubTask
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.SubTaskType
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.TimeTracking
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.User
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.Watcher
import com.kbalazsworks.ssp_ai_backend.domain.value_objects.Worklog
import kotlin.random.Random

val statuses = listOf("To Do", "In Progress", "Done", "Blocked", "Under Review")
val priorities = listOf("Low", "Medium", "High", "Critical")
val issueTypes = listOf("Bug", "Task", "Story")

val users = listOf(
    User("5b10a2844c20165700ede211", "John Carter"), // TL
    User("5b10a2844c20165700ede212", "Emily Watson"), // PM
    User("5b10a2844c20165700ede213", "Michael Brooks"), // DEV-BE
    User("5b10a2844c20165700ede214", "Sarah Bennett"), // DEV-BE
    User("5b10a2844c20165700ede216", "Olivia Parker"), // DEV-FE
    User("5b10a2844c20165700ede216", "Dave Leard"), // DEV-FE
    User("5b10a2844c20165700ede217", "James Turner"), // DEVOPS
)
val authors = listOf(
    Author(users[0].accountId!!, users[0].displayName),
    Author(users[1].accountId!!, users[1].displayName),
    Author(users[2].accountId!!, users[2].displayName),
    Author(users[3].accountId!!, users[3].displayName),
    Author(users[4].accountId!!, users[4].displayName),
    Author(users[5].accountId!!, users[5].displayName),
    Author(users[6].accountId!!, users[6].displayName),
)

val random = Random(System.currentTimeMillis())

fun getTl() = users[0]
fun getPm() = users[1]
fun getDevsBe() = listOf(users[2], users[3])
fun getDevsFe() = listOf(users[4], users[5])
fun getDevops() = listOf(users[6])

fun getDate(day: Int, hour: Int) = "2024-08-${day}T${hour}:00:00.000+0000"
fun getPastDate(monthBack: Int, day: Int, hour: Int) = "2025-${8-monthBack}-${day}T${hour}:00:00.000+0000"

fun getWatcher(id: String, isWatching: Boolean, watchCount: Int): Watcher =
    Watcher(
        isWatching = isWatching,
        self = "https://your-domain.atlassian.net/rest/api/2/issue/ED-$id/watchers",
        watchCount = watchCount
    )

///////////////////////////////////////////////////////////////////////////////////

fun randomDate(): String {
    val day = random.nextInt(10, 27)
    return "2025-04-${day}T${random.nextInt(8, 18)}:${random.nextInt(10, 59)}:00.000+0000"
}

fun randomAttachments(): List<Attachment> =
    List(random.nextInt(0, 3)) {
        Attachment(
            author = users.random(),
            content = "https://your-domain.atlassian.net/jira/rest/api/3/attachment/content/${
                random.nextInt(
                    10000,
                    10100
                )
            }",
            created = randomDate(),
            filename = "screenshot_${it}.png",
            id = random.nextLong(10000, 20000),
            mimeType = "image/png",
            self = "https://your-domain.atlassian.net/rest/api/2/attachments/${random.nextInt(10000, 20000)}",
            size = random.nextInt(1024, 5120)
        )
    }

fun randomIssueLinks(): List<IssueLink> =
    List(random.nextInt(0, 2)) {
        IssueLink(
            id = random.nextInt(10000, 11000).toString(),
            outwardIssue = OutwardIssue(
                key = "ED-${random.nextInt(1, 50)}",
                self = "https://your-domain.atlassian.net/rest/api/2/issue/ED-${random.nextInt(1, 50)}",
                fields = StatusField(status = NameField(statuses.random()))
            ),
            type = LinkType(name = "Blocks", outward = "blocks", inward = "is blocked by")
        )
    }

fun randomSubTasks(): List<SubTask> =
    List(random.nextInt(0, 2)) {
        SubTask(
            id = random.nextInt(10000, 11000).toString(),
            outwardIssue = OutwardIssue(
                key = "ED-${random.nextInt(1, 50)}",
                self = "https://your-domain.atlassian.net/rest/api/2/issue/ED-${random.nextInt(1, 50)}",
                fields = StatusField(status = NameField(statuses.random()))
            ),
            type = SubTaskType(name = "Sub-task")
        )
    }

fun randomWorklogs(): List<Worklog> =
    List(random.nextInt(1, 5)) {
        Worklog(
            author = getDevsBe()[0],
            comment = "Worked on issue part ${it}",
            started = randomDate(),
            timeSpent = "${random.nextInt(1, 8)}h",
            timeSpentSeconds = random.nextInt(3600, 28800)
        )
    }

fun randomTimeTracking(): TimeTracking =
    TimeTracking(
        originalEstimate = "1d",
        remainingEstimate = "${random.nextInt(1, 8)}h",
        timeSpent = "${random.nextInt(1, 12)}h",
        timeSpentSeconds = random.nextInt(3600, 43200)
    )

