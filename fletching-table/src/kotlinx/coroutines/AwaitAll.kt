package kotlinx.coroutines

import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.AtomicRef

@SourceDebugExtension(["SMAP\nAwait.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,121:1\n426#2,9:122\n435#2,2:133\n13402#3,2:131\n*S KotlinDebug\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll\n*L\n63#1:122,9\n63#1:133,2\n75#1:131,2\n*E\n"])
private class AwaitAll<T>(vararg deferreds: Any) {
   private final val deferreds: Array<out Deferred<Any>>
   private final val notCompletedCount: AtomicInt

   init {
      this.deferreds = deferreds;
      this.notCompletedCount$volatile = this.deferreds.length;
   }

   public suspend fun await(): List<Any> {
      val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
      `cancellable$iv`.initCancellability();
      val cont: CancellableContinuation = `cancellable$iv`;
      var disposer: Int = 0;
      val `$this$forEach$iv`: Int = access$getDeferreds$p(this).length;

      val `$i$f$forEach`: Array<AwaitAll.AwaitAllNode>;
      for ($i$f$forEach = new AwaitAll.AwaitAllNode[$this$forEach$iv]; disposer < $this$forEach$iv; disposer++) {
         val deferred: Deferred = access$getDeferreds$p(this)[disposer];
         deferred.start();
         val `element$iv`: AwaitAll.AwaitAllNode = new AwaitAll.AwaitAllNode(this, cont);
         `element$iv`.setHandle(JobKt.invokeOnCompletion$default(deferred, false, `element$iv`, 1, null));
         `$i$f$forEach`[disposer] = `element$iv`;
      }

      val var20: AwaitAll.DisposeHandlersOnCancel = new AwaitAll.DisposeHandlersOnCancel(this, `$i$f$forEach`);

      for (Object element$iv : $i$f$forEach) {
         ((AwaitAll.AwaitAllNode)var24).setDisposer(var20);
      }

      if (cont.isCompleted()) {
         var20.disposeAll();
      } else {
         CancellableContinuationKt.invokeOnCancellation(cont, var20);
      }

      val var10000: Any = `cancellable$iv`.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return var10000;
   }

   @SourceDebugExtension(["SMAP\nAwait.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll$AwaitAllNode\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,121:1\n11158#2:122\n11493#2,3:123\n*S KotlinDebug\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll$AwaitAllNode\n*L\n115#1:122\n115#1:123,3\n*E\n"])
   private inner class AwaitAllNode(continuation: CancellableContinuation<List<Any>>) : JobNode {
      private final val continuation: CancellableContinuation<List<Any>>

      public final lateinit var handle: DisposableHandle
         internal set

      private final val _disposer: AtomicRef<kotlinx.coroutines.AwaitAll.DisposeHandlersOnCancel?>

      public final var disposer: kotlinx.coroutines.AwaitAll.DisposeHandlersOnCancel?
         public final get() {
            return get_disposer$volatile$FU().get(this) as AwaitAll.DisposeHandlersOnCancel;
         }

         public final set(value) {
            get_disposer$volatile$FU().set(this, value);
         }


      public open val onCancelling: Boolean
         public open get() {
            return false;
         }


      init {
         this.this$0 = `this$0`;
         this.continuation = continuation;
      }

      public override fun invoke(cause: Throwable?) {
         if (cause != null) {
            val token: Any = this.continuation.tryResumeWithException(cause);
            if (token != null) {
               this.continuation.completeResume(token);
               val var10000: AwaitAll.DisposeHandlersOnCancel = this.getDisposer();
               if (var10000 != null) {
                  var10000.disposeAll();
               }
            }
         } else if (AwaitAll.access$getNotCompletedCount$volatile$FU().decrementAndGet(this.this$0) == 0) {
            val var14: Continuation = this.continuation;
            val `$this$map$iv`: Array<Any> = AwaitAll.access$getDeferreds$p(this.this$0);
            val `destination$iv$iv`: java.util.Collection = new ArrayList(`$this$map$iv`.length);

            for (Object item$iv$iv : $this$map$iv) {
               `destination$iv$iv`.add(((Deferred)`item$iv$iv`).getCompleted());
            }

            var14.resumeWith(Result.constructor-impl(`destination$iv$iv` as java.util.List));
         }
      }
   }

   @SourceDebugExtension(["SMAP\nAwait.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,121:1\n13402#2,2:122\n*S KotlinDebug\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel\n*L\n88#1:122,2\n*E\n"])
   private inner class DisposeHandlersOnCancel(vararg nodes: Any) : CancelHandler {
      private final val nodes: Array<kotlinx.coroutines.AwaitAll.AwaitAllNode>

      init {
         this.this$0 = `this$0`;
         this.nodes = nodes;
      }

      public fun disposeAll() {
         val `$this$forEach$iv`: Any;
         for (Object element$iv : $this$forEach$iv) {
            ((AwaitAll.AwaitAllNode)`element$iv`).getHandle().dispose();
         }
      }

      public override fun invoke(cause: Throwable?) {
         this.disposeAll();
      }

      public override fun toString(): String {
         return "DisposeHandlersOnCancel[${this.nodes}]";
      }
   }
}
