package okio

import java.io.IOException
import java.io.InterruptedIOException
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import okio.AsyncTimeout.sink.1

@SourceDebugExtension(["SMAP\nAsyncTimeout.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AsyncTimeout.kt\nokio/AsyncTimeout\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,514:1\n1#2:515\n*E\n"])
public open class AsyncTimeout : Timeout {
   private final var state: Int

   internal final var index: Int = -1
      private set

   internal final var timeoutAt: Long
      private set

   public fun enter() {
      if (this.timeoutNanos() != 0L || this.hasDeadline()) {
         label55: {
            val var4: Lock = lock;
            lock.lock();

            try {
               if (this.state != 0) {
                  throw new IllegalStateException("Unbalanced enter/exit".toString());
               }

               this.state = 1;
               AsyncTimeout.Companion.access$insertIntoQueue(Companion, this);
            } catch (var7: java.lang.Throwable) {
               var4.unlock();
            }

            var4.unlock();
         }
      }
   }

   public fun exit(): Boolean {
      label29: {
         val var1: Lock = lock;
         lock.lock();

         label26: {
            try {
               val oldState: Int = this.state;
               this.state = 0;
               if (oldState == 1) {
                  queue.remove(this);
                  break label26;
               }

               val var4: Boolean = oldState == 2;
            } catch (var6: java.lang.Throwable) {
               var1.unlock();
            }

            var1.unlock();
         }

         var1.unlock();
      }
   }

   public override fun cancel() {
      label21: {
         super.cancel();
         val var1: Lock = lock;
         lock.lock();

         try {
            if (this.state == 1) {
               queue.remove(this);
               this.state = 3;
            }
         } catch (var4: java.lang.Throwable) {
            var1.unlock();
         }

         var1.unlock();
      }
   }

   internal fun remainingNanos(now: Long): Long {
      return this.timeoutAt - now;
   }

   internal fun setTimeoutAt(now: Long = ...) {
      val timeoutNanos: Long = this.timeoutNanos();
      val hasDeadline: Boolean = this.hasDeadline();
      if (this.timeoutNanos() != 0L && this.hasDeadline()) {
         this.timeoutAt = now + Math.min(timeoutNanos, this.deadlineNanoTime() - now);
      } else if (timeoutNanos != 0L) {
         this.timeoutAt = now + timeoutNanos;
      } else {
         if (!hasDeadline) {
            throw new AssertionError();
         }

         this.timeoutAt = this.deadlineNanoTime();
      }
   }

   protected open fun timedOut() {
   }

   public fun sink(sink: Sink): Sink {
      return new 1(this, sink);
   }

   public fun source(source: Source): Source {
      return new okio.AsyncTimeout.source.1(this, source);
   }

