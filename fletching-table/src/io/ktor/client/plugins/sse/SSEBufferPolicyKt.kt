package io.ktor.client.plugins.sse

private const val NEWLINE: String = "\r\n"
internal final val EMPTY: ByteArray = new byte[0]

internal fun SSEBufferPolicy.toBodyBuffer(): BodyBuffer {
   val var10000: BodyBuffer;
   if (`$this$toBodyBuffer` is SSEBufferPolicy.Off) {
      var10000 = BodyBuffer.Empty.INSTANCE;
   } else if (`$this$toBodyBuffer` is SSEBufferPolicy.LastEvent) {
      var10000 = new BodyBuffer.Events(1);
   } else if (`$this$toBodyBuffer` is SSEBufferPolicy.LastEvents) {
      var10000 = new BodyBuffer.Events((`$this$toBodyBuffer` as SSEBufferPolicy.LastEvents).getCount());
   } else if (`$this$toBodyBuffer` is SSEBufferPolicy.LastLines) {
      var10000 = new BodyBuffer.Lines((`$this$toBodyBuffer` as SSEBufferPolicy.LastLines).getCount());
   } else {
      if (`$this$toBodyBuffer` !is SSEBufferPolicy.All) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = new BodyBuffer.Lines(Integer.MAX_VALUE);
   }

   return var10000;
}

private fun toByteArray(array: ArrayDeque<*>): ByteArray {
   return StringsKt.encodeToByteArray(CollectionsKt.joinToString$default(array, "\r\n", null, null, 0, null, null, 62, null));
}

@JvmSynthetic
fun `access$toByteArray`(array: ArrayDeque): ByteArray {
   return toByteArray(array);
}
