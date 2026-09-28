package kotlinx.serialization.modules

import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy

@ExperimentalSerializationApi
public interface SerializersModuleCollector {
   public open fun <T : Any> contextual(kClass: KClass<T>, serializer: KSerializer<T>) {
      this.contextual(kClass, SerializersModuleCollector::contextual$lambda$0);
   }

   public abstract fun <T : Any> contextual(kClass: KClass<T>, provider: (List<KSerializer<*>>) -> KSerializer<*>) {
   }

   public abstract fun <Base : Any, Sub : Base> polymorphic(baseClass: KClass<Base>, actualClass: KClass<Sub>, actualSerializer: KSerializer<Sub>) {
   }

   public abstract fun <Base : Any> polymorphicDefaultSerializer(baseClass: KClass<Base>, defaultSerializerProvider: (Base) -> SerializationStrategy<Base>?) {
   }

   public abstract fun <Base : Any> polymorphicDefaultDeserializer(
      baseClass: KClass<Base>,
      defaultDeserializerProvider: (String?) -> DeserializationStrategy<Base>?
   ) {
   }

   @Deprecated(message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = []), level = DeprecationLevel.WARNING)
   public open fun <Base : Any> polymorphicDefault(baseClass: KClass<Base>, defaultDeserializerProvider: (String?) -> DeserializationStrategy<Base>?) {
      this.polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider);
   }

   @JvmDefault
   @JvmStatic
   fun `contextual$lambda$0`(`$serializer`: KSerializer, it: java.util.List): KSerializer {
      return `$serializer`;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @java.lang.Deprecated
      @JvmStatic
      fun <T> contextual(`$this`: SerializersModuleCollector, kClass: KClass<T>, serializer: KSerializer<T>) {
         SerializersModuleCollector.access$contextual$jd(`$this`, kClass, serializer);
      }

      @Deprecated(message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = []), level = DeprecationLevel.WARNING)
      @java.lang.Deprecated
      @JvmStatic
      fun <Base> polymorphicDefault(
         `$this`: SerializersModuleCollector,
         baseClass: KClass<Base>,
         defaultDeserializerProvider: (java.lang.String?) -> DeserializationStrategy<? extends Base>
      ) {
         SerializersModuleCollector.access$polymorphicDefault$jd(`$this`, baseClass, defaultDeserializerProvider);
      }
   }
}
