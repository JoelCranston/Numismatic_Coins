package kotlin.comparisons

import kotlin.internal.InlineOnly
import kotlin.jvm.internal.Intrinsics

internal class UComparisonsKt___UComparisonsKt {
   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun maxOf(a: UInt, b: UInt): UInt {
      return if (Integer.compareUnsigned(var0, var1) >= 0) var0 else var1;
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun maxOf(a: ULong, b: ULong): ULong {
      return if (java.lang.Long.compareUnsigned(var0, var2) >= 0) var0 else var2;
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun maxOf(a: UByte, b: UByte): UByte {
      return if (Intrinsics.compare(var0 and 255, var1 and 255) >= 0) var0 else var1;
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun maxOf(a: UShort, b: UShort): UShort {
      return if (Intrinsics.compare(var0 and '\uffff', var1 and '\uffff') >= 0) var0 else var1;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun maxOf(a: UInt, b: UInt, c: UInt): UInt {
      return UComparisonsKt.maxOf-J1ME1BU(var0, UComparisonsKt.maxOf-J1ME1BU(var1, var2));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun maxOf(a: ULong, b: ULong, c: ULong): ULong {
      return UComparisonsKt.maxOf-eb3DHEI(var0, UComparisonsKt.maxOf-eb3DHEI(var2, var4));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun maxOf(a: UByte, b: UByte, c: UByte): UByte {
      return UComparisonsKt.maxOf-Kr8caGY(var0, UComparisonsKt.maxOf-Kr8caGY(var1, var2));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun maxOf(a: UShort, b: UShort, c: UShort): UShort {
      return UComparisonsKt.maxOf-5PvTz6A(var0, UComparisonsKt.maxOf-5PvTz6A(var1, var2));
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun maxOf(a: UInt, other: UIntArray): UInt {
      var max: Int = var0;
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-other$0); var3 < var4; var3++) {
         max = UComparisonsKt.maxOf-J1ME1BU(max, UIntArray.get-pVg5ArA(var1, var3));
      }

      return max;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun maxOf(a: ULong, other: ULongArray): ULong {
      var max: Long = var0;
      var var5: Int = 0;

      for (int var6 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-other$0); var5 < var6; var5++) {
         max = UComparisonsKt.maxOf-eb3DHEI(max, ULongArray.get-s-VKNKU(var2, var5));
      }

      return max;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun maxOf(a: UByte, other: UByteArray): UByte {
      var max: Byte = var0;
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-other$0); var3 < var4; var3++) {
         max = UComparisonsKt.maxOf-Kr8caGY(max, UByteArray.get-w2LRezQ(var1, var3));
      }

      return max;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun maxOf(a: UShort, other: UShortArray): UShort {
      var max: Short = var0;
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-other$0); var3 < var4; var3++) {
         max = UComparisonsKt.maxOf-5PvTz6A(max, UShortArray.get-Mh2AYeg(var1, var3));
      }

      return max;
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun minOf(a: UInt, b: UInt): UInt {
      return if (Integer.compareUnsigned(var0, var1) <= 0) var0 else var1;
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun minOf(a: ULong, b: ULong): ULong {
      return if (java.lang.Long.compareUnsigned(var0, var2) <= 0) var0 else var2;
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun minOf(a: UByte, b: UByte): UByte {
      return if (Intrinsics.compare(var0 and 255, var1 and 255) <= 0) var0 else var1;
   }

   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun minOf(a: UShort, b: UShort): UShort {
      return if (Intrinsics.compare(var0 and '\uffff', var1 and '\uffff') <= 0) var0 else var1;
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun minOf(a: UInt, b: UInt, c: UInt): UInt {
      return UComparisonsKt.minOf-J1ME1BU(var0, UComparisonsKt.minOf-J1ME1BU(var1, var2));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun minOf(a: ULong, b: ULong, c: ULong): ULong {
      return UComparisonsKt.minOf-eb3DHEI(var0, UComparisonsKt.minOf-eb3DHEI(var2, var4));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun minOf(a: UByte, b: UByte, c: UByte): UByte {
      return UComparisonsKt.minOf-Kr8caGY(var0, UComparisonsKt.minOf-Kr8caGY(var1, var2));
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun minOf(a: UShort, b: UShort, c: UShort): UShort {
      return UComparisonsKt.minOf-5PvTz6A(var0, UComparisonsKt.minOf-5PvTz6A(var1, var2));
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun minOf(a: UInt, other: UIntArray): UInt {
      var min: Int = var0;
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-other$0); var3 < var4; var3++) {
         min = UComparisonsKt.minOf-J1ME1BU(min, UIntArray.get-pVg5ArA(var1, var3));
      }

      return min;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun minOf(a: ULong, other: ULongArray): ULong {
      var min: Long = var0;
      var var5: Int = 0;

      for (int var6 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-other$0); var5 < var6; var5++) {
         min = UComparisonsKt.minOf-eb3DHEI(min, ULongArray.get-s-VKNKU(var2, var5));
      }

      return min;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun minOf(a: UByte, other: UByteArray): UByte {
      var min: Byte = var0;
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-other$0); var3 < var4; var3++) {
         min = UComparisonsKt.minOf-Kr8caGY(min, UByteArray.get-w2LRezQ(var1, var3));
      }

      return min;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun minOf(a: UShort, other: UShortArray): UShort {
      var min: Short = var0;
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-other$0); var3 < var4; var3++) {
         min = UComparisonsKt.minOf-5PvTz6A(min, UShortArray.get-Mh2AYeg(var1, var3));
      }

      return min;
   }

   open fun UComparisonsKt___UComparisonsKt() {
   }
}
