package kotlinx.coroutines

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicBoolean
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.internal.LockFreeTaskQueueCore
import kotlinx.coroutines.internal.ThreadSafeHeap
import kotlinx.coroutines.internal.ThreadSafeHeapNode

@SourceDebugExtension(["SMAP\nEventLoop.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase\n+ 2 EventLoop.kt\nkotlinx/coroutines/EventLoopKt\n+ 3 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n+ 4 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 5 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,547:1\n53#2:548\n51#3:549\n52#3,7:552\n29#4:550\n16#5:551\n1#6:559\n*S KotlinDebug\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase\n*L\n263#1:548\n336#1:549\n336#1:552,7\n336#1:550\n336#1:551\n*E\n"])
internal abstract class EventLoopImplBase : EventLoopImplPlatform, Delay {
   private final val _queue: AtomicRef<Any?>
   private final val _delayed: AtomicRef<kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue?>
   private final val _isCompleted: AtomicBoolean

   private final var isCompleted: Boolean
      private final get() {
         return get_isCompleted$volatile$FU().get(this) == 1;
      }

      private final set(value) {
         get_isCompleted$volatile$FU().set(this, if (value) 1 else 0);
      }


   protected open val isEmpty: Boolean
      protected open get() {
         if (!this.isUnconfinedQueueEmpty()) {
            return false;
         } else {
            val delayed: EventLoopImplBase.DelayedTaskQueue = get_delayed$volatile$FU().get(this) as EventLoopImplBase.DelayedTaskQueue;
            if (delayed != null && !delayed.isEmpty()) {
               return false;
            } else {
               val queue: Any = get_queue$volatile$FU().get(this);
               return queue == null
                  || (
                     if (queue is LockFreeTaskQueueCore)
                        (queue as LockFreeTaskQueueCore).isEmpty()
                        else
                        queue === EventLoop_commonKt.access$getCLOSED_EMPTY$p()
                  );
            }
         }
      }


   protected open val nextTime: Long
      protected open get() {
         if (super.getNextTime() == 0L) {
            return 0L;
         } else {
            val queue: Any = get_queue$volatile$FU().get(this);
            if (queue != null) {
               if (queue !is LockFreeTaskQueueCore) {
                  if (queue === EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                     return java.lang.Long.MAX_VALUE;
                  }

                  return 0L;
               }

               if (!(queue as LockFreeTaskQueueCore).isEmpty()) {
                  return 0L;
               }
            }

            val var10000: EventLoopImplBase.DelayedTaskQueue = get_delayed$volatile$FU().get(this) as EventLoopImplBase.DelayedTaskQueue;
            if (var10000 != null) {
               val var3: EventLoopImplBase.DelayedTask = var10000.peek();
               if (var3 != null) {
                  val var4: Long = var3.nanoTime;
                  val var10001: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
                  return RangesKt.coerceAtLeast(var4 - (if (var10001 != null) var10001.nanoTime() else System.nanoTime()), 0L);
               }
            }

            return java.lang.Long.MAX_VALUE;
         }
      }


   public override fun shutdown() {
      ThreadLocalEventLoop.INSTANCE.resetEventLoop$kotlinx_coroutines_core();
      this.setCompleted(true);
      this.closeQueue();

      while (this.processNextEvent() <= 0L) {
      }

      this.rescheduleAllDelayed();
   }

