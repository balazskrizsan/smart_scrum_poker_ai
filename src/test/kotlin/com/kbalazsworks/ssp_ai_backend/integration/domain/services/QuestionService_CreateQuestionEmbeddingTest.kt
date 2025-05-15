package com.kbalazsworks.ssp_ai_backend.integration.domain.services

import com.kbalazsworks.ssp_ai_backend.AbstractTest
import com.kbalazsworks.ssp_ai_backend.domain.entities.Question
import com.kbalazsworks.ssp_ai_backend.domain.services.QuestionService
import com.kbalazsworks.ssp_ai_backend.fakers.domain.entities.QuestionFakeBuilder
import com.kbalazsworks.ssp_ai_backend.fakers.domain.value_objects.CreateQuestionEmbeddingFakeBuilder
import com.kbalazsworks.ssp_ai_backend.mockers.common.factories.LocalDateTimeFactoryMocker
import com.kbalazsworks.ssp_ai_backend.mockers.domain.services.SqsServiceMocker
import com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service.SqlPreset
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class QuestionService_CreateQuestionEmbeddingTest : AbstractTest() {
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
        createInstance(QuestionService::class.java, mocks).createQuestionEmbedding(testedCreateEmbedding)

        // Assert
        val actual = getDSLContext().selectFrom(questionTable).fetchOneInto(Question::class.java)

        assertAll(
            { assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected) },
            { verify { sqsServiceMock.sendMessage(eq("""{"type": "question_embedder", "id: 1}""")) } }
        )
    }
}
