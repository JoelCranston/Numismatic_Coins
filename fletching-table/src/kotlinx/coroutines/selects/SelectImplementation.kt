package kotlinx.coroutines.selects

import java.util.ArrayList
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.internal.LowPriorityInOverloadResolution
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.CancelHandler
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.CancellableContinuationKt
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.internal.Segment
import kotlinx.coroutines.internal.StackTraceRecoveryKt
import kotlinx.coroutines.selects.SelectImplementation.doSelectSuspend.1

@PublishedApi
@SourceDebugExtension(["SMAP\nSelect.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Select.kt\nkotlinx/coroutines/selects/SelectImplementation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 5 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,904:1\n1#2:905\n2632#3,3:906\n1863#3,2:918\n1863#3,2:926\n1863#3,2:928\n426#4,9:909\n435#4,2:920\n149#5,4:922\n*S KotlinDebug\n*F\n+ 1 Select.kt\nkotlinx/coroutines/selects/SelectImplementation\n*L\n529#1:906,3\n593#1:918,2\n749#1:926,2\n774#1:928,2\n569#1:909,9\n569#1:920,2\n734#1:922,4\n*E\n"])
internal open class SelectImplementation<R>(context: CoroutineContext) : CancelHandler, SelectBuilder<R>, SelectInstanceInternal<R> {
   public open val context: CoroutineContext
   private final val state: AtomicRef<Any>

   private final val inRegistrationPhase: Boolean
      private final get() {
         val it: Any = getState$volatile$FU().get(this);
         return it === SelectKt.access$getSTATE_REG$p() || it is java.util.List;
      }


   private final val isSelected: Boolean
      private final get() {
         return getState$volatile$FU().get(this) is SelectImplementation.ClauseData;
      }


   private final val isCancelled: Boolean
      private final get() {
         return getState$volatile$FU().get(this) === SelectKt.access$getSTATE_CANCELLED$p();
      }


   private final var clauses: MutableList<kotlinx.coroutines.selects.SelectImplementation.ClauseData>?
   private final var disposableHandleOrSegment: Any?
   private final var indexInSegment: Int
   private final var internalResult: Any?

   init {
      this.context = context;
      this.state$volatile = SelectKt.access$getSTATE_REG$p();
      this.clauses = new ArrayList<>(2);
      this.indexInSegment = -1;
      this.internalResult = SelectKt.access$getNO_RESULT$p();
   }

   @PublishedApi
   internal open suspend fun doSelect(): Any {
      return doSelect$suspendImpl(this, `$completion`);
   }

   private suspend fun doSelectSuspend(): Any {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = this;
            `$continuation`.label = 1;
            if (this.waitUntilSelected(`$continuation`) === var4) {
               return var4;
            }
            break;
         case 1:
            this = `$continuation`.L$0 as SelectImplementation;
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            ResultKt.throwOnFailure(`$result`);
            return `$result`;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      `$continuation`.L$0 = null;
      `$continuation`.label = 2;
      val var10000: Any = this.complete(`$continuation`);
      return if (var10000 === var4) var4 else var10000;
   }

   public override operator fun SelectClause0.invoke(block: (Continuation<Any>) -> Any?) {
      register$default(
         this,
         new SelectImplementation.ClauseData(
            this,
            (Function3<Object, ? super SelectInstance<?>, Object, Unit>)`$this$invoke`.getClauseObject(),
            `$this$invoke`.getRegFunc(),
            `$this$invoke`.getProcessResFunc(),
            SelectKt.getPARAM_CLAUSE_0(),
            block,
            `$this$invoke`.getOnCancellationConstructor()
         ),
         false,
         1,
         null
      );
   }

   public override operator fun <Q> SelectClause1<Q>.invoke(block: (Q, Continuation<Any>) -> Any?) {
      register$default(
         this,
         new SelectImplementation.ClauseData(
            this,
            (Function3<Object, ? super SelectInstance<?>, Object, Unit>)`$this$invoke`.getClauseObject(),
            `$this$invoke`.getRegFunc(),
            `$this$invoke`.getProcessResFunc(),
            null,
            block,
            `$this$invoke`.getOnCancellationConstructor()
         ),
         false,
         1,
         null
      );
   }

