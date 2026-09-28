package kotlin.concurrent.atomics

internal class AtomicsKt__Atomics_commonKt {
   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public operator fun AtomicInt.plusAssign(delta: Int) {
      `$this$plusAssign`.addAndGet(delta);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public operator fun AtomicInt.minusAssign(delta: Int) {
      `$this$minusAssign`.addAndGet(-delta);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicInt.fetchAndIncrement(): Int {
      return `$this$fetchAndIncrement`.getAndAdd(1);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicInt.incrementAndFetch(): Int {
      return `$this$incrementAndFetch`.addAndGet(1);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicInt.decrementAndFetch(): Int {
      return `$this$decrementAndFetch`.addAndGet(-1);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicInt.fetchAndDecrement(): Int {
      return `$this$fetchAndDecrement`.getAndAdd(-1);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public operator fun AtomicLong.plusAssign(delta: Long) {
      `$this$plusAssign`.addAndGet(delta);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public operator fun AtomicLong.minusAssign(delta: Long) {
      `$this$minusAssign`.addAndGet(-delta);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLong.fetchAndIncrement(): Long {
      return `$this$fetchAndIncrement`.getAndAdd(1L);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLong.incrementAndFetch(): Long {
      return `$this$incrementAndFetch`.addAndGet(1L);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLong.decrementAndFetch(): Long {
      return `$this$decrementAndFetch`.addAndGet(-1L);
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalAtomicApi
   @JvmStatic
   public fun AtomicLong.fetchAndDecrement(): Long {
      return `$this$fetchAndDecrement`.getAndAdd(-1L);
   }

   open fun AtomicsKt__Atomics_commonKt() {
   }
}
