package kotlin.coroutines

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.coroutines.intrinsics.CoroutineSingletons
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.CoroutineStackFrame

@PublishedApi
@SinceKotlin(version = "1.3")
internal class SafeContinuation<T> internal constructor(delegate: Continuation<Any>, initialResult: Any?) : Continuation<T>, CoroutineStackFrame {
   private final val delegate: Continuation<Any>

   public open val context: CoroutineContext
      public open get() {
         return this.delegate.getContext();
      }


   private final var result: Any?

   public open val callerFrame: CoroutineStackFrame?
      public open get() {
         return this.delegate as? CoroutineStackFrame;
      }


   init {
      this.delegate = delegate;
      this.result = initialResult;
   }

   @PublishedApi
   internal constructor(delegate: Continuation<Any>) : this(delegate, CoroutineSingletons.UNDECIDED)
   public override fun resumeWith(result: Result<Any>) {
      while (true) {
         if (this.result === CoroutineSingletons.UNDECIDED) {
            if (RESULT.compareAndSet(this, CoroutineSingletons.UNDECIDED, result)) {
               return;
            }
         } else {
            if (this.result === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               if (!RESULT.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), CoroutineSingletons.RESUMED)) {
                  continue;
               }

               this.delegate.resumeWith(result);
               return;
            }

            throw new IllegalStateException("Already resumed");
         }
      }
   }

   @PublishedApi
   internal fun getOrThrow(): Any? {
      var result: Any = this.result;
      if (this.result === CoroutineSingletons.UNDECIDED) {
         if (RESULT.compareAndSet(this, CoroutineSingletons.UNDECIDED, IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
            return IntrinsicsKt.getCOROUTINE_SUSPENDED();
         }

         result = this.result;
      }

      val var10000: Any;
      if (result === CoroutineSingletons.RESUMED) {
         var10000 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         if (result is Result.Failure) {
            throw (result as Result.Failure).exception;
         }

         var10000 = result;
      }

      return var10000;
   }

   public override fun getStackTraceElement(): StackTraceElement? {
      return null;
   }

   public override fun toString(): String {
      return "SafeContinuation for ${this.delegate}";
   }

   private companion object {
      private final val RESULT: AtomicReferenceFieldUpdater<SafeContinuation<*>, Any>
   }
}
