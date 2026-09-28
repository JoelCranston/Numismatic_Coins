package io.ktor.client.utils

import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import kotlin.jvm.functions.Function1

public fun buildHeaders(block: (HeadersBuilder) -> Unit = HeadersKt::buildHeaders$lambda$0): Headers {
   val var1: HeadersBuilder = new HeadersBuilder(0, 1, null);
   block.invoke(var1);
   return var1.build();
}

@JvmSynthetic
fun `buildHeaders$default`(var0: Function1, var1: Int, var2: Any): Headers {
   if ((var1 and 1) != 0) {
      var0 = HeadersKt::buildHeaders$lambda$0;
   }

   return buildHeaders(var0);
}

fun `buildHeaders$lambda$0`(var0: HeadersBuilder): Unit {
   return Unit.INSTANCE;
}
