package com.charleskorn.kaml

public class UnknownPropertyException(propertyName: String, validPropertyNames: Set<String>, path: YamlPath) : YamlException(
      "Unknown property '$propertyName'. Known properties are: ${CollectionsKt.joinToString$default(
         CollectionsKt.sorted(validPropertyNames), ", ", null, null, 0, null, null, 62, null
      )}",
      path,
      null,
      4
   ) {
   public final val propertyName: String
   public final val validPropertyNames: Set<String>

   init {
      this.propertyName = propertyName;
      this.validPropertyNames = validPropertyNames;
   }
}
