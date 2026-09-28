package io.ktor.client.plugins.observer

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.slf4j.MDCContext

internal suspend fun getResponseObserverContext(): CoroutineContext {
   val var10000: MDCContext = `$completion`.getContext().get(MDCContext.Key);
   return if (var10000 != null) var10000 else EmptyCoroutineContext.INSTANCE;
}
