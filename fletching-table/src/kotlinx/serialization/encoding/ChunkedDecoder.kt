package kotlinx.serialization.encoding

import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
public interface ChunkedDecoder {
   @ExperimentalSerializationApi
   public abstract fun decodeStringChunked(consumeChunk: (String) -> Unit) {
   }
}
