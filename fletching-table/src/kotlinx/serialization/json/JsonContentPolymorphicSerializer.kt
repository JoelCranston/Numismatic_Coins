package kotlinx.serialization.json

import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public abstract class JsonContentPolymorphicSerializer<T> : KSerializer<T> {
   private final val baseClass: KClass<Any>
   public open val descriptor: SerialDescriptor

   open fun JsonContentPolymorphicSerializer(baseClass: KClass<T>) {
      this.baseClass = baseClass;
      this.descriptor = SerialDescriptorsKt.buildSerialDescriptor$default(
         "JsonContentPolymorphicSerializer<${this.baseClass.getSimpleName()}>", PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], null, 8, null
      );
   }

   public override fun serialize(encoder: Encoder, value: Any) {
      var var10000: SerializationStrategy = encoder.getSerializersModule().getPolymorphic(this.baseClass, (T)value);
      if (var10000 == null) {
         val var4: KSerializer = SerializersKt.serializerOrNull((KClass<T>)(value.getClass()::class));
         if (var4 == null) {
            this.throwSubtypeNotRegistered(value.getClass()::class, this.baseClass);
            throw new KotlinNothingValueException();
         }

         var10000 = var4;
      }

      (var10000 as KSerializer).serialize(encoder, value);
   }

   public override fun deserialize(decoder: Decoder): Any {
      val input: JsonDecoder = JsonElementSerializersKt.asJsonDecoder(decoder);
      val tree: JsonElement = input.decodeJsonElement();
      val var10000: DeserializationStrategy = this.selectDeserializer(tree);
      return input.getJson().decodeFromJsonElement(var10000, tree);
   }

   protected abstract fun selectDeserializer(element: JsonElement): DeserializationStrategy<Any> {
   }

   private fun throwSubtypeNotRegistered(subClass: KClass<*>, baseClass: KClass<*>): Nothing {
      var var10000: java.lang.String = subClass.getSimpleName();
      if (var10000 == null) {
         var10000 = java.lang.String.valueOf(subClass);
      }

      throw new SerializationException(
         "Class '$var10000' is not registered for polymorphic serialization in the scope of '${baseClass.getSimpleName()}'.\nMark the base class as 'sealed' or register the serializer explicitly."
      );
   }
}
