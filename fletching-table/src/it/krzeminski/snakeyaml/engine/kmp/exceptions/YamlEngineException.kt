package it.krzeminski.snakeyaml.engine.kmp.exceptions

public open class YamlEngineException : RuntimeException {
   public constructor(message: String) : super(message)
   public constructor(cause: Throwable) : super(cause)
   public constructor(message: String, cause: Throwable?) : super(message, cause)}
