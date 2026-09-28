package kotlinx.serialization.json.internal

@JsonFriendModuleApi
public interface InternalJsonReader {
   public abstract fun read(buffer: CharArray, bufferOffset: Int, count: Int): Int {
   }
}
