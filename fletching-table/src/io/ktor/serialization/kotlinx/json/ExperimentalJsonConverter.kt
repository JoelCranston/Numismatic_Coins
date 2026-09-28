package io.ktor.serialization.kotlinx.json

import io.ktor.http.ContentType
import io.ktor.http.content.ChannelWriterContent
import io.ktor.http.content.OutgoingContent
import io.ktor.serialization.ContentConverter
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.SerializerLookupKt
import io.ktor.serialization.kotlinx.json.ExperimentalJsonConverter.deserialize.1
import io.ktor.serialization.kotlinx.json.ExperimentalJsonConverter.serialize.2
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.core.ByteReadPacketKt
import java.nio.charset.Charset
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.io.IoStreamsKt

public class ExperimentalJsonConverter(format: Json) : ContentConverter {
   private final val format: Json

   init {
      this.format = format;
   }

   public override suspend fun serialize(contentType: ContentType, charset: Charset, typeInfo: TypeInfo, value: Any?): OutgoingContent {
      var buffer: KSerializer;
      try {
         buffer = SerializerLookupKt.serializerForTypeInfo(this.format.getSerializersModule(), typeInfo);
      } catch (var11: SerializationException) {
         buffer = SerializerLookupKt.guessSerializer(value, this.format.getSerializersModule());
      }

      val cause: Buffer = new Buffer();
      val var10000: Json = this.format;
      IoStreamsKt.encodeToSink(var10000, buffer, value, cause);
      return new ChannelWriterContent(new 2(cause, null), contentType, null, Boxing.boxLong(ByteReadPacketKt.getRemaining(cause)), 4, null);
   }

   public override suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: ByteReadChannel): Any? {
      var `$continuation`: Continuation;
      label28: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label28;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var11: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var serializer: KSerializer;
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            serializer = SerializerLookupKt.serializerForTypeInfo(this.format.getSerializersModule(), typeInfo);
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(charset);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(typeInfo);
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(content);
            `$continuation`.L$3 = serializer;
            `$continuation`.label = 1;
            var10000 = ByteReadChannelOperationsKt.readRemaining(content, `$continuation`);
            if (var10000 === var11) {
               return var11;
            }
            break;
         case 1:
            serializer = `$continuation`.L$3 as KSerializer;
            content = `$continuation`.L$2 as ByteReadChannel;
            typeInfo = `$continuation`.L$1 as TypeInfo;
            charset = `$continuation`.L$0 as Charset;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val contentPacket: Source = var10000 as Source;

      try {
         return IoStreamsKt.decodeFromSource(this.format, serializer, contentPacket);
      } catch (var12: java.lang.Throwable) {
         throw new JsonConvertException("Illegal input: ${var12.getMessage()}", var12);
      }
   }
}
