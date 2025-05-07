package com.kbalazsworks.ssp_ai_backend.domain.services

import com.kbalazsworks.ssp_ai_backend.domain.value_objects.AskAi
import com.openai.client.OpenAIClient
import com.openai.core.http.AsyncStreamResponse
import com.openai.models.ChatModel
import com.openai.models.chat.completions.ChatCompletionChunk
import com.openai.models.chat.completions.ChatCompletionCreateParams
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.*

@Service
class AskAiService(
    private val jiraIssueEmbeddingService: JiraIssueEmbeddingService,
    private val openAIClient: OpenAIClient,
    private val aiPromptServiceService: AiPromptServiceService,
    private val questionService: QuestionService,
) {
    fun askSprint(askAi: AskAi): Flux<String> {
        val question = questionService.get(askAi.questionId)
        val jiraIssueSimilarityList = jiraIssueEmbeddingService.similaritySearch(askAi)
        val chatLimitedTickets = aiPromptServiceService.getLimitedPrompt(jiraIssueSimilarityList)

        val params: ChatCompletionCreateParams = ChatCompletionCreateParams.builder()
            .addUserMessage(buildPromptMessage(question.question, chatLimitedTickets))
            .model(ChatModel.GPT_3_5_TURBO)
            .build()

        return askOpenAi(params)
    }

    fun buildPromptMessage(question: String, charLimitedTickets: String) = """
            Question:
             ${question}

             Related tickets:
             ${charLimitedTickets}
        """.trimIndent()

    fun askOpenAi(params: ChatCompletionCreateParams) = Flux.create { sink ->
        openAIClient.async()
            .chat()
            .completions()
            .createStreaming(params)
            .subscribe(object : AsyncStreamResponse.Handler<ChatCompletionChunk> {
                override fun onNext(value: ChatCompletionChunk) {
                    value.choices().firstOrNull()?.delta()?.content()?.ifPresent { sink.next(it) }
                }

                override fun onComplete(error: Optional<Throwable>) {
                    error.ifPresentOrElse({ sink.error(it) }, { sink.complete() })
                }
            })
    }
}
