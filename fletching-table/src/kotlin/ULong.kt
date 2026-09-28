package kotlin

import kotlin.internal.InlineOnly
import kotlin.internal.IntrinsicConstEvaluation

@JvmInline
@SinceKotlin(version = "1.5")
public inline class ULong : java.lang.Comparable<ULong> {
   @PublishedApi
   internal final val data: Long

   @InlineOnly
   @JvmStatic
   public inline operator fun compareTo(other: UByte): Int {
      return java.lang.Long.compareUnsigned(var0, constructor-impl((long)var2 and 255L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun compareTo(other: UShort): Int {
      return java.lang.Long.compareUnsigned(var0, constructor-impl((long)var2 and 65535L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun compareTo(other: UInt): Int {
      return java.lang.Long.compareUnsigned(var0, constructor-impl((long)var2 and 4294967295L));
   }

   @InlineOnly
   @JvmStatic
   public open inline operator fun compareTo(other: ULong): Int {
      return UnsignedKt.ulongCompare(var0, var2);
   }

   @InlineOnly
   fun `compareTo-VKZWuLQ`(other: Long): Int {
      return UnsignedKt.ulongCompare(this.unbox-impl(), other);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun plus(other: UByte): ULong {
      return constructor-impl(var0 + constructor-impl((long)var2 and 255L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun plus(other: UShort): ULong {
      return constructor-impl(var0 + constructor-impl((long)var2 and 65535L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun plus(other: UInt): ULong {
      return constructor-impl(var0 + constructor-impl((long)var2 and 4294967295L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun plus(other: ULong): ULong {
      return constructor-impl(var0 + var2);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun minus(other: UByte): ULong {
      return constructor-impl(var0 - constructor-impl((long)var2 and 255L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun minus(other: UShort): ULong {
      return constructor-impl(var0 - constructor-impl((long)var2 and 65535L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun minus(other: UInt): ULong {
      return constructor-impl(var0 - constructor-impl((long)var2 and 4294967295L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun minus(other: ULong): ULong {
      return constructor-impl(var0 - var2);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun times(other: UByte): ULong {
      return constructor-impl(var0 * constructor-impl((long)var2 and 255L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun times(other: UShort): ULong {
      return constructor-impl(var0 * constructor-impl((long)var2 and 65535L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun times(other: UInt): ULong {
      return constructor-impl(var0 * constructor-impl((long)var2 and 4294967295L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun times(other: ULong): ULong {
      return constructor-impl(var0 * var2);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun div(other: UByte): ULong {
      return java.lang.Long.divideUnsigned(var0, constructor-impl((long)var2 and 255L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun div(other: UShort): ULong {
      return java.lang.Long.divideUnsigned(var0, constructor-impl((long)var2 and 65535L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun div(other: UInt): ULong {
      return java.lang.Long.divideUnsigned(var0, constructor-impl((long)var2 and 4294967295L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun div(other: ULong): ULong {
      return UnsignedKt.ulongDivide-eb3DHEI(var0, var2);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rem(other: UByte): ULong {
      return java.lang.Long.remainderUnsigned(var0, constructor-impl((long)var2 and 255L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rem(other: UShort): ULong {
      return java.lang.Long.remainderUnsigned(var0, constructor-impl((long)var2 and 65535L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rem(other: UInt): ULong {
      return java.lang.Long.remainderUnsigned(var0, constructor-impl((long)var2 and 4294967295L));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rem(other: ULong): ULong {
      return UnsignedKt.ulongRemainder-eb3DHEI(var0, var2);
   }

   @InlineOnly
   @JvmStatic
   public inline fun floorDiv(other: UByte): ULong {
      return java.lang.Long.divideUnsigned(var0, constructor-impl((long)var2 and 255L));
   }

   @InlineOnly
   @JvmStatic
   public inline fun floorDiv(other: UShort): ULong {
      return java.lang.Long.divideUnsigned(var0, constructor-impl((long)var2 and 65535L));
   }

   @InlineOnly
   @JvmStatic
   public inline fun floorDiv(other: UInt): ULong {
      return java.lang.Long.divideUnsigned(var0, constructor-impl((long)var2 and 4294967295L));
   }

   @InlineOnly
   @JvmStatic
   public inline fun floorDiv(other: ULong): ULong {
      return java.lang.Long.divideUnsigned(var0, var2);
   }

   @InlineOnly
   @JvmStatic
   public inline fun mod(other: UByte): UByte {
      return UByte.constructor-impl((byte)((int)java.lang.Long.remainderUnsigned(var0, constructor-impl((long)var2 and 255L))));
   }

   @InlineOnly
   @JvmStatic
   public inline fun mod(other: UShort): UShort {
      return UShort.constructor-impl((short)((int)java.lang.Long.remainderUnsigned(var0, constructor-impl((long)var2 and 65535L))));
   }

   @InlineOnly
   @JvmStatic
   public inline fun mod(other: UInt): UInt {
      return UInt.constructor-impl((int)java.lang.Long.remainderUnsigned(var0, constructor-impl((long)var2 and 4294967295L)));
   }

   @InlineOnly
   @JvmStatic
   public inline fun mod(other: ULong): ULong {
      return java.lang.Long.remainderUnsigned(var0, var2);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun inc(): ULong {
      return constructor-impl(var0 + 1L);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun dec(): ULong {
      return constructor-impl(var0 + -1L);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rangeTo(other: ULong): ULongRange {
      return new ULongRange(var0, var2, null);
   }

   @SinceKotlin(version = "1.9")
   @WasExperimental(markerClass = [ExperimentalStdlibApi::class])
   @InlineOnly
   @JvmStatic
   public inline operator fun rangeUntil(other: ULong): ULongRange {
      return URangesKt.until-eb3DHEI(var0, var2);
   }

   @InlineOnly
   @JvmStatic
   public inline infix fun shl(bitCount: Int): ULong {
      return constructor-impl(var0 shl bitCount);
   }

   @InlineOnly
   @JvmStatic
   public inline infix fun shr(bitCount: Int): ULong {
      return constructor-impl(var0 ushr bitCount);
   }

   @InlineOnly
   @JvmStatic
   public inline infix fun and(other: ULong): ULong {
      return constructor-impl(var0 and var2);
   }

   @InlineOnly
   @JvmStatic
   public inline infix fun or(other: ULong): ULong {
      return constructor-impl(var0 or var2);
   }

   @InlineOnly
   @JvmStatic
   public inline infix fun xor(other: ULong): ULong {
      return constructor-impl(var0 xor var2);
   }

   @InlineOnly
   @JvmStatic
   public inline fun inv(): ULong {
      return constructor-impl(var0.inv());
   }

   @InlineOnly
   @JvmStatic
   public inline fun toByte(): Byte {
      return (byte)var0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toShort(): Short {
      return (short)var0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toInt(): Int {
      return (int)var0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toLong(): Long {
      return var0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toUByte(): UByte {
      return UByte.constructor-impl((byte)((int)var0));
   }

   @InlineOnly
   @JvmStatic
   public inline fun toUShort(): UShort {
      return UShort.constructor-impl((short)((int)var0));
   }

   @InlineOnly
   @JvmStatic
   public inline fun toUInt(): UInt {
      return UInt.constructor-impl((int)var0);
   }

   @InlineOnly
   @JvmStatic
   public inline fun toULong(): ULong {
      return var0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toFloat(): Float {
      return (float)UnsignedKt.ulongToDouble(var0);
   }

   @InlineOnly
   @JvmStatic
   public inline fun toDouble(): Double {
      return UnsignedKt.ulongToDouble(var0);
   }

   @JvmStatic
   public open fun toString(): String {
      return UnsignedKt.ulongToString(var0, 10);
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.data);
   }

   @JvmStatic
   fun `hashCode-impl`(var0: Long): Int {
      return java.lang.Long.hashCode(var0);
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.data);
   }

   @JvmStatic
   fun `equals-impl`(var0: Long, other: Any): Boolean {
      if (other !is ULong) {
         return false;
      } else {
         return var0 == (other as ULong).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.data, other);
   }

   @IntrinsicConstEvaluation
   @PublishedApi
   @JvmStatic
   fun `constructor-impl`(data: Long): Long {
      return data;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Long, p2: Long): Boolean {
      return p1 == p2;
   }

   public companion object {
      public const val MIN_VALUE: ULong
      public const val MAX_VALUE: ULong
      public const val SIZE_BYTES: Int
      public const val SIZE_BITS: Int
   }
}
