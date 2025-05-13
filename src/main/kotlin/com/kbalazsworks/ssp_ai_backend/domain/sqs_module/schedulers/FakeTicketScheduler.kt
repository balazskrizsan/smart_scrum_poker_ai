package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.schedulers

import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService.Companion.SCHEDULERS_FAKE_TICKET_SCHEDULER_ENABLED
import com.kbalazsworks.ssp_ai_backend.domain.sqs_module.services.FakeSqsTriggerService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(name = [SCHEDULERS_FAKE_TICKET_SCHEDULER_ENABLED], havingValue = "true", matchIfMissing = false)
class FakeTicketScheduler(private val fakeSqsTriggerService: FakeSqsTriggerService) {
    @Scheduled(cron = "0/10 * * * * *")
    fun task() = fakeSqsTriggerService.sendMessage()
}
