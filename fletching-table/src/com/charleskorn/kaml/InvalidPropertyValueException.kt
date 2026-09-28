package com.charleskorn.kaml

public class InvalidPropertyValueException(propertyName: String, reason: String, path: YamlPath, cause: Throwable? = null) : YamlException(
      "Value for '$propertyName' is invalid: $reason", path, cause
   ) {
   public final val propertyName: String
   public final val reason: String

   init {
      this.propertyName = propertyName;
      this.reason = reason;
   }
}
