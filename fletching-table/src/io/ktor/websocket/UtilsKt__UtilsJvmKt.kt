package io.ktor.websocket

import java.nio.ByteBuffer
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nUtilsJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UtilsJvm.kt\nio/ktor/websocket/UtilsKt__UtilsJvmKt\n+ 2 Utils.kt\nio/ktor/websocket/UtilsKt__UtilsKt\n*L\n1#1,24:1\n11#2:25\n*S KotlinDebug\n*F\n+ 1 UtilsJvm.kt\nio/ktor/websocket/UtilsKt__UtilsJvmKt\n*L\n18#1:25\n*E\n"])
@JvmSynthetic
internal class UtilsKt__UtilsJvmKt {
   internal final val OUTGOING_CHANNEL_CAPACITY: Int
      internal final get() {
         val var10000: java.lang.String = System.getProperty("io.ktor.websocket.outgoingChannelCapacity");
         return if (var10000 != null) Integer.parseInt(var10000) else 8;
      }


   @JvmStatic
   internal fun ByteBuffer.xor(other: ByteBuffer) {
      val bb: ByteBuffer = `$this$xor`.slice();
      val mask: ByteBuffer = other.slice();
      val maskSize: Int = mask.remaining();
      var i: Int = 0;

      for (int var6 = bb.remaining(); i < var6; i++) {
         bb.put(i, (byte)(bb.get(i) xor mask.get(i % maskSize)));
      }
   }
}
