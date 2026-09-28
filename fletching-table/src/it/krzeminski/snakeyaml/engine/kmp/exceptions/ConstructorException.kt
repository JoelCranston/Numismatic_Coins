package it.krzeminski.snakeyaml.engine.kmp.exceptions

public open class ConstructorException @JvmOverloads  public constructor(context: String?,
   contextMark: Mark?,
   problem: String,
   problemMark: Mark?,
   cause: Throwable? = null
) : MarkedYamlEngineException(context, contextMark, problem, problemMark, cause) {
   @JvmOverloads
   open fun ConstructorException(context: java.lang.String?, contextMark: Mark?, problem: java.lang.String, problemMark: Mark?) {
      this(context, contextMark, problem, problemMark, null, 16, null);
   }
}
