package io.ktor.serialization.kotlinx

import io.ktor.serialization.WebsocketContentConverter
import io.ktor.serialization.WebsocketConverterNotFoundException
import io.ktor.serialization.WebsocketDeserializeException
import io.ktor.util.reflect.TypeInfo
import io.ktor.websocket.Frame
import io.ktor.websocket.FrameCommonKt
import java.nio.charset.Charset
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialFormat
import kotlinx.serialization.SerializationException
import kotlinx.serialization.StringFormat

public class KotlinxWebsocketSerializationConverter(format: SerialFormat) : WebsocketContentConverter {
   private final val format: SerialFormat

   init {
      this.format = format;
      if (this.format !is BinaryFormat && this.format !is StringFormat) {
         throw new IllegalArgumentException(("Only binary and string formats are supported, ${this.format} is not supported.").toString());
      }
   }

   public override suspend fun serialize(charset: Charset, typeInfo: TypeInfo, value: Any?): Frame {
      var var6: KSerializer;
      try {
         var6 = SerializerLookupKt.serializerForTypeInfo(this.format.getSerializersModule(), typeInfo);
      } catch (var8: SerializationException) {
         var6 = SerializerLookupKt.guessSerializer(value, this.format.getSerializersModule());
      }

      return this.serializeContent(var6, this.format, value);
   }

   public override suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: Frame): Any? {
      if (!this.isApplicable(content)) {
         throw new WebsocketConverterNotFoundException("Unsupported frame ${content.getFrameType().name()}", null, 2, null);
      } else {
         val serializer: KSerializer = SerializerLookupKt.serializerForTypeInfo(this.format.getSerializersModule(), typeInfo);
         val var10000: Any;
         if (this.format is StringFormat) {
            if (content !is Frame.Text) {
               throw new WebsocketDeserializeException("Unsupported format ${this.format} for ${content.getFrameType().name()}", null, content, 2, null);
            }

            var10000 = (this.format as StringFormat).decodeFromString(serializer, FrameCommonKt.readText(content as Frame.Text));
         } else {
            if (this.format !is BinaryFormat) {
               throw new IllegalStateException(("Unsupported format ${this.format}").toString());
            }

            if (content !is Frame.Binary) {
               throw new WebsocketDeserializeException("Unsupported format ${this.format} for ${content.getFrameType().name()}", null, content, 2, null);
            }

            var10000 = (this.format as BinaryFormat).decodeFromByteArray(serializer, FrameCommonKt.readBytes(content));
         }

         return var10000;
      }
   }

   public override fun isApplicable(frame: Frame): Boolean {
      return frame is Frame.Text || frame is Frame.Binary;
   }

   private fun serializeContent(serializer: KSerializer<*>, format: SerialFormat, value: Any?): Frame {
      val var7: Frame;
      if (format is StringFormat) {
         val var10000: StringFormat = format as StringFormat;
         var7 = new Frame.Text(var10000.encodeToString(serializer, value));
      } else {
         if (format !is BinaryFormat) {
            throw new IllegalStateException(("Unsupported format $format").toString());
         }

         val var8: BinaryFormat = format as BinaryFormat;
         var7 = new Frame.Binary(true, var8.encodeToByteArray(serializer, value));
      }

      return var7;
   }
}
