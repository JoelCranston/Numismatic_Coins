package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.flow.FlowKt__LogicKt.any.1
import kotlinx.coroutines.flow.internal.AbortFlowException
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt

@SourceDebugExtension(["SMAP\nLogic.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Logic.kt\nkotlinx/coroutines/flow/FlowKt__LogicKt\n+ 2 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt\n*L\n1#1,108:1\n124#2,17:109\n124#2,17:126\n*S KotlinDebug\n*F\n+ 1 Logic.kt\nkotlinx/coroutines/flow/FlowKt__LogicKt\n*L\n36#1:109,17\n73#1:126,17\n*E\n"])
@JvmSynthetic
internal class FlowKt__LogicKt {
   @JvmStatic
   public suspend fun <T> Flow<T>.any(predicate: (T, Continuation<Boolean>) -> Any?): Boolean {
      var `$continuation`: Continuation;
      label40: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label40;
            }
         }

         `$continuation` = new 1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var found: Ref.BooleanRef;
      var `collector$iv`: kotlinx.coroutines.flow.FlowKt__LogicKt.any..inlined.collectWhile.1;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            found = new Ref.BooleanRef();
            val `$this$collectWhile$iv`: Flow = `$this$any`;
            `collector$iv` = new kotlinx.coroutines.flow.FlowKt__LogicKt.any..inlined.collectWhile.1(predicate, found);

            var var10000: Any;
            try {
               val var10001: FlowCollector = `collector$iv`;
               `$continuation`.L$0 = found;
               `$continuation`.L$1 = `collector$iv`;
               `$continuation`.label = 1;
               var10000 = `$this$collectWhile$iv`.collect(var10001, `$continuation`);
            } catch (var12: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var12, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return Boxing.boxBoolean(found.element);
            }

            if (var10000 === var10) {
               return var10;
            }
            break;
         case 1:
            `collector$iv` = `$continuation`.L$1 as kotlinx.coroutines.flow.FlowKt__LogicKt.any..inlined.collectWhile.1;
            found = `$continuation`.L$0 as Ref.BooleanRef;

            try {
               ResultKt.throwOnFailure(`$result`);
               break;
            } catch (var13: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var13, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return Boxing.boxBoolean(found.element);
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         ;
      } catch (var11: AbortFlowException) {
         FlowExceptions_commonKt.checkOwnership(var11, `collector$iv`);
         JobKt.ensureActive(`$continuation`.getContext());
      }

      return Boxing.boxBoolean(found.element);
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.all(predicate: (T, Continuation<Boolean>) -> Any?): Boolean {
      var `$continuation`: Continuation;
      label48: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__LogicKt.all.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__LogicKt.all.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__LogicKt.all.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label48;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__LogicKt.all.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var foundCounterExample: Ref.BooleanRef;
      var `collector$iv`: kotlinx.coroutines.flow.FlowKt__LogicKt.all..inlined.collectWhile.1;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            foundCounterExample = new Ref.BooleanRef();
            val `$this$collectWhile$iv`: Flow = `$this$all`;
            `collector$iv` = new kotlinx.coroutines.flow.FlowKt__LogicKt.all..inlined.collectWhile.1(predicate, foundCounterExample);

            var var10000: Any;
            try {
               val var10001: FlowCollector = `collector$iv`;
               `$continuation`.L$0 = foundCounterExample;
               `$continuation`.L$1 = `collector$iv`;
               `$continuation`.label = 1;
               var10000 = `$this$collectWhile$iv`.collect(var10001, `$continuation`);
            } catch (var12: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var12, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return Boxing.boxBoolean(!foundCounterExample.element);
            }

            if (var10000 === var10) {
               return var10;
            }
            break;
         case 1:
            `collector$iv` = `$continuation`.L$1 as kotlinx.coroutines.flow.FlowKt__LogicKt.all..inlined.collectWhile.1;
            foundCounterExample = `$continuation`.L$0 as Ref.BooleanRef;

            try {
               ResultKt.throwOnFailure(`$result`);
               break;
            } catch (var13: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var13, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return Boxing.boxBoolean(!foundCounterExample.element);
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         ;
      } catch (var11: AbortFlowException) {
         FlowExceptions_commonKt.checkOwnership(var11, `collector$iv`);
         JobKt.ensureActive(`$continuation`.getContext());
      }

      return Boxing.boxBoolean(!foundCounterExample.element);
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.none(predicate: (T, Continuation<Boolean>) -> Any?): Boolean {
      var `$continuation`: Continuation;
      label25: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__LogicKt.none.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__LogicKt.none.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__LogicKt.none.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label25;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__LogicKt.none.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.label = 1;
            var10000 = FlowKt.any(`$this$none`, predicate, `$continuation`);
            if (var10000 === var5) {
               return var5;
            }
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return Boxing.boxBoolean(!var10000 as java.lang.Boolean);
   }
}
