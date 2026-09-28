package kotlin.io.encoding

import java.nio.charset.Charset
import kotlin.enums.EnumEntries

@SinceKotlin(version = "2.2")
@WasExperimental(markerClass = [ExperimentalEncodingApi::class])
public open class Base64 private constructor(isUrlSafe: Boolean,
   isMimeScheme: Boolean,
   mimeLineLength: Int,
   paddingOption: kotlin.io.encoding.Base64.PaddingOption
) {
   internal final val isUrlSafe: Boolean
   internal final val isMimeScheme: Boolean
   internal final val mimeLineLength: Int
   internal final val paddingOption: kotlin.io.encoding.Base64.PaddingOption
   private final val mimeGroupsPerLine: Int

   init {
      this.isUrlSafe = isUrlSafe;
      this.isMimeScheme = isMimeScheme;
      this.mimeLineLength = mimeLineLength;
      this.paddingOption = paddingOption;
      if (this.isUrlSafe && this.isMimeScheme) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      } else {
         this.mimeGroupsPerLine = this.mimeLineLength / 4;
      }
   }

   @SinceKotlin(version = "2.0")
   public fun withPadding(option: kotlin.io.encoding.Base64.PaddingOption): Base64 {
      return if (this.paddingOption === option) this else new Base64(this.isUrlSafe, this.isMimeScheme, this.mimeLineLength, option);
   }

   public fun encodeToByteArray(source: ByteArray, startIndex: Int = 0, endIndex: Int = source.length): ByteArray {
      return this.encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex);
   }

   public fun encodeIntoByteArray(source: ByteArray, destination: ByteArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = source.length): Int {
      return this.encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, destinationOffset, startIndex, endIndex);
   }

   public fun encode(source: ByteArray, startIndex: Int = 0, endIndex: Int = source.length): String {
      return new java.lang.String(this.encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex), Charsets.ISO_8859_1);
   }

   public fun <A : Appendable> encodeToAppendable(source: ByteArray, destination: A, startIndex: Int = ..., endIndex: Int = ...): A {
      destination.append(new java.lang.String(this.encodeToByteArrayImpl$kotlin_stdlib(source, startIndex, endIndex), Charsets.ISO_8859_1));
      return (A)destination;
   }

   public fun decode(source: ByteArray, startIndex: Int = 0, endIndex: Int = source.length): ByteArray {
      this.checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
      val destination: ByteArray = new byte[this.decodeSize$kotlin_stdlib(source, startIndex, endIndex)];
      if (this.decodeImpl(source, destination, 0, startIndex, endIndex) != destination.length) {
         throw new IllegalStateException("Check failed.");
      } else {
         return destination;
      }
   }

   public fun decodeIntoByteArray(source: ByteArray, destination: ByteArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = source.length): Int {
      this.checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
      this.checkDestinationBounds(destination.length, destinationOffset, this.decodeSize$kotlin_stdlib(source, startIndex, endIndex));
      return this.decodeImpl(source, destination, destinationOffset, startIndex, endIndex);
   }

   public fun decode(source: CharSequence, startIndex: Int = 0, endIndex: Int = source.length()): ByteArray {
      val var8: ByteArray;
      if (source is java.lang.String) {
         this.checkSourceBounds$kotlin_stdlib((source as java.lang.String).length(), startIndex, endIndex);
         val var10000: java.lang.String = (source as java.lang.String).substring(startIndex, endIndex);
         val var7: Charset = Charsets.ISO_8859_1;
         var8 = var10000.getBytes(var7);
      } else {
         var8 = this.charsToBytesImpl$kotlin_stdlib(source, startIndex, endIndex);
      }

      return decode$default(this, var8, 0, 0, 6, null);
   }

   public fun decodeIntoByteArray(
      source: CharSequence,
      destination: ByteArray,
      destinationOffset: Int = 0,
      startIndex: Int = 0,
      endIndex: Int = source.length()
   ): Int {
      val var10: ByteArray;
      if (source is java.lang.String) {
         this.checkSourceBounds$kotlin_stdlib((source as java.lang.String).length(), startIndex, endIndex);
         val var10000: java.lang.String = (source as java.lang.String).substring(startIndex, endIndex);
         val var9: Charset = Charsets.ISO_8859_1;
         var10 = var10000.getBytes(var9);
      } else {
         var10 = this.charsToBytesImpl$kotlin_stdlib(source, startIndex, endIndex);
      }

      return decodeIntoByteArray$default(this, var10, destination, destinationOffset, 0, 0, 24, null);
   }

   internal fun encodeToByteArrayImpl(source: ByteArray, startIndex: Int, endIndex: Int): ByteArray {
      this.checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
      val destination: ByteArray = new byte[this.encodeSize$kotlin_stdlib(endIndex - startIndex)];
      this.encodeIntoByteArrayImpl$kotlin_stdlib(source, destination, 0, startIndex, endIndex);
      return destination;
   }

   internal fun encodeIntoByteArrayImpl(source: ByteArray, destination: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int): Int {
      this.checkSourceBounds$kotlin_stdlib(source.length, startIndex, endIndex);
      this.checkDestinationBounds(destination.length, destinationOffset, this.encodeSize$kotlin_stdlib(endIndex - startIndex));
      val encodeMap: ByteArray = if (this.isUrlSafe) Base64Kt.access$getBase64UrlEncodeMap$p() else Base64Kt.access$getBase64EncodeMap$p();
      var sourceIndex: Int = startIndex;
      var destinationIndex: Int = destinationOffset;
      val groupsPerLine: Int = if (this.isMimeScheme) this.mimeGroupsPerLine else Integer.MAX_VALUE;

      while (sourceIndex + 2 < endIndex) {
         val groups: Int = Math.min((endIndex - sourceIndex) / 3, groupsPerLine);

         for (int i = 0; i < groups; i++) {
            val bits: Int = (source[sourceIndex++] and 255) shl 16 or (source[sourceIndex++] and 255) shl 8 or source[sourceIndex++] and 255;
            destination[destinationIndex++] = encodeMap[bits ushr 18];
            destination[destinationIndex++] = encodeMap[bits ushr 12 and 63];
            destination[destinationIndex++] = encodeMap[bits ushr 6 and 63];
            destination[destinationIndex++] = encodeMap[bits and 63];
         }

         if (groups == groupsPerLine && sourceIndex != endIndex) {
            destination[destinationIndex++] = mimeLineSeparatorSymbols[0];
            destination[destinationIndex++] = mimeLineSeparatorSymbols[1];
         }
      }

      switch (endIndex - sourceIndex) {
         case 1:
            val bitsx: Int = (source[sourceIndex++] and 255) shl 4;
            destination[destinationIndex++] = encodeMap[bitsx ushr 6];
            destination[destinationIndex++] = encodeMap[bitsx and 63];
            if (this.shouldPadOnEncode()) {
               destination[destinationIndex++] = 61;
               destination[destinationIndex++] = 61;
            }
            break;
         case 2:
            val var31: Int = (source[sourceIndex++] and 255) shl 10 or (source[sourceIndex++] and 255) shl 2;
            destination[destinationIndex++] = encodeMap[var31 ushr 12];
            destination[destinationIndex++] = encodeMap[var31 ushr 6 and 63];
            destination[destinationIndex++] = encodeMap[var31 and 63];
            if (this.shouldPadOnEncode()) {
               destination[destinationIndex++] = 61;
            }
         default:
      }

      if (sourceIndex != endIndex) {
         throw new IllegalStateException("Check failed.");
      } else {
         return destinationIndex - destinationOffset;
      }
   }

   internal fun encodeSize(sourceSize: Int): Int {
      val trailingBytes: Int = sourceSize % 3;
      var size: Int = sourceSize / 3 * 4;
      if (trailingBytes != 0) {
         size += if (this.shouldPadOnEncode()) 4 else trailingBytes + 1;
      }

      if (size < 0) {
         throw new IllegalArgumentException("Input is too big");
      } else {
         if (this.isMimeScheme) {
            size += (size - 1) / this.mimeLineLength * 2;
         }

         if (size < 0) {
            throw new IllegalArgumentException("Input is too big");
         } else {
            return size;
         }
      }
   }

   private fun shouldPadOnEncode(): Boolean {
      return this.paddingOption === Base64.PaddingOption.PRESENT || this.paddingOption === Base64.PaddingOption.PRESENT_OPTIONAL;
   }

   private fun decodeImpl(source: ByteArray, destination: ByteArray, destinationOffset: Int, startIndex: Int, endIndex: Int): Int {
      val decodeMap: IntArray = if (this.isUrlSafe) Base64Kt.access$getBase64UrlDecodeMap$p() else Base64Kt.access$getBase64DecodeMap$p();
      var payload: Int = 0;
      var byteStart: Int = -8;
      var sourceIndex: Int = startIndex;
      var destinationIndex: Int = destinationOffset;
      var hasPadding: Boolean = false;

      while (sourceIndex < endIndex) {
         if (byteStart == -8 && sourceIndex + 3 < endIndex) {
            val bits: Int = decodeMap[source[sourceIndex++] and 255] shl 18 or decodeMap[source[sourceIndex++] and 255] shl 12 or decodeMap[source[sourceIndex++] and 255] shl 6 or decodeMap[source[sourceIndex++] and 255];
            if (bits >= 0) {
               destination[destinationIndex++] = (byte)(bits shr 16);
               destination[destinationIndex++] = (byte)(bits shr 8);
               destination[destinationIndex++] = (byte)bits;
               continue;
            }

            sourceIndex -= 4;
         }

         val var23: Int = source[sourceIndex] and 255;
         val var25: Int = decodeMap[source[sourceIndex] and 255];
         if (decodeMap[source[sourceIndex] and 255] < 0) {
            if (var25 == -2) {
               hasPadding = true;
               sourceIndex = this.handlePaddingSymbol(source, sourceIndex, endIndex, byteStart);
               break;
            }

            if (!this.isMimeScheme) {
               val var32: StringBuilder = new StringBuilder().append("Invalid symbol '").append((char)var23).append("'(");
               val var10003: java.lang.String = Integer.toString(var23, CharsKt.checkRadix(8));
               throw new IllegalArgumentException(var32.append(var10003).append(") at index ").append(sourceIndex).toString());
            }

            sourceIndex++;
         } else {
            sourceIndex++;
            payload = payload shl 6 or var25;
            byteStart += 6;
            if (byteStart >= 0) {
               destination[destinationIndex++] = (byte)(payload ushr byteStart);
               payload &= (1 shl byteStart) - 1;
               byteStart -= 8;
            }
         }
      }

      if (byteStart == -2) {
         throw new IllegalArgumentException("The last unit of input does not have enough bits");
      } else if (byteStart != -8 && !hasPadding && this.paddingOption === Base64.PaddingOption.PRESENT) {
         throw new IllegalArgumentException("The padding option is set to PRESENT, but the input is not properly padded");
      } else if (payload != 0) {
         throw new IllegalArgumentException("The pad bits must be zeros");
      } else {
         sourceIndex = this.skipIllegalSymbolsIfMime(source, sourceIndex, endIndex);
         if (sourceIndex < endIndex) {
            val var33: StringBuilder = new StringBuilder().append("Symbol '").append((char)(source[sourceIndex] and 255)).append("'(");
            val var34: java.lang.String = Integer.toString(source[sourceIndex] and 255, CharsKt.checkRadix(8));
            throw new IllegalArgumentException(
               var33.append(var34).append(") at index ").append(sourceIndex - 1).append(" is prohibited after the pad character").toString()
            );
         } else {
            return destinationIndex - destinationOffset;
         }
      }
   }

   internal fun decodeSize(source: ByteArray, startIndex: Int, endIndex: Int): Int {
      var symbols: Int = endIndex - startIndex;
      if (endIndex - startIndex == 0) {
         return 0;
      } else if (symbols == 1) {
         throw new IllegalArgumentException("Input should have at least 2 symbols for Base64 decoding, startIndex: $startIndex, endIndex: $endIndex");
      } else {
         if (this.isMimeScheme) {
            for (int index = startIndex; index < endIndex; index++) {
               val symbolBits: Int = Base64Kt.access$getBase64DecodeMap$p()[source[index] and 255];
               if (symbolBits < 0) {
                  if (symbolBits == -2) {
                     symbols -= endIndex - index;
                     break;
                  }

                  symbols--;
               }
            }
         } else if (source[endIndex - 1] == 61) {
            symbols--;
            if (source[endIndex - 2] == 61) {
               symbols--;
            }
         }

         return (int)((long)symbols * 6 / 8);
      }
   }

   internal fun charsToBytesImpl(source: CharSequence, startIndex: Int, endIndex: Int): ByteArray {
      this.checkSourceBounds$kotlin_stdlib(source.length(), startIndex, endIndex);
      val byteArray: ByteArray = new byte[endIndex - startIndex];
      var length: Int = 0;

      for (int index = startIndex; index < endIndex; index++) {
         val symbol: Int = source.charAt(index);
         if (symbol <= 255) {
            byteArray[length++] = (byte)symbol;
         } else {
            byteArray[length++] = 63;
         }
      }

      return byteArray;
   }

   internal fun bytesToStringImpl(source: ByteArray): String {
      val stringBuilder: StringBuilder = new StringBuilder(source.length);

      for (byte byte : source) {
         stringBuilder.append((char)var5);
      }

      return stringBuilder.toString();
   }

   private fun handlePaddingSymbol(source: ByteArray, padIndex: Int, endIndex: Int, byteStart: Int): Int {
      var var10000: Int;
      switch (byteStart) {
         case -8:
            throw new IllegalArgumentException("Redundant pad character at index $padIndex");
         case -7:
         case -5:
         case -3:
         default:
            throw new IllegalStateException("Unreachable".toString());
         case -6:
            this.checkPaddingIsAllowed(padIndex);
            var10000 = padIndex + 1;
            break;
         case -4:
            this.checkPaddingIsAllowed(padIndex);
            val secondPadIndex: Int = this.skipIllegalSymbolsIfMime(source, padIndex + 1, endIndex);
            if (secondPadIndex == endIndex || source[secondPadIndex] != 61) {
               throw new IllegalArgumentException("Missing one pad character at index $secondPadIndex");
            }

            var10000 = secondPadIndex + 1;
            break;
         case -2:
            var10000 = padIndex + 1;
      }

      return var10000;
   }

   private fun checkPaddingIsAllowed(padIndex: Int) {
      if (this.paddingOption === Base64.PaddingOption.ABSENT) {
         throw new IllegalArgumentException("The padding option is set to ABSENT, but the input has a pad character at index $padIndex");
      }
   }

   private fun skipIllegalSymbolsIfMime(source: ByteArray, startIndex: Int, endIndex: Int): Int {
      if (!this.isMimeScheme) {
         return startIndex;
      } else {
         var sourceIndex: Int;
         for (sourceIndex = startIndex; sourceIndex < endIndex; sourceIndex++) {
            if (Base64Kt.access$getBase64DecodeMap$p()[source[sourceIndex] and 255] != -1) {
               return sourceIndex;
            }
         }

         return sourceIndex;
      }
   }

   internal fun checkSourceBounds(sourceSize: Int, startIndex: Int, endIndex: Int) {
      AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(startIndex, endIndex, sourceSize);
   }

   private fun checkDestinationBounds(destinationSize: Int, destinationOffset: Int, capacityNeeded: Int) {
      if (destinationOffset >= 0 && destinationOffset <= destinationSize) {
         if (destinationOffset + capacityNeeded < 0 || destinationOffset + capacityNeeded > destinationSize) {
            throw new IndexOutOfBoundsException(
               "The destination array does not have enough capacity, destination offset: $destinationOffset, destination size: $destinationSize, capacity needed: $capacityNeeded"
            );
         }
      } else {
         throw new IndexOutOfBoundsException("destination offset: $destinationOffset, destination size: $destinationSize");
      }
   }

   public companion object Default : Base64(false, false, -1, Base64.PaddingOption.PRESENT) {
      private const val bitsPerByte: Int
      private const val bitsPerSymbol: Int
      internal const val bytesPerGroup: Int
      internal const val symbolsPerGroup: Int
      internal const val padSymbol: Byte
      private const val lineLengthMime: Int
      private const val lineLengthPem: Int
      internal final val mimeLineSeparatorSymbols: ByteArray
      public final val UrlSafe: Base64
      public final val Mime: Base64
      public final val Pem: Base64
   }

   @SinceKotlin(version = "2.0")
   public enum class PaddingOption {
      PRESENT,
      ABSENT,
      PRESENT_OPTIONAL,
      ABSENT_OPTIONAL
      @JvmStatic
      fun getEntries(): EnumEntries<Base64.PaddingOption> {
         return $ENTRIES;
      }
   }
}
