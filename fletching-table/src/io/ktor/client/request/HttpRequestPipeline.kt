package io.ktor.client.request

import io.ktor.util.pipeline.Pipeline
import io.ktor.util.pipeline.PipelinePhase

public class HttpRequestPipeline(developmentMode: Boolean = true) : Pipeline(Before, State, Transform, Render, Send) {
   public open val developmentMode: Boolean

   init {
      this.developmentMode = developmentMode;
   }

   fun HttpRequestPipeline() {
      this(false, 1, null);
   }

   public companion object Phases {
      public final val Before: PipelinePhase
      public final val State: PipelinePhase
      public final val Transform: PipelinePhase
      public final val Render: PipelinePhase
      public final val Send: PipelinePhase
   }
}
