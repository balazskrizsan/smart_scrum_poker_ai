package com.kbalazsworks.ssp_ai_backend.domain.ai_module.services

import com.kbalazsworks.ssp_ai_backend.domain.ai_module.value_objects.AskAi
import com.kbalazsworks.ssp_ai_backend.domain.jira_module.services.IssueEmbeddingService
import com.kbalazsworks.ssp_ai_backend.domain.question_module.QuestionModuleFacade
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
    private val issueEmbeddingService: IssueEmbeddingService,
    private val openAIClient: OpenAIClient,
    private val promptServiceService: PromptServiceService,
    private val questionModuleFacade: QuestionModuleFacade,
) {
    fun askSprint(askAi: AskAi): Flux<String> {
        val question = questionModuleFacade.getQuestion(askAi.questionId)
        val jiraIssueSimilarityList = issueEmbeddingService.similaritySearch(askAi)
        val chatLimitedTickets = promptServiceService.getLimitedPrompt(jiraIssueSimilarityList)

        val params = ChatCompletionCreateParams.builder()
            .addUserMessage(buildPromptMessage(question.question, chatLimitedTickets))
            .model(ChatModel.GPT_3_5_TURBO)
            .build()

        return askOpenAi(params)
    }

    private fun buildPromptMessage(question: String, charLimitedTickets: String) = """
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
