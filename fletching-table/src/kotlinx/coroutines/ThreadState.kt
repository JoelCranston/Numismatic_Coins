package kotlinx.coroutines

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater
import kotlinx.atomicfu.AtomicInt

private class ThreadState : JobNode {
   private final val _state: AtomicInt
   private final val targetThread: Thread = Thread.currentThread()
   private final var cancelHandle: DisposableHandle?

   public open val onCancelling: Boolean
      public open get() {
         return true;
      }


   public fun setup(job: Job) {
      this.cancelHandle = JobKt.invokeOnCompletion$default(job, false, this, 1, null);
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = get_state$volatile$FU();

      label16:
      while (true) {
         val state: Int = `handler$atomicfu$iv`.get(this);
         switch (state) {
            case 0:
               if (get_state$volatile$FU().compareAndSet(this, state, 0)) {
                  break label16;
               }
               break;
            case 1:
            default:
               this.invalidState(state);
               throw new KotlinNothingValueException();
            case 2:
            case 3:
               return;
         }
      }
   }

   public fun clearInterrupt() {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = get_state$volatile$FU();

      while (true) {
         val state: Int = `handler$atomicfu$iv`.get(this);
         switch (state) {
            case 0:
               if (get_state$volatile$FU().compareAndSet(this, state, 1)) {
                  if (this.cancelHandle != null) {
                     this.cancelHandle.dispose();
                  }

                  return;
               }
            case 2:
               break;
            case 1:
            default:
               this.invalidState(state);
               throw new KotlinNothingValueException();
            case 3:
               Thread.interrupted();
               return;
         }
      }
   }

   public override fun invoke(cause: Throwable?) {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = get_state$volatile$FU();

      label16:
      while (true) {
         val state: Int = `handler$atomicfu$iv`.get(this);
         switch (state) {
            case 0:
               if (get_state$volatile$FU().compareAndSet(this, state, 2)) {
                  break label16;
               }
               break;
            case 1:
            case 2:
            case 3:
               return;
            default:
               this.invalidState(state);
               throw new KotlinNothingValueException();
         }
      }

      this.targetThread.interrupt();
      get_state$volatile$FU().set(this, 3);
   }

   private fun invalidState(state: Int): Nothing {
      throw new IllegalStateException(("Illegal state $state").toString());
   }
}
