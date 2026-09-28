package io.ktor.client.engine

import io.ktor.util.CoroutinesUtilsKt
import io.ktor.utils.io.IODispatcher_jvmKt
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job

public abstract class HttpClientEngineBase : HttpClientEngine {
   private final val engineName: String

   public open val dispatcher: CoroutineDispatcher
      public open get() {
         return this.dispatcher$delegate.getValue() as CoroutineDispatcher;
      }


   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.coroutineContext$delegate.getValue() as CoroutineContext;
      }


   open fun HttpClientEngineBase(engineName: java.lang.String) {
      this.engineName = engineName;
      this.closed = 0;
      this.dispatcher$delegate = LazyKt.lazy(HttpClientEngineBase::dispatcher_delegate$lambda$0);
      this.coroutineContext$delegate = LazyKt.lazy(HttpClientEngineBase::coroutineContext_delegate$lambda$0);
   }

   public override fun close() {
      if (closed$FU.compareAndSet(this, 0, 1)) {
         val var2: CoroutineContext.Element = this.getCoroutineContext().get(Job.Key);
         val var10000: CompletableJob = var2 as? CompletableJob;
         if ((var2 as? CompletableJob) != null) {
            var10000.complete();
         }
      }
   }

   @JvmStatic
   fun `dispatcher_delegate$lambda$0`(`this$0`: HttpClientEngineBase): CoroutineDispatcher {
      var var10000: CoroutineDispatcher = `this$0`.getConfig().getDispatcher();
      if (var10000 == null) {
         var10000 = IODispatcher_jvmKt.ioDispatcher();
      }

      return var10000;
   }

   @JvmStatic
   fun `coroutineContext_delegate$lambda$0`(`this$0`: HttpClientEngineBase): CoroutineContext {
      return CoroutinesUtilsKt.SilentSupervisor$default(null, 1, null).plus(`this$0`.getDispatcher()).plus(new CoroutineName("${`this$0`.engineName}-context"));
   }
}
