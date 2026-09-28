package kotlinx.serialization.internal

import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer

internal interface SerializerCache<T> {
   public abstract fun get(key: KClass<Any>): KSerializer<Any>? {
   }

   public open fun isStored(key: KClass<*>): Boolean {
      return false;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <T> isStored(`$this`: SerializerCache<T>, key: KClass<?>): Boolean {
         return SerializerCache.access$isStored$jd(`$this`, key);
      }
   }
}
