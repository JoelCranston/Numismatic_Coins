package it.krzeminski.snakeyaml.engine.kmp.exceptions

public class ScannerException @JvmOverloads  public constructor(problem: String,
   problemMark: Mark?,
   context: String? = null,
   contextMark: Mark? = null,
   cause: Throwable? = null
) : MarkedYamlEngineException(context, contextMark, problem, problemMark, cause) {
   @JvmOverloads
   fun ScannerException(problem: java.lang.String, problemMark: Mark?, context: java.lang.String?, contextMark: Mark?) {
      this(problem, problemMark, context, contextMark, null, 16, null);
   }

   @JvmOverloads
   fun ScannerException(problem: java.lang.String, problemMark: Mark?, context: java.lang.String?) {
      this(problem, problemMark, context, null, null, 24, null);
   }

   @JvmOverloads
   fun ScannerException(problem: java.lang.String, problemMark: Mark?) {
      this(problem, problemMark, null, null, null, 28, null);
   }
}
