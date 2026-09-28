package kotlinx.coroutines.internal

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Delay
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.MainCoroutineDispatcher

@SourceDebugExtension(["SMAP\nMainDispatchers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MainDispatchers.kt\nkotlinx/coroutines/internal/MissingMainCoroutineDispatcher\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,130:1\n1#2:131\n*E\n"])
private class MissingMainCoroutineDispatcher(cause: Throwable?, errorHint: String? = null) : MainCoroutineDispatcher, Delay {
   private final val cause: Throwable?
   private final val errorHint: String?

   public open val immediate: MainCoroutineDispatcher
      public open get() {
         return this;
      }


   init {
      this.cause = cause;
      this.errorHint = errorHint;
   }

   public override fun isDispatchNeeded(context: CoroutineContext): Boolean {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public override fun limitedParallelism(parallelism: Int, name: String?): CoroutineDispatcher {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public override fun invokeOnTimeout(timeMillis: Long, block: Runnable, context: CoroutineContext): DisposableHandle {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public open fun dispatch(context: CoroutineContext, block: Runnable): Nothing {
      this.missing();
      throw new KotlinNothingValueException();
   }

   public open fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>): Nothing {
      this.missing();
      throw new KotlinNothingValueException();
   }

   private fun missing(): Nothing {
      if (this.cause == null) {
         MainDispatchersKt.throwMissingMainDispatcherException();
         throw new KotlinNothingValueException();
      } else {
         var var10000: StringBuilder;
         var var10001: java.lang.String;
         label14: {
            var10000 = new StringBuilder().append("Module with the Main dispatcher had failed to initialize");
            if (this.errorHint != null) {
               val it: java.lang.String = this.errorHint;
               var10001 = ". $it";
               if (var10001 != null) {
                  break label14;
               }
            }

            var10001 = "";
         }

         throw new IllegalStateException(var10000.append(var10001).toString(), this.cause);
      }
   }

   public override fun toString(): String {
      return "Dispatchers.Main[missing${if (this.cause != null) ", cause=${this.cause}" else ""}]";
   }

   /** @deprecated */
   @Deprecated(message = "Deprecated without replacement as an internal method never intended for public use", level = DeprecationLevel.ERROR)
   override fun delay(time: Long, `$completion`: Continuation<? super Unit>): Any {
      return Delay.DefaultImpls.delay(this, time, `$completion`);
   }
}
