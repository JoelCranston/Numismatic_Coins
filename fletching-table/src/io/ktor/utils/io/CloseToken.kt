package io.ktor.utils.io

import io.ktor.utils.io.CloseToken.wrapCause.1
import java.util.concurrent.CancellationException
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CopyableThrowable

@SourceDebugExtension(["SMAP\nCloseToken.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CloseToken.kt\nio/ktor/utils/io/CloseToken\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,28:1\n1#2:29\n*E\n"])
internal class CloseToken(origin: Throwable?) {
   private final val origin: Throwable?

   init {
      this.origin = origin;
   }

   public fun wrapCause(wrap: (Throwable) -> Throwable = 1.INSTANCE as Function1): Throwable? {
      return if (this.origin == null)
         null
         else
         (
            if (this.origin is CopyableThrowable)
               (this.origin as CopyableThrowable).createCopy()
               else
               (
                  if (this.origin is CancellationException)
                     kotlinx.coroutines.ExceptionsKt.CancellationException((this.origin as CancellationException).getMessage(), this.origin)
                     else
                     wrap.invoke(this.origin) as java.lang.Throwable
               )
         );
   }

   public fun throwOrNull(wrap: (Throwable) -> Throwable): Unit? {
      val var10000: java.lang.Throwable = this.wrapCause(wrap);
      if (var10000 != null) {
         throw var10000;
      } else {
         return null;
      }
   }
}
