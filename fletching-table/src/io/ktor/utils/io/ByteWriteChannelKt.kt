package io.ktor.utils.io

import io.ktor.utils.io.ByteWriteChannelKt.close.1
import io.ktor.utils.io.core.BytePacketBuilderKt
import java.io.IOException
import kotlin.coroutines.intrinsics.IntrinsicsKt

@Deprecated(message = "Async close is deprecated. Please consider replacing it with flushAndClose or cancel ", replaceWith = @ReplaceWith(expression = "flushAndClose()", imports = []), level = DeprecationLevel.WARNING)
public fun ByteWriteChannel.close() {
   ByteWriteChannelOperationsKt.fireAndForget(new 1(`$this$close`));
}

public fun ByteChannel.cancel() {
   `$this$cancel`.cancel(new IOException("Channel was cancelled"));
}

@Deprecated(message = "Cancel without reason is deprecated. Please provide a cause for cancellation.", replaceWith = @ReplaceWith(expression = "cancel(IOException())", imports = ["kotlinx.coroutines.cancel"]), level = DeprecationLevel.ERROR)
public fun ByteWriteChannel.cancel() {
   `$this$cancel`.cancel(new IOException("Channel was cancelled"));
}

@InternalAPI
public suspend fun ByteWriteChannel.flushIfNeeded() {
   ByteReadChannelOperationsKt.rethrowCloseCauseIfNeeded(`$this$flushIfNeeded`);
   if (!`$this$flushIfNeeded`.getAutoFlush() && BytePacketBuilderKt.getSize(`$this$flushIfNeeded`.getWriteBuffer()) < 1048576) {
      return Unit.INSTANCE;
   } else {
      val var10000: Any = `$this$flushIfNeeded`.flush(`$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
