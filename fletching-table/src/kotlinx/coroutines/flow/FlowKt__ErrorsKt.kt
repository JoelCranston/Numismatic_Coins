package kotlinx.coroutines.flow

import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowKt__ErrorsKt.catch..inlined.unsafeFlow.1
import kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.2
import kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry.3
import kotlinx.coroutines.internal.StackTraceRecoveryKt

@SourceDebugExtension(["SMAP\nErrors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Errors.kt\nkotlinx/coroutines/flow/FlowKt__ErrorsKt\n+ 2 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,220:1\n105#2:221\n105#2:223\n1#3:222\n159#4:224\n*S KotlinDebug\n*F\n+ 1 Errors.kt\nkotlinx/coroutines/flow/FlowKt__ErrorsKt\n*L\n54#1:221\n128#1:223\n217#1:224\n*E\n"])
@JvmSynthetic
internal class FlowKt__ErrorsKt {
   @JvmStatic
   public fun <T> Flow<T>.catch(action: (FlowCollector<T>, Throwable, Continuation<Unit>) -> Any?): Flow<T> {
      return new 1(`$this$catch`, action);
   }

   @JvmStatic
   public fun <T> Flow<T>.retry(
      retries: Long = java.lang.Long.MAX_VALUE,
      predicate: (Throwable, Continuation<Boolean>) -> Any? = (new kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry.1(null)) as Function2
   ): Flow<T> {
      if (retries <= 0L) {
         throw new IllegalArgumentException(("Expected positive amount of retries, but had $retries").toString());
      } else {
         return FlowKt.retryWhen(`$this$retry`, new 3(retries, predicate, null));
      }
   }

   @JvmStatic
   public fun <T> Flow<T>.retryWhen(predicate: (FlowCollector<T>, Throwable, Long, Continuation<Boolean>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__ErrorsKt.retryWhen..inlined.unsafeFlow.1(`$this$retryWhen`, predicate);
   }

   @JvmStatic
   internal suspend fun <T> Flow<T>.catchImpl(collector: FlowCollector<T>): Throwable? {
      var `$continuation`: Continuation;
      label61: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label61;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl.1(`$completion`);
      }

      var e: java.lang.Throwable;
      var fromDownstream: java.lang.Throwable;
      label54: {
         val `$result`: Any = `$continuation`.result;
         val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var fromDownstreamx: Ref.ObjectRef;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               fromDownstreamx = new Ref.ObjectRef();

               var var10000: Any;
               try {
                  val var10001: FlowCollector = new 2(collector, fromDownstreamx);
                  `$continuation`.L$0 = fromDownstreamx;
                  `$continuation`.label = 1;
                  var10000 = `$this$catchImpl`.collect(var10001, `$continuation`);
               } catch (var10: java.lang.Throwable) {
                  e = var10;
                  fromDownstream = fromDownstreamx.element as java.lang.Throwable;
                  if (isSameExceptionAs$FlowKt__ErrorsKt(var10, fromDownstreamx.element as java.lang.Throwable)) {
                     throw var10;
                  }
                  break label54;
               }

               if (var10000 === var8) {
                  return var8;
               }
               break;
            case 1:
               fromDownstreamx = `$continuation`.L$0 as Ref.ObjectRef;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  break;
               } catch (var11: java.lang.Throwable) {
                  e = var11;
                  fromDownstream = (`$continuation`.L$0 as Ref.ObjectRef).element as java.lang.Throwable;
                  if (isSameExceptionAs$FlowKt__ErrorsKt(var11, (`$continuation`.L$0 as Ref.ObjectRef).element as java.lang.Throwable)) {
                     throw var11;
                  }
                  break label54;
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            return null;
         } catch (var9: java.lang.Throwable) {
            e = var9;
            fromDownstream = fromDownstreamx.element as java.lang.Throwable;
            if (isSameExceptionAs$FlowKt__ErrorsKt(var9, fromDownstreamx.element as java.lang.Throwable)) {
               throw var9;
            }
         }
      }

      if (isCancellationCause$FlowKt__ErrorsKt(e, `$continuation`.getContext())) {
         throw e;
      } else if (fromDownstream == null) {
         return e;
      } else if (e is CancellationException) {
         ExceptionsKt.addSuppressed(fromDownstream, e);
         throw fromDownstream;
      } else {
         ExceptionsKt.addSuppressed(e, fromDownstream);
         throw e;
      }
   }

   @JvmStatic
   private fun Throwable.isCancellationCause(coroutineContext: CoroutineContext): Boolean {
      val job: Job = coroutineContext.get(Job.Key);
      return job != null && job.isCancelled() && isSameExceptionAs$FlowKt__ErrorsKt(`$this$isCancellationCause`, job.getCancellationException());
   }

   @JvmStatic
   private fun Throwable.isSameExceptionAs(other: Throwable?): Boolean {
      return other != null
         && (if (!DebugKt.getRECOVER_STACK_TRACES()) other else StackTraceRecoveryKt.unwrapImpl(other))
            == (if (!DebugKt.getRECOVER_STACK_TRACES()) `$this$isSameExceptionAs` else StackTraceRecoveryKt.unwrapImpl(`$this$isSameExceptionAs`));
   }
}
