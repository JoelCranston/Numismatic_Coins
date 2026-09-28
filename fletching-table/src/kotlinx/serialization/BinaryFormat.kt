package kotlinx.serialization

public interface BinaryFormat : SerialFormat {
   public abstract fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
   }

   public abstract fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray): T {
   }
}
