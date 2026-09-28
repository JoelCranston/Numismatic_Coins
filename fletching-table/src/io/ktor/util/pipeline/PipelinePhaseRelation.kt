package io.ktor.util.pipeline

internal sealed class PipelinePhaseRelation protected constructor() {
   public class After(relativeTo: PipelinePhase) : PipelinePhaseRelation() {
      public final val relativeTo: PipelinePhase

      init {
         this.relativeTo = relativeTo;
      }
   }

   public class Before(relativeTo: PipelinePhase) : PipelinePhaseRelation() {
      public final val relativeTo: PipelinePhase

      init {
         this.relativeTo = relativeTo;
      }
   }

   public data object Last : PipelinePhaseRelation() {
      public override fun toString(): String {
         return "Last";
      }

      public override fun hashCode(): Int {
         return 967869129;
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else {
            return other is PipelinePhaseRelation.Last;
         }
      }
   }
}
