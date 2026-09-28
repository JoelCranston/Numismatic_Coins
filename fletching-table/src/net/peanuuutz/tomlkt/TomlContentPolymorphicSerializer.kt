package net.peanuuutz.tomlkt

import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

public abstract class TomlContentPolymorphicSerializer<T> : KSerializer<T> {
   private final val baseClass: KClass<Any>
   public final val descriptor: SerialDescriptor

   open fun TomlContentPolymorphicSerializer(baseClass: KClass<T>, serialName: java.lang.String) {
      this.baseClass = baseClass;
      this.descriptor = SerialDescriptorsKt.buildSerialDescriptor$default(serialName, PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], null, 8, null);
   }

   protected abstract fun selectDeserializer(element: TomlElement): DeserializationStrategy<Any> {
   }

   public override fun serialize(encoder: Encoder, value: Any) {
      var var10000: SerializationStrategy = encoder.getSerializersModule().getPolymorphic(this.baseClass, (T)value);
      if (var10000 == null) {
         val var4: KSerializer = SerializersKt.serializerOrNull((KClass<T>)(value.getClass()::class));
         if (var4 == null) {
            TomlSerializationExceptionsKt.throwSubclassNotRegistered(value.getClass()::class, this.baseClass);
            throw new KotlinNothingValueException();
         }

         var10000 = var4;
      }

      (var10000 as KSerializer).serialize(encoder, value);
   }

   public override fun deserialize(decoder: Decoder): Any {
      TomlDecoderKt.asTomlDecoder(decoder);
      val element: TomlElement = (decoder as TomlDecoder).decodeTomlElement();
      return (decoder as TomlDecoder).getToml().decodeFromTomlElement(this.selectDeserializer(element), element);
   }
}
