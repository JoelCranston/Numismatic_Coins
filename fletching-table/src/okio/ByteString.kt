package okio

import java.io.EOFException
import java.io.InputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.OutputStream
import java.io.Serializable
import java.lang.reflect.Field
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.security.InvalidKeyException
import java.security.MessageDigest
import java.util.Arrays
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.jvm.internal.SourceDebugExtension
import okio.internal.-ByteString
import okio.internal.-ByteStringNonJs

@SourceDebugExtension(["SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/ByteString\n+ 2 ByteString.kt\nokio/internal/-ByteString\n+ 3 Util.kt\nokio/-SegmentedByteString\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,364:1\n42#2,7:365\n52#2:372\n55#2:373\n62#2,4:374\n66#2:379\n68#2:381\n74#2,23:382\n102#2,23:405\n129#2,2:428\n131#2,9:431\n143#2:440\n146#2:441\n149#2:442\n152#2:443\n160#2:444\n170#2,3:445\n169#2:448\n183#2,2:449\n188#2:451\n192#2:452\n196#2:453\n200#2:454\n204#2,7:455\n217#2:462\n221#2,8:463\n233#2,4:471\n242#2,5:475\n251#2,6:480\n257#2,9:487\n301#2,8:496\n129#2,2:504\n131#2,9:507\n312#2,9:516\n67#3:378\n73#3:380\n73#3:486\n1#4:430\n1#4:506\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/ByteString\n*L\n66#1:365,7\n71#1:372\n108#1:373\n110#1:374,4\n110#1:379\n110#1:381\n112#1:382,23\n114#1:405,23\n118#1:428,2\n118#1:431,9\n120#1:440\n129#1:441\n131#1:442\n133#1:443\n152#1:444\n159#1:445,3\n159#1:448\n166#1:449,2\n168#1:451\n170#1:452\n172#1:453\n174#1:454\n180#1:455,7\n183#1:462\n186#1:463,8\n188#1:471,4\n190#1:475,5\n192#1:480,6\n192#1:487,9\n194#1:496,8\n194#1:504,2\n194#1:507,9\n194#1:516,9\n110#1:378\n110#1:380\n192#1:486\n118#1:430\n194#1:506\n*E\n"])
public open class ByteString internal constructor(data: ByteArray) : Serializable, java.lang.Comparable<ByteString> {
   internal final val data: ByteArray
   internal final var hashCode: Int
   internal final var utf8: String?

   public final val size: Int
      public final get() {
         return this.getSize$okio();
      }


   init {
      this.data = data;
   }

   public open fun utf8(): String {
      var `result$iv`: java.lang.String = this.getUtf8$okio();
      if (`result$iv` == null) {
         `result$iv` = _JvmPlatformKt.toUtf8String(this.internalArray$okio());
         this.setUtf8$okio(`result$iv`);
      }

      return `result$iv`;
   }

   public open fun string(charset: Charset): String {
      return new java.lang.String(this.data, charset);
   }

   public open fun base64(): String {
      return -Base64.encodeBase64$default(this.getData$okio(), null, 1, null);
   }

   public fun md5(): ByteString {
      return this.digest$okio("MD5");
   }

   public fun sha1(): ByteString {
      return this.digest$okio("SHA-1");
   }

   public fun sha256(): ByteString {
      return this.digest$okio("SHA-256");
   }

   public fun sha512(): ByteString {
      return this.digest$okio("SHA-512");
   }

   internal open fun digest(algorithm: String): ByteString {
      val `$this$digest_u24lambda_u240`: MessageDigest = MessageDigest.getInstance(algorithm);
      `$this$digest_u24lambda_u240`.update(this.data, 0, this.size());
      val digestBytes: ByteArray = `$this$digest_u24lambda_u240`.digest();
      return new ByteString(digestBytes);
   }

   public open fun hmacSha1(key: ByteString): ByteString {
      return this.hmac$okio("HmacSHA1", key);
   }

   public open fun hmacSha256(key: ByteString): ByteString {
      return this.hmac$okio("HmacSHA256", key);
   }

   public open fun hmacSha512(key: ByteString): ByteString {
      return this.hmac$okio("HmacSHA512", key);
   }

