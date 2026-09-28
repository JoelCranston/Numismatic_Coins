@file:SourceDebugExtension(["SMAP\nByteReadChannelOperations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteReadChannelOperations.kt\nio/ktor/utils/io/ByteReadChannelOperationsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n+ 4 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n*L\n1#1,621:1\n1#2:622\n1#2:627\n21#3,3:623\n99#4:626\n100#4,8:628\n*S KotlinDebug\n*F\n+ 1 ByteReadChannelOperations.kt\nio/ktor/utils/io/ByteReadChannelOperationsKt\n*L\n503#1:627\n207#1:623,3\n503#1:626\n503#1:628,8\n*E\n"])

package io.ktor.utils.io

import io.ktor.utils.io.ByteReadChannelOperationsKt.exhausted.1
import io.ktor.utils.io.ByteReadChannelOperationsKt.readBuffer.3
import io.ktor.utils.io.ByteReadChannelOperationsKt.readRemaining.2
import io.ktor.utils.io.core.BuffersKt
import io.ktor.utils.io.core.BytePacketBuilderKt
import io.ktor.utils.io.core.ByteReadPacketKt
import io.ktor.utils.io.core.InputKt
import java.io.EOFException
import java.io.IOException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function4
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.io.Buffer
import kotlinx.io.ByteStringsKt
import kotlinx.io.Segment
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesKt
import kotlinx.io.bytestring.ByteString
import kotlinx.io.unsafe.UnsafeBufferOperations

public final val availableForWrite: Int
   public final get() {
      return 1048576 - BytePacketBuilderKt.getSize(`$this$availableForWrite`.getWriteBuffer());
   }


private const val CR: Byte = 13
private const val LF: Byte = 10

public final val availableForRead: Int
   public final get() {
      return (int)`$this$availableForRead`.getReadBuffer().getBuffer().getSize();
   }


public suspend fun ByteReadChannel.exhausted(): Boolean {
   var `$continuation`: Continuation;
   label27: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label27;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (`$this$exhausted`.getReadBuffer().exhausted()) {
            `$continuation`.L$0 = `$this$exhausted`;
            `$continuation`.label = 1;
            if (ByteReadChannel.awaitContent$default(`$this$exhausted`, 0, `$continuation`, 1, null) === var4) {
               return var4;
            }
         }
         break;
      case 1:
         `$this$exhausted` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxBoolean(`$this$exhausted`.getReadBuffer().exhausted());
}

public suspend fun ByteReadChannel.toByteArray(): ByteArray {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.toByteArray.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.toByteArray.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.toByteArray.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.toByteArray.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$toByteArray`);
         `$continuation`.label = 1;
         var10000 = readBuffer(`$this$toByteArray`, `$continuation`);
         if (var10000 === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$toByteArray` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return BuffersKt.readBytes$default(var10000 as Buffer, 0, 1, null);
}

public suspend fun ByteReadChannel.readByte(): Byte {
   var `$continuation`: Continuation;
   label29: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readByte.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readByte.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readByte.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label29;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readByte.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var currentBuffer: Source;
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         currentBuffer = `$this$readByte`.getReadBuffer();
         if (!currentBuffer.exhausted()) {
            return Boxing.boxByte(currentBuffer.readByte());
         }

         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$readByte`);
         `$continuation`.L$1 = currentBuffer;
         `$continuation`.label = 1;
         var10000 = ByteReadChannel.awaitContent$default(`$this$readByte`, 0, `$continuation`, 1, null);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         currentBuffer = `$continuation`.L$1 as Source;
         `$this$readByte` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if (!var10000 as java.lang.Boolean) {
      throw new EOFException("Not enough data available");
   } else {
      return Boxing.boxByte(currentBuffer.readByte());
   }
}

public suspend fun ByteReadChannel.readShort(): Short {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readShort.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readShort.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readShort.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readShort.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = `$this$readShort`;
         `$continuation`.label = 1;
         if (awaitUntilReadable(`$this$readShort`, 2, `$continuation`) === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$readShort` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxShort(`$this$readShort`.getReadBuffer().readShort());
}

