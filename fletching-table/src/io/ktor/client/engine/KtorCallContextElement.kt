package io.ktor.client.engine

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.CoroutineContext.Key

internal class KtorCallContextElement(callContext: CoroutineContext) : CoroutineContext.Element {
   public final val callContext: CoroutineContext

   public open val key: Key<*>
      public open get() {
         return Companion;
      }


   init {
      this.callContext = callContext;
   }

   public companion object : CoroutineContext.Key<KtorCallContextElement>
}
