@file:SourceDebugExtension(["SMAP\nStackTraceRecovery.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,210:1\n1790#2,6:211\n12567#2,2:221\n1682#2,6:223\n12567#2,2:229\n1682#2,6:232\n37#3:217\n36#3,3:218\n1#4:231\n*S KotlinDebug\n*F\n+ 1 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n39#1:211,6\n127#1:221,2\n137#1:223,6\n169#1:229,2\n190#1:232,6\n102#1:217\n102#1:218,3\n*E\n"])

package kotlinx.coroutines.internal

import _COROUTINE.ArtificialStackFrames
import _COROUTINE.CoroutineDebuggingKt
import java.util.ArrayDeque
import kotlin.coroutines.Continuation
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DebugKt

private const val baseContinuationImplClass: String = "kotlin.coroutines.jvm.internal.BaseContinuationImpl"
private const val stackTraceRecoveryClass: String = "kotlinx.coroutines.internal.StackTraceRecoveryKt"
private final val ARTIFICIAL_FRAME: StackTraceElement = new ArtificialStackFrames().coroutineBoundary()
private final val baseContinuationImplClassName: String
private final val stackTraceRecoveryClassName: String

internal fun <E : Throwable> recoverStackTrace(exception: E): E {
   label11:
   if (!DebugKt.getRECOVER_STACK_TRACES()) {
      return (E)exception;
   } else {
      val var10000: java.lang.Throwable = ExceptionsConstructorKt.tryCopyException(exception);
      return (E)(if (var10000 == null) exception else sanitizeStackTrace(var10000));
   }
}

private fun <E : Throwable> E.sanitizeStackTrace(): E {
   var stackTrace: Array<StackTraceElement>;
   var size: Int;
   var var10000: Int;
   label34: {
      stackTrace = `$this$sanitizeStackTrace`.getStackTrace();
      size = stackTrace.length;
      val startIndex: Array<Any> = stackTrace;
      var adjustment: Int = stackTrace.length + -1;
      if (0 <= stackTrace.length + -1) {
         do {
            val trace: Int = adjustment--;
            if (stackTraceRecoveryClassName == startIndex[trace].getClassName()) {
               var10000 = trace;
               break label34;
            }
         } while (0 <= adjustment);
      }

      var10000 = -1;
   }

   val var12: Int = var10000 + 1;
   val var13: Int = firstFrameIndex(stackTrace, baseContinuationImplClassName);
   val var14: Int = if (var13 == -1) 0 else size - var13;
   var var15: Int = 0;
   val var16: Int = size - var10000 - var14;

   val var10: Array<StackTraceElement>;
   for (var10 = new StackTraceElement[size - var10000 - adjustment]; var15 < var16; var15++) {
      var10[var15] = if (var15 == 0) ARTIFICIAL_FRAME else stackTrace[var12 + var15 - 1];
   }

   `$this$sanitizeStackTrace`.setStackTrace(var10);
   return (E)`$this$sanitizeStackTrace`;
}

internal inline fun <E : Throwable> recoverStackTrace(exception: E, continuation: Continuation<*>): E {
   return (E)(if (DebugKt.getRECOVER_STACK_TRACES() && continuation is CoroutineStackFrame)
      access$recoverFromStackFrame(exception, continuation as CoroutineStackFrame)
      else
      exception);
}

private fun <E : Throwable> recoverFromStackFrame(exception: E, continuation: CoroutineStackFrame): E {
   val var2: Pair = causeAndStacktrace(exception);
   val cause: java.lang.Throwable = var2.component1() as java.lang.Throwable;
   val recoveredStacktrace: Array<StackTraceElement> = var2.component2() as Array<StackTraceElement>;
   val var10000: java.lang.Throwable = ExceptionsConstructorKt.tryCopyException(cause);
   if (var10000 == null) {
      return (E)exception;
   } else {
      val stacktrace: ArrayDeque = createStackTrace(continuation);
      if (stacktrace.isEmpty()) {
         return (E)exception;
      } else {
         if (cause != exception) {
            mergeRecoveredTraces(recoveredStacktrace, stacktrace);
         }

         return (E)createFinalException(cause, var10000, stacktrace);
      }
   }
}

