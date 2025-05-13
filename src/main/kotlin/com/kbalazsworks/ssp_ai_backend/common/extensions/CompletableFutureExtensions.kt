package com.kbalazsworks.ssp_ai_backend.common.extensions

import java.util.concurrent.CompletableFuture

fun <T> CompletableFuture<T>.onSuccess(onOk: (T) -> Unit): CompletableFuture<T> =
    this.whenComplete { result, error -> if (error == null) onOk(result) }

fun <T> CompletableFuture<T>.onFailure(onFail: (Throwable) -> Unit): CompletableFuture<T> =
    this.whenComplete { _, error -> error?.let { onFail(it) } }
