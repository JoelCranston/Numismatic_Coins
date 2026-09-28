package io.ktor.serialization.kotlinx.json

import io.ktor.http.ContentType
import io.ktor.http.ContentTypesKt
import io.ktor.http.content.ChannelWriterContent
import io.ktor.http.content.OutgoingContent
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.KotlinxSerializationExtension
import io.ktor.serialization.kotlinx.SerializerLookupKt
import io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.deserialize.1
import io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.serialize.2
import io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.serialize.3
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import java.nio.charset.Charset
import java.util.LinkedHashMap
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

@SourceDebugExtension(["SMAP\nKotlinxSerializationJsonExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KotlinxSerializationJsonExtensions.kt\nio/ktor/serialization/kotlinx/json/KotlinxSerializationJsonExtensions\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 Collect.kt\nkotlinx/coroutines/flow/FlowKt__CollectKt\n*L\n1#1,112:1\n382#2,7:113\n56#3,4:120\n*S KotlinDebug\n*F\n+ 1 KotlinxSerializationJsonExtensions.kt\nio/ktor/serialization/kotlinx/json/KotlinxSerializationJsonExtensions\n*L\n78#1:113,7\n81#1:120,4\n*E\n"])
internal class KotlinxSerializationJsonExtensions(format: Json) : KotlinxSerializationExtension {
   private final val format: Json
   private final val jsonArraySymbolsMap: MutableMap<Charset, JsonArraySymbols>

   init {
      this.format = format;
      this.jsonArraySymbolsMap = new LinkedHashMap<>();
   }

   public override suspend fun serialize(contentType: ContentType, charset: Charset, typeInfo: TypeInfo, value: Any?): OutgoingContent? {
      return if (charset == Charsets.UTF_8 && typeInfo.getType() == Flow::class)
         new ChannelWriterContent(
            new 2(
               this,
               value,
               SerializerLookupKt.serializerForTypeInfo(this.format.getSerializersModule(), KotlinxSerializationJsonExtensionsKt.argumentTypeInfo(typeInfo)),
               charset,
               null
            ),
            ContentTypesKt.withCharsetIfNeeded(contentType, charset),
            null,
            null,
            12,
            null
         )
         else
         null;
   }

   public override suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: ByteReadChannel): Any? {
      var `$continuation`: Continuation;
      label43: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label43;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (!(charset == Charsets.UTF_8) || !(typeInfo.getType() == Sequence::class)) {
               return null;
            }

            try {
               var10000 = this.format;
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(charset);
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(typeInfo);
               `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(content);
               `$continuation`.label = 1;
               var10000 = JsonExtensionsJvmKt.deserializeSequence((Json)var10000, content, typeInfo, `$continuation`);
            } catch (var11: java.lang.Throwable) {
               throw new JsonConvertException("Illegal input: ${var11.getMessage()}", var11);
            }

            if (var10000 === var8) {
               return var8;
            }
            break;
         case 1:
            content = `$continuation`.L$2 as ByteReadChannel;
            typeInfo = `$continuation`.L$1 as TypeInfo;
            charset = `$continuation`.L$0 as Charset;

            try {
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            } catch (var10: java.lang.Throwable) {
               throw new JsonConvertException("Illegal input: ${var10.getMessage()}", var10);
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         return var10000;
      } catch (var9: java.lang.Throwable) {
         throw new JsonConvertException("Illegal input: ${var9.getMessage()}", var9);
      }
   }

   private suspend fun <T> Flow<Any>.serialize(serializer: KSerializer<Any>, charset: Charset, channel: ByteWriteChannel) {
      var `$continuation`: Continuation;
      label44: {
         if (`$completion` is 3) {
            `$continuation` = `$completion` as 3;
            if (((`$completion` as 3).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label44;
            }
         }

         `$continuation` = new 3(this, `$completion`);
      }

      var var14: Any;
      var var19: JsonArraySymbols;
      label36: {
         val `$result`: Any = `$continuation`.result;
         var14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               val var20: java.util.Map = this.jsonArraySymbolsMap;
               val `value$iv`: Any = this.jsonArraySymbolsMap.get(charset);
               val var10000: Any;
               if (`value$iv` == null) {
                  val var22: Any = new JsonArraySymbols(charset);
                  var20.put(charset, var22);
                  var10000 = var22;
               } else {
                  var10000 = `value$iv`;
               }

               var19 = var10000 as JsonArraySymbols;
               val var10001: ByteArray = (var10000 as JsonArraySymbols).getBeginArray();
               `$continuation`.L$0 = `$this$serialize`;
               `$continuation`.L$1 = serializer;
               `$continuation`.L$2 = charset;
               `$continuation`.L$3 = channel;
               `$continuation`.L$4 = var19;
               `$continuation`.label = 1;
               if (ByteWriteChannelOperationsKt.writeFully$default(channel, var10001, 0, 0, `$continuation`, 6, null) === var14) {
                  return var14;
               }
               break;
            case 1:
               var19 = `$continuation`.L$4 as JsonArraySymbols;
               channel = `$continuation`.L$3 as ByteWriteChannel;
               charset = `$continuation`.L$2 as Charset;
               serializer = `$continuation`.L$1 as KSerializer;
               `$this$serialize` = `$continuation`.L$0 as Flow;
               ResultKt.throwOnFailure(`$result`);
               break;
            case 2:
               val `$i$f$collectIndexed`: Int = `$continuation`.I$0;
               val `$this$collectIndexed$iv`: Flow = `$continuation`.L$5 as Flow;
               var19 = `$continuation`.L$4 as JsonArraySymbols;
               channel = `$continuation`.L$3 as ByteWriteChannel;
               charset = `$continuation`.L$2 as Charset;
               serializer = `$continuation`.L$1 as KSerializer;
               `$this$serialize` = `$continuation`.L$0 as Flow;
               ResultKt.throwOnFailure(`$result`);
               break label36;
            case 3:
               var19 = `$continuation`.L$4 as JsonArraySymbols;
               channel = `$continuation`.L$3 as ByteWriteChannel;
               charset = `$continuation`.L$2 as Charset;
               serializer = `$continuation`.L$1 as KSerializer;
               `$this$serialize` = `$continuation`.L$0 as Flow;
               ResultKt.throwOnFailure(`$result`);
               return Unit.INSTANCE;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         val var23: FlowCollector = new io.ktor.serialization.kotlinx.json.KotlinxSerializationJsonExtensions.serialize..inlined.collectIndexed.1(
            channel, var19, this, serializer, charset
         );
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$serialize`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(serializer);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(charset);
         `$continuation`.L$3 = channel;
         `$continuation`.L$4 = var19;
         `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(`$this$serialize`);
         `$continuation`.I$0 = 0;
         `$continuation`.label = 2;
         if (`$this$serialize`.collect(var23, `$continuation`) === var14) {
            return var14;
         }
      }

      val var24: ByteArray = var19.getEndArray();
      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$serialize`);
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(serializer);
      `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(charset);
      `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(channel);
      `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var19);
      `$continuation`.L$5 = null;
      `$continuation`.label = 3;
      return if (ByteWriteChannelOperationsKt.writeFully$default(channel, var24, 0, 0, `$continuation`, 6, null) === var14) var14 else Unit.INSTANCE;
   }
}
