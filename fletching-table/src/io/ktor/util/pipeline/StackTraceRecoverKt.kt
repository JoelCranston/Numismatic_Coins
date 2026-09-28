@file:SourceDebugExtension(["SMAP\nStackTraceRecover.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StackTraceRecover.kt\nio/ktor/util/pipeline/StackTraceRecoverKt\n+ 2 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,23:1\n57#2,2:24\n*S KotlinDebug\n*F\n+ 1 StackTraceRecover.kt\nio/ktor/util/pipeline/StackTraceRecoverKt\n*L\n17#1:24,2\n*E\n"])

package io.ktor.util.pipeline

import kotlin.coroutines.Continuation
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.internal.StackTraceRecoveryKt

internal fun recoverStackTraceBridge(exception: Throwable, continuation: Continuation<*>): Throwable {
   var `exception$iv`: java.lang.Throwable;
   try {
      `exception$iv` = StackTraceRecoverJvmKt.withCause(
         if (DebugKt.getRECOVER_STACK_TRACES() && continuation is CoroutineStackFrame)
            StackTraceRecoveryKt.access$recoverFromStackFrame(exception, continuation as CoroutineStackFrame)
            else
            exception,
         exception.getCause()
      );
   } catch (var5: java.lang.Throwable) {
      `exception$iv` = exception;
   }

   return `exception$iv`;
}
