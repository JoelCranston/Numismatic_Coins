package kotlinx.coroutines

import java.util.concurrent.CancellationException
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.internal.DispatchedContinuation
import kotlinx.coroutines.internal.Segment
import kotlinx.coroutines.internal.StackTraceRecoveryKt
import kotlinx.coroutines.internal.Symbol

@PublishedApi
@SourceDebugExtension(["SMAP\nCancellableContinuationImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellableContinuationImpl.kt\nkotlinx/coroutines/CancellableContinuationImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CancellableContinuationImpl.kt\nkotlinx/coroutines/CancellableContinuationImplKt\n+ 4 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,701:1\n227#1,10:705\n227#1,10:716\n1#2:702\n20#3:703\n20#3:704\n18#3:715\n17#3:726\n18#3,3:727\n17#3:730\n18#3,3:731\n18#3:738\n17#3,4:739\n57#4,2:734\n57#4,2:736\n57#4,2:743\n*S KotlinDebug\n*F\n+ 1 CancellableContinuationImpl.kt\nkotlinx/coroutines/CancellableContinuationImpl\n*L\n239#1:705,10\n244#1:716,10\n69#1:703\n155#1:704\n242#1:715\n271#1:726\n272#1:727,3\n281#1:730\n282#1:731,3\n387#1:738\n390#1:739,4\n323#1:734,2\n333#1:736,2\n614#1:743,2\n*E\n"])
internal open class CancellableContinuationImpl<T>(delegate: Continuation<Any>, resumeMode: Int) : DispatchedTask(resumeMode),
   CancellableContinuation<T>,
   CoroutineStackFrame,
   Waiter {
   internal final val delegate: Continuation<Any>
   public open val context: CoroutineContext
   private final val _decisionAndIndex: AtomicInt
   private final val _state: AtomicRef<Any?>
   private final val _parentHandle: AtomicRef<DisposableHandle?>

   private final val parentHandle: DisposableHandle?
      private final get() {
         return get_parentHandle$volatile$FU().get(this) as DisposableHandle;
      }


   internal final val state: Any?
      internal final get() {
         return get_state$volatile$FU().get(this);
      }


   public open val isActive: Boolean
      public open get() {
         return this.getState$kotlinx_coroutines_core() is NotCompleted;
      }


   public open val isCompleted: Boolean
      public open get() {
         return this.getState$kotlinx_coroutines_core() !is NotCompleted;
      }


   public open val isCancelled: Boolean
      public open get() {
         return this.getState$kotlinx_coroutines_core() is CancelledContinuation;
      }


   private final val stateDebugRepresentation: String
      private final get() {
         val var1: Any = this.getState$kotlinx_coroutines_core();
         return if (var1 is NotCompleted) "Active" else (if (var1 is CancelledContinuation) "Cancelled" else "Completed");
      }


   public open val callerFrame: CoroutineStackFrame?
      public open get() {
         return this.delegate as? CoroutineStackFrame;
      }


   init {
      this.delegate = delegate;
      if (DebugKt.getASSERTIONS_ENABLED() && resumeMode == -1) {
         throw new AssertionError();
      } else {
         this.context = this.delegate.getContext();
         this._decisionAndIndex$volatile = (0 shl 29) + 536870911;
         this._state$volatile = Active.INSTANCE;
      }
   }

   public override fun initCancellability() {
      val var10000: DisposableHandle = this.installParentHandle();
      if (var10000 != null) {
         if (this.isCompleted()) {
            var10000.dispose();
            get_parentHandle$volatile$FU().set(this, NonDisposableHandle.INSTANCE);
         }
      }
   }

   private fun isReusable(): Boolean {
      if (DispatchedTaskKt.isReusableMode(this.resumeMode)) {
         val var10000: Continuation = this.delegate;
         if ((var10000 as DispatchedContinuation).isReusable$kotlinx_coroutines_core()) {
            return true;
         }
      }

      return false;
   }

   @JvmName(name = "resetStateReusable")
   internal fun resetStateReusable(): Boolean {
      if (DebugKt.getASSERTIONS_ENABLED() && this.resumeMode != 2) {
         throw new AssertionError();
      } else if (DebugKt.getASSERTIONS_ENABLED() && this.getParentHandle() === NonDisposableHandle.INSTANCE) {
         throw new AssertionError();
      } else {
         val var6: Any = get_state$volatile$FU().get(this);
         if (DebugKt.getASSERTIONS_ENABLED() && var6 is NotCompleted) {
            throw new AssertionError();
         } else if (var6 is CompletedContinuation && (var6 as CompletedContinuation).idempotentResume != null) {
            this.detachChild$kotlinx_coroutines_core();
            return false;
         } else {
            get_decisionAndIndex$volatile$FU().set(this, (0 shl 29) + 536870911);
            get_state$volatile$FU().set(this, Active.INSTANCE);
            return true;
         }
      }
   }

   public override fun getStackTraceElement(): StackTraceElement? {
      return null;
   }

   internal override fun takeState(): Any? {
      return this.getState$kotlinx_coroutines_core();
   }

   internal override fun cancelCompletedResult(takenState: Any?, cause: Throwable) {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_state$volatile$FU();

      while (true) {
         val state: Any = `handler$atomicfu$iv`.get(this);
         if (state is NotCompleted) {
            throw new IllegalStateException("Not completed".toString());
         }

         if (state is CompletedExceptionally) {
            return;
         }

         if (state is CompletedContinuation) {
            if ((state as CompletedContinuation).getCancelled()) {
               throw new IllegalStateException("Must be called at most once".toString());
            }

            if (get_state$volatile$FU()
               .compareAndSet(this, state, CompletedContinuation.copy$default(state as CompletedContinuation, null, null, null, null, cause, 15, null))) {
               (state as CompletedContinuation).invokeHandlers(this, cause);
               return;
            }
         } else if (get_state$volatile$FU().compareAndSet(this, state, new CompletedContinuation(state, null, null, null, cause, 14, null))) {
            return;
         }
      }
   }

   private fun cancelLater(cause: Throwable): Boolean {
      if (!this.isReusable()) {
         return false;
      } else {
         val var10000: Continuation = this.delegate;
         return (var10000 as DispatchedContinuation).postponeCancellation$kotlinx_coroutines_core(cause);
      }
   }

   public override fun cancel(cause: Throwable?): Boolean {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_state$volatile$FU();

      val state: Any;
      do {
         state = `handler$atomicfu$iv`.get(this);
         if (state !is NotCompleted) {
            return false;
         }
      } while (
         !get_state$volatile$FU()
            .compareAndSet(this, state, new CancelledContinuation(this, cause, state instanceof CancelHandler || state instanceof Segment))
      );

      val var7: NotCompleted = state as NotCompleted;
      if (state as NotCompleted is CancelHandler) {
         this.callCancelHandler(state as CancelHandler, cause);
      } else if (var7 is Segment) {
         this.callSegmentOnCancellation(state as Segment<?>, cause);
      }

      this.detachChildIfNonReusable();
      this.dispatchResume(this.resumeMode);
      return true;
   }

   internal fun parentCancelled(cause: Throwable) {
      if (!this.cancelLater(cause)) {
         this.cancel(cause);
         this.detachChildIfNonReusable();
      }
   }

   private inline fun callCancelHandlerSafely(block: () -> Unit) {
      try {
         block.invoke();
      } catch (var4: java.lang.Throwable) {
         CoroutineExceptionHandlerKt.handleCoroutineException(
            this.getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for $this", var4)
         );
      }
   }

   public fun callCancelHandler(handler: CancelHandler, cause: Throwable?) {
      try {
         handler.invoke(cause);
      } catch (var7: java.lang.Throwable) {
         CoroutineExceptionHandlerKt.handleCoroutineException(
            this.getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for $this", var7)
         );
      }
   }

   private fun callSegmentOnCancellation(segment: Segment<*>, cause: Throwable?) {
      val index: Int = get_decisionAndIndex$volatile$FU().get(this) and 536870911;
      if (index == 536870911) {
         throw new IllegalStateException("The index for Segment.onCancellation(..) is broken".toString());
      } else {
         try {
            segment.onCancellation(index, cause, this.getContext());
         } catch (var8: java.lang.Throwable) {
            CoroutineExceptionHandlerKt.handleCoroutineException(
               this.getContext(), new CompletionHandlerException("Exception in invokeOnCancellation handler for $this", var8)
            );
         }
      }
   }

   public fun <R> callOnCancellation(onCancellation: (Throwable, R, CoroutineContext) -> Unit, cause: Throwable, value: R) {
      try {
         onCancellation.invoke(cause, value, this.getContext());
      } catch (var5: java.lang.Throwable) {
         CoroutineExceptionHandlerKt.handleCoroutineException(
            this.getContext(), new CompletionHandlerException("Exception in resume onCancellation handler for $this", var5)
         );
      }
   }

   public open fun getContinuationCancellationCause(parent: Job): Throwable {
      return parent.getCancellationException();
   }

   private fun trySuspend(): Boolean {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = get_decisionAndIndex$volatile$FU();

      label16:
      while (true) {
         val cur: Int = `handler$atomicfu$iv`.get(this);
         switch (cur >> 29) {
            case 0:
               if (get_decisionAndIndex$volatile$FU().compareAndSet(this, cur, (1 shl 29) + (cur and 536870911))) {
                  break label16;
               }
               break;
            case 1:
            default:
               throw new IllegalStateException("Already suspended".toString());
            case 2:
               return false;
         }
      }

      return true;
   }

   private fun tryResume(): Boolean {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = get_decisionAndIndex$volatile$FU();

      label16:
      while (true) {
         val cur: Int = `handler$atomicfu$iv`.get(this);
         switch (cur >> 29) {
            case 0:
               if (get_decisionAndIndex$volatile$FU().compareAndSet(this, cur, (2 shl 29) + (cur and 536870911))) {
                  break label16;
               }
               break;
            case 1:
               return false;
            default:
               throw new IllegalStateException("Already resumed".toString());
         }
      }

      return true;
   }

   @PublishedApi
   internal fun getResult(): Any? {
      val isReusable: Boolean = this.isReusable();
      if (this.trySuspend()) {
         if (this.getParentHandle() == null) {
            this.installParentHandle();
         }

         if (isReusable) {
            this.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
         }

         return IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         if (isReusable) {
            this.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
         }

         val state: Any = this.getState$kotlinx_coroutines_core();
         if (state is CompletedExceptionally) {
            val var6: java.lang.Throwable = (state as CompletedExceptionally).cause;
            throw if (DebugKt.getRECOVER_STACK_TRACES() && this as Continuation is CoroutineStackFrame)
               StackTraceRecoveryKt.access$recoverFromStackFrame(var6, this)
               else
               var6;
         } else {
            if (DispatchedTaskKt.isCancellableMode(this.resumeMode)) {
               val job: Job = this.getContext().get(Job.Key);
               if (job != null && !job.isActive()) {
                  val cause: CancellationException = job.getCancellationException();
                  this.cancelCompletedResult$kotlinx_coroutines_core(state, cause);
                  throw if (DebugKt.getRECOVER_STACK_TRACES() && this as Continuation is CoroutineStackFrame)
                     StackTraceRecoveryKt.access$recoverFromStackFrame(cause, this)
                     else
                     cause as java.lang.Throwable;
               }
            }

            return this.getSuccessfulResult$kotlinx_coroutines_core(state);
         }
      }
   }

   private fun installParentHandle(): DisposableHandle? {
      val var10000: Job = this.getContext().get(Job.Key);
      if (var10000 == null) {
         return null;
      } else {
         val handle: DisposableHandle = JobKt.invokeOnCompletion$default(var10000, false, new ChildContinuation(this), 1, null);
         get_parentHandle$volatile$FU().compareAndSet(this, null, handle);
         return handle;
      }
   }

   internal fun releaseClaimedReusableContinuation() {
      val var2: Continuation = this.delegate;
      val var10000: DispatchedContinuation = this.delegate as? DispatchedContinuation;
      if ((this.delegate as? DispatchedContinuation) != null) {
         val var3: java.lang.Throwable = var10000.tryReleaseClaimedContinuation$kotlinx_coroutines_core(this);
         if (var3 != null) {
            this.detachChild$kotlinx_coroutines_core();
            this.cancel(var3);
            return;
         }
      }
   }

   public override fun resumeWith(result: Result<Any>) {
      resumeImpl$kotlinx_coroutines_core$default(this, CompletionStateKt.toState(result, this), this.resumeMode, null, 4, null);
   }

   public override fun resume(value: Any, onCancellation: ((Throwable) -> Unit)?) {
      var var10000: CancellableContinuationImpl = this;
      var var10001: Any = value;
      var var10002: Int = this.resumeMode;
      val var10003: Function3;
      if (onCancellation != null) {
         val var7: Int = this.resumeMode;
         val var8: Function3 = CancellableContinuationImpl::resume$lambda$13$lambda$12;
         var10000 = this;
         var10001 = value;
         var10002 = var7;
         var10003 = var8;
      } else {
         var10003 = null;
      }

      var10000.resumeImpl$kotlinx_coroutines_core(var10001, var10002, var10003);
   }

   public override fun <R : Any> resume(value: R, onCancellation: ((Throwable, R, CoroutineContext) -> Unit)?) {
      this.resumeImpl$kotlinx_coroutines_core(value, this.resumeMode, onCancellation);
   }

   public override fun invokeOnCancellation(segment: Segment<*>, index: Int) {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = get_decisionAndIndex$volatile$FU();

      val var5: Int;
      do {
         var5 = `handler$atomicfu$iv`.get(this);
         if ((var5 and 536870911) != 536870911) {
            throw new IllegalStateException("invokeOnCancellation should be called at most once".toString());
         }
      } while (!handler$atomicfu$iv.compareAndSet(this, var5, (var5 >> 29 << 29) + index));

      this.invokeOnCancellationImpl(segment);
   }

   public override fun invokeOnCancellation(handler: (Throwable?) -> Unit) {
      CancellableContinuationKt.invokeOnCancellation(this, new CancelHandler.UserSupplied(handler));
   }

   internal fun invokeOnCancellationInternal(handler: CancelHandler) {
      this.invokeOnCancellationImpl(handler);
   }

   private fun invokeOnCancellationImpl(handler: Any) {
      if (DebugKt.getASSERTIONS_ENABLED() && handler !is CancelHandler && handler !is Segment) {
         throw new AssertionError();
      } else {
         val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_state$volatile$FU();

         while (true) {
            val state: Any = `handler$atomicfu$iv`.get(this);
            if (state is Active) {
               if (get_state$volatile$FU().compareAndSet(this, state, handler)) {
                  return;
               }
            } else if (state !is CancelHandler && state !is Segment) {
               if (state is CompletedExceptionally) {
                  if (!(state as CompletedExceptionally).makeHandled()) {
                     this.multipleHandlersError(handler, state);
                  }

                  if (state is CancelledContinuation) {
                     val var11: java.lang.Throwable = if ((state as? CompletedExceptionally) != null) (state as? CompletedExceptionally).cause else null;
                     if (handler is CancelHandler) {
                        this.callCancelHandler(handler as CancelHandler, var11);
                     } else {
                        this.callSegmentOnCancellation(handler as Segment<?>, var11);
                     }
                  }

                  return;
               }

               if (state is CompletedContinuation) {
                  if ((state as CompletedContinuation).cancelHandler != null) {
                     this.multipleHandlersError(handler, state);
                  }

                  if (handler is Segment) {
                     return;
                  }

                  if ((state as CompletedContinuation).getCancelled()) {
                     this.callCancelHandler(handler as CancelHandler, (state as CompletedContinuation).cancelCause);
                     return;
                  }

                  if (get_state$volatile$FU()
                     .compareAndSet(
                        this,
                        state,
                        CompletedContinuation.copy$default(state as CompletedContinuation, null, handler as CancelHandler, null, null, null, 29, null)
                     )) {
                     return;
                  }
               } else {
                  if (handler is Segment) {
                     return;
                  }

                  if (get_state$volatile$FU()
                     .compareAndSet(this, state, new CompletedContinuation(state, handler as CancelHandler, null, null, null, 28, null))) {
                     return;
                  }
               }
            } else {
               this.multipleHandlersError(handler, state);
            }
         }
      }
   }

   private fun multipleHandlersError(handler: Any, state: Any?) {
      throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register $handler, already has $state").toString());
   }

   private fun dispatchResume(mode: Int) {
      if (!this.tryResume()) {
         DispatchedTaskKt.dispatch(this, mode);
      }
   }

   private fun <R> resumedState(
      state: NotCompleted,
      proposedUpdate: R,
      resumeMode: Int,
      onCancellation: ((Throwable, R, CoroutineContext) -> Unit)?,
      idempotent: Any?
   ): Any? {
      val var10000: Any;
      if (proposedUpdate is CompletedExceptionally) {
         if (DebugKt.getASSERTIONS_ENABLED() && idempotent != null) {
            throw new AssertionError();
         }

         if (DebugKt.getASSERTIONS_ENABLED() && onCancellation != null) {
            throw new AssertionError();
         }

         var10000 = proposedUpdate;
      } else {
         var10000 = if (!DispatchedTaskKt.isCancellableMode(resumeMode) && idempotent == null)
            proposedUpdate
            else
            (
               if (onCancellation == null && state !is CancelHandler && idempotent == null)
                  proposedUpdate
                  else
                  new CompletedContinuation(proposedUpdate, state as? CancelHandler, onCancellation, idempotent, null, 16, null)
            );
      }

      return var10000;
   }

   internal fun <R> resumeImpl(proposedUpdate: R, resumeMode: Int, onCancellation: ((Throwable, R, CoroutineContext) -> Unit)? = ...) {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_state$volatile$FU();

      val state: Any;
      do {
         state = `handler$atomicfu$iv`.get(this);
         if (state !is NotCompleted) {
            if (state is CancelledContinuation && (state as CancelledContinuation).makeResumed()) {
               if (onCancellation != null) {
                  this.callOnCancellation(onCancellation, (state as CancelledContinuation).cause, proposedUpdate);
               }

               return;
            }

            this.alreadyResumedError(proposedUpdate);
            throw new KotlinNothingValueException();
         }
      } while (!get_state$volatile$FU().compareAndSet(this, state, this.resumedState((NotCompleted)state, proposedUpdate, resumeMode, onCancellation, null)));

      this.detachChildIfNonReusable();
      this.dispatchResume(resumeMode);
   }

   private fun <R> tryResumeImpl(proposedUpdate: R, idempotent: Any?, onCancellation: ((Throwable, R, CoroutineContext) -> Unit)?): Symbol? {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_state$volatile$FU();

      val state: Any;
      do {
         state = `handler$atomicfu$iv`.get(this);
         if (state !is NotCompleted) {
            if (state !is CompletedContinuation) {
               return null;
            } else {
               val var10000: Symbol;
               if (idempotent != null && (state as CompletedContinuation).idempotentResume === idempotent) {
                  if (DebugKt.getASSERTIONS_ENABLED() && !((state as CompletedContinuation).result == proposedUpdate)) {
                     throw new AssertionError();
                  }

                  var10000 = CancellableContinuationImplKt.RESUME_TOKEN;
               } else {
                  var10000 = null;
               }

               return var10000;
            }
         }
      } while (
         !get_state$volatile$FU()
            .compareAndSet(this, state, this.resumedState((NotCompleted)state, proposedUpdate, this.resumeMode, onCancellation, idempotent))
      );

      this.detachChildIfNonReusable();
      return CancellableContinuationImplKt.RESUME_TOKEN;
   }

   private fun alreadyResumedError(proposedUpdate: Any?): Nothing {
      throw new IllegalStateException(("Already resumed, but proposed with update $proposedUpdate").toString());
   }

   private fun detachChildIfNonReusable() {
      if (!this.isReusable()) {
         this.detachChild$kotlinx_coroutines_core();
      }
   }

   internal fun detachChild() {
      val var10000: DisposableHandle = this.getParentHandle();
      if (var10000 != null) {
         var10000.dispose();
         get_parentHandle$volatile$FU().set(this, NonDisposableHandle.INSTANCE);
      }
   }

   public override fun tryResume(value: Any, idempotent: Any?): Any? {
      return this.tryResumeImpl(value, idempotent, null);
   }

   public override fun <R : Any> tryResume(value: R, idempotent: Any?, onCancellation: ((Throwable, R, CoroutineContext) -> Unit)?): Any? {
      return this.tryResumeImpl(value, idempotent, onCancellation);
   }

   public override fun tryResumeWithException(exception: Throwable): Any? {
      return this.tryResumeImpl(new CompletedExceptionally(exception, false, 2, null), null, null);
   }

   public override fun completeResume(token: Any) {
      if (DebugKt.getASSERTIONS_ENABLED() && token != CancellableContinuationImplKt.RESUME_TOKEN) {
         throw new AssertionError();
      } else {
         this.dispatchResume(this.resumeMode);
      }
   }

   public override fun CoroutineDispatcher.resumeUndispatched(value: Any) {
      resumeImpl$kotlinx_coroutines_core$default(
         this,
         value,
         if ((if ((this.delegate as? DispatchedContinuation) != null) (this.delegate as? DispatchedContinuation).dispatcher else null) === `$this$resumeUndispatched`)
            4
            else
            this.resumeMode,
         null,
         4,
         null
      );
   }

   public override fun CoroutineDispatcher.resumeUndispatchedWithException(exception: Throwable) {
      val dc: DispatchedContinuation = this.delegate as? DispatchedContinuation;
      resumeImpl$kotlinx_coroutines_core$default(
         this,
         new CompletedExceptionally(exception, false, 2, null),
         if ((if (dc != null) dc.dispatcher else null) === `$this$resumeUndispatchedWithException`) 4 else this.resumeMode,
         null,
         4,
         null
      );
   }

   internal override fun <T> getSuccessfulResult(state: Any?): T {
      return (T)(if (state is CompletedContinuation) (state as CompletedContinuation).result else state);
   }

   internal override fun getExceptionalResult(state: Any?): Throwable? {
      var var10000: java.lang.Throwable = super.getExceptionalResult$kotlinx_coroutines_core(state);
      if (var10000 != null) {
         val `continuation$iv`: Continuation = this.delegate;
         var10000 = if (DebugKt.getRECOVER_STACK_TRACES() && `continuation$iv` is CoroutineStackFrame)
            StackTraceRecoveryKt.access$recoverFromStackFrame(var10000, `continuation$iv` as CoroutineStackFrame)
            else
            var10000;
      } else {
         var10000 = null;
      }

      return var10000;
   }

   public override fun toString(): String {
      return "${this.nameString()}(${DebugStringsKt.toDebugString(this.delegate)}){${this.getStateDebugRepresentation()}}@${DebugStringsKt.getHexAddress(this)}";
   }

   protected open fun nameString(): String {
      return "CancellableContinuation";
   }

   @JvmStatic
   fun `resume$lambda$13$lambda$12`(`$onCancellation`: Function1, cause: java.lang.Throwable, var2: Any, var3: CoroutineContext): Unit {
      `$onCancellation`.invoke(cause);
      return Unit.INSTANCE;
   }
}
