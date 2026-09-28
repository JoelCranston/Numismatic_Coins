package kotlin.concurrent.atomics

import java.util.concurrent.atomic.AtomicIntegerArray
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nAtomicArrays.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AtomicArrays.common.kt\nkotlin/concurrent/atomics/AtomicArraysKt__AtomicArrays_commonKt\n*L\n1#1,768:1\n666#1:769\n*S KotlinDebug\n*F\n+ 1 AtomicArrays.common.kt\nkotlin/concurrent/atomics/AtomicArraysKt__AtomicArrays_commonKt\n*L\n678#1:769\n*E\n"])
internal class AtomicArraysKt__AtomicArrays_commonKt {
   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public inline fun AtomicIntArray(size: Int, init: (Int) -> Int): AtomicIntArray {
      var var3: Int = 0;

      val var4: IntArray;
      for (var4 = new int[size]; var3 < size; var3++) {
         var4[var3] = (init.invoke(var3) as java.lang.Number).intValue();
      }

      return new AtomicIntegerArray(var4);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicIntArray.fetchAndIncrementAt(index: Int): Int {
      return `$this$fetchAndIncrementAt`.getAndAdd(index, 1);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicIntArray.incrementAndFetchAt(index: Int): Int {
      return `$this$incrementAndFetchAt`.addAndGet(index, 1);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicIntArray.decrementAndFetchAt(index: Int): Int {
      return `$this$decrementAndFetchAt`.addAndGet(index, -1);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicIntArray.fetchAndDecrementAt(index: Int): Int {
      return `$this$fetchAndDecrementAt`.getAndAdd(index, -1);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public inline fun AtomicLongArray(size: Int, init: (Int) -> Long): AtomicLongArray {
      var var3: Int = 0;

      val var4: LongArray;
      for (var4 = new long[size]; var3 < size; var3++) {
         var4[var3] = (init.invoke(var3) as java.lang.Number).longValue();
      }

      return new java.util.concurrent.atomic.AtomicLongArray(var4);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLongArray.fetchAndIncrementAt(index: Int): Long {
      return `$this$fetchAndIncrementAt`.getAndAdd(index, 1L);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLongArray.incrementAndFetchAt(index: Int): Long {
      return `$this$incrementAndFetchAt`.addAndGet(index, 1L);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLongArray.decrementAndFetchAt(index: Int): Long {
      return `$this$decrementAndFetchAt`.addAndGet(index, -1L);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLongArray.fetchAndDecrementAt(index: Int): Long {
      return `$this$fetchAndDecrementAt`.getAndAdd(index, -1L);
   }

   open fun AtomicArraysKt__AtomicArrays_commonKt() {
   }
}