private fun <E : Throwable> createFinalException(cause: E, result: E, resultStackTrace: ArrayDeque<StackTraceElement>): E {
   resultStackTrace.addFirst(ARTIFICIAL_FRAME);
   val causeTrace: Array<StackTraceElement> = cause.getStackTrace();
   val size: Int = firstFrameIndex(causeTrace, baseContinuationImplClassName);
   if (size == -1) {
      result.setStackTrace(resultStackTrace.toArray(new StackTraceElement[0]));
      return (E)result;
   } else {
      val mergedStackTrace: Array<StackTraceElement> = new StackTraceElement[resultStackTrace.size() + size];

      for (int i = 0; i < size; i++) {
         mergedStackTrace[i] = causeTrace[i];
      }

      val var11: java.util.Iterator = resultStackTrace.iterator();
      var `thisCollection$iv`: Int = 0;

      while (var11.hasNext()) {
         mergedStackTrace[size + `thisCollection$iv`++] = var11.next() as StackTraceElement;
      }

      result.setStackTrace(mergedStackTrace);
      return (E)result;
   }
}

private fun <E : Throwable> E.causeAndStacktrace(): Pair<E, Array<StackTraceElement>> {
   val cause: java.lang.Throwable = `$this$causeAndStacktrace`.getCause();
   val var10000: Pair;
   if (cause != null && cause.getClass() == `$this$causeAndStacktrace`.getClass()) {
      val currentTrace: Array<StackTraceElement> = `$this$causeAndStacktrace`.getStackTrace();
      val `$this$any$iv`: Array<Any> = currentTrace;
      var var5: Int = 0;
      val var6: Int = currentTrace.length;

      while (true) {
         if (var5 >= var6) {
            var10 = false;
            break;
         }

         if (isArtificial((StackTraceElement)`$this$any$iv`[var5])) {
            var10 = true;
            break;
         }

         var5++;
      }

      var10000 = if (var10) TuplesKt.to(cause, currentTrace) else TuplesKt.to(`$this$causeAndStacktrace`, new StackTraceElement[0]);
   } else {
      var10000 = TuplesKt.to(`$this$causeAndStacktrace`, new StackTraceElement[0]);
   }

   return var10000;
}

private fun mergeRecoveredTraces(recoveredStacktrace: Array<StackTraceElement>, result: ArrayDeque<StackTraceElement>) {
   val lastFrameIndex: Array<Any> = recoveredStacktrace;
   var element: Int = 0;
   val var6: Int = recoveredStacktrace.length;

   var var10000: Int;
   while (true) {
      if (element >= var6) {
         var10000 = -1;
         break;
      }

      if (isArtificial((StackTraceElement)lastFrameIndex[element])) {
         var10000 = element;
         break;
      }

      element++;
   }

   val startIndex: Int = var10000 + 1;
   val var9: Int = recoveredStacktrace.length - 1;
   var var10: Int = recoveredStacktrace.length - 1;
   if (startIndex <= var9) {
      while (true) {
         if (elementWiseEquals(recoveredStacktrace[var10], result.getLast() as StackTraceElement)) {
            result.removeLast();
         }

         result.addFirst(recoveredStacktrace[var10]);
         if (var10 == startIndex) {
            break;
         }

         var10--;
      }
   }
}

internal suspend inline fun recoverAndThrow(exception: Throwable): Nothing {
   if (!DebugKt.getRECOVER_STACK_TRACES()) {
      throw exception;
   } else if (`$completion` !is CoroutineStackFrame) {
      throw exception;
   } else {
      throw access$recoverFromStackFrame(exception, `$completion` as CoroutineStackFrame);
   }
}

