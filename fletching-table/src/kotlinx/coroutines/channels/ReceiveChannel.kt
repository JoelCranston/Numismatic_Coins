package kotlinx.coroutines.channels

import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.internal.LowPriorityInOverloadResolution
import kotlinx.coroutines.channels.ReceiveChannel.receiveOrNull.1
import kotlinx.coroutines.internal.StackTraceRecoveryKt
import kotlinx.coroutines.selects.SelectClause1

public interface ReceiveChannel<E> {
   public val isClosedForReceive: Boolean
   public val isEmpty: Boolean
   public val onReceive: SelectClause1<Any>
   public val onReceiveCatching: SelectClause1<ChannelResult<Any>>

   public open val onReceiveOrNull: SelectClause1<Any?>
      public open get() {
      }


   public abstract suspend fun receive(): Any {
   }

   public abstract suspend fun receiveCatching(): ChannelResult<Any> {
   }

   public abstract fun tryReceive(): ChannelResult<Any> {
   }

   public abstract operator fun iterator(): ChannelIterator<Any> {
   }

   public abstract fun cancel(cause: CancellationException? = ...) {
   }

   @Deprecated(message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = []), level = DeprecationLevel.ERROR)
   public open fun poll(): Any? {
   }

   @Deprecated(message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = []), level = DeprecationLevel.ERROR)
   @LowPriorityInOverloadResolution
   public open suspend fun receiveOrNull(): Any? {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = []), level = DeprecationLevel.ERROR)
      @JvmStatic
      fun <E> poll(`$this`: ReceiveChannel<? extends E>): E {
         val result: Any = `$this`.tryReceive-PtdJZtk();
         if (ChannelResult.isSuccess-impl(result)) {
            return (E)ChannelResult.getOrThrow-impl(result);
         } else {
            val var10000: java.lang.Throwable = ChannelResult.exceptionOrNull-impl(result);
            if (var10000 == null) {
               return null;
            } else {
               throw StackTraceRecoveryKt.recoverStackTrace(var10000);
            }
         }
      }

      /** @deprecated */
      @Deprecated(message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = []), level = DeprecationLevel.ERROR)
      @LowPriorityInOverloadResolution
      @JvmStatic
      fun <E> receiveOrNull(`$this`: ReceiveChannel<? extends E>, `$completion`: Continuation<? super E>): Any {
         var `$continuation`: Continuation;
         label20: {
            if (`$completion` is 1) {
               `$continuation` = `$completion` as 1;
               if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
                  `$continuation`.label -= Integer.MIN_VALUE;
                  break label20;
               }
            }

            `$continuation` = new 1(`$completion`);
         }

         val `$result`: Any = `$continuation`.result;
         val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var var10000: Any;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               `$continuation`.label = 1;
               var10000 = `$this`.receiveCatching-JP2dKIU(`$continuation`);
               if (var10000 === var4) {
                  return var4;
               }
               break;
            case 1:
               ResultKt.throwOnFailure(`$result`);
               var10000 = (`$result` as ChannelResult).unbox-impl();
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         return ChannelResult.getOrNull-impl(var10000);
      }

      /** @deprecated */
      @JvmStatic
      fun <E> getOnReceiveOrNull(`$this`: ReceiveChannel<? extends E>): SelectClause1<E> {
         return (`$this` as BufferedChannel).getOnReceiveOrNull();
      }
   }
}
