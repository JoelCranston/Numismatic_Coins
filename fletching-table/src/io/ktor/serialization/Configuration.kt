package io.ktor.serialization

import io.ktor.http.ContentType

public interface Configuration {
   public abstract fun <T : ContentConverter> register(contentType: ContentType, converter: Any, configuration: (Any) -> Unit = ...) {
   }

   @JvmDefault
   @JvmStatic
   fun `register$lambda$0`(var0: ContentConverter): Unit {
      return Unit.INSTANCE;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
