package kotlinx.io

private class DiscardingSink : RawSink {
   public override fun write(source: Buffer, byteCount: Long) {
      source.skip(byteCount);
   }

   public override fun flush() {
   }

   public override fun close() {
   }
}