public suspend fun ByteReadChannel.readInt(): Int {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readInt.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readInt.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readInt.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readInt.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = `$this$readInt`;
         `$continuation`.label = 1;
         if (awaitUntilReadable(`$this$readInt`, 4, `$continuation`) === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$readInt` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxInt(`$this$readInt`.getReadBuffer().readInt());
}

public suspend fun ByteReadChannel.readFloat(): Float {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readFloat.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readFloat.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readFloat.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readFloat.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = `$this$readFloat`;
         `$continuation`.label = 1;
         if (awaitUntilReadable(`$this$readFloat`, 4, `$continuation`) === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$readFloat` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxFloat(SourcesKt.readFloat(`$this$readFloat`.getReadBuffer()));
}

public suspend fun ByteReadChannel.readLong(): Long {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readLong.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readLong.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readLong.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readLong.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = `$this$readLong`;
         `$continuation`.label = 1;
         if (awaitUntilReadable(`$this$readLong`, 8, `$continuation`) === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$readLong` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxLong(`$this$readLong`.getReadBuffer().readLong());
}

public suspend fun ByteReadChannel.readDouble(): Double {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readDouble.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readDouble.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readDouble.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readDouble.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = `$this$readDouble`;
         `$continuation`.label = 1;
         if (awaitUntilReadable(`$this$readDouble`, 8, `$continuation`) === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$readDouble` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxDouble(SourcesKt.readDouble(`$this$readDouble`.getReadBuffer()));
}

private suspend fun ByteReadChannel.awaitUntilReadable(numberOfBytes: Int) {
   var `$continuation`: Continuation;
   label24: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.awaitUntilReadable.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.awaitUntilReadable.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.awaitUntilReadable.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label24;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.awaitUntilReadable.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$awaitUntilReadable`);
         `$continuation`.I$0 = numberOfBytes;
         `$continuation`.label = 1;
         var10000 = `$this$awaitUntilReadable`.awaitContent(numberOfBytes, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         numberOfBytes = `$continuation`.I$0;
         `$this$awaitUntilReadable` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if (!var10000 as java.lang.Boolean) {
      throw new EOFException("Not enough data available");
   } else {
      return Unit.INSTANCE;
   }
}

public suspend fun ByteReadChannel.readBuffer(): Buffer {
   var `$continuation`: Continuation;
   label37: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readBuffer.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readBuffer.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readBuffer.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label37;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readBuffer.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var result: Buffer;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         result = new Buffer();
         break;
      case 1:
         result = `$continuation`.L$1 as Buffer;
         `$this$readBuffer` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (!$this$readBuffer.isClosedForRead()) {
      result.transferFrom(`$this$readBuffer`.getReadBuffer());
      `$continuation`.L$0 = `$this$readBuffer`;
      `$continuation`.L$1 = result;
      `$continuation`.label = 1;
      if (ByteReadChannel.awaitContent$default(`$this$readBuffer`, 0, `$continuation`, 1, null) === var7) {
         return var7;
      }
   }

   val var10000: java.lang.Throwable = `$this$readBuffer`.getClosedCause();
   if (var10000 != null) {
      throw var10000;
   } else {
      return result;
   }
}

public suspend fun ByteReadChannel.readBuffer(max: Int): Buffer {
   var `$continuation`: Continuation;
   label44: {
      if (`$completion` is 3) {
         `$continuation` = `$completion` as 3;
         if (((`$completion` as 3).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label44;
         }
      }

      `$continuation` = new 3(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var result: Buffer;
   var var10: Int;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         result = new Buffer();
         var10 = max;
         break;
      case 1:
         var10 = `$continuation`.I$1;
         max = `$continuation`.I$0;
         result = `$continuation`.L$1 as Buffer;
         `$this$readBuffer` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         val size: Long = Math.min((long)var10, ByteReadPacketKt.getRemaining(`$this$readBuffer`.getReadBuffer()));
         `$this$readBuffer`.getReadBuffer().readTo(result, size);
         var10 = var10 - (int)size;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (remaining > 0 && !$this$readBuffer.isClosedForRead()) {
      if (`$this$readBuffer`.getReadBuffer().exhausted()) {
         `$continuation`.L$0 = `$this$readBuffer`;
         `$continuation`.L$1 = result;
         `$continuation`.I$0 = max;
         `$continuation`.I$1 = var10;
         `$continuation`.label = 1;
         if (ByteReadChannel.awaitContent$default(`$this$readBuffer`, 0, `$continuation`, 1, null) === var9) {
            return var9;
         }
      }

      val var11: Long = Math.min((long)var10, ByteReadPacketKt.getRemaining(`$this$readBuffer`.getReadBuffer()));
      `$this$readBuffer`.getReadBuffer().readTo(result, var11);
      var10 -= (int)var11;
   }

   return result;
}

public suspend fun ByteReadChannel.copyAndClose(channel: ByteWriteChannel): Long {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
   //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
   //
   // Bytecode:
   // 000: aload 2
   // 001: instanceof io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1
   // 004: ifeq 027
   // 007: aload 2
   // 008: checkcast io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1
   // 00b: astore 9
   // 00d: aload 9
   // 00f: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.label I
   // 012: ldc -2147483648
   // 014: iand
   // 015: ifeq 027
   // 018: aload 9
   // 01a: dup
   // 01b: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.label I
   // 01e: ldc -2147483648
   // 020: isub
   // 021: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.label I
   // 024: goto 031
   // 027: new io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1
   // 02a: dup
   // 02b: aload 2
   // 02c: invokespecial io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.<init> (Lkotlin/coroutines/Continuation;)V
   // 02f: astore 9
   // 031: aload 9
   // 033: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.result Ljava/lang/Object;
   // 036: astore 8
   // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03b: astore 10
   // 03d: aload 9
   // 03f: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.label I
   // 042: tableswitch 452 0 4 34 115 190 292 402
   // 064: aload 8
   // 066: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 069: lconst_0
   // 06a: lstore 3
   // 06b: nop
   // 06c: aload 0
   // 06d: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 072: ifne 124
   // 075: lload 3
   // 076: aload 0
   // 077: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 07c: aload 1
   // 07d: invokeinterface io/ktor/utils/io/ByteWriteChannel.getWriteBuffer ()Lkotlinx/io/Sink; 1
   // 082: checkcast kotlinx/io/RawSink
   // 085: invokeinterface kotlinx/io/Source.transferTo (Lkotlinx/io/RawSink;)J 2
   // 08a: ladd
   // 08b: lstore 3
   // 08c: aload 1
   // 08d: aload 9
   // 08f: aload 9
   // 091: aload 0
   // 092: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$0 Ljava/lang/Object;
   // 095: aload 9
   // 097: aload 1
   // 098: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$1 Ljava/lang/Object;
   // 09b: aload 9
   // 09d: lload 3
   // 09e: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.J$0 J
   // 0a1: aload 9
   // 0a3: bipush 1
   // 0a4: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.label I
   // 0a7: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 0ac: dup
   // 0ad: aload 10
   // 0af: if_acmpne 0d5
   // 0b2: aload 10
   // 0b4: areturn
   // 0b5: aload 9
   // 0b7: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.J$0 J
   // 0ba: lstore 3
   // 0bb: aload 9
   // 0bd: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$1 Ljava/lang/Object;
   // 0c0: checkcast io/ktor/utils/io/ByteWriteChannel
   // 0c3: astore 1
   // 0c4: aload 9
   // 0c6: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$0 Ljava/lang/Object;
   // 0c9: checkcast io/ktor/utils/io/ByteReadChannel
   // 0cc: astore 0
   // 0cd: nop
   // 0ce: aload 8
   // 0d0: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 0d3: aload 8
   // 0d5: pop
   // 0d6: aload 0
   // 0d7: bipush 0
   // 0d8: aload 9
   // 0da: bipush 1
   // 0db: aconst_null
   // 0dc: aload 9
   // 0de: aload 0
   // 0df: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$0 Ljava/lang/Object;
   // 0e2: aload 9
   // 0e4: aload 1
   // 0e5: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$1 Ljava/lang/Object;
   // 0e8: aload 9
   // 0ea: lload 3
   // 0eb: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.J$0 J
   // 0ee: aload 9
   // 0f0: bipush 2
   // 0f1: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.label I
   // 0f4: invokestatic io/ktor/utils/io/ByteReadChannel.awaitContent$default (Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 0f7: dup
   // 0f8: aload 10
   // 0fa: if_acmpne 120
   // 0fd: aload 10
   // 0ff: areturn
   // 100: aload 9
   // 102: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.J$0 J
   // 105: lstore 3
   // 106: aload 9
   // 108: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$1 Ljava/lang/Object;
   // 10b: checkcast io/ktor/utils/io/ByteWriteChannel
   // 10e: astore 1
   // 10f: aload 9
   // 111: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$0 Ljava/lang/Object;
   // 114: checkcast io/ktor/utils/io/ByteReadChannel
   // 117: astore 0
   // 118: nop
   // 119: aload 8
   // 11b: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 11e: aload 8
   // 120: pop
   // 121: goto 06c
   // 124: aload 0
   // 125: invokeinterface io/ktor/utils/io/ByteReadChannel.getClosedCause ()Ljava/lang/Throwable; 1
   // 12a: dup
   // 12b: ifnull 136
   // 12e: astore 6
   // 130: bipush 0
   // 131: istore 7
   // 133: aload 6
   // 135: athrow
   // 136: pop
   // 137: aload 1
   // 138: aload 9
   // 13a: aload 9
   // 13c: aload 0
   // 13d: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 140: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$0 Ljava/lang/Object;
   // 143: aload 9
   // 145: aload 1
   // 146: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 149: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$1 Ljava/lang/Object;
   // 14c: aload 9
   // 14e: lload 3
   // 14f: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.J$0 J
   // 152: aload 9
   // 154: bipush 3
   // 155: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.label I
   // 158: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 15d: dup
   // 15e: aload 10
   // 160: if_acmpne 185
   // 163: aload 10
   // 165: areturn
   // 166: aload 9
   // 168: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.J$0 J
   // 16b: lstore 3
   // 16c: aload 9
   // 16e: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$1 Ljava/lang/Object;
   // 171: checkcast io/ktor/utils/io/ByteWriteChannel
   // 174: astore 1
   // 175: aload 9
   // 177: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$0 Ljava/lang/Object;
   // 17a: checkcast io/ktor/utils/io/ByteReadChannel
   // 17d: astore 0
   // 17e: aload 8
   // 180: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 183: aload 8
   // 185: pop
   // 186: goto 201
   // 189: astore 5
   // 18b: aload 0
   // 18c: aload 5
   // 18e: invokeinterface io/ktor/utils/io/ByteReadChannel.cancel (Ljava/lang/Throwable;)V 2
   // 193: aload 1
   // 194: aload 5
   // 196: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.close (Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Throwable;)V
   // 199: aload 5
   // 19b: athrow
   // 19c: astore 5
   // 19e: aload 1
   // 19f: aload 9
   // 1a1: aload 9
   // 1a3: aload 0
   // 1a4: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1a7: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$0 Ljava/lang/Object;
   // 1aa: aload 9
   // 1ac: aload 1
   // 1ad: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1b0: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$1 Ljava/lang/Object;
   // 1b3: aload 9
   // 1b5: aload 5
   // 1b7: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$2 Ljava/lang/Object;
   // 1ba: aload 9
   // 1bc: lload 3
   // 1bd: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.J$0 J
   // 1c0: aload 9
   // 1c2: bipush 4
   // 1c3: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.label I
   // 1c6: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 1cb: dup
   // 1cc: aload 10
   // 1ce: if_acmpne 1fd
   // 1d1: aload 10
   // 1d3: areturn
   // 1d4: aload 9
   // 1d6: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.J$0 J
   // 1d9: lstore 3
   // 1da: aload 9
   // 1dc: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$2 Ljava/lang/Object;
   // 1df: checkcast java/lang/Throwable
   // 1e2: astore 5
   // 1e4: aload 9
   // 1e6: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$1 Ljava/lang/Object;
   // 1e9: checkcast io/ktor/utils/io/ByteWriteChannel
   // 1ec: astore 1
   // 1ed: aload 9
   // 1ef: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyAndClose$1.L$0 Ljava/lang/Object;
   // 1f2: checkcast io/ktor/utils/io/ByteReadChannel
   // 1f5: astore 0
   // 1f6: aload 8
   // 1f8: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 1fb: aload 8
   // 1fd: pop
   // 1fe: aload 5
   // 200: athrow
   // 201: lload 3
   // 202: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxLong (J)Ljava/lang/Long;
   // 205: areturn
   // 206: new java/lang/IllegalStateException
   // 209: dup
   // 20a: ldc "call to 'resume' before 'invoke' with coroutine"
   // 20c: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 20f: athrow
}

public suspend fun ByteReadChannel.readUTF8Line(max: Int = ...): String? {
   var `$continuation`: Continuation;
   label25: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readUTF8Line.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readUTF8Line.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readUTF8Line.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label25;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readUTF8Line.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var result: StringBuilder;
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         result = new StringBuilder();
         val var10001: Appendable = result;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$readUTF8Line`);
         `$continuation`.L$1 = result;
         `$continuation`.I$0 = max;
         `$continuation`.label = 1;
         var10000 = readUTF8LineTo(`$this$readUTF8Line`, var10001, max, `$continuation`);
         if (var10000 === var7) {
            return var7;
         }
         break;
      case 1:
         max = `$continuation`.I$0;
         result = `$continuation`.L$1 as StringBuilder;
         `$this$readUTF8Line` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return if (!var10000 as java.lang.Boolean) null else result.toString();
}

@JvmSynthetic
fun `readUTF8Line$default`(var0: ByteReadChannel, var1: Int, var2: Continuation, var3: Int, var4: Any): Any {
   if ((var3 and 1) != 0) {
      var1 = Integer.MAX_VALUE;
   }

   return readUTF8Line(var0, var1, var2);
}

public suspend fun ByteReadChannel.copyTo(channel: ByteWriteChannel): Long {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
   //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
   //
   // Bytecode:
   // 000: aload 2
   // 001: instanceof io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1
   // 004: ifeq 027
   // 007: aload 2
   // 008: checkcast io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1
   // 00b: astore 7
   // 00d: aload 7
   // 00f: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.label I
   // 012: ldc -2147483648
   // 014: iand
   // 015: ifeq 027
   // 018: aload 7
   // 01a: dup
   // 01b: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.label I
   // 01e: ldc -2147483648
   // 020: isub
   // 021: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.label I
   // 024: goto 031
   // 027: new io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1
   // 02a: dup
   // 02b: aload 2
   // 02c: invokespecial io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.<init> (Lkotlin/coroutines/Continuation;)V
   // 02f: astore 7
   // 031: aload 7
   // 033: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.result Ljava/lang/Object;
   // 036: astore 6
   // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03b: astore 8
   // 03d: aload 7
   // 03f: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.label I
   // 042: tableswitch 433 0 4 34 115 190 273 383
   // 064: aload 6
   // 066: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 069: lconst_0
   // 06a: lstore 3
   // 06b: nop
   // 06c: aload 0
   // 06d: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 072: ifne 124
   // 075: lload 3
   // 076: aload 0
   // 077: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 07c: aload 1
   // 07d: invokeinterface io/ktor/utils/io/ByteWriteChannel.getWriteBuffer ()Lkotlinx/io/Sink; 1
   // 082: checkcast kotlinx/io/RawSink
   // 085: invokeinterface kotlinx/io/Source.transferTo (Lkotlinx/io/RawSink;)J 2
   // 08a: ladd
   // 08b: lstore 3
   // 08c: aload 1
   // 08d: aload 7
   // 08f: aload 7
   // 091: aload 0
   // 092: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$0 Ljava/lang/Object;
   // 095: aload 7
   // 097: aload 1
   // 098: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$1 Ljava/lang/Object;
   // 09b: aload 7
   // 09d: lload 3
   // 09e: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.J$0 J
   // 0a1: aload 7
   // 0a3: bipush 1
   // 0a4: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.label I
   // 0a7: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 0ac: dup
   // 0ad: aload 8
   // 0af: if_acmpne 0d5
   // 0b2: aload 8
   // 0b4: areturn
   // 0b5: aload 7
   // 0b7: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.J$0 J
   // 0ba: lstore 3
   // 0bb: aload 7
   // 0bd: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$1 Ljava/lang/Object;
   // 0c0: checkcast io/ktor/utils/io/ByteWriteChannel
   // 0c3: astore 1
   // 0c4: aload 7
   // 0c6: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$0 Ljava/lang/Object;
   // 0c9: checkcast io/ktor/utils/io/ByteReadChannel
   // 0cc: astore 0
   // 0cd: nop
   // 0ce: aload 6
   // 0d0: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 0d3: aload 6
   // 0d5: pop
   // 0d6: aload 0
   // 0d7: bipush 0
   // 0d8: aload 7
   // 0da: bipush 1
   // 0db: aconst_null
   // 0dc: aload 7
   // 0de: aload 0
   // 0df: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$0 Ljava/lang/Object;
   // 0e2: aload 7
   // 0e4: aload 1
   // 0e5: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$1 Ljava/lang/Object;
   // 0e8: aload 7
   // 0ea: lload 3
   // 0eb: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.J$0 J
   // 0ee: aload 7
   // 0f0: bipush 2
   // 0f1: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.label I
   // 0f4: invokestatic io/ktor/utils/io/ByteReadChannel.awaitContent$default (Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 0f7: dup
   // 0f8: aload 8
   // 0fa: if_acmpne 120
   // 0fd: aload 8
   // 0ff: areturn
   // 100: aload 7
   // 102: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.J$0 J
   // 105: lstore 3
   // 106: aload 7
   // 108: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$1 Ljava/lang/Object;
   // 10b: checkcast io/ktor/utils/io/ByteWriteChannel
   // 10e: astore 1
   // 10f: aload 7
   // 111: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$0 Ljava/lang/Object;
   // 114: checkcast io/ktor/utils/io/ByteReadChannel
   // 117: astore 0
   // 118: nop
   // 119: aload 6
   // 11b: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 11e: aload 6
   // 120: pop
   // 121: goto 06c
   // 124: aload 1
   // 125: aload 7
   // 127: aload 7
   // 129: aload 0
   // 12a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 12d: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$0 Ljava/lang/Object;
   // 130: aload 7
   // 132: aload 1
   // 133: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 136: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$1 Ljava/lang/Object;
   // 139: aload 7
   // 13b: lload 3
   // 13c: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.J$0 J
   // 13f: aload 7
   // 141: bipush 3
   // 142: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.label I
   // 145: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 14a: dup
   // 14b: aload 8
   // 14d: if_acmpne 172
   // 150: aload 8
   // 152: areturn
   // 153: aload 7
   // 155: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.J$0 J
   // 158: lstore 3
   // 159: aload 7
   // 15b: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$1 Ljava/lang/Object;
   // 15e: checkcast io/ktor/utils/io/ByteWriteChannel
   // 161: astore 1
   // 162: aload 7
   // 164: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$0 Ljava/lang/Object;
   // 167: checkcast io/ktor/utils/io/ByteReadChannel
   // 16a: astore 0
   // 16b: aload 6
   // 16d: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 170: aload 6
   // 172: pop
   // 173: goto 1ee
   // 176: astore 5
   // 178: aload 0
   // 179: aload 5
   // 17b: invokeinterface io/ktor/utils/io/ByteReadChannel.cancel (Ljava/lang/Throwable;)V 2
   // 180: aload 1
   // 181: aload 5
   // 183: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.close (Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Throwable;)V
   // 186: aload 5
   // 188: athrow
   // 189: astore 5
   // 18b: aload 1
   // 18c: aload 7
   // 18e: aload 7
   // 190: aload 0
   // 191: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 194: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$0 Ljava/lang/Object;
   // 197: aload 7
   // 199: aload 1
   // 19a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 19d: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$1 Ljava/lang/Object;
   // 1a0: aload 7
   // 1a2: aload 5
   // 1a4: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$2 Ljava/lang/Object;
   // 1a7: aload 7
   // 1a9: lload 3
   // 1aa: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.J$0 J
   // 1ad: aload 7
   // 1af: bipush 4
   // 1b0: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.label I
   // 1b3: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 1b8: dup
   // 1b9: aload 8
   // 1bb: if_acmpne 1ea
   // 1be: aload 8
   // 1c0: areturn
   // 1c1: aload 7
   // 1c3: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.J$0 J
   // 1c6: lstore 3
   // 1c7: aload 7
   // 1c9: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$2 Ljava/lang/Object;
   // 1cc: checkcast java/lang/Throwable
   // 1cf: astore 5
   // 1d1: aload 7
   // 1d3: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$1 Ljava/lang/Object;
   // 1d6: checkcast io/ktor/utils/io/ByteWriteChannel
   // 1d9: astore 1
   // 1da: aload 7
   // 1dc: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$1.L$0 Ljava/lang/Object;
   // 1df: checkcast io/ktor/utils/io/ByteReadChannel
   // 1e2: astore 0
   // 1e3: aload 6
   // 1e5: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 1e8: aload 6
   // 1ea: pop
   // 1eb: aload 5
   // 1ed: athrow
   // 1ee: lload 3
   // 1ef: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxLong (J)Ljava/lang/Long;
   // 1f2: areturn
   // 1f3: new java/lang/IllegalStateException
   // 1f6: dup
   // 1f7: ldc "call to 'resume' before 'invoke' with coroutine"
   // 1f9: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 1fc: athrow
}

public suspend fun ByteReadChannel.copyTo(channel: ByteWriteChannel, limit: Long): Long {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
   //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
   //
   // Bytecode:
   // 000: aload 4
   // 002: instanceof io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2
   // 005: ifeq 029
   // 008: aload 4
   // 00a: checkcast io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2
   // 00d: astore 10
   // 00f: aload 10
   // 011: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.label I
   // 014: ldc -2147483648
   // 016: iand
   // 017: ifeq 029
   // 01a: aload 10
   // 01c: dup
   // 01d: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.label I
   // 020: ldc -2147483648
   // 022: isub
   // 023: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.label I
   // 026: goto 034
   // 029: new io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2
   // 02c: dup
   // 02d: aload 4
   // 02f: invokespecial io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.<init> (Lkotlin/coroutines/Continuation;)V
   // 032: astore 10
   // 034: aload 10
   // 036: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.result Ljava/lang/Object;
   // 039: astore 9
   // 03b: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03e: astore 11
   // 040: aload 10
   // 042: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.label I
   // 045: tableswitch 551 0 4 35 123 263 367 491
   // 068: aload 9
   // 06a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 06d: lload 2
   // 06e: lstore 5
   // 070: nop
   // 071: aload 0
   // 072: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 077: ifne 17e
   // 07a: lload 5
   // 07c: lconst_0
   // 07d: lcmp
   // 07e: ifle 17e
   // 081: aload 0
   // 082: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 087: invokeinterface kotlinx/io/Source.exhausted ()Z 1
   // 08c: ifeq 0e8
   // 08f: aload 0
   // 090: bipush 0
   // 091: aload 10
   // 093: bipush 1
   // 094: aconst_null
   // 095: aload 10
   // 097: aload 0
   // 098: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$0 Ljava/lang/Object;
   // 09b: aload 10
   // 09d: aload 1
   // 09e: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$1 Ljava/lang/Object;
   // 0a1: aload 10
   // 0a3: lload 2
   // 0a4: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$0 J
   // 0a7: aload 10
   // 0a9: lload 5
   // 0ab: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$1 J
   // 0ae: aload 10
   // 0b0: bipush 1
   // 0b1: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.label I
   // 0b4: invokestatic io/ktor/utils/io/ByteReadChannel.awaitContent$default (Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 0b7: dup
   // 0b8: aload 11
   // 0ba: if_acmpne 0e7
   // 0bd: aload 11
   // 0bf: areturn
   // 0c0: aload 10
   // 0c2: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$1 J
   // 0c5: lstore 5
   // 0c7: aload 10
   // 0c9: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$0 J
   // 0cc: lstore 2
   // 0cd: aload 10
   // 0cf: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$1 Ljava/lang/Object;
   // 0d2: checkcast io/ktor/utils/io/ByteWriteChannel
   // 0d5: astore 1
   // 0d6: aload 10
   // 0d8: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$0 Ljava/lang/Object;
   // 0db: checkcast io/ktor/utils/io/ByteReadChannel
   // 0de: astore 0
   // 0df: nop
   // 0e0: aload 9
   // 0e2: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 0e5: aload 9
   // 0e7: pop
   // 0e8: lload 5
   // 0ea: aload 0
   // 0eb: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 0f0: invokestatic io/ktor/utils/io/core/ByteReadPacketKt.getRemaining (Lkotlinx/io/Source;)J
   // 0f3: invokestatic java/lang/Math.min (JJ)J
   // 0f6: lstore 7
   // 0f8: aload 0
   // 0f9: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 0fe: aload 1
   // 0ff: invokeinterface io/ktor/utils/io/ByteWriteChannel.getWriteBuffer ()Lkotlinx/io/Sink; 1
   // 104: checkcast kotlinx/io/RawSink
   // 107: lload 7
   // 109: invokeinterface kotlinx/io/Source.readTo (Lkotlinx/io/RawSink;J)V 4
   // 10e: lload 5
   // 110: lload 7
   // 112: lsub
   // 113: lstore 5
   // 115: aload 1
   // 116: aload 10
   // 118: aload 10
   // 11a: aload 0
   // 11b: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$0 Ljava/lang/Object;
   // 11e: aload 10
   // 120: aload 1
   // 121: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$1 Ljava/lang/Object;
   // 124: aload 10
   // 126: lload 2
   // 127: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$0 J
   // 12a: aload 10
   // 12c: lload 5
   // 12e: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$1 J
   // 131: aload 10
   // 133: lload 7
   // 135: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$2 J
   // 138: aload 10
   // 13a: bipush 2
   // 13b: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.label I
   // 13e: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 143: dup
   // 144: aload 11
   // 146: if_acmpne 17a
   // 149: aload 11
   // 14b: areturn
   // 14c: aload 10
   // 14e: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$2 J
   // 151: lstore 7
   // 153: aload 10
   // 155: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$1 J
   // 158: lstore 5
   // 15a: aload 10
   // 15c: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$0 J
   // 15f: lstore 2
   // 160: aload 10
   // 162: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$1 Ljava/lang/Object;
   // 165: checkcast io/ktor/utils/io/ByteWriteChannel
   // 168: astore 1
   // 169: aload 10
   // 16b: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$0 Ljava/lang/Object;
   // 16e: checkcast io/ktor/utils/io/ByteReadChannel
   // 171: astore 0
   // 172: nop
   // 173: aload 9
   // 175: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 178: aload 9
   // 17a: pop
   // 17b: goto 071
   // 17e: aload 1
   // 17f: aload 10
   // 181: aload 10
   // 183: aload 0
   // 184: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 187: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$0 Ljava/lang/Object;
   // 18a: aload 10
   // 18c: aload 1
   // 18d: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 190: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$1 Ljava/lang/Object;
   // 193: aload 10
   // 195: lload 2
   // 196: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$0 J
   // 199: aload 10
   // 19b: lload 5
   // 19d: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$1 J
   // 1a0: aload 10
   // 1a2: bipush 3
   // 1a3: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.label I
   // 1a6: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 1ab: dup
   // 1ac: aload 11
   // 1ae: if_acmpne 1da
   // 1b1: aload 11
   // 1b3: areturn
   // 1b4: aload 10
   // 1b6: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$1 J
   // 1b9: lstore 5
   // 1bb: aload 10
   // 1bd: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$0 J
   // 1c0: lstore 2
   // 1c1: aload 10
   // 1c3: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$1 Ljava/lang/Object;
   // 1c6: checkcast io/ktor/utils/io/ByteWriteChannel
   // 1c9: astore 1
   // 1ca: aload 10
   // 1cc: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$0 Ljava/lang/Object;
   // 1cf: checkcast io/ktor/utils/io/ByteReadChannel
   // 1d2: astore 0
   // 1d3: aload 9
   // 1d5: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 1d8: aload 9
   // 1da: pop
   // 1db: goto 264
   // 1de: astore 7
   // 1e0: aload 0
   // 1e1: aload 7
   // 1e3: invokeinterface io/ktor/utils/io/ByteReadChannel.cancel (Ljava/lang/Throwable;)V 2
   // 1e8: aload 1
   // 1e9: aload 7
   // 1eb: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.close (Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Throwable;)V
   // 1ee: aload 7
   // 1f0: athrow
   // 1f1: astore 7
   // 1f3: aload 1
   // 1f4: aload 10
   // 1f6: aload 10
   // 1f8: aload 0
   // 1f9: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1fc: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$0 Ljava/lang/Object;
   // 1ff: aload 10
   // 201: aload 1
   // 202: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 205: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$1 Ljava/lang/Object;
   // 208: aload 10
   // 20a: aload 7
   // 20c: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$2 Ljava/lang/Object;
   // 20f: aload 10
   // 211: lload 2
   // 212: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$0 J
   // 215: aload 10
   // 217: lload 5
   // 219: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$1 J
   // 21c: aload 10
   // 21e: bipush 4
   // 21f: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.label I
   // 222: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 227: dup
   // 228: aload 11
   // 22a: if_acmpne 260
   // 22d: aload 11
   // 22f: areturn
   // 230: aload 10
   // 232: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$1 J
   // 235: lstore 5
   // 237: aload 10
   // 239: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.J$0 J
   // 23c: lstore 2
   // 23d: aload 10
   // 23f: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$2 Ljava/lang/Object;
   // 242: checkcast java/lang/Throwable
   // 245: astore 7
   // 247: aload 10
   // 249: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$1 Ljava/lang/Object;
   // 24c: checkcast io/ktor/utils/io/ByteWriteChannel
   // 24f: astore 1
   // 250: aload 10
   // 252: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$copyTo$2.L$0 Ljava/lang/Object;
   // 255: checkcast io/ktor/utils/io/ByteReadChannel
   // 258: astore 0
   // 259: aload 9
   // 25b: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 25e: aload 9
   // 260: pop
   // 261: aload 7
   // 263: athrow
   // 264: lload 2
   // 265: lload 5
   // 267: lsub
   // 268: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxLong (J)Ljava/lang/Long;
   // 26b: areturn
   // 26c: new java/lang/IllegalStateException
   // 26f: dup
   // 270: ldc "call to 'resume' before 'invoke' with coroutine"
   // 272: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 275: athrow
}

public suspend fun ByteReadChannel.readByteArray(count: Int): ByteArray {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readByteArray.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readByteArray.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readByteArray.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readByteArray.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var `$i$f$buildPacket`: Int;
   var `builder$iv`: Buffer;
   var `$this$readByteArray_u24lambda_u240`: Sink;
   var var6: Int;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$i$f$buildPacket` = 0;
         `builder$iv` = new Buffer();
         `$this$readByteArray_u24lambda_u240` = `builder$iv`;
         var6 = 0;
         break;
      case 1:
         var6 = `$continuation`.I$2;
         `$i$f$buildPacket` = `$continuation`.I$1;
         count = `$continuation`.I$0;
         `$this$readByteArray_u24lambda_u240` = `$continuation`.L$2 as Sink;
         `builder$iv` = `$continuation`.L$1 as Buffer;
         `$this$readByteArray` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         BytePacketBuilderKt.writePacket(`$this$readByteArray_u24lambda_u240`, `$result` as Source);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (BytePacketBuilderKt.getSize($this$readByteArray_u24lambda_u240) < count) {
      val var10001: Int = count - BytePacketBuilderKt.getSize(`$this$readByteArray_u24lambda_u240`);
      `$continuation`.L$0 = `$this$readByteArray`;
      `$continuation`.L$1 = `builder$iv`;
      `$continuation`.L$2 = `$this$readByteArray_u24lambda_u240`;
      `$continuation`.I$0 = count;
      `$continuation`.I$1 = `$i$f$buildPacket`;
      `$continuation`.I$2 = var6;
      `$continuation`.label = 1;
      val var10000: Any = readPacket(`$this$readByteArray`, var10001, `$continuation`);
      if (var10000 === var10) {
         return var10;
      }

      BytePacketBuilderKt.writePacket(`$this$readByteArray_u24lambda_u240`, var10000 as Source);
   }

   return SourcesKt.readByteArray(`builder$iv`);
}

public suspend fun ByteReadChannel.readRemaining(): Source {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readRemaining.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readRemaining.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readRemaining.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readRemaining.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var result: Sink;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         result = BytePacketBuilderKt.BytePacketBuilder();
         break;
      case 1:
         result = `$continuation`.L$1 as Sink;
         `$this$readRemaining` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (!$this$readRemaining.isClosedForRead()) {
      result.transferFrom(`$this$readRemaining`.getReadBuffer());
      `$continuation`.L$0 = `$this$readRemaining`;
      `$continuation`.L$1 = result;
      `$continuation`.label = 1;
      if (ByteReadChannel.awaitContent$default(`$this$readRemaining`, 0, `$continuation`, 1, null) === var5) {
         return var5;
      }
   }

   rethrowCloseCauseIfNeeded(`$this$readRemaining`);
   return result.getBuffer();
}

public suspend fun ByteReadChannel.readRemaining(max: Long): Source {
   var `$continuation`: Continuation;
   label43: {
      if (`$completion` is 2) {
         `$continuation` = `$completion` as 2;
         if (((`$completion` as 2).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label43;
         }
      }

      `$continuation` = new 2(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var result: Sink;
   var remaining: Long;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         result = BytePacketBuilderKt.BytePacketBuilder();
         remaining = max;
         break;
      case 1:
         remaining = `$continuation`.J$1;
         max = `$continuation`.J$0;
         result = `$continuation`.L$1 as Sink;
         `$this$readRemaining` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   do {
      if (`$this$readRemaining`.isClosedForRead() || remaining <= 0L) {
         return result.getBuffer();
      }

      if (remaining >= ByteReadPacketKt.getRemaining(`$this$readRemaining`.getReadBuffer())) {
         remaining -= ByteReadPacketKt.getRemaining(`$this$readRemaining`.getReadBuffer());
         Boxing.boxLong(`$this$readRemaining`.getReadBuffer().transferTo(result));
      } else {
         `$this$readRemaining`.getReadBuffer().readTo(result, remaining);
         remaining = 0L;
      }

      `$continuation`.L$0 = `$this$readRemaining`;
      `$continuation`.L$1 = result;
      `$continuation`.J$0 = max;
      `$continuation`.J$1 = remaining;
      `$continuation`.label = 1;
   } while (ByteReadChannel.awaitContent$default($this$readRemaining, 0, $continuation, 1, null) != var9);

   return var9;
}

public suspend fun ByteReadChannel.readAvailable(buffer: ByteArray, offset: Int = ..., length: Int = ...): Int {
   var `$continuation`: Continuation;
   label38: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readAvailable.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readAvailable.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readAvailable.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label38;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readAvailable.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (`$this$readAvailable`.isClosedForRead()) {
            return Boxing.boxInt(-1);
         }

         if (`$this$readAvailable`.getReadBuffer().exhausted()) {
            `$continuation`.L$0 = `$this$readAvailable`;
            `$continuation`.L$1 = buffer;
            `$continuation`.I$0 = offset;
            `$continuation`.I$1 = length;
            `$continuation`.label = 1;
            if (ByteReadChannel.awaitContent$default(`$this$readAvailable`, 0, `$continuation`, 1, null) === var7) {
               return var7;
            }
         }
         break;
      case 1:
         length = `$continuation`.I$1;
         offset = `$continuation`.I$0;
         buffer = `$continuation`.L$1 as ByteArray;
         `$this$readAvailable` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return if (`$this$readAvailable`.isClosedForRead())
      Boxing.boxInt(-1)
      else
      Boxing.boxInt(InputKt.readAvailable(`$this$readAvailable`.getReadBuffer(), buffer, offset, length));
}

@JvmSynthetic
fun `readAvailable$default`(var0: ByteReadChannel, var1: ByteArray, var2: Int, var3: Int, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = 0;
   }

   if ((var5 and 4) != 0) {
      var3 = var1.length - var2;
   }

   return readAvailable(var0, var1, var2, var3, var4);
}

public fun ByteReadChannel.readAvailable(min: Int, block: (Buffer) -> Int): Int {
   if (min <= 0) {
      throw new IllegalArgumentException("min should be positive".toString());
   } else if (min > 1048576) {
      throw new IllegalArgumentException(("Min($min) shouldn't be greater than 1048576").toString());
   } else {
      return if (getAvailableForRead(`$this$readAvailable`) < min)
         -1
         else
         (block.invoke(`$this$readAvailable`.getReadBuffer().getBuffer()) as java.lang.Number).intValue();
   }
}

public fun CoroutineScope.reader(
   coroutineContext: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   autoFlush: Boolean = false,
   block: (ReaderScope, Continuation<Unit>) -> Any?
): ReaderJob {
   return reader(`$this$reader`, coroutineContext, new ByteChannel(false, 1, null), block);
}

@JvmSynthetic
fun `reader$default`(var0: CoroutineScope, var1: CoroutineContext, var2: Boolean, var3: Function2, var4: Int, var5: Any): ReaderJob {
   if ((var4 and 1) != 0) {
      var1 = EmptyCoroutineContext.INSTANCE;
   }

   if ((var4 and 2) != 0) {
      var2 = false;
   }

   return reader(var0, var1, var2, var3);
}

public fun CoroutineScope.reader(coroutineContext: CoroutineContext, channel: ByteChannel, block: (ReaderScope, Continuation<Unit>) -> Any?): ReaderJob {
   val var5: Job = BuildersKt.launch$default(
      `$this$reader`, coroutineContext, null, new io.ktor.utils.io.ByteReadChannelOperationsKt.reader.job.1(block, channel, null), 2, null
   );
   var5.invokeOnCompletion(ByteReadChannelOperationsKt::reader$lambda$0$0);
   return new ReaderJob(CloseHookByteWriteChannelKt.onClose(channel, new io.ktor.utils.io.ByteReadChannelOperationsKt.reader.1(var5, null)), var5);
}

public suspend fun ByteReadChannel.readPacket(packet: Int): Source {
   var `$continuation`: Continuation;
   label57: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readPacket.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readPacket.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readPacket.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label57;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readPacket.1(`$completion`);
   }

   var result: Buffer;
   label51: {
      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            result = new Buffer();
            break;
         case 1:
            packet = `$continuation`.I$0;
            result = `$continuation`.L$1 as Buffer;
            `$this$readPacket` = `$continuation`.L$0 as ByteReadChannel;
            ResultKt.throwOnFailure(`$result`);
            if (`$this$readPacket`.isClosedForRead()) {
               break label51;
            }

            if (ByteReadPacketKt.getRemaining(`$this$readPacket`.getReadBuffer()) > packet - result.getSize()) {
               `$this$readPacket`.getReadBuffer().readTo(result, (long)packet - result.getSize());
            } else {
               Boxing.boxLong(`$this$readPacket`.getReadBuffer().transferTo(result));
            }
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (result.getSize() < packet) {
         if (`$this$readPacket`.getReadBuffer().exhausted()) {
            `$continuation`.L$0 = `$this$readPacket`;
            `$continuation`.L$1 = result;
            `$continuation`.I$0 = packet;
            `$continuation`.label = 1;
            if (ByteReadChannel.awaitContent$default(`$this$readPacket`, 0, `$continuation`, 1, null) === var6) {
               return var6;
            }
         }

         if (`$this$readPacket`.isClosedForRead()) {
            break;
         }

         if (ByteReadPacketKt.getRemaining(`$this$readPacket`.getReadBuffer()) > packet - result.getSize()) {
            `$this$readPacket`.getReadBuffer().readTo(result, (long)packet - result.getSize());
         } else {
            Boxing.boxLong(`$this$readPacket`.getReadBuffer().transferTo(result));
         }
      }
   }

   if (result.getSize() < packet) {
      throw new EOFException("Not enough data available, required $packet bytes but only ${result.getSize()} available");
   } else {
      return result;
   }
}

public suspend fun ByteReadChannel.discardExact(value: Long) {
   var `$continuation`: Continuation;
   label24: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.discardExact.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.discardExact.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.discardExact.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label24;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.discardExact.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$discardExact`);
         `$continuation`.J$0 = value;
         `$continuation`.label = 1;
         var10000 = discard(`$this$discardExact`, value, `$continuation`);
         if (var10000 === var6) {
            return var6;
         }
         break;
      case 1:
         value = `$continuation`.J$0;
         `$this$discardExact` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if ((var10000 as java.lang.Number).longValue() < value) {
      throw new EOFException("Unable to discard $value bytes");
   } else {
      return Unit.INSTANCE;
   }
}

