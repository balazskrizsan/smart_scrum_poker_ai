package com.kbalazsworks.ssp_ai_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class AiBackendApplication

fun main(args: Array<String>) {
    runApplication<AiBackendApplication>(*args)
}
