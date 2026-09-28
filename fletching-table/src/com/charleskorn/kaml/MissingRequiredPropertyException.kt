package com.charleskorn.kaml

public class MissingRequiredPropertyException(propertyName: String, path: YamlPath, cause: Throwable? = null) : YamlException(
      "Property '$propertyName' is required but it is missing.", path, cause
   ) {
   public final val propertyName: String

   init {
      this.propertyName = propertyName;
   }
}
