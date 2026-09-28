package io.ktor.http

public class IllegalHeaderValueException(headerValue: String, position: Int) : IllegalArgumentException(
      "Header value '$headerValue' contains illegal character '${headerValue.charAt(position)}' (code ${headerValue.charAt(position) and 255})"
   ) {
   public final val headerValue: String
   public final val position: Int

   init {
      this.headerValue = headerValue;
      this.position = position;
   }
}
