package com.kbalazsworks.ssp_ai_backend.integration.domain.question_module.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.domain.question_module.entities.Question
import com.kbalazsworks.ssp_ai_backend.domain.question_module.services.QuestionService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.entites.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.question_module.value_objects.CreateQuestionEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.mockers.common.factories.LocalDateTimeFactoryMocker
import com.kbalazsworks.ssp_ai_backend.mockers.domain.sqs_module.SqsServiceMocker
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class QuestionService_CreateQuestionTest : AbstractTest() {
    @Test
    @SqlPreset
    fun validQuestion_embeddedSavedToDb() {
        // Arrange
        val testedCreateEmbedding = CreateQuestionEmbeddingFakeBuilder().build()

        val expected = QuestionFakeBuilder().build()

        val localDateTimeFactoryMock = LocalDateTimeFactoryMocker().setDefaultValues().create()
        val sqsServiceMock = SqsServiceMocker().withDefaultResponse().create()

        // Act
        val mocks = listOf(sqsServiceMock, localDateTimeFactoryMock)
        createInstance(QuestionService::class.java, mocks).createQuestion(testedCreateEmbedding)

        // Assert
        val actual = getDSLContext().selectFrom(questionTable).fetchOneInto(Question::class.java)

        assertAll(
            { assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected) },
            { verify { sqsServiceMock.sendMessage(eq("""{"type": "question_embedder", "data": 1}""")) } }
        )
    }
}
