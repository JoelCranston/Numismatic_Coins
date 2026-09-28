@file:SourceDebugExtension(["SMAP\nExceptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Exceptions.kt\nkotlinx/coroutines/ExceptionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,77:1\n1#2:78\n*E\n"])

package kotlinx.coroutines

import java.util.concurrent.CancellationException
import kotlin.jvm.internal.SourceDebugExtension

public fun CancellationException(message: String?, cause: Throwable?): CancellationException {
   val var2: CancellationException = new CancellationException(message);
   var2.initCause(cause);
   return var2;
}
