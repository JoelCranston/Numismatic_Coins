package io.ktor.websocket

import io.ktor.util.NIOKt
import io.ktor.utils.io.core.StringsKt
import java.nio.ByteBuffer
import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DisposableHandle
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesKt

public sealed class Frame protected constructor(fin: Boolean,
   frameType: FrameType,
   data: ByteArray,
   disposableHandle: DisposableHandle = NonDisposableHandle.INSTANCE as DisposableHandle,
   rsv1: Boolean = false,
   rsv2: Boolean = false,
   rsv3: Boolean = false
) {
   public final val fin: Boolean
   public final val frameType: FrameType
   public final val data: ByteArray
   public final val disposableHandle: DisposableHandle
   public final val rsv1: Boolean
   public final val rsv2: Boolean
   public final val rsv3: Boolean
   public final val buffer: ByteBuffer

   init {
      this.fin = fin;
      this.frameType = frameType;
      this.data = data;
      this.disposableHandle = disposableHandle;
      this.rsv1 = rsv1;
      this.rsv2 = rsv2;
      this.rsv3 = rsv3;
      val var10001: ByteBuffer = ByteBuffer.wrap(this.data);
      this.buffer = var10001;
   }

   public override fun toString(): String {
      return "Frame ${this.frameType} (fin=${this.fin}, buffer len = ${this.data.length})";
   }

   public fun copy(): Frame {
      val var10000: Frame.Companion = Companion;
      val var10001: Boolean = this.fin;
      val var10002: FrameType = this.frameType;
      val var10003: ByteArray = Arrays.copyOf(this.data, this.data.length);
      return var10000.byType(var10001, var10002, var10003, this.rsv1, this.rsv2, this.rsv3);
   }

   public class Binary(fin: Boolean, data: ByteArray, rsv1: Boolean = false, rsv2: Boolean = false, rsv3: Boolean = false) : Frame(
         fin, FrameType.BINARY, data, NonDisposableHandle.INSTANCE, rsv1, rsv2, rsv3
      ) {
      public constructor(fin: Boolean, buffer: ByteBuffer) : this(fin, NIOKt.moveToByteArray(buffer))
      public constructor(fin: Boolean, data: ByteArray) : this(fin, data, false, false, false)
      public constructor(fin: Boolean, packet: Source) : this(fin, SourcesKt.readByteArray(packet))   }

   @SourceDebugExtension(["SMAP\nFrame.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Frame.kt\nio/ktor/websocket/Frame$Close\n+ 2 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n*L\n1#1,177:1\n21#2,3:178\n*S KotlinDebug\n*F\n+ 1 Frame.kt\nio/ktor/websocket/Frame$Close\n*L\n100#1:178,3\n*E\n"])
   public class Close(data: ByteArray) : Frame(true, FrameType.CLOSE, data, NonDisposableHandle.INSTANCE, false, false, false) {
      public constructor(reason: CloseReason)  {
         val `builder$iv`: Buffer = new Buffer();
         val `$this$_init__u24lambda_u240`: Sink = `builder$iv`;
         `builder$iv`.writeShort(reason.getCode());
         StringsKt.writeText$default(`$this$_init__u24lambda_u240`, reason.getMessage(), 0, 0, null, 14, null);
         this(`builder$iv`);
      }

      public constructor(packet: Source) : this(SourcesKt.readByteArray(packet))
      public constructor() : this(Frame.access$getEmpty$cp())
      public constructor(buffer: ByteBuffer) : this(NIOKt.moveToByteArray(buffer))   }

   public companion object {
      private final val Empty: ByteArray

      public fun byType(fin: Boolean, frameType: FrameType, data: ByteArray, rsv1: Boolean, rsv2: Boolean, rsv3: Boolean): Frame {
         var var10000: Frame;
         switch (Frame.Companion.WhenMappings.$EnumSwitchMapping$0[frameType.ordinal()]) {
            case 1:
               var10000 = new Frame.Binary(fin, data, rsv1, rsv2, rsv3);
               break;
            case 2:
               var10000 = new Frame.Text(fin, data, rsv1, rsv2, rsv3);
               break;
            case 3:
               var10000 = new Frame.Close(data);
               break;
            case 4:
               var10000 = new Frame.Ping(data);
               break;
            case 5:
               var10000 = new Frame.Pong(data, NonDisposableHandle.INSTANCE);
               break;
            default:
               throw new NoWhenBranchMatchedException();
         }

         return var10000;
      }
   }

   public class Ping(data: ByteArray) : Frame(true, FrameType.PING, data, NonDisposableHandle.INSTANCE, false, false, false) {
      public constructor(packet: Source) : this(SourcesKt.readByteArray(packet))
      public constructor(buffer: ByteBuffer) : this(NIOKt.moveToByteArray(buffer))   }

   public class Pong(data: ByteArray, disposableHandle: DisposableHandle = NonDisposableHandle.INSTANCE as DisposableHandle) : Frame(
         true, FrameType.PONG, data, disposableHandle, false, false, false
      ) {
      public constructor(packet: Source) : this(SourcesKt.readByteArray(packet), NonDisposableHandle.INSTANCE)
      public constructor(buffer: ByteBuffer, disposableHandle: DisposableHandle = NonDisposableHandle.INSTANCE as DisposableHandle) : this(
            NIOKt.moveToByteArray(buffer), disposableHandle
         )
      public constructor(buffer: ByteBuffer) : this(NIOKt.moveToByteArray(buffer), NonDisposableHandle.INSTANCE)   }

   public class Text(fin: Boolean, data: ByteArray, rsv1: Boolean = false, rsv2: Boolean = false, rsv3: Boolean = false) : Frame(
         fin, FrameType.TEXT, data, NonDisposableHandle.INSTANCE, rsv1, rsv2, rsv3
      ) {
      public constructor(fin: Boolean, data: ByteArray) : this(fin, data, false, false, false)
      public constructor(text: String) : this(true, StringsKt.toByteArray$default(text, null, 1, null))
      public constructor(fin: Boolean, packet: Source) : this(fin, SourcesKt.readByteArray(packet))
      public constructor(fin: Boolean, buffer: ByteBuffer) : this(fin, NIOKt.moveToByteArray(buffer))   }
}
