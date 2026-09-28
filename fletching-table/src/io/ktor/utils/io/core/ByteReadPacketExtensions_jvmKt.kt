@file:SourceDebugExtension(["SMAP\nByteReadPacketExtensions.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteReadPacketExtensions.jvm.kt\nio/ktor/utils/io/core/ByteReadPacketExtensions_jvmKt\n+ 2 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,36:1\n99#2:37\n100#2,8:39\n1#3:38\n*S KotlinDebug\n*F\n+ 1 ByteReadPacketExtensions.jvm.kt\nio/ktor/utils/io/core/ByteReadPacketExtensions_jvmKt\n*L\n28#1:37\n28#1:39,8\n28#1:38\n*E\n"])

package io.ktor.utils.io.core

import java.nio.ByteBuffer
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Segment
import kotlinx.io.SinksJvmKt
import kotlinx.io.Source
import kotlinx.io.SourcesJvmKt
import kotlinx.io.unsafe.UnsafeBufferOperations

public fun ByteReadPacket(byteBuffer: ByteBuffer): Source {
   val var1: Buffer = new Buffer();
   SinksJvmKt.write(var1, byteBuffer);
   return var1;
}

public fun Source.readAvailable(buffer: ByteBuffer): Int {
   val result: Int = buffer.remaining();
   SourcesJvmKt.readAtMostTo(`$this$readAvailable`, buffer);
   return result - buffer.remaining();
}

public fun Source.readFully(buffer: ByteBuffer) {
   while (!$this$readFully.exhausted() && buffer.hasRemaining()) {
      SourcesJvmKt.readAtMostTo(`$this$readFully`, buffer);
   }
}

public fun Source.read(block: (ByteBuffer) -> Unit) {
   val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
   val `buffer$iv`: Buffer = `$this$read`.getBuffer();
   if (`buffer$iv`.exhausted()) {
      throw new IllegalArgumentException("Buffer is empty".toString());
   } else {
      val var10000: Segment = `buffer$iv`.getHead();
      val var14: ByteArray = var10000.dataAsByteArray(true);
      val start: Int = var10000.getPos();
      val wrap: ByteBuffer = ByteBuffer.wrap(var14, start, var10000.getLimit() - start);
      block.invoke(wrap);
      val consumed: Int = wrap.position() - start;
      if (consumed != 0) {
         if (consumed < 0) {
            throw new IllegalStateException("Returned negative read bytes count");
         }

         if (consumed > var10000.getSize()) {
            throw new IllegalStateException("Returned too many bytes");
         }

         `buffer$iv`.skip((long)consumed);
      }
   }
}
