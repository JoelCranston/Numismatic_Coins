@file:SourceDebugExtension(["SMAP\nFrameCommon.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FrameCommon.kt\nio/ktor/websocket/FrameCommonKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n*L\n1#1,202:1\n1#2:203\n21#3,3:204\n21#3,3:207\n*S KotlinDebug\n*F\n+ 1 FrameCommon.kt\nio/ktor/websocket/FrameCommonKt\n*L\n169#1:204,3\n191#1:207,3\n*E\n"])

package io.ktor.websocket

import io.ktor.utils.io.charsets.EncodingKt
import io.ktor.utils.io.core.BytePacketBuilderKt
import io.ktor.utils.io.core.StringsKt
import io.ktor.websocket.Frame.Close
import io.ktor.websocket.Frame.Text
import java.nio.charset.CharsetDecoder
import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer

public fun Text.readText(): String {
   if (!`$this$readText`.getFin()) {
      throw new IllegalArgumentException("Text could be only extracted from non-fragmented frame".toString());
   } else {
      val var10000: CharsetDecoder = Charsets.UTF_8.newDecoder();
      val `builder$iv`: Buffer = new Buffer();
      BytePacketBuilderKt.writeFully$default(`builder$iv`, `$this$readText`.getData(), 0, 0, 6, null);
      return EncodingKt.decode$default(var10000, `builder$iv`, 0, 2, null);
   }
}

public fun Frame.readBytes(): ByteArray {
   var var10000: ByteArray = `$this$readBytes`.getData();
   var10000 = Arrays.copyOf(var10000, var10000.length);
   return var10000;
}

public fun Close.readReason(): CloseReason? {
   if (`$this$readReason`.getData().length < 2) {
      return null;
   } else {
      val message: Buffer = new Buffer();
      BytePacketBuilderKt.writeFully$default(message, `$this$readReason`.getData(), 0, 0, 6, null);
      return new CloseReason(message.readShort(), StringsKt.readText$default(message, null, 0, 3, null));
   }
}
