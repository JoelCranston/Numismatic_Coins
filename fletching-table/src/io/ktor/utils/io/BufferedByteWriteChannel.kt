package io.ktor.utils.io

public interface BufferedByteWriteChannel : ByteWriteChannel {
   @InternalAPI
   public abstract fun flushWriteBuffer() {
   }

   public abstract fun close() {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun getAutoFlush(`$this`: BufferedByteWriteChannel): Boolean {
         return BufferedByteWriteChannel.access$getAutoFlush$jd(`$this`);
      }
   }
}
