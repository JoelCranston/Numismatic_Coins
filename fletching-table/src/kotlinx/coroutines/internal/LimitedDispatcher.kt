package kotlinx.coroutines.internal

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicInt
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandlerKt
import kotlinx.coroutines.DefaultExecutorKt
import kotlinx.coroutines.Delay
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.InternalCoroutinesApi

@SourceDebugExtension(["SMAP\nLimitedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcher\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,154:1\n62#1,18:155\n62#1,18:173\n29#2:191\n29#2:193\n16#3:192\n16#3:194\n*S KotlinDebug\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcher\n*L\n44#1:155,18\n51#1:173,18\n85#1:191\n98#1:193\n85#1:192\n98#1:194\n*E\n"])
internal class LimitedDispatcher(dispatcher: CoroutineDispatcher, parallelism: Int, name: String?) : CoroutineDispatcher, Delay {
   private final val dispatcher: CoroutineDispatcher
   private final val parallelism: Int
   private final val name: String?
   private final val runningWorkers: AtomicInt
   private final val queue: LockFreeTaskQueue<Runnable>
   private final val workerAllocationLock: Any

   init {
      var var10001: Delay = dispatcher as? Delay;
      if ((dispatcher as? Delay) == null) {
         var10001 = DefaultExecutorKt.getDefaultDelay();
      }

      this.$$delegate_0 = var10001;
      this.dispatcher = dispatcher;
      this.parallelism = parallelism;
      this.name = name;
      this.queue = new LockFreeTaskQueue<>(false);
      this.workerAllocationLock = new Object();
   }

   public override fun limitedParallelism(parallelism: Int, name: String?): CoroutineDispatcher {
      LimitedDispatcherKt.checkParallelism(parallelism);
      return if (parallelism >= this.parallelism) LimitedDispatcherKt.namedOrThis(this, name) else super.limitedParallelism(parallelism, name);
   }

   public override fun dispatch(context: CoroutineContext, block: Runnable) {
      val `this_$iv`: LimitedDispatcher = this;
      this.queue.addLast(block);
      if (getRunningWorkers$volatile$FU().get(this) < this.parallelism && this.tryAllocateWorker()) {
         val var10000: Runnable = this.obtainTaskOrDeallocateWorker();
         if (var10000 != null) {
            val `task$iv`: Runnable = var10000;

            try {
               DispatchedContinuationKt.safeDispatch(this.dispatcher, this, `this_$iv`.new Worker(`this_$iv`, `task$iv`));
            } catch (var8: java.lang.Throwable) {
               getRunningWorkers$volatile$FU().decrementAndGet(this);
               throw var8;
            }
         }
      }
   }

   @InternalCoroutinesApi
   public override fun dispatchYield(context: CoroutineContext, block: Runnable) {
      val `this_$iv`: LimitedDispatcher = this;
      this.queue.addLast(block);
      if (getRunningWorkers$volatile$FU().get(this) < this.parallelism && this.tryAllocateWorker()) {
         val var10000: Runnable = this.obtainTaskOrDeallocateWorker();
         if (var10000 != null) {
            val `task$iv`: Runnable = var10000;

            try {
               this.dispatcher.dispatchYield(this, `this_$iv`.new Worker(`this_$iv`, `task$iv`));
            } catch (var8: java.lang.Throwable) {
               getRunningWorkers$volatile$FU().decrementAndGet(this);
               throw var8;
            }
         }
      }
   }

   private inline fun dispatchInternal(block: Runnable, startWorker: (kotlinx.coroutines.internal.LimitedDispatcher.Worker) -> Unit) {
      this.queue.addLast(block);
      if (getRunningWorkers$volatile$FU().get(this) < this.parallelism) {
         if (this.tryAllocateWorker()) {
            val var10000: Runnable = this.obtainTaskOrDeallocateWorker();
            if (var10000 != null) {
               val task: Runnable = var10000;

               try {
                  startWorker.invoke(new LimitedDispatcher.Worker(this, task));
               } catch (var6: java.lang.Throwable) {
                  getRunningWorkers$volatile$FU().decrementAndGet(this);
                  throw var6;
               }
            }
         }
      }
   }

