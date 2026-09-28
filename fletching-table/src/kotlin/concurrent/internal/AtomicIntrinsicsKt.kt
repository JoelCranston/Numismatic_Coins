package kotlin.concurrent.internal

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicIntegerArray
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicLongArray
import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.atomic.AtomicReferenceArray

@SinceKotlin(version = "2.1")
@PublishedApi
internal fun AtomicInteger.compareAndExchange(expected: Int, newValue: Int): Int {
   do {
      val currentValue: Int = `$this$compareAndExchange`.get();
      if (expected != currentValue) {
         return currentValue;
      }
   } while (!$this$compareAndExchange.compareAndSet(expected, newValue));

   return expected;
}

@SinceKotlin(version = "2.1")
@PublishedApi
internal fun AtomicLong.compareAndExchange(expected: Long, newValue: Long): Long {
   do {
      val currentValue: Long = `$this$compareAndExchange`.get();
      if (expected != currentValue) {
         return currentValue;
      }
   } while (!$this$compareAndExchange.compareAndSet(expected, newValue));

   return expected;
}

@SinceKotlin(version = "2.1")
@PublishedApi
internal fun AtomicBoolean.compareAndExchange(expected: Boolean, newValue: Boolean): Boolean {
   do {
      val currentValue: Boolean = `$this$compareAndExchange`.get();
      if (expected != currentValue) {
         return currentValue;
      }
   } while (!$this$compareAndExchange.compareAndSet(expected, newValue));

   return expected;
}

@SinceKotlin(version = "2.1")
@PublishedApi
internal fun <T> AtomicReference<T>.compareAndExchange(expected: T, newValue: T): T {
   do {
      val currentValue: Any = `$this$compareAndExchange`.get();
      if (expected != currentValue) {
         return (T)currentValue;
      }
   } while (!$this$compareAndExchange.compareAndSet(expected, newValue));

   return (T)expected;
}

@SinceKotlin(version = "2.1")
@PublishedApi
internal fun AtomicIntegerArray.compareAndExchange(index: Int, expected: Int, newValue: Int): Int {
   do {
      val currentValue: Int = `$this$compareAndExchange`.get(index);
      if (expected != currentValue) {
         return currentValue;
      }
   } while (!$this$compareAndExchange.compareAndSet(index, expected, newValue));

   return expected;
}

@SinceKotlin(version = "2.1")
@PublishedApi
internal fun AtomicLongArray.compareAndExchange(index: Int, expected: Long, newValue: Long): Long {
   do {
      val currentValue: Long = `$this$compareAndExchange`.get(index);
      if (expected != currentValue) {
         return currentValue;
      }
   } while (!$this$compareAndExchange.compareAndSet(index, expected, newValue));

   return expected;
}

@SinceKotlin(version = "2.1")
@PublishedApi
internal fun <T> AtomicReferenceArray<T>.compareAndExchange(index: Int, expected: T, newValue: T): T {
   do {
      val currentValue: Any = `$this$compareAndExchange`.get(index);
      if (expected != currentValue) {
         return (T)currentValue;
      }
   } while (!$this$compareAndExchange.compareAndSet(index, expected, newValue));

   return (T)expected;
}
