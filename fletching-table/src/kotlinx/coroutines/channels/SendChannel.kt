package kotlinx.coroutines.channels

import kotlinx.coroutines.internal.StackTraceRecoveryKt
import kotlinx.coroutines.selects.SelectClause2

public interface SendChannel<E> {
   public val isClosedForSend: Boolean
   public val onSend: SelectClause2<Any, SendChannel<Any>>

   public abstract suspend fun send(element: Any) {
   }

   public abstract fun trySend(element: Any): ChannelResult<Unit> {
   }

   public abstract fun close(cause: Throwable? = ...): Boolean {
   }

   public abstract fun invokeOnClose(handler: (Throwable?) -> Unit) {
   }

   @Deprecated(message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = []), level = DeprecationLevel.ERROR)
   public open fun offer(element: Any): Boolean {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = []), level = DeprecationLevel.ERROR)
      @JvmStatic
      fun <E> offer(`$this`: SendChannel<? super E>, element: E): Boolean {
         val result: Any = `$this`.trySend-JP2dKIU(element);
         if (ChannelResult.isSuccess-impl(result)) {
            return true;
         } else {
            val var10000: java.lang.Throwable = ChannelResult.exceptionOrNull-impl(result);
            if (var10000 == null) {
               return false;
            } else {
               throw StackTraceRecoveryKt.recoverStackTrace(var10000);
            }
         }
      }
   }
}