   private fun tryAllocateWorker(): Boolean {
      label25: {
         val `lock$iv`: Any = this.workerAllocationLock;
         synchronized (this.workerAllocationLock){} // $VF: monitorenter 

         label22: {
            try {
               if (getRunningWorkers$volatile$FU().get(this) >= this.parallelism) {
                  break label22;
               }

               getRunningWorkers$volatile$FU().incrementAndGet(this);
            } catch (var9: java.lang.Throwable) {
               // $VF: monitorexit
            }

            // $VF: monitorexit
         }

         // $VF: monitorexit
      }
   }

   private fun obtainTaskOrDeallocateWorker(): Runnable? {
      while (true) {
         val nextTask: Runnable = this.queue.removeFirstOrNull();
         if (nextTask == null) {
            val `lock$iv`: Any = this.workerAllocationLock;
            synchronized (this.workerAllocationLock){} // $VF: monitorenter 

            label25: {
               try {
                  getRunningWorkers$volatile$FU().decrementAndGet(this);
                  if (this.queue.getSize() == 0) {
                     break label25;
                  }

                  val var11: Int = getRunningWorkers$volatile$FU().incrementAndGet(this);
               } catch (var9: java.lang.Throwable) {
                  // $VF: monitorexit
               }

               // $VF: monitorexit
               continue;
            }

            // $VF: monitorexit
         }

         return nextTask;
      }
   }

   public override fun toString(): String {
      var var10000: java.lang.String = this.name;
      if (this.name == null) {
         var10000 = "${this.dispatcher}.limitedParallelism(${this.parallelism})";
      }

      return var10000;
   }

   @Deprecated(message = "Deprecated without replacement as an internal method never intended for public use", level = DeprecationLevel.ERROR)
   public override suspend fun delay(time: Long) {
      return this.$$delegate_0.delay(time, `$completion`);
   }

   public override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
      this.$$delegate_0.scheduleResumeAfterDelay(timeMillis, continuation);
   }

   public override fun invokeOnTimeout(timeMillis: Long, block: Runnable, context: CoroutineContext): DisposableHandle {
      return this.$$delegate_0.invokeOnTimeout(timeMillis, block, context);
   }

   @SourceDebugExtension(["SMAP\nLimitedDispatcher.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcher$Worker\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,154:1\n29#2:155\n16#3:156\n*S KotlinDebug\n*F\n+ 1 LimitedDispatcher.kt\nkotlinx/coroutines/internal/LimitedDispatcher$Worker\n*L\n139#1:155\n139#1:156\n*E\n"])
   private inner class Worker(currentTask: Runnable) : Runnable {
      private final var currentTask: Runnable

      init {
         this.this$0 = `this$0`;
         this.currentTask = currentTask;
      }

      public override fun run() {
         try {
            val e: Int = 0;

            do {
               try {
                  this.currentTask.run();
               } catch (var10: java.lang.Throwable) {
                  CoroutineExceptionHandlerKt.handleCoroutineException(EmptyCoroutineContext.INSTANCE, var10);
               }

               val var10001: Runnable = LimitedDispatcher.access$obtainTaskOrDeallocateWorker(this.this$0);
               if (var10001 == null) {
                  return;
               }

               this.currentTask = var10001;
            } while (
               ++fairnessCounter < 16 || !DispatchedContinuationKt.safeIsDispatchNeeded(LimitedDispatcher.access$getDispatcher$p(this.this$0), this.this$0)
            );

            DispatchedContinuationKt.safeDispatch(LimitedDispatcher.access$getDispatcher$p(this.this$0), this.this$0, this);
         } catch (var11: java.lang.Throwable) {
            val `lock$iv`: Any = LimitedDispatcher.access$getWorkerAllocationLock$p(this.this$0);
            val var3: LimitedDispatcher = this.this$0;
            synchronized (lock$iv) {
               val var12: Int = LimitedDispatcher.access$getRunningWorkers$volatile$FU().decrementAndGet(var3);
            }

            throw var11;
         }
      }
   }
}
