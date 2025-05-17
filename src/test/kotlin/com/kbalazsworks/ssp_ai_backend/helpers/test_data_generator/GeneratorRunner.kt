package com.kbalazsworks.ssp_ai_backend.helpers.test_data_generator

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.JiraIssue
import com.kbalazsworks.ssp_ai_backend.helpers.jira_issues.sprint_1.*
import org.junit.jupiter.api.Test

class GeneratorRunner {
    companion object {
        private val objectMapper: ObjectMapper = jacksonObjectMapper()
    }

    @Test
    fun run() {
        println(genDotHttpFile(toEscapedString(issue101)))
        println(genDotHttpFile(toEscapedString(issue102)))
        println(genDotHttpFile(toEscapedString(issue103)))
        println(genDotHttpFile(toEscapedString(issue104)))
        println(genDotHttpFile(toEscapedString(issue105)))
        println(genDotHttpFile(toEscapedString(issue106)))
        println(genDotHttpFile(toEscapedString(issue107)))
        println(genDotHttpFile(toEscapedString(issue108)))
        println(genDotHttpFile(toEscapedString(issue109)))
        println(genDotHttpFile(toEscapedString(issue110)))
        println(genDotHttpFile(toEscapedString(issue111)))
        println(genDotHttpFile(toEscapedString(issue112)))
        println(genDotHttpFile(toEscapedString(issue113)))
        println(genDotHttpFile(toEscapedString(issue114)))
        println(genDotHttpFile(toEscapedString(issue115)))
        println(genDotHttpFile(toEscapedString(issue116)))
        println(genDotHttpFile(toEscapedString(issue117)))
        println(genDotHttpFile(toEscapedString(issue118)))
    }

    fun toEscapedString(jsonIssue: JiraIssue) =
        objectMapper.writeValueAsString(objectMapper.writeValueAsString(jsonIssue))

    fun genDotHttpFile(issueJson: String) = """POST {{host}}/api/v1/jira-issue
Content-Type: application/json
Accept: application/json
User-Agent: IntelliJ HTTP Client/IntelliJ IDEA 2024.3.5
Accept-Encoding: br, deflate, gzip, x-gzip

{
  "jiraIssueJson": $issueJson, "jiraSprintId": 1
}

###

""".trimIndent()
}
