@file:SourceDebugExtension(["SMAP\nContextUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContextUtils.kt\nio/ktor/util/debug/ContextUtilsKt\n+ 2 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n*L\n1#1,54:1\n375#2:55\n375#2:56\n375#2:57\n*S KotlinDebug\n*F\n+ 1 ContextUtils.kt\nio/ktor/util/debug/ContextUtilsKt\n*L\n21#1:55\n36#1:56\n52#1:57\n*E\n"])

package io.ktor.util.debug

import io.ktor.util.debug.ContextUtilsKt.initContextInDebugMode.2
import io.ktor.util.debug.plugins.PluginName
import io.ktor.util.debug.plugins.PluginsTrace
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.CoroutineContext.Element
import kotlin.coroutines.CoroutineContext.Key
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt

public suspend fun <T> initContextInDebugMode(block: (Continuation<Any>) -> Any?): Any {
   return if (!IntellijIdeaDebugDetector.INSTANCE.isDebuggerConnected())
      block.invoke(`$completion`)
      else
      BuildersKt.withContext(`$completion`.getContext().plus(new PluginsTrace(null, 1, null)), new 2(block, null), `$completion`);
}

public suspend fun <T> addToContextInDebugMode(pluginName: String, block: (Continuation<Any>) -> Any?): Any {
   return if (!IntellijIdeaDebugDetector.INSTANCE.isDebuggerConnected())
      block.invoke(`$completion`)
      else
      BuildersKt.withContext(
         `$completion`.getContext().plus(new PluginName(pluginName)),
         new io.ktor.util.debug.ContextUtilsKt.addToContextInDebugMode.2(block, null),
         `$completion`
      );
}

public suspend fun <Element : Element> useContextElementInDebugMode(key: Key<Any>, action: (Any) -> Unit) {
   if (!IntellijIdeaDebugDetector.INSTANCE.isDebuggerConnected()) {
      return Unit.INSTANCE;
   } else {
      val var10000: CoroutineContext.Element = `$completion`.getContext().get(key);
      if (var10000 != null) {
         action.invoke(var10000);
      }

      return Unit.INSTANCE;
   }
}
