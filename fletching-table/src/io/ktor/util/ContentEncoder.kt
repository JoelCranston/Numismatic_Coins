package io.ktor.util

public interface ContentEncoder : Encoder {
   public val name: String

   public open fun predictCompressedLength(contentLength: Long): Long? {
      return null;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun predictCompressedLength(`$this`: ContentEncoder, contentLength: Long): java.lang.Long {
         return ContentEncoder.access$predictCompressedLength$jd(`$this`, contentLength);
      }
   }
}
