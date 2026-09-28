@file:SourceDebugExtension(["SMAP\nDeflaterUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeflaterUtils.kt\nio/ktor/websocket/internals/DeflaterUtilsKt\n+ 2 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n+ 3 Pool.kt\nio/ktor/utils/io/pool/PoolKt\n*L\n1#1,86:1\n21#2,2:87\n23#2:94\n21#2,3:95\n21#2,2:98\n23#2:105\n182#3,5:89\n182#3,5:100\n*S KotlinDebug\n*F\n+ 1 DeflaterUtils.kt\nio/ktor/websocket/internals/DeflaterUtilsKt\n*L\n20#1:87,2\n20#1:94\n36#1:95,3\n46#1:98,2\n46#1:105\n21#1:89,5\n47#1:100,5\n*E\n"])

package io.ktor.websocket.internals

import io.ktor.util.cio.ByteBufferPoolKt
import io.ktor.utils.io.core.BytePacketBuilderExtensions_jvmKt
import io.ktor.utils.io.pool.ObjectPool
import java.nio.ByteBuffer
import java.util.zip.Deflater
import java.util.zip.Inflater
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Sink

private final val PADDED_EMPTY_CHUNK: ByteArray = new byte[]{0, 0, 0, -1, -1}
private final val EMPTY_CHUNK: ByteArray = new byte[]{0, 0, -1, -1}

internal fun Deflater.deflateFully(data: ByteArray): ByteArray {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.ClassCastException: class org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent cannot be cast to class org.jetbrains.java.decompiler.modules.decompiler.exps.IfExprent (org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent and org.jetbrains.java.decompiler.modules.decompiler.exps.IfExprent are in unnamed module of loader 'app')
   //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.initExprents(IfStatement.java:276)
   //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:189)
   //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
   //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.initStatementExprents(ExprProcessor.java:192)
   //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:148)
   //
   // Bytecode:
   // 00: aload 0
   // 01: ldc "<this>"
   // 03: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
   // 06: aload 1
   // 07: ldc "data"
   // 09: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNullParameter (Ljava/lang/Object;Ljava/lang/String;)V
   // 0c: aload 0
   // 0d: aload 1
   // 0e: invokevirtual java/util/zip/Deflater.setInput ([B)V
   // 11: bipush 0
   // 12: istore 3
   // 13: new kotlinx/io/Buffer
   // 16: dup
   // 17: invokespecial kotlinx/io/Buffer.<init> ()V
   // 1a: astore 4
   // 1c: aload 4
   // 1e: checkcast kotlinx/io/Sink
   // 21: astore 5
   // 23: bipush 0
   // 24: istore 6
   // 26: invokestatic io/ktor/util/cio/ByteBufferPoolKt.getKtorDefaultPool ()Lio/ktor/utils/io/pool/ObjectPool;
   // 29: astore 7
   // 2b: bipush 0
   // 2c: istore 8
   // 2e: aload 7
   // 30: invokeinterface io/ktor/utils/io/pool/ObjectPool.borrow ()Ljava/lang/Object; 1
   // 35: astore 9
   // 37: nop
   // 38: aload 9
   // 3a: checkcast java/nio/ByteBuffer
   // 3d: astore 10
   // 3f: bipush 0
   // 40: istore 11
   // 42: aload 0
   // 43: invokevirtual java/util/zip/Deflater.needsInput ()Z
   // 46: ifne 56
   // 49: aload 5
   // 4b: aload 0
   // 4c: aload 10
   // 4e: bipush 0
   // 4f: invokestatic io/ktor/websocket/internals/DeflaterUtilsKt.deflateTo (Lkotlinx/io/Sink;Ljava/util/zip/Deflater;Ljava/nio/ByteBuffer;Z)I
   // 52: pop
   // 53: goto 42
   // 56: aload 5
   // 58: aload 0
   // 59: aload 10
   // 5b: bipush 1
   // 5c: invokestatic io/ktor/websocket/internals/DeflaterUtilsKt.deflateTo (Lkotlinx/io/Sink;Ljava/util/zip/Deflater;Ljava/nio/ByteBuffer;Z)I
   // 5f: ifne 56
   // 62: nop
   // 63: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 66: astore 12
   // 68: aload 7
   // 6a: aload 9
   // 6c: invokeinterface io/ktor/utils/io/pool/ObjectPool.recycle (Ljava/lang/Object;)V 2
   // 71: goto 82
   // 74: astore 10
   // 76: aload 7
   // 78: aload 9
   // 7a: invokeinterface io/ktor/utils/io/pool/ObjectPool.recycle (Ljava/lang/Object;)V 2
   // 7f: aload 10
   // 81: athrow
   // 82: nop
   // 83: nop
   // 84: aload 4
   // 86: checkcast kotlinx/io/Source
   // 89: astore 2
   // 8a: aload 2
   // 8b: getstatic io/ktor/websocket/internals/DeflaterUtilsKt.PADDED_EMPTY_CHUNK [B
   // 8e: invokestatic io/ktor/websocket/internals/BytePacketUtilsKt.endsWith (Lkotlinx/io/Source;[B)Z
   // 91: ifeq b2
   // 94: aload 2
   // 95: aload 2
   // 96: invokestatic io/ktor/utils/io/core/ByteReadPacketKt.getRemaining (Lkotlinx/io/Source;)J
   // 99: l2i
   // 9a: getstatic io/ktor/websocket/internals/DeflaterUtilsKt.EMPTY_CHUNK [B
   // 9d: arraylength
   // 9e: isub
   // 9f: invokestatic kotlinx/io/SourcesKt.readByteArray (Lkotlinx/io/Source;I)[B
   // a2: astore 3
   // a3: aload 3
   // a4: astore 4
   // a6: bipush 0
   // a7: istore 5
   // a9: aload 2
   // aa: invokeinterface kotlinx/io/Source.close ()V 1
   // af: nop
   // b0: aload 3
   // b1: areturn
   // b2: bipush 0
   // b3: istore 3
   // b4: new kotlinx/io/Buffer
   // b7: dup
   // b8: invokespecial kotlinx/io/Buffer.<init> ()V
   // bb: astore 4
   // bd: aload 4
   // bf: checkcast kotlinx/io/Sink
   // c2: astore 5
   // c4: bipush 0
   // c5: istore 6
   // c7: aload 5
   // c9: aload 2
   // ca: invokestatic io/ktor/utils/io/core/BytePacketBuilderKt.writePacket (Lkotlinx/io/Sink;Lkotlinx/io/Source;)V
   // cd: aload 5
   // cf: bipush 0
   // d0: invokeinterface kotlinx/io/Sink.writeByte (B)V 2
   // d5: nop
   // d6: nop
   // d7: aload 4
   // d9: checkcast kotlinx/io/Source
   // dc: invokestatic kotlinx/io/SourcesKt.readByteArray (Lkotlinx/io/Source;)[B
   // df: areturn
}

