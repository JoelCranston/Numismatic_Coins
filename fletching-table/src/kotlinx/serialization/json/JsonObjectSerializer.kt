package kotlinx.serialization.json

import kotlin.jvm.internal.StringCompanionObject
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object JsonObjectSerializer : KSerializer<JsonObject> {
   public open val descriptor: SerialDescriptor = JsonObjectSerializer.JsonObjectDescriptor.INSTANCE as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: JsonObject) {
      JsonElementSerializersKt.access$verify(encoder);
      BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE), JsonElementSerializer.INSTANCE)
         .serialize(encoder, value);
   }

   public open fun deserialize(decoder: Decoder): JsonObject {
      JsonElementSerializersKt.access$verify(decoder);
      return new JsonObject(
         BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE), JsonElementSerializer.INSTANCE)
            .deserialize(decoder)
      );
   }

   private object JsonObjectDescriptor : SerialDescriptor {
      @ExperimentalSerializationApi
      public open val serialName: String = "kotlinx.serialization.json.JsonObject"

      public open val annotations: List<Annotation>
         public open get() {
            return this.$$delegate_0.getAnnotations();
         }


      public open val elementsCount: Int

      public open val isInline: Boolean
         public open get() {
            return this.$$delegate_0.isInline();
         }


      public open val isNullable: Boolean
         public open get() {
            return this.$$delegate_0.isNullable();
         }


      public open val kind: SerialKind

      public override fun getElementName(index: Int): String {
         return this.$$delegate_0.getElementName(index);
      }

      public override fun getElementIndex(name: String): Int {
         return this.$$delegate_0.getElementIndex(name);
      }

      public override fun getElementAnnotations(index: Int): List<Annotation> {
         return this.$$delegate_0.getElementAnnotations(index);
      }

      public override fun getElementDescriptor(index: Int): SerialDescriptor {
         return this.$$delegate_0.getElementDescriptor(index);
      }

      public override fun isElementOptional(index: Int): Boolean {
         return this.$$delegate_0.isElementOptional(index);
      }
   }
}
