package io.ktor.http

public final val authority: String
   public final get() {
      val var1: StringBuilder = new StringBuilder();
      var1.append(getEncodedUserAndPassword(`$this$authority`));
      var1.append(URLUtilsKt.getHostWithPortIfSpecified(`$this$authority`));
      return var1.toString();
   }


public final val protocolWithAuthority: String
   public final get() {
      val var1: StringBuilder = new StringBuilder();
      var1.append(`$this$protocolWithAuthority`.getProtocol().getName());
      var1.append("://");
      var1.append(getEncodedUserAndPassword(`$this$protocolWithAuthority`));
      if (`$this$protocolWithAuthority`.getSpecifiedPort() != 0
         && `$this$protocolWithAuthority`.getSpecifiedPort() != `$this$protocolWithAuthority`.getProtocol().getDefaultPort()) {
         var1.append(URLUtilsKt.getHostWithPort(`$this$protocolWithAuthority`));
      } else {
         var1.append(`$this$protocolWithAuthority`.getHost());
      }

      return var1.toString();
   }


internal final val encodedUserAndPassword: String
   internal final get() {
      val var1: StringBuilder = new StringBuilder();
      URLUtilsKt.appendUserAndPassword(var1, `$this$encodedUserAndPassword`.getEncodedUser(), `$this$encodedUserAndPassword`.getEncodedPassword());
      return var1.toString();
   }

