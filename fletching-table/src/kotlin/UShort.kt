package kotlin

import kotlin.internal.InlineOnly
import kotlin.internal.IntrinsicConstEvaluation
import kotlin.jvm.internal.Intrinsics

@JvmInline
@SinceKotlin(version = "1.5")
public inline class UShort : java.lang.Comparable<UShort> {
   @PublishedApi
   internal final val data: Short

   @InlineOnly
   @JvmStatic
   public inline operator fun compareTo(other: UByte): Int {
      return Intrinsics.compare(var0 and 65535, var1 and 255);
   }

   @InlineOnly
   @JvmStatic
   public open inline operator fun compareTo(other: UShort): Int {
      return Intrinsics.compare(var0 and 65535, var1 and 65535);
   }

   @InlineOnly
   fun `compareTo-xj2QHRw`(other: Short): Int {
      return Intrinsics.compare(this.unbox-impl() and 65535, other and 65535);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun compareTo(other: UInt): Int {
      return Integer.compareUnsigned(UInt.constructor-impl(var0 and 65535), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun compareTo(other: ULong): Int {
      return java.lang.Long.compareUnsigned(ULong.constructor-impl((long)var0 and 65535L), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun plus(other: UByte): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) + UInt.constructor-impl(var1 and 255));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun plus(other: UShort): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) + UInt.constructor-impl(var1 and 65535));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun plus(other: UInt): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) + var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun plus(other: ULong): ULong {
      return ULong.constructor-impl(ULong.constructor-impl((long)var0 and 65535L) + var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun minus(other: UByte): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) - UInt.constructor-impl(var1 and 255));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun minus(other: UShort): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) - UInt.constructor-impl(var1 and 65535));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun minus(other: UInt): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) - var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun minus(other: ULong): ULong {
      return ULong.constructor-impl(ULong.constructor-impl((long)var0 and 65535L) - var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun times(other: UByte): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) * UInt.constructor-impl(var1 and 255));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun times(other: UShort): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) * UInt.constructor-impl(var1 and 65535));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun times(other: UInt): UInt {
      return UInt.constructor-impl(UInt.constructor-impl(var0 and 65535) * var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun times(other: ULong): ULong {
      return ULong.constructor-impl(ULong.constructor-impl((long)var0 and 65535L) * var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun div(other: UByte): UInt {
      return Integer.divideUnsigned(UInt.constructor-impl(var0 and 65535), UInt.constructor-impl(var1 and 255));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun div(other: UShort): UInt {
      return Integer.divideUnsigned(UInt.constructor-impl(var0 and 65535), UInt.constructor-impl(var1 and 65535));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun div(other: UInt): UInt {
      return Integer.divideUnsigned(UInt.constructor-impl(var0 and 65535), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun div(other: ULong): ULong {
      return java.lang.Long.divideUnsigned(ULong.constructor-impl((long)var0 and 65535L), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rem(other: UByte): UInt {
      return Integer.remainderUnsigned(UInt.constructor-impl(var0 and 65535), UInt.constructor-impl(var1 and 255));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rem(other: UShort): UInt {
      return Integer.remainderUnsigned(UInt.constructor-impl(var0 and 65535), UInt.constructor-impl(var1 and 65535));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rem(other: UInt): UInt {
      return Integer.remainderUnsigned(UInt.constructor-impl(var0 and 65535), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rem(other: ULong): ULong {
      return java.lang.Long.remainderUnsigned(ULong.constructor-impl((long)var0 and 65535L), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline fun floorDiv(other: UByte): UInt {
      return Integer.divideUnsigned(UInt.constructor-impl(var0 and 65535), UInt.constructor-impl(var1 and 255));
   }

   @InlineOnly
   @JvmStatic
   public inline fun floorDiv(other: UShort): UInt {
      return Integer.divideUnsigned(UInt.constructor-impl(var0 and 65535), UInt.constructor-impl(var1 and 65535));
   }

   @InlineOnly
   @JvmStatic
   public inline fun floorDiv(other: UInt): UInt {
      return Integer.divideUnsigned(UInt.constructor-impl(var0 and 65535), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline fun floorDiv(other: ULong): ULong {
      return java.lang.Long.divideUnsigned(ULong.constructor-impl((long)var0 and 65535L), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline fun mod(other: UByte): UByte {
      return UByte.constructor-impl((byte)Integer.remainderUnsigned(UInt.constructor-impl(var0 and '\uffff'), UInt.constructor-impl(var1 and 255)));
   }

   @InlineOnly
   @JvmStatic
   public inline fun mod(other: UShort): UShort {
      return constructor-impl((short)Integer.remainderUnsigned(UInt.constructor-impl(var0 and '\uffff'), UInt.constructor-impl(var1 and '\uffff')));
   }

   @InlineOnly
   @JvmStatic
   public inline fun mod(other: UInt): UInt {
      return Integer.remainderUnsigned(UInt.constructor-impl(var0 and 65535), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline fun mod(other: ULong): ULong {
      return java.lang.Long.remainderUnsigned(ULong.constructor-impl((long)var0 and 65535L), var1);
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun inc(): UShort {
      return constructor-impl((short)(var0 + 1));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun dec(): UShort {
      return constructor-impl((short)(var0 + -1));
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun rangeTo(other: UShort): UIntRange {
      return new UIntRange(UInt.constructor-impl(var0 and '\uffff'), UInt.constructor-impl(var1 and '\uffff'), null);
   }

   @SinceKotlin(version = "1.9")
   @WasExperimental(markerClass = [ExperimentalStdlibApi::class])
   @InlineOnly
   @JvmStatic
   public inline operator fun rangeUntil(other: UShort): UIntRange {
      return URangesKt.until-J1ME1BU(UInt.constructor-impl(var0 and '\uffff'), UInt.constructor-impl(var1 and '\uffff'));
   }

   @InlineOnly
   @JvmStatic
   public inline infix fun and(other: UShort): UShort {
      return constructor-impl((short)(var0 and var1));
   }

   @InlineOnly
   @JvmStatic
   public inline infix fun or(other: UShort): UShort {
      return constructor-impl((short)(var0 or var1));
   }

   @InlineOnly
   @JvmStatic
   public inline infix fun xor(other: UShort): UShort {
      return constructor-impl((short)(var0 xor var1));
   }

   @InlineOnly
   @JvmStatic
   public inline fun inv(): UShort {
      return constructor-impl((short)(var0.inv()));
   }

   @InlineOnly
   @JvmStatic
   public inline fun toByte(): Byte {
      return (byte)var0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toShort(): Short {
      return var0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toInt(): Int {
      return var0 and 65535;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toLong(): Long {
      return var0 and 65535L;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toUByte(): UByte {
      return UByte.constructor-impl((byte)var0);
   }

   @InlineOnly
   @JvmStatic
   public inline fun toUShort(): UShort {
      return var0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun toUInt(): UInt {
      return UInt.constructor-impl(var0 and 65535);
   }

   @InlineOnly
   @JvmStatic
   public inline fun toULong(): ULong {
      return ULong.constructor-impl((long)var0 and 65535L);
   }

   @InlineOnly
   @JvmStatic
   public inline fun toFloat(): Float {
      return (float)UnsignedKt.uintToDouble(var0 and '\uffff');
   }

   @InlineOnly
   @JvmStatic
   public inline fun toDouble(): Double {
      return UnsignedKt.uintToDouble(var0 and '\uffff');
   }

   @JvmStatic
   public open fun toString(): String {
      return java.lang.String.valueOf(var0 and '\uffff');
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.data);
   }

   @JvmStatic
   fun `hashCode-impl`(var0: Short): Int {
      return java.lang.Short.hashCode(var0);
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.data);
   }

   @JvmStatic
   fun `equals-impl`(var0: Short, other: Any): Boolean {
      if (other !is UShort) {
         return false;
      } else {
         return var0 == (other as UShort).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.data, other);
   }

   @IntrinsicConstEvaluation
   @PublishedApi
   @JvmStatic
   fun `constructor-impl`(data: Short): Short {
      return data;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Short, p2: Short): Boolean {
      return p1 == p2;
   }

   public companion object {
      public const val MIN_VALUE: UShort
      public const val MAX_VALUE: UShort
      public const val SIZE_BYTES: Int
      public const val SIZE_BITS: Int
   }
}