   internal open fun hmac(algorithm: String, key: ByteString): ByteString {
      try {
         val mac: Mac = Mac.getInstance(algorithm);
         mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
         val var10002: ByteArray = mac.doFinal(this.data);
         return new ByteString(var10002);
      } catch (var5: InvalidKeyException) {
         throw new IllegalArgumentException(var5);
      }
   }

   public open fun base64Url(): String {
      return -Base64.encodeBase64(this.getData$okio(), -Base64.getBASE64_URL_SAFE());
   }

   public open fun hex(): String {
      val `result$iv`: CharArray = new char[this.getData$okio().length * 2];
      var `c$iv`: Int = 0;

      for (byte b$iv : this.getData$okio()) {
         `result$iv`[`c$iv`++] = -ByteString.getHEX_DIGIT_CHARS()[`b$iv` shr 4 and 15];
         `result$iv`[`c$iv`++] = -ByteString.getHEX_DIGIT_CHARS()[`b$iv` and 15];
      }

      return StringsKt.concatToString(`result$iv`);
   }

   public open fun toAsciiLowercase(): ByteString {
      val `$this$commonToAsciiLowercase$iv`: ByteString = this;
      var `i$iv`: Int = 0;

      var var9: ByteString;
      while (true) {
         if (`i$iv` >= `$this$commonToAsciiLowercase$iv`.getData$okio().length) {
            var9 = `$this$commonToAsciiLowercase$iv`;
            break;
         }

         var `c$iv`: Byte = `$this$commonToAsciiLowercase$iv`.getData$okio()[`i$iv`];
         if (`c$iv` >= 65 && `c$iv` <= 90) {
            val var10000: ByteArray = `$this$commonToAsciiLowercase$iv`.getData$okio();
            val var8: ByteArray = Arrays.copyOf(var10000, var10000.length);
            val `lowercase$iv`: ByteArray = var8;
            var8[`i$iv`++] = (byte)(`c$iv` - -32);

            while (i$iv < lowercase$iv.length) {
               `c$iv` = `lowercase$iv`[`i$iv`];
               if (`lowercase$iv`[`i$iv`] >= 65 && `lowercase$iv`[`i$iv`] <= 90) {
                  `lowercase$iv`[`i$iv`] = (byte)(`c$iv` - -32);
                  `i$iv`++;
               } else {
                  `i$iv`++;
               }
            }

            var9 = new ByteString(`lowercase$iv`);
            break;
         }

         `i$iv`++;
      }

      return var9;
   }

   public open fun toAsciiUppercase(): ByteString {
      val `$this$commonToAsciiUppercase$iv`: ByteString = this;
      var `i$iv`: Int = 0;

      var var9: ByteString;
      while (true) {
         if (`i$iv` >= `$this$commonToAsciiUppercase$iv`.getData$okio().length) {
            var9 = `$this$commonToAsciiUppercase$iv`;
            break;
         }

         var `c$iv`: Byte = `$this$commonToAsciiUppercase$iv`.getData$okio()[`i$iv`];
         if (`c$iv` >= 97 && `c$iv` <= 122) {
            val var10000: ByteArray = `$this$commonToAsciiUppercase$iv`.getData$okio();
            val var8: ByteArray = Arrays.copyOf(var10000, var10000.length);
            val `lowercase$iv`: ByteArray = var8;
            var8[`i$iv`++] = (byte)(`c$iv` - 32);

            while (i$iv < lowercase$iv.length) {
               `c$iv` = `lowercase$iv`[`i$iv`];
               if (`lowercase$iv`[`i$iv`] >= 97 && `lowercase$iv`[`i$iv`] <= 122) {
                  `lowercase$iv`[`i$iv`] = (byte)(`c$iv` - 32);
                  `i$iv`++;
               } else {
                  `i$iv`++;
               }
            }

            var9 = new ByteString(`lowercase$iv`);
            break;
         }

         `i$iv`++;
      }

      return var9;
   }

