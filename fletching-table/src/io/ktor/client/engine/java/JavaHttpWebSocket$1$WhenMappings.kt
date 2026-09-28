package io.ktor.client.engine.java

import io.ktor.websocket.FrameType

// $VF: Class flags could not be determined
@JvmSynthetic
internal class `JavaHttpWebSocket$1$WhenMappings` {
   @JvmStatic
   fun {
      val var0: IntArray = new int[FrameType.values().length];

      try {
         var0[FrameType.TEXT.ordinal()] = 1;
      } catch (var6: NoSuchFieldError) {
      }

      try {
         var0[FrameType.BINARY.ordinal()] = 2;
      } catch (var5: NoSuchFieldError) {
      }

      try {
         var0[FrameType.CLOSE.ordinal()] = 3;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[FrameType.PING.ordinal()] = 4;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[FrameType.PONG.ordinal()] = 5;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
