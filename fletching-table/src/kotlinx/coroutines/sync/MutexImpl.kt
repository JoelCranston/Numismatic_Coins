package kotlinx.coroutines.sync

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.CancellableContinuationKt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DebugStringsKt
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Waiter
import kotlinx.coroutines.internal.Segment
import kotlinx.coroutines.selects.SelectClause2
import kotlinx.coroutines.selects.SelectClause2Impl
import kotlinx.coroutines.selects.SelectInstance
import kotlinx.coroutines.selects.SelectInstanceInternal
import kotlinx.coroutines.sync.MutexImpl.onLock.1
import kotlinx.coroutines.sync.MutexImpl.onLock.2

@SourceDebugExtension(["SMAP\nMutex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Mutex.kt\nkotlinx/coroutines/sync/MutexImpl\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,314:1\n444#2,12:315\n1#3:327\n*S KotlinDebug\n*F\n+ 1 Mutex.kt\nkotlinx/coroutines/sync/MutexImpl\n*L\n171#1:315,12\n*E\n"])
internal open class MutexImpl(locked: Boolean) : SemaphoreAndMutexImpl(1, if (locked) 1 else 0), Mutex {
   private final val owner: AtomicRef<Any?>
   private final val onSelectCancellationUnlockConstructor: (SelectInstance<*>, Any?, Any?) -> (Throwable, Any?, CoroutineContext) -> Unit

   public open val isLocked: Boolean
      public open get() {
         return this.getAvailablePermits() == 0;
      }


   public open val onLock: SelectClause2<Any?, Mutex>
      public open get() {
         val var10003: 1 = 1.INSTANCE;
         val var1: Function3 = TypeIntrinsics.beforeCheckcastToFunctionOfArity(var10003, 3) as Function3;
         val var10004: 2 = 2.INSTANCE;
         return new SelectClause2Impl<>(
            this, var1, TypeIntrinsics.beforeCheckcastToFunctionOfArity(var10004, 3) as (Any?, Any?, Any?) -> Any, this.onSelectCancellationUnlockConstructor
         );
      }


   init {
      this.owner$volatile = if (locked) null else MutexKt.access$getNO_OWNER$p();
      this.onSelectCancellationUnlockConstructor = MutexImpl::onSelectCancellationUnlockConstructor$lambda$1;
   }

   public override fun holdsLock(owner: Any): Boolean {
      return this.holdsLockImpl(owner) == 1;
   }

   private fun holdsLockImpl(owner: Any?): Int {
      while (this.isLocked()) {
         val curOwner: Any = getOwner$volatile$FU().get(this);
         if (curOwner != MutexKt.access$getNO_OWNER$p()) {
            return if (curOwner === owner) 1 else 2;
         }
      }

      return 0;
   }

   public override suspend fun lock(owner: Any?) {
      return lock$suspendImpl(this, owner, `$completion`);
   }

   private suspend fun lockSuspend(owner: Any?) {
      val `cancellable$iv`: CancellableContinuationImpl = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(`$completion`));

      try {
         this.acquire(new MutexImpl.CancellableContinuationWithOwner(this, `cancellable$iv`, owner));
      } catch (var11: java.lang.Throwable) {
         `cancellable$iv`.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
         throw var11;
      }

      val var10000: Any = `cancellable$iv`.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public override fun tryLock(owner: Any?): Boolean {
      var var10000: Boolean;
      switch (this.tryLockImpl(owner)) {
         case 0:
            var10000 = true;
            break;
         case 1:
            var10000 = false;
            break;
         case 2:
            throw new IllegalStateException(("This mutex is already locked by the specified owner: $owner").toString());
         default:
            throw new IllegalStateException("unexpected".toString());
      }

      return var10000;
   }

   private fun tryLockImpl(owner: Any?): Int {
      while (!this.tryAcquire()) {
         if (owner == null) {
            return 1;
         }

         switch (this.holdsLockImpl(owner)) {
            case 0:
            default:
               break;
            case 1:
               return 2;
            case 2:
               return 1;
         }
      }

      if (DebugKt.getASSERTIONS_ENABLED() && getOwner$volatile$FU().get(this) != MutexKt.access$getNO_OWNER$p()) {
         throw new AssertionError();
      } else {
         getOwner$volatile$FU().set(this, owner);
         return 0;
      }
   }

