package okio

import java.io.InterruptedIOException
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.DurationUnitKt

@SourceDebugExtension(["SMAP\nTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timeout.kt\nokio/Timeout\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,358:1\n1#2:359\n*E\n"])
public open class Timeout {
   private final var hasDeadline: Boolean
   private final var deadlineNanoTime: Long
   private final var timeoutNanos: Long
   private final var cancelMark: Any?

   public open fun timeout(timeout: Long, unit: TimeUnit): Timeout {
      if (timeout < 0L) {
         throw new IllegalArgumentException(("timeout < 0: $timeout").toString());
      } else {
         this.timeoutNanos = unit.toNanos(timeout);
         return this;
      }
   }

   public open fun timeoutNanos(): Long {
      return this.timeoutNanos;
   }

   public open fun hasDeadline(): Boolean {
      return this.hasDeadline;
   }

   public open fun deadlineNanoTime(): Long {
      if (!this.hasDeadline) {
         throw new IllegalStateException("No deadline".toString());
      } else {
         return this.deadlineNanoTime;
      }
   }

   public open fun deadlineNanoTime(deadlineNanoTime: Long): Timeout {
      this.hasDeadline = true;
      this.deadlineNanoTime = deadlineNanoTime;
      return this;
   }

   public fun deadline(duration: Long, unit: TimeUnit): Timeout {
      if (duration <= 0L) {
         throw new IllegalArgumentException(("duration <= 0: $duration").toString());
      } else {
         return this.deadlineNanoTime(System.nanoTime() + unit.toNanos(duration));
      }
   }

   public open fun clearTimeout(): Timeout {
      this.timeoutNanos = 0L;
      return this;
   }

   public open fun clearDeadline(): Timeout {
      this.hasDeadline = false;
      return this;
   }

   @Throws(java/io/IOException::class)
   public open fun throwIfReached() {
      if (Thread.currentThread().isInterrupted()) {
         throw new InterruptedIOException("interrupted");
      } else if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0L) {
         throw new InterruptedIOException("deadline reached");
      }
   }

   public open fun cancel() {
      this.cancelMark = new Object();
   }

   @Throws(java/io/InterruptedIOException::class)
   public open fun awaitSignal(condition: Condition) {
      try {
         val hasDeadline: Boolean = this.hasDeadline();
         val e: Long = this.timeoutNanos();
         if (!hasDeadline && e == 0L) {
            condition.await();
         } else {
            val var10000: Long = if (hasDeadline && e != 0L)
               Math.min(e, this.deadlineNanoTime() - System.nanoTime())
               else
               (if (hasDeadline) this.deadlineNanoTime() - System.nanoTime() else e);
            if (var10000 <= 0L) {
               throw new InterruptedIOException("timeout");
            } else {
               val var11: Any = this.cancelMark;
               if (condition.awaitNanos(var10000) <= 0L) {
                  if (this.cancelMark === var11) {
                     throw new InterruptedIOException("timeout");
                  }
               }
            }
         }
      } catch (var10: InterruptedException) {
         Thread.currentThread().interrupt();
         throw new InterruptedIOException("interrupted");
      }
   }

   @Throws(java/io/InterruptedIOException::class)
   public open fun waitUntilNotified(monitor: Any) {
      try {
         val hasDeadline: Boolean = this.hasDeadline();
         val e: Long = this.timeoutNanos();
         if (!hasDeadline && e == 0L) {
            monitor.wait();
         } else {
            val start: Long = System.nanoTime();
            val var10000: Long = if (hasDeadline && e != 0L)
               Math.min(e, this.deadlineNanoTime() - start)
               else
               (if (hasDeadline) this.deadlineNanoTime() - start else e);
            if (var10000 <= 0L) {
               throw new InterruptedIOException("timeout");
            } else {
               val var15: Any = this.cancelMark;
               monitor.wait(var10000 / 1000000L, (int)(var10000 - var10000 / 1000000L * 1000000L));
               if (System.nanoTime() - start >= var10000) {
                  if (this.cancelMark === var15) {
                     throw new InterruptedIOException("timeout");
                  }
               }
            }
         }
      } catch (var14: InterruptedException) {
         Thread.currentThread().interrupt();
         throw new InterruptedIOException("interrupted");
      }
   }

   public inline fun <T> intersectWith(other: Timeout, block: () -> T): T {
      val originalTimeout: Long = this.timeoutNanos();
      this.timeout(Companion.minTimeout(other.timeoutNanos(), this.timeoutNanos()), TimeUnit.NANOSECONDS);
      label40:
      if (!this.hasDeadline()) {
         if (other.hasDeadline()) {
            this.deadlineNanoTime(other.deadlineNanoTime());
         }

         try {
            val var16: Any = block.invoke();
         } catch (var10: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            this.timeout(originalTimeout, TimeUnit.NANOSECONDS);
            if (other.hasDeadline()) {
               this.clearDeadline();
            }

            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         this.timeout(originalTimeout, TimeUnit.NANOSECONDS);
         if (other.hasDeadline()) {
            this.clearDeadline();
         }

         InlineMarker.finallyEnd(1);
      } else {
         label100: {
            val originalDeadline: Long = this.deadlineNanoTime();
            if (other.hasDeadline()) {
               this.deadlineNanoTime(Math.min(this.deadlineNanoTime(), other.deadlineNanoTime()));
            }

            try {
               val var8: Any = block.invoke();
            } catch (var11: java.lang.Throwable) {
               InlineMarker.finallyStart(1);
               this.timeout(originalTimeout, TimeUnit.NANOSECONDS);
               if (other.hasDeadline()) {
                  this.deadlineNanoTime(originalDeadline);
               }

               InlineMarker.finallyEnd(1);
            }

            InlineMarker.finallyStart(1);
            this.timeout(originalTimeout, TimeUnit.NANOSECONDS);
            if (other.hasDeadline()) {
               this.deadlineNanoTime(originalDeadline);
            }

            InlineMarker.finallyEnd(1);
         }
      }
   }

   public companion object {
      public final val NONE: Timeout

      public fun Timeout.timeout(timeout: Long, unit: DurationUnit): Timeout {
         return `$this$timeout`.timeout(timeout, DurationUnitKt.toTimeUnit(unit));
      }

      public fun Timeout.timeout(duration: Duration): Timeout {
         return `$this$timeout_u2dHG0u8IE`.timeout(Duration.getInWholeNanoseconds-impl(var2), TimeUnit.NANOSECONDS);
      }

      public fun minTimeout(aNanos: Long, bNanos: Long): Long {
         return if (aNanos == 0L) bNanos else (if (bNanos == 0L) aNanos else (if (aNanos < bNanos) aNanos else bNanos));
      }
   }
}