   public override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
      val timeNanos: Long = EventLoop_commonKt.delayToNanos(timeMillis);
      if (timeNanos < 4611686018427387903L) {
         val var10000: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
         val now: Long = if (var10000 != null) var10000.nanoTime() else System.nanoTime();
         val var8: EventLoopImplBase.DelayedResumeTask = new EventLoopImplBase.DelayedResumeTask((long)this, now + timeNanos, continuation);
         this.schedule(now, var8);
         CancellableContinuationKt.disposeOnCancellation(continuation, var8);
      }
   }

   protected fun scheduleInvokeOnTimeout(timeMillis: Long, block: Runnable): DisposableHandle {
      val timeNanos: Long = EventLoop_commonKt.delayToNanos(timeMillis);
      val var11: DisposableHandle;
      if (timeNanos < 4611686018427387903L) {
         val var10000: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
         val now: Long = if (var10000 != null) var10000.nanoTime() else System.nanoTime();
         val var8: EventLoopImplBase.DelayedRunnableTask = new EventLoopImplBase.DelayedRunnableTask(now + timeNanos, block);
         this.schedule(now, var8);
         var11 = var8;
      } else {
         var11 = NonDisposableHandle.INSTANCE;
      }

      return var11;
   }

   public override fun processNextEvent(): Long {
      if (this.processUnconfinedEvent()) {
         return 0L;
      } else {
         this.enqueueDelayedTasks();
         val task: Runnable = this.dequeue();
         if (task != null) {
            task.run();
            return 0L;
         } else {
            return this.getNextTime();
         }
      }
   }

   public override fun dispatch(context: CoroutineContext, block: Runnable) {
      this.enqueue(block);
   }

   public open fun enqueue(task: Runnable) {
      this.enqueueDelayedTasks();
      if (this.enqueueImpl(task)) {
         this.unpark();
      } else {
         DefaultExecutor.INSTANCE.enqueue(task);
      }
   }

   private fun enqueueImpl(task: Runnable): Boolean {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_queue$volatile$FU();

      while (true) {
         val queue: Any = `handler$atomicfu$iv`.get(this);
         if (this.isCompleted()) {
            return false;
         }

         if (queue == null) {
            if (get_queue$volatile$FU().compareAndSet(this, null, task)) {
               return true;
            }
         } else if (queue is LockFreeTaskQueueCore) {
            switch (((LockFreeTaskQueueCore)queue).addLast(task)) {
               case 0:
                  return true;
               case 1:
                  get_queue$volatile$FU().compareAndSet(this, queue, (queue as LockFreeTaskQueueCore).next());
                  break;
               case 2:
                  return false;
               default:
            }
         } else {
            if (queue === EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
               return false;
            }

            val newQueue: LockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
            newQueue.addLast(queue as Runnable);
            newQueue.addLast(task);
            if (get_queue$volatile$FU().compareAndSet(this, queue, newQueue)) {
               return true;
            }
         }
      }
   }

   private fun dequeue(): Runnable? {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_queue$volatile$FU();

      while (true) {
         val queue: Any = `handler$atomicfu$iv`.get(this);
         if (queue == null) {
            return null;
         }

         if (queue is LockFreeTaskQueueCore) {
            val result: Any = (queue as LockFreeTaskQueueCore).removeFirstOrNull();
            if (result != LockFreeTaskQueueCore.REMOVE_FROZEN) {
               return result as Runnable;
            }

            get_queue$volatile$FU().compareAndSet(this, queue, (queue as LockFreeTaskQueueCore).next());
         } else {
            if (queue === EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
               return null;
            }

            if (get_queue$volatile$FU().compareAndSet(this, queue, null)) {
               return queue as Runnable;
            }
         }
      }
   }

   private fun enqueueDelayedTasks() {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.<init>(FunctionExprent.java:159)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:459)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
      //
      // Bytecode:
      // 00: invokestatic kotlinx/coroutines/EventLoopImplBase.get_delayed$volatile$FU ()Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;
      // 03: aload 0
      // 04: invokevirtual java/util/concurrent/atomic/AtomicReferenceFieldUpdater.get (Ljava/lang/Object;)Ljava/lang/Object;
      // 07: checkcast kotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue
      // 0a: astore 1
      // 0b: aload 1
      // 0c: ifnull ac
      // 0f: aload 1
      // 10: invokevirtual kotlinx/coroutines/EventLoopImplBase$DelayedTaskQueue.isEmpty ()Z
      // 13: ifne ac
      // 16: invokestatic kotlinx/coroutines/AbstractTimeSourceKt.access$getTimeSource$p ()Lkotlinx/coroutines/AbstractTimeSource;
      // 19: dup
      // 1a: ifnull 23
      // 1d: invokevirtual kotlinx/coroutines/AbstractTimeSource.nanoTime ()J
      // 20: goto 27
      // 23: pop
      // 24: invokestatic java/lang/System.nanoTime ()J
      // 27: lstore 2
      // 28: nop
      // 29: aload 1
      // 2a: checkcast kotlinx/coroutines/internal/ThreadSafeHeap
      // 2d: astore 4
      // 2f: bipush 0
      // 30: istore 5
      // 32: bipush 0
      // 33: istore 6
      // 35: bipush 0
      // 36: istore 7
      // 38: aload 4
      // 3a: astore 8
      // 3c: aload 8
      // 3e: monitorenter
      // 3f: nop
      // 40: bipush 0
      // 41: istore 9
      // 43: aload 4
      // 45: invokevirtual kotlinx/coroutines/internal/ThreadSafeHeap.firstImpl ()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;
      // 48: dup
      // 49: ifnonnull 58
      // 4c: pop
      // 4d: aconst_null
      // 4e: astore 10
      // 50: aload 8
      // 52: monitorexit
      // 53: aload 10
      // 55: goto 9d
      // 58: astore 11
      // 5a: aload 11
      // 5c: checkcast kotlinx/coroutines/EventLoopImplBase$DelayedTask
      // 5f: astore 12
      // 61: bipush 0
      // 62: istore 13
      // 64: aload 12
      // 66: lload 2
      // 67: invokevirtual kotlinx/coroutines/EventLoopImplBase$DelayedTask.timeToExecute (J)Z
      // 6a: ifeq 79
      // 6d: aload 0
      // 6e: aload 12
      // 70: checkcast java/lang/Runnable
      // 73: invokespecial kotlinx/coroutines/EventLoopImplBase.enqueueImpl (Ljava/lang/Runnable;)Z
      // 76: goto 7a
      // 79: bipush 0
      // 7a: ifeq 86
      // 7d: aload 4
      // 7f: bipush 0
      // 80: invokevirtual kotlinx/coroutines/internal/ThreadSafeHeap.removeAtImpl (I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;
      // 83: goto 87
      // 86: aconst_null
      // 87: nop
      // 88: astore 14
      // 8a: aload 8
      // 8c: monitorexit
      // 8d: aload 14
      // 8f: goto 9a
      // 92: astore 9
      // 94: aload 8
      // 96: monitorexit
      // 97: aload 9
      // 99: athrow
      // 9a: nop
      // 9b: nop
      // 9c: nop
      // 9d: checkcast kotlinx/coroutines/EventLoopImplBase$DelayedTask
      // a0: dup
      // a1: ifnonnull a8
      // a4: pop
      // a5: goto ac
      // a8: pop
      // a9: goto 28
      // ac: return
   }

   private fun closeQueue() {
      if (DebugKt.getASSERTIONS_ENABLED() && !this.isCompleted()) {
         throw new AssertionError();
      } else {
         val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_queue$volatile$FU();

         while (true) {
            val queue: Any = `handler$atomicfu$iv`.get(this);
            if (queue == null) {
               if (get_queue$volatile$FU().compareAndSet(this, null, EventLoop_commonKt.access$getCLOSED_EMPTY$p())) {
                  return;
               }
            } else {
               if (queue is LockFreeTaskQueueCore) {
                  (queue as LockFreeTaskQueueCore).close();
                  return;
               }

               if (queue === EventLoop_commonKt.access$getCLOSED_EMPTY$p()) {
                  return;
               }

               val newQueue: LockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
               newQueue.addLast(queue as Runnable);
               if (get_queue$volatile$FU().compareAndSet(this, queue, newQueue)) {
                  return;
               }
            }
         }
      }
   }

   public fun schedule(now: Long, delayedTask: kotlinx.coroutines.EventLoopImplBase.DelayedTask) {
      switch (this.scheduleImpl(now, delayedTask)) {
         case 0:
            if (this.shouldUnpark(delayedTask)) {
               this.unpark();
            }
            break;
         case 1:
            this.reschedule(now, delayedTask);
         case 2:
            break;
         default:
            throw new IllegalStateException("unexpected result".toString());
      }
   }

   private fun shouldUnpark(task: kotlinx.coroutines.EventLoopImplBase.DelayedTask): Boolean {
      val var10000: EventLoopImplBase.DelayedTaskQueue = get_delayed$volatile$FU().get(this) as EventLoopImplBase.DelayedTaskQueue;
      return (if (var10000 != null) var10000.peek() else null) === task;
   }

   private fun scheduleImpl(now: Long, delayedTask: kotlinx.coroutines.EventLoopImplBase.DelayedTask): Int {
      if (this.isCompleted()) {
         return 1;
      } else {
         var var10000: EventLoopImplBase.DelayedTaskQueue = get_delayed$volatile$FU().get(this) as EventLoopImplBase.DelayedTaskQueue;
         if (var10000 == null) {
            val `$this$scheduleImpl_u24lambda_u248`: EventLoopImplBase = this;
            get_delayed$volatile$FU().compareAndSet(this, null, new EventLoopImplBase.DelayedTaskQueue(now));
            var10000 = (EventLoopImplBase.DelayedTaskQueue)get_delayed$volatile$FU().get(`$this$scheduleImpl_u24lambda_u248`);
            var10000 = var10000;
         }

         return delayedTask.scheduleTask(now, var10000, this);
      }
   }

   protected fun resetAll() {
      get_queue$volatile$FU().set(this, null);
      get_delayed$volatile$FU().set(this, null);
   }

   private fun rescheduleAllDelayed() {
      val var10000: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
      val now: Long = if (var10000 != null) var10000.nanoTime() else System.nanoTime();

      while (true) {
         val var4: EventLoopImplBase.DelayedTaskQueue = get_delayed$volatile$FU().get(this) as EventLoopImplBase.DelayedTaskQueue;
         if (var4 == null) {
            break;
         }

         val var5: EventLoopImplBase.DelayedTask = var4.removeFirstOrNull();
         if (var5 == null) {
            break;
         }

         this.reschedule(now, var5);
      }
   }

   /** @deprecated */
   @Deprecated(message = "Deprecated without replacement as an internal method never intended for public use", level = DeprecationLevel.ERROR)
   override fun delay(time: Long, `$completion`: Continuation<? super Unit>): Any {
      return Delay.DefaultImpls.delay(this, time, `$completion`);
   }

   override fun invokeOnTimeout(timeMillis: Long, block: Runnable, context: CoroutineContext): DisposableHandle {
      return Delay.DefaultImpls.invokeOnTimeout(this, timeMillis, block, context);
   }

   @SourceDebugExtension(["SMAP\nEventLoop.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase$DelayedResumeTask\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,547:1\n1#2:548\n*E\n"])
   private inner class DelayedResumeTask(nanoTime: Long, cont: CancellableContinuation<Unit>) : EventLoopImplBase.DelayedTask(nanoTime) {
      private final val cont: CancellableContinuation<Unit>

      init {
         this.this$0 = `this$0`;
         this.cont = cont;
      }

      public override fun run() {
         this.cont.resumeUndispatched(this.this$0, Unit.INSTANCE);
      }

      public override fun toString(): String {
         return "${super.toString()}${this.cont}";
      }
   }

   private class DelayedRunnableTask(nanoTime: Long, block: Runnable) : EventLoopImplBase.DelayedTask(nanoTime) {
      private final val block: Runnable

      init {
         this.block = block;
      }

      public override fun run() {
         this.block.run();
      }

      public override fun toString(): String {
         return "${super.toString()}${this.block}";
      }
   }

   @SourceDebugExtension(["SMAP\nEventLoop.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase$DelayedTask\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n*L\n1#1,547:1\n29#2:548\n29#2:551\n29#2:560\n16#3:549\n16#3:552\n16#3:561\n63#4:550\n64#4,7:553\n*S KotlinDebug\n*F\n+ 1 EventLoop.common.kt\nkotlinx/coroutines/EventLoopImplBase$DelayedTask\n*L\n441#1:548\n443#1:551\n483#1:560\n441#1:549\n443#1:552\n483#1:561\n443#1:550\n443#1:553,7\n*E\n"])
   internal abstract class DelayedTask : Runnable, java.lang.Comparable<EventLoopImplBase.DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
      public final var nanoTime: Long
         private set

      private final var _heap: Any?

      public open var heap: ThreadSafeHeap<*>?
         public open get() {
            return this._heap as? ThreadSafeHeap;
         }

         public open set(value) {
            if (this._heap === EventLoop_commonKt.access$getDISPOSED_TASK$p()) {
               throw new IllegalArgumentException("Failed requirement.".toString());
            } else {
               this._heap = value;
            }
         }


      public open var index: Int
         internal final set

      open fun DelayedTask(nanoTime: Long) {
         this.nanoTime = nanoTime;
         this.index = -1;
      }

      public open operator fun compareTo(other: kotlinx.coroutines.EventLoopImplBase.DelayedTask): Int {
         return if (this.nanoTime - other.nanoTime > 0L) 1 else (if (this.nanoTime - other.nanoTime < 0L) -1 else 0);
      }

      public fun timeToExecute(now: Long): Boolean {
         return now - this.nanoTime >= 0L;
      }

      public fun scheduleTask(now: Long, delayed: kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue, eventLoop: EventLoopImplBase): Int {
         label122: {
            var var27: Byte;
            label67: {
               synchronized (this) {
                  if (this._heap === EventLoop_commonKt.access$getDISPOSED_TASK$p()) {
                     var27 = 2;
                     break label67;
                  }

                  val `this_$iv`: ThreadSafeHeap = delayed;
                  synchronized (delayed as ThreadSafeHeap){} // $VF: monitorenter 

                  try {
                     val firstTask: EventLoopImplBase.DelayedTask = `this_$iv`.firstImpl() as EventLoopImplBase.DelayedTask;
                     if (!EventLoopImplBase.access$isCompleted(eventLoop)) {
                        if (firstTask == null) {
                           delayed.timeNow = now;
                        } else {
                           val minTime: Long = if (firstTask.nanoTime - now >= 0L) now else firstTask.nanoTime;
                           if ((if (firstTask.nanoTime - now >= 0L) now else firstTask.nanoTime) - delayed.timeNow > 0L) {
                              delayed.timeNow = minTime;
                           }
                        }

                        if (this.nanoTime - delayed.timeNow < 0L) {
                           this.nanoTime = delayed.timeNow;
                        }

                        if (true) {
                           `this_$iv`.addImpl(this);
                        }
                     } else {
                        val var26: Byte;
                        return var26;
                     }
                  } catch (var28: java.lang.Throwable) {
                     ;
                  }
               }

               val var25: Byte;
               return var25;
            }

            // $VF: monitorexit
            return var27;
         }
      }

      public override fun dispose() {
         label33: {
            synchronized (this){} // $VF: monitorenter 

            label30: {
               try {
                  val heap: Any = this._heap;
                  if (this._heap === EventLoop_commonKt.access$getDISPOSED_TASK$p()) {
                     break label30;
                  }

                  val var10000: EventLoopImplBase.DelayedTaskQueue = heap as? EventLoopImplBase.DelayedTaskQueue;
                  if ((heap as? EventLoopImplBase.DelayedTaskQueue) != null) {
                     var10000.remove(this);
                  }

                  this._heap = EventLoop_commonKt.access$getDISPOSED_TASK$p();
               } catch (var6: java.lang.Throwable) {
                  // $VF: monitorexit
               }

               // $VF: monitorexit
            }

            // $VF: monitorexit
         }
      }

      public override fun toString(): String {
         return "Delayed[nanos=${this.nanoTime}]";
      }
   }

   internal class DelayedTaskQueue(timeNow: Long) : ThreadSafeHeap<EventLoopImplBase.DelayedTask> {
      public final var timeNow: Long
         private set

      init {
         this.timeNow = timeNow;
      }
   }
}
