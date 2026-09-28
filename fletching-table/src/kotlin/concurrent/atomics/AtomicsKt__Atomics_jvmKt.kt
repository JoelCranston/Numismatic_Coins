package kotlin.concurrent.atomics

import java.util.concurrent.atomic.AtomicInteger
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly

internal class AtomicsKt__Atomics_jvmKt : AtomicsKt__Atomics_commonKt {
   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicInt.asJavaAtomic(): AtomicInteger {
      return `$this$asJavaAtomic`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicInteger.asKotlinAtomic(): AtomicInt {
      return `$this$asKotlinAtomic`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLong.asJavaAtomic(): java.util.concurrent.atomic.AtomicLong {
      return `$this$asJavaAtomic`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun java.util.concurrent.atomic.AtomicLong.asKotlinAtomic(): AtomicLong {
      return `$this$asKotlinAtomic`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicBoolean.asJavaAtomic(): java.util.concurrent.atomic.AtomicBoolean {
      return `$this$asJavaAtomic`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun java.util.concurrent.atomic.AtomicBoolean.asKotlinAtomic(): AtomicBoolean {
      return `$this$asKotlinAtomic`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun <T> AtomicReference<T>.asJavaAtomic(): java.util.concurrent.atomic.AtomicReference<T> {
      return `$this$asJavaAtomic`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun <T> java.util.concurrent.atomic.AtomicReference<T>.asKotlinAtomic(): AtomicReference<T> {
      return `$this$asKotlinAtomic`;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicInt.update(transform: (Int) -> Int) {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val var2: AtomicInteger = `$this$update`;

      val var3: Int;
      do {
         var3 = var2.get();
      } while (!var2.compareAndSet(var3, ((java.lang.Number)transform.invoke(var3)).intValue()));
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicInt.fetchAndUpdate(transform: (Int) -> Int): Int {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Int;
      do {
         old = `$this$fetchAndUpdate`.get();
      } while (!$this$fetchAndUpdate.compareAndSet(old, ((java.lang.Number)transform.invoke(old)).intValue()));

      return old;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicInt.updateAndFetch(transform: (Int) -> Int): Int {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Int;
      val newValue: Int;
      do {
         old = `$this$updateAndFetch`.get();
         newValue = (transform.invoke(old) as java.lang.Number).intValue();
      } while (!$this$updateAndFetch.compareAndSet(old, newValue));

      return newValue;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicLong.update(transform: (Long) -> Long) {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val var2: java.util.concurrent.atomic.AtomicLong = `$this$update`;

      val var3: Long;
      do {
         var3 = var2.get();
      } while (!var2.compareAndSet(var3, ((java.lang.Number)transform.invoke(var3)).longValue()));
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicLong.fetchAndUpdate(transform: (Long) -> Long): Long {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Long;
      do {
         old = `$this$fetchAndUpdate`.get();
      } while (!$this$fetchAndUpdate.compareAndSet(old, ((java.lang.Number)transform.invoke(old)).longValue()));

      return old;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicLong.updateAndFetch(transform: (Long) -> Long): Long {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Long;
      val newValue: Long;
      do {
         old = `$this$updateAndFetch`.get();
         newValue = (transform.invoke(old) as java.lang.Number).longValue();
      } while (!$this$updateAndFetch.compareAndSet(old, newValue));

      return newValue;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun <T> AtomicReference<T>.update(transform: (T) -> T) {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val var2: java.util.concurrent.atomic.AtomicReference = `$this$update`;

      val var3: Any;
      do {
         var3 = var2.get();
      } while (!var2.compareAndSet(var3, transform.invoke(var3)));
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun <T> AtomicReference<T>.fetchAndUpdate(transform: (T) -> T): T {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Any;
      do {
         old = `$this$fetchAndUpdate`.get();
      } while (!$this$fetchAndUpdate.compareAndSet(old, transform.invoke(old)));

      return (T)old;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun <T> AtomicReference<T>.updateAndFetch(transform: (T) -> T): T {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Any;
      val newValue: Any;
      do {
         old = `$this$updateAndFetch`.get();
         newValue = transform.invoke(old);
      } while (!$this$updateAndFetch.compareAndSet(old, newValue));

      return (T)newValue;
   }

   open fun AtomicsKt__Atomics_jvmKt() {
   }
}
