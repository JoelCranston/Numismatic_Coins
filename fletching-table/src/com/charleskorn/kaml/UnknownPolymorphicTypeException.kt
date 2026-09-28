package com.charleskorn.kaml

public class UnknownPolymorphicTypeException(typeName: String, validTypeNames: Set<String>, path: YamlPath, cause: Throwable? = null) : YamlException(
      "Unknown type '$typeName'. Known types are: ${CollectionsKt.joinToString$default(
         CollectionsKt.sorted(validTypeNames), ", ", null, null, 0, null, null, 62, null
      )}",
      path,
      cause
   ) {
   public final val typeName: String
   public final val validTypeNames: Set<String>

   init {
      this.typeName = typeName;
      this.validTypeNames = validTypeNames;
   }
}
