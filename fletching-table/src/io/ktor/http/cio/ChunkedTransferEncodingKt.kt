@file:SourceDebugExtension(["SMAP\nChunkedTransferEncoding.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChunkedTransferEncoding.kt\nio/ktor/http/cio/ChunkedTransferEncodingKt\n+ 2 ByteReadChannelOperations.kt\nio/ktor/utils/io/ByteReadChannelOperationsKt\n+ 3 UnsafeBufferOperations.kt\nkotlinx/io/unsafe/UnsafeBufferOperations\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,173:1\n498#2,6:174\n504#2,2:184\n508#2:192\n99#3:180\n100#3,2:182\n102#3,6:186\n1#4:181\n*S KotlinDebug\n*F\n+ 1 ChunkedTransferEncoding.kt\nio/ktor/http/cio/ChunkedTransferEncodingKt\n*L\n133#1:174,6\n133#1:184,2\n133#1:192\n133#1:180\n133#1:182,2\n133#1:186,6\n133#1:181\n*E\n"])

package io.ktor.http.cio

import io.ktor.http.cio.ChunkedTransferEncodingKt.ChunkSizeBufferPool.1
import io.ktor.http.cio.internals.CharsKt
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import io.ktor.utils.io.ReaderJob
import io.ktor.utils.io.WriterJob
import io.ktor.utils.io.core.StringsKt
import io.ktor.utils.io.pool.ObjectPool
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

private const val MAX_CHUNK_SIZE_LENGTH: Int = 128
private const val CHUNK_BUFFER_POOL_SIZE: Int = 2048
private final val ChunkSizeBufferPool: ObjectPool<StringBuilder> = (new 1()) as ObjectPool
private const val CrLfShort: Short = 3338
private final val CrLf: ByteArray = StringsKt.toByteArray$default("\r\n", null, 1, null)
private final val LastChunkBytes: ByteArray = StringsKt.toByteArray$default("0\r\n\r\n", null, 1, null)

@Deprecated(message = "Specify content length if known or pass -1L", replaceWith = @ReplaceWith(expression = "decodeChunked(input, -1L)", imports = []), level = DeprecationLevel.ERROR)
public fun CoroutineScope.decodeChunked(input: ByteReadChannel): WriterJob {
   return decodeChunked(`$this$decodeChunked`, input, -1L);
}

public fun CoroutineScope.decodeChunked(input: ByteReadChannel, contentLength: Long): WriterJob {
   return ByteWriteChannelOperationsKt.writer$default(
      `$this$decodeChunked`,
      `$this$decodeChunked`.getCoroutineContext(),
      false,
      new io.ktor.http.cio.ChunkedTransferEncodingKt.decodeChunked.1(input, null),
      2,
      null
   );
}

