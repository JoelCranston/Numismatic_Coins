package io.ktor.http.auth

public object AuthScheme {
   public const val Basic: String = "Basic"
   public const val Digest: String = "Digest"
   public const val Negotiate: String = "Negotiate"
   public const val OAuth: String = "OAuth"
   public const val Bearer: String = "Bearer"
}
