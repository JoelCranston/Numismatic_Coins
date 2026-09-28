package io.ktor.client.request

import io.ktor.util.pipeline.Pipeline
import io.ktor.util.pipeline.PipelinePhase

public class HttpSendPipeline(developmentMode: Boolean = true) : Pipeline(Before, State, Monitoring, Engine, Receive) {
   public open val developmentMode: Boolean

   init {
      this.developmentMode = developmentMode;
   }

   fun HttpSendPipeline() {
      this(false, 1, null);
   }

   public companion object Phases {
      public final val Before: PipelinePhase
      public final val State: PipelinePhase
      public final val Monitoring: PipelinePhase
      public final val Engine: PipelinePhase
      public final val Receive: PipelinePhase
   }
}
