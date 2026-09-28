package kotlinx.serialization

public interface StringFormat : SerialFormat {
   public abstract fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String {
   }

   public abstract fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T {
   }
}
