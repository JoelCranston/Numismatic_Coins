@file:SourceDebugExtension(["SMAP\nDeflater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Deflater.kt\nio/ktor/util/DeflaterKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ByteOrderJvm.kt\nio/ktor/utils/io/bits/ByteOrderJVMKt\n*L\n1#1,137:1\n1#2:138\n11#3:139\n19#3:140\n19#3:141\n*S KotlinDebug\n*F\n+ 1 Deflater.kt\nio/ktor/util/DeflaterKt\n*L\n42#1:139\n48#1:140\n49#1:141\n*E\n"])

package io.ktor.util

import io.ktor.util.DeflaterKt.deflated.2
import io.ktor.util.DeflaterKt.putGzipHeader.1
import io.ktor.util.cio.ByteBufferPoolKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannelOperations_jvmKt
import io.ktor.utils.io.pool.ObjectPool
import java.nio.Buffer
import java.nio.ByteBuffer
import java.util.zip.Checksum
import java.util.zip.Deflater
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

internal const val GZIP_MAGIC: Short = -29921
internal final val GZIP_HEADER_PADDING: ByteArray = new byte[7]
private final val DeflateWriterCoroutineName: CoroutineName = new CoroutineName("encoder-deflate-writer")
private final val DeflateReaderCoroutineName: CoroutineName = new CoroutineName("encoder-deflate-reader")

private fun Deflater.deflateTo(outBuffer: ByteBuffer) {
   if (outBuffer.hasRemaining()) {
      ((Buffer)outBuffer).position(
         outBuffer.position() + `$this$deflateTo`.deflate(outBuffer.array(), outBuffer.arrayOffset() + outBuffer.position(), outBuffer.remaining())
      );
   }
}

