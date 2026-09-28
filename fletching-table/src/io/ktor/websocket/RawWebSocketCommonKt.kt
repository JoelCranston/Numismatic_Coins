@file:SourceDebugExtension(["SMAP\nRawWebSocketCommon.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RawWebSocketCommon.kt\nio/ktor/websocket/RawWebSocketCommonKt\n+ 2 Utils.kt\nio/ktor/websocket/UtilsKt__UtilsKt\n+ 3 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n*L\n1#1,278:1\n14#2:279\n14#2:280\n14#2:281\n14#2:282\n14#2:283\n21#3,3:284\n*S KotlinDebug\n*F\n+ 1 RawWebSocketCommon.kt\nio/ktor/websocket/RawWebSocketCommonKt\n*L\n177#1:279\n178#1:280\n179#1:281\n180#1:282\n191#1:283\n160#1:284,3\n*E\n"])

package io.ktor.websocket

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.core.ByteReadPacketKt
import io.ktor.utils.io.core.MemoryKt
import io.ktor.websocket.RawWebSocketCommonKt.writeFrame.1
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.random.Random
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesKt

private fun Source.mask(maskKey: Int): Source {
   return MemoryKt.withMemory(4, RawWebSocketCommonKt::mask$lambda$0);
}

@InternalAPI
public suspend fun ByteWriteChannel.writeFrame(frame: Frame, masking: Boolean) {
   var `$continuation`: Continuation;
   label117: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label117;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   var var14: Any;
   var var18: Int;
   var var19: Int;
   var var20: Int;
   var var25: Int;
   var var30: Source;
   var var39: Source;
   label137: {
      var maskKey: Int;
      label138: {
         label102: {
            label121: {
               val `$result`: Any = `$continuation`.result;
               var14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               switch ($continuation.label) {
                  case 0:
                     ResultKt.throwOnFailure(`$result`);
                     var18 = frame.getData().length;
                     var19 = (if (frame.getFin()) 1 shl 7 else 0) or (if (frame.getRsv1()) 1 shl 6 else 0) or (if (frame.getRsv2()) 1 shl 5 else 0) or (
                        if (frame.getRsv3()) 1 shl 4 else 0
                     ) or frame.getFrameType().getOpcode();
                     val var10001: Byte = (byte)var19;
                     `$continuation`.L$0 = `$this$writeFrame`;
                     `$continuation`.L$1 = frame;
                     `$continuation`.Z$0 = masking;
                     `$continuation`.I$0 = var18;
                     `$continuation`.I$1 = var19;
                     `$continuation`.label = 1;
                     if (ByteWriteChannelOperationsKt.writeByte(`$this$writeFrame`, var10001, `$continuation`) === var14) {
                        return var14;
                     }
                     break;
                  case 1:
                     var19 = `$continuation`.I$1;
                     var18 = `$continuation`.I$0;
                     masking = `$continuation`.Z$0;
                     frame = `$continuation`.L$1 as Frame;
                     `$this$writeFrame` = `$continuation`.L$0 as ByteWriteChannel;
                     ResultKt.throwOnFailure(`$result`);
                     break;
                  case 2:
                     var25 = `$continuation`.I$3;
                     var20 = `$continuation`.I$2;
                     var19 = `$continuation`.I$1;
                     var18 = `$continuation`.I$0;
                     masking = `$continuation`.Z$0;
                     frame = `$continuation`.L$1 as Frame;
                     `$this$writeFrame` = `$continuation`.L$0 as ByteWriteChannel;
                     ResultKt.throwOnFailure(`$result`);
                     break label121;
                  case 3:
                     var25 = `$continuation`.I$3;
                     var20 = `$continuation`.I$2;
                     var19 = `$continuation`.I$1;
                     var18 = `$continuation`.I$0;
                     masking = `$continuation`.Z$0;
                     frame = `$continuation`.L$1 as Frame;
                     `$this$writeFrame` = `$continuation`.L$0 as ByteWriteChannel;
                     ResultKt.throwOnFailure(`$result`);
                     break label102;
                  case 4:
                     var25 = `$continuation`.I$3;
                     var20 = `$continuation`.I$2;
                     var19 = `$continuation`.I$1;
                     var18 = `$continuation`.I$0;
                     masking = `$continuation`.Z$0;
                     frame = `$continuation`.L$1 as Frame;
                     `$this$writeFrame` = `$continuation`.L$0 as ByteWriteChannel;
                     ResultKt.throwOnFailure(`$result`);
                     break label102;
                  case 5:
                     maskKey = `$continuation`.I$4;
                     var25 = `$continuation`.I$3;
                     var20 = `$continuation`.I$2;
                     var19 = `$continuation`.I$1;
                     var18 = `$continuation`.I$0;
                     masking = `$continuation`.Z$0;
                     var30 = `$continuation`.L$2 as Source;
                     frame = `$continuation`.L$1 as Frame;
                     `$this$writeFrame` = `$continuation`.L$0 as ByteWriteChannel;
                     ResultKt.throwOnFailure(`$result`);
                     break label138;
                  case 6:
                     var25 = `$continuation`.I$3;
                     var20 = `$continuation`.I$2;
                     var19 = `$continuation`.I$1;
                     var18 = `$continuation`.I$0;
                     masking = `$continuation`.Z$0;
                     val maskedData: Source = `$continuation`.L$3 as Source;
                     var30 = `$continuation`.L$2 as Source;
                     frame = `$continuation`.L$1 as Frame;
                     `$this$writeFrame` = `$continuation`.L$0 as ByteWriteChannel;
                     ResultKt.throwOnFailure(`$result`);
                     return Unit.INSTANCE;
                  default:
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var20 = if (var18 < 126) var18 else (if (var18 <= 65535) 126 else 127);
               var25 = (if (masking) 1 shl 7 else 0) or (if (var18 < 126) var18 else (if (var18 <= 65535) 126 else 127));
               val var40: Byte = (byte)((if (masking) 1 shl 7 else 0) or (if (var18 < 126) var18 else (if (var18 <= 65535) 126 else 127)));
               `$continuation`.L$0 = `$this$writeFrame`;
               `$continuation`.L$1 = frame;
               `$continuation`.Z$0 = masking;
               `$continuation`.I$0 = var18;
               `$continuation`.I$1 = var19;
               `$continuation`.I$2 = var20;
               `$continuation`.I$3 = var25;
               `$continuation`.label = 2;
               if (ByteWriteChannelOperationsKt.writeByte(`$this$writeFrame`, var40, `$continuation`) === var14) {
                  return var14;
               }
            }

            switch (formattedLength) {
               case 126:
                  val var42: Short = (short)var18;
                  `$continuation`.L$0 = `$this$writeFrame`;
                  `$continuation`.L$1 = frame;
                  `$continuation`.Z$0 = masking;
                  `$continuation`.I$0 = var18;
                  `$continuation`.I$1 = var19;
                  `$continuation`.I$2 = var20;
                  `$continuation`.I$3 = var25;
                  `$continuation`.label = 3;
                  if (ByteWriteChannelOperationsKt.writeShort(`$this$writeFrame`, var42, `$continuation`) === var14) {
                     return var14;
                  }
                  break;
               case 127:
                  val var41: Long = var18;
                  `$continuation`.L$0 = `$this$writeFrame`;
                  `$continuation`.L$1 = frame;
                  `$continuation`.Z$0 = masking;
                  `$continuation`.I$0 = var18;
                  `$continuation`.I$1 = var19;
                  `$continuation`.I$2 = var20;
                  `$continuation`.I$3 = var25;
                  `$continuation`.label = 4;
                  if (ByteWriteChannelOperationsKt.writeLong(`$this$writeFrame`, var41, `$continuation`) === var14) {
                     return var14;
                  }
               default:
            }
         }

         var30 = ByteReadPacketKt.ByteReadPacket$default(frame.getData(), 0, 0, 6, null);
         if (!masking) {
            if (masking) {
               throw new NoWhenBranchMatchedException();
            }

            var39 = var30;
            break label137;
         }

         maskKey = Random.Default.nextInt();
         `$continuation`.L$0 = `$this$writeFrame`;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(frame);
         `$continuation`.L$2 = var30;
         `$continuation`.Z$0 = masking;
         `$continuation`.I$0 = var18;
         `$continuation`.I$1 = var19;
         `$continuation`.I$2 = var20;
         `$continuation`.I$3 = var25;
         `$continuation`.I$4 = maskKey;
         `$continuation`.label = 5;
         if (ByteWriteChannelOperationsKt.writeInt(`$this$writeFrame`, maskKey, `$continuation`) === var14) {
            return var14;
         }
      }

      var39 = mask(var30, maskKey);
   }

   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$writeFrame`);
   `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(frame);
   `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var30);
   `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var39);
   `$continuation`.Z$0 = masking;
   `$continuation`.I$0 = var18;
   `$continuation`.I$1 = var19;
   `$continuation`.I$2 = var20;
   `$continuation`.I$3 = var25;
   `$continuation`.label = 6;
   return if (ByteWriteChannelOperationsKt.writePacket(`$this$writeFrame`, var39, `$continuation`) === var14) var14 else Unit.INSTANCE;
}

