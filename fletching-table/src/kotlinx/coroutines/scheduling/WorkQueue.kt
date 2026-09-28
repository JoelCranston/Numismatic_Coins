package kotlinx.coroutines.scheduling

import java.util.concurrent.atomic.AtomicReferenceArray
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.Ref.ObjectRef
import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.DebugKt

@SourceDebugExtension(["SMAP\nWorkQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkQueue.kt\nkotlinx/coroutines/scheduling/WorkQueue\n+ 2 Tasks.kt\nkotlinx/coroutines/scheduling/TasksKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 WorkQueue.kt\nkotlinx/coroutines/scheduling/WorkQueueKt\n*L\n1#1,251:1\n77#2:252\n77#2:253\n77#2:254\n77#2:257\n77#2:258\n1#3:255\n21#4:256\n*S KotlinDebug\n*F\n+ 1 WorkQueue.kt\nkotlinx/coroutines/scheduling/WorkQueue\n*L\n91#1:252\n158#1:253\n181#1:254\n201#1:257\n245#1:258\n201#1:256\n*E\n"])
internal class WorkQueue {
   private final val bufferSize: Int
      private final get() {
         return getProducerIndex$volatile$FU().get(this) - getConsumerIndex$volatile$FU().get(this);
      }


   internal final val size: Int
      internal final get() {
         return if (getLastScheduledTask$volatile$FU().get(this) != null) this.getBufferSize() + 1 else this.getBufferSize();
      }


   private final val buffer: AtomicReferenceArray<Task?> = new AtomicReferenceArray(128)
   private final val lastScheduledTask: AtomicRef<Task?>
   private final val producerIndex: AtomicInt
   private final val consumerIndex: AtomicInt
   private final val blockingTasksInBuffer: AtomicInt

   public fun poll(): Task? {
      var var10000: Task = getLastScheduledTask$volatile$FU().getAndSet(this, null) as Task;
      if (var10000 == null) {
         var10000 = this.pollBuffer();
      }

      return var10000;
   }

   public fun add(task: Task, fair: Boolean = false): Task? {
      label11:
      if (fair) {
         return this.addLast(task);
      } else {
         val var10000: Task = getLastScheduledTask$volatile$FU().getAndSet(this, task);
         return if (var10000 == null) null else this.addLast(var10000);
      }
   }

   private fun addLast(task: Task): Task? {
      if (this.getBufferSize() == 127) {
         return task;
      } else {
         if (task.taskContext) {
            getBlockingTasksInBuffer$volatile$FU().incrementAndGet(this);
         }

         val nextIndex: Int = getProducerIndex$volatile$FU().get(this) and 127;

         while (this.buffer.get(nextIndex) != null) {
            Thread.yield();
         }

         this.buffer.lazySet(nextIndex, task);
         getProducerIndex$volatile$FU().incrementAndGet(this);
         return null;
      }
   }

   public fun trySteal(stealingMode: Int, stolenTaskRef: ObjectRef<Task?>): Long {
      val task: Task = if (stealingMode == 3) this.pollBuffer() else this.stealWithExclusiveMode(stealingMode);
      if (task != null) {
         stolenTaskRef.element = (T)task;
         return -1L;
      } else {
         return this.tryStealLastScheduled(stealingMode, stolenTaskRef);
      }
   }

   private fun stealWithExclusiveMode(stealingMode: Int): Task? {
      var start: Int = getConsumerIndex$volatile$FU().get(this);
      val end: Int = getProducerIndex$volatile$FU().get(this);
      val onlyBlocking: Boolean = stealingMode == 1;

      while (start != end) {
         if (onlyBlocking && getBlockingTasksInBuffer$volatile$FU().get(this) == 0) {
            return null;
         }

         val var10000: Task = this.tryExtractFromTheMiddle(start++, onlyBlocking);
         if (var10000 != null) {
            return var10000;
         }
      }

      return null;
   }