fun `recoverAndThrow$$forInline`(exception: java.lang.Throwable, `$completion`: Continuation<?>): Any {
   if (!DebugKt.getRECOVER_STACK_TRACES()) {
      throw exception;
   } else {
      InlineMarker.mark(0);
      val it: Continuation = `$completion`;
      if (`$completion` !is CoroutineStackFrame) {
         throw exception;
      } else {
         throw access$recoverFromStackFrame(exception, it as CoroutineStackFrame);
      }
   }
}

@PublishedApi
internal inline fun <E : Throwable> unwrap(exception: E): E {
   return (E)(if (!DebugKt.getRECOVER_STACK_TRACES()) exception else unwrapImpl(exception));
}

@PublishedApi
internal fun <E : Throwable> unwrapImpl(exception: E): E {
   val cause: java.lang.Throwable = exception.getCause();
   if (cause != null && cause.getClass() == exception.getClass()) {
      val `$this$any$iv`: Array<Any> = exception.getStackTrace();
      var var4: Int = 0;
      val var5: Int = `$this$any$iv`.length;

      var var10000: Boolean;
      while (true) {
         if (var4 >= var5) {
            var10000 = false;
            break;
         }

         if (isArtificial((StackTraceElement)`$this$any$iv`[var4])) {
            var10000 = true;
            break;
         }

         var4++;
      }

      return (E)(if (var10000) cause else exception);
   } else {
      return (E)exception;
   }
}

private fun createStackTrace(continuation: CoroutineStackFrame): ArrayDeque<StackTraceElement> {
   val stack: ArrayDeque = new ArrayDeque();
   var var10000: StackTraceElement = continuation.getStackTraceElement();
   if (var10000 != null) {
      stack.add(var10000);
   }

   var last: CoroutineStackFrame = continuation;

   while (true) {
      val var7: CoroutineStackFrame = if (last is CoroutineStackFrame) last else null;
      if ((if (last is CoroutineStackFrame) last else null) == null) {
         break;
      }

      val var8: CoroutineStackFrame = var7.getCallerFrame();
      if (var8 == null) {
         break;
      }

      last = var8;
      var10000 = var8.getStackTraceElement();
      if (var10000 != null) {
         stack.add(var10000);
      }
   }

   return stack;
}

internal fun StackTraceElement.isArtificial(): Boolean {
   return StringsKt.startsWith$default(`$this$isArtificial`.getClassName(), CoroutineDebuggingKt.getARTIFICIAL_FRAME_PACKAGE_NAME(), false, 2, null);
}

private fun Array<StackTraceElement>.firstFrameIndex(methodName: String): Int {
   val `$this$indexOfFirst$iv`: Array<Any> = `$this$firstFrameIndex`;
   var `index$iv`: Int = 0;
   val var5: Int = `$this$firstFrameIndex`.length;

   var var10000: Int;
   while (true) {
      if (`index$iv` >= var5) {
         var10000 = -1;
         break;
      }

      if (methodName == `$this$indexOfFirst$iv`[`index$iv`].getClassName()) {
         var10000 = `index$iv`;
         break;
      }

      `index$iv`++;
   }

   return var10000;
}

private fun StackTraceElement.elementWiseEquals(e: StackTraceElement): Boolean {
   return `$this$elementWiseEquals`.getLineNumber() == e.getLineNumber()
      && `$this$elementWiseEquals`.getMethodName() == e.getMethodName()
      && `$this$elementWiseEquals`.getFileName() == e.getFileName()
      && `$this$elementWiseEquals`.getClassName() == e.getClassName();
}

internal fun Throwable.initCause(cause: Throwable) {
   `$this$initCause`.initCause(cause);
}

@JvmSynthetic
fun `access$recoverFromStackFrame`(exception: java.lang.Throwable, continuation: CoroutineStackFrame): java.lang.Throwable {
   return recoverFromStackFrame(exception, continuation);
}