private fun Deflater.setInputBuffer(buffer: ByteBuffer) {
   if (!buffer.hasArray()) {
      throw new IllegalArgumentException("buffer need to be array-backed".toString());
   } else {
      `$this$setInputBuffer`.setInput(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
   }
}

internal fun Checksum.updateKeepPosition(buffer: ByteBuffer) {
   if (!buffer.hasArray()) {
      throw new IllegalArgumentException("buffer need to be array-backed".toString());
   } else {
      `$this$updateKeepPosition`.update(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
   }
}

private suspend fun ByteWriteChannel.putGzipHeader() {
   var `$continuation`: Continuation;
   label39: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label39;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   var var6: Any;
   label31: {
      val `$result`: Any = `$continuation`.result;
      var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var10001: Short = java.lang.Short.reverseBytes((short)-29921);
            `$continuation`.L$0 = `$this$putGzipHeader`;
            `$continuation`.label = 1;
            if (ByteWriteChannelOperationsKt.writeShort(`$this$putGzipHeader`, var10001, `$continuation`) === var6) {
               return var6;
            }
            break;
         case 1:
            `$this$putGzipHeader` = `$continuation`.L$0 as ByteWriteChannel;
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            `$this$putGzipHeader` = `$continuation`.L$0 as ByteWriteChannel;
            ResultKt.throwOnFailure(`$result`);
            break label31;
         case 3:
            `$this$putGzipHeader` = `$continuation`.L$0 as ByteWriteChannel;
            ResultKt.throwOnFailure(`$result`);
            return Unit.INSTANCE;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      `$continuation`.L$0 = `$this$putGzipHeader`;
      `$continuation`.label = 2;
      if (ByteWriteChannelOperationsKt.writeByte(`$this$putGzipHeader`, (byte)8, `$continuation`) === var6) {
         return var6;
      }
   }

   val var8: ByteArray = GZIP_HEADER_PADDING;
   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$putGzipHeader`);
   `$continuation`.label = 3;
   return if (ByteWriteChannelOperationsKt.writeFully$default(`$this$putGzipHeader`, var8, 0, 0, `$continuation`, 6, null) === var6) var6 else Unit.INSTANCE;
}

private suspend fun ByteWriteChannel.putGzipTrailer(crc: Checksum, deflater: Deflater) {
   var `$continuation`: Continuation;
   label27: {
      if (`$completion` is io.ktor.util.DeflaterKt.putGzipTrailer.1) {
         `$continuation` = `$completion` as io.ktor.util.DeflaterKt.putGzipTrailer.1;
         if (((`$completion` as io.ktor.util.DeflaterKt.putGzipTrailer.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label27;
         }
      }

      `$continuation` = new io.ktor.util.DeflaterKt.putGzipTrailer.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         val var10001: Int = Integer.reverseBytes((int)crc.getValue());
         `$continuation`.L$0 = `$this$putGzipTrailer`;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(crc);
         `$continuation`.L$2 = deflater;
         `$continuation`.label = 1;
         if (ByteWriteChannelOperationsKt.writeInt(`$this$putGzipTrailer`, var10001, `$continuation`) === var8) {
            return var8;
         }
         break;
      case 1:
         deflater = `$continuation`.L$2 as Deflater;
         crc = `$continuation`.L$1 as Checksum;
         `$this$putGzipTrailer` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      case 2:
         deflater = `$continuation`.L$2 as Deflater;
         crc = `$continuation`.L$1 as Checksum;
         `$this$putGzipTrailer` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         return Unit.INSTANCE;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   val var14: Int = Integer.reverseBytes(deflater.getTotalIn());
   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$putGzipTrailer`);
   `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(crc);
   `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(deflater);
   `$continuation`.label = 2;
   return if (ByteWriteChannelOperationsKt.writeInt(`$this$putGzipTrailer`, var14, `$continuation`) === var8) var8 else Unit.INSTANCE;
}

private suspend fun ByteWriteChannel.deflateWhile(deflater: Deflater, buffer: ByteBuffer, predicate: () -> Boolean) {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is io.ktor.util.DeflaterKt.deflateWhile.1) {
         `$continuation` = `$completion` as io.ktor.util.DeflaterKt.deflateWhile.1;
         if (((`$completion` as io.ktor.util.DeflaterKt.deflateWhile.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new io.ktor.util.DeflaterKt.deflateWhile.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         break;
      case 1:
         predicate = `$continuation`.L$3 as Function0;
         buffer = `$continuation`.L$2 as ByteBuffer;
         deflater = `$continuation`.L$1 as Deflater;
         `$this$deflateWhile` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (predicate.invoke()) {
      ((Buffer)buffer).clear();
      deflateTo(deflater, buffer);
      ((Buffer)buffer).flip();
      `$continuation`.L$0 = `$this$deflateWhile`;
      `$continuation`.L$1 = deflater;
      `$continuation`.L$2 = buffer;
      `$continuation`.L$3 = predicate;
      `$continuation`.label = 1;
      if (ByteWriteChannelOperations_jvmKt.writeFully(`$this$deflateWhile`, buffer, `$continuation`) === var7) {
         return var7;
      }
   }

   return Unit.INSTANCE;
}

private suspend fun ByteReadChannel.deflateTo(destination: ByteWriteChannel, gzip: Boolean = ..., pool: ObjectPool<ByteBuffer> = ...) {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.RuntimeException: parsing failure!
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:211)
   //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
   //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
   //
   // Bytecode:
   // 000: aload 4
   // 002: instanceof io/ktor/util/DeflaterKt$deflateTo$1
   // 005: ifeq 029
   // 008: aload 4
   // 00a: checkcast io/ktor/util/DeflaterKt$deflateTo$1
   // 00d: astore 13
   // 00f: aload 13
   // 011: getfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 014: ldc -2147483648
   // 016: iand
   // 017: ifeq 029
   // 01a: aload 13
   // 01c: dup
   // 01d: getfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 020: ldc -2147483648
   // 022: isub
   // 023: putfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 026: goto 034
   // 029: new io/ktor/util/DeflaterKt$deflateTo$1
   // 02c: dup
   // 02d: aload 4
   // 02f: invokespecial io/ktor/util/DeflaterKt$deflateTo$1.<init> (Lkotlin/coroutines/Continuation;)V
   // 032: astore 13
   // 034: aload 13
   // 036: getfield io/ktor/util/DeflaterKt$deflateTo$1.result Ljava/lang/Object;
   // 039: astore 12
   // 03b: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03e: astore 14
   // 040: aload 13
   // 042: getfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 045: tableswitch 1040 0 5 39 164 336 533 729 904
   // 06c: aload 12
   // 06e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 071: new java/util/zip/CRC32
   // 074: dup
   // 075: invokespecial java/util/zip/CRC32.<init> ()V
   // 078: astore 5
   // 07a: new java/util/zip/Deflater
   // 07d: dup
   // 07e: bipush -1
   // 07f: bipush 1
   // 080: invokespecial java/util/zip/Deflater.<init> (IZ)V
   // 083: astore 6
   // 085: aload 3
   // 086: invokeinterface io/ktor/utils/io/pool/ObjectPool.borrow ()Ljava/lang/Object; 1
   // 08b: checkcast java/nio/ByteBuffer
   // 08e: astore 7
   // 090: aload 3
   // 091: invokeinterface io/ktor/utils/io/pool/ObjectPool.borrow ()Ljava/lang/Object; 1
   // 096: checkcast java/nio/ByteBuffer
   // 099: astore 8
   // 09b: nop
   // 09c: iload 2
   // 09d: ifeq 13b
   // 0a0: aload 1
   // 0a1: aload 13
   // 0a3: aload 13
   // 0a5: aload 0
   // 0a6: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 0a9: aload 13
   // 0ab: aload 1
   // 0ac: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 0af: aload 13
   // 0b1: aload 3
   // 0b2: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 0b5: aload 13
   // 0b7: aload 5
   // 0b9: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 0bc: aload 13
   // 0be: aload 6
   // 0c0: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 0c3: aload 13
   // 0c5: aload 7
   // 0c7: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 0ca: aload 13
   // 0cc: aload 8
   // 0ce: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 0d1: aload 13
   // 0d3: iload 2
   // 0d4: putfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 0d7: aload 13
   // 0d9: bipush 1
   // 0da: putfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 0dd: invokestatic io/ktor/util/DeflaterKt.putGzipHeader (Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 0e0: dup
   // 0e1: aload 14
   // 0e3: if_acmpne 13a
   // 0e6: aload 14
   // 0e8: areturn
   // 0e9: aload 13
   // 0eb: getfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 0ee: istore 2
   // 0ef: aload 13
   // 0f1: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 0f4: checkcast java/nio/ByteBuffer
   // 0f7: astore 8
   // 0f9: aload 13
   // 0fb: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 0fe: checkcast java/nio/ByteBuffer
   // 101: astore 7
   // 103: aload 13
   // 105: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 108: checkcast java/util/zip/Deflater
   // 10b: astore 6
   // 10d: aload 13
   // 10f: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 112: checkcast java/util/zip/CRC32
   // 115: astore 5
   // 117: aload 13
   // 119: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 11c: checkcast io/ktor/utils/io/pool/ObjectPool
   // 11f: astore 3
   // 120: aload 13
   // 122: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 125: checkcast io/ktor/utils/io/ByteWriteChannel
   // 128: astore 1
   // 129: aload 13
   // 12b: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 12e: checkcast io/ktor/utils/io/ByteReadChannel
   // 131: astore 0
   // 132: nop
   // 133: aload 12
   // 135: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 138: aload 12
   // 13a: pop
   // 13b: aload 0
   // 13c: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 141: ifne 2af
   // 144: aload 7
   // 146: invokevirtual java/nio/ByteBuffer.clear ()Ljava/nio/Buffer;
   // 149: pop
   // 14a: aload 0
   // 14b: aload 7
   // 14d: aload 13
   // 14f: aload 13
   // 151: aload 0
   // 152: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 155: aload 13
   // 157: aload 1
   // 158: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 15b: aload 13
   // 15d: aload 3
   // 15e: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 161: aload 13
   // 163: aload 5
   // 165: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 168: aload 13
   // 16a: aload 6
   // 16c: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 16f: aload 13
   // 171: aload 7
   // 173: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 176: aload 13
   // 178: aload 8
   // 17a: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 17d: aload 13
   // 17f: iload 2
   // 180: putfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 183: aload 13
   // 185: bipush 2
   // 186: putfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 189: invokestatic io/ktor/utils/io/ByteReadChannelOperations_jvmKt.readAvailable (Lio/ktor/utils/io/ByteReadChannel;Ljava/nio/ByteBuffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 18c: dup
   // 18d: aload 14
   // 18f: if_acmpne 1e6
   // 192: aload 14
   // 194: areturn
   // 195: aload 13
   // 197: getfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 19a: istore 2
   // 19b: aload 13
   // 19d: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 1a0: checkcast java/nio/ByteBuffer
   // 1a3: astore 8
   // 1a5: aload 13
   // 1a7: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 1aa: checkcast java/nio/ByteBuffer
   // 1ad: astore 7
   // 1af: aload 13
   // 1b1: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 1b4: checkcast java/util/zip/Deflater
   // 1b7: astore 6
   // 1b9: aload 13
   // 1bb: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 1be: checkcast java/util/zip/CRC32
   // 1c1: astore 5
   // 1c3: aload 13
   // 1c5: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 1c8: checkcast io/ktor/utils/io/pool/ObjectPool
   // 1cb: astore 3
   // 1cc: aload 13
   // 1ce: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 1d1: checkcast io/ktor/utils/io/ByteWriteChannel
   // 1d4: astore 1
   // 1d5: aload 13
   // 1d7: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 1da: checkcast io/ktor/utils/io/ByteReadChannel
   // 1dd: astore 0
   // 1de: nop
   // 1df: aload 12
   // 1e1: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 1e4: aload 12
   // 1e6: checkcast java/lang/Number
   // 1e9: invokevirtual java/lang/Number.intValue ()I
   // 1ec: ifle 13b
   // 1ef: aload 7
   // 1f1: invokevirtual java/nio/ByteBuffer.flip ()Ljava/nio/Buffer;
   // 1f4: pop
   // 1f5: aload 5
   // 1f7: checkcast java/util/zip/Checksum
   // 1fa: aload 7
   // 1fc: invokestatic io/ktor/util/DeflaterKt.updateKeepPosition (Ljava/util/zip/Checksum;Ljava/nio/ByteBuffer;)V
   // 1ff: aload 6
   // 201: aload 7
   // 203: invokestatic io/ktor/util/DeflaterKt.setInputBuffer (Ljava/util/zip/Deflater;Ljava/nio/ByteBuffer;)V
   // 206: aload 1
   // 207: aload 6
   // 209: aload 8
   // 20b: aload 6
   // 20d: invokedynamic invoke (Ljava/util/zip/Deflater;)Lkotlin/jvm/functions/Function0; bsm=java/lang/invoke/LambdaMetafactory.metafactory (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite; args=[ ()Ljava/lang/Object;, io/ktor/util/DeflaterKt.deflateTo$lambda$0 (Ljava/util/zip/Deflater;)Z, ()Ljava/lang/Boolean; ]
   // 212: aload 13
   // 214: aload 13
   // 216: aload 0
   // 217: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 21a: aload 13
   // 21c: aload 1
   // 21d: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 220: aload 13
   // 222: aload 3
   // 223: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 226: aload 13
   // 228: aload 5
   // 22a: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 22d: aload 13
   // 22f: aload 6
   // 231: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 234: aload 13
   // 236: aload 7
   // 238: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 23b: aload 13
   // 23d: aload 8
   // 23f: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 242: aload 13
   // 244: iload 2
   // 245: putfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 248: aload 13
   // 24a: bipush 3
   // 24b: putfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 24e: invokestatic io/ktor/util/DeflaterKt.deflateWhile (Lio/ktor/utils/io/ByteWriteChannel;Ljava/util/zip/Deflater;Ljava/nio/ByteBuffer;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 251: dup
   // 252: aload 14
   // 254: if_acmpne 2ab
   // 257: aload 14
   // 259: areturn
   // 25a: aload 13
   // 25c: getfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 25f: istore 2
   // 260: aload 13
   // 262: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 265: checkcast java/nio/ByteBuffer
   // 268: astore 8
   // 26a: aload 13
   // 26c: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 26f: checkcast java/nio/ByteBuffer
   // 272: astore 7
   // 274: aload 13
   // 276: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 279: checkcast java/util/zip/Deflater
   // 27c: astore 6
   // 27e: aload 13
   // 280: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 283: checkcast java/util/zip/CRC32
   // 286: astore 5
   // 288: aload 13
   // 28a: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 28d: checkcast io/ktor/utils/io/pool/ObjectPool
   // 290: astore 3
   // 291: aload 13
   // 293: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 296: checkcast io/ktor/utils/io/ByteWriteChannel
   // 299: astore 1
   // 29a: aload 13
   // 29c: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 29f: checkcast io/ktor/utils/io/ByteReadChannel
   // 2a2: astore 0
   // 2a3: nop
   // 2a4: aload 12
   // 2a6: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 2a9: aload 12
   // 2ab: pop
   // 2ac: goto 13b
   // 2af: aload 0
   // 2b0: invokeinterface io/ktor/utils/io/ByteReadChannel.getClosedCause ()Ljava/lang/Throwable; 1
   // 2b5: dup
   // 2b6: ifnull 2c1
   // 2b9: astore 10
   // 2bb: bipush 0
   // 2bc: istore 11
   // 2be: aload 10
   // 2c0: athrow
   // 2c1: pop
   // 2c2: aload 6
   // 2c4: invokevirtual java/util/zip/Deflater.finish ()V
   // 2c7: aload 1
   // 2c8: aload 6
   // 2ca: aload 8
   // 2cc: aload 6
   // 2ce: invokedynamic invoke (Ljava/util/zip/Deflater;)Lkotlin/jvm/functions/Function0; bsm=java/lang/invoke/LambdaMetafactory.metafactory (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite; args=[ ()Ljava/lang/Object;, io/ktor/util/DeflaterKt.deflateTo$lambda$2 (Ljava/util/zip/Deflater;)Z, ()Ljava/lang/Boolean; ]
   // 2d3: aload 13
   // 2d5: aload 13
   // 2d7: aload 0
   // 2d8: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2db: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 2de: aload 13
   // 2e0: aload 1
   // 2e1: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 2e4: aload 13
   // 2e6: aload 3
   // 2e7: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 2ea: aload 13
   // 2ec: aload 5
   // 2ee: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 2f1: aload 13
   // 2f3: aload 6
   // 2f5: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 2f8: aload 13
   // 2fa: aload 7
   // 2fc: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 2ff: aload 13
   // 301: aload 8
   // 303: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 306: aload 13
   // 308: iload 2
   // 309: putfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 30c: aload 13
   // 30e: bipush 4
   // 30f: putfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 312: invokestatic io/ktor/util/DeflaterKt.deflateWhile (Lio/ktor/utils/io/ByteWriteChannel;Ljava/util/zip/Deflater;Ljava/nio/ByteBuffer;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 315: dup
   // 316: aload 14
   // 318: if_acmpne 36f
   // 31b: aload 14
   // 31d: areturn
   // 31e: aload 13
   // 320: getfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 323: istore 2
   // 324: aload 13
   // 326: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 329: checkcast java/nio/ByteBuffer
   // 32c: astore 8
   // 32e: aload 13
   // 330: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 333: checkcast java/nio/ByteBuffer
   // 336: astore 7
   // 338: aload 13
   // 33a: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 33d: checkcast java/util/zip/Deflater
   // 340: astore 6
   // 342: aload 13
   // 344: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 347: checkcast java/util/zip/CRC32
   // 34a: astore 5
   // 34c: aload 13
   // 34e: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 351: checkcast io/ktor/utils/io/pool/ObjectPool
   // 354: astore 3
   // 355: aload 13
   // 357: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 35a: checkcast io/ktor/utils/io/ByteWriteChannel
   // 35d: astore 1
   // 35e: aload 13
   // 360: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 363: checkcast io/ktor/utils/io/ByteReadChannel
   // 366: astore 0
   // 367: nop
   // 368: aload 12
   // 36a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 36d: aload 12
   // 36f: pop
   // 370: iload 2
   // 371: ifeq 41f
   // 374: aload 1
   // 375: aload 5
   // 377: checkcast java/util/zip/Checksum
   // 37a: aload 6
   // 37c: aload 13
   // 37e: aload 13
   // 380: aload 0
   // 381: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 384: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 387: aload 13
   // 389: aload 1
   // 38a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 38d: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 390: aload 13
   // 392: aload 3
   // 393: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 396: aload 13
   // 398: aload 5
   // 39a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 39d: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 3a0: aload 13
   // 3a2: aload 6
   // 3a4: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 3a7: aload 13
   // 3a9: aload 7
   // 3ab: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 3ae: aload 13
   // 3b0: aload 8
   // 3b2: putfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 3b5: aload 13
   // 3b7: iload 2
   // 3b8: putfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 3bb: aload 13
   // 3bd: bipush 5
   // 3be: putfield io/ktor/util/DeflaterKt$deflateTo$1.label I
   // 3c1: invokestatic io/ktor/util/DeflaterKt.putGzipTrailer (Lio/ktor/utils/io/ByteWriteChannel;Ljava/util/zip/Checksum;Ljava/util/zip/Deflater;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 3c4: dup
   // 3c5: aload 14
   // 3c7: if_acmpne 41e
   // 3ca: aload 14
   // 3cc: areturn
   // 3cd: aload 13
   // 3cf: getfield io/ktor/util/DeflaterKt$deflateTo$1.Z$0 Z
   // 3d2: istore 2
   // 3d3: aload 13
   // 3d5: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$6 Ljava/lang/Object;
   // 3d8: checkcast java/nio/ByteBuffer
   // 3db: astore 8
   // 3dd: aload 13
   // 3df: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$5 Ljava/lang/Object;
   // 3e2: checkcast java/nio/ByteBuffer
   // 3e5: astore 7
   // 3e7: aload 13
   // 3e9: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$4 Ljava/lang/Object;
   // 3ec: checkcast java/util/zip/Deflater
   // 3ef: astore 6
   // 3f1: aload 13
   // 3f3: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$3 Ljava/lang/Object;
   // 3f6: checkcast java/util/zip/CRC32
   // 3f9: astore 5
   // 3fb: aload 13
   // 3fd: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$2 Ljava/lang/Object;
   // 400: checkcast io/ktor/utils/io/pool/ObjectPool
   // 403: astore 3
   // 404: aload 13
   // 406: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$1 Ljava/lang/Object;
   // 409: checkcast io/ktor/utils/io/ByteWriteChannel
   // 40c: astore 1
   // 40d: aload 13
   // 40f: getfield io/ktor/util/DeflaterKt$deflateTo$1.L$0 Ljava/lang/Object;
   // 412: checkcast io/ktor/utils/io/ByteReadChannel
   // 415: astore 0
   // 416: nop
   // 417: aload 12
   // 419: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 41c: aload 12
   // 41e: pop
   // 41f: aload 6
   // 421: invokevirtual java/util/zip/Deflater.end ()V
   // 424: aload 3
   // 425: aload 7
   // 427: invokeinterface io/ktor/utils/io/pool/ObjectPool.recycle (Ljava/lang/Object;)V 2
   // 42c: aload 3
   // 42d: aload 8
   // 42f: invokeinterface io/ktor/utils/io/pool/ObjectPool.recycle (Ljava/lang/Object;)V 2
   // 434: goto 451
   // 437: astore 9
   // 439: aload 6
   // 43b: invokevirtual java/util/zip/Deflater.end ()V
   // 43e: aload 3
   // 43f: aload 7
   // 441: invokeinterface io/ktor/utils/io/pool/ObjectPool.recycle (Ljava/lang/Object;)V 2
   // 446: aload 3
   // 447: aload 8
   // 449: invokeinterface io/ktor/utils/io/pool/ObjectPool.recycle (Ljava/lang/Object;)V 2
   // 44e: aload 9
   // 450: athrow
   // 451: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 454: areturn
   // 455: new java/lang/IllegalStateException
   // 458: dup
   // 459: ldc "call to 'resume' before 'invoke' with coroutine"
   // 45b: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 45e: athrow
}

@JvmSynthetic
fun `deflateTo$default`(var0: ByteReadChannel, var1: ByteWriteChannel, var2: Boolean, var3: ObjectPool, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = true;
   }

   if ((var5 and 4) != 0) {
      var3 = ByteBufferPoolKt.getKtorDefaultPool();
   }

   return deflateTo(var0, var1, var2, var3, var4);
}

public fun ByteReadChannel.deflated(
   gzip: Boolean = true,
   pool: ObjectPool<ByteBuffer> = ByteBufferPoolKt.getKtorDefaultPool(),
   coroutineContext: CoroutineContext = Dispatchers.getUnconfined() as CoroutineContext
): ByteReadChannel {
   return ByteWriteChannelOperationsKt.writer(
         GlobalScope.INSTANCE,
         coroutineContext.plus(DeflateWriterCoroutineName),
         true,
         new io.ktor.util.DeflaterKt.deflated.1(`$this$deflated`, gzip, pool, null)
      )
      .getChannel();
}

@JvmSynthetic
fun `deflated$default`(var0: ByteReadChannel, var1: Boolean, var2: ObjectPool, var3: CoroutineContext, var4: Int, var5: Any): ByteReadChannel {
   if ((var4 and 1) != 0) {
      var1 = true;
   }

   if ((var4 and 2) != 0) {
      var2 = ByteBufferPoolKt.getKtorDefaultPool();
   }

   if ((var4 and 4) != 0) {
      var3 = Dispatchers.getUnconfined();
   }

   return deflated(var0, var1, var2, var3);
}

public fun ByteWriteChannel.deflated(
   gzip: Boolean = true,
   pool: ObjectPool<ByteBuffer> = ByteBufferPoolKt.getKtorDefaultPool(),
   coroutineContext: CoroutineContext = Dispatchers.getUnconfined() as CoroutineContext
): ByteWriteChannel {
   return ByteReadChannelOperationsKt.reader(
         GlobalScope.INSTANCE, coroutineContext.plus(DeflateReaderCoroutineName), true, new 2(`$this$deflated`, gzip, pool, null)
      )
      .getChannel();
}

@JvmSynthetic
fun `deflated$default`(var0: ByteWriteChannel, var1: Boolean, var2: ObjectPool, var3: CoroutineContext, var4: Int, var5: Any): ByteWriteChannel {
   if ((var4 and 1) != 0) {
      var1 = true;
   }

   if ((var4 and 2) != 0) {
      var2 = ByteBufferPoolKt.getKtorDefaultPool();
   }

   if ((var4 and 4) != 0) {
      var3 = Dispatchers.getUnconfined();
   }

   return deflated(var0, var1, var2, var3);
}

fun `deflateTo$lambda$0`(`$deflater`: Deflater): Boolean {
   return !`$deflater`.needsInput();
}

fun `deflateTo$lambda$2`(`$deflater`: Deflater): Boolean {
   return !`$deflater`.finished();
}

@JvmSynthetic
fun `access$putGzipHeader`(`$receiver`: ByteWriteChannel, `$completion`: Continuation): Any {
   return putGzipHeader(`$receiver`, `$completion`);
}

@JvmSynthetic
fun `access$putGzipTrailer`(`$receiver`: ByteWriteChannel, crc: Checksum, deflater: Deflater, `$completion`: Continuation): Any {
   return putGzipTrailer(`$receiver`, crc, deflater, `$completion`);
}

@JvmSynthetic
fun `access$deflateWhile`(`$receiver`: ByteWriteChannel, deflater: Deflater, buffer: ByteBuffer, predicate: Function0, `$completion`: Continuation): Any {
   return deflateWhile(`$receiver`, deflater, buffer, predicate, `$completion`);
}

@JvmSynthetic
fun `access$deflateTo`(`$receiver`: ByteReadChannel, destination: ByteWriteChannel, gzip: Boolean, pool: ObjectPool, `$completion`: Continuation): Any {
   return deflateTo(`$receiver`, destination, gzip, pool, `$completion`);
}
