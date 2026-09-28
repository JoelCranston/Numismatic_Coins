package kotlinx.serialization.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object JsonArraySerializer : KSerializer<JsonArray> {
   public open val descriptor: SerialDescriptor = JsonArraySerializer.JsonArrayDescriptor.INSTANCE as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: JsonArray) {
      JsonElementSerializersKt.access$verify(encoder);
      BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE).serialize(encoder, value);
   }

   public open fun deserialize(decoder: Decoder): JsonArray {
      JsonElementSerializersKt.access$verify(decoder);
      return new JsonArray(BuiltinSerializersKt.ListSerializer(JsonElementSerializer.INSTANCE).deserialize(decoder));
   }

   private object JsonArrayDescriptor : SerialDescriptor {
      @ExperimentalSerializationApi
      public open val serialName: String = "kotlinx.serialization.json.JsonArray"

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