   public override operator fun <P, Q> SelectClause2<P, Q>.invoke(param: P, block: (Q, Continuation<Any>) -> Any?) {
      register$default(
         this,
         new SelectImplementation.ClauseData(
            this,
            (Function3<Object, ? super SelectInstance<?>, Object, Unit>)`$this$invoke`.getClauseObject(),
            `$this$invoke`.getRegFunc(),
            `$this$invoke`.getProcessResFunc(),
            param,
            block,
            `$this$invoke`.getOnCancellationConstructor()
         ),
         false,
         1,
         null
      );
   }

   @JvmName(name = "register")
   internal fun kotlinx.coroutines.selects.SelectImplementation.ClauseData.register(reregister: Boolean = false) {
      if (DebugKt.getASSERTIONS_ENABLED() && getState$volatile$FU().get(this) === SelectKt.access$getSTATE_CANCELLED$p()) {
         throw new AssertionError();
      } else if (getState$volatile$FU().get(this) !is SelectImplementation.ClauseData) {
         if (!reregister) {
            this.checkClauseObject(`$this$register`.clauseObject);
         }

         if (`$this$register`.tryRegisterAsWaiter(this)) {
            if (!reregister) {
               val var10000: java.util.List = this.clauses;
               var10000.add(`$this$register`);
            }

            `$this$register`.disposableHandleOrSegment = this.disposableHandleOrSegment;
            `$this$register`.indexInSegment = this.indexInSegment;
            this.disposableHandleOrSegment = null;
            this.indexInSegment = -1;
         } else {
            getState$volatile$FU().set(this, `$this$register`);
         }
      }
   }

   private fun checkClauseObject(clauseObject: Any) {
      val var10000: java.util.List = this.clauses;
      val `$this$none$iv`: java.lang.Iterable = var10000;
      var var11: Boolean;
      if (var10000 is java.util.Collection && (var10000 as java.util.Collection).isEmpty()) {
         var11 = true;
      } else {
         label39: {
            for (Object element$iv : $this$none$iv) {
               if ((`element$iv` as SelectImplementation.ClauseData).clauseObject === clauseObject) {
                  var11 = false;
                  break label39;
               }
            }

            var11 = true;
         }
      }

      if (!var11) {
         throw new IllegalStateException(("Cannot use select clauses on the same object: $clauseObject").toString());
      }
   }

   public override fun disposeOnCompletion(disposableHandle: DisposableHandle) {
      this.disposableHandleOrSegment = disposableHandle;
   }

   public override fun invokeOnCancellation(segment: Segment<*>, index: Int) {
      this.disposableHandleOrSegment = segment;
      this.indexInSegment = index;
   }

   public override fun selectInRegistrationPhase(internalResult: Any?) {
      this.internalResult = internalResult;
   }

