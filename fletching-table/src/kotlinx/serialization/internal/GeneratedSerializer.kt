package kotlinx.serialization.internal

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer

@InternalSerializationApi
public interface GeneratedSerializer<T> : KSerializer<T> {
   public abstract fun childSerializers(): Array<KSerializer<*>> {
   }

   public open fun typeParametersSerializers(): Array<KSerializer<*>> {
      return PluginHelperInterfacesKt.EMPTY_SERIALIZER_ARRAY;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <T> typeParametersSerializers(`$this`: GeneratedSerializer<T>): Array<KSerializer<?>> {
         return GeneratedSerializer.access$typeParametersSerializers$jd(`$this`);
      }
   }
}
