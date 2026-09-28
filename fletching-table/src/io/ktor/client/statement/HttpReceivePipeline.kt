package io.ktor.client.statement

import io.ktor.util.pipeline.Pipeline
import io.ktor.util.pipeline.PipelinePhase

public class HttpReceivePipeline(developmentMode: Boolean = true) : Pipeline(Before, State, After) {
   public open val developmentMode: Boolean

   init {
      this.developmentMode = developmentMode;
   }

   fun HttpReceivePipeline() {
      this(false, 1, null);
   }

   public companion object Phases {
      public final val Before: PipelinePhase
      public final val State: PipelinePhase
      public final val After: PipelinePhase
   }
}
