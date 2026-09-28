package kotlinx.coroutines

import java.util.ArrayList
import java.util.Collections
import java.util.IdentityHashMap
import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.CoroutineContext.Key
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics
import kotlinx.atomicfu.AtomicBoolean
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.JobSupport.onAwaitInternal.2
import kotlinx.coroutines.JobSupport.onJoin.1
import kotlinx.coroutines.internal.LockFreeLinkedListHead
import kotlinx.coroutines.internal.LockFreeLinkedListNode
import kotlinx.coroutines.internal.StackTraceRecoveryKt
import kotlinx.coroutines.internal.Symbol
import kotlinx.coroutines.selects.SelectClause0
import kotlinx.coroutines.selects.SelectClause0Impl
import kotlinx.coroutines.selects.SelectClause1
import kotlinx.coroutines.selects.SelectClause1Impl
import kotlinx.coroutines.selects.SelectInstance

/** @deprecated */
@Deprecated(message = "This is internal API and may be removed in the future releases", level = DeprecationLevel.ERROR)
@SourceDebugExtension(["SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 4 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 Concurrent.kt\nkotlinx/coroutines/internal/ConcurrentKt\n+ 7 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n+ 8 LockFreeLinkedList.kt\nkotlinx/coroutines/internal/LockFreeLinkedListHead\n+ 9 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,1583:1\n732#1,3:1587\n361#1,2:1597\n363#1,5:1602\n368#1,5:1608\n373#1,2:1616\n361#1,2:1618\n363#1,5:1623\n368#1,5:1629\n373#1,2:1637\n169#1,2:1645\n734#1:1647\n536#1:1648\n169#1,2:1649\n537#1,15:1651\n169#1,2:1666\n169#1,2:1668\n169#1,2:1681\n732#1,3:1683\n732#1,3:1686\n169#1,2:1689\n732#1,3:1691\n169#1,2:1694\n169#1,2:1698\n169#1,2:1700\n536#1:1704\n169#1,2:1705\n537#1,15:1707\n1#2:1584\n1#2:1607\n1#2:1628\n29#3:1585\n29#3:1696\n29#3:1702\n16#4:1586\n16#4:1697\n16#4:1703\n295#5,2:1590\n295#5,2:1592\n23#6:1594\n159#7:1595\n159#7:1596\n149#7,4:1722\n273#8,3:1599\n276#8,3:1613\n273#8,3:1620\n276#8,3:1634\n273#8,6:1639\n426#9,11:1670\n*S KotlinDebug\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport\n*L\n241#1:1587,3\n324#1:1597,2\n324#1:1602,5\n324#1:1608,5\n324#1:1616,2\n357#1:1618,2\n357#1:1623,5\n357#1:1629,5\n357#1:1637,2\n377#1:1645,2\n422#1:1647\n468#1:1648\n468#1:1649,2\n468#1:1651,15\n536#1:1666,2\n579#1:1668,2\n621#1:1681,2\n648#1:1683,3\n657#1:1686,3\n721#1:1689,2\n750#1:1691,3\n763#1:1694,2\n836#1:1698,2\n858#1:1700,2\n1023#1:1704\n1023#1:1705,2\n1023#1:1707,15\n324#1:1607\n357#1:1628\n204#1:1585\n766#1:1696\n911#1:1702\n204#1:1586\n766#1:1697\n911#1:1703\n252#1:1590,2\n256#1:1592,2\n264#1:1594\n270#1:1595\n272#1:1596\n1327#1:1722,4\n324#1:1599,3\n324#1:1613,3\n357#1:1620,3\n357#1:1634,3\n362#1:1639,6\n585#1:1670,11\n*E\n"])
public open class JobSupport(active: Boolean) : Job, ChildJob, ParentJob {
   public final val key: Key<*>
      public final get() {
         return Job.Key;
      }


   private final val _state: AtomicRef<Any?>
   private final val _parentHandle: AtomicRef<ChildHandle?>

   internal final var parentHandle: ChildHandle?
      internal final get() {
         return get_parentHandle$volatile$FU().get(this) as ChildHandle;
      }

      internal final set(value) {
         get_parentHandle$volatile$FU().set(this, value);
      }


   public open val parent: Job?
      public open get() {
         val var10000: ChildHandle = this.getParentHandle$kotlinx_coroutines_core();
         return if (var10000 != null) var10000.getParent() else null;
      }


   internal final val state: Any?
      internal final get() {
         return get_state$volatile$FU().get(this);
      }


   public open val isActive: Boolean
      public open get() {
         val state: Any = this.getState$kotlinx_coroutines_core();
         return state is Incomplete && (state as Incomplete).isActive();
      }


   public final val isCompleted: Boolean
      public final get() {
         return this.getState$kotlinx_coroutines_core() !is Incomplete;
      }


   public final val isCancelled: Boolean
      public final get() {
         val state: Any = this.getState$kotlinx_coroutines_core();
         return state is CompletedExceptionally || state is JobSupport.Finishing && (state as JobSupport.Finishing).isCancelling();
      }


   protected final val completionCause: Throwable?
      protected final get() {
         val state: Any = this.getState$kotlinx_coroutines_core();
         val var10000: java.lang.Throwable;
         if (state is JobSupport.Finishing) {
            var10000 = (state as JobSupport.Finishing).getRootCause();
            if (var10000 == null) {
               throw new IllegalStateException(("Job is still new or active: $this").toString());
            }
         } else {
            if (state is Incomplete) {
               throw new IllegalStateException(("Job is still new or active: $this").toString());
            }

            var10000 = if (state is CompletedExceptionally) (state as CompletedExceptionally).cause else null;
         }

         return var10000;
      }


   protected final val completionCauseHandled: Boolean
      protected final get() {
         val it: Any = this.getState$kotlinx_coroutines_core();
         return it is CompletedExceptionally && (it as CompletedExceptionally).getHandled();
      }


   public final val onJoin: SelectClause0
      public final get() {
         val var10003: 1 = 1.INSTANCE;
         return new SelectClause0Impl(this, TypeIntrinsics.beforeCheckcastToFunctionOfArity(var10003, 3) as Function3, null, 4, null);
      }


   internal open val onCancelComplete: Boolean
      internal open get() {
         return false;
      }


   private final val exceptionOrNull: Throwable?
      private final get() {
         return if ((`$this$exceptionOrNull` as? CompletedExceptionally) != null) (`$this$exceptionOrNull` as? CompletedExceptionally).cause else null;
      }


   public final val children: Sequence<Job>
      public final get() {
         return SequencesKt.sequence(new kotlinx.coroutines.JobSupport.children.1(this, null));
      }


   protected open val isScopedCoroutine: Boolean
      protected open get() {
         return false;
      }


   internal open val handlesException: Boolean
      internal open get() {
         return true;
      }


   private final val isCancelling: Boolean
      private final get() {
         return `$this$isCancelling` is JobSupport.Finishing && (`$this$isCancelling` as JobSupport.Finishing).isCancelling();
      }


   public final val isCompletedExceptionally: Boolean
      public final get() {
         return this.getState$kotlinx_coroutines_core() is CompletedExceptionally;
      }


   protected final val onAwaitInternal: SelectClause1<*>
      protected final get() {
         val var10003: kotlinx.coroutines.JobSupport.onAwaitInternal.1 = kotlinx.coroutines.JobSupport.onAwaitInternal.1.INSTANCE;
         val var1: Function3 = TypeIntrinsics.beforeCheckcastToFunctionOfArity(var10003, 3) as Function3;
         val var10004: 2 = 2.INSTANCE;
         return new SelectClause1Impl(this, var1, TypeIntrinsics.beforeCheckcastToFunctionOfArity(var10004, 3) as Function3, null, 8, null);
      }


