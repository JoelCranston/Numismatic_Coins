package kotlinx.serialization.internal

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlinx.serialization.KSerializer

private final val useClassValue: Boolean

internal fun <T> createCache(factory: (KClass<*>) -> KSerializer<T>?): SerializerCache<T> {
   return (SerializerCache<T>)(if (useClassValue) new ClassValueCache(factory) else new ConcurrentHashMapCache(factory));
}

internal fun <T> createParametrizedCache(factory: (KClass<Any>, List<KType>) -> KSerializer<T>?): ParametrizedSerializerCache<T> {
   return (ParametrizedSerializerCache<T>)(if (useClassValue) new ClassValueParametrizedCache(factory) else new ConcurrentHashMapParametrizedCache(factory));
}
