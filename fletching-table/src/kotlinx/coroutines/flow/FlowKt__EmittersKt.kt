package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.flow.FlowKt__EmittersKt.transform.1

@SourceDebugExtension(["SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 2 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,218:1\n105#2:219\n105#2:220\n105#2:221\n105#2:222\n*S KotlinDebug\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n46#1:219\n72#1:220\n142#1:221\n177#1:222\n*E\n"])
@JvmSynthetic
internal class FlowKt__EmittersKt {
   @JvmStatic
   public inline fun <T, R> Flow<T>.transform(crossinline transform: (FlowCollector<R>, T, Continuation<Unit>) -> Any?): Flow<R> {
      return FlowKt.flow(new 1(`$this$transform`, transform, null));
   }

   @PublishedApi
   @JvmStatic
   internal inline fun <T, R> Flow<T>.unsafeTransform(crossinline transform: (FlowCollector<R>, T, Continuation<Unit>) -> Any?): Flow<R> {
      return new kotlinx.coroutines.flow.FlowKt__EmittersKt.unsafeTransform..inlined.unsafeFlow.1(`$this$unsafeTransform`, transform);
   }

   @JvmStatic
   public fun <T> Flow<T>.onStart(action: (FlowCollector<T>, Continuation<Unit>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__EmittersKt.onStart..inlined.unsafeFlow.1(action, `$this$onStart`);
   }

   @JvmStatic
   public fun <T> Flow<T>.onCompletion(action: (FlowCollector<T>, Throwable?, Continuation<Unit>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__EmittersKt.onCompletion..inlined.unsafeFlow.1(`$this$onCompletion`, action);
   }

   @JvmStatic
   public fun <T> Flow<T>.onEmpty(action: (FlowCollector<T>, Continuation<Unit>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__EmittersKt.onEmpty..inlined.unsafeFlow.1(`$this$onEmpty`, action);
   }

   @JvmStatic
   internal fun FlowCollector<*>.ensureActive() {
      if (`$this$ensureActive` is ThrowingCollector) {
         throw (`$this$ensureActive` as ThrowingCollector).e;
      }
   }

   @JvmStatic
   private suspend fun <T> FlowCollector<T>.invokeSafely(action: (FlowCollector<T>, Throwable?, Continuation<Unit>) -> Any?, cause: Throwable?) {
      var `$continuation`: Continuation;
      label54: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label54;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__EmittersKt.invokeSafely.1(`$completion`);
      }

      var e: java.lang.Throwable;
      label57: {
         val `$result`: Any = `$continuation`.result;
         val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);

               var var10000: Any;
               try {
                  `$continuation`.L$0 = cause;
                  `$continuation`.label = 1;
                  var10000 = action.invoke(`$this$invokeSafely`, cause, `$continuation`);
               } catch (var9: java.lang.Throwable) {
                  e = var9;
                  if (cause == null) {
                     throw var9;
                  }
                  break label57;
               }

               if (var10000 === var7) {
                  return var7;
               }
               break;
            case 1:
               cause = `$continuation`.L$0 as java.lang.Throwable;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  break;
               } catch (var10: java.lang.Throwable) {
                  e = var10;
                  if (`$continuation`.L$0 as java.lang.Throwable == null) {
                     throw var10;
                  }
                  break label57;
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            ;
         } catch (var8: java.lang.Throwable) {
            e = var8;
            if (cause == null) {
               throw var8;
            }
            break label57;
         }

         return Unit.INSTANCE;
      }

      if (cause != e) {
         ExceptionsKt.addSuppressed(e, cause);
      }

      throw e;
   }
}