public suspend fun decodeChunked(input: ByteReadChannel, out: ByteWriteChannel) {
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
   // 001: instanceof io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2
   // 004: ifeq 027
   // 007: aload 2
   // 008: checkcast io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2
   // 00b: astore 9
   // 00d: aload 9
   // 00f: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 012: ldc -2147483648
   // 014: iand
   // 015: ifeq 027
   // 018: aload 9
   // 01a: dup
   // 01b: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 01e: ldc -2147483648
   // 020: isub
   // 021: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 024: goto 031
   // 027: new io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2
   // 02a: dup
   // 02b: aload 2
   // 02c: invokespecial io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.<init> (Lkotlin/coroutines/Continuation;)V
   // 02f: astore 9
   // 031: aload 9
   // 033: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.result Ljava/lang/Object;
   // 036: astore 8
   // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03b: astore 10
   // 03d: aload 9
   // 03f: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 042: tableswitch 910 0 6 42 119 294 399 522 719 851
   // 06c: aload 8
   // 06e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 071: getstatic io/ktor/http/cio/ChunkedTransferEncodingKt.ChunkSizeBufferPool Lio/ktor/utils/io/pool/ObjectPool;
   // 074: invokeinterface io/ktor/utils/io/pool/ObjectPool.borrow ()Ljava/lang/Object; 1
   // 079: checkcast java/lang/StringBuilder
   // 07c: astore 3
   // 07d: lconst_0
   // 07e: lstore 4
   // 080: nop
   // 081: aload 0
   // 082: aload 3
   // 083: checkcast java/lang/Appendable
   // 086: sipush 128
   // 089: invokestatic io/ktor/http/cio/HttpParserKt.getHttpLineEndings ()I
   // 08c: aload 9
   // 08e: aload 9
   // 090: aload 0
   // 091: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 094: aload 9
   // 096: aload 1
   // 097: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 09a: aload 9
   // 09c: aload 3
   // 09d: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 0a0: aload 9
   // 0a2: lload 4
   // 0a4: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 0a7: aload 9
   // 0a9: bipush 1
   // 0aa: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 0ad: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.readUTF8LineTo-RRvyBJ8 (Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 0b0: dup
   // 0b1: aload 10
   // 0b3: if_acmpne 0e3
   // 0b6: aload 10
   // 0b8: areturn
   // 0b9: aload 9
   // 0bb: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 0be: lstore 4
   // 0c0: aload 9
   // 0c2: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 0c5: checkcast java/lang/StringBuilder
   // 0c8: astore 3
   // 0c9: aload 9
   // 0cb: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 0ce: checkcast io/ktor/utils/io/ByteWriteChannel
   // 0d1: astore 1
   // 0d2: aload 9
   // 0d4: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 0d7: checkcast io/ktor/utils/io/ByteReadChannel
   // 0da: astore 0
   // 0db: nop
   // 0dc: aload 8
   // 0de: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 0e1: aload 8
   // 0e3: checkcast java/lang/Boolean
   // 0e6: invokevirtual java/lang/Boolean.booleanValue ()Z
   // 0e9: ifeq 2cf
   // 0ec: aload 3
   // 0ed: checkcast java/lang/CharSequence
   // 0f0: invokeinterface java/lang/CharSequence.length ()I 1
   // 0f5: ifne 0fc
   // 0f8: bipush 1
   // 0f9: goto 0fd
   // 0fc: bipush 0
   // 0fd: ifeq 10a
   // 100: new java/io/EOFException
   // 103: dup
   // 104: ldc "Invalid chunk size: empty"
   // 106: invokespecial java/io/EOFException.<init> (Ljava/lang/String;)V
   // 109: athrow
   // 10a: aload 3
   // 10b: invokevirtual java/lang/StringBuilder.length ()I
   // 10e: bipush 1
   // 10f: if_icmpne 120
   // 112: aload 3
   // 113: bipush 0
   // 114: invokevirtual java/lang/StringBuilder.charAt (I)C
   // 117: bipush 48
   // 119: if_icmpne 120
   // 11c: lconst_0
   // 11d: goto 127
   // 120: aload 3
   // 121: checkcast java/lang/CharSequence
   // 124: invokestatic io/ktor/http/cio/internals/CharsKt.parseHexLong (Ljava/lang/CharSequence;)J
   // 127: lstore 6
   // 129: lload 6
   // 12b: lconst_0
   // 12c: lcmp
   // 12d: ifle 20a
   // 130: aload 0
   // 131: aload 1
   // 132: lload 6
   // 134: aload 9
   // 136: aload 9
   // 138: aload 0
   // 139: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 13c: aload 9
   // 13e: aload 1
   // 13f: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 142: aload 9
   // 144: aload 3
   // 145: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 148: aload 9
   // 14a: lload 4
   // 14c: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 14f: aload 9
   // 151: lload 6
   // 153: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$1 J
   // 156: aload 9
   // 158: bipush 2
   // 159: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 15c: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.copyTo (Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 15f: dup
   // 160: aload 10
   // 162: if_acmpne 199
   // 165: aload 10
   // 167: areturn
   // 168: aload 9
   // 16a: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$1 J
   // 16d: lstore 6
   // 16f: aload 9
   // 171: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 174: lstore 4
   // 176: aload 9
   // 178: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 17b: checkcast java/lang/StringBuilder
   // 17e: astore 3
   // 17f: aload 9
   // 181: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 184: checkcast io/ktor/utils/io/ByteWriteChannel
   // 187: astore 1
   // 188: aload 9
   // 18a: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 18d: checkcast io/ktor/utils/io/ByteReadChannel
   // 190: astore 0
   // 191: nop
   // 192: aload 8
   // 194: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 197: aload 8
   // 199: pop
   // 19a: aload 1
   // 19b: aload 9
   // 19d: aload 9
   // 19f: aload 0
   // 1a0: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 1a3: aload 9
   // 1a5: aload 1
   // 1a6: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 1a9: aload 9
   // 1ab: aload 3
   // 1ac: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 1af: aload 9
   // 1b1: lload 4
   // 1b3: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 1b6: aload 9
   // 1b8: lload 6
   // 1ba: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$1 J
   // 1bd: aload 9
   // 1bf: bipush 3
   // 1c0: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 1c3: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 1c8: dup
   // 1c9: aload 10
   // 1cb: if_acmpne 202
   // 1ce: aload 10
   // 1d0: areturn
   // 1d1: aload 9
   // 1d3: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$1 J
   // 1d6: lstore 6
   // 1d8: aload 9
   // 1da: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 1dd: lstore 4
   // 1df: aload 9
   // 1e1: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 1e4: checkcast java/lang/StringBuilder
   // 1e7: astore 3
   // 1e8: aload 9
   // 1ea: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 1ed: checkcast io/ktor/utils/io/ByteWriteChannel
   // 1f0: astore 1
   // 1f1: aload 9
   // 1f3: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 1f6: checkcast io/ktor/utils/io/ByteReadChannel
   // 1f9: astore 0
   // 1fa: nop
   // 1fb: aload 8
   // 1fd: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 200: aload 8
   // 202: pop
   // 203: lload 4
   // 205: lload 6
   // 207: ladd
   // 208: lstore 4
   // 20a: aload 3
   // 20b: invokestatic kotlin/text/StringsKt.clear (Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;
   // 20e: pop
   // 20f: aload 0
   // 210: aload 3
   // 211: checkcast java/lang/Appendable
   // 214: bipush 2
   // 215: invokestatic io/ktor/http/cio/HttpParserKt.getHttpLineEndings ()I
   // 218: aload 9
   // 21a: aload 9
   // 21c: aload 0
   // 21d: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 220: aload 9
   // 222: aload 1
   // 223: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 226: aload 9
   // 228: aload 3
   // 229: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 22c: aload 9
   // 22e: lload 4
   // 230: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 233: aload 9
   // 235: lload 6
   // 237: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$1 J
   // 23a: aload 9
   // 23c: bipush 4
   // 23d: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 240: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.readUTF8LineTo-RRvyBJ8 (Lio/ktor/utils/io/ByteReadChannel;Ljava/lang/Appendable;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 243: dup
   // 244: aload 10
   // 246: if_acmpne 27d
   // 249: aload 10
   // 24b: areturn
   // 24c: aload 9
   // 24e: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$1 J
   // 251: lstore 6
   // 253: aload 9
   // 255: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 258: lstore 4
   // 25a: aload 9
   // 25c: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 25f: checkcast java/lang/StringBuilder
   // 262: astore 3
   // 263: aload 9
   // 265: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 268: checkcast io/ktor/utils/io/ByteWriteChannel
   // 26b: astore 1
   // 26c: aload 9
   // 26e: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 271: checkcast io/ktor/utils/io/ByteReadChannel
   // 274: astore 0
   // 275: nop
   // 276: aload 8
   // 278: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 27b: aload 8
   // 27d: checkcast java/lang/Boolean
   // 280: invokevirtual java/lang/Boolean.booleanValue ()Z
   // 283: ifne 2a7
   // 286: new java/io/EOFException
   // 289: dup
   // 28a: new java/lang/StringBuilder
   // 28d: dup
   // 28e: invokespecial java/lang/StringBuilder.<init> ()V
   // 291: ldc "Invalid chunk: content block of size "
   // 293: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 296: lload 6
   // 298: invokevirtual java/lang/StringBuilder.append (J)Ljava/lang/StringBuilder;
   // 29b: ldc " ended unexpectedly"
   // 29d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
   // 2a0: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
   // 2a3: invokespecial java/io/EOFException.<init> (Ljava/lang/String;)V
   // 2a6: athrow
   // 2a7: aload 3
   // 2a8: checkcast java/lang/CharSequence
   // 2ab: invokeinterface java/lang/CharSequence.length ()I 1
   // 2b0: ifle 2b7
   // 2b3: bipush 1
   // 2b4: goto 2b8
   // 2b7: bipush 0
   // 2b8: ifeq 2c5
   // 2bb: new java/io/EOFException
   // 2be: dup
   // 2bf: ldc "Invalid chunk: content block should end with CR+LF"
   // 2c1: invokespecial java/io/EOFException.<init> (Ljava/lang/String;)V
   // 2c4: athrow
   // 2c5: lload 6
   // 2c7: lconst_0
   // 2c8: lcmp
   // 2c9: ifne 081
   // 2cc: goto 2cf
   // 2cf: getstatic io/ktor/http/cio/ChunkedTransferEncodingKt.ChunkSizeBufferPool Lio/ktor/utils/io/pool/ObjectPool;
   // 2d2: aload 3
   // 2d3: invokeinterface io/ktor/utils/io/pool/ObjectPool.recycle (Ljava/lang/Object;)V 2
   // 2d8: aload 1
   // 2d9: aload 9
   // 2db: aload 9
   // 2dd: aload 0
   // 2de: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2e1: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 2e4: aload 9
   // 2e6: aload 1
   // 2e7: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2ea: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 2ed: aload 9
   // 2ef: aload 3
   // 2f0: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 2f3: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 2f6: aload 9
   // 2f8: lload 4
   // 2fa: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 2fd: aload 9
   // 2ff: bipush 5
   // 300: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 303: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 308: dup
   // 309: aload 10
   // 30b: if_acmpne 33a
   // 30e: aload 10
   // 310: areturn
   // 311: aload 9
   // 313: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 316: lstore 4
   // 318: aload 9
   // 31a: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 31d: checkcast java/lang/StringBuilder
   // 320: astore 3
   // 321: aload 9
   // 323: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 326: checkcast io/ktor/utils/io/ByteWriteChannel
   // 329: astore 1
   // 32a: aload 9
   // 32c: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 32f: checkcast io/ktor/utils/io/ByteReadChannel
   // 332: astore 0
   // 333: aload 8
   // 335: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 338: aload 8
   // 33a: pop
   // 33b: goto 3cc
   // 33e: astore 6
   // 340: aload 1
   // 341: aload 6
   // 343: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.close (Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Throwable;)V
   // 346: aload 6
   // 348: athrow
   // 349: astore 6
   // 34b: getstatic io/ktor/http/cio/ChunkedTransferEncodingKt.ChunkSizeBufferPool Lio/ktor/utils/io/pool/ObjectPool;
   // 34e: aload 3
   // 34f: invokeinterface io/ktor/utils/io/pool/ObjectPool.recycle (Ljava/lang/Object;)V 2
   // 354: aload 1
   // 355: aload 9
   // 357: aload 9
   // 359: aload 0
   // 35a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 35d: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 360: aload 9
   // 362: aload 1
   // 363: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 366: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 369: aload 9
   // 36b: aload 3
   // 36c: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 36f: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 372: aload 9
   // 374: aload 6
   // 376: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$3 Ljava/lang/Object;
   // 379: aload 9
   // 37b: lload 4
   // 37d: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 380: aload 9
   // 382: bipush 6
   // 384: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.label I
   // 387: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 38c: dup
   // 38d: aload 10
   // 38f: if_acmpne 3c8
   // 392: aload 10
   // 394: areturn
   // 395: aload 9
   // 397: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.J$0 J
   // 39a: lstore 4
   // 39c: aload 9
   // 39e: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$3 Ljava/lang/Object;
   // 3a1: checkcast java/lang/Throwable
   // 3a4: astore 6
   // 3a6: aload 9
   // 3a8: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$2 Ljava/lang/Object;
   // 3ab: checkcast java/lang/StringBuilder
   // 3ae: astore 3
   // 3af: aload 9
   // 3b1: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$1 Ljava/lang/Object;
   // 3b4: checkcast io/ktor/utils/io/ByteWriteChannel
   // 3b7: astore 1
   // 3b8: aload 9
   // 3ba: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$decodeChunked$2.L$0 Ljava/lang/Object;
   // 3bd: checkcast io/ktor/utils/io/ByteReadChannel
   // 3c0: astore 0
   // 3c1: aload 8
   // 3c3: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 3c6: aload 8
   // 3c8: pop
   // 3c9: aload 6
   // 3cb: athrow
   // 3cc: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 3cf: areturn
   // 3d0: new java/lang/IllegalStateException
   // 3d3: dup
   // 3d4: ldc "call to 'resume' before 'invoke' with coroutine"
   // 3d6: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 3d9: athrow
}

