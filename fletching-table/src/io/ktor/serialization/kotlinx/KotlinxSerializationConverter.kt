package io.ktor.serialization.kotlinx

import io.ktor.http.ContentType
import io.ktor.http.ContentTypesKt
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.TextContent
import io.ktor.http.content.OutgoingContent.ByteArrayContent
import io.ktor.serialization.ContentConverter
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter.serialize.1
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter.serialize.fromExtension.2
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.core.ByteReadPacketKt
import io.ktor.utils.io.core.StringsKt
import java.nio.charset.Charset
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowKt
import kotlinx.io.Source
import kotlinx.io.SourcesKt
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.SerializationException
import kotlinx.serialization.StringFormat

@SourceDebugExtension(["SMAP\nKotlinxSerializationConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KotlinxSerializationConverter.kt\nio/ktor/serialization/kotlinx/KotlinxSerializationConverter\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,126:1\n49#2:127\n51#2:131\n49#2:132\n51#2:136\n46#3:128\n51#3:130\n46#3:133\n51#3:135\n105#4:129\n105#4:134\n*S KotlinDebug\n*F\n+ 1 KotlinxSerializationConverter.kt\nio/ktor/serialization/kotlinx/KotlinxSerializationConverter\n*L\n47#1:127\n47#1:131\n62#1:132\n62#1:136\n47#1:128\n47#1:130\n62#1:133\n62#1:135\n47#1:129\n62#1:134\n*E\n"])
public class KotlinxSerializationConverter(format: SerialFormat) : ContentConverter {
   private final val format: SerialFormat
   private final val extensions: List<KotlinxSerializationExtension>

   init {
      this.format = format;
      this.extensions = ExtensionsKt.extensions(this.format);
      if (this.format !is BinaryFormat && this.format !is StringFormat) {
         throw new IllegalArgumentException(("Only binary and string formats are supported, ${this.format} is not supported.").toString());
      }
   }

   public override suspend fun serialize(contentType: ContentType, charset: Charset, typeInfo: TypeInfo, value: Any?): OutgoingContent {
      var `$continuation`: Continuation;
      label33: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label33;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var14: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var10000 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter.serialize..inlined.map.1(
               FlowKt.asFlow(this.extensions), contentType, charset, typeInfo, value
            );
            val var10001: Function2 = new 2(null);
            `$continuation`.L$0 = contentType;
            `$continuation`.L$1 = charset;
            `$continuation`.L$2 = typeInfo;
            `$continuation`.L$3 = value;
            `$continuation`.label = 1;
            var10000 = FlowKt.firstOrNull((Flow)var10000, var10001, `$continuation`);
            if (var10000 === var14) {
               return var14;
            }
            break;
         case 1:
            value = `$continuation`.L$3;
            typeInfo = `$continuation`.L$2 as TypeInfo;
            charset = `$continuation`.L$1 as Charset;
            contentType = `$continuation`.L$0 as ContentType;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val fromExtension: OutgoingContent = var10000 as OutgoingContent;
      if (var10000 as OutgoingContent != null) {
         return fromExtension;
      } else {
         var var16: KSerializer;
         try {
            var16 = SerializerLookupKt.serializerForTypeInfo(this.format.getSerializersModule(), typeInfo);
         } catch (var15: SerializationException) {
            var16 = SerializerLookupKt.guessSerializer(value, this.format.getSerializersModule());
         }

         return this.serializeContent(var16, this.format, value, contentType, charset);
      }
   }

   public override suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: ByteReadChannel): Any? {
      var `$continuation`: Continuation;
      label61: {
         if (`$completion` is io.ktor.serialization.kotlinx.KotlinxSerializationConverter.deserialize.1) {
            `$continuation` = `$completion` as io.ktor.serialization.kotlinx.KotlinxSerializationConverter.deserialize.1;
            if (((`$completion` as io.ktor.serialization.kotlinx.KotlinxSerializationConverter.deserialize.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label61;
            }
         }

         `$continuation` = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter.deserialize.1(this, `$completion`);
      }

      var serializer: KSerializer;
      var var10000: Any;
      label65: {
         val `$result`: Any = `$continuation`.result;
         val var13: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               var10000 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter.deserialize..inlined.map.1(
                  FlowKt.asFlow(this.extensions), charset, typeInfo, content
               );
               val var10001: Function2 = new io.ktor.serialization.kotlinx.KotlinxSerializationConverter.deserialize.fromExtension.2(content, null);
               `$continuation`.L$0 = charset;
               `$continuation`.L$1 = typeInfo;
               `$continuation`.L$2 = content;
               `$continuation`.label = 1;
               var10000 = FlowKt.firstOrNull((Flow)var10000, var10001, `$continuation`);
               if (var10000 === var13) {
                  return var13;
               }
               break;
            case 1:
               content = `$continuation`.L$2 as ByteReadChannel;
               typeInfo = `$continuation`.L$1 as TypeInfo;
               charset = `$continuation`.L$0 as Charset;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            case 2:
               serializer = `$continuation`.L$4 as KSerializer;
               val fromExtension: Any = `$continuation`.L$3;
               content = `$continuation`.L$2 as ByteReadChannel;
               typeInfo = `$continuation`.L$1 as TypeInfo;
               charset = `$continuation`.L$0 as Charset;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break label65;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         if (!this.extensions.isEmpty() && (var10000 != null || content.isClosedForRead())) {
            return var10000;
         }

         serializer = SerializerLookupKt.serializerForTypeInfo(this.format.getSerializersModule(), typeInfo);
         `$continuation`.L$0 = charset;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(typeInfo);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(content);
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var10000);
         `$continuation`.L$4 = serializer;
         `$continuation`.label = 2;
         var10000 = ByteReadChannelOperationsKt.readRemaining(content, `$continuation`);
         if (var10000 === var13) {
            return var13;
         }
      }

      val var19: Source = var10000 as Source;

      try {
         if (this.format is StringFormat) {
            var10000 = (this.format as StringFormat).decodeFromString(serializer, StringsKt.readText$default(var19, charset, 0, 2, null));
         } else {
            if (this.format !is BinaryFormat) {
               ByteReadPacketKt.discard$default(var19, 0L, 1, null);
               throw new IllegalStateException(("Unsupported format ${this.format}").toString());
            }

            var10000 = (this.format as BinaryFormat).decodeFromByteArray(serializer, SourcesKt.readByteArray(var19));
         }

         return var10000;
      } catch (var14: java.lang.Throwable) {
         throw new JsonConvertException("Illegal input: ${var14.getMessage()}", var14);
      }
   }

   private fun serializeContent(serializer: KSerializer<*>, format: SerialFormat, value: Any?, contentType: ContentType, charset: Charset): ByteArrayContent {
      val var9: OutgoingContent.ByteArrayContent;
      if (format is StringFormat) {
         val var10000: StringFormat = format as StringFormat;
         var9 = new TextContent(var10000.encodeToString(serializer, value), ContentTypesKt.withCharsetIfNeeded(contentType, charset), null, 4, null);
      } else {
         if (format !is BinaryFormat) {
            throw new IllegalStateException(("Unsupported format $format").toString());
         }

         val var10: BinaryFormat = format as BinaryFormat;
         var9 = new io.ktor.http.content.ByteArrayContent(var10.encodeToByteArray(serializer, value), contentType, null, 4, null);
      }

      return var9;
   }
}