   private suspend fun waitUntilSelected() {
      val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
      `cancellable$iv`.initCancellability();
      val cont: CancellableContinuation = `cancellable$iv`;
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = access$getState$volatile$FU();

      while (true) {
         val curState: Any = `handler$atomicfu$iv`.get(this);
         if (curState === SelectKt.access$getSTATE_REG$p()) {
            if (access$getState$volatile$FU().compareAndSet(this, curState, cont)) {
               CancellableContinuationKt.invokeOnCancellation(cont, this);
               break;
            }
         } else {
            if (curState !is java.util.List) {
               if (curState !is SelectImplementation.ClauseData) {
                  throw new IllegalStateException(("unexpected state: $curState").toString());
               }

               cont.resume(Unit.INSTANCE, (curState as SelectImplementation.ClauseData).createOnCancellationAction(this, access$getInternalResult$p(this)));
               break;
            }

            if (access$getState$volatile$FU().compareAndSet(this, curState, SelectKt.access$getSTATE_REG$p())) {
               val `$this$forEach$iv`: java.lang.Iterable;
               for (Object element$iv : $this$forEach$iv) {
                  access$reregisterClause(this, `element$iv`);
               }
            }
         }
      }

      val var10000: Any = `cancellable$iv`.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   private fun reregisterClause(clauseObject: Any) {
      val var10000: SelectImplementation.ClauseData = this.findClause(clauseObject);
      var10000.disposableHandleOrSegment = null;
      var10000.indexInSegment = -1;
      this.register(var10000, true);
   }

   public override fun trySelect(clauseObject: Any, result: Any?): Boolean {
      return this.trySelectInternal(clauseObject, result) == 0;
   }

   public fun trySelectDetailed(clauseObject: Any, result: Any?): TrySelectDetailedResult {
      return SelectKt.access$TrySelectDetailedResult(this.trySelectInternal(clauseObject, result));
   }

   private fun trySelectInternal(clauseObject: Any, internalResult: Any?): Int {
      while (true) {
         val curState: Any = getState$volatile$FU().get(this);
         if (curState is CancellableContinuation) {
            val var10000: SelectImplementation.ClauseData = this.findClause(clauseObject);
            if (var10000 != null) {
               val onCancellation: Function3 = var10000.createOnCancellationAction(this, internalResult);
               if (getState$volatile$FU().compareAndSet(this, curState, var10000)) {
                  val cont: CancellableContinuation = curState as CancellableContinuation;
                  this.internalResult = internalResult;
                  if (SelectKt.access$tryResume(cont, onCancellation)) {
                     return 0;
                  }

                  this.internalResult = SelectKt.access$getNO_RESULT$p();
                  return 2;
               }
            }
         } else {
            if (!(curState == SelectKt.access$getSTATE_COMPLETED$p()) && curState !is SelectImplementation.ClauseData) {
               if (curState == SelectKt.access$getSTATE_CANCELLED$p()) {
                  return 2;
               }

               if (curState == SelectKt.access$getSTATE_REG$p()) {
                  if (!getState$volatile$FU().compareAndSet(this, curState, CollectionsKt.listOf(clauseObject))) {
                     continue;
                  }

                  return 1;
               }

               if (curState is java.util.List) {
                  if (!getState$volatile$FU().compareAndSet(this, curState, CollectionsKt.plus(curState as MutableCollection<Any>, clauseObject))) {
                     continue;
                  }

                  return 1;
               }

               throw new IllegalStateException(("Unexpected state: $curState").toString());
            }

            return 3;
         }
      }
   }

   private fun findClause(clauseObject: Any): kotlinx.coroutines.selects.SelectImplementation.ClauseData? {
      if (this.clauses == null) {
         return null;
      } else {
         val var4: java.util.Iterator = this.clauses.iterator();

         var var10000: Any;
         while (true) {
            if (var4.hasNext()) {
               val var5: Any = var4.next();
               if ((var5 as SelectImplementation.ClauseData).clauseObject != clauseObject) {
                  continue;
               }

               var10000 = (SelectImplementation.ClauseData)var5;
               break;
            }

            var10000 = null;
            break;
         }

         var10000 = var10000;
         if (var10000 == null) {
            throw new IllegalStateException(("Clause with object $clauseObject is not found").toString());
         } else {
            return var10000;
         }
      }
   }

   private suspend fun complete(): Any {
      label13:
      if (DebugKt.getASSERTIONS_ENABLED() && !this.isSelected()) {
         throw new AssertionError();
      } else {
         val var10000: Any = getState$volatile$FU().get(this);
         val var5: SelectImplementation.ClauseData = var10000 as SelectImplementation.ClauseData;
         val internalResult: Any = this.internalResult;
         this.cleanup(var5);
         return if (!DebugKt.getRECOVER_STACK_TRACES())
            var5.invokeBlock(var5.processResult(internalResult), `$completion`)
            else
            this.processResultAndInvokeBlockRecoveringException(var5, internalResult, `$completion`);
      }
   }

   private suspend fun processResultAndInvokeBlockRecoveringException(clause: kotlinx.coroutines.selects.SelectImplementation.ClauseData, internalResult: Any?): Any {
      var `$continuation`: Continuation;
      label53: {
         if (`$completion` is kotlinx.coroutines.selects.SelectImplementation.processResultAndInvokeBlockRecoveringException.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.selects.SelectImplementation.processResultAndInvokeBlockRecoveringException.1;
            if ((
                  (`$completion` as kotlinx.coroutines.selects.SelectImplementation.processResultAndInvokeBlockRecoveringException.1).label and Integer.MIN_VALUE
               )
               != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label53;
            }
         }

         `$continuation` = new kotlinx.coroutines.selects.SelectImplementation.processResultAndInvokeBlockRecoveringException.1(this, `$completion`);
      }

      var e: java.lang.Throwable;
      label46: {
         val `$result`: Any = `$continuation`.result;
         val var11: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var var10000: Any;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);

               try {
                  val blockArgument: Any = clause.processResult(internalResult);
                  `$continuation`.label = 1;
                  var10000 = clause.invokeBlock(blockArgument, `$continuation`);
               } catch (var13: java.lang.Throwable) {
                  e = var13;
                  if (!DebugKt.getRECOVER_STACK_TRACES()) {
                     throw var13;
                  }
                  break label46;
               }

               if (var10000 === var11) {
                  return var11;
               }
               break;
            case 1:
               try {
                  ResultKt.throwOnFailure(`$result`);
                  var10000 = `$result`;
                  break;
               } catch (var14: java.lang.Throwable) {
                  e = var14;
                  if (!DebugKt.getRECOVER_STACK_TRACES()) {
                     throw var14;
                  }
                  break label46;
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            return var10000;
         } catch (var12: java.lang.Throwable) {
            e = var12;
            if (!DebugKt.getRECOVER_STACK_TRACES()) {
               throw var12;
            }
         }
      }

      if (`$continuation` !is CoroutineStackFrame) {
         throw e;
      } else {
         throw StackTraceRecoveryKt.access$recoverFromStackFrame(e, `$continuation` as CoroutineStackFrame);
      }
   }

