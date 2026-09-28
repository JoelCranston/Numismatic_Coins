package kotlinx.coroutines.scheduling

import java.io.Closeable
import java.lang.Thread.State
import java.util.ArrayList
import java.util.concurrent.Executor
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.atomic.AtomicLongFieldUpdater
import java.util.concurrent.locks.LockSupport
import kotlin.enums.EnumEntries
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.Ref.ObjectRef
import kotlinx.atomicfu.AtomicBoolean
import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.AtomicLong
import kotlinx.coroutines.AbstractTimeSource
import kotlinx.coroutines.AbstractTimeSourceKt
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DebugStringsKt
import kotlinx.coroutines.internal.ResizableAtomicArray
import kotlinx.coroutines.internal.Symbol

@SourceDebugExtension(["SMAP\nCoroutineScheduler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler\n+ 2 Tasks.kt\nkotlinx/coroutines/scheduling/TasksKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 5 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 6 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler$Worker\n*L\n1#1,1041:1\n286#1:1044\n284#1:1045\n284#1:1046\n286#1:1047\n281#1:1050\n282#1,5:1051\n292#1:1057\n284#1:1058\n285#1:1059\n284#1:1062\n285#1:1063\n281#1:1064\n289#1:1065\n284#1:1066\n284#1:1069\n285#1:1070\n286#1:1071\n77#2:1042\n77#2:1056\n77#2:1067\n1#3:1043\n29#4:1048\n29#4:1060\n16#5:1049\n16#5:1061\n619#6:1068\n*S KotlinDebug\n*F\n+ 1 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler\n*L\n282#1:1044\n289#1:1045\n290#1:1046\n299#1:1047\n348#1:1050\n377#1:1051,5\n400#1:1057\n444#1:1058\n445#1:1059\n481#1:1062\n482#1:1063\n488#1:1064\n497#1:1065\n497#1:1066\n578#1:1069\n579#1:1070\n580#1:1071\n120#1:1042\n397#1:1056\n514#1:1067\n348#1:1048\n477#1:1060\n348#1:1049\n477#1:1061\n521#1:1068\n*E\n"])
internal class CoroutineScheduler(corePoolSize: Int,
      maxPoolSize: Int,
      idleWorkerKeepAliveNs: Long = TasksKt.IDLE_WORKER_KEEP_ALIVE_NS,
      schedulerName: String = TasksKt.DEFAULT_SCHEDULER_NAME
   ) :
   Executor,
   Closeable {
   public final val corePoolSize: Int
   public final val maxPoolSize: Int
   public final val idleWorkerKeepAliveNs: Long
   public final val schedulerName: String
   public final val globalCpuQueue: GlobalQueue
   public final val globalBlockingQueue: GlobalQueue
   private final val parkedWorkersStack: AtomicLong
   public final val workers: ResizableAtomicArray<kotlinx.coroutines.scheduling.CoroutineScheduler.Worker>
   private final val controlState: AtomicLong

   private final val createdWorkers: Int
      private final inline get() {
         return (int)(access$getControlState$volatile$FU().get(this) and 2097151L);
      }


   private final val availableCpuPermits: Int
      private final inline get() {
         return (int)((getControlState$volatile$FU().get(this) and 9223367638808264704L) shr 42);
      }


   private final val _isTerminated: AtomicBoolean

   public final val isTerminated: Boolean
      public final get() {
         return get_isTerminated$volatile$FU().get(this) == 1;
      }


   init {
      this.corePoolSize = corePoolSize;
      this.maxPoolSize = maxPoolSize;
      this.idleWorkerKeepAliveNs = idleWorkerKeepAliveNs;
      this.schedulerName = schedulerName;
      if (this.corePoolSize < 1) {
         throw new IllegalArgumentException(("Core pool size ${this.corePoolSize} should be at least 1").toString());
      } else if (this.maxPoolSize < this.corePoolSize) {
         throw new IllegalArgumentException(
            ("Max pool size ${this.maxPoolSize} should be greater than or equals to core pool size ${this.corePoolSize}").toString()
         );
      } else if (this.maxPoolSize > 2097150) {
         throw new IllegalArgumentException(("Max pool size ${this.maxPoolSize} should not exceed maximal supported number of threads 2097150").toString());
      } else if (this.idleWorkerKeepAliveNs <= 0L) {
         throw new IllegalArgumentException(("Idle worker keep alive time ${this.idleWorkerKeepAliveNs} must be positive").toString());
      } else {
         this.globalCpuQueue = new GlobalQueue();
         this.globalBlockingQueue = new GlobalQueue();
         this.workers = new ResizableAtomicArray<>((this.corePoolSize + 1) * 2);
         this.controlState$volatile = (long)this.corePoolSize shl 42;
      }
   }

   private fun addToGlobalQueue(task: Task): Boolean {
      return if (task.taskContext) this.globalBlockingQueue.addLast(task) else this.globalCpuQueue.addLast(task);
   }

   public fun parkedWorkersStackTopUpdate(worker: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker, oldIndex: Int, newIndex: Int) {
      val `handler$atomicfu$iv`: AtomicLongFieldUpdater = getParkedWorkersStack$volatile$FU();

      val top: Long;
      val updVersion: Long;
      val updIndex: Int;
      do {
         top = `handler$atomicfu$iv`.get(this);
         val index: Int = (int)(top and 2097151L);
         updVersion = top + 2097152L and -2097152L;
         updIndex = if (index == oldIndex) (if (newIndex == 0) this.parkedWorkersStackNextIndex(worker) else newIndex) else index;
      } while (updIndex < 0 || !getParkedWorkersStack$volatile$FU().compareAndSet(this, top, updVersion | updIndex));
   }

   public fun parkedWorkersStackPush(worker: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker): Boolean {
      if (worker.getNextParkedWorker() != NOT_IN_STACK) {
         return false;
      } else {
         val `handler$atomicfu$iv`: AtomicLongFieldUpdater = getParkedWorkersStack$volatile$FU();

         val top: Long;
         val updVersion: Long;
         val updIndex: Int;
         do {
            top = `handler$atomicfu$iv`.get(this);
            val index: Int = (int)(top and 2097151L);
            updVersion = top + 2097152L and -2097152L;
            updIndex = worker.getIndexInArray();
            if (DebugKt.getASSERTIONS_ENABLED() && updIndex == 0) {
               throw new AssertionError();
            }

            worker.setNextParkedWorker(this.workers.get(index));
         } while (!getParkedWorkersStack$volatile$FU().compareAndSet(this, top, updVersion | updIndex));

         return true;
      }
   }

   private fun parkedWorkersStackPop(): kotlinx.coroutines.scheduling.CoroutineScheduler.Worker? {
      val `handler$atomicfu$iv`: AtomicLongFieldUpdater = getParkedWorkersStack$volatile$FU();

      val top: Long;
      val updVersion: Long;
      val updIndex: Int;
      val var10000: CoroutineScheduler.Worker;
      do {
         top = `handler$atomicfu$iv`.get(this);
         var10000 = this.workers.get((int)(top and 2097151L));
         if (var10000 == null) {
            return null;
         }

         updVersion = top + 2097152L and -2097152L;
         updIndex = this.parkedWorkersStackNextIndex(var10000);
      } while (updIndex < 0 || !getParkedWorkersStack$volatile$FU().compareAndSet(this, top, updVersion | updIndex));

      var10000.setNextParkedWorker(NOT_IN_STACK);
      return var10000;
   }

   private fun parkedWorkersStackNextIndex(worker: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker): Int {
      var next: Any = worker.getNextParkedWorker();

      while (next != NOT_IN_STACK) {
         if (next == null) {
            return 0;
         }

         val nextWorker: CoroutineScheduler.Worker = next as CoroutineScheduler.Worker;
         val updIndex: Int = (next as CoroutineScheduler.Worker).getIndexInArray();
         if (updIndex != 0) {
            return updIndex;
         }

         next = nextWorker.getNextParkedWorker();
      }

      return -1;
   }

   private inline fun createdWorkers(state: Long): Int {
      return (int)(state and 2097151L);
   }

   private inline fun blockingTasks(state: Long): Int {
      return (int)((state and 4398044413952L) shr 21);
   }

   public inline fun availableCpuPermits(state: Long): Int {
      return (int)((state and 9223367638808264704L) shr 42);
   }

   private inline fun incrementCreatedWorkers(): Int {
      return (int)(getControlState$volatile$FU().incrementAndGet(this) and 2097151L);
   }

   private inline fun decrementCreatedWorkers(): Int {
      return (int)(access$getControlState$volatile$FU().getAndDecrement(this) and 2097151L);
   }

   private inline fun incrementBlockingTasks(): Long {
      return getControlState$volatile$FU().addAndGet(this, 2097152L);
   }

   private inline fun decrementBlockingTasks() {
      access$getControlState$volatile$FU().addAndGet(this, -2097152L);
   }

   private inline fun tryAcquireCpuPermit(): Boolean {
      val `handler$atomicfu$iv`: AtomicLongFieldUpdater = access$getControlState$volatile$FU();

      val state: Long;
      do {
         state = `handler$atomicfu$iv`.get(this);
         if ((int)((state and 9223367638808264704L) shr 42) == 0) {
            return false;
         }
      } while (!access$getControlState$volatile$FU().compareAndSet(this, state, state - 4398046511104L));

      return true;
   }

   private inline fun releaseCpuPermit(): Long {
      return access$getControlState$volatile$FU().addAndGet(this, 4398046511104L);
   }

   public override fun execute(command: Runnable) {
      dispatch$default(this, command, false, false, 6, null);
   }

   public override fun close() {
      this.shutdown(10000L);
   }

   public fun shutdown(timeout: Long) {
      if (get_isTerminated$volatile$FU().compareAndSet(this, 0, 1)) {
         val currentWorker: CoroutineScheduler.Worker = this.currentWorker();
         val var10000: Int;
         synchronized (this.workers) {
            var10000 = (int)(access$getControlState$volatile$FU().get(this) and 2097151L);
         }

         val created: Int = var10000;
         var var13: Int = 1;
         if (1 <= var10000) {
            while (true) {
               val var22: Any = this.workers.get(var13);
               val var16: CoroutineScheduler.Worker = var22 as CoroutineScheduler.Worker;
               if (var22 as CoroutineScheduler.Worker != currentWorker) {
                  while (worker.getState() != State.TERMINATED) {
                     LockSupport.unpark(var16);
                     var16.join(timeout);
                  }

                  if (DebugKt.getASSERTIONS_ENABLED() && var16.state != CoroutineScheduler.WorkerState.TERMINATED) {
                     throw new AssertionError();
                  }

                  var16.localQueue.offloadAllWorkTo(this.globalBlockingQueue);
               }

               if (var13 == created) {
                  break;
               }

               var13++;
            }
         }

         this.globalBlockingQueue.close();
         this.globalCpuQueue.close();

         while (true) {
            label62: {
               if (currentWorker != null) {
                  var23 = currentWorker.findTask(true);
                  if (var23 != null) {
                     break label62;
                  }
               }

               var23 = this.globalCpuQueue.removeFirstOrNull();
               if (var23 == null) {
                  var23 = this.globalBlockingQueue.removeFirstOrNull();
                  if (var23 == null) {
                     if (currentWorker != null) {
                        currentWorker.tryReleaseCpu(CoroutineScheduler.WorkerState.TERMINATED);
                     }

                     if (DebugKt.getASSERTIONS_ENABLED()
                        && (int)((getControlState$volatile$FU().get(this) and 9223367638808264704L) shr 42) != this.corePoolSize) {
                        throw new AssertionError();
                     }

                     getParkedWorkersStack$volatile$FU().set(this, 0L);
                     getControlState$volatile$FU().set(this, 0L);
                     return;
                  }
               }
            }

            this.runSafely(var23);
         }
      }
   }

   public fun dispatch(block: Runnable, taskContext: Boolean = false, fair: Boolean = false) {
      val var10000: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
      if (var10000 != null) {
         var10000.trackTask();
      }

      val task: Task = this.createTask(block, taskContext);
      val isBlockingTask: Boolean = task.taskContext;
      val stateSnapshot: Long = if (task.taskContext) getControlState$volatile$FU().addAndGet(this, 2097152L) else 0L;
      val var10: Task = this.submitToLocalQueue(this.currentWorker(), task, fair);
      if (var10 != null && !this.addToGlobalQueue(var10)) {
         throw new RejectedExecutionException("${this.schedulerName} was terminated");
      } else {
         if (isBlockingTask) {
            this.signalBlockingWork(stateSnapshot);
         } else {
            this.signalCpuWork();
         }
      }
   }

   public fun createTask(block: Runnable, taskContext: Boolean): Task {
      val nanoTime: Long = TasksKt.schedulerTimeSource.nanoTime();
      if (block is Task) {
         (block as Task).submissionTime = nanoTime;
         (block as Task).taskContext = taskContext;
         return block as Task;
      } else {
         return TasksKt.asTask(block, nanoTime, taskContext);
      }
   }

   private fun signalBlockingWork(stateSnapshot: Long) {
      if (!this.tryUnpark()) {
         if (!this.tryCreateWorker(stateSnapshot)) {
            this.tryUnpark();
         }
      }
   }

   public fun signalCpuWork() {
      if (!this.tryUnpark()) {
         if (!tryCreateWorker$default(this, 0L, 1, null)) {
            this.tryUnpark();
         }
      }
   }

   private fun tryCreateWorker(state: Long = getControlState$volatile$FU().get(this)): Boolean {
      if (RangesKt.coerceAtLeast((int)(state and 2097151L) - (int)((state and 4398044413952L) shr 21), 0) < this.corePoolSize) {
         val var8: Int = this.createNewWorker();
         if (var8 == 1 && this.corePoolSize > 1) {
            this.createNewWorker();
         }

         if (var8 > 0) {
            return true;
         }
      }

      return false;
   }

   private fun tryUnpark(): Boolean {
      val var10000: CoroutineScheduler.Worker;
      do {
         var10000 = this.parkedWorkersStackPop();
         if (var10000 == null) {
            return false;
         }
      } while (!CoroutineScheduler.Worker.getWorkerCtl$volatile$FU().compareAndSet(var10000, -1, 0));

      LockSupport.unpark(var10000);
      return true;
   }

   private fun createNewWorker(): Int {
      var `lock$iv`: Any;
      var var22: Byte;
      label75: {
         var var21: Byte;
         label61: {
            `lock$iv` = this.workers;
            val var19: Int;
            val var24: CoroutineScheduler.Worker;
            synchronized (this.workers) {
               if (this.isTerminated()) {
                  var22 = -1;
                  break label75;
               }

               val state: Long = getControlState$volatile$FU().get(this);
               val created: Int = (int)(state and 2097151L);
               val var26: Int = RangesKt.coerceAtLeast((int)(state and 2097151L) - (int)((state and 4398044413952L) shr 21), 0);
               if (var26 >= this.corePoolSize) {
                  var21 = 0;
                  break label61;
               }

               if (created >= this.maxPoolSize) {
                  return 0;
               }

               val var27: Int = (int)(access$getControlState$volatile$FU().get(this) and 2097151L) + 1;
               if (var27 <= 0 || this.workers.get(var27) != null) {
                  throw new IllegalArgumentException("Failed requirement.".toString());
               }

               var24 = new CoroutineScheduler.Worker((int)this, var27);
               this.workers.setSynchronized(var27, var24);
               if (var27 != (int)(getControlState$volatile$FU().incrementAndGet(this) and 2097151L)) {
                  throw new IllegalArgumentException("Failed requirement.".toString());
               }

               var19 = var26 + 1;
            }

            var24.start();
            return var19;
         }

         // $VF: monitorexit
         return var21;
      }

      // $VF: monitorexit
      return var22;
   }

   private fun kotlinx.coroutines.scheduling.CoroutineScheduler.Worker?.submitToLocalQueue(task: Task, fair: Boolean): Task? {
      if (`$this$submitToLocalQueue` == null) {
         return task;
      } else if (`$this$submitToLocalQueue`.state === CoroutineScheduler.WorkerState.TERMINATED) {
         return task;
      } else if (!task.taskContext && `$this$submitToLocalQueue`.state === CoroutineScheduler.WorkerState.BLOCKING) {
         return task;
      } else {
         `$this$submitToLocalQueue`.mayHaveLocalTasks = true;
         return `$this$submitToLocalQueue`.localQueue.add(task, fair);
      }
   }

   private fun currentWorker(): kotlinx.coroutines.scheduling.CoroutineScheduler.Worker? {
      val var1: Thread = Thread.currentThread();
      return if ((var1 as? CoroutineScheduler.Worker) != null)
         (if (CoroutineScheduler.Worker.access$getThis$0$p(var1 as? CoroutineScheduler.Worker) == this) (var1 as? CoroutineScheduler.Worker) else null)
         else
         null;
   }

   public override fun toString(): String {
      var parkedWorkers: Int = 0;
      var blockingWorkers: Int = 0;
      var cpuWorkers: Int = 0;
      var dormant: Int = 0;
      var terminated: Int = 0;
      val queueSizes: ArrayList = new ArrayList();
      var state: Int = 1;

      for (int var8 = this.workers.currentLength(); index < var8; index++) {
         val var10000: CoroutineScheduler.Worker = this.workers.get(state);
         if (var10000 != null) {
            val `this_$iv`: Int = var10000.localQueue.getSize$kotlinx_coroutines_core();
            switch (CoroutineScheduler.WhenMappings.$EnumSwitchMapping$0[var10000.state.ordinal()]) {
               case 1:
                  parkedWorkers++;
                  break;
               case 2:
                  blockingWorkers++;
                  queueSizes.add("$`this_$iv`b");
                  break;
               case 3:
                  cpuWorkers++;
                  queueSizes.add("$`this_$iv`c");
                  break;
               case 4:
                  dormant++;
                  if (`this_$iv` > 0) {
                     queueSizes.add("$`this_$iv`d");
                  }
                  break;
               case 5:
                  terminated++;
                  break;
               default:
                  throw new NoWhenBranchMatchedException();
            }
         }
      }

      val var12: Long = getControlState$volatile$FU().get(this);
      val var13: StringBuilder = new StringBuilder();
      var13.append(this.schedulerName)
         .append('@')
         .append(DebugStringsKt.getHexAddress(this))
         .append("[Pool Size {core = ")
         .append(this.corePoolSize)
         .append(", max = ")
         .append(this.maxPoolSize)
         .append("}, Worker States {CPU = ")
         .append(cpuWorkers)
         .append(", blocking = ")
         .append(blockingWorkers)
         .append(", parked = ")
         .append(parkedWorkers)
         .append(", dormant = ")
         .append(dormant)
         .append(", terminated = ")
         .append(terminated)
         .append("}, running workers queues = ")
         .append(queueSizes)
         .append(", global CPU queue size = ")
         .append(this.globalCpuQueue.getSize())
         .append(", global blocking queue size = ")
         .append(this.globalBlockingQueue.getSize());
      var13.append(", Control State {created workers= ")
         .append((int)(var12 and 2097151L))
         .append(", blocking tasks = ")
         .append((int)((var12 and 4398044413952L) shr 21))
         .append(", CPUs acquired = ")
         .append(this.corePoolSize - (int)((var12 and 9223367638808264704L) shr 42))
         .append("}]");
      return var13.toString();
   }

   public fun runSafely(task: Task) {
      label36: {
         label35: {
            try {
               try {
                  task.run();
                  break label35;
               } catch (var4: java.lang.Throwable) {
                  val thread: Thread = Thread.currentThread();
                  thread.getUncaughtExceptionHandler().uncaughtException(thread, var4);
               }
            } catch (var5: java.lang.Throwable) {
               val var10001: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
               if (var10001 != null) {
                  var10001.unTrackTask();
               }
            }

            val var10000: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
            if (var10000 != null) {
               var10000.unTrackTask();
            }
         }

         val var8: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
         if (var8 != null) {
            var8.unTrackTask();
         }
      }
   }

   public companion object {
      public final val NOT_IN_STACK: Symbol
      private const val PARKED: Int
      private const val CLAIMED: Int
      private const val TERMINATED: Int
      private const val BLOCKING_SHIFT: Int
      private const val CREATED_MASK: Long
      private const val BLOCKING_MASK: Long
      private const val CPU_PERMITS_SHIFT: Int
      private const val CPU_PERMITS_MASK: Long
      internal const val MIN_SUPPORTED_POOL_SIZE: Int
      internal const val MAX_SUPPORTED_POOL_SIZE: Int
      private const val PARKED_INDEX_MASK: Long
      private const val PARKED_VERSION_MASK: Long
      private const val PARKED_VERSION_INC: Long
   }

   @SourceDebugExtension(["SMAP\nCoroutineScheduler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler$Worker\n+ 2 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Tasks.kt\nkotlinx/coroutines/scheduling/TasksKt\n+ 5 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 6 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,1041:1\n298#2,2:1042\n286#2:1044\n300#2,4:1045\n305#2:1049\n295#2,2:1050\n295#2,2:1055\n281#2:1059\n290#2:1060\n284#2:1061\n281#2:1062\n1#3:1052\n77#4:1053\n77#4:1054\n29#5:1057\n16#6:1058\n*S KotlinDebug\n*F\n+ 1 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler$Worker\n*L\n684#1:1042,2\n684#1:1044\n684#1:1045,4\n699#1:1049\n773#1:1050,2\n821#1:1055,2\n872#1:1059\n898#1:1060\n898#1:1061\n971#1:1062\n812#1:1053\n815#1:1054\n868#1:1057\n868#1:1058\n*E\n"])
   internal inner class Worker private constructor() : Thread {
      public final var indexInArray: Int
         internal final set(index) {
            this.setName("${this.this$0.schedulerName}-worker-${if (index == 0) "TERMINATED" else java.lang.String.valueOf(index)}");
            this.indexInArray = index;
         }


      public final val scheduler: CoroutineScheduler
         public final inline get() {
            return access$getThis$0$p(this);
         }


      public final val localQueue: WorkQueue
      private final val stolenTask: ObjectRef<Task?>

      public final var state: kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState
         private set

      public final val workerCtl: AtomicInt
      private final var terminationDeadline: Long

      public final var nextParkedWorker: Any?
         internal set

      private final var minDelayUntilStealableTaskNs: Long
      private final var rngState: Int

      public final var mayHaveLocalTasks: Boolean
         private set

      init {
         this.this$0 = `this$0`;
         this.setDaemon(true);
         this.setContextClassLoader(this.this$0.getClass().getClassLoader());
         this.localQueue = new WorkQueue();
         this.stolenTask = new Ref.ObjectRef<>();
         this.state = CoroutineScheduler.WorkerState.DORMANT;
         this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
         val `$this$rngState_u24lambda_u240`: CoroutineScheduler.Worker = this;
         val seed: Int = (int)System.nanoTime();
         this.rngState = if (seed != 0) seed else 42;
      }

      public constructor(index: Int) : this(`this$0`) {
         this.setIndexInArray(index);
      }

      private fun tryAcquireCpuPermit(): Boolean {
         var var10000: Boolean;
         if (this.state === CoroutineScheduler.WorkerState.CPU_ACQUIRED) {
            var10000 = true;
         } else {
            val `this_$iv`: CoroutineScheduler = this.this$0;
            val `handler$atomicfu$iv$iv`: AtomicLongFieldUpdater = CoroutineScheduler.access$getControlState$volatile$FU();

            while (true) {
               val `state$iv`: Long = `handler$atomicfu$iv$iv`.get(`this_$iv`);
               if ((int)((`state$iv` and 9223367638808264704L) shr 42) == 0) {
                  var10000 = false;
                  break;
               }

               if (CoroutineScheduler.access$getControlState$volatile$FU().compareAndSet(`this_$iv`, `state$iv`, `state$iv` - 4398046511104L)) {
                  var10000 = true;
                  break;
               }
            }

            if (var10000) {
               this.state = CoroutineScheduler.WorkerState.CPU_ACQUIRED;
               var10000 = true;
            } else {
               var10000 = false;
            }
         }

         return var10000;
      }

      public fun tryReleaseCpu(newState: kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState): Boolean {
         val hadCpu: Boolean = this.state === CoroutineScheduler.WorkerState.CPU_ACQUIRED;
         if (this.state === CoroutineScheduler.WorkerState.CPU_ACQUIRED) {
            val `this_$iv`: CoroutineScheduler = this.this$0;
            CoroutineScheduler.access$getControlState$volatile$FU().addAndGet(`this_$iv`, 4398046511104L);
         }

         if (this.state != newState) {
            this.state = newState;
         }

         return hadCpu;
      }

      public override fun run() {
         this.runWorker();
      }

      private fun runWorker() {
         var rescanned: Boolean = false;

         while (!this.this$0.isTerminated() && this.state != CoroutineScheduler.WorkerState.TERMINATED) {
            val task: Task = this.findTask(this.mayHaveLocalTasks);
            if (task != null) {
               rescanned = false;
               this.minDelayUntilStealableTaskNs = 0L;
               this.executeTask(task);
            } else {
               this.mayHaveLocalTasks = false;
               if (this.minDelayUntilStealableTaskNs != 0L) {
                  if (!rescanned) {
                     rescanned = true;
                  } else {
                     rescanned = false;
                     this.tryReleaseCpu(CoroutineScheduler.WorkerState.PARKING);
                     Thread.interrupted();
                     LockSupport.parkNanos(this.minDelayUntilStealableTaskNs);
                     this.minDelayUntilStealableTaskNs = 0L;
                  }
               } else {
                  this.tryPark();
               }
            }
         }

         this.tryReleaseCpu(CoroutineScheduler.WorkerState.TERMINATED);
      }

      public fun runSingleTask(): Long {
         val stateSnapshot: CoroutineScheduler.WorkerState = this.state;
         val isCpuThread: Boolean = this.state === CoroutineScheduler.WorkerState.CPU_ACQUIRED;
         val task: Task = if (this.state === CoroutineScheduler.WorkerState.CPU_ACQUIRED) this.findCpuTask() else this.findBlockingTask();
         if (task == null) {
            return if (this.minDelayUntilStealableTaskNs == 0L) -1L else this.minDelayUntilStealableTaskNs;
         } else {
            this.this$0.runSafely(task);
            if (!isCpuThread) {
               val var4: CoroutineScheduler = this.this$0;
               CoroutineScheduler.access$getControlState$volatile$FU().addAndGet(var4, -2097152L);
            }

            if (DebugKt.getASSERTIONS_ENABLED() && this.state != stateSnapshot) {
               throw new AssertionError();
            } else {
               return 0L;
            }
         }
      }

      public fun isIo(): Boolean {
         return this.state === CoroutineScheduler.WorkerState.BLOCKING;
      }

      private fun tryPark() {
         if (!this.inStack()) {
            this.this$0.parkedWorkersStackPush(this);
         } else {
            getWorkerCtl$volatile$FU().set(this, -1);

            while (
               this.inStack()
                  && getWorkerCtl$volatile$FU().get(this) == -1
                  && !this.this$0.isTerminated()
                  && this.state != CoroutineScheduler.WorkerState.TERMINATED
            ) {
               this.tryReleaseCpu(CoroutineScheduler.WorkerState.PARKING);
               Thread.interrupted();
               this.park();
            }
         }
      }

      private fun inStack(): Boolean {
         return this.nextParkedWorker != CoroutineScheduler.NOT_IN_STACK;
      }

      private fun executeTask(task: Task) {
         this.terminationDeadline = 0L;
         if (this.state === CoroutineScheduler.WorkerState.PARKING) {
            if (DebugKt.getASSERTIONS_ENABLED() && !task.taskContext) {
               throw new AssertionError();
            }

            this.state = CoroutineScheduler.WorkerState.BLOCKING;
         }

         if (task.taskContext) {
            if (this.tryReleaseCpu(CoroutineScheduler.WorkerState.BLOCKING)) {
               this.this$0.signalCpuWork();
            }

            this.this$0.runSafely(task);
            val var5: CoroutineScheduler = this.this$0;
            CoroutineScheduler.access$getControlState$volatile$FU().addAndGet(var5, -2097152L);
            val var6: CoroutineScheduler.WorkerState = this.state;
            if (this.state != CoroutineScheduler.WorkerState.TERMINATED) {
               if (DebugKt.getASSERTIONS_ENABLED() && var6 != CoroutineScheduler.WorkerState.BLOCKING) {
                  throw new AssertionError();
               }

               this.state = CoroutineScheduler.WorkerState.DORMANT;
            }
         } else {
            this.this$0.runSafely(task);
         }
      }

      public fun nextInt(upperBound: Int): Int {
         val var6: Int = this.rngState xor this.rngState shl 13 xor (this.rngState xor this.rngState shl 13) shr 17 xor (
            this.rngState xor this.rngState shl 13 xor (this.rngState xor this.rngState shl 13) shr 17
         ) shl 5;
         this.rngState = this.rngState xor this.rngState shl 13 xor (this.rngState xor this.rngState shl 13) shr 17 xor (
            this.rngState xor this.rngState shl 13 xor (this.rngState xor this.rngState shl 13) shr 17
         ) shl 5;
         return if ((upperBound - 1 and upperBound) == 0) var6 and upperBound - 1 else (var6 and Integer.MAX_VALUE) % upperBound;
      }

      private fun park() {
         if (this.terminationDeadline == 0L) {
            this.terminationDeadline = System.nanoTime() + this.this$0.idleWorkerKeepAliveNs;
         }

         LockSupport.parkNanos(this.this$0.idleWorkerKeepAliveNs);
         if (System.nanoTime() - this.terminationDeadline >= 0L) {
            this.terminationDeadline = 0L;
            this.tryTerminateWorker();
         }
      }

      private fun tryTerminateWorker() {
         label43: {
            val `lock$iv`: ResizableAtomicArray = this.this$0.workers;
            val var2: CoroutineScheduler = this.this$0;
            synchronized (this.this$0.workers){} // $VF: monitorenter 

            label40: {
               label39: {
                  label38: {
                     try {
                        if (var2.isTerminated()) {
                           break label40;
                        }

                        if ((int)(CoroutineScheduler.access$getControlState$volatile$FU().get(var2) and 2097151L) <= var2.corePoolSize) {
                           break label39;
                        }

                        if (!getWorkerCtl$volatile$FU().compareAndSet(this, -1, 1)) {
                           break label38;
                        }

                        val oldIndex: Int = this.indexInArray;
                        this.setIndexInArray(0);
                        var2.parkedWorkersStackTopUpdate(this, oldIndex, 0);
                        val var18: Int = (int)(CoroutineScheduler.access$getControlState$volatile$FU().getAndDecrement(var2) and 2097151L);
                        if (var18 != oldIndex) {
                           val var10000: Any = var2.workers.get(var18);
                           val lastWorker: CoroutineScheduler.Worker = var10000 as CoroutineScheduler.Worker;
                           var2.workers.setSynchronized(oldIndex, var10000 as CoroutineScheduler.Worker);
                           lastWorker.setIndexInArray(oldIndex);
                           var2.parkedWorkersStackTopUpdate(lastWorker, var18, oldIndex);
                        }

                        var2.workers.setSynchronized(var18, null);
                     } catch (var16: java.lang.Throwable) {
                        // $VF: monitorexit
                     }

                     // $VF: monitorexit
                  }

                  // $VF: monitorexit
               }

               // $VF: monitorexit
            }

            // $VF: monitorexit
         }
      }

      public fun findTask(mayHaveLocalTasks: Boolean): Task? {
         return if (this.tryAcquireCpuPermit()) this.findAnyTask(mayHaveLocalTasks) else this.findBlockingTask();
      }

      private fun findBlockingTask(): Task? {
         var var10000: Task = this.localQueue.pollBlocking();
         if (var10000 == null) {
            var10000 = this.this$0.globalBlockingQueue.removeFirstOrNull();
            if (var10000 == null) {
               var10000 = this.trySteal(1);
            }
         }

         return var10000;
      }

      private fun findCpuTask(): Task? {
         var var10000: Task = this.localQueue.pollCpu();
         if (var10000 == null) {
            var10000 = this.this$0.globalBlockingQueue.removeFirstOrNull();
            if (var10000 == null) {
               var10000 = this.trySteal(2);
            }
         }

         return var10000;
      }

      private fun findAnyTask(scanLocalQueue: Boolean): Task? {
         if (scanLocalQueue) {
            val globalFirst: Boolean = this.nextInt(2 * this.this$0.corePoolSize) == 0;
            if (globalFirst) {
               val var3: Task = this.pollGlobalQueues();
               if (var3 != null) {
                  return var3;
               }
            }

            var var8: Task = this.localQueue.poll();
            if (var8 != null) {
               return var8;
            }

            if (!globalFirst) {
               var8 = this.pollGlobalQueues();
               if (var8 != null) {
                  return var8;
               }
            }
         } else {
            val var7: Task = this.pollGlobalQueues();
            if (var7 != null) {
               return var7;
            }
         }

         return this.trySteal(3);
      }

      private fun pollGlobalQueues(): Task? {
         label15:
         if (this.nextInt(2) == 0) {
            val var4: Task = this.this$0.globalCpuQueue.removeFirstOrNull();
            return var4 ?: this.this$0.globalBlockingQueue.removeFirstOrNull();
         } else {
            val var1: Task = this.this$0.globalBlockingQueue.removeFirstOrNull();
            return var1 ?: this.this$0.globalCpuQueue.removeFirstOrNull();
         }
      }

      private fun trySteal(stealingMode: Int): Task? {
         val currentIndex: CoroutineScheduler = this.this$0;
         val created: Int = (int)(CoroutineScheduler.access$getControlState$volatile$FU().get(currentIndex) and 2097151L);
         if (created < 2) {
            return null;
         } else {
            var var16: Int = this.nextInt(created);
            var var17: Long = java.lang.Long.MAX_VALUE;
            val var5: CoroutineScheduler = this.this$0;

            for (int var6 = 0; var6 < created; var6++) {
               if (++var16 > created) {
                  var16 = 1;
               }

               val worker: CoroutineScheduler.Worker = var5.workers.get(var16);
               if (worker != null && worker != this) {
                  val stealResult: Long = worker.localQueue.trySteal(stealingMode, this.stolenTask);
                  if (stealResult == -1L) {
                     val result: Task = this.stolenTask.element;
                     this.stolenTask.element = null;
                     return result;
                  }

                  if (stealResult > 0L) {
                     var17 = Math.min(var17, stealResult);
                  }
               }
            }

            this.minDelayUntilStealableTaskNs = if (var17 != java.lang.Long.MAX_VALUE) var17 else 0L;
            return null;
         }
      }
   }

   public enum class WorkerState {
      CPU_ACQUIRED,
      BLOCKING,
      PARKING,
      DORMANT,
      TERMINATED
      @JvmStatic
      fun getEntries(): EnumEntries<CoroutineScheduler.WorkerState> {
         return $ENTRIES;
      }
   }
}
