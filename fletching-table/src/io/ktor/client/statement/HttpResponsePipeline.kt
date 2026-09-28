package io.ktor.client.statement

import io.ktor.util.pipeline.Pipeline
import io.ktor.util.pipeline.PipelinePhase

public class HttpResponsePipeline(developmentMode: Boolean = true) : Pipeline(Receive, Parse, Transform, State, After) {
   public open val developmentMode: Boolean

   init {
      this.developmentMode = developmentMode;
   }

   fun HttpResponsePipeline() {
      this(false, 1, null);
   }

   public companion object Phases {
      public final val Receive: PipelinePhase
      public final val Parse: PipelinePhase
      public final val Transform: PipelinePhase
      public final val State: PipelinePhase
      public final val After: PipelinePhase
   }
}
