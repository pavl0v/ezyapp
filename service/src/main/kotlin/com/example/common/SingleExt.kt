package com.example.common

import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.rx2.await
import kotlinx.coroutines.withContext

suspend fun <T> Single<T>.toDeferred(scope: CoroutineScope): T =
    withContext(scope.coroutineContext) { await() }