public suspend fun ByteReadChannel.discard(max: Long = ...): Long {
   var `$continuation`: Continuation;
   label44: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.discard.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.discard.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.discard.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label44;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.discard.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var11: Long;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var11 = max;
         break;
      case 1:
         var11 = `$continuation`.J$1;
         max = `$continuation`.J$0;
         `$this$discard` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         val count: Long = Math.min(var11, ByteReadPacketKt.getRemaining(`$this$discard`.getReadBuffer()));
         ByteReadPacketKt.discard(`$this$discard`.getReadBuffer(), count);
         var11 = var11 - count;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (remaining > 0L && !$this$discard.isClosedForRead()) {
      if (getAvailableForRead(`$this$discard`) == 0) {
         `$continuation`.L$0 = `$this$discard`;
         `$continuation`.J$0 = max;
         `$continuation`.J$1 = var11;
         `$continuation`.label = 1;
         if (ByteReadChannel.awaitContent$default(`$this$discard`, 0, `$continuation`, 1, null) === var10) {
            return var10;
         }
      }

      val var12: Long = Math.min(var11, ByteReadPacketKt.getRemaining(`$this$discard`.getReadBuffer()));
      ByteReadPacketKt.discard(`$this$discard`.getReadBuffer(), var12);
      var11 -= var12;
   }

   return Boxing.boxLong(max - var11);
}