internal fun Inflater.inflateFully(data: ByteArray): ByteArray {
   label24: {
      val dataToInflate: ByteArray = ArraysKt.plus(data, EMPTY_CHUNK);
      `$this$inflateFully`.setInput(dataToInflate);
      val `$this$inflateFully_u24lambda_u240`: Sink = new Buffer();
      val `$this$useInstance$iv`: ObjectPool = ByteBufferPoolKt.getKtorDefaultPool();
      val `instance$iv`: Any = `$this$useInstance$iv`.borrow();

      try {
         val buffer: ByteBuffer = `instance$iv` as ByteBuffer;
         val limit: Long = dataToInflate.length + `$this$inflateFully`.getBytesRead();

         while ($this$inflateFully.getBytesRead() < limit) {
            ((java.nio.Buffer)buffer).clear();
            ((java.nio.Buffer)buffer).position(buffer.position() + `$this$inflateFully`.inflate(buffer.array(), buffer.position(), buffer.limit()));
            ((java.nio.Buffer)buffer).flip();
            BytePacketBuilderExtensions_jvmKt.writeFully(`$this$inflateFully_u24lambda_u240`, buffer);
         }
      } catch (var18: java.lang.Throwable) {
         `$this$useInstance$iv`.recycle(`instance$iv`);
      }

      `$this$useInstance$iv`.recycle(`instance$iv`);
   }
}

private fun Sink.deflateTo(deflater: Deflater, buffer: ByteBuffer, flush: Boolean): Int {
   ((java.nio.Buffer)buffer).clear();
   val deflated: Int = if (flush)
      deflater.deflate(buffer.array(), buffer.position(), buffer.limit(), 2)
      else
      deflater.deflate(buffer.array(), buffer.position(), buffer.limit());
   if (deflated == 0) {
      return 0;
   } else {
      ((java.nio.Buffer)buffer).position(buffer.position() + deflated);
      ((java.nio.Buffer)buffer).flip();
      BytePacketBuilderExtensions_jvmKt.writeFully(`$this$deflateTo`, buffer);
      return deflated;
   }
}
