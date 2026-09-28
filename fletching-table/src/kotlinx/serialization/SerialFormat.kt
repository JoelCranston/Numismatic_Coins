package kotlinx.serialization

import kotlinx.serialization.modules.SerializersModule

public interface SerialFormat {
   public val serializersModule: SerializersModule
}