public fun encodeChunked(output: ByteWriteChannel, coroutineContext: CoroutineContext): ReaderJob {
   return ByteReadChannelOperationsKt.reader(
      GlobalScope.INSTANCE, coroutineContext, false, new io.ktor.http.cio.ChunkedTransferEncodingKt.encodeChunked.1(output, null)
   );
}

public suspend fun encodeChunked(output: ByteWriteChannel, input: ByteReadChannel) {
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
   // 001: instanceof io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2
   // 004: ifeq 027
   // 007: aload 2
   // 008: checkcast io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2
   // 00b: astore 22
   // 00d: aload 22
   // 00f: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 012: ldc -2147483648
   // 014: iand
   // 015: ifeq 027
   // 018: aload 22
   // 01a: dup
   // 01b: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 01e: ldc -2147483648
   // 020: isub
   // 021: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 024: goto 031
   // 027: new io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2
   // 02a: dup
   // 02b: aload 2
   // 02c: invokespecial io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.<init> (Lkotlin/coroutines/Continuation;)V
   // 02f: astore 22
   // 031: aload 22
   // 033: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.result Ljava/lang/Object;
   // 036: astore 21
   // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03b: astore 23
   // 03d: aload 22
   // 03f: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 042: tableswitch 1144 0 5 38 178 545 895 963 1103
   // 068: aload 21
   // 06a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 06d: nop
   // 06e: aload 1
   // 06f: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 074: ifne 35e
   // 077: aload 1
   // 078: astore 3
   // 079: bipush 0
   // 07a: istore 4
   // 07c: aload 3
   // 07d: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 082: ifne 06e
   // 085: aload 3
   // 086: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 08b: invokeinterface kotlinx/io/Source.exhausted ()Z 1
   // 090: ifeq 11f
   // 093: aload 3
   // 094: bipush 0
   // 095: aload 22
   // 097: bipush 1
   // 098: aconst_null
   // 099: aload 22
   // 09b: aload 0
   // 09c: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 09f: aload 22
   // 0a1: aload 1
   // 0a2: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 0a5: aload 22
   // 0a7: aload 3
   // 0a8: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$2 Ljava/lang/Object;
   // 0ab: aload 22
   // 0ad: aconst_null
   // 0ae: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$3 Ljava/lang/Object;
   // 0b1: aload 22
   // 0b3: aconst_null
   // 0b4: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$4 Ljava/lang/Object;
   // 0b7: aload 22
   // 0b9: aconst_null
   // 0ba: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$5 Ljava/lang/Object;
   // 0bd: aload 22
   // 0bf: aconst_null
   // 0c0: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$6 Ljava/lang/Object;
   // 0c3: aload 22
   // 0c5: aconst_null
   // 0c6: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$7 Ljava/lang/Object;
   // 0c9: aload 22
   // 0cb: aconst_null
   // 0cc: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$8 Ljava/lang/Object;
   // 0cf: aload 22
   // 0d1: aconst_null
   // 0d2: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$9 Ljava/lang/Object;
   // 0d5: aload 22
   // 0d7: aconst_null
   // 0d8: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$10 Ljava/lang/Object;
   // 0db: aload 22
   // 0dd: iload 4
   // 0df: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$0 I
   // 0e2: aload 22
   // 0e4: bipush 1
   // 0e5: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 0e8: invokestatic io/ktor/utils/io/ByteReadChannel.awaitContent$default (Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 0eb: dup
   // 0ec: aload 23
   // 0ee: if_acmpne 11e
   // 0f1: aload 23
   // 0f3: areturn
   // 0f4: aload 22
   // 0f6: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$0 I
   // 0f9: istore 4
   // 0fb: aload 22
   // 0fd: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$2 Ljava/lang/Object;
   // 100: checkcast io/ktor/utils/io/ByteReadChannel
   // 103: astore 3
   // 104: aload 22
   // 106: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 109: checkcast io/ktor/utils/io/ByteReadChannel
   // 10c: astore 1
   // 10d: aload 22
   // 10f: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 112: checkcast io/ktor/utils/io/ByteWriteChannel
   // 115: astore 0
   // 116: nop
   // 117: aload 21
   // 119: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 11c: aload 21
   // 11e: pop
   // 11f: aload 3
   // 120: invokeinterface io/ktor/utils/io/ByteReadChannel.isClosedForRead ()Z 1
   // 125: ifne 06e
   // 128: new kotlin/jvm/internal/Ref$IntRef
   // 12b: dup
   // 12c: invokespecial kotlin/jvm/internal/Ref$IntRef.<init> ()V
   // 12f: astore 5
   // 131: getstatic kotlinx/io/unsafe/UnsafeBufferOperations.INSTANCE Lkotlinx/io/unsafe/UnsafeBufferOperations;
   // 134: astore 6
   // 136: aload 3
   // 137: invokeinterface io/ktor/utils/io/ByteReadChannel.getReadBuffer ()Lkotlinx/io/Source; 1
   // 13c: invokeinterface kotlinx/io/Source.getBuffer ()Lkotlinx/io/Buffer; 1
   // 141: astore 7
   // 143: bipush 0
   // 144: istore 8
   // 146: aload 7
   // 148: invokevirtual kotlinx/io/Buffer.exhausted ()Z
   // 14b: ifne 152
   // 14e: bipush 1
   // 14f: goto 153
   // 152: bipush 0
   // 153: ifne 16b
   // 156: bipush 0
   // 157: istore 9
   // 159: ldc_w "Buffer is empty"
   // 15c: astore 9
   // 15e: new java/lang/IllegalArgumentException
   // 161: dup
   // 162: aload 9
   // 164: invokevirtual java/lang/Object.toString ()Ljava/lang/String;
   // 167: invokespecial java/lang/IllegalArgumentException.<init> (Ljava/lang/String;)V
   // 16a: athrow
   // 16b: aload 7
   // 16d: invokevirtual kotlinx/io/Buffer.getHead ()Lkotlinx/io/Segment;
   // 170: dup
   // 171: invokestatic kotlin/jvm/internal/Intrinsics.checkNotNull (Ljava/lang/Object;)V
   // 174: astore 10
   // 176: aload 10
   // 178: bipush 1
   // 179: invokevirtual kotlinx/io/Segment.dataAsByteArray (Z)[B
   // 17c: aload 10
   // 17e: invokevirtual kotlinx/io/Segment.getPos ()I
   // 181: aload 10
   // 183: invokevirtual kotlinx/io/Segment.getLimit ()I
   // 186: istore 11
   // 188: istore 12
   // 18a: astore 13
   // 18c: bipush 0
   // 18d: istore 14
   // 18f: aload 5
   // 191: astore 15
   // 193: aload 13
   // 195: iload 12
   // 197: iload 11
   // 199: aload 22
   // 19b: checkcast kotlin/coroutines/Continuation
   // 19e: astore 16
   // 1a0: istore 17
   // 1a2: istore 18
   // 1a4: astore 19
   // 1a6: bipush 0
   // 1a7: istore 20
   // 1a9: iload 17
   // 1ab: iload 18
   // 1ad: if_icmpne 1b7
   // 1b0: bipush 0
   // 1b1: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxInt (I)Ljava/lang/Integer;
   // 1b4: goto 30f
   // 1b7: aload 0
   // 1b8: aload 19
   // 1ba: iload 18
   // 1bc: iload 17
   // 1be: aload 22
   // 1c0: aload 22
   // 1c2: aload 0
   // 1c3: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 1c6: aload 22
   // 1c8: aload 1
   // 1c9: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 1cc: aload 22
   // 1ce: aload 3
   // 1cf: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1d2: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$2 Ljava/lang/Object;
   // 1d5: aload 22
   // 1d7: aload 5
   // 1d9: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$3 Ljava/lang/Object;
   // 1dc: aload 22
   // 1de: aload 6
   // 1e0: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1e3: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$4 Ljava/lang/Object;
   // 1e6: aload 22
   // 1e8: aload 7
   // 1ea: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$5 Ljava/lang/Object;
   // 1ed: aload 22
   // 1ef: aload 10
   // 1f1: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$6 Ljava/lang/Object;
   // 1f4: aload 22
   // 1f6: aload 13
   // 1f8: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 1fb: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$7 Ljava/lang/Object;
   // 1fe: aload 22
   // 200: aload 15
   // 202: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$8 Ljava/lang/Object;
   // 205: aload 22
   // 207: aload 16
   // 209: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 20c: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$9 Ljava/lang/Object;
   // 20f: aload 22
   // 211: aload 19
   // 213: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 216: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$10 Ljava/lang/Object;
   // 219: aload 22
   // 21b: iload 4
   // 21d: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$0 I
   // 220: aload 22
   // 222: iload 8
   // 224: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$1 I
   // 227: aload 22
   // 229: iload 11
   // 22b: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$2 I
   // 22e: aload 22
   // 230: iload 12
   // 232: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$3 I
   // 235: aload 22
   // 237: iload 14
   // 239: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$4 I
   // 23c: aload 22
   // 23e: iload 17
   // 240: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$5 I
   // 243: aload 22
   // 245: iload 18
   // 247: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$6 I
   // 24a: aload 22
   // 24c: iload 20
   // 24e: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$7 I
   // 251: aload 22
   // 253: bipush 2
   // 254: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 257: invokestatic io/ktor/http/cio/ChunkedTransferEncodingKt.access$writeChunk (Lio/ktor/utils/io/ByteWriteChannel;[BIILkotlin/coroutines/Continuation;)Ljava/lang/Object;
   // 25a: dup
   // 25b: aload 23
   // 25d: if_acmpne 30e
   // 260: aload 23
   // 262: areturn
   // 263: aload 22
   // 265: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$7 I
   // 268: istore 20
   // 26a: aload 22
   // 26c: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$6 I
   // 26f: istore 18
   // 271: aload 22
   // 273: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$5 I
   // 276: istore 17
   // 278: aload 22
   // 27a: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$4 I
   // 27d: istore 14
   // 27f: aload 22
   // 281: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$3 I
   // 284: istore 12
   // 286: aload 22
   // 288: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$2 I
   // 28b: istore 11
   // 28d: aload 22
   // 28f: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$1 I
   // 292: istore 8
   // 294: aload 22
   // 296: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.I$0 I
   // 299: istore 4
   // 29b: aload 22
   // 29d: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$10 Ljava/lang/Object;
   // 2a0: checkcast [B
   // 2a3: astore 19
   // 2a5: aload 22
   // 2a7: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$9 Ljava/lang/Object;
   // 2aa: checkcast kotlin/coroutines/Continuation
   // 2ad: astore 16
   // 2af: aload 22
   // 2b1: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$8 Ljava/lang/Object;
   // 2b4: checkcast kotlin/jvm/internal/Ref$IntRef
   // 2b7: astore 15
   // 2b9: aload 22
   // 2bb: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$7 Ljava/lang/Object;
   // 2be: checkcast [B
   // 2c1: astore 13
   // 2c3: aload 22
   // 2c5: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$6 Ljava/lang/Object;
   // 2c8: checkcast kotlinx/io/Segment
   // 2cb: astore 10
   // 2cd: aload 22
   // 2cf: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$5 Ljava/lang/Object;
   // 2d2: checkcast kotlinx/io/Buffer
   // 2d5: astore 7
   // 2d7: aload 22
   // 2d9: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$4 Ljava/lang/Object;
   // 2dc: checkcast kotlinx/io/unsafe/UnsafeBufferOperations
   // 2df: astore 6
   // 2e1: aload 22
   // 2e3: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$3 Ljava/lang/Object;
   // 2e6: checkcast kotlin/jvm/internal/Ref$IntRef
   // 2e9: astore 5
   // 2eb: aload 22
   // 2ed: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$2 Ljava/lang/Object;
   // 2f0: checkcast io/ktor/utils/io/ByteReadChannel
   // 2f3: astore 3
   // 2f4: aload 22
   // 2f6: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 2f9: checkcast io/ktor/utils/io/ByteReadChannel
   // 2fc: astore 1
   // 2fd: aload 22
   // 2ff: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 302: checkcast io/ktor/utils/io/ByteWriteChannel
   // 305: astore 0
   // 306: nop
   // 307: aload 21
   // 309: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 30c: aload 21
   // 30e: nop
   // 30f: aload 15
   // 311: swap
   // 312: checkcast java/lang/Number
   // 315: invokevirtual java/lang/Number.intValue ()I
   // 318: putfield kotlin/jvm/internal/Ref$IntRef.element I
   // 31b: aload 5
   // 31d: getfield kotlin/jvm/internal/Ref$IntRef.element I
   // 320: istore 9
   // 322: iload 9
   // 324: ifeq 354
   // 327: iload 9
   // 329: ifge 337
   // 32c: new java/lang/IllegalStateException
   // 32f: dup
   // 330: ldc_w "Returned negative read bytes count"
   // 333: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 336: athrow
   // 337: iload 9
   // 339: aload 10
   // 33b: invokevirtual kotlinx/io/Segment.getSize ()I
   // 33e: if_icmple 34c
   // 341: new java/lang/IllegalStateException
   // 344: dup
   // 345: ldc_w "Returned too many bytes"
   // 348: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 34b: athrow
   // 34c: aload 7
   // 34e: iload 9
   // 350: i2l
   // 351: invokevirtual kotlinx/io/Buffer.skip (J)V
   // 354: nop
   // 355: aload 5
   // 357: getfield kotlin/jvm/internal/Ref$IntRef.element I
   // 35a: pop
   // 35b: goto 06e
   // 35e: aload 1
   // 35f: invokestatic io/ktor/http/cio/ChunkedTransferEncodingKt.rethrowCloseCause (Lio/ktor/utils/io/ByteReadChannel;)V
   // 362: aload 0
   // 363: getstatic io/ktor/http/cio/ChunkedTransferEncodingKt.LastChunkBytes [B
   // 366: bipush 0
   // 367: bipush 0
   // 368: aload 22
   // 36a: bipush 6
   // 36c: aconst_null
   // 36d: aload 22
   // 36f: aload 0
   // 370: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 373: aload 22
   // 375: aload 1
   // 376: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 379: aload 22
   // 37b: aconst_null
   // 37c: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$2 Ljava/lang/Object;
   // 37f: aload 22
   // 381: aconst_null
   // 382: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$3 Ljava/lang/Object;
   // 385: aload 22
   // 387: aconst_null
   // 388: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$4 Ljava/lang/Object;
   // 38b: aload 22
   // 38d: aconst_null
   // 38e: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$5 Ljava/lang/Object;
   // 391: aload 22
   // 393: aconst_null
   // 394: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$6 Ljava/lang/Object;
   // 397: aload 22
   // 399: aconst_null
   // 39a: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$7 Ljava/lang/Object;
   // 39d: aload 22
   // 39f: aconst_null
   // 3a0: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$8 Ljava/lang/Object;
   // 3a3: aload 22
   // 3a5: aconst_null
   // 3a6: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$9 Ljava/lang/Object;
   // 3a9: aload 22
   // 3ab: aconst_null
   // 3ac: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$10 Ljava/lang/Object;
   // 3af: aload 22
   // 3b1: bipush 3
   // 3b2: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 3b5: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.writeFully$default (Lio/ktor/utils/io/ByteWriteChannel;[BIILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 3b8: dup
   // 3b9: aload 23
   // 3bb: if_acmpne 3db
   // 3be: aload 23
   // 3c0: areturn
   // 3c1: aload 22
   // 3c3: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 3c6: checkcast io/ktor/utils/io/ByteReadChannel
   // 3c9: astore 1
   // 3ca: aload 22
   // 3cc: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 3cf: checkcast io/ktor/utils/io/ByteWriteChannel
   // 3d2: astore 0
   // 3d3: nop
   // 3d4: aload 21
   // 3d6: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 3d9: aload 21
   // 3db: pop
   // 3dc: aload 0
   // 3dd: aload 22
   // 3df: aload 22
   // 3e1: aload 0
   // 3e2: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 3e5: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 3e8: aload 22
   // 3ea: aload 1
   // 3eb: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 3ee: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 3f1: aload 22
   // 3f3: bipush 4
   // 3f4: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 3f7: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 3fc: dup
   // 3fd: aload 23
   // 3ff: if_acmpne 41e
   // 402: aload 23
   // 404: areturn
   // 405: aload 22
   // 407: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 40a: checkcast io/ktor/utils/io/ByteReadChannel
   // 40d: astore 1
   // 40e: aload 22
   // 410: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 413: checkcast io/ktor/utils/io/ByteWriteChannel
   // 416: astore 0
   // 417: aload 21
   // 419: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 41c: aload 21
   // 41e: pop
   // 41f: goto 4b6
   // 422: astore 3
   // 423: aload 0
   // 424: aload 3
   // 425: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.close (Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Throwable;)V
   // 428: aload 1
   // 429: aload 3
   // 42a: invokeinterface io/ktor/utils/io/ByteReadChannel.cancel (Ljava/lang/Throwable;)V 2
   // 42f: aload 3
   // 430: athrow
   // 431: astore 3
   // 432: aload 0
   // 433: aload 22
   // 435: aload 22
   // 437: aload 0
   // 438: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 43b: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 43e: aload 22
   // 440: aload 1
   // 441: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 444: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 447: aload 22
   // 449: aload 3
   // 44a: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$2 Ljava/lang/Object;
   // 44d: aload 22
   // 44f: aconst_null
   // 450: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$3 Ljava/lang/Object;
   // 453: aload 22
   // 455: aconst_null
   // 456: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$4 Ljava/lang/Object;
   // 459: aload 22
   // 45b: aconst_null
   // 45c: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$5 Ljava/lang/Object;
   // 45f: aload 22
   // 461: aconst_null
   // 462: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$6 Ljava/lang/Object;
   // 465: aload 22
   // 467: aconst_null
   // 468: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$7 Ljava/lang/Object;
   // 46b: aload 22
   // 46d: aconst_null
   // 46e: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$8 Ljava/lang/Object;
   // 471: aload 22
   // 473: aconst_null
   // 474: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$9 Ljava/lang/Object;
   // 477: aload 22
   // 479: aconst_null
   // 47a: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$10 Ljava/lang/Object;
   // 47d: aload 22
   // 47f: bipush 5
   // 480: putfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.label I
   // 483: invokeinterface io/ktor/utils/io/ByteWriteChannel.flush (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
   // 488: dup
   // 489: aload 23
   // 48b: if_acmpne 4b3
   // 48e: aload 23
   // 490: areturn
   // 491: aload 22
   // 493: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$2 Ljava/lang/Object;
   // 496: checkcast java/lang/Throwable
   // 499: astore 3
   // 49a: aload 22
   // 49c: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$1 Ljava/lang/Object;
   // 49f: checkcast io/ktor/utils/io/ByteReadChannel
   // 4a2: astore 1
   // 4a3: aload 22
   // 4a5: getfield io/ktor/http/cio/ChunkedTransferEncodingKt$encodeChunked$2.L$0 Ljava/lang/Object;
   // 4a8: checkcast io/ktor/utils/io/ByteWriteChannel
   // 4ab: astore 0
   // 4ac: aload 21
   // 4ae: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 4b1: aload 21
   // 4b3: pop
   // 4b4: aload 3
   // 4b5: athrow
   // 4b6: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 4b9: areturn
   // 4ba: new java/lang/IllegalStateException
   // 4bd: dup
   // 4be: ldc "call to 'resume' before 'invoke' with coroutine"
   // 4c0: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 4c3: athrow
}

