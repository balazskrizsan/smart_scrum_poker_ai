package com.kbalazsworks.ssp_ai_backend

import com.kbalazsworks.ssp_ai_backend.common.services.JooqService
import com.kbalazsworks.ssp_ai_backend.test_services.service_factory.ServiceFactory
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import java.util.function.Consumer
import com.kbalazsworks.ssp_ai_backend.db.tables.JiraTicketEmbeddings

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = [AiBackendApplication::class])
abstract class AbstractTest {
    @Autowired
    private lateinit var serviceFactory: ServiceFactory

    @Autowired
    private lateinit var jooqService: JooqService

    protected val jiraTicketEmbeddingsTable = JiraTicketEmbeddings.JIRA_TICKET_EMBEDDINGS

    @AfterEach
    fun after() {
        serviceFactory.resetMockContainer()
    }

    protected fun <T> createInstance(clazz: Class<T>, mocks: List<Any>): T {
        mocks.forEach(Consumer { m: Any -> setOneTimeMock(clazz, m) })

        return createInstance(clazz)
    }

    protected fun <T> createInstance(clazz: Class<T>, mock: Any): T {
        setOneTimeMock(clazz, mock)

        return createInstance(clazz)
    }

    protected fun <T> createInstance(clazz: Class<T>): T {
        return serviceFactory.createInstance(clazz)
    }

    protected fun setOneTimeMock(newClass: Class<*>, mock: Any) {
        serviceFactory.setOneTimeMock(newClass, mock)
    }

    protected fun getDSLContext() = jooqService.getDslContext()
}
