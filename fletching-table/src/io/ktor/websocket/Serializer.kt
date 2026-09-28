package io.ktor.websocket

import io.ktor.util.NIOKt
import java.nio.Buffer
import java.nio.ByteBuffer
import java.util.concurrent.ArrayBlockingQueue
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.random.Random

@SourceDebugExtension(["SMAP\nSerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Serializer.kt\nio/ktor/websocket/Serializer\n+ 2 Utils.kt\nio/ktor/websocket/UtilsKt__UtilsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,127:1\n14#2:128\n14#2:129\n14#2:130\n14#2:131\n14#2:132\n1#3:133\n*S KotlinDebug\n*F\n+ 1 Serializer.kt\nio/ktor/websocket/Serializer\n*L\n75#1:128\n76#1:129\n77#1:130\n78#1:131\n82#1:132\n*E\n"])
public class Serializer {
   private final val messages: ArrayBlockingQueue<Frame> = new ArrayBlockingQueue(1024)
   private final var frameBody: ByteBuffer?
   private final var maskBuffer: ByteBuffer?
   private final var lastDataFrameType: FrameType?
   public final var masking: Boolean

   public final val hasOutstandingBytes: Boolean
      public final get() {
         return !this.messages.isEmpty() || this.frameBody != null;
      }


   public final val remainingCapacity: Int
      public final get() {
         return this.messages.remainingCapacity();
      }


   public fun enqueue(f: Frame) {
      this.messages.put(f);
   }

   public fun serialize(buffer: ByteBuffer) {
      while (this.writeCurrentPayload(buffer)) {
         val var10000: Frame = this.messages.peek();
         if (var10000 == null) {
            break;
         }

         val mask: Boolean = this.masking;
         this.setMaskBuffer(this.masking);
         if (buffer.remaining() < this.estimateFrameHeaderSize(var10000, mask)) {
            break;
         }

         this.serializeHeader(var10000, buffer, mask);
         this.messages.remove();
         this.frameBody = this.maskedIfNeeded(var10000.getBuffer());
      }
   }

   private fun serializeHeader(frame: Frame, buffer: ByteBuffer, mask: Boolean) {
      val size: Int = frame.getBuffer().remaining();
      val formattedLength: Int = if (size < 126) size else (if (size <= 65535) 126 else 127);
      val var10000: Int;
      if (this.lastDataFrameType == null) {
         if (!frame.getFin()) {
            this.lastDataFrameType = frame.getFrameType();
         }

         var10000 = frame.getFrameType().getOpcode();
      } else if (this.lastDataFrameType === frame.getFrameType()) {
         if (frame.getFin()) {
            this.lastDataFrameType = null;
         }

         var10000 = 0;
      } else {
         if (!frame.getFrameType().getControlFrame()) {
            throw new IllegalStateException("Can't continue with different data frame opcode");
         }

         var10000 = frame.getFrameType().getOpcode();
      }

      buffer.put(
         (byte)(
            (if (frame.getFin()) 1 shl 7 else 0) or (if (frame.getRsv1()) 1 shl 6 else 0) or (if (frame.getRsv2()) 1 shl 5 else 0) or (
               if (frame.getRsv3()) 1 shl 4 else 0
            ) or var10000
         )
      );
      buffer.put((byte)((if (mask) 1 shl 7 else 0) or formattedLength));
      switch (formattedLength) {
         case 126:
            buffer.putShort((short)frame.getBuffer().remaining());
            break;
         case 127:
            buffer.putLong((long)frame.getBuffer().remaining());
         default:
      }

      if (this.maskBuffer != null) {
         val var26: ByteBuffer = this.maskBuffer.duplicate();
         if (var26 != null) {
            NIOKt.moveTo$default(var26, buffer, 0, 2, null);
         }
      }
   }

   private fun estimateFrameHeaderSize(f: Frame, mask: Boolean): Int {
      val size: Int = f.getBuffer().remaining();
      return (if (size < 126) 2 else (if (size <= 32767) 4 else 10)) + this.maskSize(mask);
   }

   private fun writeCurrentPayload(buffer: ByteBuffer): Boolean {
      if (this.frameBody == null) {
         return true;
      } else {
         val frame: ByteBuffer = this.frameBody;
         NIOKt.moveTo$default(this.frameBody, buffer, 0, 2, null);
         if (!frame.hasRemaining()) {
            this.frameBody = null;
            return true;
         } else {
            return false;
         }
      }
   }

   private fun maskSize(mask: Boolean): Int {
      return if (mask) 4 else 0;
   }

   private fun ByteBuffer.maskedIfNeeded(): ByteBuffer {
      if (this.maskBuffer != null) {
         val mask: ByteBuffer = this.maskBuffer;
         val var4: ByteBuffer = NIOKt.copy$default(`$this$maskedIfNeeded`, 0, 1, null);
         UtilsKt.xor(var4, mask);
         if (var4 != null) {
            return var4;
         }
      }

      return `$this$maskedIfNeeded`;
   }

   private fun setMaskBuffer(mask: Boolean) {
      var var10000: Serializer = this;
      val var10001: ByteBuffer;
      if (mask) {
         val var2: ByteBuffer = ByteBuffer.allocate(4);
         var2.putInt(Random.Default.nextInt());
         ((Buffer)var2).clear();
         var10000 = this;
         var10001 = var2;
      } else {
         var10001 = null;
      }

      var10000.maskBuffer = var10001;
   }
}
