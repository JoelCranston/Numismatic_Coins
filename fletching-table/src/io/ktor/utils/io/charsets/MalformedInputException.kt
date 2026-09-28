package io.ktor.utils.io.charsets

public open class MalformedInputException(message: String) : java.nio.charset.MalformedInputException(0) {
   private final val _message: String

   public open val message: String?
      public open get() {
         return this._message;
      }


   init {
      this._message = message;
   }
}
