package okio.internal

private class EocdRecord(entryCount: Long, centralDirectoryOffset: Long, commentByteCount: Int) {
   public final val entryCount: Long
   public final val centralDirectoryOffset: Long
   public final val commentByteCount: Int

   init {
      this.entryCount = entryCount;
      this.centralDirectoryOffset = centralDirectoryOffset;
      this.commentByteCount = commentByteCount;
   }
}
