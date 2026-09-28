package io.ktor.client.engine.java

import io.ktor.client.engine.HttpClientEngineConfig
import java.net.http.HttpClient.Builder
import java.net.http.HttpClient.Redirect
import java.net.http.HttpClient.Version
import kotlin.jvm.functions.Function1

public class JavaHttpConfig : HttpClientEngineConfig {
   public final var protocolVersion: Version = Version.HTTP_1_1
   internal final var config: (Builder) -> Unit = JavaHttpConfig::config$lambda$0

   public fun config(block: (Builder) -> Unit) {
      this.config = JavaHttpConfig::config$lambda$1;
   }

   @JvmStatic
   fun `config$lambda$0`(var0: Builder): Unit {
      var0.followRedirects(Redirect.NEVER);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `config$lambda$1`(`$oldConfig`: Function1, `$block`: Function1, var2: Builder): Unit {
      `$oldConfig`.invoke(var2);
      `$block`.invoke(var2);
      return Unit.INSTANCE;
   }
}
