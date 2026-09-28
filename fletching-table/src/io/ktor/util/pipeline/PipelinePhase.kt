package io.ktor.util.pipeline

public class PipelinePhase(name: String) {
   public final val name: String

   init {
      this.name = name;
   }

   public override fun toString(): String {
      return "Phase('${this.name}')";
   }
}
