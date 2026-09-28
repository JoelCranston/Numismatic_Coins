package io.ktor.network.sockets

import io.ktor.utils.io.core.ByteReadPacketExtensions_jvmKt
import java.nio.Buffer
import java.nio.ByteBuffer
import kotlin.jvm.functions.Function1
import kotlinx.io.Source

private final val CLOSED: (Throwable?) -> Unit = DatagramSendChannelKt::CLOSED$lambda$0
private final val CLOSED_INVOKED: (Throwable?) -> Unit = DatagramSendChannelKt::CLOSED_INVOKED$lambda$0

private fun failInvokeOnClose(handler: ((Throwable?) -> Unit)?) {
   throw new IllegalStateException(
      if (handler === CLOSED_INVOKED) "Another handler was already registered and successfully invoked" else "Another handler was already registered: $handler"
   );
}

private fun Source.writeMessageTo(buffer: ByteBuffer) {
   ByteReadPacketExtensions_jvmKt.readFully(`$this$writeMessageTo`, buffer);
   ((Buffer)buffer).flip();
}

fun `CLOSED$lambda$0`(it: java.lang.Throwable): Unit {
   return Unit.INSTANCE;
}

fun `CLOSED_INVOKED$lambda$0`(it: java.lang.Throwable): Unit {
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getCLOSED$p`(): Function1 {
   return CLOSED;
}

@JvmSynthetic
fun `access$getCLOSED_INVOKED$p`(): Function1 {
   return CLOSED_INVOKED;
}

@JvmSynthetic
fun `access$failInvokeOnClose`(handler: Function1) {
   failInvokeOnClose(handler);
}

@JvmSynthetic
fun `access$writeMessageTo`(`$receiver`: Source, buffer: ByteBuffer) {
   writeMessageTo(`$receiver`, buffer);
}
