package it.krzeminski.snakeyaml.engine.kmp.common

import net.thauvin.erik.urlencoder.UrlEncoderUtil
import okio.Buffer

public object UriEncoder {
   private const val SAFE_CHARS: String = "-_.!~*'()@:$&,;=[]/"

   private fun urlDecode(content: String): String {
      return UrlEncoderUtil.decode(content, false);
   }

   private fun urlEncode(content: String): String {
      return UrlEncoderUtil.encode(content, "-_.!~*'()@:$&,;=[]/", false);
   }

   @JvmStatic
   public fun encode(uri: String): String {
      return INSTANCE.urlEncode(uri);
   }

   @Throws(java/nio/charset/CharacterCodingException::class)
   @JvmStatic
   public fun decode(buff: Buffer): String {
      return decode(StringsKt.decodeToString(buff.readByteArray()));
   }

   @JvmStatic
   public fun decode(buff: String): String {
      return INSTANCE.urlDecode(buff);
   }
}
