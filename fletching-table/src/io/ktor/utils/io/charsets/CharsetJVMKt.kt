@file:SourceDebugExtension(["SMAP\nCharsetJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CharsetJVM.kt\nio/ktor/utils/io/charsets/CharsetJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n1#2:103\n*E\n"])

package io.ktor.utils.io.charsets

import io.ktor.utils.io.core.ByteReadPacketKt
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CharsetDecoder
import java.nio.charset.CharsetEncoder
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.ByteStringsKt
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.Utf8Kt
import kotlinx.io.bytestring.ByteStringJvmExtKt

public final val name: String
   public final get() {
      val var10000: java.lang.String = `$this$name`.name();
      return var10000;
   }


public final val charset: Charset
   public final get() {
      val var10000: Charset = `$this$charset`.charset();
      return var10000;
   }


public final val charset: Charset
   public final get() {
      val var10000: Charset = `$this$charset`.charset();
      return var10000;
   }


public fun Charsets.forName(name: String): Charset {
   val var10000: Charset = Charset.forName(name);
   return var10000;
}

public fun Charsets.isSupported(name: String): Boolean {
   return Charset.isSupported(name);
}

public fun CharsetEncoder.encodeToByteArray(input: CharSequence, fromIndex: Int = 0, toIndex: Int = input.length()): ByteArray {
   if (input is java.lang.String) {
      if (fromIndex == 0 && toIndex == (input as java.lang.String).length()) {
         val var5: ByteArray = (input as java.lang.String).getBytes(`$this$encodeToByteArray`.charset());
         return var5;
      } else {
         val var10000: java.lang.String = (input as java.lang.String).substring(fromIndex, toIndex);
         val var4: ByteArray = var10000.getBytes(`$this$encodeToByteArray`.charset());
         return var4;
      }
   } else {
      return encodeToByteArraySlow(`$this$encodeToByteArray`, input, fromIndex, toIndex);
   }
}

@JvmSynthetic
fun `encodeToByteArray$default`(var0: CharsetEncoder, var1: java.lang.CharSequence, var2: Int, var3: Int, var4: Int, var5: Any): ByteArray {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length();
   }

   return encodeToByteArray(var0, var1, var2, var3);
}

private fun CharsetEncoder.encodeToByteArraySlow(input: CharSequence, fromIndex: Int, toIndex: Int): ByteArray {
   val result: ByteBuffer = `$this$encodeToByteArraySlow`.encode(CharBuffer.wrap(input, fromIndex, toIndex));
   var var10000: ByteArray;
   if (result.hasArray() && result.arrayOffset() == 0) {
      val var6: ByteArray = result.array();
      var10000 = if (var6.length == result.remaining()) var6 else null;
   } else {
      var10000 = null;
   }

   var10000 = var10000;
   if (var10000 == null) {
      val var9: ByteArray = new byte[result.remaining()];
      result.get(var9);
      var10000 = var9;
   }

   return var10000;
}

internal fun CharsetEncoder.encodeImpl(input: CharSequence, fromIndex: Int, toIndex: Int, dst: Sink): Int {
   val result: ByteArray = encodeToByteArray(`$this$encodeImpl`, input, fromIndex, toIndex);
   Sink.write$default(dst, result, 0, 0, 6, null);
   return result.length;
}

internal fun CharsetEncoder.encodeToByteArrayImpl(input: CharSequence, fromIndex: Int = 0, toIndex: Int = input.length()): ByteArray {
   throw new IllegalStateException("Not needed on jvm".toString());
}

@JvmSynthetic
fun `encodeToByteArrayImpl$default`(var0: CharsetEncoder, var1: java.lang.CharSequence, var2: Int, var3: Int, var4: Int, var5: Any): ByteArray {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length();
   }

   return encodeToByteArrayImpl(var0, var1, var2, var3);
}

public fun CharsetDecoder.decode(input: Source, dst: Appendable, max: Int): Int {
   if (getCharset(`$this$decode`) == Charsets.UTF_8) {
      val var7: java.lang.String = Utf8Kt.readString(input);
      dst.append(var7);
      return var7.length();
   } else {
      val result: Long = ByteReadPacketKt.getRemaining(input);
      dst.append(ByteStringJvmExtKt.decodeToString(ByteStringsKt.readByteString(input), getCharset(`$this$decode`)));
      return (int)result;
   }
}

/** @deprecated */
@JvmSynthetic
fun `Charset$annotations`() {
}
