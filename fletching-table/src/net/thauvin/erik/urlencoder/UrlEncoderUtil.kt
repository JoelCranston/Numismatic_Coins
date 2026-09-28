package net.thauvin.erik.urlencoder

import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nUrlEncoderUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UrlEncoderUtil.kt\nnet/thauvin/erik/urlencoder/UrlEncoderUtil\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,248:1\n1#2:249\n*E\n"])
public object UrlEncoderUtil {
   private final val hexDigits: CharArray
   private final val unreservedChars: BooleanArray

   private fun Char.isUnreserved(): Boolean {
      return Intrinsics.compare(`$this$isUnreserved`, 122) <= 0 && unreservedChars[`$this$isUnreserved`];
   }

   private fun StringBuilder.appendEncodedDigit(digit: Int) {
      `$this$appendEncodedDigit`.append(hexDigits[digit and 15]);
   }

   private fun StringBuilder.appendEncodedByte(ch: Int) {
      `$this$appendEncodedByte`.append("%");
      this.appendEncodedDigit(`$this$appendEncodedByte`, ch shr 4);
      this.appendEncodedDigit(`$this$appendEncodedByte`, ch);
   }

   @JvmOverloads
   @JvmStatic
   public fun decode(source: String, plusToSpace: Boolean = false): String {
      if (source.length() == 0) {
         return source;
      } else {
         val length: Int = source.length();
         val out: StringBuilder = new StringBuilder(length);
         var bytesBuffer: ByteArray = null;
         var bytesPos: Int = 0;
         var i: Int = 0;
         var started: Boolean = false;

         while (i < length) {
            val ch: Char = source.charAt(i);
            if (ch == '%') {
               if (!started) {
                  out.append(source, 0, i);
                  started = true;
               }

               if (bytesBuffer == null) {
                  bytesBuffer = new byte[(length - i) / 3];
               }

               if (length < ++i + 2) {
                  throw new IllegalArgumentException(("Incomplete trailing escape ($ch) pattern").toString());
               }

               try {
                  val var10000: java.lang.String = source.substring(i, i + 2);
                  val e: Int = Integer.parseInt(var10000, CharsKt.checkRadix(16));
                  if (0 > e || e >= 256) {
                     throw new IllegalArgumentException("Illegal escape value".toString());
                  }

                  bytesBuffer[bytesPos++] = (byte)e;
                  i += 2;
               } catch (var12: NumberFormatException) {
                  throw new IllegalArgumentException("Illegal characters in escape sequence: $var12.message", var12);
               }
            } else {
               if (bytesBuffer != null) {
                  out.append(StringsKt.decodeToString$default(bytesBuffer, 0, bytesPos, false, 4, null));
                  started = true;
                  bytesBuffer = null;
                  bytesPos = 0;
               }

               if (plusToSpace && ch == '+') {
                  if (!started) {
                     out.append(source, 0, i);
                     started = true;
                  }

                  out.append(" ");
               } else if (started) {
                  out.append(ch);
               }

               i++;
            }
         }

         if (bytesBuffer != null) {
            out.append(StringsKt.decodeToString$default(bytesBuffer, 0, bytesPos, false, 4, null));
         }

         val var16: java.lang.String;
         if (!started) {
            var16 = source;
         } else {
            var16 = out.toString();
         }

         return var16;
      }
   }

   @JvmOverloads
   @JvmStatic
   public fun encode(source: String, allow: String = "", spaceToPlus: Boolean = false): String {
      if (source.length() == 0) {
         return source;
      } else {
         var out: StringBuilder = null;
         var i: Int = 0;

         while (i < source.length()) {
            val ch: Char = source.charAt(i);
            if (!INSTANCE.isUnreserved(ch) && !StringsKt.contains$default(allow, ch, false, 2, null)) {
               if (out == null) {
                  out = new StringBuilder(source.length());
                  out.append(source, 0, i);
               }

               val cp: Int = INSTANCE.codePointAt(source, i);
               if (cp < 128) {
                  if (spaceToPlus && ch == ' ') {
                     out.append('+');
                  } else {
                     INSTANCE.appendEncodedByte(out, cp);
                  }

                  i++;
               } else if (Character.INSTANCE.isBmpCodePoint$urlencoder_lib(cp)) {
                  for (byte b : StringsKt.encodeToByteArray(java.lang.String.valueOf(ch))) {
                     INSTANCE.appendEncodedByte(out, var17);
                  }

                  i++;
               } else if (Character.INSTANCE.isSupplementaryCodePoint$urlencoder_lib(cp)) {
                  for (byte b : StringsKt.encodeToByteArray(
                     StringsKt.concatToString(
                        new char[]{Character.INSTANCE.highSurrogateOf$urlencoder_lib(cp), Character.INSTANCE.lowSurrogateOf$urlencoder_lib(cp)}
                     )
                  )) {
                     INSTANCE.appendEncodedByte(out, b);
                  }

                  i += 2;
               }
            } else {
               if (out != null) {
                  out.append(ch);
               }

               i++;
            }
         }

         var var10000: java.lang.String = if (out != null) out.toString() else null;
         if (var10000 == null) {
            var10000 = source;
         }

         return var10000;
      }
   }

   private fun CharSequence.codePointAt(index: Int): Int {
      if (0 > index || index >= `$this$codePointAt`.length()) {
         throw new IndexOutOfBoundsException("index $index was not in range ${StringsKt.getIndices(`$this$codePointAt`)}");
      } else {
         val firstChar: Char = `$this$codePointAt`.charAt(index);
         if (java.lang.Character.isHighSurrogate(firstChar)) {
            val nextChar: java.lang.Character = StringsKt.getOrNull(`$this$codePointAt`, index + 1);
            if (nextChar != null && java.lang.Character.isLowSurrogate(nextChar)) {
               return Character.INSTANCE.toCodePoint$urlencoder_lib(firstChar, nextChar);
            }
         }

         return firstChar;
      }
   }

   @JvmOverloads
   @JvmStatic
   fun decode(source: java.lang.String): java.lang.String {
      return decode$default(source, false, 2, null);
   }

   @JvmOverloads
   @JvmStatic
   fun encode(source: java.lang.String, allow: java.lang.String): java.lang.String {
      return encode$default(source, allow, false, 4, null);
   }

   @JvmOverloads
   @JvmStatic
   fun encode(source: java.lang.String): java.lang.String {
      return encode$default(source, null, false, 6, null);
   }

   @JvmStatic
   fun {
      val var10000: CharArray = "0123456789ABCDEF".toCharArray();
      hexDigits = var10000;
      val var0: BooleanArray = new boolean[123];
      val `$this$unreservedChars_u24lambda_u240`: BooleanArray = var0;
      var0[45] = true;
      var0[46] = true;
      var0[95] = true;

      for (char c = '0'; c < ':'; c++) {
         `$this$unreservedChars_u24lambda_u240`[c] = true;
      }

      for (char c = 'A'; c < '['; c++) {
         `$this$unreservedChars_u24lambda_u240`[var4] = true;
      }

      for (char c = 'a'; c < '{'; c++) {
         `$this$unreservedChars_u24lambda_u240`[var5] = true;
      }

      unreservedChars = var0;
   }
}
