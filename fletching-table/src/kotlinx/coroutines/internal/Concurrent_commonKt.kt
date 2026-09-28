package kotlinx.coroutines.internal

import java.util.concurrent.atomic.AtomicReference

internal final var value: T
   internal final get() {
      return (T)`$this$value`.get();
   }

   internal final set(value) {
      `$this$value`.set(value);
   }


internal inline fun <T> AtomicReference<T>.loop(action: (AtomicReference<T>, T) -> Unit) {
   while (true) {
      action.invoke(`$this$loop`, getValue(`$this$loop`));
   }
}
