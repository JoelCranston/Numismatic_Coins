package kotlin.concurrent.atomics

import java.util.concurrent.atomic.AtomicIntegerArray
import java.util.concurrent.atomic.AtomicReferenceArray
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly

internal class AtomicArraysKt__AtomicArrays_jvmKt : AtomicArraysKt__AtomicArrays_commonKt {
   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicIntArray.asJavaAtomicArray(): AtomicIntegerArray {
      return `$this$asJavaAtomicArray`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicIntegerArray.asKotlinAtomicArray(): AtomicIntArray {
      return `$this$asKotlinAtomicArray`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLongArray.asJavaAtomicArray(): java.util.concurrent.atomic.AtomicLongArray {
      return `$this$asJavaAtomicArray`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun java.util.concurrent.atomic.AtomicLongArray.asKotlinAtomicArray(): AtomicLongArray {
      return `$this$asKotlinAtomicArray`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun <T> AtomicArray<T>.asJavaAtomicArray(): AtomicReferenceArray<T> {
      return `$this$asJavaAtomicArray`;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun <T> AtomicReferenceArray<T>.asKotlinAtomicArray(): AtomicArray<T> {
      return `$this$asKotlinAtomicArray`;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicIntArray.updateAt(index: Int, transform: (Int) -> Int) {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val var3: AtomicIntegerArray = `$this$updateAt`;

      val var4: Int;
      do {
         var4 = var3.get(index);
      } while (!var3.compareAndSet(index, var4, ((java.lang.Number)transform.invoke(var4)).intValue()));
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicIntArray.updateAndFetchAt(index: Int, transform: (Int) -> Int): Int {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Int;
      val var4: Int;
      do {
         old = `$this$updateAndFetchAt`.get(index);
         var4 = (transform.invoke(old) as java.lang.Number).intValue();
      } while (!$this$updateAndFetchAt.compareAndSet(index, old, new));

      return var4;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicIntArray.fetchAndUpdateAt(index: Int, transform: (Int) -> Int): Int {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Int;
      do {
         old = `$this$fetchAndUpdateAt`.get(index);
      } while (!$this$fetchAndUpdateAt.compareAndSet(index, old, ((java.lang.Number)transform.invoke(old)).intValue()));

      return old;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicLongArray.updateAt(index: Int, transform: (Long) -> Long) {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val var3: java.util.concurrent.atomic.AtomicLongArray = `$this$updateAt`;

      val var4: Long;
      do {
         var4 = var3.get(index);
      } while (!var3.compareAndSet(index, var4, ((java.lang.Number)transform.invoke(var4)).longValue()));
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicLongArray.updateAndFetchAt(index: Int, transform: (Long) -> Long): Long {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Long;
      val var5: Long;
      do {
         old = `$this$updateAndFetchAt`.get(index);
         var5 = (transform.invoke(old) as java.lang.Number).longValue();
      } while (!$this$updateAndFetchAt.compareAndSet(index, old, new));

      return var5;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun AtomicLongArray.fetchAndUpdateAt(index: Int, transform: (Long) -> Long): Long {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Long;
      do {
         old = `$this$fetchAndUpdateAt`.get(index);
      } while (!$this$fetchAndUpdateAt.compareAndSet(index, old, ((java.lang.Number)transform.invoke(old)).longValue()));

      return old;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun <T> AtomicArray<T>.updateAt(index: Int, transform: (T) -> T) {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val var3: AtomicReferenceArray = `$this$updateAt`;

      val var4: Any;
      do {
         var4 = var3.get(index);
      } while (!var3.compareAndSet(index, var4, transform.invoke(var4)));
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun <T> AtomicArray<T>.updateAndFetchAt(index: Int, transform: (T) -> T): T {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Any;
      val var4: Any;
      do {
         old = `$this$updateAndFetchAt`.get(index);
         var4 = transform.invoke(old);
      } while (!$this$updateAndFetchAt.compareAndSet(index, old, new));

      return (T)var4;
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalAtomicApi
   @InlineOnly
   @JvmStatic
   public inline fun <T> AtomicArray<T>.fetchAndUpdateAt(index: Int, transform: (T) -> T): T {
      contract {
         callsInPlace(transform, InvocationKind.AT_LEAST_ONCE)
      }

      val old: Any;
      do {
         old = `$this$fetchAndUpdateAt`.get(index);
      } while (!$this$fetchAndUpdateAt.compareAndSet(index, old, transform.invoke(old)));

      return (T)old;
   }

   open fun AtomicArraysKt__AtomicArrays_jvmKt() {
   }
}
