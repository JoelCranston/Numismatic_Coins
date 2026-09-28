@file:SourceDebugExtension(["SMAP\nStrings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Strings.kt\nio/ktor/utils/io/core/StringsKt\n+ 2 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n*L\n1#1,141:1\n21#2,3:142\n*S KotlinDebug\n*F\n+ 1 Strings.kt\nio/ktor/utils/io/core/StringsKt\n*L\n35#1:142,3\n*E\n"])

package io.ktor.utils.io.core

import io.ktor.utils.io.charsets.CharsetJVMKt
import io.ktor.utils.io.charsets.EncodingKt
import java.io.EOFException
import java.nio.charset.Charset
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.SourcesKt
import kotlinx.io.Utf8Kt

public fun String.toByteArray(charset: Charset = Charsets.UTF_8): ByteArray {
   return if (charset == Charsets.UTF_8)
      kotlin.text.StringsKt.encodeToByteArray$default(`$this$toByteArray`, 0, 0, true, 3, null)
      else
      CharsetJVMKt.encodeToByteArray(charset.newEncoder(), `$this$toByteArray`, 0, `$this$toByteArray`.length());
}

@JvmSynthetic
fun `toByteArray$default`(var0: java.lang.String, var1: Charset, var2: Int, var3: Any): ByteArray {
   if ((var2 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   return toByteArray(var0, var1);
}

@Deprecated(message = "Use decodeToString instead", replaceWith = @ReplaceWith(expression = "bytes.decodeToString(offset, offset + length)", imports = []), level = DeprecationLevel.WARNING)
public fun String(bytes: ByteArray, offset: Int = 0, length: Int = bytes.length, charset: Charset = Charsets.UTF_8): String {
   val var10000: java.lang.String;
   if (charset == Charsets.UTF_8) {
      var10000 = kotlin.text.StringsKt.decodeToString$default(bytes, offset, offset + length, false, 4, null);
   } else {
      val `builder$iv`: Buffer = new Buffer();
      BytePacketBuilderKt.writeFully(`builder$iv`, bytes, offset, length);
      var10000 = readText$default(`builder$iv`, charset, 0, 2, null);
   }

   return var10000;
}

/** @deprecated */
@JvmSynthetic
fun `String$default`(var0: ByteArray, var1: Int, var2: Int, var3: Charset, var4: Int, var5: Any): java.lang.String {
   if ((var4 and 2) != 0) {
      var1 = 0;
   }

   if ((var4 and 4) != 0) {
      var2 = var0.length;
   }

   if ((var4 and 8) != 0) {
      var3 = Charsets.UTF_8;
   }

   return String(var0, var1, var2, var3);
}

@Deprecated(message = "Use readByteArray instead", replaceWith = @ReplaceWith(expression = "this.readByteArray()", imports = ["kotlinx.io.readByteArray"]))
public fun Source.readBytes(): ByteArray {
   return SourcesKt.readByteArray(`$this$readBytes`);
}

@Deprecated(message = "Use readByteArray instead", replaceWith = @ReplaceWith(expression = "this.readByteArray(count)", imports = []))
public fun Source.readBytes(count: Int): ByteArray {
   return SourcesKt.readByteArray(`$this$readBytes`, count);
}

public fun Source.readText(charset: Charset = Charsets.UTF_8, max: Int = Integer.MAX_VALUE): String {
   label11:
   if (charset == Charsets.UTF_8) {
      return if (max == Integer.MAX_VALUE)
         Utf8Kt.readString(`$this$readText`)
         else
         Utf8Kt.readString(`$this$readText`, Math.min(`$this$readText`.getBuffer().getSize(), (long)max));
   } else {
      return EncodingKt.decode(charset.newDecoder(), `$this$readText`, max);
   }
}

@JvmSynthetic
fun `readText$default`(var0: Source, var1: Charset, var2: Int, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   if ((var3 and 2) != 0) {
      var2 = Integer.MAX_VALUE;
   }

   return readText(var0, var1, var2);
}

@Deprecated(message = "Use readTextExactCharacters instead.", replaceWith = @ReplaceWith(expression = "readTextExactCharacters(n, charset)", imports = []))
public fun Source.readTextExact(charset: Charset = Charsets.UTF_8, n: Int): String {
   return readTextExactCharacters(`$this$readTextExact`, n, charset);
}

/** @deprecated */
@JvmSynthetic
fun `readTextExact$default`(var0: Source, var1: Charset, var2: Int, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   return readTextExact(var0, var1, var2);
}

public fun Source.readTextExactCharacters(charactersCount: Int, charset: Charset = Charsets.UTF_8): String {
   val s: java.lang.String = readText(`$this$readTextExactCharacters`, charset, charactersCount);
   if (s.length() < charactersCount) {
      prematureEndOfStreamToReadChars(charactersCount);
      throw new KotlinNothingValueException();
   } else {
      return s;
   }
}

@JvmSynthetic
fun `readTextExactCharacters$default`(var0: Source, var1: Int, var2: Charset, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 2) != 0) {
      var2 = Charsets.UTF_8;
   }

   return readTextExactCharacters(var0, var1, var2);
}

public fun Sink.writeText(text: CharSequence, fromIndex: Int = 0, toIndex: Int = text.length(), charset: Charset = Charsets.UTF_8) {
   if (charset === Charsets.UTF_8) {
      Utf8Kt.writeString(`$this$writeText`, text.toString(), fromIndex, toIndex);
   } else {
      EncodingKt.encodeToImpl(charset.newEncoder(), `$this$writeText`, text, fromIndex, toIndex);
   }
}

@JvmSynthetic
fun `writeText$default`(var0: Sink, var1: java.lang.CharSequence, var2: Int, var3: Int, var4: Charset, var5: Int, var6: Any) {
   if ((var5 and 2) != 0) {
      var2 = 0;
   }

   if ((var5 and 4) != 0) {
      var3 = var1.length();
   }

   if ((var5 and 8) != 0) {
      var4 = Charsets.UTF_8;
   }

   writeText(var0, var1, var2, var3, var4);
}

public fun Sink.writeText(text: CharArray, fromIndex: Int = 0, toIndex: Int = text.length, charset: Charset = Charsets.UTF_8) {
   if (charset === Charsets.UTF_8) {
      Utf8Kt.writeString(`$this$writeText`, kotlin.text.StringsKt.concatToString(text, fromIndex, fromIndex + toIndex), 0, toIndex - fromIndex);
   } else {
      EncodingKt.encode(charset.newEncoder(), text, fromIndex, toIndex, `$this$writeText`);
   }
}

@JvmSynthetic
fun `writeText$default`(var0: Sink, var1: CharArray, var2: Int, var3: Int, var4: Charset, var5: Int, var6: Any) {
   if ((var5 and 2) != 0) {
      var2 = 0;
   }

   if ((var5 and 4) != 0) {
      var3 = var1.length;
   }

   if ((var5 and 8) != 0) {
      var4 = Charsets.UTF_8;
   }

   writeText(var0, var1, var2, var3, var4);
}

private fun prematureEndOfStreamToReadChars(charactersCount: Int): Nothing {
   throw new EOFException("Not enough input bytes to read $charactersCount characters.");
}
