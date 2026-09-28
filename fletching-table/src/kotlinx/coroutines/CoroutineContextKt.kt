package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Ref
import kotlinx.coroutines.internal.ThreadContextKt

internal final val coroutineName: String?
   internal final get() {
      if (!DebugKt.getDEBUG()) {
         return null;
      } else {
         val var10000: CoroutineId = `$this$coroutineName`.get(CoroutineId.Key);
         if (var10000 == null) {
            return null;
         } else {
            label16: {
               val var3: CoroutineName = `$this$coroutineName`.get(CoroutineName.Key);
               if (var3 != null) {
                  var4 = var3.getName();
                  if (var4 != null) {
                     break label16;
                  }
               }

               var4 = "coroutine";
            }

            return "$var4#${var10000.getId()}";
         }
      }
   }


private const val DEBUG_THREAD_NAME_SEPARATOR: String = " @"

@ExperimentalCoroutinesApi
public fun CoroutineScope.newCoroutineContext(context: CoroutineContext): CoroutineContext {
   val combined: CoroutineContext = foldCopies(`$this$newCoroutineContext`.getCoroutineContext(), context, true);
   val debug: CoroutineContext = if (DebugKt.getDEBUG()) combined.plus(new CoroutineId(DebugKt.getCOROUTINE_ID().incrementAndGet())) else combined;
   return if (combined != Dispatchers.getDefault() && combined.get(ContinuationInterceptor.Key) == null) debug.plus(Dispatchers.getDefault()) else debug;
}

@InternalCoroutinesApi
public fun CoroutineContext.newCoroutineContext(addedContext: CoroutineContext): CoroutineContext {
   return if (!hasCopyableElements(addedContext))
      `$this$newCoroutineContext`.plus(addedContext)
      else
      foldCopies(`$this$newCoroutineContext`, addedContext, false);
}

private fun CoroutineContext.hasCopyableElements(): Boolean {
   return `$this$hasCopyableElements`.fold(false, CoroutineContextKt::hasCopyableElements$lambda$0);
}

private fun foldCopies(originalContext: CoroutineContext, appendContext: CoroutineContext, isNewCoroutine: Boolean): CoroutineContext {
   val hasElementsLeft: Boolean = hasCopyableElements(originalContext);
   val hasElementsRight: Boolean = hasCopyableElements(appendContext);
   if (!hasElementsLeft && !hasElementsRight) {
      return originalContext.plus(appendContext);
   } else {
      val leftoverContext: Ref.ObjectRef = new Ref.ObjectRef();
      leftoverContext.element = (T)appendContext;
      val folded: CoroutineContext = originalContext.fold(EmptyCoroutineContext.INSTANCE, CoroutineContextKt::foldCopies$lambda$1);
      if (hasElementsRight) {
         leftoverContext.element = (T)(leftoverContext.element as CoroutineContext)
            .fold(EmptyCoroutineContext.INSTANCE, CoroutineContextKt::foldCopies$lambda$2);
      }

      return folded.plus(leftoverContext.element as CoroutineContext);
   }
}

internal inline fun <T> withCoroutineContext(context: CoroutineContext, countOrElement: Any?, block: () -> T): T {
   label14: {
      val oldValue: Any = ThreadContextKt.updateThreadContext(context, countOrElement);

      try {
         val var5: Any = block.invoke();
      } catch (var7: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         ThreadContextKt.restoreThreadContext(context, oldValue);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      ThreadContextKt.restoreThreadContext(context, oldValue);
      InlineMarker.finallyEnd(1);
   }
}

internal inline fun <T> withContinuationContext(continuation: Continuation<*>, countOrElement: Any?, block: () -> T): T {
   label51: {
      val context: CoroutineContext = continuation.getContext();
      val oldValue: Any = ThreadContextKt.updateThreadContext(context, countOrElement);
      val undispatchedCompletion: UndispatchedCoroutine = if (oldValue != ThreadContextKt.NO_THREAD_ELEMENTS)
         updateUndispatchedCompletion(continuation, context, oldValue)
         else
         null;

      try {
         val var7: Any = block.invoke();
      } catch (var9: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         if (undispatchedCompletion == null || undispatchedCompletion.clearThreadContext()) {
            ThreadContextKt.restoreThreadContext(context, oldValue);
         }

         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      if (undispatchedCompletion == null || undispatchedCompletion.clearThreadContext()) {
         ThreadContextKt.restoreThreadContext(context, oldValue);
      }

      InlineMarker.finallyEnd(1);
   }
}

internal fun Continuation<*>.updateUndispatchedCompletion(context: CoroutineContext, oldValue: Any?): UndispatchedCoroutine<*>? {
   if (`$this$updateUndispatchedCompletion` !is CoroutineStackFrame) {
      return null;
   } else if (context.get(UndispatchedMarker.INSTANCE) == null) {
      return null;
   } else {
      val completion: UndispatchedCoroutine = undispatchedCompletion(`$this$updateUndispatchedCompletion` as CoroutineStackFrame);
      if (completion != null) {
         completion.saveThreadContext(context, oldValue);
      }

      return completion;
   }
}

internal tailrec fun CoroutineStackFrame.undispatchedCompletion(): UndispatchedCoroutine<*>? {
   while (!($this$undispatchedCompletion instanceof DispatchedCoroutine)) {
      val var10000: CoroutineStackFrame = `$this$undispatchedCompletion`.getCallerFrame();
      if (var10000 == null) {
         return null;
      }

      if (var10000 is UndispatchedCoroutine) {
         return var10000 as UndispatchedCoroutine<?>;
      }

      `$this$undispatchedCompletion` = var10000;
   }

   return null;
}

fun `hasCopyableElements$lambda$0`(result: Boolean, it: CoroutineContext.Element): Boolean {
   return result || it is CopyableThreadContextElement;
}

fun `foldCopies$lambda$1`(`$leftoverContext`: Ref.ObjectRef, `$isNewCoroutine`: Boolean, result: CoroutineContext, element: CoroutineContext.Element): CoroutineContext {
   if (element !is CopyableThreadContextElement) {
      return result.plus(element);
   } else {
      val newElement: CoroutineContext.Element = (`$leftoverContext`.element as CoroutineContext)
         .get((CoroutineContext.Key<CoroutineContext.Element>)element.getKey());
      if (newElement == null) {
         return result.plus(if (`$isNewCoroutine`) (element as CopyableThreadContextElement).copyForChild() else element as CopyableThreadContextElement);
      } else {
         `$leftoverContext`.element = (T)(`$leftoverContext`.element as CoroutineContext).minusKey(element.getKey());
         return result.plus((element as CopyableThreadContextElement).mergeForChild(newElement));
      }
   }
}

fun `foldCopies$lambda$2`(result: CoroutineContext, element: CoroutineContext.Element): CoroutineContext {
   return if (element is CopyableThreadContextElement) result.plus((element as CopyableThreadContextElement).copyForChild()) else result.plus(element);
}
