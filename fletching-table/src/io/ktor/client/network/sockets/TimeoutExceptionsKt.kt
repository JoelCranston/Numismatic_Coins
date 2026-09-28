@file:SourceDebugExtension(["SMAP\nTimeoutExceptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeoutExceptions.kt\nio/ktor/client/network/sockets/TimeoutExceptionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,31:1\n1#2:32\n*E\n"])

package io.ktor.client.network.sockets

import java.net.SocketTimeoutException
import kotlin.jvm.internal.SourceDebugExtension

public fun SocketTimeoutException(message: String, cause: Throwable? = null): SocketTimeoutException {
   val var2: SocketTimeoutException = new SocketTimeoutException(message);
   var2.initCause(cause);
   return var2;
}

@JvmSynthetic
fun `SocketTimeoutException$default`(var0: java.lang.String, var1: java.lang.Throwable, var2: Int, var3: Any): SocketTimeoutException {
   if ((var2 and 2) != 0) {
      var1 = null;
   }

   return SocketTimeoutException(var0, var1);
}