@JvmSynthetic
fun `discard$default`(var0: ByteReadChannel, var1: Long, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 1) != 0) {
      var1 = java.lang.Long.MAX_VALUE;
   }

   return discard(var0, var1, var3);
}

public suspend fun ByteReadChannel.readUTF8LineTo(out: Appendable, max: Int = ...): Boolean {
   return readUTF8LineTo-RRvyBJ8(`$this$readUTF8LineTo`, out, max, LineEndingMode.Companion.getAny-f0jXZW8(), `$completion`);
}

@JvmSynthetic
fun `readUTF8LineTo$default`(var0: ByteReadChannel, var1: Appendable, var2: Int, var3: Continuation, var4: Int, var5: Any): Any {
   if ((var4 and 2) != 0) {
      var2 = Integer.MAX_VALUE;
   }

   return readUTF8LineTo(var0, var1, var2, var3);
}

@InternalAPI
public suspend fun ByteReadChannel.readUTF8LineTo(out: Appendable, max: Int = ..., lineEnding: LineEndingMode = ...): Boolean {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
   //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
   //
   // Bytecode:
   // 000: aload 4
   // 002: instanceof io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2
   // 005: ifeq 029
   // 008: aload 4
   // 00a: checkcast io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2
   // 00d: astore 16
   // 00f: aload 16
   // 011: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.label I
   // 014: ldc -2147483648
   // 016: iand
   // 017: ifeq 029
   // 01a: aload 16
   // 01c: dup
   // 01d: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.label I
   // 020: ldc -2147483648
   // 022: isub
   // 023: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.label I
   // 026: goto 034
   // 029: new io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2
   // 02c: dup
   // 02d: aload 4
   // 02f: invokespecial io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.<init> (Lkotlin/coroutines/Continuation;)V
   // 032: astore 16
   // 034: aload 16
   // 036: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.result Ljava/lang/Object;
   // 039: astore 15
   // 03b: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03e: astore 17
   // 040: aload 16
   // 042: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.label I
   // 045: tableswitch 804 0 3 31 98 309 646
   // 064: aload 15
   // 066: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 069: aload 0
   // 06a: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 06f: invokeinterface kotlinx/io/Source.exhausted ()Z 1
   // 074: ifeq 0cd
   // 077: aload 0
   // 078: bipush 0
   // 079: aload 16
   // 07b: bipush 1
   // 07c: aconst_null
   // 07d: aload 16
   // 07f: aload 0
   // 080: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$0 Ljava/lang/Object;
   // 083: aload 16
   // 085: aload 1
   // 086: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$1 Ljava/lang/Object;
   // 089: aload 16
   // 08b: iload 2
   // 08c: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$0 I
   // 08f: aload 16
   // 091: iload 3
   // 092: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$1 I
   // 095: aload 16
   // 097: bipush 1
   // 098: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.label I
   // 09b: invokestatic io/ktor/utils/io/ByteReadChannel.awaitContent$default (Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 09e: dup
   // 09f: aload 17
   // 0a1: if_acmpne 0cc
   // 0a4: aload 17
   // 0a6: areturn
   // 0a7: aload 16
   // 0a9: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$1 I
   // 0ac: istore 3
   // 0ad: aload 16
   // 0af: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$0 I
   // 0b2: istore 2
   // 0b3: aload 16
   // 0b5: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$1 Ljava/lang/Object;
   // 0b8: checkcast java/lang/Appendable
   // 0bb: astore 1
   // 0bc: aload 16
   // 0be: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$0 Ljava/lang/Object;
   // 0c1: checkcast io/ktor/utils/io/ByteReadChannel
   // 0c4: astore 0
   // 0c5: aload 15
   // 0c7: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 0ca: aload 15
   // 0cc: pop
   // 0cd: aload 0
   // 0ce: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 0d3: ifeq 0db
   // 0d6: bipush 0
   // 0d7: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxBoolean (Z)Ljava/lang/Boolean;
   // 0da: areturn
   // 0db: new kotlinx/io/Buffer
   // 0de: dup
   // 0df: invokespecial kotlinx/io/Buffer.<init> ()V
   // 0e2: checkcast java/lang/AutoCloseable
   // 0e5: astore 5
   // 0e7: aconst_null
   // 0e8: astore 6
   // 0ea: nop
   // 0eb: aload 5
   // 0ed: checkcast kotlinx/io/Buffer
   // 0f0: astore 7
   // 0f2: bipush 0
   // 0f3: istore 8
   // 0f5: aload 0
   // 0f6: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 0fb: ifne 313
   // 0fe: aload 0
   // 0ff: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 104: invokeinterface kotlinx/io/Source.exhausted ()Z 1
   // 109: ifne 259
   // 10c: aload 0
   // 10d: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 112: invokeinterface kotlinx/io/Source.readByte ()B 1
   // 117: istore 9
   // 119: iload 9
   // 11b: bipush 13
   // 11d: if_icmpne 21e
   // 120: aload 0
   // 121: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 126: invokeinterface kotlinx/io/Source.exhausted ()Z 1
   // 12b: ifeq 1c6
   // 12e: aload 0
   // 12f: bipush 0
   // 130: aload 16
   // 132: bipush 1
   // 133: aconst_null
   // 134: aload 16
   // 136: aload 0
   // 137: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$0 Ljava/lang/Object;
   // 13a: aload 16
   // 13c: aload 1
   // 13d: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$1 Ljava/lang/Object;
   // 140: aload 16
   // 142: aload 5
   // 144: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$2 Ljava/lang/Object;
   // 147: aload 16
   // 149: aload 7
   // 14b: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$3 Ljava/lang/Object;
   // 14e: aload 16
   // 150: iload 2
   // 151: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$0 I
   // 154: aload 16
   // 156: iload 3
   // 157: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$1 I
   // 15a: aload 16
   // 15c: iload 8
   // 15e: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$2 I
   // 161: aload 16
   // 163: iload 9
   // 165: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.B$0 B
   // 168: aload 16
   // 16a: bipush 2
   // 16b: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.label I
   // 16e: invokestatic io/ktor/utils/io/ByteReadChannel.awaitContent$default (Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 171: dup
   // 172: aload 17
   // 174: if_acmpne 1c5
   // 177: aload 17
   // 179: areturn
   // 17a: aload 16
   // 17c: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.B$0 B
   // 17f: istore 9
   // 181: aload 16
   // 183: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$2 I
   // 186: istore 8
   // 188: aload 16
   // 18a: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$1 I
   // 18d: istore 3
   // 18e: aload 16
   // 190: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$0 I
   // 193: istore 2
   // 194: aload 16
   // 196: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$3 Ljava/lang/Object;
   // 199: checkcast kotlinx/io/Buffer
   // 19c: astore 7
   // 19e: aconst_null
   // 19f: astore 6
   // 1a1: aload 16
   // 1a3: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$2 Ljava/lang/Object;
   // 1a6: checkcast java/lang/AutoCloseable
   // 1a9: astore 5
   // 1ab: aload 16
   // 1ad: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$1 Ljava/lang/Object;
   // 1b0: checkcast java/lang/Appendable
   // 1b3: astore 1
   // 1b4: aload 16
   // 1b6: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$0 Ljava/lang/Object;
   // 1b9: checkcast io/ktor/utils/io/ByteReadChannel
   // 1bc: astore 0
   // 1bd: nop
   // 1be: aload 15
   // 1c0: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 1c3: aload 15
   // 1c5: pop
   // 1c6: aload 0
   // 1c7: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 1cc: invokeinterface kotlinx/io/Source.getBuffer ()Lkotlinx/io/Buffer; 1
   // 1d1: lconst_0
   // 1d2: invokevirtual kotlinx/io/Buffer.get (J)B
   // 1d5: bipush 10
   // 1d7: if_icmpne 1f5
   // 1da: iload 3
   // 1db: getstatic io/ktor/utils/io/LineEndingMode.Companion Lio/ktor/utils/io/LineEndingMode$Companion;
   // 1de: invokevirtual io/ktor/utils/io/LineEndingMode$Companion.getCRLF-f0jXZW8 ()I
   // 1e1: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.readUTF8LineTo_RRvyBJ8$checkLineEndingAllowed (II)V
   // 1e4: aload 0
   // 1e5: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 1ea: lconst_1
   // 1eb: invokestatic io/ktor/utils/io/core/ByteReadPacketKt.discard (Lkotlinx/io/Source;J)J
   // 1ee: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxLong (J)Ljava/lang/Long;
   // 1f1: pop
   // 1f2: goto 1ff
   // 1f5: iload 3
   // 1f6: getstatic io/ktor/utils/io/LineEndingMode.Companion Lio/ktor/utils/io/LineEndingMode$Companion;
   // 1f9: invokevirtual io/ktor/utils/io/LineEndingMode$Companion.getCR-f0jXZW8 ()I
   // 1fc: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.readUTF8LineTo_RRvyBJ8$checkLineEndingAllowed (II)V
   // 1ff: aload 1
   // 200: aload 7
   // 202: invokestatic kotlinx/io/Utf8Kt.readString (Lkotlinx/io/Buffer;)Ljava/lang/String;
   // 205: checkcast java/lang/CharSequence
   // 208: invokeinterface java/lang/Appendable.append (Ljava/lang/CharSequence;)Ljava/lang/Appendable; 2
   // 20d: pop
   // 20e: bipush 1
   // 20f: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxBoolean (Z)Ljava/lang/Boolean;
   // 212: astore 14
   // 214: aload 5
   // 216: aload 6
   // 218: invokestatic kotlin/jdk7/AutoCloseableKt.closeFinally (Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V
   // 21b: aload 14
   // 21d: areturn
   // 21e: iload 9
   // 220: bipush 10
   // 222: if_icmpne 24e
   // 225: iload 3
   // 226: getstatic io/ktor/utils/io/LineEndingMode.Companion Lio/ktor/utils/io/LineEndingMode$Companion;
   // 229: invokevirtual io/ktor/utils/io/LineEndingMode$Companion.getLF-f0jXZW8 ()I
   // 22c: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.readUTF8LineTo_RRvyBJ8$checkLineEndingAllowed (II)V
   // 22f: aload 1
   // 230: aload 7
   // 232: invokestatic kotlinx/io/Utf8Kt.readString (Lkotlinx/io/Buffer;)Ljava/lang/String;
   // 235: checkcast java/lang/CharSequence
   // 238: invokeinterface java/lang/Appendable.append (Ljava/lang/CharSequence;)Ljava/lang/Appendable; 2
   // 23d: pop
   // 23e: bipush 1
   // 23f: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxBoolean (Z)Ljava/lang/Boolean;
   // 242: astore 13
   // 244: aload 5
   // 246: aload 6
   // 248: invokestatic kotlin/jdk7/AutoCloseableKt.closeFinally (Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V
   // 24b: aload 13
   // 24d: areturn
   // 24e: aload 7
   // 250: iload 9
   // 252: i2b
   // 253: invokevirtual kotlinx/io/Buffer.writeByte (B)V
   // 256: goto 0fe
   // 259: aload 7
   // 25b: invokevirtual kotlinx/io/Buffer.getSize ()J
   // 25e: iload 2
   // 25f: i2l
   // 260: lcmp
   // 261: iflt 286
   // 264: new io/ktor/utils/io/charsets/TooLongLineException
   // 267: dup
   // 268: new java/lang/StringBuilder
   // 26b: dup
   // 26c: invokespecial java/lang/StringBuilder.<init> ()V
   // 26f: ldc_w "Line exceeds limit of "
   // 272: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 275: iload 2
   // 276: invokevirtual java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
   // 279: ldc_w " characters"
   // 27c: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 27f: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
   // 282: invokespecial io/ktor/utils/io/charsets/TooLongLineException.<init> (Ljava/lang/String;)V
   // 285: athrow
   // 286: aload 0
   // 287: bipush 0
   // 288: aload 16
   // 28a: bipush 1
   // 28b: aconst_null
   // 28c: aload 16
   // 28e: aload 0
   // 28f: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$0 Ljava/lang/Object;
   // 292: aload 16
   // 294: aload 1
   // 295: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$1 Ljava/lang/Object;
   // 298: aload 16
   // 29a: aload 5
   // 29c: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$2 Ljava/lang/Object;
   // 29f: aload 16
   // 2a1: aload 7
   // 2a3: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$3 Ljava/lang/Object;
   // 2a6: aload 16
   // 2a8: iload 2
   // 2a9: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$0 I
   // 2ac: aload 16
   // 2ae: iload 3
   // 2af: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$1 I
   // 2b2: aload 16
   // 2b4: iload 8
   // 2b6: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$2 I
   // 2b9: aload 16
   // 2bb: bipush 3
   // 2bc: putfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.label I
   // 2bf: invokestatic io/ktor/utils/io/ByteReadChannel.awaitContent$default (Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 2c2: dup
   // 2c3: aload 17
   // 2c5: if_acmpne 30f
   // 2c8: aload 17
   // 2ca: areturn
   // 2cb: aload 16
   // 2cd: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$2 I
   // 2d0: istore 8
   // 2d2: aload 16
   // 2d4: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$1 I
   // 2d7: istore 3
   // 2d8: aload 16
   // 2da: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.I$0 I
   // 2dd: istore 2
   // 2de: aload 16
   // 2e0: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$3 Ljava/lang/Object;
   // 2e3: checkcast kotlinx/io/Buffer
   // 2e6: astore 7
   // 2e8: aconst_null
   // 2e9: astore 6
   // 2eb: aload 16
   // 2ed: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$2 Ljava/lang/Object;
   // 2f0: checkcast java/lang/AutoCloseable
   // 2f3: astore 5
   // 2f5: aload 16
   // 2f7: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$1 Ljava/lang/Object;
   // 2fa: checkcast java/lang/Appendable
   // 2fd: astore 1
   // 2fe: aload 16
   // 300: getfield io/ktor/utils/io/ByteReadChannelOperationsKt$readUTF8LineTo$2.L$0 Ljava/lang/Object;
   // 303: checkcast io/ktor/utils/io/ByteReadChannel
   // 306: astore 0
   // 307: nop
   // 308: aload 15
   // 30a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 30d: aload 15
   // 30f: pop
   // 310: goto 0f5
   // 313: aload 7
   // 315: invokevirtual kotlinx/io/Buffer.getSize ()J
   // 318: lconst_0
   // 319: lcmp
   // 31a: ifle 321
   // 31d: bipush 1
   // 31e: goto 322
   // 321: bipush 0
   // 322: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxBoolean (Z)Ljava/lang/Boolean;
   // 325: astore 9
   // 327: aload 9
   // 329: invokevirtual java/lang/Boolean.booleanValue ()Z
   // 32c: istore 10
   // 32e: bipush 0
   // 32f: istore 11
   // 331: iload 10
   // 333: ifeq 345
   // 336: aload 1
   // 337: aload 7
   // 339: invokestatic kotlinx/io/Utf8Kt.readString (Lkotlinx/io/Buffer;)Ljava/lang/String;
   // 33c: checkcast java/lang/CharSequence
   // 33f: invokeinterface java/lang/Appendable.append (Ljava/lang/CharSequence;)Ljava/lang/Appendable; 2
   // 344: pop
   // 345: nop
   // 346: aload 9
   // 348: astore 12
   // 34a: aload 5
   // 34c: aload 6
   // 34e: invokestatic kotlin/jdk7/AutoCloseableKt.closeFinally (Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V
   // 351: aload 12
   // 353: areturn
   // 354: astore 8
   // 356: aload 8
   // 358: astore 6
   // 35a: aload 8
   // 35c: athrow
   // 35d: astore 8
   // 35f: aload 5
   // 361: aload 6
   // 363: invokestatic kotlin/jdk7/AutoCloseableKt.closeFinally (Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V
   // 366: aload 8
   // 368: athrow
   // 369: new java/lang/IllegalStateException
   // 36c: dup
   // 36d: ldc "call to 'resume' before 'invoke' with coroutine"
   // 36f: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 372: athrow
}

