package kotlinx.coroutines

import java.util.concurrent.CancellationException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.JobKt__JobKt.invokeOnCompletion.1

@SourceDebugExtension(["SMAP\nJob.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Job.kt\nkotlinx/coroutines/JobKt__JobKt\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,692:1\n1317#2,2:693\n1317#2,2:695\n1317#2,2:697\n1317#2,2:699\n*S KotlinDebug\n*F\n+ 1 Job.kt\nkotlinx/coroutines/JobKt__JobKt\n*L\n520#1:693,2\n534#1:695,2\n628#1:697,2\n652#1:699,2\n*E\n"])
@JvmSynthetic
internal class JobKt__JobKt {
   public final val isActive: Boolean
      public final get() {
         val var10000: Job = `$this$isActive`.get(Job.Key);
         return var10000 == null || var10000.isActive();
      }


   public final val job: Job
      public final get() {
         val var10000: Job = `$this$job`.get(Job.Key);
         if (var10000 == null) {
            throw new IllegalStateException(("Current context doesn't contain Job in it: $`$this$job`").toString());
         } else {
            return var10000;
         }
      }


   @JvmStatic
   internal fun Job.invokeOnCompletion(invokeImmediately: Boolean = true, handler: JobNode): DisposableHandle {
      return if (`$this$invokeOnCompletion` is JobSupport)
         (`$this$invokeOnCompletion` as JobSupport).invokeOnCompletionInternal$kotlinx_coroutines_core(invokeImmediately, handler)
         else
         `$this$invokeOnCompletion`.invokeOnCompletion(handler.getOnCancelling(), invokeImmediately, new 1(handler));
   }

   @JvmStatic
   public fun Job(parent: Job? = null): CompletableJob {
      return new JobImpl(parent);
   }

   @JvmStatic
   internal fun Job.disposeOnCompletion(handle: DisposableHandle): DisposableHandle {
      return JobKt.invokeOnCompletion$default(`$this$disposeOnCompletion`, false, new DisposeOnCompletion(handle), 1, null);
   }

   @JvmStatic
   public suspend fun Job.cancelAndJoin() {
      Job.DefaultImpls.cancel$default(`$this$cancelAndJoin`, null, 1, null);
      val var10000: Any = `$this$cancelAndJoin`.join(`$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   @JvmStatic
   public fun Job.cancelChildren(cause: CancellationException? = null) {
      val `$this$forEach$iv`: Sequence;
      for (Object element$iv : $this$forEach$iv) {
         (`element$iv` as Job).cancel(cause);
      }
   }

   @JvmStatic
   public fun CoroutineContext.cancel(cause: CancellationException? = null) {
      val var10000: Job = `$this$cancel`.get(Job.Key);
      if (var10000 != null) {
         var10000.cancel(cause);
      }
   }

   @JvmStatic
   public fun Job.ensureActive() {
      if (!`$this$ensureActive`.isActive()) {
         throw `$this$ensureActive`.getCancellationException();
      }
   }

   @JvmStatic
   public fun CoroutineContext.ensureActive() {
      val var10000: Job = `$this$ensureActive`.get(Job.Key);
      if (var10000 != null) {
         JobKt.ensureActive(var10000);
      }
   }

   @JvmStatic
   public fun Job.cancel(message: String, cause: Throwable? = null) {
      `$this$cancel`.cancel(ExceptionsKt.CancellationException(message, cause));
   }

   @JvmStatic
   public fun CoroutineContext.cancelChildren(cause: CancellationException? = null) {
      val var10000: Job = `$this$cancelChildren`.get(Job.Key);
      if (var10000 != null && var10000.getChildren() != null) {
         val `$this$forEach$iv`: Sequence;
         for (Object element$iv : $this$forEach$iv) {
            (`element$iv` as Job).cancel(cause);
         }
      }
   }

   @JvmStatic
   private fun Throwable?.orCancellation(job: Job): Throwable {
      var var10000: java.lang.Throwable = `$this$orCancellation`;
      if (`$this$orCancellation` == null) {
         var10000 = new JobCancellationException("Job was cancelled", null, job);
      }

      return var10000;
   }
}
