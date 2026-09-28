package kotlinx.io.bytestring.unsafe

import kotlin.contracts.InvocationKind
import kotlinx.io.bytestring.ByteString

@UnsafeByteStringApi
public object UnsafeByteStringOperations {
   public fun wrapUnsafe(array: ByteArray): ByteString {
      return ByteString.Companion.wrap$kotlinx_io_bytestring(array);
   }

   public inline fun withByteArrayUnsafe(byteString: ByteString, block: (ByteArray) -> Unit) {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      block.invoke(byteString.getBackingArrayReference());
   }
}