private fun ByteReadChannel.rethrowCloseCause() {
   val cause: java.lang.Throwable = if (`$this$rethrowCloseCause` is ByteChannel) (`$this$rethrowCloseCause` as ByteChannel).getClosedCause() else null;
   if (cause != null) {
      throw cause;
   }
}

private suspend fun ByteWriteChannel.writeChunk(memory: ByteArray, startIndex: Int, endIndex: Int): Int {
   var `$continuation`: Continuation;
   label57: {
      if (`$completion` is io.ktor.http.cio.ChunkedTransferEncodingKt.writeChunk.1) {
         `$continuation` = `$completion` as io.ktor.http.cio.ChunkedTransferEncodingKt.writeChunk.1;
         if (((`$completion` as io.ktor.http.cio.ChunkedTransferEncodingKt.writeChunk.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label57;
         }
      }

      `$continuation` = new io.ktor.http.cio.ChunkedTransferEncodingKt.writeChunk.1(`$completion`);
   }

   var var8: Any;
   var size: Int;
   label66: {
      label46: {
         label45: {
            val `$result`: Any = `$continuation`.result;
            var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch ($continuation.label) {
               case 0:
                  ResultKt.throwOnFailure(`$result`);
                  size = endIndex - startIndex;
                  `$continuation`.L$0 = `$this$writeChunk`;
                  `$continuation`.L$1 = memory;
                  `$continuation`.I$0 = startIndex;
                  `$continuation`.I$1 = endIndex;
                  `$continuation`.I$2 = size;
                  `$continuation`.label = 1;
                  if (CharsKt.writeIntHex(`$this$writeChunk`, size, `$continuation`) === var8) {
                     return var8;
                  }
                  break;
               case 1:
                  size = `$continuation`.I$2;
                  endIndex = `$continuation`.I$1;
                  startIndex = `$continuation`.I$0;
                  memory = `$continuation`.L$1 as ByteArray;
                  `$this$writeChunk` = `$continuation`.L$0 as ByteWriteChannel;
                  ResultKt.throwOnFailure(`$result`);
                  break;
               case 2:
                  size = `$continuation`.I$2;
                  endIndex = `$continuation`.I$1;
                  startIndex = `$continuation`.I$0;
                  memory = `$continuation`.L$1 as ByteArray;
                  `$this$writeChunk` = `$continuation`.L$0 as ByteWriteChannel;
                  ResultKt.throwOnFailure(`$result`);
                  break label45;
               case 3:
                  size = `$continuation`.I$2;
                  endIndex = `$continuation`.I$1;
                  startIndex = `$continuation`.I$0;
                  memory = `$continuation`.L$1 as ByteArray;
                  `$this$writeChunk` = `$continuation`.L$0 as ByteWriteChannel;
                  ResultKt.throwOnFailure(`$result`);
                  break label46;
               case 4:
                  size = `$continuation`.I$2;
                  endIndex = `$continuation`.I$1;
                  startIndex = `$continuation`.I$0;
                  memory = `$continuation`.L$1 as ByteArray;
                  `$this$writeChunk` = `$continuation`.L$0 as ByteWriteChannel;
                  ResultKt.throwOnFailure(`$result`);
                  break label66;
               case 5:
                  size = `$continuation`.I$2;
                  endIndex = `$continuation`.I$1;
                  startIndex = `$continuation`.I$0;
                  memory = `$continuation`.L$1 as ByteArray;
                  `$this$writeChunk` = `$continuation`.L$0 as ByteWriteChannel;
                  ResultKt.throwOnFailure(`$result`);
                  return Boxing.boxInt(size);
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            `$continuation`.L$0 = `$this$writeChunk`;
            `$continuation`.L$1 = memory;
            `$continuation`.I$0 = startIndex;
            `$continuation`.I$1 = endIndex;
            `$continuation`.I$2 = size;
            `$continuation`.label = 2;
            if (ByteWriteChannelOperationsKt.writeShort(`$this$writeChunk`, (short)3338, `$continuation`) === var8) {
               return var8;
            }
         }

         `$continuation`.L$0 = `$this$writeChunk`;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(memory);
         `$continuation`.I$0 = startIndex;
         `$continuation`.I$1 = endIndex;
         `$continuation`.I$2 = size;
         `$continuation`.label = 3;
         if (ByteWriteChannelOperationsKt.writeFully(`$this$writeChunk`, memory, startIndex, endIndex, `$continuation`) === var8) {
            return var8;
         }
      }

      val var10001: ByteArray = CrLf;
      `$continuation`.L$0 = `$this$writeChunk`;
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(memory);
      `$continuation`.I$0 = startIndex;
      `$continuation`.I$1 = endIndex;
      `$continuation`.I$2 = size;
      `$continuation`.label = 4;
      if (ByteWriteChannelOperationsKt.writeFully$default(`$this$writeChunk`, var10001, 0, 0, `$continuation`, 6, null) === var8) {
         return var8;
      }
   }

   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$writeChunk`);
   `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(memory);
   `$continuation`.I$0 = startIndex;
   `$continuation`.I$1 = endIndex;
   `$continuation`.I$2 = size;
   `$continuation`.label = 5;
   return if (`$this$writeChunk`.flush(`$continuation`) === var8) var8 else Boxing.boxInt(size);
}

@JvmSynthetic
fun `access$writeChunk`(`$receiver`: ByteWriteChannel, memory: ByteArray, startIndex: Int, endIndex: Int, `$completion`: Continuation): Any {
   return writeChunk(`$receiver`, memory, startIndex, endIndex, `$completion`);
}
