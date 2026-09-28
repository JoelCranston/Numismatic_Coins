package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.ScopeCoroutine
import kotlinx.coroutines.internal.ThreadContextKt

@SourceDebugExtension(["SMAP\nCoroutineContext.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineContext.kt\nkotlinx/coroutines/UndispatchedCoroutine\n+ 2 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,319:1\n103#2,13:320\n1#3:333\n*S KotlinDebug\n*F\n+ 1 CoroutineContext.kt\nkotlinx/coroutines/UndispatchedCoroutine\n*L\n265#1:320,13\n*E\n"])
internal class UndispatchedCoroutine<T>(context: CoroutineContext, uCont: Continuation<Any>) : ScopeCoroutine(
      if (context.get(UndispatchedMarker.INSTANCE) == null) context.plus(UndispatchedMarker.INSTANCE) else context, uCont
   ) {
   private final val threadStateToRecover: ThreadLocal<Pair<CoroutineContext, Any?>> = new ThreadLocal()
   private final var threadLocalIsSet: Boolean

   public fun saveThreadContext(context: CoroutineContext, oldValue: Any?) {
      this.threadLocalIsSet = true;
      this.threadStateToRecover.set(TuplesKt.to(context, oldValue));
   }

   public fun clearThreadContext(): Boolean {
      val var1: Boolean = this.threadLocalIsSet && this.threadStateToRecover.get() == null;
      this.threadStateToRecover.remove();
      return !var1;
   }

   public override fun afterCompletionUndispatched() {
      this.clearThreadLocal();
   }

   protected override fun afterResume(state: Any?) {
      label51: {
         this.clearThreadLocal();
         val result: Any = CompletionStateKt.recoverResult(state, this.uCont);
         val `continuation$iv`: Continuation = this.uCont;
         val `context$iv`: CoroutineContext = this.uCont.getContext();
         val `oldValue$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv`, null);
         val `undispatchedCompletion$iv`: UndispatchedCoroutine = if (`oldValue$iv` != ThreadContextKt.NO_THREAD_ELEMENTS)
            CoroutineContextKt.updateUndispatchedCompletion(`continuation$iv`, `context$iv`, `oldValue$iv`)
            else
            null;

         try {
            this.uCont.resumeWith(result);
         } catch (var11: java.lang.Throwable) {
            if (`undispatchedCompletion$iv` == null || `undispatchedCompletion$iv`.clearThreadContext()) {
               ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
            }
         }

         if (`undispatchedCompletion$iv` == null || `undispatchedCompletion$iv`.clearThreadContext()) {
            ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
         }
      }
   }

   private fun clearThreadLocal() {
      if (this.threadLocalIsSet) {
         val var10000: Pair = this.threadStateToRecover.get();
         if (var10000 != null) {
            ThreadContextKt.restoreThreadContext(var10000.component1() as CoroutineContext, var10000.component2());
         }

         this.threadStateToRecover.remove();
      }
   }
}
