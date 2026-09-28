package kotlinx.serialization.modules

import kotlin.reflect.KClass

private class SerializerAlreadyRegisteredException internal constructor(msg: String) : IllegalArgumentException(msg) {
   internal constructor(baseClass: KClass<*>, concreteClass: KClass<*>) : this("Serializer for $concreteClass already registered in the scope of $baseClass")}
