package io.ktor.utils.io

@Deprecated(
   message = "Counter is no longer available on the regular ByteReadChannel. Use CounterByteReadChannel instead.",
   replaceWith = @ReplaceWith(
      expression = "this.counted().totalBytesRead",
      imports = {}
   ),
   level = DeprecationLevel.ERROR
)
public final val totalBytesRead: Long
   public final get() {
      throw new IllegalStateException("Counter is no longer available on the regular ByteReadChannel. Use CounterByteReadChannel instead.".toString());
   }


public fun ByteReadChannel.counted(): CountedByteReadChannel {
   return new CountedByteReadChannel(`$this$counted`);
}
