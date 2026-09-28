package kotlinx.serialization

import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlinx.serialization.modules.SerializersModule

// $VF: Class flags could not be determined
internal class SerializersKt {
   @JvmStatic
   fun serializer(type: Type): KSerializer<Object> {
      return SerializersKt__SerializersJvmKt.serializer(type);
   }

   @JvmStatic
   fun serializerOrNull(type: Type): KSerializer<Object>? {
      return SerializersKt__SerializersJvmKt.serializerOrNull(type);
   }

   @JvmStatic
   fun SerializersModule.serializer(type: Type): KSerializer<Object> {
      return SerializersKt__SerializersJvmKt.serializer(`$this$serializer`, type);
   }

   @JvmStatic
   fun SerializersModule.serializerOrNull(type: Type): KSerializer<Object>? {
      return SerializersKt__SerializersJvmKt.serializerOrNull(`$this$serializerOrNull`, type);
   }

   @JvmStatic
   fun serializer(type: KType): KSerializer<Object> {
      return SerializersKt__SerializersKt.serializer(type);
   }

   @ExperimentalSerializationApi
   @JvmStatic
   fun serializer(kClass: KClass<?>, typeArgumentsSerializers: MutableList<KSerializer<?>>, isNullable: Boolean): KSerializer<Object> {
      return SerializersKt__SerializersKt.serializer(kClass, typeArgumentsSerializers, isNullable);
   }

   @JvmStatic
   fun serializerOrNull(type: KType): KSerializer<Object>? {
      return SerializersKt__SerializersKt.serializerOrNull(type);
   }

   @JvmStatic
   fun SerializersModule.serializer(type: KType): KSerializer<Object> {
      return SerializersKt__SerializersKt.serializer(`$this$serializer`, type);
   }

   @ExperimentalSerializationApi
   @JvmStatic
   fun SerializersModule.serializer(kClass: KClass<?>, typeArgumentsSerializers: MutableList<KSerializer<?>>, isNullable: Boolean): KSerializer<Object> {
      return SerializersKt__SerializersKt.serializer(`$this$serializer`, kClass, typeArgumentsSerializers, isNullable);
   }

   @JvmStatic
   fun SerializersModule.serializerOrNull(type: KType): KSerializer<Object>? {
      return SerializersKt__SerializersKt.serializerOrNull(`$this$serializerOrNull`, type);
   }

   @JvmStatic
   fun SerializersModule.serializersForParameters(typeArguments: MutableList<KType>, failOnMissingTypeArgSerializer: Boolean): MutableList<KSerializer<Object>>? {
      return SerializersKt__SerializersKt.serializersForParameters(`$this$serializersForParameters`, typeArguments, failOnMissingTypeArgSerializer);
   }

   @InternalSerializationApi
   @JvmStatic
   fun <T> KClass<T>.serializer(): KSerializer<T> {
      return SerializersKt__SerializersKt.serializer(`$this$serializer`);
   }

   @InternalSerializationApi
   @JvmStatic
   fun <T> KClass<T>.serializerOrNull(): KSerializer<T> {
      return SerializersKt__SerializersKt.serializerOrNull(`$this$serializerOrNull`);
   }

   @JvmStatic
   fun KClass<Object>.parametrizedSerializerOrNull(serializers: MutableList<KSerializer<Object>>, elementClassifierIfArray: () -> KClassifier): KSerializer<? extends Object>? {
      return SerializersKt__SerializersKt.parametrizedSerializerOrNull(`$this$parametrizedSerializerOrNull`, serializers, elementClassifierIfArray);
   }

   @PublishedApi
   @JvmStatic
   fun noCompiledSerializer(forClass: java.lang.String): KSerializer<?> {
      return SerializersKt__SerializersKt.noCompiledSerializer(forClass);
   }

   @PublishedApi
   @JvmStatic
   fun noCompiledSerializer(module: SerializersModule, kClass: KClass<?>): KSerializer<?> {
      return SerializersKt__SerializersKt.noCompiledSerializer(module, kClass);
   }

   @PublishedApi
   @JvmStatic
   fun noCompiledSerializer(module: SerializersModule, kClass: KClass<?>, argSerializers: Array<KSerializer<?>>): KSerializer<?> {
      return SerializersKt__SerializersKt.noCompiledSerializer(module, kClass, argSerializers);
   }

   @PublishedApi
   @JvmStatic
   fun moduleThenPolymorphic(module: SerializersModule, kClass: KClass<?>): KSerializer<?> {
      return SerializersKt__SerializersKt.moduleThenPolymorphic(module, kClass);
   }

   @PublishedApi
   @JvmStatic
   fun moduleThenPolymorphic(module: SerializersModule, kClass: KClass<?>, argSerializers: Array<KSerializer<?>>): KSerializer<?> {
      return SerializersKt__SerializersKt.moduleThenPolymorphic(module, kClass, argSerializers);
   }
}