   init {
      this._state$volatile = if (active) JobSupportKt.access$getEMPTY_ACTIVE$p() else JobSupportKt.access$getEMPTY_NEW$p();
   }

   protected fun initParentJob(parent: Job?) {
      if (DebugKt.getASSERTIONS_ENABLED() && this.getParentHandle$kotlinx_coroutines_core() != null) {
         throw new AssertionError();
      } else if (parent == null) {
         this.setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
      } else {
         parent.start();
         val var3: ChildHandle = parent.attachChild(this);
         this.setParentHandle$kotlinx_coroutines_core(var3);
         if (this.isCompleted()) {
            var3.dispose();
            this.setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
         }
      }
   }

   private inline fun loopOnState(block: (Any?) -> Unit): Nothing {
      while (true) {
         block.invoke(this.getState$kotlinx_coroutines_core());
      }
   }

   private fun finalizeFinishingState(state: kotlinx.coroutines.JobSupport.Finishing, proposedUpdate: Any?): Any? {
      if (DebugKt.getASSERTIONS_ENABLED() && this.getState$kotlinx_coroutines_core() != state) {
         throw new AssertionError();
      } else if (DebugKt.getASSERTIONS_ENABLED() && state.isSealed()) {
         throw new AssertionError();
      } else if (DebugKt.getASSERTIONS_ENABLED() && !state.isCompleting()) {
         throw new AssertionError();
      } else {
         val var17: java.lang.Throwable = if ((proposedUpdate as? CompletedExceptionally) != null) (proposedUpdate as? CompletedExceptionally).cause else null;
         val var18: Boolean;
         val var10000: java.lang.Throwable;
         synchronized (state) {
            var18 = state.isCancelling();
            val exceptions: java.util.List = state.sealLocked(var17);
            val finalCause: java.lang.Throwable = this.getFinalRootCause(state, exceptions);
            if (finalCause != null) {
               this.addSuppressedExceptions(finalCause, exceptions);
            }

            var10000 = finalCause;
         }

         val var19: Any = if (var10000 == null)
            proposedUpdate
            else
            (if (var10000 === var17) proposedUpdate else new CompletedExceptionally(var10000, false, 2, null));
         if (var10000 != null && (this.cancelParent(var10000) || this.handleJobException(var10000))) {
            (var19 as CompletedExceptionally).makeHandled();
         }

         if (!var18) {
            this.onCancelling(var10000);
         }

         this.onCompletionInternal(var19);
         if (DebugKt.getASSERTIONS_ENABLED() && !get_state$volatile$FU().compareAndSet(this, state, (JobSupport.Finishing)JobSupportKt.boxIncomplete(var19))) {
            throw new AssertionError();
         } else {
            this.completeStateFinalization(state, var19);
            return var19;
         }
      }
   }

   private fun getFinalRootCause(state: kotlinx.coroutines.JobSupport.Finishing, exceptions: List<Throwable>): Throwable? {
      label36:
      if (exceptions.isEmpty()) {
         return if (state.isCancelling()) new JobCancellationException(access$cancellationExceptionMessage(this), null, this) else null;
      } else {
         val `$this$firstOrNull$iv`: java.util.Iterator = exceptions.iterator();

         var var10000: Any;
         while (true) {
            if (`$this$firstOrNull$iv`.hasNext()) {
               val `$i$f$firstOrNull`: Any = `$this$firstOrNull$iv`.next();
               if (`$i$f$firstOrNull` as java.lang.Throwable is CancellationException) {
                  continue;
               }

               var10000 = `$i$f$firstOrNull`;
               break;
            }

            var10000 = null;
            break;
         }

         val firstNonCancellation: java.lang.Throwable = var10000 as java.lang.Throwable;
         if (var10000 as java.lang.Throwable != null) {
            return firstNonCancellation;
         } else {
            val var13: java.lang.Throwable = exceptions.get(0) as java.lang.Throwable;
            if (var13 is TimeoutCancellationException) {
               val var19: java.util.Iterator = exceptions.iterator();

               while (true) {
                  if (!var19.hasNext()) {
                     var10000 = null;
                     break;
                  }

                  val var20: Any = var19.next();
                  if (var20 as java.lang.Throwable != var13 && var20 as java.lang.Throwable is TimeoutCancellationException) {
                     var10000 = var20;
                     break;
                  }
               }

               val var15: java.lang.Throwable = var10000 as java.lang.Throwable;
               if (var10000 as java.lang.Throwable != null) {
                  return var15;
               }
            }

            return var13;
         }
      }
   }

   private fun addSuppressedExceptions(rootCause: Throwable, exceptions: List<Throwable>) {
      if (exceptions.size() > 1) {
         val seenExceptions: java.util.Set = Collections.newSetFromMap(new IdentityHashMap(exceptions.size()));
         val var9: java.lang.Throwable = if (!DebugKt.getRECOVER_STACK_TRACES()) rootCause else StackTraceRecoveryKt.unwrapImpl(rootCause);

         for (java.lang.Throwable exception : exceptions) {
            val unwrapped: java.lang.Throwable = if (!DebugKt.getRECOVER_STACK_TRACES()) exception else StackTraceRecoveryKt.unwrapImpl(exception);
            if (unwrapped != rootCause && unwrapped != var9 && unwrapped !is CancellationException && seenExceptions.add(unwrapped)) {
               kotlin.ExceptionsKt.addSuppressed(rootCause, unwrapped);
            }
         }
      }
   }

   private fun tryFinalizeSimpleState(state: Incomplete, update: Any?): Boolean {
      if (DebugKt.getASSERTIONS_ENABLED() && state !is Empty && state !is JobNode) {
         throw new AssertionError();
      } else if (DebugKt.getASSERTIONS_ENABLED() && update is CompletedExceptionally) {
         throw new AssertionError();
      } else if (!get_state$volatile$FU().compareAndSet(this, state, (Incomplete)JobSupportKt.boxIncomplete(update))) {
         return false;
      } else {
         this.onCancelling(null);
         this.onCompletionInternal(update);
         this.completeStateFinalization(state, update);
         return true;
      }
   }

