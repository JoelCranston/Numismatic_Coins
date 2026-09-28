package kotlinx.coroutines

import java.util.concurrent.Future

@JvmSynthetic
internal class JobKt__FutureKt {
   @Deprecated(message = "This function does not do what its name implies: it will not cancel the future if just cancel() was called.", replaceWith = @ReplaceWith(expression = "this.invokeOnCancellation { future.cancel(false) }", imports = []), level = DeprecationLevel.WARNING)
   @JvmStatic
   public fun CancellableContinuation<*>.cancelFutureOnCancellation(future: Future<*>) {
      CancellableContinuationKt.invokeOnCancellation(`$this$cancelFutureOnCancellation`, new PublicCancelFutureOnCancel(future));
   }
}