@JvmSynthetic
fun `readUTF8LineTo-RRvyBJ8$default`(var0: ByteReadChannel, var1: Appendable, var2: Int, var3: Int, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = Integer.MAX_VALUE;
   }

   if ((var5 and 4) != 0) {
      var3 = LineEndingMode.Companion.getAny-f0jXZW8();
   }

   return readUTF8LineTo-RRvyBJ8(var0, var1, var2, var3, var4);
}

public suspend inline fun ByteReadChannel.read(crossinline block: (ByteArray, Int, Int, Continuation<Int>) -> Any?): Int {
   var `$continuation`: Continuation;
   label68: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.read.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.read.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.read.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label68;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.read.1(`$completion`);
   }

   var result: Ref.IntRef;
   var `buffer$iv`: Buffer;
   var `head$iv`: Segment;
   var var14: Ref.IntRef;
   var var10000: Segment;
   label75: {
      val `$result`: Any = `$continuation`.result;
      val var17: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var20: Int;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var20 = 0;
            if (`$this$read`.isClosedForRead()) {
               return Boxing.boxInt(-1);
            }

            if (`$this$read`.getReadBuffer().exhausted()) {
               `$continuation`.L$0 = `$this$read`;
               `$continuation`.L$1 = block;
               `$continuation`.I$0 = 0;
               `$continuation`.label = 1;
               if (ByteReadChannel.awaitContent$default(`$this$read`, 0, `$continuation`, 1, null) === var17) {
                  return var17;
               }
            }
            break;
         case 1:
            var20 = `$continuation`.I$0;
            block = `$continuation`.L$1 as Function4;
            `$this$read` = `$continuation`.L$0 as ByteReadChannel;
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            val var13: Int = `$continuation`.I$4;
            val start: Int = `$continuation`.I$3;
            val endExclusive: Int = `$continuation`.I$2;
            val `$i$f$readFromHead`: Int = `$continuation`.I$1;
            var20 = `$continuation`.I$0;
            var14 = `$continuation`.L$7 as Ref.IntRef;
            val array: ByteArray = `$continuation`.L$6 as ByteArray;
            `head$iv` = `$continuation`.L$5 as Segment;
            `buffer$iv` = `$continuation`.L$4 as Buffer;
            val `this_$iv`: UnsafeBufferOperations = `$continuation`.L$3 as UnsafeBufferOperations;
            result = `$continuation`.L$2 as Ref.IntRef;
            block = `$continuation`.L$1 as Function4;
            `$this$read` = `$continuation`.L$0 as ByteReadChannel;
            ResultKt.throwOnFailure(`$result`);
            var10000 = (Segment)`$result`;
            break label75;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (`$this$read`.isClosedForRead()) {
         return Boxing.boxInt(-1);
      }

      result = new Ref.IntRef();
      val var21: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
      `buffer$iv` = `$this$read`.getReadBuffer().getBuffer();
      if (`buffer$iv`.exhausted()) {
         throw new IllegalArgumentException("Buffer is empty".toString());
      }

      var10000 = `buffer$iv`.getHead();
      `head$iv` = var10000;
      val var30: ByteArray = var10000.dataAsByteArray(true);
      val var25: Int = var10000.getLimit();
      val var26: Int = var10000.getPos();
      var14 = result;
      val var10002: Int = Boxing.boxInt(var26);
      val var10003: Int = Boxing.boxInt(var25);
      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$read`);
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(block);
      `$continuation`.L$2 = result;
      `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var21);
      `$continuation`.L$4 = `buffer$iv`;
      `$continuation`.L$5 = var10000;
      `$continuation`.L$6 = SpillingKt.nullOutSpilledVariable(var30);
      `$continuation`.L$7 = result;
      `$continuation`.I$0 = var20;
      `$continuation`.I$1 = 0;
      `$continuation`.I$2 = var25;
      `$continuation`.I$3 = var26;
      `$continuation`.I$4 = 0;
      `$continuation`.label = 2;
      var10000 = (Segment)block.invoke(var30, var10002, var10003, `$continuation`);
      if (var10000 === var17) {
         return var17;
      }
   }

   var14.element = (var10000 as java.lang.Number).intValue();
   val `bytesRead$iv`: Int = result.element;
   if (result.element != 0) {
      if (result.element < 0) {
         throw new IllegalStateException("Returned negative read bytes count");
      }

      if (result.element > `head$iv`.getSize()) {
         throw new IllegalStateException("Returned too many bytes");
      }

      `buffer$iv`.skip((long)`bytesRead$iv`);
   }

   return Boxing.boxInt(result.element);
}