   public fun pollBlocking(): Task? {
      return this.pollWithExclusiveMode(true);
   }

   public fun pollCpu(): Task? {
      return this.pollWithExclusiveMode(false);
   }

   private fun pollWithExclusiveMode(onlyBlocking: Boolean): Task? {
      while (true) {
         val var10000: Task = getLastScheduledTask$volatile$FU().get(this) as Task;
         if (var10000 != null) {
            if (var10000.taskContext == onlyBlocking) {
               if (!getLastScheduledTask$volatile$FU().compareAndSet(this, var10000, null)) {
                  continue;
               }

               return var10000;
            }
         }

         val var5: Int = getConsumerIndex$volatile$FU().get(this);
         var end: Int = getProducerIndex$volatile$FU().get(this);

         while (start != end) {
            if (onlyBlocking && getBlockingTasksInBuffer$volatile$FU().get(this) == 0) {
               return null;
            }

            val var6: Task = this.tryExtractFromTheMiddle(--end, onlyBlocking);
            if (var6 != null) {
               return var6;
            }
         }

         return null;
      }
   }

   private fun tryExtractFromTheMiddle(index: Int, onlyBlocking: Boolean): Task? {
      val arrayIndex: Int = index and 127;
      val value: Task = this.buffer.get(index and 127);
      if (value != null && value.taskContext == onlyBlocking && this.buffer.compareAndSet(arrayIndex, value, null)) {
         if (onlyBlocking) {
            getBlockingTasksInBuffer$volatile$FU().decrementAndGet(this);
         }

         return value;
      } else {
         return null;
      }
   }

   public fun offloadAllWorkTo(globalQueue: GlobalQueue) {
      val var10000: Task = getLastScheduledTask$volatile$FU().getAndSet(this, null) as Task;
      if (var10000 != null) {
         globalQueue.addLast(var10000);
      }

      while (this.pollTo(globalQueue)) {
      }
   }

   private fun tryStealLastScheduled(stealingMode: Int, stolenTaskRef: ObjectRef<Task?>): Long {
      val var10000: Task;
      do {
         var10000 = getLastScheduledTask$volatile$FU().get(this) as Task;
         if (var10000 == null) {
            return -2L;
         }

         if (((if (var10000.taskContext) 1 else 2) and stealingMode) == 0) {
            return -2L;
         }

         val staleness: Long = TasksKt.schedulerTimeSource.nanoTime() - var10000.submissionTime;
         if (staleness < TasksKt.WORK_STEALING_TIME_RESOLUTION_NS) {
            return TasksKt.WORK_STEALING_TIME_RESOLUTION_NS - staleness;
         }
      } while (!getLastScheduledTask$volatile$FU().compareAndSet(this, var10000, null));

      stolenTaskRef.element = (T)var10000;
      return -1L;
   }

   private fun pollTo(queue: GlobalQueue): Boolean {
      val var10000: Task = this.pollBuffer();
      if (var10000 == null) {
         return false;
      } else {
         queue.addLast(var10000);
         return true;
      }
   }

   private fun pollBuffer(): Task? {
      while (true) {
         val tailLocal: Int = getConsumerIndex$volatile$FU().get(this);
         if (tailLocal - getProducerIndex$volatile$FU().get(this) == 0) {
            return null;
         }

         val index: Int = tailLocal and 127;
         if (getConsumerIndex$volatile$FU().compareAndSet(this, tailLocal, tailLocal + 1)) {
            val var10000: Task = this.buffer.getAndSet(index, null);
            if (var10000 != null) {
               this.decrementIfBlocking(var10000);
               return var10000;
            }
         }
      }
   }

   private fun Task?.decrementIfBlocking() {
      if (`$this$decrementIfBlocking` != null
         && `$this$decrementIfBlocking`.taskContext
         && DebugKt.getASSERTIONS_ENABLED()
         && getBlockingTasksInBuffer$volatile$FU().decrementAndGet(this) < 0) {
         throw new AssertionError();
      }
   }
}
