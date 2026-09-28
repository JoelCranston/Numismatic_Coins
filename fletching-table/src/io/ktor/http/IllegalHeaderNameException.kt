package io.ktor.http

public class IllegalHeaderNameException(headerName: String, position: Int) : IllegalArgumentException(
      "Header name '$headerName' contains illegal character '${headerName.charAt(position)}' (code ${headerName.charAt(position) and 255})"
   ) {
   public final val headerName: String
   public final val position: Int

   init {
      this.headerName = headerName;
      this.position = position;
   }
}