   @JvmOverloads
   public open fun substring(beginIndex: Int = 0, endIndex: Int = -SegmentedByteString.getDEFAULT__ByteString_size()): ByteString {
      val `endIndex$iv`: Int = -SegmentedByteString.resolveDefaultParameter(this, endIndex);
      if (beginIndex < 0) {
         throw new IllegalArgumentException("beginIndex < 0".toString());
      } else if (`endIndex$iv` > this.getData$okio().length) {
         throw new IllegalArgumentException(("endIndex > length(${this.getData$okio().length})").toString());
      } else if (`endIndex$iv` - beginIndex < 0) {
         throw new IllegalArgumentException("endIndex < beginIndex".toString());
      } else {
         return if (beginIndex == 0 && `endIndex$iv` == this.getData$okio().length)
            this
            else
            new ByteString(ArraysKt.copyOfRange(this.getData$okio(), beginIndex, `endIndex$iv`));
      }
   }

   internal open fun internalGet(pos: Int): Byte {
      return this.getData$okio()[pos];
   }

   @JvmName(name = "getByte")
   public operator fun get(index: Int): Byte {
      return this.internalGet$okio(index);
   }

   internal open fun getSize(): Int {
      return this.getData$okio().length;
   }

   public open fun toByteArray(): ByteArray {
      var var10000: ByteArray = this.getData$okio();
      var10000 = Arrays.copyOf(var10000, var10000.length);
      return var10000;
   }

   internal open fun internalArray(): ByteArray {
      return this.getData$okio();
   }

   public open fun asByteBuffer(): ByteBuffer {
      val var10000: ByteBuffer = ByteBuffer.wrap(this.data).asReadOnlyBuffer();
      return var10000;
   }

   @Throws(java/io/IOException::class)
   public open fun write(out: OutputStream) {
      out.write(this.data);
   }

   internal open fun write(buffer: Buffer, offset: Int, byteCount: Int) {
      -ByteString.commonWrite(this, buffer, offset, byteCount);
   }

   public open fun rangeEquals(offset: Int, other: ByteString, otherOffset: Int, byteCount: Int): Boolean {
      return other.rangeEquals(otherOffset, this.getData$okio(), offset, byteCount);
   }

   public open fun rangeEquals(offset: Int, other: ByteArray, otherOffset: Int, byteCount: Int): Boolean {
      return offset >= 0
         && offset <= this.getData$okio().length - byteCount
         && otherOffset >= 0
         && otherOffset <= other.length - byteCount
         && -SegmentedByteString.arrayRangeEquals(this.getData$okio(), offset, other, otherOffset, byteCount);
   }

   public open fun copyInto(offset: Int = 0, target: ByteArray, targetOffset: Int = 0, byteCount: Int) {
      ArraysKt.copyInto(this.getData$okio(), target, targetOffset, offset, offset + byteCount);
   }

   public fun startsWith(prefix: ByteString): Boolean {
      return this.rangeEquals(0, prefix, 0, prefix.size());
   }

   public fun startsWith(prefix: ByteArray): Boolean {
      return this.rangeEquals(0, prefix, 0, prefix.length);
   }

   public fun endsWith(suffix: ByteString): Boolean {
      return this.rangeEquals(this.size() - suffix.size(), suffix, 0, suffix.size());
   }

   public fun endsWith(suffix: ByteArray): Boolean {
      return this.rangeEquals(this.size() - suffix.length, suffix, 0, suffix.length);
   }

   @JvmOverloads
   public fun indexOf(other: ByteString, fromIndex: Int = 0): Int {
      return this.indexOf(other.internalArray$okio(), fromIndex);
   }

   @JvmOverloads
   public open fun indexOf(other: ByteArray, fromIndex: Int = 0): Int {
      val `$this$commonIndexOf$iv`: ByteString = this;
      val `other$iv`: ByteArray = other;
      val `limit$iv`: Int = this.getData$okio().length - other.length;
      var `i$iv`: Int = Math.max(fromIndex, 0);
      if (`i$iv` <= `limit$iv`) {
         while (true) {
            if (-SegmentedByteString.arrayRangeEquals(`$this$commonIndexOf$iv`.getData$okio(), `i$iv`, `other$iv`, 0, `other$iv`.length)) {
               return `i$iv`;
            }

            if (`i$iv` == `limit$iv`) {
               break;
            }

            `i$iv`++;
         }
      }

      return -1;
   }

