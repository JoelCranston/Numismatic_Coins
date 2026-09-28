package io.ktor.util

import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.core.internal.ChunkBufferJvmKt
import io.ktor.utils.io.core.internal.ChunkBufferKt
import java.nio.ByteBuffer
import java.nio.channels.ReadableByteChannel
import java.nio.channels.WritableByteChannel
import kotlin.jvm.internal.Ref
import kotlinx.io.Buffer

public fun ReadableByteChannel.read(buffer: Buffer): Int {
   if (ChunkBufferKt.getWriteRemaining(buffer) == 0) {
      return 0;
   } else {
      val count: Ref.IntRef = new Ref.IntRef();
      ChunkBufferJvmKt.writeDirect(buffer, 1, BufferViewJvmKt::read$lambda$0);
      return count.element;
   }
}

@InternalAPI
public fun WritableByteChannel.write(buffer: Buffer): Int {
   val count: Ref.IntRef = new Ref.IntRef();
   ChunkBufferJvmKt.readDirect(buffer, BufferViewJvmKt::write$lambda$0);
   return count.element;
}

fun `read$lambda$0`(`$count`: Ref.IntRef, `$this_read`: ReadableByteChannel, bb: ByteBuffer): Unit {
   `$count`.element = `$this_read`.read(bb);
   return Unit.INSTANCE;
}

fun `write$lambda$0`(`$count`: Ref.IntRef, `$this_write`: WritableByteChannel, bb: ByteBuffer): Unit {
   `$count`.element = `$this_write`.write(bb);
   return Unit.INSTANCE;
}
