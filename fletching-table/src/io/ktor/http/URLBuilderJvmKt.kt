package io.ktor.http

import io.ktor.http.Url.Companion
import java.net.URI

public final val origin: String
   public final get() {
      return "http://localhost";
   }


public operator fun Companion.invoke(fullUrl: String): Url {
   val var2: URLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null);
   URLUtilsJvmKt.takeFrom(var2, new URI(fullUrl));
   return var2.build();
}
