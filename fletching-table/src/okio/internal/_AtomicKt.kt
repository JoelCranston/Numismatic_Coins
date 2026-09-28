package okio.internal

import java.util.concurrent.atomic.AtomicInteger

internal fun AtomicInteger.setBitsOrZero(bits: Int): Int {
   val current: Int;
   do {
      current = `$this$setBitsOrZero`.get();
      if ((current and bits) != 0) {
         return 0;
      }
   } while (!$this$setBitsOrZero.compareAndSet(current, current | bits));

   return current or bits;
}
