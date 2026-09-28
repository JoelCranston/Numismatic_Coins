package io.ktor.utils.io

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt

public suspend fun ByteReadChannel.lookAhead(block: (LookAheadSuspendSession, Continuation<Unit>) -> Any?) {
   val var10000: Any = block.invoke(new LookAheadSuspendSession(`$this$lookAhead`), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun ByteReadChannel.lookAheadSuspend(block: (LookAheadSuspendSession, Continuation<Unit>) -> Any?) {
   val var10000: Any = block.invoke(new LookAheadSuspendSession(`$this$lookAheadSuspend`), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}
