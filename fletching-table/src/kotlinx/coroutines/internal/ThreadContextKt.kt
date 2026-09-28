package kotlinx.coroutines.internal

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.CoroutineContext.Element
import kotlinx.coroutines.ThreadContextElement

internal final val NO_THREAD_ELEMENTS: Symbol = new Symbol("NO_THREAD_ELEMENTS")
private final val countAll: (Any?, Element) -> Any? = ThreadContextKt::countAll$lambda$0
private final val findOne: (ThreadContextElement<*>?, Element) -> ThreadContextElement<*>? = ThreadContextKt::findOne$lambda$1
private final val updateState: (ThreadState, Element) -> ThreadState = ThreadContextKt::updateState$lambda$2

internal fun threadContextElements(context: CoroutineContext): Any {
   val var10000: Any = context.fold(0, countAll);
   return var10000;
}

internal fun updateThreadContext(context: CoroutineContext, countOrElement: Any?): Any? {
   var var10000: Any = countOrElement;
   if (countOrElement == null) {
      var10000 = threadContextElements(context);
   }

   if (var10000 === 0) {
      var10000 = NO_THREAD_ELEMENTS;
   } else if (var10000 is Int) {
      var10000 = context.fold(new ThreadState(context, (var10000 as java.lang.Number).intValue()), updateState);
   } else {
      var10000 = (var10000 as ThreadContextElement).updateThreadContext(context);
   }

   return var10000;
}

internal fun restoreThreadContext(context: CoroutineContext, oldState: Any?) {
   if (oldState != NO_THREAD_ELEMENTS) {
      if (oldState is ThreadState) {
         (oldState as ThreadState).restore(context);
      } else {
         val var10000: Any = context.fold(null, findOne);
         (var10000 as ThreadContextElement).restoreThreadContext(context, oldState);
      }
   }
}

fun `countAll$lambda$0`(countOrElement: Any, element: CoroutineContext.Element): Any {
   if (element is ThreadContextElement) {
      val inCount: Int = if ((countOrElement as? Int) != null) countOrElement as? Int else 1;
      return if (inCount == 0) element else inCount + 1;
   } else {
      return countOrElement;
   }
}

fun `findOne$lambda$1`(found: ThreadContextElement<?>, element: CoroutineContext.Element): ThreadContextElement<?> {
   if (found != null) {
      return found;
   } else {
      return element as? ThreadContextElement;
   }
}

fun `updateState$lambda$2`(state: ThreadState, element: CoroutineContext.Element): ThreadState {
   if (element is ThreadContextElement) {
      state.append(element as ThreadContextElement<?>, (element as ThreadContextElement).updateThreadContext(state.context));
   }

   return state;
}
