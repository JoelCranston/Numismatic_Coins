package com.charleskorn.kaml

import kotlinx.serialization.SerializationException

public open class YamlException(message: String, path: YamlPath, cause: Throwable? = null) : SerializationException(message, cause) {
   public open val message: String
   public final val path: YamlPath
   public open val cause: Throwable?
   public final val location: Location
   public final val line: Int
   public final val column: Int

   init {
      this.message = message;
      this.path = path;
      this.cause = cause;
      this.location = this.path.getEndLocation();
      this.line = this.location.getLine();
      this.column = this.location.getColumn();
   }

   public override fun toString(): String {
      return "${(this.getClass()::class).getSimpleName()} at ${this.path.toHumanReadableString()} on line ${this.line}, column ${this.column}: ${this.getMessage()}";
   }
}
