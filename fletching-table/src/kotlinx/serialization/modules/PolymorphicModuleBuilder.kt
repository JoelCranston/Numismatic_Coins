package kotlinx.serialization.modules

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy

@SourceDebugExtension(["SMAP\nPolymorphicModuleBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PolymorphicModuleBuilder.kt\nkotlinx/serialization/modules/PolymorphicModuleBuilder\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n*L\n1#1,119:1\n1869#2:120\n1870#2:122\n78#3:121\n*S KotlinDebug\n*F\n+ 1 PolymorphicModuleBuilder.kt\nkotlinx/serialization/modules/PolymorphicModuleBuilder\n*L\n88#1:120\n88#1:122\n92#1:121\n*E\n"])
public class PolymorphicModuleBuilder<Base> @PublishedApi  internal constructor(baseClass: KClass<Any>, baseSerializer: KSerializer<Any>? = null) {
   private final val baseClass: KClass<Any>
   private final val baseSerializer: KSerializer<Any>?
   private final val subclasses: MutableList<Pair<KClass<out Any>, KSerializer<out Any>>>
   private final var defaultSerializerProvider: ((Any) -> SerializationStrategy<Any>?)?
   private final var defaultDeserializerProvider: ((String?) -> DeserializationStrategy<Any>?)?

   init {
      this.baseClass = baseClass;
      this.baseSerializer = baseSerializer;
      this.subclasses = new ArrayList<>();
   }

   public fun <T : Any> subclass(subclass: KClass<T>, serializer: KSerializer<T>) {
      this.subclasses.add(TuplesKt.to(subclass, serializer));
   }

   public fun defaultDeserializer(defaultDeserializerProvider: (String?) -> DeserializationStrategy<Any>?) {
      if (this.defaultDeserializerProvider != null) {
         throw new IllegalArgumentException(
            ("Default deserializer provider is already registered for class ${this.baseClass}: ${this.defaultDeserializerProvider}").toString()
         );
      } else {
         this.defaultDeserializerProvider = defaultDeserializerProvider;
      }
   }

   @Deprecated(message = "Deprecated in favor of function with more precise name: defaultDeserializer", replaceWith = @ReplaceWith(expression = "defaultDeserializer(defaultSerializerProvider)", imports = []), level = DeprecationLevel.WARNING)
   public fun default(defaultSerializerProvider: (String?) -> DeserializationStrategy<Any>?) {
      this.defaultDeserializer(defaultSerializerProvider);
   }

   @PublishedApi
   internal fun buildTo(builder: SerializersModuleBuilder) {
      if (this.baseSerializer != null) {
         SerializersModuleBuilder.registerPolymorphicSerializer$default(builder, this.baseClass, this.baseClass, this.baseSerializer, false, 8, null);
      }

      val defaultSerializer: java.lang.Iterable;
      for (Object element$iv : defaultSerializer) {
         val kclass: KClass = (`element$iv` as Pair).component1() as KClass;
         val serializer: KSerializer = (`element$iv` as Pair).component2() as KSerializer;
         val var10001: KClass = this.baseClass;
         SerializersModuleBuilder.registerPolymorphicSerializer$default(builder, var10001, kclass, serializer, false, 8, null);
      }

      if (this.defaultSerializerProvider != null) {
         builder.registerDefaultPolymorphicSerializer(this.baseClass, this.defaultSerializerProvider, false);
      }

      if (this.defaultDeserializerProvider != null) {
         builder.registerDefaultPolymorphicDeserializer(this.baseClass, this.defaultDeserializerProvider, false);
      }
   }
}
