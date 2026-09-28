package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer

/** @deprecated */
@Deprecated(message = "Inserted into generated code and should not be used directly", level = DeprecationLevel.HIDDEN)
public interface SerializerFactory {
   public abstract fun serializer(vararg typeParamsSerializers: KSerializer<*>): KSerializer<*> {
   }
}
