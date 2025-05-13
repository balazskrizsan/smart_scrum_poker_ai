package com.kbalazsworks.ssp_ai_backend.domain.sqs_module.beans

import com.kbalazsworks.ssp_ai_backend.common.services.ApplicationPropertiesService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region.US_EAST_1
import software.amazon.awssdk.services.sqs.SqsAsyncClient

@Configuration
class SqsAsyncClientBean(private val appProps: ApplicationPropertiesService) {
    @Bean
    fun sqsAsyncClient(): SqsAsyncClient = SqsAsyncClient.builder()
        .region(US_EAST_1)
        .credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(appProps.awsSqsAccessKeyId, appProps.awsSqsSecretAccessKey)
            )
        )
        .build()
}
