package kotlinx.io.bytestring

import java.util.Arrays
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nkotlinx/io/bytestring/ByteString\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,550:1\n1#2:551\n*E\n"])
public class ByteString private constructor(data: ByteArray, dummy: Any?) : java.lang.Comparable<ByteString> {
   private final val data: ByteArray
   private final var hashCode: Int

   public final val size: Int
      public final get() {
         return this.data.length;
      }


   init {
      this.data = data;
   }

   public constructor(data: ByteArray, startIndex: Int = 0, endIndex: Int = data.length) : this(ArraysKt.copyOfRange(data, startIndex, endIndex), null)
   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other == null || this.getClass() != other.getClass()) {
         return false;
      } else if ((other as ByteString).data.length != this.data.length) {
         return false;
      } else {
         return ((other as ByteString).hashCode == 0 || this.hashCode == 0 || (other as ByteString).hashCode == this.hashCode)
            && Arrays.equals(this.data, (other as ByteString).data);
      }
   }

   public override fun hashCode(): Int {
      var hc: Int = this.hashCode;
      if (this.hashCode == 0) {
         hc = Arrays.hashCode(this.data);
         this.hashCode = hc;
      }

      return hc;
   }

   public operator fun get(index: Int): Byte {
      if (index >= 0 && index < this.getSize()) {
         return this.data[index];
      } else {
         throw new IndexOutOfBoundsException("index ($index) is out of byte string bounds: [0..${this.getSize()})");
      }
   }

   public fun toByteArray(startIndex: Int = 0, endIndex: Int = this.getSize()): ByteArray {
      if (startIndex > endIndex) {
         throw new IllegalArgumentException(("startIndex ($startIndex) > endIndex ($endIndex)").toString());
      } else {
         return ArraysKt.copyOfRange(this.data, startIndex, endIndex);
      }
   }

   public fun copyInto(destination: ByteArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = this.getSize()) {
      if (startIndex > endIndex) {
         throw new IllegalArgumentException(("startIndex ($startIndex) > endIndex ($endIndex)").toString());
      } else {
         ArraysKt.copyInto(this.data, destination, destinationOffset, startIndex, endIndex);
      }
   }

   public fun substring(startIndex: Int, endIndex: Int = this.getSize()): ByteString {
      return if (startIndex == endIndex) EMPTY else new ByteString(this.data, startIndex, endIndex);
   }

   public open operator fun compareTo(other: ByteString): Int {
      if (other === this) {
         return 0;
      } else {
         val localData: ByteArray = this.data;
         val otherData: ByteArray = other.data;
         var i: Int = 0;

         for (int var5 = Math.min(this.getSize(), other.getSize()); i < var5; i++) {
            val cmp: Int = Intrinsics.compare(UByte.constructor-impl(localData[i]) and 255, UByte.constructor-impl(otherData[i]) and 255);
            if (cmp != 0) {
               return cmp;
            }
         }

         return Intrinsics.compare(this.getSize(), other.getSize());
      }
   }

   public override fun toString(): String {
      if (ByteStringKt.isEmpty(this)) {
         return "ByteString(size=0)";
      } else {
         val sizeStr: java.lang.String = java.lang.String.valueOf(this.getSize());
         val `$this$toString_u24lambda_u242`: StringBuilder = new StringBuilder(22 + sizeStr.length() + this.getSize() * 2);
         `$this$toString_u24lambda_u242`.append("ByteString(size=");
         `$this$toString_u24lambda_u242`.append(sizeStr);
         `$this$toString_u24lambda_u242`.append(" hex=");
         val localData: ByteArray = this.data;
         var i: Int = 0;

         for (int var7 = this.getSize(); i < var7; i++) {
            val b: Int = localData[i];
            `$this$toString_u24lambda_u242`.append(HEX_DIGITS[localData[i] ushr 4 and 15]);
            `$this$toString_u24lambda_u242`.append(HEX_DIGITS[b and 15]);
         }

         val var10000: java.lang.String = `$this$toString_u24lambda_u242`.append(')').toString();
         return var10000;
      }
   }

   @PublishedApi
   internal fun getBackingArrayReference(): ByteArray {
      return this.data;
   }

   @JvmStatic
   fun {
      val var10000: CharArray = "0123456789abcdef".toCharArray();
      HEX_DIGITS = var10000;
   }

   public companion object {
      internal final val EMPTY: ByteString
      private final val HEX_DIGITS: CharArray

      internal fun wrap(byteArray: ByteArray): ByteString {
         return new ByteString(byteArray, null, null);
      }
   }
}
