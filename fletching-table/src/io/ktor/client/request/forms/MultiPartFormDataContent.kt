package io.ktor.client.request.forms

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.PartData
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.core.BytePacketBuilderKt
import io.ktor.utils.io.core.StringsKt
import java.util.ArrayList
import java.util.Map.Entry
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesKt

@SourceDebugExtension(["SMAP\nFormDataContent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FormDataContent.kt\nio/ktor/client/request/forms/MultiPartFormDataContent\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n*L\n1#1,170:1\n1563#2:171\n1634#2,2:172\n1636#2:177\n21#3,3:174\n21#3,3:178\n*S KotlinDebug\n*F\n+ 1 FormDataContent.kt\nio/ktor/client/request/forms/MultiPartFormDataContent\n*L\n60#1:171\n60#1:172,2\n60#1:177\n80#1:174,3\n81#1:178,3\n*E\n"])
public class MultiPartFormDataContent(parts: List<PartData>,
      boundary: String = FormDataContentKt.access$generateBoundary(),
      contentType: ContentType = ContentType.MultiPart.INSTANCE.getFormData().withParameter("boundary", boundary)
   )
   : OutgoingContent.WriteChannelContent {
   public final val boundary: String
   public open val contentType: ContentType
   private final val BOUNDARY_BYTES: ByteArray
   private final val LAST_BOUNDARY_BYTES: ByteArray
   private final val BODY_OVERHEAD_SIZE: Int
   private final val PART_OVERHEAD_SIZE: Int
   private final val rawParts: List<PreparedPart>

   public open var contentLength: Long?
      private set

   init {
      this.boundary = boundary;
      this.contentType = contentType;
      this.BOUNDARY_BYTES = StringsKt.toByteArray$default("--${this.boundary}\r\n", null, 1, null);
      this.LAST_BOUNDARY_BYTES = StringsKt.toByteArray$default("--${this.boundary}--\r\n", null, 1, null);
      this.BODY_OVERHEAD_SIZE = this.LAST_BOUNDARY_BYTES.length;
      this.PART_OVERHEAD_SIZE = FormDataContentKt.access$getRN_BYTES$p().length * 2 + this.BOUNDARY_BYTES.length;
      val rawLength: java.lang.Iterable = parts;
      val size: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(parts, 10));

      for (Object item$iv$iv : rawLength) {
         val part: PartData = `item$iv$iv` as PartData;
         val headersBuilder: Sink = BytePacketBuilderKt.BytePacketBuilder();

         for (Entry var15 : part.getHeaders().entries()) {
            StringsKt.writeText$default(
               headersBuilder,
               "${var15.getKey() as java.lang.String}: ${CollectionsKt.joinToString$default(
                  var15.getValue() as java.util.List, "; ", null, null, 0, null, null, 62, null
               )}",
               0,
               0,
               null,
               14,
               null
            );
            BytePacketBuilderKt.writeFully$default(headersBuilder, FormDataContentKt.access$getRN_BYTES$p(), 0, 0, 6, null);
         }

         val var10000: java.lang.String = part.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
         val var26: java.lang.Long = if (var10000 != null) java.lang.Long.parseLong(var10000) else null;
         val var38: PreparedPart;
         if (part is PartData.FileItem) {
            val var27: ByteArray = SourcesKt.readByteArray(BytePacketBuilderKt.build(headersBuilder));
            var38 = new PreparedPart.ChannelPart(
               var27, (part as PartData.FileItem).getProvider(), if (var26 != null) var26 + (long)this.PART_OVERHEAD_SIZE + (long)var27.length else null
            );
         } else if (part is PartData.BinaryItem) {
            val var28: ByteArray = SourcesKt.readByteArray(BytePacketBuilderKt.build(headersBuilder));
            var38 = new PreparedPart.InputPart(
               var28, (part as PartData.BinaryItem).getProvider(), if (var26 != null) var26 + (long)this.PART_OVERHEAD_SIZE + (long)var28.length else null
            );
         } else if (part is PartData.FormItem) {
            val headers: Buffer = new Buffer();
            StringsKt.writeText$default(headers, (part as PartData.FormItem).getValue(), 0, 0, null, 14, null);
            val var29: ByteArray = SourcesKt.readByteArray(headers);
            val var34: Function0 = MultiPartFormDataContent::rawParts$lambda$0$1;
            if (var26 == null) {
               StringsKt.writeText$default(headersBuilder, "${HttpHeaders.INSTANCE.getContentLength()}: ${var29.length}", 0, 0, null, 14, null);
               BytePacketBuilderKt.writeFully$default(headersBuilder, FormDataContentKt.access$getRN_BYTES$p(), 0, 0, 6, null);
            }

            val var36: ByteArray = SourcesKt.readByteArray(BytePacketBuilderKt.build(headersBuilder));
            var38 = new PreparedPart.InputPart(var36, var34, (long)(var29.length + this.PART_OVERHEAD_SIZE + var36.length));
         } else {
            if (part !is PartData.BinaryChannelItem) {
               throw new NoWhenBranchMatchedException();
            }

            val var30: ByteArray = SourcesKt.readByteArray(BytePacketBuilderKt.build(headersBuilder));
            var38 = new PreparedPart.ChannelPart(
               var30,
               (part as PartData.BinaryChannelItem).getProvider(),
               if (var26 != null) var26 + (long)this.PART_OVERHEAD_SIZE + (long)var30.length else null
            );
         }

         size.add(var38);
      }

      this.rawParts = size as MutableList<PreparedPart>;
      var var23: java.lang.Long = 0L;

      for (PreparedPart part : this.rawParts) {
         val var25: java.lang.Long = part.getSize();
         if (var25 == null) {
            var23 = null;
            break;
         }

         var23 = if (var23 != null) var23 + var25 else null;
      }

      if (var23 != null) {
         var23 = var23 + (long)this.BODY_OVERHEAD_SIZE;
      }

      this.contentLength = var23;
   }

   public override suspend fun writeTo(channel: ByteWriteChannel) {
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
      // 001: instanceof io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1
      // 004: ifeq 029
      // 007: aload 2
      // 008: checkcast io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1
      // 00b: astore 11
      // 00d: aload 11
      // 00f: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 012: ldc_w -2147483648
      // 015: iand
      // 016: ifeq 029
      // 019: aload 11
      // 01b: dup
      // 01c: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 01f: ldc_w -2147483648
      // 022: isub
      // 023: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 026: goto 034
      // 029: new io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1
      // 02c: dup
      // 02d: aload 0
      // 02e: aload 2
      // 02f: invokespecial io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.<init> (Lio/ktor/client/request/forms/MultiPartFormDataContent;Lkotlin/coroutines/Continuation;)V
      // 032: astore 11
      // 034: aload 11
      // 036: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.result Ljava/lang/Object;
      // 039: astore 10
      // 03b: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03e: astore 12
      // 040: aload 11
      // 042: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 045: tableswitch 1087 0 10 59 144 231 316 466 642 754 843 894 977 1055
      // 080: aload 10
      // 082: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 085: nop
      // 086: aload 0
      // 087: getfield io/ktor/client/request/forms/MultiPartFormDataContent.rawParts Ljava/util/List;
      // 08a: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 08f: astore 3
      // 090: aload 3
      // 091: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 096: ifeq 35f
      // 099: aload 3
      // 09a: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 09f: checkcast io/ktor/client/request/forms/PreparedPart
      // 0a2: astore 4
      // 0a4: aload 1
      // 0a5: aload 0
      // 0a6: getfield io/ktor/client/request/forms/MultiPartFormDataContent.BOUNDARY_BYTES [B
      // 0a9: bipush 0
      // 0aa: bipush 0
      // 0ab: aload 11
      // 0ad: bipush 6
      // 0af: aconst_null
      // 0b0: aload 11
      // 0b2: aload 1
      // 0b3: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 0b6: aload 11
      // 0b8: aload 3
      // 0b9: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 0bc: aload 11
      // 0be: aload 4
      // 0c0: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 0c3: aload 11
      // 0c5: bipush 1
      // 0c6: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 0c9: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.writeFully$default (Lio/ktor/utils/io/ByteWriteChannel;[BIILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
      // 0cc: dup
      // 0cd: aload 12
      // 0cf: if_acmpne 0f9
      // 0d2: aload 12
      // 0d4: areturn
      // 0d5: aload 11
      // 0d7: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 0da: checkcast io/ktor/client/request/forms/PreparedPart
      // 0dd: astore 4
      // 0df: aload 11
      // 0e1: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 0e4: checkcast java/util/Iterator
      // 0e7: astore 3
      // 0e8: aload 11
      // 0ea: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 0ed: checkcast io/ktor/utils/io/ByteWriteChannel
      // 0f0: astore 1
      // 0f1: nop
      // 0f2: aload 10
      // 0f4: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0f7: aload 10
      // 0f9: pop
      // 0fa: aload 1
      // 0fb: aload 4
      // 0fd: invokevirtual io/ktor/client/request/forms/PreparedPart.getHeaders ()[B
      // 100: bipush 0
      // 101: bipush 0
      // 102: aload 11
      // 104: bipush 6
      // 106: aconst_null
      // 107: aload 11
      // 109: aload 1
      // 10a: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 10d: aload 11
      // 10f: aload 3
      // 110: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 113: aload 11
      // 115: aload 4
      // 117: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 11a: aload 11
      // 11c: bipush 2
      // 11d: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 120: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.writeFully$default (Lio/ktor/utils/io/ByteWriteChannel;[BIILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
      // 123: dup
      // 124: aload 12
      // 126: if_acmpne 150
      // 129: aload 12
      // 12b: areturn
      // 12c: aload 11
      // 12e: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 131: checkcast io/ktor/client/request/forms/PreparedPart
      // 134: astore 4
      // 136: aload 11
      // 138: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 13b: checkcast java/util/Iterator
      // 13e: astore 3
      // 13f: aload 11
      // 141: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 144: checkcast io/ktor/utils/io/ByteWriteChannel
      // 147: astore 1
      // 148: nop
      // 149: aload 10
      // 14b: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 14e: aload 10
      // 150: pop
      // 151: aload 1
      // 152: invokestatic io/ktor/client/request/forms/FormDataContentKt.access$getRN_BYTES$p ()[B
      // 155: bipush 0
      // 156: bipush 0
      // 157: aload 11
      // 159: bipush 6
      // 15b: aconst_null
      // 15c: aload 11
      // 15e: aload 1
      // 15f: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 162: aload 11
      // 164: aload 3
      // 165: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 168: aload 11
      // 16a: aload 4
      // 16c: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 16f: aload 11
      // 171: bipush 3
      // 172: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 175: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.writeFully$default (Lio/ktor/utils/io/ByteWriteChannel;[BIILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
      // 178: dup
      // 179: aload 12
      // 17b: if_acmpne 1a5
      // 17e: aload 12
      // 180: areturn
      // 181: aload 11
      // 183: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 186: checkcast io/ktor/client/request/forms/PreparedPart
      // 189: astore 4
      // 18b: aload 11
      // 18d: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 190: checkcast java/util/Iterator
      // 193: astore 3
      // 194: aload 11
      // 196: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 199: checkcast io/ktor/utils/io/ByteWriteChannel
      // 19c: astore 1
      // 19d: nop
      // 19e: aload 10
      // 1a0: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 1a3: aload 10
      // 1a5: pop
      // 1a6: aload 4
      // 1a8: astore 5
      // 1aa: aload 5
      // 1ac: instanceof io/ktor/client/request/forms/PreparedPart$InputPart
      // 1af: ifeq 284
      // 1b2: aload 4
      // 1b4: checkcast io/ktor/client/request/forms/PreparedPart$InputPart
      // 1b7: invokevirtual io/ktor/client/request/forms/PreparedPart$InputPart.getProvider ()Lkotlin/jvm/functions/Function0;
      // 1ba: invokeinterface kotlin/jvm/functions/Function0.invoke ()Ljava/lang/Object; 1
      // 1bf: checkcast java/lang/AutoCloseable
      // 1c2: astore 6
      // 1c4: aconst_null
      // 1c5: astore 7
      // 1c7: nop
      // 1c8: aload 6
      // 1ca: checkcast kotlinx/io/Source
      // 1cd: astore 8
      // 1cf: bipush 0
      // 1d0: istore 9
      // 1d2: aload 8
      // 1d4: aload 1
      // 1d5: aload 11
      // 1d7: aload 11
      // 1d9: aload 1
      // 1da: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 1dd: aload 11
      // 1df: aload 3
      // 1e0: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 1e3: aload 11
      // 1e5: aload 4
      // 1e7: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 1ea: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 1ed: aload 11
      // 1ef: aload 6
      // 1f1: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$3 Ljava/lang/Object;
      // 1f4: aload 11
      // 1f6: aload 8
      // 1f8: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 1fb: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$4 Ljava/lang/Object;
      // 1fe: aload 11
      // 200: iload 9
      // 202: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.I$0 I
      // 205: aload 11
      // 207: bipush 4
      // 208: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 20b: invokestatic io/ktor/client/request/forms/FormDataContentKt.access$copyTo (Lkotlinx/io/Source;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 20e: dup
      // 20f: aload 12
      // 211: if_acmpne 259
      // 214: aload 12
      // 216: areturn
      // 217: aload 11
      // 219: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.I$0 I
      // 21c: istore 9
      // 21e: aload 11
      // 220: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$4 Ljava/lang/Object;
      // 223: checkcast kotlinx/io/Source
      // 226: astore 8
      // 228: aconst_null
      // 229: astore 7
      // 22b: aload 11
      // 22d: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$3 Ljava/lang/Object;
      // 230: checkcast java/lang/AutoCloseable
      // 233: astore 6
      // 235: aload 11
      // 237: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 23a: checkcast io/ktor/client/request/forms/PreparedPart
      // 23d: astore 4
      // 23f: aload 11
      // 241: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 244: checkcast java/util/Iterator
      // 247: astore 3
      // 248: aload 11
      // 24a: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 24d: checkcast io/ktor/utils/io/ByteWriteChannel
      // 250: astore 1
      // 251: nop
      // 252: aload 10
      // 254: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 257: aload 10
      // 259: pop
      // 25a: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 25d: astore 8
      // 25f: aload 6
      // 261: aload 7
      // 263: invokestatic kotlin/jdk7/AutoCloseableKt.closeFinally (Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V
      // 266: goto 27e
      // 269: astore 9
      // 26b: aload 9
      // 26d: astore 7
      // 26f: aload 9
      // 271: athrow
      // 272: astore 9
      // 274: aload 6
      // 276: aload 7
      // 278: invokestatic kotlin/jdk7/AutoCloseableKt.closeFinally (Ljava/lang/AutoCloseable;Ljava/lang/Throwable;)V
      // 27b: aload 9
      // 27d: athrow
      // 27e: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 281: goto 2f6
      // 284: aload 5
      // 286: instanceof io/ktor/client/request/forms/PreparedPart$ChannelPart
      // 289: ifeq 2ee
      // 28c: aload 4
      // 28e: checkcast io/ktor/client/request/forms/PreparedPart$ChannelPart
      // 291: invokevirtual io/ktor/client/request/forms/PreparedPart$ChannelPart.getProvider ()Lkotlin/jvm/functions/Function0;
      // 294: invokeinterface kotlin/jvm/functions/Function0.invoke ()Ljava/lang/Object; 1
      // 299: checkcast io/ktor/utils/io/ByteReadChannel
      // 29c: aload 1
      // 29d: aload 11
      // 29f: aload 11
      // 2a1: aload 1
      // 2a2: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 2a5: aload 11
      // 2a7: aload 3
      // 2a8: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 2ab: aload 11
      // 2ad: aload 4
      // 2af: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 2b2: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 2b5: aload 11
      // 2b7: bipush 5
      // 2b8: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 2bb: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.copyTo (Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 2be: dup
      // 2bf: aload 12
      // 2c1: if_acmpne 2eb
      // 2c4: aload 12
      // 2c6: areturn
      // 2c7: aload 11
      // 2c9: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 2cc: checkcast io/ktor/client/request/forms/PreparedPart
      // 2cf: astore 4
      // 2d1: aload 11
      // 2d3: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 2d6: checkcast java/util/Iterator
      // 2d9: astore 3
      // 2da: aload 11
      // 2dc: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 2df: checkcast io/ktor/utils/io/ByteWriteChannel
      // 2e2: astore 1
      // 2e3: nop
      // 2e4: aload 10
      // 2e6: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 2e9: aload 10
      // 2eb: goto 2f6
      // 2ee: new kotlin/NoWhenBranchMatchedException
      // 2f1: dup
      // 2f2: invokespecial kotlin/NoWhenBranchMatchedException.<init> ()V
      // 2f5: athrow
      // 2f6: pop
      // 2f7: aload 1
      // 2f8: invokestatic io/ktor/client/request/forms/FormDataContentKt.access$getRN_BYTES$p ()[B
      // 2fb: bipush 0
      // 2fc: bipush 0
      // 2fd: aload 11
      // 2ff: bipush 6
      // 301: aconst_null
      // 302: aload 11
      // 304: aload 1
      // 305: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 308: aload 11
      // 30a: aload 3
      // 30b: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 30e: aload 11
      // 310: aload 4
      // 312: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 315: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 318: aload 11
      // 31a: aconst_null
      // 31b: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$3 Ljava/lang/Object;
      // 31e: aload 11
      // 320: aconst_null
      // 321: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$4 Ljava/lang/Object;
      // 324: aload 11
      // 326: bipush 6
      // 328: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 32b: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.writeFully$default (Lio/ktor/utils/io/ByteWriteChannel;[BIILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
      // 32e: dup
      // 32f: aload 12
      // 331: if_acmpne 35b
      // 334: aload 12
      // 336: areturn
      // 337: aload 11
      // 339: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 33c: checkcast io/ktor/client/request/forms/PreparedPart
      // 33f: astore 4
      // 341: aload 11
      // 343: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 346: checkcast java/util/Iterator
      // 349: astore 3
      // 34a: aload 11
      // 34c: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 34f: checkcast io/ktor/utils/io/ByteWriteChannel
      // 352: astore 1
      // 353: nop
      // 354: aload 10
      // 356: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 359: aload 10
      // 35b: pop
      // 35c: goto 090
      // 35f: aload 1
      // 360: aload 0
      // 361: getfield io/ktor/client/request/forms/MultiPartFormDataContent.LAST_BOUNDARY_BYTES [B
      // 364: bipush 0
      // 365: bipush 0
      // 366: aload 11
      // 368: bipush 6
      // 36a: aconst_null
      // 36b: aload 11
      // 36d: aload 1
      // 36e: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 371: aload 11
      // 373: aconst_null
      // 374: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 377: aload 11
      // 379: aconst_null
      // 37a: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 37d: aload 11
      // 37f: bipush 7
      // 381: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 384: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.writeFully$default (Lio/ktor/utils/io/ByteWriteChannel;[BIILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
      // 387: dup
      // 388: aload 12
      // 38a: if_acmpne 3a1
      // 38d: aload 12
      // 38f: areturn
      // 390: aload 11
      // 392: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 395: checkcast io/ktor/utils/io/ByteWriteChannel
      // 398: astore 1
      // 399: nop
      // 39a: aload 10
      // 39c: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 39f: aload 10
      // 3a1: pop
      // 3a2: aload 1
      // 3a3: aload 11
      // 3a5: aload 11
      // 3a7: aload 1
      // 3a8: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 3ab: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 3ae: aload 11
      // 3b0: bipush 8
      // 3b2: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 3b5: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 3ba: dup
      // 3bb: aload 12
      // 3bd: if_acmpne 3d3
      // 3c0: aload 12
      // 3c2: areturn
      // 3c3: aload 11
      // 3c5: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 3c8: checkcast io/ktor/utils/io/ByteWriteChannel
      // 3cb: astore 1
      // 3cc: aload 10
      // 3ce: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 3d1: aload 10
      // 3d3: pop
      // 3d4: goto 480
      // 3d7: astore 3
      // 3d8: aload 1
      // 3d9: aload 3
      // 3da: invokestatic io/ktor/utils/io/ByteWriteChannelOperationsKt.close (Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/Throwable;)V
      // 3dd: aload 1
      // 3de: aload 11
      // 3e0: aload 11
      // 3e2: aload 1
      // 3e3: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 3e6: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 3e9: aload 11
      // 3eb: aconst_null
      // 3ec: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 3ef: aload 11
      // 3f1: aconst_null
      // 3f2: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 3f5: aload 11
      // 3f7: aconst_null
      // 3f8: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$3 Ljava/lang/Object;
      // 3fb: aload 11
      // 3fd: aconst_null
      // 3fe: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$4 Ljava/lang/Object;
      // 401: aload 11
      // 403: bipush 9
      // 405: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 408: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 40d: dup
      // 40e: aload 12
      // 410: if_acmpne 426
      // 413: aload 12
      // 415: areturn
      // 416: aload 11
      // 418: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 41b: checkcast io/ktor/utils/io/ByteWriteChannel
      // 41e: astore 1
      // 41f: aload 10
      // 421: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 424: aload 10
      // 426: pop
      // 427: goto 480
      // 42a: astore 3
      // 42b: aload 1
      // 42c: aload 11
      // 42e: aload 11
      // 430: aload 1
      // 431: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 434: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 437: aload 11
      // 439: aload 3
      // 43a: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 43d: aload 11
      // 43f: aconst_null
      // 440: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$2 Ljava/lang/Object;
      // 443: aload 11
      // 445: aconst_null
      // 446: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$3 Ljava/lang/Object;
      // 449: aload 11
      // 44b: aconst_null
      // 44c: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$4 Ljava/lang/Object;
      // 44f: aload 11
      // 451: bipush 10
      // 453: putfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.label I
      // 456: invokeinterface io/ktor/utils/io/ByteWriteChannel.flushAndClose (Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 2
      // 45b: dup
      // 45c: aload 12
      // 45e: if_acmpne 47d
      // 461: aload 12
      // 463: areturn
      // 464: aload 11
      // 466: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$1 Ljava/lang/Object;
      // 469: checkcast java/lang/Throwable
      // 46c: astore 3
      // 46d: aload 11
      // 46f: getfield io/ktor/client/request/forms/MultiPartFormDataContent$writeTo$1.L$0 Ljava/lang/Object;
      // 472: checkcast io/ktor/utils/io/ByteWriteChannel
      // 475: astore 1
      // 476: aload 10
      // 478: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 47b: aload 10
      // 47d: pop
      // 47e: aload 3
      // 47f: athrow
      // 480: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 483: areturn
      // 484: new java/lang/IllegalStateException
      // 487: dup
      // 488: ldc_w "call to 'resume' before 'invoke' with coroutine"
      // 48b: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 48e: athrow
   }

   @JvmStatic
   fun `rawParts$lambda$0$1`(`$bytes`: ByteArray): Source {
      val `builder$iv`: Buffer = new Buffer();
      BytePacketBuilderKt.writeFully$default(`builder$iv`, `$bytes`, 0, 0, 6, null);
      return `builder$iv`;
   }
}