fun ByteReadChannel.`read$$forInline`(block: (ByteArray?, Int?, Int?, Continuation<? super Integer>?) -> Any, `$completion`: Continuation<? super Integer>): Any {
   if (`$this$read`.isClosedForRead()) {
      return -1;
   } else {
      if (`$this$read`.getReadBuffer().exhausted()) {
         InlineMarker.mark(0);
         ByteReadChannel.awaitContent$default(`$this$read`, 0, `$completion`, 1, null);
         InlineMarker.mark(1);
      }

      if (`$this$read`.isClosedForRead()) {
         return -1;
      } else {
         val result: Ref.IntRef = new Ref.IntRef();
         val `this_$iv`: UnsafeBufferOperations = UnsafeBufferOperations.INSTANCE;
         val `buffer$iv`: Buffer = `$this$read`.getReadBuffer().getBuffer();
         if (`buffer$iv`.exhausted()) {
            throw new IllegalArgumentException("Buffer is empty".toString());
         } else {
            val var10000: Segment = `buffer$iv`.getHead();
            val var18: ByteArray = var10000.dataAsByteArray(true);
            val endExclusive: Int = var10000.getLimit().intValue();
            val start: Int = var10000.getPos().intValue();
            val array: ByteArray = var18;
            val var10002: Int = start;
            val var10003: Int = endExclusive;
            InlineMarker.mark(3);
            result.element = (block.invoke(array, var10002, var10003, null) as java.lang.Number).intValue();
            val `bytesRead$iv`: Int = result.element.intValue();
            if (`bytesRead$iv` != 0) {
               if (`bytesRead$iv` < 0) {
                  throw new IllegalStateException("Returned negative read bytes count");
               }

               if (`bytesRead$iv` > var10000.getSize()) {
                  throw new IllegalStateException("Returned too many bytes");
               }

               `buffer$iv`.skip((long)`bytesRead$iv`);
            }

            return result.element;
         }
      }
   }
}