   @JvmOverloads
   public fun lastIndexOf(other: ByteString, fromIndex: Int = -SegmentedByteString.getDEFAULT__ByteString_size()): Int {
      return this.lastIndexOf(other.internalArray$okio(), fromIndex);
   }

   @JvmOverloads
   public open fun lastIndexOf(other: ByteArray, fromIndex: Int = -SegmentedByteString.getDEFAULT__ByteString_size()): Int {
      val `$this$commonLastIndexOf$iv`: ByteString = this;
      val `other$iv`: ByteArray = other;
      var `i$iv`: Int = Math.min(-SegmentedByteString.resolveDefaultParameter(this, fromIndex), this.getData$okio().length - other.length);

      var var10000: Int;
      while (true) {
         if (-1 >= `i$iv`) {
            var10000 = -1;
            break;
         }

         if (-SegmentedByteString.arrayRangeEquals(`$this$commonLastIndexOf$iv`.getData$okio(), `i$iv`, `other$iv`, 0, `other$iv`.length)) {
            var10000 = `i$iv`;
            break;
         }

         `i$iv`--;
      }

      return var10000;
   }

   public override operator fun equals(other: Any?): Boolean {
      return other === this
         || other is ByteString
            && (other as ByteString).size() == this.getData$okio().length
            && (other as ByteString).rangeEquals(0, this.getData$okio(), 0, this.getData$okio().length);
   }

   public override fun hashCode(): Int {
      val `result$iv`: Int = this.getHashCode$okio();
      val var10000: Int;
      if (`result$iv` != 0) {
         var10000 = `result$iv`;
      } else {
         val var4: Int = Arrays.hashCode(this.getData$okio());
         this.setHashCode$okio(var4);
         var10000 = var4;
      }

      return var10000;
   }

   public open operator fun compareTo(other: ByteString): Int {
      val `$this$commonCompareTo$iv`: ByteString = this;
      val `other$iv`: ByteString = other;
      val `sizeA$iv`: Int = this.size();
      val `sizeB$iv`: Int = other.size();
      var `i$iv`: Int = 0;
      val `size$iv`: Int = Math.min(`sizeA$iv`, `sizeB$iv`);

      var var10000: Int;
      while (true) {
         if (`i$iv` < `size$iv`) {
            val `byteA$iv`: Int = `$this$commonCompareTo$iv`.getByte(`i$iv`) and 255;
            val var15: Byte = `other$iv`.getByte(`i$iv`);
            val var14: Int = var15 and 255;
            if (`byteA$iv` == (var15 and 255)) {
               `i$iv`++;
               continue;
            }

            var10000 = if (`byteA$iv` < var14) -1 else 1;
            break;
         }

         var10000 = if (`sizeA$iv` == `sizeB$iv`) 0 else (if (`sizeA$iv` < `sizeB$iv`) -1 else 1);
         break;
      }

      return var10000;
   }