   private fun completeStateFinalization(state: Incomplete, update: Any?) {
      val var10000: ChildHandle = this.getParentHandle$kotlinx_coroutines_core();
      if (var10000 != null) {
         var10000.dispose();
         this.setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
      }

      val cause: java.lang.Throwable = if ((update as? CompletedExceptionally) != null) (update as? CompletedExceptionally).cause else null;
      if (state is JobNode) {
         try {
            (state as JobNode).invoke(cause);
         } catch (var7: java.lang.Throwable) {
            this.handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException("Exception in completion handler $state for $this", var7));
         }
      } else {
         val var8: NodeList = state.getList();
         if (var8 != null) {
            this.notifyCompletion(var8, cause);
         }
      }
   }

   private fun notifyCancelling(list: NodeList, cause: Throwable) {
      this.onCancelling(cause);
      list.close(4);
      val `this_$iv`: JobSupport = this;
      var `exception$iv`: Any = null;
      val `this_$iv$iv`: LockFreeLinkedListHead = list;
      var var10000: Any = list.getNext();

      for (LockFreeLinkedListNode cur$iv$iv = (LockFreeLinkedListNode)var10000; !(`it$iv` == `this_$iv$iv`); cur$iv$iv = cur$iv$iv.getNextNode()) {
         val var9: LockFreeLinkedListNode = `it$iv`;
         if (`it$iv` is JobNode && (`it$iv` as JobNode).getOnCancelling()) {
            try {
               (var9 as JobNode).invoke(cause);
            } catch (var17: java.lang.Throwable) {
               var10000 = `exception$iv` as java.lang.Throwable;
               if (`exception$iv` as java.lang.Throwable != null) {
                  kotlin.ExceptionsKt.addSuppressed((java.lang.Throwable)var10000, var17);
                  if (var10000 != null) {
                     continue;
                  }
               }

               `exception$iv` = new CompletionHandlerException("Exception in completion handler $`it$iv` for $`this_$iv`", var17);
            }
         }
      }

      var10000 = `exception$iv` as java.lang.Throwable;
      if (`exception$iv` as java.lang.Throwable != null) {
         `this_$iv`.handleOnCompletionException$kotlinx_coroutines_core((java.lang.Throwable)var10000);
      }

      this.cancelParent(cause);
   }

   private fun cancelParent(cause: Throwable): Boolean {
      if (this.isScopedCoroutine()) {
         return true;
      } else {
         val isCancellation: Boolean = cause is CancellationException;
         val parent: ChildHandle = this.getParentHandle$kotlinx_coroutines_core();
         if (parent != null && parent != NonDisposableHandle.INSTANCE) {
            return parent.childCancelled(cause) || isCancellation;
         } else {
            return isCancellation;
         }
      }
   }

   private fun NodeList.notifyCompletion(cause: Throwable?) {
      `$this$notifyCompletion`.close(1);
      val `this_$iv`: JobSupport = this;
      var `exception$iv`: Any = null;
      val `this_$iv$iv`: LockFreeLinkedListHead = `$this$notifyCompletion`;
      var var10000: Any = `$this$notifyCompletion`.getNext();

      for (LockFreeLinkedListNode cur$iv$iv = (LockFreeLinkedListNode)var10000; !(`it$iv` == `this_$iv$iv`); cur$iv$iv = cur$iv$iv.getNextNode()) {
         val var9: LockFreeLinkedListNode = `it$iv`;
         if (`it$iv` is JobNode) {
            val it: JobNode = `it$iv` as JobNode;
            if (true) {
               try {
                  (var9 as JobNode).invoke(cause);
               } catch (var17: java.lang.Throwable) {
                  var10000 = `exception$iv` as java.lang.Throwable;
                  if (`exception$iv` as java.lang.Throwable != null) {
                     kotlin.ExceptionsKt.addSuppressed((java.lang.Throwable)var10000, var17);
                     if (var10000 != null) {
                        continue;
                     }
                  }

                  `exception$iv` = new CompletionHandlerException("Exception in completion handler $`it$iv` for $`this_$iv`", var17);
               }
            }
         }
      }

      var10000 = `exception$iv` as java.lang.Throwable;
      if (`exception$iv` as java.lang.Throwable != null) {
         `this_$iv`.handleOnCompletionException$kotlinx_coroutines_core((java.lang.Throwable)var10000);
      }
   }

   private inline fun notifyHandlers(list: NodeList, cause: Throwable?, predicate: (JobNode) -> Boolean) {
      var exception: Any = null;
      val `this_$iv`: LockFreeLinkedListHead = list;
      var var10000: Any = list.getNext();

      for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode)var10000; !(it == `this_$iv`); cur$iv = cur$iv.getNextNode()) {
         val var9: LockFreeLinkedListNode = it;
         if (it is JobNode && predicate.invoke(it) as java.lang.Boolean) {
            try {
               (var9 as JobNode).invoke(cause);
            } catch (var15: java.lang.Throwable) {
               var10000 = exception as java.lang.Throwable;
               if (exception as java.lang.Throwable != null) {
                  kotlin.ExceptionsKt.addSuppressed((java.lang.Throwable)var10000, var15);
                  if (var10000 != null) {
                     continue;
                  }
               }

               exception = new CompletionHandlerException("Exception in completion handler $it for $this", var15);
            }
         }
      }

      var10000 = exception as java.lang.Throwable;
      if (exception as java.lang.Throwable != null) {
         this.handleOnCompletionException$kotlinx_coroutines_core((java.lang.Throwable)var10000);
      }
   }

   public override fun start(): Boolean {
      val `this_$iv`: JobSupport = this;

      while (true) {
         switch (this.startInternal(this_$iv.getState$kotlinx_coroutines_core())) {
            case 0:
               return false;
            case 1:
               return true;
            default:
         }
      }
   }

   private fun startInternal(state: Any?): Int {
      if (state is Empty) {
         if ((state as Empty).isActive()) {
            return 0;
         } else if (!get_state$volatile$FU().compareAndSet(this, state, JobSupportKt.access$getEMPTY_ACTIVE$p())) {
            return -1;
         } else {
            this.onStart();
            return 1;
         }
      } else if (state is InactiveNodeList) {
         if (!get_state$volatile$FU().compareAndSet(this, state, (state as InactiveNodeList).getList())) {
            return -1;
         } else {
            this.onStart();
            return 1;
         }
      } else {
         return 0;
      }
   }

   protected open fun onStart() {
   }

   public override fun getCancellationException(): CancellationException {
      val state: Any = this.getState$kotlinx_coroutines_core();
      val var2: CancellationException;
      if (state is JobSupport.Finishing) {
         val var10000: java.lang.Throwable = (state as JobSupport.Finishing).getRootCause();
         if (var10000 == null) {
            throw new IllegalStateException(("Job is still new or active: $this").toString());
         }

         var2 = this.toCancellationException(var10000, "${DebugStringsKt.getClassSimpleName(this)} is cancelling");
         if (var2 == null) {
            throw new IllegalStateException(("Job is still new or active: $this").toString());
         }
      } else {
         if (state is Incomplete) {
            throw new IllegalStateException(("Job is still new or active: $this").toString());
         }

         var2 = if (state is CompletedExceptionally)
            toCancellationException$default(this, (state as CompletedExceptionally).cause, null, 1, null)
            else
            new JobCancellationException("${DebugStringsKt.getClassSimpleName(this)} has completed normally", null, this);
      }

      return var2;
   }

   protected fun Throwable.toCancellationException(message: String? = null): CancellationException {
      var var10000: CancellationException = `$this$toCancellationException` as? CancellationException;
      if ((`$this$toCancellationException` as? CancellationException) == null) {
         val var5: JobCancellationException = new JobCancellationException;
         var var10002: java.lang.String = message;
         if (message == null) {
            var10002 = access$cancellationExceptionMessage(this);
         }

         var5./* $VF: Unable to resugar constructor */<init>(var10002, `$this$toCancellationException`, this);
         var10000 = var5;
      }

      return var10000;
   }

   public override fun invokeOnCompletion(handler: (Throwable?) -> Unit): DisposableHandle {
      return this.invokeOnCompletionInternal$kotlinx_coroutines_core(true, new InvokeOnCompletion(handler));
   }

   public override fun invokeOnCompletion(onCancelling: Boolean, invokeImmediately: Boolean, handler: (Throwable?) -> Unit): DisposableHandle {
      return this.invokeOnCompletionInternal$kotlinx_coroutines_core(
         invokeImmediately, if (onCancelling) new InvokeOnCancelling(handler) else new InvokeOnCompletion(handler)
      );
   }

   internal fun invokeOnCompletionInternal(invokeImmediately: Boolean, node: JobNode): DisposableHandle {
      node.setJob(this);
      val `this_$iv`: JobSupport = this;
      val `this_$iv$iv`: JobSupport = this;

      var var10000: Boolean;
      while (true) {
         val `state$iv`: Any = `this_$iv$iv`.getState$kotlinx_coroutines_core();
         if (`state$iv` is Empty) {
            if ((`state$iv` as Empty).isActive()) {
               if (get_state$volatile$FU().compareAndSet(`this_$iv`, `state$iv`, node)) {
                  var10000 = true;
                  break;
               }
            } else {
               `this_$iv`.promoteEmptyToNodeList(`state$iv` as Empty);
            }
         } else {
            if (`state$iv` !is Incomplete) {
               var10000 = false;
               break;
            }

            val `list$iv`: NodeList = (`state$iv` as Incomplete).getList();
            if (`list$iv` == null) {
               `this_$iv`.promoteSingleToNodeList(`state$iv` as JobNode);
            } else {
               val state: Incomplete = `state$iv` as Incomplete;
               if (node.getOnCancelling()) {
                  val rootCause: java.lang.Throwable = if ((state as? JobSupport.Finishing) != null) (state as? JobSupport.Finishing).getRootCause() else null;
                  if (rootCause != null) {
                     if (invokeImmediately) {
                        node.invoke(rootCause);
                     }

                     return NonDisposableHandle.INSTANCE;
                  }

                  var10000 = `list$iv`.addLast(node, 5);
               } else {
                  var10000 = `list$iv`.addLast(node, 1);
               }

               if (var10000) {
                  var10000 = true;
                  break;
               }
            }
         }
      }

      if (var10000) {
         return node;
      } else {
         if (invokeImmediately) {
            val var16: Any = this.getState$kotlinx_coroutines_core();
            node.invoke(if ((var16 as? CompletedExceptionally) != null) (var16 as? CompletedExceptionally).cause else null);
         }

         return NonDisposableHandle.INSTANCE;
      }
   }

   private inline fun tryPutNodeIntoList(node: JobNode, tryAdd: (Incomplete, NodeList) -> Boolean): Boolean {
      val `this_$iv`: JobSupport = this;

      while (true) {
         val state: Any = `this_$iv`.getState$kotlinx_coroutines_core();
         if (state is Empty) {
            if ((state as Empty).isActive()) {
               if (get_state$volatile$FU().compareAndSet(this, state, node)) {
                  return true;
               }
            } else {
               this.promoteEmptyToNodeList(state as Empty);
            }
         } else {
            if (state !is Incomplete) {
               return false;
            }

            val list: NodeList = (state as Incomplete).getList();
            if (list == null) {
               this.promoteSingleToNodeList(state as JobNode);
            } else if (tryAdd.invoke(state, list) as java.lang.Boolean) {
               return true;
            }
         }
      }
   }

   private fun promoteEmptyToNodeList(state: Empty) {
      val list: NodeList = new NodeList();
      get_state$volatile$FU().compareAndSet(this, state, if (state.isActive()) list else new InactiveNodeList(list));
   }

   private fun promoteSingleToNodeList(state: JobNode) {
      state.addOneIfEmpty(new NodeList());
      get_state$volatile$FU().compareAndSet(this, state, state.getNextNode());
   }

   public override suspend fun join() {
      if (!this.joinInternal()) {
         JobKt.ensureActive(`$completion`.getContext());
         return Unit.INSTANCE;
      } else {
         val var10000: Any = this.joinSuspend(`$completion`);
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }

   private fun joinInternal(): Boolean {
      val `this_$iv`: JobSupport = this;

      val state: Any;
      do {
         state = `this_$iv`.getState$kotlinx_coroutines_core();
         if (state !is Incomplete) {
            return false;
         }
      } while (this.startInternal(state) < 0);

      return true;
   }

   private suspend fun joinSuspend() {
      val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
      `cancellable$iv`.initCancellability();
      CancellableContinuationKt.disposeOnCancellation(
         `cancellable$iv`, JobKt.invokeOnCompletion$default(this, false, new ResumeOnCompletion(`cancellable$iv`), 1, null)
      );
      val var10000: Any = `cancellable$iv`.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   private fun registerSelectForOnJoin(select: SelectInstance<*>, ignoredParam: Any?) {
      if (!this.joinInternal()) {
         select.selectInRegistrationPhase(Unit.INSTANCE);
      } else {
         select.disposeOnCompletion(JobKt.invokeOnCompletion$default(this, false, new JobSupport.SelectOnJoinCompletionHandler(this, select), 1, null));
      }
   }

   internal fun removeNode(node: JobNode) {
      val `this_$iv`: JobSupport = this;

      val state: Any;
      do {
         state = `this_$iv`.getState$kotlinx_coroutines_core();
         if (state !is JobNode) {
            if (state is Incomplete) {
               if ((state as Incomplete).getList() != null) {
                  node.remove();
               }

               return;
            }

            return;
         }

         if (state != node) {
            return;
         }
      } while (!get_state$volatile$FU().compareAndSet(this, state, JobSupportKt.access$getEMPTY_ACTIVE$p()));
   }

   public override fun cancel(cause: CancellationException?) {
      var var10001: CancellationException = cause;
      if (cause == null) {
         var10001 = new JobCancellationException(access$cancellationExceptionMessage(this), null, this);
      }

      this.cancelInternal(var10001);
   }

   protected open fun cancellationExceptionMessage(): String {
      return "Job was cancelled";
   }

   public open fun cancelInternal(cause: Throwable) {
      this.cancelImpl$kotlinx_coroutines_core(cause);
   }

   public override fun parentCancelled(parentJob: ParentJob) {
      this.cancelImpl$kotlinx_coroutines_core(parentJob);
   }

   public open fun childCancelled(cause: Throwable): Boolean {
      if (cause is CancellationException) {
         return true;
      } else {
         return this.cancelImpl$kotlinx_coroutines_core(cause) && this.getHandlesException$kotlinx_coroutines_core();
      }
   }

   public fun cancelCoroutine(cause: Throwable?): Boolean {
      return this.cancelImpl$kotlinx_coroutines_core(cause);
   }

   internal fun cancelImpl(cause: Any?): Boolean {
      var finalState: Any = JobSupportKt.access$getCOMPLETING_ALREADY$p();
      if (this.getOnCancelComplete$kotlinx_coroutines_core()) {
         finalState = this.cancelMakeCompleting(cause);
         if (finalState === JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
         }
      }

      if (finalState === JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
         finalState = this.makeCancelling(cause);
      }

      val var10000: Boolean;
      if (finalState === JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
         var10000 = true;
      } else if (finalState === JobSupportKt.COMPLETING_WAITING_CHILDREN) {
         var10000 = true;
      } else if (finalState === JobSupportKt.access$getTOO_LATE_TO_CANCEL$p()) {
         var10000 = false;
      } else {
         this.afterCompletion(finalState);
         var10000 = true;
      }

      return var10000;
   }

   private fun cancelMakeCompleting(cause: Any?): Any? {
      val `this_$iv`: JobSupport = this;

      val finalState: Any;
      do {
         val state: Any = `this_$iv`.getState$kotlinx_coroutines_core();
         if (state !is Incomplete || state is JobSupport.Finishing && (state as JobSupport.Finishing).isCompleting()) {
            return JobSupportKt.access$getCOMPLETING_ALREADY$p();
         }

         finalState = this.tryMakeCompleting(state, new CompletedExceptionally(this.createCauseException(cause), false, 2, null));
      } while (finalState == JobSupportKt.access$getCOMPLETING_RETRY$p());

      return finalState;
   }

   internal inline fun defaultCancellationException(message: String? = ..., cause: Throwable? = ...): JobCancellationException {
      val var10000: JobCancellationException = new JobCancellationException;
      var var10002: java.lang.String = message;
      if (message == null) {
         var10002 = access$cancellationExceptionMessage(this);
      }

      var10000./* $VF: Unable to resugar constructor */<init>(var10002, cause, this);
      return var10000;
   }

   public override fun getChildJobCancellationCause(): CancellationException {
      val state: Any = this.getState$kotlinx_coroutines_core();
      val var10000: java.lang.Throwable;
      if (state is JobSupport.Finishing) {
         var10000 = (state as JobSupport.Finishing).getRootCause();
      } else if (state is CompletedExceptionally) {
         var10000 = (state as CompletedExceptionally).cause;
      } else {
         if (state is Incomplete) {
            throw new IllegalStateException(("Cannot be cancelling child in this state: $state").toString());
         }

         var10000 = null;
      }

      var var4: CancellationException = var10000 as? CancellationException;
      if ((var10000 as? CancellationException) == null) {
         var4 = new JobCancellationException("Parent job is ${this.stateString(state)}", var10000, this);
      }

      return var4;
   }

   private fun createCauseException(cause: Any?): Throwable {
      var var10000: java.lang.Throwable;
      if (cause == null || cause is java.lang.Throwable) {
         var10000 = cause as java.lang.Throwable;
         if (cause as java.lang.Throwable == null) {
            var10000 = new JobCancellationException(access$cancellationExceptionMessage(this), null, this);
         }
      } else {
         var10000 = (cause as ParentJob).getChildJobCancellationCause();
      }

      return var10000;
   }

   private fun makeCancelling(cause: Any?): Any? {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:305)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
      //
      // Bytecode:
      // 000: aconst_null
      // 001: astore 2
      // 002: aload 0
      // 003: astore 3
      // 004: bipush 0
      // 005: istore 4
      // 007: nop
      // 008: aload 3
      // 009: invokevirtual kotlinx/coroutines/JobSupport.getState$kotlinx_coroutines_core ()Ljava/lang/Object;
      // 00c: astore 5
      // 00e: bipush 0
      // 00f: istore 6
      // 011: aload 5
      // 013: astore 7
      // 015: aload 7
      // 017: instanceof kotlinx/coroutines/JobSupport$Finishing
      // 01a: ifeq 0dd
      // 01d: aload 5
      // 01f: astore 8
      // 021: bipush 0
      // 022: istore 9
      // 024: bipush 0
      // 025: istore 10
      // 027: aload 8
      // 029: astore 11
      // 02b: aload 11
      // 02d: monitorenter
      // 02e: nop
      // 02f: bipush 0
      // 030: istore 12
      // 032: aload 5
      // 034: checkcast kotlinx/coroutines/JobSupport$Finishing
      // 037: invokevirtual kotlinx/coroutines/JobSupport$Finishing.isSealed ()Z
      // 03a: ifeq 048
      // 03d: invokestatic kotlinx/coroutines/JobSupportKt.access$getTOO_LATE_TO_CANCEL$p ()Lkotlinx/coroutines/internal/Symbol;
      // 040: astore 13
      // 042: aload 11
      // 044: monitorexit
      // 045: aload 13
      // 047: areturn
      // 048: aload 5
      // 04a: checkcast kotlinx/coroutines/JobSupport$Finishing
      // 04d: invokevirtual kotlinx/coroutines/JobSupport$Finishing.isCancelling ()Z
      // 050: istore 14
      // 052: aload 1
      // 053: ifnonnull 05b
      // 056: iload 14
      // 058: ifne 080
      // 05b: aload 2
      // 05c: dup
      // 05d: ifnonnull 074
      // 060: pop
      // 061: aload 0
      // 062: aload 1
      // 063: invokespecial kotlinx/coroutines/JobSupport.createCauseException (Ljava/lang/Object;)Ljava/lang/Throwable;
      // 066: astore 15
      // 068: aload 15
      // 06a: astore 16
      // 06c: bipush 0
      // 06d: istore 17
      // 06f: aload 16
      // 071: astore 2
      // 072: aload 15
      // 074: astore 18
      // 076: aload 5
      // 078: checkcast kotlinx/coroutines/JobSupport$Finishing
      // 07b: aload 18
      // 07d: invokevirtual kotlinx/coroutines/JobSupport$Finishing.addExceptionLocked (Ljava/lang/Throwable;)V
      // 080: aload 5
      // 082: checkcast kotlinx/coroutines/JobSupport$Finishing
      // 085: invokevirtual kotlinx/coroutines/JobSupport$Finishing.getRootCause ()Ljava/lang/Throwable;
      // 088: astore 18
      // 08a: aload 18
      // 08c: astore 19
      // 08e: bipush 0
      // 08f: istore 15
      // 091: iload 14
      // 093: ifne 09a
      // 096: bipush 1
      // 097: goto 09b
      // 09a: bipush 0
      // 09b: ifeq 0a3
      // 09e: aload 18
      // 0a0: goto 0a4
      // 0a3: aconst_null
      // 0a4: nop
      // 0a5: astore 20
      // 0a7: aload 11
      // 0a9: monitorexit
      // 0aa: aload 20
      // 0ac: goto 0b7
      // 0af: astore 21
      // 0b1: aload 11
      // 0b3: monitorexit
      // 0b4: aload 21
      // 0b6: athrow
      // 0b7: nop
      // 0b8: nop
      // 0b9: astore 22
      // 0bb: aload 22
      // 0bd: dup
      // 0be: ifnull 0d7
      // 0c1: astore 9
      // 0c3: bipush 0
      // 0c4: istore 10
      // 0c6: aload 0
      // 0c7: aload 5
      // 0c9: checkcast kotlinx/coroutines/JobSupport$Finishing
      // 0cc: invokevirtual kotlinx/coroutines/JobSupport$Finishing.getList ()Lkotlinx/coroutines/NodeList;
      // 0cf: aload 9
      // 0d1: invokespecial kotlinx/coroutines/JobSupport.notifyCancelling (Lkotlinx/coroutines/NodeList;Ljava/lang/Throwable;)V
      // 0d4: goto 0d9
      // 0d7: pop
      // 0d8: nop
      // 0d9: invokestatic kotlinx/coroutines/JobSupportKt.access$getCOMPLETING_ALREADY$p ()Lkotlinx/coroutines/internal/Symbol;
      // 0dc: areturn
      // 0dd: aload 7
      // 0df: instanceof kotlinx/coroutines/Incomplete
      // 0e2: ifeq 167
      // 0e5: aload 2
      // 0e6: dup
      // 0e7: ifnonnull 0fe
      // 0ea: pop
      // 0eb: aload 0
      // 0ec: aload 1
      // 0ed: invokespecial kotlinx/coroutines/JobSupport.createCauseException (Ljava/lang/Object;)Ljava/lang/Throwable;
      // 0f0: astore 9
      // 0f2: aload 9
      // 0f4: astore 10
      // 0f6: bipush 0
      // 0f7: istore 11
      // 0f9: aload 10
      // 0fb: astore 2
      // 0fc: aload 9
      // 0fe: astore 22
      // 100: aload 5
      // 102: checkcast kotlinx/coroutines/Incomplete
      // 105: invokeinterface kotlinx/coroutines/Incomplete.isActive ()Z 1
      // 10a: ifeq 11f
      // 10d: aload 0
      // 10e: aload 5
      // 110: checkcast kotlinx/coroutines/Incomplete
      // 113: aload 22
      // 115: invokespecial kotlinx/coroutines/JobSupport.tryMakeCancelling (Lkotlinx/coroutines/Incomplete;Ljava/lang/Throwable;)Z
      // 118: ifeq 16b
      // 11b: invokestatic kotlinx/coroutines/JobSupportKt.access$getCOMPLETING_ALREADY$p ()Lkotlinx/coroutines/internal/Symbol;
      // 11e: areturn
      // 11f: aload 0
      // 120: aload 5
      // 122: new kotlinx/coroutines/CompletedExceptionally
      // 125: dup
      // 126: aload 22
      // 128: bipush 0
      // 129: bipush 2
      // 12a: aconst_null
      // 12b: invokespecial kotlinx/coroutines/CompletedExceptionally.<init> (Ljava/lang/Throwable;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V
      // 12e: invokespecial kotlinx/coroutines/JobSupport.tryMakeCompleting (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      // 131: astore 8
      // 133: nop
      // 134: aload 8
      // 136: invokestatic kotlinx/coroutines/JobSupportKt.access$getCOMPLETING_ALREADY$p ()Lkotlinx/coroutines/internal/Symbol;
      // 139: if_acmpne 15c
      // 13c: new java/lang/IllegalStateException
      // 13f: dup
      // 140: new java/lang/StringBuilder
      // 143: dup
      // 144: invokespecial java/lang/StringBuilder.<init> ()V
      // 147: ldc_w "Cannot happen in "
      // 14a: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 14d: aload 5
      // 14f: invokevirtual java/lang/StringBuilder.append (Ljava/lang/Object;)Ljava/lang/StringBuilder;
      // 152: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 155: invokevirtual java/lang/Object.toString ()Ljava/lang/String;
      // 158: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 15b: athrow
      // 15c: aload 8
      // 15e: invokestatic kotlinx/coroutines/JobSupportKt.access$getCOMPLETING_RETRY$p ()Lkotlinx/coroutines/internal/Symbol;
      // 161: if_acmpeq 16c
      // 164: aload 8
      // 166: areturn
      // 167: invokestatic kotlinx/coroutines/JobSupportKt.access$getTOO_LATE_TO_CANCEL$p ()Lkotlinx/coroutines/internal/Symbol;
      // 16a: areturn
      // 16b: nop
      // 16c: goto 007
   }

   private fun getOrPromoteCancellingList(state: Incomplete): NodeList? {
      var var10000: NodeList = state.getList();
      if (var10000 == null) {
         if (state is Empty) {
            var10000 = new NodeList();
         } else {
            if (state !is JobNode) {
               throw new IllegalStateException(("State should have list: $state").toString());
            }

            this.promoteSingleToNodeList(state as JobNode);
            var10000 = null;
         }
      }

      return var10000;
   }

   private fun tryMakeCancelling(state: Incomplete, rootCause: Throwable): Boolean {
      if (DebugKt.getASSERTIONS_ENABLED() && state is JobSupport.Finishing) {
         throw new AssertionError();
      } else if (DebugKt.getASSERTIONS_ENABLED() && !state.isActive()) {
         throw new AssertionError();
      } else {
         val var10000: NodeList = this.getOrPromoteCancellingList(state);
         if (var10000 == null) {
            return false;
         } else if (!get_state$volatile$FU().compareAndSet(this, state, new JobSupport.Finishing(var10000, false, rootCause))) {
            return false;
         } else {
            this.notifyCancelling(var10000, rootCause);
            return true;
         }
      }
   }

   internal fun makeCompleting(proposedUpdate: Any?): Boolean {
      val `this_$iv`: JobSupport = this;

      val finalState: Any;
      do {
         finalState = this.tryMakeCompleting(`this_$iv`.getState$kotlinx_coroutines_core(), proposedUpdate);
         if (finalState === JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            return false;
         }

         if (finalState === JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
         }
      } while (finalState == JobSupportKt.access$getCOMPLETING_RETRY$p());

      this.afterCompletion(finalState);
      return true;
   }

   internal fun makeCompletingOnce(proposedUpdate: Any?): Any? {
      val `this_$iv`: JobSupport = this;

      val finalState: Any;
      do {
         finalState = this.tryMakeCompleting(`this_$iv`.getState$kotlinx_coroutines_core(), proposedUpdate);
         if (finalState === JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
            throw new IllegalStateException(
               "Job $this is already complete or completing, but is being completed with $proposedUpdate", this.getExceptionOrNull(proposedUpdate)
            );
         }
      } while (finalState == JobSupportKt.access$getCOMPLETING_RETRY$p());

      return finalState;
   }

   private fun tryMakeCompleting(state: Any?, proposedUpdate: Any?): Any? {
      if (state !is Incomplete) {
         return JobSupportKt.access$getCOMPLETING_ALREADY$p();
      } else if ((state is Empty || state is JobNode) && state !is ChildHandleNode && proposedUpdate !is CompletedExceptionally) {
         return if (this.tryFinalizeSimpleState(state as Incomplete, proposedUpdate)) proposedUpdate else JobSupportKt.access$getCOMPLETING_RETRY$p();
      } else {
         return this.tryMakeCompletingSlowPath(state as Incomplete, proposedUpdate);
      }
   }

   private fun tryMakeCompletingSlowPath(state: Incomplete, proposedUpdate: Any?): Any? {
      val var10000: NodeList = this.getOrPromoteCancellingList(state);
      if (var10000 == null) {
         return JobSupportKt.access$getCOMPLETING_RETRY$p();
      } else {
         var var26: JobSupport.Finishing = state as? JobSupport.Finishing;
         if ((state as? JobSupport.Finishing) == null) {
            var26 = new JobSupport.Finishing(var10000, false, null);
         }

         var var19: Symbol;
         label100: {
            val finishing: JobSupport.Finishing = var26;
            val notifyRootCause: Ref.ObjectRef = new Ref.ObjectRef();
            synchronized (var26) {
               if (finishing.isCompleting()) {
                  var19 = JobSupportKt.access$getCOMPLETING_ALREADY$p();
                  break label100;
               }

               finishing.setCompleting(true);
               if (finishing != state && !get_state$volatile$FU().compareAndSet(this, state, finishing)) {
                  return JobSupportKt.access$getCOMPLETING_RETRY$p();
               }

               if (DebugKt.getASSERTIONS_ENABLED() && finishing.isSealed()) {
                  throw new AssertionError();
               }

               val var24: Boolean = finishing.isCancelling();
               val var27: CompletedExceptionally = proposedUpdate as? CompletedExceptionally;
               if ((proposedUpdate as? CompletedExceptionally) != null) {
                  finishing.addExceptionLocked(var27.cause);
               }

               notifyRootCause.element = (T)(if (java.lang.Boolean.valueOf(!var24)) finishing.getRootCause() else null);
            }

            val var28: java.lang.Throwable = notifyRootCause.element as java.lang.Throwable;
            if (notifyRootCause.element as java.lang.Throwable != null) {
               this.notifyCancelling(var10000, var28);
            }

            val var21: ChildHandleNode = this.nextChild(var10000);
            if (var21 != null && this.tryWaitForChild(var26, var21, proposedUpdate)) {
               return JobSupportKt.COMPLETING_WAITING_CHILDREN;
            }

            var10000.close(2);
            val var22: ChildHandleNode = this.nextChild(var10000);
            if (var22 != null && this.tryWaitForChild(var26, var22, proposedUpdate)) {
               return JobSupportKt.COMPLETING_WAITING_CHILDREN;
            }

            return this.finalizeFinishingState(var26, proposedUpdate);
         }

         // $VF: monitorexit
         return var19;
      }
   }

   private tailrec fun tryWaitForChild(state: kotlinx.coroutines.JobSupport.Finishing, child: ChildHandleNode, proposedUpdate: Any?): Boolean {
      var var4: JobSupport = this;

      while (true) {
         if (JobKt.invokeOnCompletion(child.childJob, false, new JobSupport.ChildCompletion(var4, state, child, proposedUpdate))
            != NonDisposableHandle.INSTANCE) {
            return true;
         }

         val var10000: ChildHandleNode = var4.nextChild(child);
         if (var10000 == null) {
            return false;
         }

         var4 = var4;
         state = state;
         child = var10000;
         proposedUpdate = proposedUpdate;
      }
   }

   private fun continueCompleting(state: kotlinx.coroutines.JobSupport.Finishing, lastChild: ChildHandleNode, proposedUpdate: Any?) {
      if (DebugKt.getASSERTIONS_ENABLED() && this.getState$kotlinx_coroutines_core() != state) {
         throw new AssertionError();
      } else {
         val var7: ChildHandleNode = this.nextChild(lastChild);
         if (var7 == null || !this.tryWaitForChild(state, var7, proposedUpdate)) {
            state.getList().close(2);
            val waitChildAgain: ChildHandleNode = this.nextChild(lastChild);
            if (waitChildAgain == null || !this.tryWaitForChild(state, waitChildAgain, proposedUpdate)) {
               this.afterCompletion(this.finalizeFinishingState(state, proposedUpdate));
            }
         }
      }
   }

   private fun LockFreeLinkedListNode.nextChild(): ChildHandleNode? {
      var cur: LockFreeLinkedListNode = `$this$nextChild`;

      while (cur.isRemoved()) {
         cur = cur.getPrevNode();
      }

      while (true) {
         cur = cur.getNextNode();
         if (!cur.isRemoved()) {
            if (cur is ChildHandleNode) {
               return cur as ChildHandleNode;
            }

            if (cur is NodeList) {
               return null;
            }
         }
      }
   }

   public override fun attachChild(child: ChildJob): ChildHandle {
      val added: ChildHandleNode = new ChildHandleNode(child);
      added.setJob(this);
      val node: ChildHandleNode = added;
      val `this_$iv`: JobSupport = this;
      val `this_$iv$iv`: JobSupport = this;

      var var10000: Boolean;
      while (true) {
         val `state$iv`: Any = `this_$iv$iv`.getState$kotlinx_coroutines_core();
         if (`state$iv` is Empty) {
            if ((`state$iv` as Empty).isActive()) {
               if (get_state$volatile$FU().compareAndSet(`this_$iv`, `state$iv`, node)) {
                  var10000 = true;
                  break;
               }
            } else {
               `this_$iv`.promoteEmptyToNodeList(`state$iv` as Empty);
            }
         } else {
            if (`state$iv` !is Incomplete) {
               var10000 = false;
               break;
            }

            val `list$iv`: NodeList = (`state$iv` as Incomplete).getList();
            if (`list$iv` == null) {
               `this_$iv`.promoteSingleToNodeList(`state$iv` as JobNode);
            } else {
               if (`list$iv`.addLast(node, 7)) {
                  var10000 = true;
               } else {
                  val addedBeforeCompletion: Boolean = `list$iv`.addLast(node, 3);
                  val var16: Any = this.getState$kotlinx_coroutines_core();
                  val var24: java.lang.Throwable;
                  if (var16 is JobSupport.Finishing) {
                     var24 = (var16 as JobSupport.Finishing).getRootCause();
                  } else {
                     if (DebugKt.getASSERTIONS_ENABLED() && var16 is Incomplete) {
                        throw new AssertionError();
                     }

                     var24 = if ((var16 as? CompletedExceptionally) != null) (var16 as? CompletedExceptionally).cause else null;
                  }

                  node.invoke(var24);
                  if (!addedBeforeCompletion) {
                     return NonDisposableHandle.INSTANCE;
                  }

                  if (DebugKt.getASSERTIONS_ENABLED() && var24 == null) {
                     throw new AssertionError();
                  }

                  var10000 = true;
               }

               if (var10000) {
                  var10000 = true;
                  break;
               }
            }
         }
      }

      if (var10000) {
         return node;
      } else {
         val var21: Any = this.getState$kotlinx_coroutines_core();
         node.invoke(if ((var21 as? CompletedExceptionally) != null) (var21 as? CompletedExceptionally).cause else null);
         return NonDisposableHandle.INSTANCE;
      }
   }

   internal open fun handleOnCompletionException(exception: Throwable) {
      throw exception;
   }

   protected open fun onCancelling(cause: Throwable?) {
   }

   protected open fun handleJobException(exception: Throwable): Boolean {
      return false;
   }

   protected open fun onCompletionInternal(state: Any?) {
   }

   protected open fun afterCompletion(state: Any?) {
   }

   public override fun toString(): String {
      return "${this.toDebugString()}@${DebugStringsKt.getHexAddress(this)}";
   }

   @InternalCoroutinesApi
   public fun toDebugString(): String {
      return "${this.nameString$kotlinx_coroutines_core()}{${this.stateString(this.getState$kotlinx_coroutines_core())}}";
   }

   internal open fun nameString(): String {
      return DebugStringsKt.getClassSimpleName(this);
   }

   private fun stateString(state: Any?): String {
      return if (state is JobSupport.Finishing)
         (
            if ((state as JobSupport.Finishing).isCancelling())
               "Cancelling"
               else
               (if ((state as JobSupport.Finishing).isCompleting()) "Completing" else "Active")
         )
         else
         (
            if (state is Incomplete)
               (if ((state as Incomplete).isActive()) "Active" else "New")
               else
               (if (state is CompletedExceptionally) "Cancelled" else "Completed")
         );
   }

   public fun getCompletionExceptionOrNull(): Throwable? {
      val state: Any = this.getState$kotlinx_coroutines_core();
      if (state is Incomplete) {
         throw new IllegalStateException("This job has not completed yet".toString());
      } else {
         return this.getExceptionOrNull(state);
      }
   }

   internal fun getCompletedInternal(): Any? {
      val state: Any = this.getState$kotlinx_coroutines_core();
      if (state is Incomplete) {
         throw new IllegalStateException("This job has not completed yet".toString());
      } else if (state is CompletedExceptionally) {
         throw (state as CompletedExceptionally).cause;
      } else {
         return JobSupportKt.unboxState(state);
      }
   }

   protected suspend fun awaitInternal(): Any? {
      val state: Any;
      do {
         state = this.getState$kotlinx_coroutines_core();
         if (state !is Incomplete) {
            if (state is CompletedExceptionally) {
               val `exception$iv`: java.lang.Throwable = (state as CompletedExceptionally).cause;
               if (!DebugKt.getRECOVER_STACK_TRACES()) {
                  throw `exception$iv`;
               }

               if (`$completion` !is CoroutineStackFrame) {
                  throw `exception$iv`;
               }

               throw StackTraceRecoveryKt.access$recoverFromStackFrame(`exception$iv`, `$completion` as CoroutineStackFrame);
            }

            return JobSupportKt.unboxState(state);
         }
      } while (this.startInternal(state) < 0);

      return this.awaitSuspend(`$completion`);
   }

   private suspend fun awaitSuspend(): Any? {
      val cont: JobSupport.AwaitContinuation = new JobSupport.AwaitContinuation(IntrinsicsKt.intercepted(`$completion`), this);
      cont.initCancellability();
      CancellableContinuationKt.disposeOnCancellation(cont, JobKt.invokeOnCompletion$default(this, false, new ResumeAwaitOnCompletion(cont), 1, null));
      val var10000: Any = cont.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return var10000;
   }

   private fun onAwaitInternalRegFunc(select: SelectInstance<*>, ignoredParam: Any?) {
      val disposableHandle: Any;
      do {
         disposableHandle = this.getState$kotlinx_coroutines_core();
         if (disposableHandle !is Incomplete) {
            select.selectInRegistrationPhase(if (disposableHandle is CompletedExceptionally) disposableHandle else JobSupportKt.unboxState(disposableHandle));
            return;
         }
      } while (this.startInternal(state) < 0);

      select.disposeOnCompletion(JobKt.invokeOnCompletion$default(this, false, new JobSupport.SelectOnAwaitCompletionHandler(this, select), 1, null));
   }

   private fun onAwaitInternalProcessResFunc(ignoredParam: Any?, result: Any?): Any? {
      if (result is CompletedExceptionally) {
         throw (result as CompletedExceptionally).cause;
      } else {
         return result;
      }
   }

   /** @deprecated */
   @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
   override fun plus(other: Job): Job {
      return Job.DefaultImpls.plus(this, other);
   }

   override fun plus(context: CoroutineContext): CoroutineContext {
      return Job.DefaultImpls.plus(this, context);
   }

   override fun <E extends CoroutineContext.Element> get(key: CoroutineContextKey<E>): E? {
      return Job.DefaultImpls.get(this, key);
   }

   override fun <R> fold(initial: R, operation: (R?, CoroutineContext.Element?) -> R): R {
      return Job.DefaultImpls.fold(this, (R)initial, operation);
   }

   override fun minusKey(key: CoroutineContextKey<?>): CoroutineContext {
      return Job.DefaultImpls.minusKey(this, key);
   }

   @SourceDebugExtension(["SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport$AwaitContinuation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1583:1\n1#2:1584\n*E\n"])
   private class AwaitContinuation<T>(delegate: Continuation<Any>, job: JobSupport) : CancellableContinuationImpl(delegate, 1) {
      private final val job: JobSupport

      init {
         this.job = job;
      }

      public override fun getContinuationCancellationCause(parent: Job): Throwable {
         val state: Any = this.job.getState$kotlinx_coroutines_core();
         if (state is JobSupport.Finishing) {
            val var3: java.lang.Throwable = (state as JobSupport.Finishing).getRootCause();
            if (var3 != null) {
               return var3;
            }
         }

         return if (state is CompletedExceptionally) (state as CompletedExceptionally).cause else parent.getCancellationException();
      }

      protected override fun nameString(): String {
         return "AwaitContinuation";
      }
   }

   private class ChildCompletion(parent: JobSupport, state: kotlinx.coroutines.JobSupport.Finishing, child: ChildHandleNode, proposedUpdate: Any?) : JobNode {
      private final val parent: JobSupport
      private final val state: kotlinx.coroutines.JobSupport.Finishing
      private final val child: ChildHandleNode
      private final val proposedUpdate: Any?

      public open val onCancelling: Boolean
         public open get() {
            return false;
         }


      init {
         this.parent = parent;
         this.state = state;
         this.child = child;
         this.proposedUpdate = proposedUpdate;
      }

      public override fun invoke(cause: Throwable?) {
         JobSupport.access$continueCompleting(this.parent, this.state, this.child, this.proposedUpdate);
      }
   }

   @SourceDebugExtension(["SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport$Finishing\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1583:1\n1#2:1584\n*E\n"])
   private class Finishing(list: NodeList, isCompleting: Boolean, rootCause: Throwable?) : Incomplete {
      public open val list: NodeList
      private final val _isCompleting: AtomicBoolean

      public final var isCompleting: Boolean
         public final get() {
            return get_isCompleting$volatile$FU().get(this) == 1;
         }

         public final set(value) {
            get_isCompleting$volatile$FU().set(this, if (value) 1 else 0);
         }


      private final val _rootCause: AtomicRef<Throwable?>

      public final var rootCause: Throwable?
         public final get() {
            return get_rootCause$volatile$FU().get(this) as java.lang.Throwable;
         }

         public final set(value) {
            get_rootCause$volatile$FU().set(this, value);
         }


      private final val _exceptionsHolder: AtomicRef<Any?>

      private final var exceptionsHolder: Any?
         private final get() {
            return get_exceptionsHolder$volatile$FU().get(this);
         }

         private final set(value) {
            get_exceptionsHolder$volatile$FU().set(this, value);
         }


      public final val isSealed: Boolean
         public final get() {
            return this.getExceptionsHolder() === JobSupportKt.access$getSEALED$p();
         }


      public final val isCancelling: Boolean
         public final get() {
            return this.getRootCause() != null;
         }


      public open val isActive: Boolean
         public open get() {
            return this.getRootCause() == null;
         }


      init {
         this.list = list;
         this._isCompleting$volatile = if (isCompleting) 1 else 0;
         this._rootCause$volatile = rootCause;
      }

      public fun sealLocked(proposedException: Throwable?): List<Throwable> {
         var rootCause: Any = this.getExceptionsHolder();
         val var10000: ArrayList;
         if (rootCause == null) {
            var10000 = this.allocateList();
         } else if (rootCause is java.lang.Throwable) {
            val var4: ArrayList = this.allocateList();
            var4.add(rootCause);
            var10000 = var4;
         } else {
            if (rootCause !is ArrayList) {
               throw new IllegalStateException(("State is $rootCause").toString());
            }

            var10000 = rootCause as ArrayList;
         }

         rootCause = this.getRootCause();
         if (rootCause != null) {
            var10000.add(0, rootCause);
         }

         if (proposedException != null && !(proposedException == rootCause)) {
            var10000.add(proposedException);
         }

         this.setExceptionsHolder(JobSupportKt.access$getSEALED$p());
         return var10000;
      }

      public fun addExceptionLocked(exception: Throwable) {
         val rootCause: java.lang.Throwable = this.getRootCause();
         if (rootCause == null) {
            this.setRootCause(exception);
         } else if (exception != rootCause) {
            val eh: Any = this.getExceptionsHolder();
            if (eh == null) {
               this.setExceptionsHolder(exception);
            } else if (eh is java.lang.Throwable) {
               if (exception === eh) {
                  return;
               }

               val var4: ArrayList = this.allocateList();
               var4.add(eh);
               var4.add(exception);
               this.setExceptionsHolder(var4);
            } else {
               if (eh !is ArrayList) {
                  throw new IllegalStateException(("State is $eh").toString());
               }

               (eh as ArrayList).add(exception);
            }
         }
      }

      private fun allocateList(): ArrayList<Throwable> {
         return new ArrayList<>(4);
      }

      public override fun toString(): String {
         return "Finishing[cancelling=${this.isCancelling()}, completing=${this.isCompleting()}, rootCause=${this.getRootCause()}, exceptions=${this.getExceptionsHolder()}, list=${this.getList()}]";
      }
   }

   private inner class SelectOnAwaitCompletionHandler(select: SelectInstance<*>) : JobNode {
      private final val select: SelectInstance<*>

      public open val onCancelling: Boolean
         public open get() {
            return false;
         }


      init {
         this.this$0 = `this$0`;
         this.select = select;
      }

      public override fun invoke(cause: Throwable?) {
         val state: Any = this.this$0.getState$kotlinx_coroutines_core();
         this.select.trySelect(this.this$0, if (state is CompletedExceptionally) state else JobSupportKt.unboxState(state));
      }
   }

   private inner class SelectOnJoinCompletionHandler(select: SelectInstance<*>) : JobNode {
      private final val select: SelectInstance<*>

      public open val onCancelling: Boolean
         public open get() {
            return false;
         }


      init {
         this.this$0 = `this$0`;
         this.select = select;
      }

      public override fun invoke(cause: Throwable?) {
         this.select.trySelect(this.this$0, Unit.INSTANCE);
      }
   }
}
