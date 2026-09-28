package kotlin.uuid

import java.io.InvalidObjectException
import java.io.ObjectInputStream
import java.io.Serializable
import java.util.Comparator
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly

@SinceKotlin(version = "2.0")
@ExperimentalUuidApi
public class Uuid private constructor(mostSignificantBits: Long, leastSignificantBits: Long) : java.lang.Comparable<Uuid>, Serializable {
   @PublishedApi
   internal final val mostSignificantBits: Long

   @PublishedApi
   internal final val leastSignificantBits: Long

   init {
      this.mostSignificantBits = mostSignificantBits;
      this.leastSignificantBits = leastSignificantBits;
   }

   @InlineOnly
   public inline fun <T> toLongs(action: (Long, Long) -> T): T {
      contract {
         callsInPlace(action, InvocationKind.EXACTLY_ONCE)
      }

      return (T)action.invoke(this.getMostSignificantBits(), this.getLeastSignificantBits());
   }

   @InlineOnly
   public inline fun <T> toULongs(action: (ULong, ULong) -> T): T {
      contract {
         callsInPlace(action, InvocationKind.EXACTLY_ONCE)
      }

      return (T)action.invoke(
         ULong.box-impl(ULong.constructor-impl(this.getMostSignificantBits())), ULong.box-impl(ULong.constructor-impl(this.getLeastSignificantBits()))
      );
   }

   public override fun toString(): String {
      return this.toHexDashString();
   }

   @SinceKotlin(version = "2.1")
   public fun toHexDashString(): String {
      val bytes: ByteArray = new byte[36];
      UuidKt.formatBytesInto(this.mostSignificantBits, bytes, 0, 0, 4);
      bytes[8] = 45;
      UuidKt.formatBytesInto(this.mostSignificantBits, bytes, 9, 4, 6);
      bytes[13] = 45;
      UuidKt.formatBytesInto(this.mostSignificantBits, bytes, 14, 6, 8);
      bytes[18] = 45;
      UuidKt.formatBytesInto(this.leastSignificantBits, bytes, 19, 0, 2);
      bytes[23] = 45;
      UuidKt.formatBytesInto(this.leastSignificantBits, bytes, 24, 2, 8);
      return StringsKt.decodeToString(bytes);
   }

   public fun toHexString(): String {
      val bytes: ByteArray = new byte[32];
      UuidKt.formatBytesInto(this.mostSignificantBits, bytes, 0, 0, 8);
      UuidKt.formatBytesInto(this.leastSignificantBits, bytes, 16, 0, 8);
      return StringsKt.decodeToString(bytes);
   }

   public fun toByteArray(): ByteArray {
      val bytes: ByteArray = new byte[16];
      UuidKt.setLongAt(bytes, 0, this.mostSignificantBits);
      UuidKt.setLongAt(bytes, 8, this.leastSignificantBits);
      return bytes;
   }

   @SinceKotlin(version = "2.1")
   @ExperimentalUnsignedTypes
   public fun toUByteArray(): UByteArray {
      return UByteArray.constructor-impl(this.toByteArray());
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is Uuid) {
         return false;
      } else {
         return this.mostSignificantBits == (other as Uuid).mostSignificantBits && this.leastSignificantBits == (other as Uuid).leastSignificantBits;
      }
   }

   @SinceKotlin(version = "2.1")
   public open operator fun compareTo(other: Uuid): Int {
      return if (this.mostSignificantBits != other.mostSignificantBits)
         java.lang.Long.compareUnsigned(ULong.constructor-impl(this.mostSignificantBits), ULong.constructor-impl(other.mostSignificantBits))
         else
         java.lang.Long.compareUnsigned(ULong.constructor-impl(this.leastSignificantBits), ULong.constructor-impl(other.leastSignificantBits));
   }

   public override fun hashCode(): Int {
      return java.lang.Long.hashCode(this.mostSignificantBits xor this.leastSignificantBits);
   }

   private fun writeReplace(): Any {
      return UuidKt.serializedUuid(this);
   }

   private fun readObject(input: ObjectInputStream) {
      throw new InvalidObjectException("Deserialization is supported via proxy only");
   }

   public companion object {
      public final val NIL: Uuid
      public const val SIZE_BYTES: Int
      public const val SIZE_BITS: Int

      @Deprecated(
         message = "Use naturalOrder<Uuid>() instead",
         replaceWith = @ReplaceWith(
            expression = "naturalOrder<Uuid>()",
            imports = {"kotlin.comparisons.naturalOrder"}
         )
      )
      @DeprecatedSinceKotlin(
         warningSince = "2.1"
      )
      public final val LEXICAL_ORDER: Comparator<Uuid>
         public final get() {
            return ComparisonsKt.naturalOrder();
         }


      public fun fromLongs(mostSignificantBits: Long, leastSignificantBits: Long): Uuid {
         return if (mostSignificantBits == 0L && leastSignificantBits == 0L) this.getNIL() else new Uuid(mostSignificantBits, leastSignificantBits, null);
      }

      public fun fromULongs(mostSignificantBits: ULong, leastSignificantBits: ULong): Uuid {
         return this.fromLongs(var1, var3);
      }

      public fun fromByteArray(byteArray: ByteArray): Uuid {
         if (byteArray.length != 16) {
            throw new IllegalArgumentException(
               ("Expected exactly 16 bytes, but was ${UuidKt__UuidKt.access$truncateForErrorMessage(byteArray, 32)} of size ${byteArray.length}").toString()
            );
         } else {
            return this.fromLongs(UuidKt.getLongAt(byteArray, 0), UuidKt.getLongAt(byteArray, 8));
         }
      }

      @SinceKotlin(version = "2.1")
      @ExperimentalUnsignedTypes
      public fun fromUByteArray(ubyteArray: UByteArray): Uuid {
         return this.fromByteArray(var1);
      }

      public fun parse(uuidString: String): Uuid {
         var var10000: Uuid;
         switch (uuidString.length()) {
            case 32:
               var10000 = UuidKt.uuidParseHex(uuidString);
               break;
            case 36:
               var10000 = UuidKt.uuidParseHexDash(uuidString);
               break;
            default:
               throw new IllegalArgumentException(
                  "Expected either a 36-char string in the standard hex-and-dash UUID format or a 32-char hexadecimal string, but was \"${UuidKt__UuidKt.access$truncateForErrorMessage(
                     uuidString, 64
                  )}\" of length ${uuidString.length()}"
               );
         }

         return var10000;
      }

      @SinceKotlin(version = "2.1")
      public fun parseHexDash(hexDashString: String): Uuid {
         if (hexDashString.length() != 36) {
            throw new IllegalArgumentException(
               ("Expected a 36-char string in the standard hex-and-dash UUID format, but was \"${UuidKt__UuidKt.access$truncateForErrorMessage(
                     hexDashString, 64
                  )}\" of length ${hexDashString.length()}")
                  .toString()
            );
         } else {
            return UuidKt.uuidParseHexDash(hexDashString);
         }
      }

      public fun parseHex(hexString: String): Uuid {
         if (hexString.length() != 32) {
            throw new IllegalArgumentException(
               ("Expected a 32-char hexadecimal string, but was \"${UuidKt__UuidKt.access$truncateForErrorMessage(hexString, 64)}\" of length ${hexString.length()}")
                  .toString()
            );
         } else {
            return UuidKt.uuidParseHex(hexString);
         }
      }

      public fun random(): Uuid {
         return UuidKt.secureRandomUuid();
      }
   }
}
