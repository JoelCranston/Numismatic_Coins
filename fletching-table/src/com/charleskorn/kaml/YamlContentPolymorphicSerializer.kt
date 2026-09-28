package com.charleskorn.kaml

import com.charleskorn.kaml.YamlContentPolymorphicSerializer.annotationImpl.com_charleskorn_kaml_YamlContentPolymorphicSerializer_Marker.0
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public abstract class YamlContentPolymorphicSerializer<T> : KSerializer<T> {
   private final val baseClass: KClass<Any>
   public open val descriptor: SerialDescriptor

   open fun YamlContentPolymorphicSerializer(baseClass: KClass<T>) {
      this.baseClass = baseClass;
      this.descriptor = SerialDescriptorsKt.buildSerialDescriptor(
         "${(YamlContentPolymorphicSerializer::class).getSimpleName()}<${this.baseClass.getSimpleName()}>",
         PolymorphicKind.SEALED.INSTANCE,
         new SerialDescriptor[0],
         YamlContentPolymorphicSerializer::descriptor$lambda$0
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
      return decoder.decodeSerializableValue(this.selectDeserializer((decoder as YamlInput).getNode()));
   }

   public abstract fun selectDeserializer(node: YamlNode): DeserializationStrategy<Any> {
   }

   private fun throwSubtypeNotRegistered(subClass: KClass<*>, baseClass: KClass<*>): Nothing {
      var var10000: java.lang.String = subClass.getSimpleName();
      if (var10000 == null) {
         var10000 = java.lang.String.valueOf(subClass);
      }

      throw new SerializationException(
         StringsKt.trimIndent(
            "\n            Class '$var10000' is not registered for polymorphic serialization in the scope of '${baseClass.getSimpleName()}'.\n            Mark the base class as 'sealed' or register the serializer explicitly.\n            "
         )
      );
   }

   @JvmStatic
   fun ClassSerialDescriptorBuilder.`descriptor$lambda$0`(): Unit {
      `$this$buildSerialDescriptor`.setAnnotations(CollectionsKt.plus(`$this$buildSerialDescriptor`.getAnnotations(), new 0()));
      return Unit.INSTANCE;
   }

   @Retention(RetentionPolicy.RUNTIME)
   @SerialInfo
   annotation class Marker(

   ) {
      // $VF: Class flags could not be determined
      @JvmSynthetic
      internal class Impl : YamlContentPolymorphicSerializer.Marker
   }
}
