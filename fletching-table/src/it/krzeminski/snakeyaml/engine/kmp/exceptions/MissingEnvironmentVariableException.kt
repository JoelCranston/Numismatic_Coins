package it.krzeminski.snakeyaml.engine.kmp.exceptions

public class MissingEnvironmentVariableException(message: String) : YamlEngineException(message) {
   public companion object {
      internal fun forMissingVariable(name: String, value: String): MissingEnvironmentVariableException {
         return new MissingEnvironmentVariableException("Missing mandatory variable $name: $value");
      }

      internal fun forEmptyVariable(name: String, value: String): MissingEnvironmentVariableException {
         return new MissingEnvironmentVariableException("Empty mandatory variable $name: $value");
      }
   }
}
