package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCancellableContinuationImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellableContinuationImpl.kt\nkotlinx/coroutines/CompletedContinuation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,701:1\n1#2:702\n*E\n"])
private data class CompletedContinuation<R>(result: Any,
   cancelHandler: CancelHandler? = null,
   onCancellation: ((Throwable, Any, CoroutineContext) -> Unit)? = null,
   idempotentResume: Any? = null,
   cancelCause: Throwable? = null
) {
   public final val result: Any
   public final val cancelHandler: CancelHandler?
   public final val onCancellation: ((Throwable, Any, CoroutineContext) -> Unit)?
   public final val idempotentResume: Any?
   public final val cancelCause: Throwable?

   public final val cancelled: Boolean
      public final get() {
         return this.cancelCause != null;
      }


   init {
      this.result = (R)result;
      this.cancelHandler = cancelHandler;
      this.onCancellation = onCancellation;
      this.idempotentResume = idempotentResume;
      this.cancelCause = cancelCause;
   }

   public fun invokeHandlers(cont: CancellableContinuationImpl<*>, cause: Throwable) {
      if (this.cancelHandler != null) {
         cont.callCancelHandler(this.cancelHandler, cause);
      }

      if (this.onCancellation != null) {
         cont.callOnCancellation(this.onCancellation, cause, this.result);
      }
   }

   public operator fun component1(): Any {
      return this.result;
   }

   public operator fun component2(): CancelHandler? {
      return this.cancelHandler;
   }

   public operator fun component3(): ((Throwable, Any, CoroutineContext) -> Unit)? {
      return this.onCancellation;
   }

   public operator fun component4(): Any? {
      return this.idempotentResume;
   }

   public operator fun component5(): Throwable? {
      return this.cancelCause;
   }

   public fun copy(
      result: Any = this.result,
      cancelHandler: CancelHandler? = this.cancelHandler,
      onCancellation: ((Throwable, Any, CoroutineContext) -> Unit)? = this.onCancellation,
      idempotentResume: Any? = this.idempotentResume,
      cancelCause: Throwable? = this.cancelCause
   ): CompletedContinuation<Any> {
      return new CompletedContinuation<>((R)result, cancelHandler, onCancellation, idempotentResume, cancelCause);
   }

   public override fun toString(): String {
      return "CompletedContinuation(result=${this.result}, cancelHandler=${this.cancelHandler}, onCancellation=${this.onCancellation}, idempotentResume=${this.idempotentResume}, cancelCause=${this.cancelCause})";
   }

   public override fun hashCode(): Int {
      return (
               (
                        ((if (this.result == null) 0 else this.result.hashCode()) * 31 + (if (this.cancelHandler == null) 0 else this.cancelHandler.hashCode()))
                              * 31
                           + (if (this.onCancellation == null) 0 else this.onCancellation.hashCode())
                     )
                     * 31
                  + (if (this.idempotentResume == null) 0 else this.idempotentResume.hashCode())
            )
            * 31
         + (if (this.cancelCause == null) 0 else this.cancelCause.hashCode());
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CompletedContinuation) {
         return false;
      } else {
         val var2: CompletedContinuation = other as CompletedContinuation;
         if (!(this.result == (other as CompletedContinuation).result)) {
            return false;
         } else if (!(this.cancelHandler == var2.cancelHandler)) {
            return false;
         } else if (!(this.onCancellation == var2.onCancellation)) {
            return false;
         } else if (!(this.idempotentResume == var2.idempotentResume)) {
            return false;
         } else {
            return this.cancelCause == var2.cancelCause;
         }
      }
   }
}
