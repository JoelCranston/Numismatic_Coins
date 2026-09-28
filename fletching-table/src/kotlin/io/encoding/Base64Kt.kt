@file:SourceDebugExtension(["SMAP\nBase64.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Base64.kt\nkotlin/io/encoding/Base64Kt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,843:1\n13880#2,3:844\n13880#2,3:847\n*S KotlinDebug\n*F\n+ 1 Base64.kt\nkotlin/io/encoding/Base64Kt\n*L\n785#1:844,3\n801#1:847,3\n*E\n"])

package kotlin.io.encoding

import kotlin.jvm.internal.SourceDebugExtension

private final val base64EncodeMap: ByteArray =
   new byte[]{
      65,
      66,
      67,
      68,
      69,
      70,
      71,
      72,
      73,
      74,
      75,
      76,
      77,
      78,
      79,
      80,
      81,
      82,
      83,
      84,
      85,
      86,
      87,
      88,
      89,
      90,
      97,
      98,
      99,
      100,
      101,
      102,
      103,
      104,
      105,
      106,
      107,
      108,
      109,
      110,
      111,
      112,
      113,
      114,
      115,
      116,
      117,
      118,
      119,
      120,
      121,
      122,
      48,
      49,
      50,
      51,
      52,
      53,
      54,
      55,
      56,
      57,
      43,
      47
   }
   private final val base64DecodeMap: IntArray
private final val base64UrlEncodeMap: ByteArray
private final val base64UrlDecodeMap: IntArray

@SinceKotlin(version = "1.8")
internal fun isInMimeAlphabet(symbol: Int): Boolean {
   return 0 <= symbol && symbol < base64DecodeMap.length && base64DecodeMap[symbol] != -1;
}

@JvmSynthetic
fun `access$getBase64UrlEncodeMap$p`(): ByteArray {
   return base64UrlEncodeMap;
}

@JvmSynthetic
fun `access$getBase64EncodeMap$p`(): ByteArray {
   return base64EncodeMap;
}

@JvmSynthetic
fun `access$getBase64UrlDecodeMap$p`(): IntArray {
   return base64UrlDecodeMap;
}

@JvmSynthetic
fun `access$getBase64DecodeMap$p`(): IntArray {
   return base64DecodeMap;
}
