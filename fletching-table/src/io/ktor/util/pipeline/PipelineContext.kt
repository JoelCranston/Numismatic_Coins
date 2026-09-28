package io.ktor.util.pipeline

import io.ktor.utils.io.KtorDsl
import kotlinx.coroutines.CoroutineScope

@KtorDsl
public abstract class PipelineContext<TSubject, TContext> : CoroutineScope {
   public final val context: Any
   public abstract var subject: Any

   open fun PipelineContext(context: TContext) {
      this.context = (TContext)context;
   }

   public abstract fun finish() {
   }

   public abstract suspend fun proceedWith(subject: Any): Any {
   }

   public abstract suspend fun proceed(): Any {
   }

   internal abstract suspend fun execute(initial: Any): Any {
   }
}