public suspend fun ByteReadChannel.readFully(out: ByteArray, start: Int = ..., end: Int = ...) {
   var `$continuation`: Continuation;
   label53: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.readFully.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readFully.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.readFully.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label53;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.readFully.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10: Int;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (end > start && `$this$readFully`.isClosedForRead()) {
            throw new EOFException("Channel is already closed");
         }

         var10 = start;
         break;
      case 1:
         var10 = `$continuation`.I$2;
         end = `$continuation`.I$1;
         start = `$continuation`.I$0;
         out = `$continuation`.L$1 as ByteArray;
         `$this$readFully` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         if (`$this$readFully`.isClosedForRead()) {
            throw new EOFException("Channel is already closed");
         }

         val count: Int = Math.min(end - var10, (int)ByteReadPacketKt.getRemaining(`$this$readFully`.getReadBuffer()));
         SourcesKt.readTo(`$this$readFully`.getReadBuffer(), out, var10, var10 + count);
         var10 = var10 + count;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (offset < end) {
      if (`$this$readFully`.getReadBuffer().exhausted()) {
         `$continuation`.L$0 = `$this$readFully`;
         `$continuation`.L$1 = out;
         `$continuation`.I$0 = start;
         `$continuation`.I$1 = end;
         `$continuation`.I$2 = var10;
         `$continuation`.label = 1;
         if (ByteReadChannel.awaitContent$default(`$this$readFully`, 0, `$continuation`, 1, null) === var9) {
            return var9;
         }
      }

      if (`$this$readFully`.isClosedForRead()) {
         throw new EOFException("Channel is already closed");
      }

      val var11: Int = Math.min(end - var10, (int)ByteReadPacketKt.getRemaining(`$this$readFully`.getReadBuffer()));
      SourcesKt.readTo(`$this$readFully`.getReadBuffer(), out, var10, var10 + var11);
      var10 += var11;
   }

   return Unit.INSTANCE;
}