   public override fun unlock(owner: Any?) {
      while (this.isLocked()) {
         val curOwner: Any = getOwner$volatile$FU().get(this);
         if (curOwner != MutexKt.access$getNO_OWNER$p()) {
            if (curOwner != owner && owner != null) {
               throw new IllegalStateException(("This mutex is locked by $curOwner, but $owner is expected").toString());
            }

            if (getOwner$volatile$FU().compareAndSet(this, curOwner, MutexKt.access$getNO_OWNER$p())) {
               this.release();
               return;
            }
         }
      }

      throw new IllegalStateException("This mutex is not locked".toString());
   }

   protected open fun onLockRegFunction(select: SelectInstance<*>, owner: Any?) {
      if (owner != null && this.holdsLock(owner)) {
         select.selectInRegistrationPhase(MutexKt.access$getON_LOCK_ALREADY_LOCKED_BY_OWNER$p());
      } else {
         this.onAcquireRegFunction(new MutexImpl.SelectInstanceWithOwner(this, select as SelectInstanceInternal, owner), owner);
      }
   }

   protected open fun onLockProcessResult(owner: Any?, result: Any?): Any? {
      if (result == MutexKt.access$getON_LOCK_ALREADY_LOCKED_BY_OWNER$p()) {
         throw new IllegalStateException(("This mutex is already locked by the specified owner: $owner").toString());
      } else {
         return this;
      }
   }

   public override fun toString(): String {
      return "Mutex@${DebugStringsKt.getHexAddress(this)}[isLocked=${this.isLocked()},owner=${getOwner$volatile$FU().get(this)}]";
   }

