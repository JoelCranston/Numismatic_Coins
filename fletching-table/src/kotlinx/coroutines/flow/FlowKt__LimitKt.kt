package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.flow.FlowKt__LimitKt.drop..inlined.unsafeFlow.1
import kotlinx.coroutines.flow.internal.AbortFlowException
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt

@SourceDebugExtension(["SMAP\nLimit.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,141:1\n1#2:142\n105#3:143\n105#3:144\n105#3:145\n105#3:146\n*S KotlinDebug\n*F\n+ 1 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt\n*L\n19#1:143\n30#1:144\n49#1:145\n81#1:146\n*E\n"])
@JvmSynthetic
internal class FlowKt__LimitKt {
   @JvmStatic
   public fun <T> Flow<T>.drop(count: Int): Flow<T> {
      if (count < 0) {
         throw new IllegalArgumentException(("Drop count should be non-negative, but had $count").toString());
      } else {
         return new 1(`$this$drop`, count);
      }
   }

   @JvmStatic
   public fun <T> Flow<T>.dropWhile(predicate: (T, Continuation<Boolean>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__LimitKt.dropWhile..inlined.unsafeFlow.1(`$this$dropWhile`, predicate);
   }

   @JvmStatic
   public fun <T> Flow<T>.take(count: Int): Flow<T> {
      if (count <= 0) {
         throw new IllegalArgumentException(("Requested element count $count should be positive").toString());
      } else {
         return new kotlinx.coroutines.flow.FlowKt__LimitKt.take..inlined.unsafeFlow.1(`$this$take`, count);
      }
   }

   @JvmStatic
   private suspend fun <T> FlowCollector<T>.emitAbort(value: T, ownershipMarker: Any) {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__LimitKt.emitAbort.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__LimitKt.emitAbort.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__LimitKt.emitAbort.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__LimitKt.emitAbort.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = ownershipMarker;
            `$continuation`.label = 1;
            if (`$this$emitAbort`.emit(value, `$continuation`) === var6) {
               return var6;
            }
            break;
         case 1:
            ownershipMarker = `$continuation`.L$0;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      throw new AbortFlowException(ownershipMarker);
   }

   @JvmStatic
   public fun <T> Flow<T>.takeWhile(predicate: (T, Continuation<Boolean>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__LimitKt.takeWhile..inlined.unsafeFlow.1(`$this$takeWhile`, predicate);
   }

   @JvmStatic
   public fun <T, R> Flow<T>.transformWhile(transform: (FlowCollector<R>, T, Continuation<Boolean>) -> Any?): Flow<R> {
      return FlowKt.flow(new kotlinx.coroutines.flow.FlowKt__LimitKt.transformWhile.1(`$this$transformWhile`, transform, null));
   }

   @JvmStatic
   internal suspend inline fun <T> Flow<T>.collectWhile(crossinline predicate: (T, Continuation<Boolean>) -> Any?) {
      var `$continuation`: Continuation;
      label40: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label40;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var collector: kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            collector = new kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1(predicate);

            var var10000: Any;
            try {
               val var10001: FlowCollector = collector;
               `$continuation`.L$0 = collector;
               `$continuation`.label = 1;
               var10000 = `$this$collectWhile`.collect(var10001, `$continuation`);
            } catch (var10: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var10, collector);
               JobKt.ensureActive(`$continuation`.getContext());
               return Unit.INSTANCE;
            }

            if (var10000 === var8) {
               return var8;
            }
            break;
         case 1:
            collector = `$continuation`.L$0 as kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1;

            try {
               ResultKt.throwOnFailure(`$result`);
               break;
            } catch (var11: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var11, `$continuation`.L$0 as kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1);
               JobKt.ensureActive(`$continuation`.getContext());
               return Unit.INSTANCE;
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         ;
      } catch (var9: AbortFlowException) {
         FlowExceptions_commonKt.checkOwnership(var9, collector);
         JobKt.ensureActive(`$continuation`.getContext());
      }

      return Unit.INSTANCE;
   }

   @JvmStatic
   fun <T> Flow<? extends T>.`collectWhile$$forInline`(
      predicate: (T?, Continuation<? super java.lang.Boolean>?) -> Any, `$completion`: Continuation<? super Unit>
   ): Any {
      val collector: kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1 = new kotlinx.coroutines.flow.FlowKt__LimitKt.collectWhile.collector.1(
         predicate
      );

      try {
         val var10001: FlowCollector = collector;
         InlineMarker.mark(0);
         `$this$collectWhile`.collect(var10001, `$completion`);
         InlineMarker.mark(1);
      } catch (var6: AbortFlowException) {
         FlowExceptions_commonKt.checkOwnership(var6, collector);
         InlineMarker.mark(3);
         JobKt.ensureActive(null.getContext());
      }

      return Unit.INSTANCE;
   }
}
