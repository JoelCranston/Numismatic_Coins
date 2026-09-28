package it.krzeminski.snakeyaml.engine.kmp.exceptions

public class DuplicateKeyException(contextMark: Mark?, key: Any, problemMark: Mark?) : ConstructorException(
      "while constructing a mapping", contextMark, "found duplicate key $key", problemMark, null, 16
   )
