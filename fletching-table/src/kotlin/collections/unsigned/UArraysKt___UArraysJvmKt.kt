package kotlin.collections.unsigned

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.collections.unsigned.UArraysKt___UArraysJvmKt.asList.1
import kotlin.collections.unsigned.UArraysKt___UArraysJvmKt.asList.2
import kotlin.collections.unsigned.UArraysKt___UArraysJvmKt.asList.3
import kotlin.collections.unsigned.UArraysKt___UArraysJvmKt.asList.4
import kotlin.internal.InlineOnly

internal class UArraysKt___UArraysJvmKt {
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.elementAt(index: Int): UInt {
      return UIntArray.get-pVg5ArA(var0, index);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.elementAt(index: Int): ULong {
      return ULongArray.get-s-VKNKU(var0, index);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.elementAt(index: Int): UByte {
      return UByteArray.get-w2LRezQ(var0, index);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.elementAt(index: Int): UShort {
      return UShortArray.get-Mh2AYeg(var0, index);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.asList(): List<UInt> {
      return new 1(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.asList(): List<ULong> {
      return new 2(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.asList(): List<UByte> {
      return new 3(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.asList(): List<UShort> {
      return new 4(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.binarySearch(element: UInt, fromIndex: Int = ..., toIndex: Int = ...): Int {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UIntArray.getSize-impl(var0));
      val signedElement: Int = var1;
      var low: Int = fromIndex;
      var high: Int = toIndex - 1;

      while (low <= high) {
         val mid: Int = low + high ushr 1;
         val cmp: Int = UnsignedKt.uintCompare(var0[low + high ushr 1], signedElement);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.binarySearch(element: ULong, fromIndex: Int = ..., toIndex: Int = ...): Int {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, ULongArray.getSize-impl(var0));
      val signedElement: Long = var1;
      var low: Int = fromIndex;
      var high: Int = toIndex - 1;

      while (low <= high) {
         val mid: Int = low + high ushr 1;
         val cmp: Int = UnsignedKt.ulongCompare(var0[low + high ushr 1], signedElement);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.binarySearch(element: UByte, fromIndex: Int = ..., toIndex: Int = ...): Int {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UByteArray.getSize-impl(var0));
      val signedElement: Int = var1 and 255;
      var low: Int = fromIndex;
      var high: Int = toIndex - 1;

      while (low <= high) {
         val mid: Int = low + high ushr 1;
         val cmp: Int = UnsignedKt.uintCompare(var0[low + high ushr 1], signedElement);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.binarySearch(element: UShort, fromIndex: Int = ..., toIndex: Int = ...): Int {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UShortArray.getSize-impl(var0));
      val signedElement: Int = var1 and '\uffff';
      var low: Int = fromIndex;
      var high: Int = toIndex - 1;

      while (low <= high) {
         val mid: Int = low + high ushr 1;
         val cmp: Int = UnsignedKt.uintCompare(var0[low + high ushr 1], signedElement);
         if (cmp < 0) {
            low = mid + 1;
         } else {
            if (cmp <= 0) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -(low + 1);
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumOf(selector: (UInt) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumOf$0); var3 < var4; var3++) {
         var10000 = sum.add(selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3))) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumOf(selector: (ULong) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumOf$0); var3 < var4; var3++) {
         var10000 = sum.add(selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3))) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumOf(selector: (UByte) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumOf$0); var3 < var4; var3++) {
         var10000 = sum.add(selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3))) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumOf(selector: (UShort) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumOf$0); var3 < var4; var3++) {
         var10000 = sum.add(selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3))) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumOf(selector: (UInt) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumOf$0); var3 < var4; var3++) {
         var10000 = sum.add(selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3))) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumOf(selector: (ULong) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumOf$0); var3 < var4; var3++) {
         var10000 = sum.add(selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3))) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumOf(selector: (UByte) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumOf$0); var3 < var4; var3++) {
         var10000 = sum.add(selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3))) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumOf(selector: (UShort) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumOf$0); var3 < var4; var3++) {
         var10000 = sum.add(selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3))) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   open fun UArraysKt___UArraysJvmKt() {
   }
}
