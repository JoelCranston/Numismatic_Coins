package it.krzeminski.snakeyaml.engine.kmp.exceptions

public class ParserException @JvmOverloads  public constructor(problem: String,
   contextMark: Mark?,
   context: String? = null,
   problemMark: Mark? = null,
   cause: Throwable? = null
) : MarkedYamlEngineException(context, contextMark, problem, problemMark, cause) {
   @JvmOverloads
   fun ParserException(problem: java.lang.String, contextMark: Mark?, context: java.lang.String?, problemMark: Mark?) {
      this(problem, contextMark, context, problemMark, null, 16, null);
   }

   @JvmOverloads
   fun ParserException(problem: java.lang.String, contextMark: Mark?, context: java.lang.String?) {
      this(problem, contextMark, context, null, null, 24, null);
   }

   @JvmOverloads
   fun ParserException(problem: java.lang.String, contextMark: Mark?) {
      this(problem, contextMark, null, null, null, 28, null);
   }
}
