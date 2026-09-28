package okio

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.InlineMarker

internal fun ByteArray.toUtf8String(): String {
   return new java.lang.String(`$this$toUtf8String`, Charsets.UTF_8);
}

internal fun String.asUtf8ToByteArray(): ByteArray {
   val var10000: ByteArray = `$this$asUtf8ToByteArray`.getBytes(Charsets.UTF_8);
   return var10000;
}

internal fun newLock(): ReentrantLock {
   return new ReentrantLock();
}

public inline fun <T> ReentrantLock.withLock(action: () -> T): T {
   contract {
      callsInPlace(action, InvocationKind.EXACTLY_ONCE)
   }

   label15: {
      val var3: Lock = `$this$withLock`;
      `$this$withLock`.lock();

      try {
         val var4: Any = action.invoke();
      } catch (var6: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         var3.unlock();
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      var3.unlock();
      InlineMarker.finallyEnd(1);
   }
}
