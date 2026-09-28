package io.ktor.websocket

import io.ktor.util.NIOKt
import java.nio.Buffer
import java.nio.ByteBuffer
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nSimpleFrameCollector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpleFrameCollector.kt\nio/ktor/websocket/SimpleFrameCollector\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,51:1\n1#2:52\n*E\n"])
public class SimpleFrameCollector {
   private final var remaining: Int
   private final var buffer: ByteBuffer?
   private final val maskBuffer: ByteBuffer = ByteBuffer.allocate(4)

   public final val hasRemaining: Boolean
      public final get() {
         return this.remaining > 0;
      }


   public fun start(length: Int, bb: ByteBuffer) {
      if (this.remaining != 0) {
         throw new IllegalStateException("remaining should be 0");
      } else {
         label19: {
            this.remaining = length;
            if (this.buffer != null) {
               val var10000: ByteBuffer = this.buffer;
               if (var10000.capacity() >= length) {
                  break label19;
               }
            }

            this.buffer = ByteBuffer.allocate(length);
         }

         val var4: ByteBuffer = this.buffer;
         ((Buffer)var4).clear();
         this.handle(bb);
      }
   }

   public fun handle(bb: ByteBuffer) {
      val var10001: Int = this.remaining;
      val var10003: ByteBuffer = this.buffer;
      this.remaining = var10001 - NIOKt.moveTo(bb, var10003, this.remaining);
   }

   public fun take(maskKey: Int?): ByteBuffer {
      var var10000: ByteBuffer = this.buffer;
      ((Buffer)var10000).flip();
      val view: ByteBuffer = var10000.slice();
      if (maskKey != null) {
         ((Buffer)this.maskBuffer).clear();
         this.maskBuffer.asIntBuffer().put(maskKey);
         ((Buffer)this.maskBuffer).clear();
         val var10001: ByteBuffer = this.maskBuffer;
         UtilsKt.xor(view, var10001);
      }

      this.buffer = null;
      var10000 = view.asReadOnlyBuffer();
      return var10000;
   }
}