@JvmSynthetic
fun `readFully$default`(var0: ByteReadChannel, var1: ByteArray, var2: Int, var3: Int, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = 0;
   }

   if ((var5 and 4) != 0) {
      var3 = var1.length;
   }

   return readFully(var0, var1, var2, var3, var4);
}

@InternalAPI
public fun ByteReadChannel.rethrowCloseCauseIfNeeded() {
   val var10000: java.lang.Throwable = `$this$rethrowCloseCauseIfNeeded`.getClosedCause();
   if (var10000 != null) {
      throw var10000;
   }
}

@InternalAPI
public fun ByteWriteChannel.rethrowCloseCauseIfNeeded() {
   val var10000: java.lang.Throwable = `$this$rethrowCloseCauseIfNeeded`.getClosedCause();
   if (var10000 != null) {
      throw var10000;
   }
}

@InternalAPI
public fun ByteChannel.rethrowCloseCauseIfNeeded() {
   val var10000: java.lang.Throwable = `$this$rethrowCloseCauseIfNeeded`.getClosedCause();
   if (var10000 != null) {
      throw var10000;
   }
}

public suspend fun ByteReadChannel.readUntil(matchString: ByteString, writeChannel: ByteWriteChannel, limit: Long = ..., ignoreMissing: Boolean = ...): Long {
   return new ByteChannelScanner(`$this$readUntil`, matchString, writeChannel, limit).findNext$ktor_io(ignoreMissing, `$completion`);
}

@JvmSynthetic
fun `readUntil$default`(var0: ByteReadChannel, var1: ByteString, var2: ByteWriteChannel, var3: Long, var5: Boolean, var6: Continuation, var7: Int, var8: Any): Any {
   if ((var7 and 4) != 0) {
      var3 = java.lang.Long.MAX_VALUE;
   }

   if ((var7 and 8) != 0) {
      var5 = false;
   }

   return readUntil(var0, var1, var2, var3, var5, var6);
}

public suspend fun ByteReadChannel.skipIfFound(byteString: ByteString): Boolean {
   var `$continuation`: Continuation;
   label31: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.skipIfFound.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.skipIfFound.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.skipIfFound.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label31;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.skipIfFound.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         val var10001: Int = byteString.getSize();
         `$continuation`.L$0 = `$this$skipIfFound`;
         `$continuation`.L$1 = byteString;
         `$continuation`.label = 1;
         var10000 = peek(`$this$skipIfFound`, var10001, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         byteString = `$continuation`.L$1 as ByteString;
         `$this$skipIfFound` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      case 2:
         byteString = `$continuation`.L$1 as ByteString;
         `$this$skipIfFound` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         return Boxing.boxBoolean(true);
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if (!(var10000 == byteString)) {
      return Boxing.boxBoolean(false);
   } else {
      val var8: Long = byteString.getSize();
      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$skipIfFound`);
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(byteString);
      `$continuation`.label = 2;
      return if (discard(`$this$skipIfFound`, var8, `$continuation`) === var5) var5 else Boxing.boxBoolean(true);
   }
}

public suspend fun ByteReadChannel.peek(count: Int): ByteString? {
   var `$continuation`: Continuation;
   label28: {
      if (`$completion` is io.ktor.utils.io.ByteReadChannelOperationsKt.peek.1) {
         `$continuation` = `$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.peek.1;
         if (((`$completion` as io.ktor.utils.io.ByteReadChannelOperationsKt.peek.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label28;
         }
      }

      `$continuation` = new io.ktor.utils.io.ByteReadChannelOperationsKt.peek.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (`$this$peek`.isClosedForRead()) {
            return null;
         }

         `$continuation`.L$0 = `$this$peek`;
         `$continuation`.I$0 = count;
         `$continuation`.label = 1;
         var10000 = `$this$peek`.awaitContent(count, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         count = `$continuation`.I$0;
         `$this$peek` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return if (!var10000 as java.lang.Boolean) null else ByteStringsKt.readByteString(`$this$peek`.getReadBuffer().peek(), count);
}

fun `reader$lambda$0$0`(`$channel`: ByteChannel, it: java.lang.Throwable): Unit {
   if (it != null && !`$channel`.isClosedForRead()) {
      `$channel`.cancel(it);
   }

   return Unit.INSTANCE;
}

fun `readUTF8LineTo_RRvyBJ8$checkLineEndingAllowed`(var0: Int, lineEndingToCheck: Int) {
   if (!LineEndingMode.contains-lTjpP64(var0, lineEndingToCheck)) {
      throw new IOException("Unexpected line ending ${LineEndingMode.toString-impl(lineEndingToCheck)}, while expected ${LineEndingMode.toString-impl(var0)}");
   }
}

@JvmSynthetic
fun `access$awaitUntilReadable`(`$receiver`: ByteReadChannel, numberOfBytes: Int, `$completion`: Continuation): Any {
   return awaitUntilReadable(`$receiver`, numberOfBytes, `$completion`);
}