   private fun cleanup(selectedClause: kotlinx.coroutines.selects.SelectImplementation.ClauseData) {
      if (DebugKt.getASSERTIONS_ENABLED() && !(getState$volatile$FU().get(this) == selectedClause)) {
         throw new AssertionError();
      } else if (this.clauses != null) {
         val `$this$forEach$iv`: java.lang.Iterable;
         for (Object element$iv : $this$forEach$iv) {
            val clause: SelectImplementation.ClauseData = `element$iv` as SelectImplementation.ClauseData;
            if (`element$iv` as SelectImplementation.ClauseData != selectedClause) {
               clause.dispose();
            }
         }

         getState$volatile$FU().set(this, SelectKt.access$getSTATE_COMPLETED$p());
         this.internalResult = SelectKt.access$getNO_RESULT$p();
         this.clauses = null;
      }
   }

   public override fun invoke(cause: Throwable?) {
      val `$this$forEach$iv`: AtomicReferenceFieldUpdater = getState$volatile$FU();

      val `$i$f$forEach`: Any;
      do {
         `$i$f$forEach` = `$this$forEach$iv`.get(this);
         if (`$i$f$forEach` === SelectKt.access$getSTATE_COMPLETED$p()) {
            return;
         }
      } while (!handler$atomicfu$iv.compareAndSet(this, $i$f$forEach, SelectKt.access$getSTATE_CANCELLED$p()));

      if (this.clauses != null) {
         for (Object element$iv : var9) {
            (var12 as SelectImplementation.ClauseData).dispose();
         }

         this.internalResult = SelectKt.access$getNO_RESULT$p();
         this.clauses = null;
      }
   }

   override fun <P, Q> SelectClause2<? super P, ? extends Q>.invoke(block: (Q?, Continuation<? super R>?) -> Any) {
      SelectBuilder.DefaultImpls.invoke(this, `$this$invoke`, block);
   }