   @JvmStatic
   fun `onSelectCancellationUnlockConstructor$lambda$1$lambda$0`(
      `this$0`: MutexImpl, `$owner`: Any, var2: java.lang.Throwable, var3: Any, var4: CoroutineContext
   ): Unit {
      `this$0`.unlock(`$owner`);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `onSelectCancellationUnlockConstructor$lambda$1`(`this$0`: MutexImpl, var1: SelectInstance, owner: Any, var3: Any): Function3 {
      return MutexImpl::onSelectCancellationUnlockConstructor$lambda$1$lambda$0;
   }

   @SourceDebugExtension(["SMAP\nMutex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Mutex.kt\nkotlinx/coroutines/sync/MutexImpl$CancellableContinuationWithOwner\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,314:1\n1#2:315\n*E\n"])
   private inner class CancellableContinuationWithOwner(cont: CancellableContinuationImpl<Unit>, owner: Any?) : CancellableContinuation<Unit>, Waiter {
      public final val cont: CancellableContinuationImpl<Unit>
      public final val owner: Any?
      public open val context: CoroutineContext
      public open val isActive: Boolean
      public open val isCancelled: Boolean
      public open val isCompleted: Boolean

      init {
         this.this$0 = `this$0`;
         this.cont = cont;
         this.owner = owner;
      }

      public open fun <R : Unit> tryResume(value: R, idempotent: Any?, onCancellation: ((Throwable, R, CoroutineContext) -> Unit)?): Any? {
         var token: Any = this.this$0;
         if (DebugKt.getASSERTIONS_ENABLED() && MutexImpl.access$getOwner$volatile$FU().get(token) != MutexKt.access$getNO_OWNER$p()) {
            throw new AssertionError();
         } else {
            token = this.cont.tryResume(value, idempotent, MutexImpl.CancellableContinuationWithOwner::tryResume$lambda$3);
            if (token != null) {
               val var8: MutexImpl = this.this$0;
               if (DebugKt.getASSERTIONS_ENABLED() && MutexImpl.access$getOwner$volatile$FU().get(var8) != MutexKt.access$getNO_OWNER$p()) {
                  throw new AssertionError();
               }

               MutexImpl.access$getOwner$volatile$FU().set(this.this$0, this.owner);
            }

            return token;
         }
      }

      public open fun <R : Unit> resume(value: R, onCancellation: ((Throwable, R, CoroutineContext) -> Unit)?) {
         val var3: MutexImpl = this.this$0;
         if (DebugKt.getASSERTIONS_ENABLED() && MutexImpl.access$getOwner$volatile$FU().get(var3) != MutexKt.access$getNO_OWNER$p()) {
            throw new AssertionError();
         } else {
            MutexImpl.access$getOwner$volatile$FU().set(this.this$0, this.owner);
            this.cont.resume(value, MutexImpl.CancellableContinuationWithOwner::resume$lambda$6);
         }
      }

      @InternalCoroutinesApi
      public open fun tryResume(value: Unit, idempotent: Any?): Any? {
         return this.cont.tryResume(value, idempotent);
      }

      @InternalCoroutinesApi
      public override fun tryResumeWithException(exception: Throwable): Any? {
         return this.cont.tryResumeWithException(exception);
      }

      @InternalCoroutinesApi
      public override fun completeResume(token: Any) {
         this.cont.completeResume(token);
      }

      @InternalCoroutinesApi
      public override fun initCancellability() {
         this.cont.initCancellability();
      }

      public override fun cancel(cause: Throwable?): Boolean {
         return this.cont.cancel(cause);
      }

      public override fun invokeOnCancellation(handler: (Throwable?) -> Unit) {
         this.cont.invokeOnCancellation(handler);
      }

      public override fun invokeOnCancellation(segment: Segment<*>, index: Int) {
         this.cont.invokeOnCancellation(segment, index);
      }

      @ExperimentalCoroutinesApi
      public open fun CoroutineDispatcher.resumeUndispatched(value: Unit) {
         this.cont.resumeUndispatched(`$this$resumeUndispatched`, value);
      }

      @ExperimentalCoroutinesApi
      public override fun CoroutineDispatcher.resumeUndispatchedWithException(exception: Throwable) {
         this.cont.resumeUndispatchedWithException(`$this$resumeUndispatchedWithException`, exception);
      }

      @Deprecated(message = "Use the overload that also accepts the `value` and the coroutine context in lambda", replaceWith = @ReplaceWith(expression = "resume(value) { cause, _, _ -> onCancellation(cause) }", imports = []), level = DeprecationLevel.WARNING)
      public open fun resume(value: Unit, onCancellation: ((Throwable) -> Unit)?) {
         this.cont.resume(value, onCancellation);
      }

      public override fun resumeWith(result: Result<Unit>) {
         this.cont.resumeWith(result);
      }

      @JvmStatic
      fun `tryResume$lambda$3`(
         `this$0`: MutexImpl, `this$1`: MutexImpl.CancellableContinuationWithOwner, var2: java.lang.Throwable, var3: Unit, var4: CoroutineContext
      ): Unit {
         if (DebugKt.getASSERTIONS_ENABLED()) {
            val it: Any = MutexImpl.access$getOwner$volatile$FU().get(`this$0`);
            if (it != MutexKt.access$getNO_OWNER$p() && it != `this$1`.owner) {
               throw new AssertionError();
            }
         }

         MutexImpl.access$getOwner$volatile$FU().set(`this$0`, `this$1`.owner);
         `this$0`.unlock(`this$1`.owner);
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `resume$lambda$6`(`this$0`: MutexImpl, `this$1`: MutexImpl.CancellableContinuationWithOwner, it: java.lang.Throwable): Unit {
         `this$0`.unlock(`this$1`.owner);
         return Unit.INSTANCE;
      }
   }

   @SourceDebugExtension(["SMAP\nMutex.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Mutex.kt\nkotlinx/coroutines/sync/MutexImpl$SelectInstanceWithOwner\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,314:1\n1#2:315\n*E\n"])
   private inner class SelectInstanceWithOwner<Q>(select: SelectInstanceInternal<Any>, owner: Any?) : SelectInstanceInternal<Q> {
      public final val select: SelectInstanceInternal<Any>
      public final val owner: Any?
      public open val context: CoroutineContext

      init {
         this.this$0 = `this$0`;
         this.select = select;
         this.owner = owner;
      }

      public override fun trySelect(clauseObject: Any, result: Any?): Boolean {
         val var3: MutexImpl = this.this$0;
         if (DebugKt.getASSERTIONS_ENABLED() && MutexImpl.access$getOwner$volatile$FU().get(var3) != MutexKt.access$getNO_OWNER$p()) {
            throw new AssertionError();
         } else {
            val var7: Boolean = this.select.trySelect(clauseObject, result);
            val var8: MutexImpl = this.this$0;
            if (var7) {
               MutexImpl.access$getOwner$volatile$FU().set(var8, this.owner);
            }

            return var7;
         }
      }

      public override fun selectInRegistrationPhase(internalResult: Any?) {
         val var2: MutexImpl = this.this$0;
         if (DebugKt.getASSERTIONS_ENABLED() && MutexImpl.access$getOwner$volatile$FU().get(var2) != MutexKt.access$getNO_OWNER$p()) {
            throw new AssertionError();
         } else {
            MutexImpl.access$getOwner$volatile$FU().set(this.this$0, this.owner);
            this.select.selectInRegistrationPhase(internalResult);
         }
      }

      public override fun disposeOnCompletion(disposableHandle: DisposableHandle) {
         this.select.disposeOnCompletion(disposableHandle);
      }

      public override fun invokeOnCancellation(segment: Segment<*>, index: Int) {
         this.select.invokeOnCancellation(segment, index);
      }
   }
}
