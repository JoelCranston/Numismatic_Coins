package io.ktor.http.parsing

public class ParseException(message: String, cause: Throwable? = null) : IllegalArgumentException(message, cause) {
   public open val message: String
   public open val cause: Throwable?

   init {
      this.message = message;
      this.cause = cause;
   }
}
