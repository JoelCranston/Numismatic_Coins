package kotlinx.serialization.modules

import kotlin.reflect.KClass
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy

public sealed class SerializersModule protected constructor() {
   @InternalSerializationApi
   internal abstract val hasInterfaceContextualSerializers: Boolean

   @ExperimentalSerializationApi
   public abstract fun <T : Any> getContextual(kClass: KClass<T>, typeArgumentsSerializers: List<KSerializer<*>> = CollectionsKt.emptyList()): KSerializer<T>? {
   }

   @ExperimentalSerializationApi
   public abstract fun <T : Any> getPolymorphic(baseClass: KClass<in T>, value: T): SerializationStrategy<T>? {
   }

   @ExperimentalSerializationApi
   public abstract fun <T : Any> getPolymorphic(baseClass: KClass<in T>, serializedClassName: String?): DeserializationStrategy<T>? {
   }

   @ExperimentalSerializationApi
   public abstract fun dumpTo(collector: SerializersModuleCollector) {
   }
}
