package com.example.common

import io.reactivex.Maybe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.rx2.awaitSingleOrNull
import kotlinx.coroutines.withContext

suspend fun <T> Maybe<T?>.toDeferred(scope: CoroutineScope): T? =
    withContext(scope.coroutineContext) { awaitSingleOrNull() }
