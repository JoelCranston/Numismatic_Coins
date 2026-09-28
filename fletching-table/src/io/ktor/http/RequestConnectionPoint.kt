package io.ktor.http

public interface RequestConnectionPoint {
   public val scheme: String
   public val version: String
   public val port: Int
   public val localPort: Int
   public val serverPort: Int
   public val host: String
   public val localHost: String
   public val serverHost: String
   public val localAddress: String
   public val uri: String
   public val method: HttpMethod
   public val remoteHost: String
   public val remotePort: Int
   public val remoteAddress: String

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
