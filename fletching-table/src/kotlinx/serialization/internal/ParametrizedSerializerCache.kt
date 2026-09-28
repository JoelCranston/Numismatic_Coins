package kotlinx.serialization.internal

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlinx.serialization.KSerializer

internal interface ParametrizedSerializerCache<T> {
   public abstract fun get(key: KClass<Any>, types: List<KType> = ...): Result<KSerializer<Any>?> {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
