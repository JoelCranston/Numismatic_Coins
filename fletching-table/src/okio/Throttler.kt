package okio

import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import okio.Throttler.source.1

public class Throttler internal constructor(allocatedUntil: Long) {
   private final var allocatedUntil: Long
   private final var bytesPerSecond: Long
   private final var waitByteCount: Long
   private final var maxByteCount: Long
   public final val lock: ReentrantLock
   public final val condition: Condition

   init {
      this.allocatedUntil = allocatedUntil;
      this.waitByteCount = 8192L;
      this.maxByteCount = 262144L;
      this.lock = new ReentrantLock();
      val var10001: Condition = this.lock.newCondition();
      this.condition = var10001;
   }

   public constructor() : this(System.nanoTime())
   @JvmOverloads
   public fun bytesPerSecond(bytesPerSecond: Long, waitByteCount: Long = this.waitByteCount, maxByteCount: Long = this.maxByteCount) {
      label47: {
         val var7: Lock = this.lock;
         this.lock.lock();

         try {
            if (bytesPerSecond < 0L) {
               throw new IllegalArgumentException("Failed requirement.".toString());
            }

            if (waitByteCount <= 0L) {
               throw new IllegalArgumentException("Failed requirement.".toString());
            }

            if (maxByteCount < waitByteCount) {
               throw new IllegalArgumentException("Failed requirement.".toString());
            }

            this.bytesPerSecond = bytesPerSecond;
            this.waitByteCount = waitByteCount;
            this.maxByteCount = maxByteCount;
            this.condition.signalAll();
         } catch (var10: java.lang.Throwable) {
            var7.unlock();
         }

         var7.unlock();
      }
   }

   internal fun take(byteCount: Long): Long {
      if (byteCount <= 0L) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         label54: {
            val var3: Lock = this.lock;
            this.lock.lock();

            try {
               while (true) {
                  val byteCountOrWaitNanos: Long = this.byteCountOrWaitNanos$okio(System.nanoTime(), byteCount);
                  if (byteCountOrWaitNanos >= 0L) {
                     break;
                  }

                  this.condition.awaitNanos(-byteCountOrWaitNanos);
               }
            } catch (var12: java.lang.Throwable) {
               var3.unlock();
            }

            var3.unlock();
         }
      }
   }

   internal fun byteCountOrWaitNanos(now: Long, byteCount: Long): Long {
      if (this.bytesPerSecond == 0L) {
         return byteCount;
      } else {
         val idleInNanos: Long = Math.max(this.allocatedUntil - now, 0L);
         val immediateBytes: Long = this.maxByteCount - this.nanosToBytes(idleInNanos);
         if (immediateBytes >= byteCount) {
            this.allocatedUntil = now + idleInNanos + this.bytesToNanos(byteCount);
            return byteCount;
         } else if (immediateBytes >= this.waitByteCount) {
            this.allocatedUntil = now + this.bytesToNanos(this.maxByteCount);
            return immediateBytes;
         } else {
            val minByteCount: Long = Math.min(this.waitByteCount, byteCount);
            val minWaitNanos: Long = idleInNanos + this.bytesToNanos(minByteCount - this.maxByteCount);
            if (minWaitNanos == 0L) {
               this.allocatedUntil = now + this.bytesToNanos(this.maxByteCount);
               return minByteCount;
            } else {
               return -minWaitNanos;
            }
         }
      }
   }

   private fun Long.nanosToBytes(): Long {
      return `$this$nanosToBytes` * this.bytesPerSecond / 1000000000L;
   }

   private fun Long.bytesToNanos(): Long {
      return `$this$bytesToNanos` * 1000000000L / this.bytesPerSecond;
   }

   public fun source(source: Source): Source {
      return new 1(source, this);
   }

   public fun sink(sink: Sink): Sink {
      return new okio.Throttler.sink.1(sink, this);
   }

   @JvmOverloads
   fun bytesPerSecond(bytesPerSecond: Long, waitByteCount: Long) {
      bytesPerSecond$default(this, bytesPerSecond, waitByteCount, 0L, 4, null);
   }

   @JvmOverloads
   fun bytesPerSecond(bytesPerSecond: Long) {
      bytesPerSecond$default(this, bytesPerSecond, 0L, 0L, 6, null);
   }
}
