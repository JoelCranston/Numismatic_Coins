package kotlinx.coroutines.flow

import java.util.NoSuchElementException
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.flow.FlowKt__ReduceKt.first.3
import kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce.1
import kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce.2
import kotlinx.coroutines.flow.internal.AbortFlowException
import kotlinx.coroutines.flow.internal.FlowExceptions_commonKt
import kotlinx.coroutines.flow.internal.NullSurrogateKt

@SourceDebugExtension(["SMAP\nReduce.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Reduce.kt\nkotlinx/coroutines/flow/FlowKt__ReduceKt\n+ 2 Limit.kt\nkotlinx/coroutines/flow/FlowKt__LimitKt\n*L\n1#1,168:1\n124#2,17:169\n124#2,17:186\n124#2,17:203\n124#2,17:220\n124#2,17:237\n*S KotlinDebug\n*F\n+ 1 Reduce.kt\nkotlinx/coroutines/flow/FlowKt__ReduceKt\n*L\n68#1:169,17\n88#1:186,17\n102#1:203,17\n120#1:220,17\n133#1:237,17\n*E\n"])
@JvmSynthetic
internal class FlowKt__ReduceKt {
   @JvmStatic
   public suspend fun <S, T : S> Flow<T>.reduce(operation: (S, T, Continuation<S>) -> Any?): S {
      var `$continuation`: Continuation;
      label24: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label24;
            }
         }

         `$continuation` = new 1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var accumulator: Ref.ObjectRef;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            accumulator = new Ref.ObjectRef();
            accumulator.element = (T)NullSurrogateKt.NULL;
            val var10001: FlowCollector = new 2(accumulator, operation);
            `$continuation`.L$0 = accumulator;
            `$continuation`.label = 1;
            if (`$this$reduce`.collect(var10001, `$continuation`) === var6) {
               return var6;
            }
            break;
         case 1:
            accumulator = `$continuation`.L$0 as Ref.ObjectRef;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (accumulator.element === NullSurrogateKt.NULL) {
         throw new NoSuchElementException("Empty flow can't be reduced");
      } else {
         return accumulator.element;
      }
   }

   @JvmStatic
   public suspend inline fun <T, R> Flow<T>.fold(initial: R, crossinline operation: (R, T, Continuation<R>) -> Any?): R {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var accumulator: Ref.ObjectRef;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            accumulator = new Ref.ObjectRef();
            accumulator.element = (T)initial;
            val var10001: FlowCollector = new kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.2(accumulator, operation);
            `$continuation`.L$0 = accumulator;
            `$continuation`.label = 1;
            if (`$this$fold`.collect(var10001, `$continuation`) === var8) {
               return var8;
            }
            break;
         case 1:
            accumulator = `$continuation`.L$0 as Ref.ObjectRef;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return accumulator.element;
   }

   @JvmStatic
   fun <T, R> Flow<? extends T>.`fold$$forInline`(initial: R, operation: (R?, T?, Continuation<? super R>?) -> Any, `$completion`: Continuation<? super R>): Any {
      val accumulator: Ref.ObjectRef = new Ref.ObjectRef();
      accumulator.element = (T)initial;
      val var10001: FlowCollector = new kotlinx.coroutines.flow.FlowKt__ReduceKt.fold.2(accumulator, operation);
      InlineMarker.mark(0);
      `$this$fold`.collect(var10001, `$completion`);
      InlineMarker.mark(1);
      return accumulator.element;
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.single(): T {
      var `$continuation`: Continuation;
      label24: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ReduceKt.single.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.single.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.single.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label24;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.single.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var result: Ref.ObjectRef;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            result = new Ref.ObjectRef();
            result.element = (T)NullSurrogateKt.NULL;
            val var10001: FlowCollector = new kotlinx.coroutines.flow.FlowKt__ReduceKt.single.2(result);
            `$continuation`.L$0 = result;
            `$continuation`.label = 1;
            if (`$this$single`.collect(var10001, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            result = `$continuation`.L$0 as Ref.ObjectRef;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (result.element === NullSurrogateKt.NULL) {
         throw new NoSuchElementException("Flow is empty");
      } else {
         return result.element;
      }
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.singleOrNull(): T? {
      var `$continuation`: Continuation;
      label48: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label48;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var result: Ref.ObjectRef;
      var `collector$iv`: kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull..inlined.collectWhile.1;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            result = new Ref.ObjectRef();
            result.element = (T)NullSurrogateKt.NULL;
            val `$this$collectWhile$iv`: Flow = `$this$singleOrNull`;
            `collector$iv` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull..inlined.collectWhile.1(result);

            var var10000: Any;
            try {
               val var10001: FlowCollector = `collector$iv`;
               `$continuation`.L$0 = result;
               `$continuation`.L$1 = `collector$iv`;
               `$continuation`.label = 1;
               var10000 = `$this$collectWhile$iv`.collect(var10001, `$continuation`);
            } catch (var11: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var11, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return if (result.element === NullSurrogateKt.NULL) null else result.element;
            }

            if (var10000 === var9) {
               return var9;
            }
            break;
         case 1:
            `collector$iv` = `$continuation`.L$1 as kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull..inlined.collectWhile.1;
            result = `$continuation`.L$0 as Ref.ObjectRef;

            try {
               ResultKt.throwOnFailure(`$result`);
               break;
            } catch (var12: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var12, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return if (result.element === NullSurrogateKt.NULL) null else result.element;
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         ;
      } catch (var10: AbortFlowException) {
         FlowExceptions_commonKt.checkOwnership(var10, `collector$iv`);
         JobKt.ensureActive(`$continuation`.getContext());
      }

      return if (result.element === NullSurrogateKt.NULL) null else result.element;
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.first(): T {
      var `$continuation`: Continuation;
      label47: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ReduceKt.first.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.first.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.first.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label47;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.first.1(`$completion`);
      }

      var result: Ref.ObjectRef;
      label41: {
         val `$result`: Any = `$continuation`.result;
         val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var `collector$iv`: kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.1;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               result = new Ref.ObjectRef();
               result.element = (T)NullSurrogateKt.NULL;
               val `$this$collectWhile$iv`: Flow = `$this$first`;
               `collector$iv` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.1(result);

               var var10000: Any;
               try {
                  val var10001: FlowCollector = `collector$iv`;
                  `$continuation`.L$0 = result;
                  `$continuation`.L$1 = `collector$iv`;
                  `$continuation`.label = 1;
                  var10000 = `$this$collectWhile$iv`.collect(var10001, `$continuation`);
               } catch (var11: AbortFlowException) {
                  FlowExceptions_commonKt.checkOwnership(var11, `collector$iv`);
                  JobKt.ensureActive(`$continuation`.getContext());
                  break label41;
               }

               if (var10000 === var9) {
                  return var9;
               }
               break;
            case 1:
               `collector$iv` = `$continuation`.L$1 as kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.1;
               result = `$continuation`.L$0 as Ref.ObjectRef;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  break;
               } catch (var12: AbortFlowException) {
                  FlowExceptions_commonKt.checkOwnership(var12, `collector$iv`);
                  JobKt.ensureActive(`$continuation`.getContext());
                  break label41;
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            ;
         } catch (var10: AbortFlowException) {
            FlowExceptions_commonKt.checkOwnership(var10, `collector$iv`);
            JobKt.ensureActive(`$continuation`.getContext());
         }
      }

      if (result.element === NullSurrogateKt.NULL) {
         throw new NoSuchElementException("Expected at least one element");
      } else {
         return result.element;
      }
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.first(predicate: (T, Continuation<Boolean>) -> Any?): T {
      var `$continuation`: Continuation;
      label47: {
         if (`$completion` is 3) {
            `$continuation` = `$completion` as 3;
            if (((`$completion` as 3).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label47;
            }
         }

         `$continuation` = new 3(`$completion`);
      }

      var result: Ref.ObjectRef;
      label41: {
         val `$result`: Any = `$continuation`.result;
         val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var `collector$iv`: kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               result = new Ref.ObjectRef();
               result.element = (T)NullSurrogateKt.NULL;
               val `$this$collectWhile$iv`: Flow = `$this$first`;
               `collector$iv` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2(predicate, result);

               var var10000: Any;
               try {
                  val var10001: FlowCollector = `collector$iv`;
                  `$continuation`.L$0 = result;
                  `$continuation`.L$1 = `collector$iv`;
                  `$continuation`.label = 1;
                  var10000 = `$this$collectWhile$iv`.collect(var10001, `$continuation`);
               } catch (var12: AbortFlowException) {
                  FlowExceptions_commonKt.checkOwnership(var12, `collector$iv`);
                  JobKt.ensureActive(`$continuation`.getContext());
                  break label41;
               }

               if (var10000 === var10) {
                  return var10;
               }
               break;
            case 1:
               `collector$iv` = `$continuation`.L$1 as kotlinx.coroutines.flow.FlowKt__ReduceKt.first..inlined.collectWhile.2;
               result = `$continuation`.L$0 as Ref.ObjectRef;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  break;
               } catch (var13: AbortFlowException) {
                  FlowExceptions_commonKt.checkOwnership(var13, `collector$iv`);
                  JobKt.ensureActive(`$continuation`.getContext());
                  break label41;
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
      }

      if (result.element === NullSurrogateKt.NULL) {
         throw new NoSuchElementException("Expected at least one element matching the predicate");
      } else {
         return result.element;
      }
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.firstOrNull(): T? {
      var `$continuation`: Continuation;
      label40: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label40;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var result: Ref.ObjectRef;
      var `collector$iv`: kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.1;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            result = new Ref.ObjectRef();
            val `$this$collectWhile$iv`: Flow = `$this$firstOrNull`;
            `collector$iv` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.1(result);

            var var10000: Any;
            try {
               val var10001: FlowCollector = `collector$iv`;
               `$continuation`.L$0 = result;
               `$continuation`.L$1 = `collector$iv`;
               `$continuation`.label = 1;
               var10000 = `$this$collectWhile$iv`.collect(var10001, `$continuation`);
            } catch (var11: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var11, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return result.element;
            }

            if (var10000 === var9) {
               return var9;
            }
            break;
         case 1:
            `collector$iv` = `$continuation`.L$1 as kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.1;
            result = `$continuation`.L$0 as Ref.ObjectRef;

            try {
               ResultKt.throwOnFailure(`$result`);
               break;
            } catch (var12: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var12, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return result.element;
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         ;
      } catch (var10: AbortFlowException) {
         FlowExceptions_commonKt.checkOwnership(var10, `collector$iv`);
         JobKt.ensureActive(`$continuation`.getContext());
      }

      return result.element;
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.firstOrNull(predicate: (T, Continuation<Boolean>) -> Any?): T? {
      var `$continuation`: Continuation;
      label40: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.3) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.3;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.3).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label40;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull.3(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var result: Ref.ObjectRef;
      var `collector$iv`: kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            result = new Ref.ObjectRef();
            val `$this$collectWhile$iv`: Flow = `$this$firstOrNull`;
            `collector$iv` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2(predicate, result);

            var var10000: Any;
            try {
               val var10001: FlowCollector = `collector$iv`;
               `$continuation`.L$0 = result;
               `$continuation`.L$1 = `collector$iv`;
               `$continuation`.label = 1;
               var10000 = `$this$collectWhile$iv`.collect(var10001, `$continuation`);
            } catch (var12: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var12, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return result.element;
            }

            if (var10000 === var10) {
               return var10;
            }
            break;
         case 1:
            `collector$iv` = `$continuation`.L$1 as kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull..inlined.collectWhile.2;
            result = `$continuation`.L$0 as Ref.ObjectRef;

            try {
               ResultKt.throwOnFailure(`$result`);
               break;
            } catch (var13: AbortFlowException) {
               FlowExceptions_commonKt.checkOwnership(var13, `collector$iv`);
               JobKt.ensureActive(`$continuation`.getContext());
               return result.element;
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

      return result.element;
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.last(): T {
      var `$continuation`: Continuation;
      label24: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ReduceKt.last.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.last.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.last.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label24;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.last.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var result: Ref.ObjectRef;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            result = new Ref.ObjectRef();
            result.element = (T)NullSurrogateKt.NULL;
            val var10001: FlowCollector = new kotlinx.coroutines.flow.FlowKt__ReduceKt.last.2(result);
            `$continuation`.L$0 = result;
            `$continuation`.label = 1;
            if (`$this$last`.collect(var10001, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            result = `$continuation`.L$0 as Ref.ObjectRef;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (result.element === NullSurrogateKt.NULL) {
         throw new NoSuchElementException("Expected at least one element");
      } else {
         return result.element;
      }
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.lastOrNull(): T? {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var result: Ref.ObjectRef;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            result = new Ref.ObjectRef();
            val var10001: FlowCollector = new kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull.2(result);
            `$continuation`.L$0 = result;
            `$continuation`.label = 1;
            if (`$this$lastOrNull`.collect(var10001, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            result = `$continuation`.L$0 as Ref.ObjectRef;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return result.element;
   }
}