   /** @deprecated */
   @Deprecated(message = "Replaced with the same extension function", replaceWith = @ReplaceWith(expression = "onTimeout", imports = ["kotlinx.coroutines.selects.onTimeout"]), level = DeprecationLevel.ERROR)
   @ExperimentalCoroutinesApi
   @LowPriorityInOverloadResolution
   override fun onTimeout(timeMillis: Long, block: (Continuation<? super R>?) -> Any) {
      SelectBuilder.DefaultImpls.onTimeout(this, timeMillis, block);
   }

   @SourceDebugExtension(["SMAP\nSelect.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Select.kt\nkotlinx/coroutines/selects/SelectImplementation$ClauseData\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,904:1\n1#2:905\n*E\n"])
   internal inner class ClauseData(clauseObject: Any,
      regFunc: (Any, SelectInstance<*>, Any?) -> Unit,
      processResFunc: (Any, Any?, Any?) -> Any?,
      param: Any?,
      block: Any,
      onCancellationConstructor: ((SelectInstance<*>, Any?, Any?) -> (Throwable, Any?, CoroutineContext) -> Unit)?
   ) {
      public final val clauseObject: Any
      private final val regFunc: (Any, SelectInstance<*>, Any?) -> Unit
      private final val processResFunc: (Any, Any?, Any?) -> Any?
      private final val param: Any?
      private final val block: Any
      public final val onCancellationConstructor: ((SelectInstance<*>, Any?, Any?) -> (Throwable, Any?, CoroutineContext) -> Unit)?

      public final var disposableHandleOrSegment: Any?
         private set

      public final var indexInSegment: Int
         private set

      init {
         this.this$0 = `this$0`;
         this.clauseObject = clauseObject;
         this.regFunc = regFunc;
         this.processResFunc = processResFunc;
         this.param = param;
         this.block = block;
         this.onCancellationConstructor = onCancellationConstructor;
         this.indexInSegment = -1;
      }

      public fun tryRegisterAsWaiter(select: SelectImplementation<Any>): Boolean {
         if (DebugKt.getASSERTIONS_ENABLED() && !SelectImplementation.access$getInRegistrationPhase(select) && !SelectImplementation.access$isCancelled(select)
            )
          {
            throw new AssertionError();
         } else if (DebugKt.getASSERTIONS_ENABLED() && SelectImplementation.access$getInternalResult$p(select) != SelectKt.access$getNO_RESULT$p()) {
            throw new AssertionError();
         } else {
            this.regFunc.invoke(this.clauseObject, select, this.param);
            return SelectImplementation.access$getInternalResult$p(select) === SelectKt.access$getNO_RESULT$p();
         }
      }

      public fun processResult(result: Any?): Any? {
         return this.processResFunc.invoke(this.clauseObject, this.param, result);
      }

      public suspend fun invokeBlock(argument: Any?): Any {
         val block: Any = this.block;
         if (this.param === SelectKt.getPARAM_CLAUSE_0()) {
            return (block as Function1).invoke(`$completion`);
         } else {
            return (block as Function2).invoke(argument, `$completion`);
         }
      }

      public fun dispose() {
         val var1: Any = this.disposableHandleOrSegment;
         if (this.disposableHandleOrSegment is Segment) {
            (this.disposableHandleOrSegment as Segment).onCancellation(this.indexInSegment, null, this.this$0.getContext());
         } else {
            val var10000: DisposableHandle = this.disposableHandleOrSegment as? DisposableHandle;
            if ((this.disposableHandleOrSegment as? DisposableHandle) != null) {
               var10000.dispose();
            }
         }
      }

      public fun createOnCancellationAction(select: SelectInstance<*>, internalResult: Any?): ((Throwable, Any?, CoroutineContext) -> Unit)? {
         return if (this.onCancellationConstructor != null) this.onCancellationConstructor.invoke(select, this.param, internalResult) else null;
      }
   }
}