@InternalAPI
public suspend fun ByteReadChannel.readFrame(maxFrameSize: Long, lastOpcode: Int): Frame {
   var `$continuation`: Continuation;
   label157: {
      if (`$completion` is io.ktor.websocket.RawWebSocketCommonKt.readFrame.1) {
         `$continuation` = `$completion` as io.ktor.websocket.RawWebSocketCommonKt.readFrame.1;
         if (((`$completion` as io.ktor.websocket.RawWebSocketCommonKt.readFrame.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label157;
         }
      }

      `$continuation` = new io.ktor.websocket.RawWebSocketCommonKt.readFrame.1(`$completion`);
   }

   var flagsAndOpcode: Int;
   var frameType: FrameType;
   var fin: Boolean;
   var maskKey: Int;
   var var10000: Any;
   label161: {
      var var18: Any;
      var var22: Int;
      var var23: Int;
      var var24: Int;
      var var25: Long;
      label177: {
         label162: {
            label145: {
               label144: {
                  label163: {
                     label142: {
                        val `$result`: Any = `$continuation`.result;
                        var18 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch ($continuation.label) {
                           case 0:
                              ResultKt.throwOnFailure(`$result`);
                              `$continuation`.L$0 = `$this$readFrame`;
                              `$continuation`.J$0 = maxFrameSize;
                              `$continuation`.I$0 = lastOpcode;
                              `$continuation`.label = 1;
                              var10000 = ByteReadChannelOperationsKt.readByte(`$this$readFrame`, `$continuation`);
                              if (var10000 === var18) {
                                 return var18;
                              }
                              break;
                           case 1:
                              lastOpcode = `$continuation`.I$0;
                              maxFrameSize = `$continuation`.J$0;
                              `$this$readFrame` = `$continuation`.L$0 as ByteReadChannel;
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = `$result`;
                              break;
                           case 2:
                              flagsAndOpcode = `$continuation`.B$0;
                              lastOpcode = `$continuation`.I$0;
                              maxFrameSize = `$continuation`.J$0;
                              `$this$readFrame` = `$continuation`.L$0 as ByteReadChannel;
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = `$result`;
                              break label142;
                           case 3:
                              maskKey = `$continuation`.I$4;
                              fin = (boolean)`$continuation`.I$3;
                              var24 = `$continuation`.I$2;
                              var23 = `$continuation`.I$1;
                              var22 = `$continuation`.B$1;
                              flagsAndOpcode = `$continuation`.B$0;
                              lastOpcode = `$continuation`.I$0;
                              maxFrameSize = `$continuation`.J$0;
                              frameType = `$continuation`.L$1 as FrameType;
                              `$this$readFrame` = `$continuation`.L$0 as ByteReadChannel;
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = `$result`;
                              break label144;
                           case 4:
                              maskKey = `$continuation`.I$4;
                              fin = (boolean)`$continuation`.I$3;
                              var24 = `$continuation`.I$2;
                              var23 = `$continuation`.I$1;
                              var22 = `$continuation`.B$1;
                              flagsAndOpcode = `$continuation`.B$0;
                              lastOpcode = `$continuation`.I$0;
                              maxFrameSize = `$continuation`.J$0;
                              frameType = `$continuation`.L$1 as FrameType;
                              `$this$readFrame` = `$continuation`.L$0 as ByteReadChannel;
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = `$result`;
                              break label163;
                           case 5:
                              var25 = `$continuation`.J$1;
                              fin = (boolean)`$continuation`.I$3;
                              var24 = `$continuation`.I$2;
                              var23 = `$continuation`.I$1;
                              var22 = `$continuation`.B$1;
                              flagsAndOpcode = `$continuation`.B$0;
                              lastOpcode = `$continuation`.I$0;
                              maxFrameSize = `$continuation`.J$0;
                              frameType = `$continuation`.L$1 as FrameType;
                              `$this$readFrame` = `$continuation`.L$0 as ByteReadChannel;
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = `$result`;
                              break label162;
                           case 6:
                              maskKey = `$continuation`.I$4;
                              var25 = `$continuation`.J$1;
                              fin = (boolean)`$continuation`.I$3;
                              var24 = `$continuation`.I$2;
                              var23 = `$continuation`.I$1;
                              var22 = `$continuation`.B$1;
                              flagsAndOpcode = `$continuation`.B$0;
                              lastOpcode = `$continuation`.I$0;
                              maxFrameSize = `$continuation`.J$0;
                              frameType = `$continuation`.L$1 as FrameType;
                              `$this$readFrame` = `$continuation`.L$0 as ByteReadChannel;
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = `$result`;
                              break label161;
                           default:
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        flagsAndOpcode = (var10000 as java.lang.Number).byteValue();
                        `$continuation`.L$0 = `$this$readFrame`;
                        `$continuation`.J$0 = maxFrameSize;
                        `$continuation`.I$0 = lastOpcode;
                        `$continuation`.B$0 = (byte)flagsAndOpcode;
                        `$continuation`.label = 2;
                        var10000 = ByteReadChannelOperationsKt.readByte(`$this$readFrame`, `$continuation`);
                        if (var10000 === var18) {
                           return var18;
                        }
                     }

                     var22 = (var10000 as java.lang.Number).byteValue();
                     var23 = flagsAndOpcode and 15;
                     if ((flagsAndOpcode and 15) == 0 && lastOpcode == 0) {
                        throw new ProtocolViolationException("Can't continue finished frames");
                     }

                     var24 = if (var23 == 0) lastOpcode else var23;
                     var10000 = FrameType.Companion.get(if (var23 == 0) lastOpcode else var23);
                     if (var10000 == null) {
                        throw new ProtocolViolationException("Unsupported opcode: $var24");
                     }

                     frameType = (FrameType)var10000;
                     if (var23 != 0 && lastOpcode != 0 && !((FrameType)var10000).getControlFrame()) {
                        throw new ProtocolViolationException("Can't start new data frame before finishing previous one");
                     }

                     fin = (flagsAndOpcode and 128) != 0;
                     if (((FrameType)var10000).getControlFrame() && (flagsAndOpcode and 128) != 0 == 0) {
                        throw new ProtocolViolationException("control frames can't be fragmented");
                     }

                     maskKey = var22 and 127;
                     switch (maskAndLength & 127) {
                        case 126:
                           `$continuation`.L$0 = `$this$readFrame`;
                           `$continuation`.L$1 = var10000;
                           `$continuation`.J$0 = maxFrameSize;
                           `$continuation`.I$0 = lastOpcode;
                           `$continuation`.B$0 = (byte)flagsAndOpcode;
                           `$continuation`.B$1 = (byte)var22;
                           `$continuation`.I$1 = var23;
                           `$continuation`.I$2 = var24;
                           `$continuation`.I$3 = fin;
                           `$continuation`.I$4 = maskKey;
                           `$continuation`.label = 3;
                           var10000 = ByteReadChannelOperationsKt.readShort(`$this$readFrame`, `$continuation`);
                           if (var10000 === var18) {
                              return var18;
                           }
                           break label144;
                        case 127:
                           `$continuation`.L$0 = `$this$readFrame`;
                           `$continuation`.L$1 = var10000;
                           `$continuation`.J$0 = maxFrameSize;
                           `$continuation`.I$0 = lastOpcode;
                           `$continuation`.B$0 = (byte)flagsAndOpcode;
                           `$continuation`.B$1 = (byte)var22;
                           `$continuation`.I$1 = var23;
                           `$continuation`.I$2 = var24;
                           `$continuation`.I$3 = fin;
                           `$continuation`.I$4 = maskKey;
                           `$continuation`.label = 4;
                           var10000 = ByteReadChannelOperationsKt.readLong(`$this$readFrame`, `$continuation`);
                           if (var10000 === var18) {
                              return var18;
                           }
                           break;
                        default:
                           var36 = maskKey;
                           break label145;
                     }
                  }

                  var36 = (var10000 as java.lang.Number).longValue();
                  break label145;
               }

               var36 = (var10000 as java.lang.Number).shortValue() and 65535L;
            }

            var25 = var36;
            if (frameType.getControlFrame() && var36 > 125L) {
               throw new ProtocolViolationException("control frames can't be larger than 125 bytes");
            }

            val data: Boolean = (var22 and 128) != 0;
            if ((var22 and 128) == 0) {
               if (data) {
                  throw new NoWhenBranchMatchedException();
               }

               var37 = -1;
               break label177;
            }

            `$continuation`.L$0 = `$this$readFrame`;
            `$continuation`.L$1 = frameType;
            `$continuation`.J$0 = maxFrameSize;
            `$continuation`.I$0 = lastOpcode;
            `$continuation`.B$0 = (byte)flagsAndOpcode;
            `$continuation`.B$1 = (byte)var22;
            `$continuation`.I$1 = var23;
            `$continuation`.I$2 = var24;
            `$continuation`.I$3 = fin;
            `$continuation`.J$1 = var36;
            `$continuation`.label = 5;
            var10000 = ByteReadChannelOperationsKt.readInt(`$this$readFrame`, `$continuation`);
            if (var10000 === var18) {
               return var18;
            }
         }

         var37 = (var10000 as java.lang.Number).intValue();
      }

      maskKey = var37;
      if (var25 > 2147483647L || var25 > maxFrameSize) {
         throw new FrameTooBigException(var25);
      }

      val var10001: Int = (int)var25;
      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$readFrame`);
      `$continuation`.L$1 = frameType;
      `$continuation`.J$0 = maxFrameSize;
      `$continuation`.I$0 = lastOpcode;
      `$continuation`.B$0 = (byte)flagsAndOpcode;
      `$continuation`.B$1 = (byte)var22;
      `$continuation`.I$1 = var23;
      `$continuation`.I$2 = var24;
      `$continuation`.I$3 = fin;
      `$continuation`.J$1 = var25;
      `$continuation`.I$4 = var37;
      `$continuation`.label = 6;
      var10000 = ByteReadChannelOperationsKt.readPacket(`$this$readFrame`, var10001, `$continuation`);
      if (var10000 === var18) {
         return var18;
      }
   }

   return Frame.Companion
      .byType(
         fin != 0,
         frameType,
         SourcesKt.readByteArray(if (maskKey == -1) var10000 as Source else mask(var10000 as Source, maskKey)),
         (flagsAndOpcode and 64) != 0,
         (flagsAndOpcode and 32) != 0,
         (flagsAndOpcode and 16) != 0
      );
}

fun `mask$lambda$0`(`$maskKey`: Int, `$this_mask`: Source, maskMemory: ByteArray): Source {
   MemoryKt.storeIntAt(maskMemory, 0, `$maskKey`);
   val `builder$iv`: Buffer = new Buffer();
   val `$this$mask_u24lambda_u240_u240`: Sink = `builder$iv`;
   val var7: Int = (int)ByteReadPacketKt.getRemaining(`$this_mask`);

   for (int var8 = 0; var8 < var7; var8++) {
      `$this$mask_u24lambda_u240_u240`.writeByte((byte)(`$this_mask`.readByte() xor maskMemory[var8 % 4]));
   }

   return `builder$iv`;
}
