package kotlinx.coroutines.stream

import java.util.stream.Stream
import kotlinx.coroutines.flow.Flow

public fun <T> Stream<T>.consumeAsFlow(): Flow<T> {
   return new StreamFlow(`$this$consumeAsFlow`);
}
