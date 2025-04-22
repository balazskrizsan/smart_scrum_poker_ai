package com.kbalazsworks.ssp_ai_backend.test_services.db_preset_service

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.InvocationInterceptor
import org.junit.jupiter.api.extension.ReflectiveInvocationContext
import org.slf4j.LoggerFactory
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.lang.reflect.Method

class SqlPresetExtension : InvocationInterceptor {
    companion object {
        private val logger = LoggerFactory.getLogger(SqlPresetExtension::class.java)
    }

    override fun interceptTestMethod(
        invocation: InvocationInterceptor.Invocation<Void>,
        context: ReflectiveInvocationContext<Method>,
        extensionContext: ExtensionContext
    ) {
        val presetService = SpringExtension.getApplicationContext(extensionContext).getBean(PresetService::class.java)

        val method = context.executable

        val annotation = method.getAnnotation(SqlPreset::class.java)
        if (annotation != null) {
            val truncateBefore = annotation.truncateBefore
            val truncateAfter = annotation.truncateAfter
            val presetClasses = annotation.presets

            if (truncateBefore) {
                logger.info("Truncate before enabled")
                presetService.truncate()
            }

            if (presetClasses.isNotEmpty())
            {
                presetService.setupDb(presetClasses)
            }

            invocation.proceed()

            if (truncateAfter) {
                logger.info("Truncate after enabled")
                presetService.truncate()
            }

            return
        }

        invocation.proceed()
    }
}
