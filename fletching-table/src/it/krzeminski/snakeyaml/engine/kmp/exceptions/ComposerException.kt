package it.krzeminski.snakeyaml.engine.kmp.exceptions

public class ComposerException @JvmOverloads  public constructor(problem: String, problemMark: Mark?, context: String = "", contextMark: Mark? = null) : MarkedYamlEngineException(
      context, contextMark, problem, problemMark, null, 16
   ) {
   @JvmOverloads
   fun ComposerException(problem: java.lang.String, problemMark: Mark?, context: java.lang.String) {
      this(problem, problemMark, context, null, 8, null);
   }

   @JvmOverloads
   fun ComposerException(problem: java.lang.String, problemMark: Mark?) {
      this(problem, problemMark, null, null, 12, null);
   }
}