   public override fun toString(): String {
      var var10000: java.lang.String;
      if (this.getData$okio().length == 0) {
         var10000 = "[size=0]";
      } else {
         val `i$iv`: Int = -ByteString.access$codePointIndexToCharIndex(this.getData$okio(), 64);
         if (`i$iv` == -1) {
            if (this.getData$okio().length <= 64) {
               var10000 = "[hex=${this.hex()}]";
            } else {
               val var15: StringBuilder = new StringBuilder().append("[size=").append(this.getData$okio().length).append(" hex=");
               val `endIndex$iv$iv`: Int = -SegmentedByteString.resolveDefaultParameter(this, 64);
               if (`endIndex$iv$iv` > this.getData$okio().length) {
                  throw new IllegalArgumentException(("endIndex > length(${this.getData$okio().length})").toString());
               }

               if (`endIndex$iv$iv` - 0 < 0) {
                  throw new IllegalArgumentException("endIndex < beginIndex".toString());
               }

               var10000 = var15.append(
                     (if (`endIndex$iv$iv` == this.getData$okio().length)
                           this
                           else
                           new ByteString(ArraysKt.copyOfRange(this.getData$okio(), 0, `endIndex$iv$iv`)))
                        .hex()
                  )
                  .append("…]")
                  .toString();
            }
         } else {
            val `text$iv`: java.lang.String = this.utf8();
            var10000 = `text$iv`.substring(0, `i$iv`);
            val var12: java.lang.String = StringsKt.replace$default(
               StringsKt.replace$default(StringsKt.replace$default(var10000, "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null),
               "\r",
               "\\r",
               false,
               4,
               null
            );
            var10000 = if (`i$iv` < `text$iv`.length()) "[size=${this.getData$okio().length} text=$var12…]" else "[text=$var12]";
         }
      }

      return var10000;
   }

   @Throws(java/io/IOException::class)
   private fun readObject(`in`: ObjectInputStream) {
      val byteString: ByteString = Companion.read(`in`, `in`.readInt());
      val field: Field = ByteString.class.getDeclaredField("data");
      field.setAccessible(true);
      field.set(this, byteString.data);
   }

   @Throws(java/io/IOException::class)
   private fun writeObject(out: ObjectOutputStream) {
      out.writeInt(this.data.length);
      out.write(this.data);
   }

   @Deprecated(message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = []), level = DeprecationLevel.ERROR)
   @JvmName(name = "-deprecated_getByte")
   public fun getByte(index: Int): Byte {
      return this.getByte(index);
   }

   @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = []), level = DeprecationLevel.ERROR)
   @JvmName(name = "-deprecated_size")
   public fun size(): Int {
      return this.size();
   }

   @JvmOverloads
   fun substring(beginIndex: Int): ByteString {
      return substring$default(this, beginIndex, 0, 2, null);
   }

   @JvmOverloads
   fun substring(): ByteString {
      return substring$default(this, 0, 0, 3, null);
   }

   @JvmOverloads
   fun indexOf(other: ByteString): Int {
      return indexOf$default(this, other, 0, 2, null);
   }

   @JvmOverloads
   fun indexOf(other: ByteArray): Int {
      return indexOf$default(this, other, 0, 2, null);
   }

   @JvmOverloads
   fun lastIndexOf(other: ByteString): Int {
      return lastIndexOf$default(this, other, 0, 2, null);
   }

   @JvmOverloads
   fun lastIndexOf(other: ByteArray): Int {
      return lastIndexOf$default(this, other, 0, 2, null);
   }

   @SourceDebugExtension(["SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/ByteString$Companion\n+ 2 ByteString.kt\nokio/internal/-ByteString\n+ 3 ByteStringNonJs.kt\nokio/internal/-ByteStringNonJs\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,364:1\n269#2:365\n273#2,3:366\n280#2,3:369\n287#2,2:372\n25#3:374\n27#3,7:376\n1#4:375\n1#4:383\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/ByteString$Companion\n*L\n234#1:365\n239#1:366,3\n251#1:369,3\n259#1:372,2\n262#1:374\n262#1:376,7\n262#1:375\n*E\n"])
   public companion object {
      private const val serialVersionUID: Long
      public final val EMPTY: ByteString

      public fun of(data: ByteArray): ByteString {
         val var10002: ByteArray = Arrays.copyOf(data, data.length);
         return new ByteString(var10002);
      }

      @JvmName(name = "of")
      public fun ByteArray.toByteString(offset: Int = ..., byteCount: Int = ...): ByteString {
         val `byteCount$iv`: Int = -SegmentedByteString.resolveDefaultParameter(`$this$toByteString`, byteCount);
         -SegmentedByteString.checkOffsetAndCount((long)`$this$toByteString`.length, (long)offset, (long)`byteCount$iv`);
         return new ByteString(ArraysKt.copyOfRange(`$this$toByteString`, offset, offset + `byteCount$iv`));
      }

      @JvmName(name = "of")
      public fun ByteBuffer.toByteString(): ByteString {
         val copy: ByteArray = new byte[`$this$toByteString`.remaining()];
         `$this$toByteString`.get(copy);
         return new ByteString(copy);
      }

      public fun String.encodeUtf8(): ByteString {
         val `byteString$iv`: ByteString = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(`$this$encodeUtf8`));
         `byteString$iv`.setUtf8$okio(`$this$encodeUtf8`);
         return `byteString$iv`;
      }

      @JvmName(name = "encodeString")
      public fun String.encode(charset: Charset = ...): ByteString {
         val var10002: ByteArray = `$this$encode`.getBytes(charset);
         return new ByteString(var10002);
      }

      public fun String.decodeBase64(): ByteString? {
         val `decoded$iv`: ByteArray = -Base64.decodeBase64ToArray(`$this$decodeBase64`);
         return if (`decoded$iv` != null) new ByteString(`decoded$iv`) else null;
      }

      public fun String.decodeHex(): ByteString {
         val `$this$commonDecodeHex$iv`: java.lang.String = `$this$decodeHex`;
         if (`$this$decodeHex`.length() % 2 != 0) {
            throw new IllegalArgumentException(("Unexpected hex string: $`$this$decodeHex`").toString());
         } else {
            val `result$iv`: ByteArray = new byte[`$this$decodeHex`.length() / 2];
            var `i$iv`: Int = 0;

            for (int var6 = result$iv.length; i$iv < var6; i$iv++) {
               `result$iv`[`i$iv`] = (byte)(
                  (-ByteStringNonJs.access$decodeHexDigit(`$this$commonDecodeHex$iv`.charAt(`i$iv` * 2)) shl 4)
                     + -ByteStringNonJs.access$decodeHexDigit(`$this$commonDecodeHex$iv`.charAt(`i$iv` * 2 + 1))
               );
            }

            return new ByteString(`result$iv`);
         }
      }

      @JvmName(name = "read")
      @Throws(java/io/IOException::class)
      public fun InputStream.readByteString(byteCount: Int): ByteString {
         if (byteCount < 0) {
            throw new IllegalArgumentException(("byteCount < 0: $byteCount").toString());
         } else {
            val result: ByteArray = new byte[byteCount];
            var offset: Int = 0;

            while (offset < byteCount) {
               val var8: Int = `$this$readByteString`.read(result, offset, byteCount - offset);
               if (var8 == -1) {
                  throw new EOFException();
               }

               offset += var8;
            }

            return new ByteString(result);
         }
      }

      @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.decodeBase64()", imports = ["okio.ByteString.Companion.decodeBase64"]), level = DeprecationLevel.ERROR)
      @JvmName(name = "-deprecated_decodeBase64")
      public fun decodeBase64(string: String): ByteString? {
         return this.decodeBase64(string);
      }

      @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.decodeHex()", imports = ["okio.ByteString.Companion.decodeHex"]), level = DeprecationLevel.ERROR)
      @JvmName(name = "-deprecated_decodeHex")
      public fun decodeHex(string: String): ByteString {
         return this.decodeHex(string);
      }

      @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.encode(charset)", imports = ["okio.ByteString.Companion.encode"]), level = DeprecationLevel.ERROR)
      @JvmName(name = "-deprecated_encodeString")
      public fun encodeString(string: String, charset: Charset): ByteString {
         return this.encodeString(string, charset);
      }

      @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.encodeUtf8()", imports = ["okio.ByteString.Companion.encodeUtf8"]), level = DeprecationLevel.ERROR)
      @JvmName(name = "-deprecated_encodeUtf8")
      public fun encodeUtf8(string: String): ByteString {
         return this.encodeUtf8(string);
      }

      @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "buffer.toByteString()", imports = ["okio.ByteString.Companion.toByteString"]), level = DeprecationLevel.ERROR)
      @JvmName(name = "-deprecated_of")
      public fun of(buffer: ByteBuffer): ByteString {
         return this.of(buffer);
      }

      @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "array.toByteString(offset, byteCount)", imports = ["okio.ByteString.Companion.toByteString"]), level = DeprecationLevel.ERROR)
      @JvmName(name = "-deprecated_of")
      public fun of(array: ByteArray, offset: Int, byteCount: Int): ByteString {
         return this.of(array, offset, byteCount);
      }

      @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "inputstream.readByteString(byteCount)", imports = ["okio.ByteString.Companion.readByteString"]), level = DeprecationLevel.ERROR)
      @JvmName(name = "-deprecated_read")
      public fun read(inputstream: InputStream, byteCount: Int): ByteString {
         return this.read(inputstream, byteCount);
      }
   }
}