   public inline fun <T> withTimeout(block: () -> T): T {
      label37: {
         this.enter();

         try {
            try {
               ;
            } catch (var7: IOException) {
               throw if (!this.exit()) var7 as java.lang.Throwable else this.access$newTimeoutException(var7) as java.lang.Throwable;
            }
         } catch (var8: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            if (this.exit() && false) {
               throw this.access$newTimeoutException(null);
            }

            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         if (this.exit()) {
            throw this.access$newTimeoutException(null);
         } else {
            InlineMarker.finallyEnd(1);
            val e: Any;
            return (T)e;
         }
      }
   }

   @PublishedApi
   internal fun `access$newTimeoutException`(cause: IOException?): IOException {
      return this.newTimeoutException(cause);
   }

   protected open fun newTimeoutException(cause: IOException?): IOException {
      val e: InterruptedIOException = new InterruptedIOException("timeout");
      if (cause != null) {
         e.initCause(cause);
      }

      return e;
   }

   @JvmStatic
   fun {
      val var10000: Condition = lock.newCondition();
      condition = var10000;
   }

   private companion object {
      public final val queue: PriorityQueue

      public final var idleSentinel: AsyncTimeout?
         internal set

      public final val lock: ReentrantLock
      public final val condition: Condition
      private const val TIMEOUT_WRITE_SIZE: Int
      private final val IDLE_TIMEOUT_MILLIS: Long
      private final val IDLE_TIMEOUT_NANOS: Long
      private const val STATE_IDLE: Int
      private const val STATE_IN_QUEUE: Int
      private const val STATE_TIMED_OUT: Int
      private const val STATE_CANCELED: Int

      private fun insertIntoQueue(node: AsyncTimeout) {
         if (this.getIdleSentinel() == null) {
            this.setIdleSentinel(new AsyncTimeout());
            new AsyncTimeout.Watchdog().start();
         }

         AsyncTimeout.setTimeoutAt$okio$default(node, 0L, 1, null);
         this.getQueue().add(node);
         if (node.index == 1) {
            this.getCondition().signal();
         }
      }

      @Throws(java/lang/InterruptedException::class)
      public fun awaitTimeout(): AsyncTimeout? {
         val node: AsyncTimeout = this.getQueue().first();
         if (node != null) {
            val var4: Long = node.remainingNanos$okio(System.nanoTime());
            if (var4 > 0L) {
               this.getCondition().await(var4, TimeUnit.NANOSECONDS);
               return null;
            } else {
               this.getQueue().remove(node);
               AsyncTimeout.access$setState$p(node, 2);
               return node;
            }
         } else {
            val waitNanos: Long = System.nanoTime();
            this.getCondition().await(AsyncTimeout.access$getIDLE_TIMEOUT_MILLIS$cp(), TimeUnit.MILLISECONDS);
            return if (this.getQueue().first() == null && System.nanoTime() - waitNanos >= AsyncTimeout.access$getIDLE_TIMEOUT_NANOS$cp())
               this.getIdleSentinel()
               else
               null;
         }
      }
   }

   private class Watchdog : Thread("Okio Watchdog") {
      init {
         this.setDaemon(true);
      }

      public override fun run() {
         // $VF: Couldn't be decompiled
         // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
         // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
         //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
         //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
         //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
         //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
         //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
         //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
         //   at org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent.<init>(InvocationExprent.java:163)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:490)
         //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
         //
         // Bytecode:
         // 00: nop
         // 01: nop
         // 02: aconst_null
         // 03: astore 1
         // 04: invokestatic okio/AsyncTimeout.access$getCompanion$p ()Lokio/AsyncTimeout$Companion;
         // 07: invokevirtual okio/AsyncTimeout$Companion.getLock ()Ljava/util/concurrent/locks/ReentrantLock;
         // 0a: checkcast java/util/concurrent/locks/Lock
         // 0d: astore 2
         // 0e: aload 2
         // 0f: invokeinterface java/util/concurrent/locks/Lock.lock ()V 1
         // 14: nop
         // 15: bipush 0
         // 16: istore 3
         // 17: invokestatic okio/AsyncTimeout.access$getCompanion$p ()Lokio/AsyncTimeout$Companion;
         // 1a: invokevirtual okio/AsyncTimeout$Companion.awaitTimeout ()Lokio/AsyncTimeout;
         // 1d: astore 1
         // 1e: aload 1
         // 1f: invokestatic okio/AsyncTimeout.access$getCompanion$p ()Lokio/AsyncTimeout$Companion;
         // 22: invokevirtual okio/AsyncTimeout$Companion.getIdleSentinel ()Lokio/AsyncTimeout;
         // 25: if_acmpne 37
         // 28: invokestatic okio/AsyncTimeout.access$getCompanion$p ()Lokio/AsyncTimeout$Companion;
         // 2b: aconst_null
         // 2c: invokevirtual okio/AsyncTimeout$Companion.setIdleSentinel (Lokio/AsyncTimeout;)V
         // 2f: nop
         // 30: aload 2
         // 31: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
         // 36: return
         // 37: nop
         // 38: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
         // 3b: astore 3
         // 3c: aload 2
         // 3d: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
         // 42: goto 50
         // 45: astore 4
         // 47: aload 2
         // 48: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
         // 4d: aload 4
         // 4f: athrow
         // 50: aload 1
         // 51: dup
         // 52: ifnull 5b
         // 55: invokevirtual okio/AsyncTimeout.timedOut ()V
         // 58: goto 00
         // 5b: pop
         // 5c: goto 00
         // 5f: astore 2
         // 60: goto 00
      }
   }
}
