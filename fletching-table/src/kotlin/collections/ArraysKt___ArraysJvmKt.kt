package kotlin.collections

import java.math.BigDecimal
import java.math.BigInteger
import java.util.ArrayList
import java.util.Arrays
import java.util.Comparator
import java.util.SortedSet
import java.util.TreeSet
import kotlin.collections.ArraysKt___ArraysJvmKt.asList.1
import kotlin.collections.ArraysKt___ArraysJvmKt.asList.2
import kotlin.collections.ArraysKt___ArraysJvmKt.asList.3
import kotlin.collections.ArraysKt___ArraysJvmKt.asList.4
import kotlin.collections.ArraysKt___ArraysJvmKt.asList.5
import kotlin.collections.ArraysKt___ArraysJvmKt.asList.6
import kotlin.collections.ArraysKt___ArraysJvmKt.asList.7
import kotlin.collections.ArraysKt___ArraysJvmKt.asList.8
import kotlin.internal.InlineOnly
import kotlin.internal.LowPriorityInOverloadResolution
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\n_ArraysJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,3051:1\n14484#2,14:3052\n14514#2,14:3066\n14544#2,14:3080\n14574#2,14:3094\n14604#2,14:3108\n14634#2,14:3122\n14664#2,14:3136\n14694#2,14:3150\n14724#2,14:3164\n17456#2,14:3178\n17486#2,14:3192\n17516#2,14:3206\n17546#2,14:3220\n17576#2,14:3234\n17606#2,14:3248\n17636#2,14:3262\n17666#2,14:3276\n17696#2,14:3290\n*S KotlinDebug\n*F\n+ 1 _ArraysJvm.kt\nkotlin/collections/ArraysKt___ArraysJvmKt\n*L\n2443#1:3052,14\n2450#1:3066,14\n2457#1:3080,14\n2464#1:3094,14\n2471#1:3108,14\n2478#1:3122,14\n2485#1:3136,14\n2492#1:3150,14\n2499#1:3164,14\n2641#1:3178,14\n2648#1:3192,14\n2655#1:3206,14\n2662#1:3220,14\n2669#1:3234,14\n2676#1:3248,14\n2683#1:3262,14\n2690#1:3276,14\n2697#1:3290,14\n*E\n"])
internal class ArraysKt___ArraysJvmKt : ArraysKt__ArraysKt {
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.elementAt(index: Int): T {
      return (T)`$this$elementAt`[index];
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.elementAt(index: Int): Byte {
      return `$this$elementAt`[index];
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.elementAt(index: Int): Short {
      return `$this$elementAt`[index];
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.elementAt(index: Int): Int {
      return `$this$elementAt`[index];
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.elementAt(index: Int): Long {
      return `$this$elementAt`[index];
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.elementAt(index: Int): Float {
      return `$this$elementAt`[index];
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.elementAt(index: Int): Double {
      return `$this$elementAt`[index];
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.elementAt(index: Int): Boolean {
      return `$this$elementAt`[index];
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.elementAt(index: Int): Char {
      return `$this$elementAt`[index];
   }

   @JvmStatic
   public fun <R> Array<*>.filterIsInstance(klass: Class<R>): List<R> {
      return ArraysKt.filterIsInstanceTo(`$this$filterIsInstance`, new ArrayList(), klass) as MutableList<R>;
   }

   @JvmStatic
   public fun <C : MutableCollection<in R>, R> Array<*>.filterIsInstanceTo(destination: C, klass: Class<R>): C {
      for (Object element : $this$filterIsInstanceTo) {
         if (klass.isInstance(element)) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Array<out T>.asList(): List<T> {
      val var10000: java.util.List = ArraysUtilJVM.asList(`$this$asList`);
      return var10000;
   }

   @JvmStatic
   public fun ByteArray.asList(): List<Byte> {
      return new 1(`$this$asList`);
   }

   @JvmStatic
   public fun ShortArray.asList(): List<Short> {
      return new 2(`$this$asList`);
   }

   @JvmStatic
   public fun IntArray.asList(): List<Int> {
      return new 3(`$this$asList`);
   }

   @JvmStatic
   public fun LongArray.asList(): List<Long> {
      return new 4(`$this$asList`);
   }

   @JvmStatic
   public fun FloatArray.asList(): List<Float> {
      return new 5(`$this$asList`);
   }

   @JvmStatic
   public fun DoubleArray.asList(): List<Double> {
      return new 6(`$this$asList`);
   }

   @JvmStatic
   public fun BooleanArray.asList(): List<Boolean> {
      return new 7(`$this$asList`);
   }

   @JvmStatic
   public fun CharArray.asList(): List<Char> {
      return new 8(`$this$asList`);
   }

   @JvmStatic
   public fun <T> Array<out T>.binarySearch(element: T, comparator: Comparator<in T>, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element, comparator);
   }

   @JvmStatic
   public fun <T> Array<out T>.binarySearch(element: T, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun ByteArray.binarySearch(element: Byte, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun ShortArray.binarySearch(element: Short, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun IntArray.binarySearch(element: Int, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun LongArray.binarySearch(element: Long, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun FloatArray.binarySearch(element: Float, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun DoubleArray.binarySearch(element: Double, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun CharArray.binarySearch(element: Char, fromIndex: Int = 0, toIndex: Int = `$this$binarySearch`.length): Int {
      return Arrays.binarySearch(`$this$binarySearch`, fromIndex, toIndex, element);
   }

   @SinceKotlin(version = "1.1")
   @LowPriorityInOverloadResolution
   @JvmName(name = "contentDeepEqualsInline")
   @InlineOnly
   @JvmStatic
   public inline infix fun <T> Array<out T>.contentDeepEquals(other: Array<out T>): Boolean {
      return ArraysKt.contentDeepEquals(`$this$contentDeepEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "contentDeepEqualsNullable")
   @InlineOnly
   @JvmStatic
   public inline infix fun <T> Array<out T>?.contentDeepEquals(other: Array<out T>?): Boolean {
      return ArraysKt.contentDeepEquals(`$this$contentDeepEquals`, other);
   }

   @SinceKotlin(version = "1.1")
   @LowPriorityInOverloadResolution
   @JvmName(name = "contentDeepHashCodeInline")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.contentDeepHashCode(): Int {
      return ArraysKt.contentDeepHashCode(`$this$contentDeepHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "contentDeepHashCodeNullable")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>?.contentDeepHashCode(): Int {
      return ArraysKt.contentDeepHashCode(`$this$contentDeepHashCode`);
   }

   @SinceKotlin(version = "1.1")
   @LowPriorityInOverloadResolution
   @JvmName(name = "contentDeepToStringInline")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.contentDeepToString(): String {
      return ArraysKt.contentDeepToString(`$this$contentDeepToString`);
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "contentDeepToStringNullable")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>?.contentDeepToString(): String {
      return ArraysKt.contentDeepToString(`$this$contentDeepToString`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun <T> Array<out T>?.contentEquals(other: Array<out T>?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun ByteArray?.contentEquals(other: ByteArray?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun ShortArray?.contentEquals(other: ShortArray?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun IntArray?.contentEquals(other: IntArray?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun LongArray?.contentEquals(other: LongArray?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun FloatArray?.contentEquals(other: FloatArray?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun DoubleArray?.contentEquals(other: DoubleArray?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun BooleanArray?.contentEquals(other: BooleanArray?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline infix fun CharArray?.contentEquals(other: CharArray?): Boolean {
      return Arrays.equals(`$this$contentEquals`, other);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray?.contentHashCode(): Int {
      return Arrays.hashCode(`$this$contentHashCode`);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray?.contentToString(): String {
      val var10000: java.lang.String = Arrays.toString(`$this$contentToString`);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun <T> Array<out T>.copyInto(destination: Array<T>, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): Array<
         T
      > {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return (T[])destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun ByteArray.copyInto(destination: ByteArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): ByteArray {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun ShortArray.copyInto(destination: ShortArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): ShortArray {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun IntArray.copyInto(destination: IntArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): IntArray {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun LongArray.copyInto(destination: LongArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): LongArray {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun FloatArray.copyInto(destination: FloatArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): FloatArray {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun DoubleArray.copyInto(destination: DoubleArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): DoubleArray {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun BooleanArray.copyInto(destination: BooleanArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): BooleanArray {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return destination;
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun CharArray.copyInto(destination: CharArray, destinationOffset: Int = 0, startIndex: Int = 0, endIndex: Int = `$this$copyInto`.length): CharArray {
      System.arraycopy(`$this$copyInto`, startIndex, destination, destinationOffset, endIndex - startIndex);
      return destination;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<T>.copyOf(): Array<T> {
      val var10000: Array<Any> = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return (T[])var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.copyOf(): ByteArray {
      val var10000: ByteArray = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.copyOf(): ShortArray {
      val var10000: ShortArray = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.copyOf(): IntArray {
      val var10000: IntArray = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.copyOf(): LongArray {
      val var10000: LongArray = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.copyOf(): FloatArray {
      val var10000: FloatArray = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.copyOf(): DoubleArray {
      val var10000: DoubleArray = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.copyOf(): BooleanArray {
      val var10000: BooleanArray = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.copyOf(): CharArray {
      val var10000: CharArray = Arrays.copyOf(`$this$copyOf`, `$this$copyOf`.length);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.copyOf(newSize: Int): ByteArray {
      val var10000: ByteArray = Arrays.copyOf(`$this$copyOf`, newSize);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.copyOf(newSize: Int): ShortArray {
      val var10000: ShortArray = Arrays.copyOf(`$this$copyOf`, newSize);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.copyOf(newSize: Int): IntArray {
      val var10000: IntArray = Arrays.copyOf(`$this$copyOf`, newSize);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.copyOf(newSize: Int): LongArray {
      val var10000: LongArray = Arrays.copyOf(`$this$copyOf`, newSize);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.copyOf(newSize: Int): FloatArray {
      val var10000: FloatArray = Arrays.copyOf(`$this$copyOf`, newSize);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.copyOf(newSize: Int): DoubleArray {
      val var10000: DoubleArray = Arrays.copyOf(`$this$copyOf`, newSize);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.copyOf(newSize: Int): BooleanArray {
      val var10000: BooleanArray = Arrays.copyOf(`$this$copyOf`, newSize);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.copyOf(newSize: Int): CharArray {
      val var10000: CharArray = Arrays.copyOf(`$this$copyOf`, newSize);
      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<T>.copyOf(newSize: Int): Array<T?> {
      val var10000: Array<Any> = Arrays.copyOf(`$this$copyOf`, newSize);
      return (T[])var10000;
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<T>.copyOfRange(fromIndex: Int, toIndex: Int): Array<T> {
      return (T[])ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.copyOfRange(fromIndex: Int, toIndex: Int): ByteArray {
      return ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.copyOfRange(fromIndex: Int, toIndex: Int): ShortArray {
      return ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.copyOfRange(fromIndex: Int, toIndex: Int): IntArray {
      return ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.copyOfRange(fromIndex: Int, toIndex: Int): LongArray {
      return ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.copyOfRange(fromIndex: Int, toIndex: Int): FloatArray {
      return ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.copyOfRange(fromIndex: Int, toIndex: Int): DoubleArray {
      return ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.copyOfRange(fromIndex: Int, toIndex: Int): BooleanArray {
      return ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @JvmName(name = "copyOfRangeInline")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.copyOfRange(fromIndex: Int, toIndex: Int): CharArray {
      return ArraysKt.copyOfRange(`$this$copyOfRange`, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun <T> Array<T>.copyOfRangeImpl(fromIndex: Int, toIndex: Int): Array<T> {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: Array<Any> = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return (T[])var10000;
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun ByteArray.copyOfRangeImpl(fromIndex: Int, toIndex: Int): ByteArray {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: ByteArray = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun ShortArray.copyOfRangeImpl(fromIndex: Int, toIndex: Int): ShortArray {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: ShortArray = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun IntArray.copyOfRangeImpl(fromIndex: Int, toIndex: Int): IntArray {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: IntArray = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun LongArray.copyOfRangeImpl(fromIndex: Int, toIndex: Int): LongArray {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: LongArray = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun FloatArray.copyOfRangeImpl(fromIndex: Int, toIndex: Int): FloatArray {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: FloatArray = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun DoubleArray.copyOfRangeImpl(fromIndex: Int, toIndex: Int): DoubleArray {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: DoubleArray = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun BooleanArray.copyOfRangeImpl(fromIndex: Int, toIndex: Int): BooleanArray {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: BooleanArray = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "copyOfRange")
   @JvmStatic
   internal fun CharArray.copyOfRangeImpl(fromIndex: Int, toIndex: Int): CharArray {
      ArraysKt.copyOfRangeToIndexCheck(toIndex, `$this$copyOfRangeImpl`.length);
      val var10000: CharArray = Arrays.copyOfRange(`$this$copyOfRangeImpl`, fromIndex, toIndex);
      return var10000;
   }

   @JvmStatic
   public fun <T> Array<T>.fill(element: T, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun ByteArray.fill(element: Byte, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun ShortArray.fill(element: Short, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun IntArray.fill(element: Int, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun LongArray.fill(element: Long, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun FloatArray.fill(element: Float, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun DoubleArray.fill(element: Double, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun BooleanArray.fill(element: Boolean, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public fun CharArray.fill(element: Char, fromIndex: Int = 0, toIndex: Int = `$this$fill`.length) {
      Arrays.fill(`$this$fill`, fromIndex, toIndex, element);
   }

   @JvmStatic
   public operator fun <T> Array<T>.plus(element: T): Array<T> {
      val result: Array<Any> = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return (T[])result;
   }

   @JvmStatic
   public operator fun ByteArray.plus(element: Byte): ByteArray {
      val result: ByteArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return result;
   }

   @JvmStatic
   public operator fun ShortArray.plus(element: Short): ShortArray {
      val result: ShortArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return result;
   }

   @JvmStatic
   public operator fun IntArray.plus(element: Int): IntArray {
      val result: IntArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return result;
   }

   @JvmStatic
   public operator fun LongArray.plus(element: Long): LongArray {
      val result: LongArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return result;
   }

   @JvmStatic
   public operator fun FloatArray.plus(element: Float): FloatArray {
      val result: FloatArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return result;
   }

   @JvmStatic
   public operator fun DoubleArray.plus(element: Double): DoubleArray {
      val result: DoubleArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return result;
   }

   @JvmStatic
   public operator fun BooleanArray.plus(element: Boolean): BooleanArray {
      val result: BooleanArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return result;
   }

   @JvmStatic
   public operator fun CharArray.plus(element: Char): CharArray {
      val result: CharArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + 1);
      result[`$this$plus`.length] = element;
      return result;
   }

   @JvmStatic
   public operator fun <T> Array<T>.plus(elements: Collection<T>): Array<T> {
      var index: Int = `$this$plus`.length;
      val result: Array<Any> = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());

      for (Object element : elements) {
         result[index++] = element;
      }

      return (T[])result;
   }

   @JvmStatic
   public operator fun ByteArray.plus(elements: Collection<Byte>): ByteArray {
      var index: Int = `$this$plus`.length;
      val result: ByteArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as java.lang.Number).byteValue();
      }

      return result;
   }

   @JvmStatic
   public operator fun ShortArray.plus(elements: Collection<Short>): ShortArray {
      var index: Int = `$this$plus`.length;
      val result: ShortArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as java.lang.Number).shortValue();
      }

      return result;
   }

   @JvmStatic
   public operator fun IntArray.plus(elements: Collection<Int>): IntArray {
      var index: Int = `$this$plus`.length;
      val result: IntArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as java.lang.Number).intValue();
      }

      return result;
   }

   @JvmStatic
   public operator fun LongArray.plus(elements: Collection<Long>): LongArray {
      var index: Int = `$this$plus`.length;
      val result: LongArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as java.lang.Number).longValue();
      }

      return result;
   }

   @JvmStatic
   public operator fun FloatArray.plus(elements: Collection<Float>): FloatArray {
      var index: Int = `$this$plus`.length;
      val result: FloatArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as java.lang.Number).floatValue();
      }

      return result;
   }

   @JvmStatic
   public operator fun DoubleArray.plus(elements: Collection<Double>): DoubleArray {
      var index: Int = `$this$plus`.length;
      val result: DoubleArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as java.lang.Number).doubleValue();
      }

      return result;
   }

   @JvmStatic
   public operator fun BooleanArray.plus(elements: Collection<Boolean>): BooleanArray {
      var index: Int = `$this$plus`.length;
      val result: BooleanArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());

      for (boolean element : elements) {
         result[index++] = element;
      }

      return result;
   }

   @JvmStatic
   public operator fun CharArray.plus(elements: Collection<Char>): CharArray {
      var index: Int = `$this$plus`.length;
      val result: CharArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.size());

      for (char element : elements) {
         result[index++] = element;
      }

      return result;
   }

   @JvmStatic
   public operator fun <T> Array<T>.plus(elements: Array<out T>): Array<T> {
      val result: Array<Any> = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return (T[])result;
   }

   @JvmStatic
   public operator fun ByteArray.plus(elements: ByteArray): ByteArray {
      val result: ByteArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return result;
   }

   @JvmStatic
   public operator fun ShortArray.plus(elements: ShortArray): ShortArray {
      val result: ShortArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return result;
   }

   @JvmStatic
   public operator fun IntArray.plus(elements: IntArray): IntArray {
      val result: IntArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return result;
   }

   @JvmStatic
   public operator fun LongArray.plus(elements: LongArray): LongArray {
      val result: LongArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return result;
   }

   @JvmStatic
   public operator fun FloatArray.plus(elements: FloatArray): FloatArray {
      val result: FloatArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return result;
   }

   @JvmStatic
   public operator fun DoubleArray.plus(elements: DoubleArray): DoubleArray {
      val result: DoubleArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return result;
   }

   @JvmStatic
   public operator fun BooleanArray.plus(elements: BooleanArray): BooleanArray {
      val result: BooleanArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return result;
   }

   @JvmStatic
   public operator fun CharArray.plus(elements: CharArray): CharArray {
      val result: CharArray = Arrays.copyOf(`$this$plus`, `$this$plus`.length + elements.length);
      System.arraycopy(elements, 0, result, `$this$plus`.length, elements.length);
      return result;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<T>.plusElement(element: T): Array<T> {
      return (T[])ArraysKt.plus(`$this$plusElement`, element);
   }

   @JvmStatic
   public fun IntArray.sort() {
      if (`$this$sort`.length > 1) {
         Arrays.sort(`$this$sort`);
      }
   }

   @JvmStatic
   public fun LongArray.sort() {
      if (`$this$sort`.length > 1) {
         Arrays.sort(`$this$sort`);
      }
   }

   @JvmStatic
   public fun ByteArray.sort() {
      if (`$this$sort`.length > 1) {
         Arrays.sort(`$this$sort`);
      }
   }

   @JvmStatic
   public fun ShortArray.sort() {
      if (`$this$sort`.length > 1) {
         Arrays.sort(`$this$sort`);
      }
   }

   @JvmStatic
   public fun DoubleArray.sort() {
      if (`$this$sort`.length > 1) {
         Arrays.sort(`$this$sort`);
      }
   }

   @JvmStatic
   public fun FloatArray.sort() {
      if (`$this$sort`.length > 1) {
         Arrays.sort(`$this$sort`);
      }
   }

   @JvmStatic
   public fun CharArray.sort() {
      if (`$this$sort`.length > 1) {
         Arrays.sort(`$this$sort`);
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T : Comparable<T>> Array<out T>.sort() {
      ArraysKt.sort(`$this$sort`);
   }

   @JvmStatic
   public fun <T> Array<out T>.sort() {
      if (`$this$sort`.length > 1) {
         Arrays.sort(`$this$sort`);
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort((Object[])`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun ByteArray.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort(`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun ShortArray.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort(`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun IntArray.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort(`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun LongArray.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort(`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun FloatArray.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort(`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun DoubleArray.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort(`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun CharArray.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort(`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun <T> Array<out T>.sort(fromIndex: Int = 0, toIndex: Int = `$this$sort`.length) {
      Arrays.sort(`$this$sort`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun <T> Array<out T>.sortWith(comparator: Comparator<in T>) {
      if (`$this$sortWith`.length > 1) {
         Arrays.sort(`$this$sortWith`, comparator);
      }
   }

   @JvmStatic
   public fun <T> Array<out T>.sortWith(comparator: Comparator<in T>, fromIndex: Int = 0, toIndex: Int = `$this$sortWith`.length) {
      Arrays.sort(`$this$sortWith`, fromIndex, toIndex, comparator);
   }

   @JvmStatic
   public fun ByteArray.toTypedArray(): Array<Byte> {
      val result: Array<java.lang.Byte> = new java.lang.Byte[`$this$toTypedArray`.length];
      var index: Int = 0;

      for (int var3 = $this$toTypedArray.length; index < var3; index++) {
         result[index] = `$this$toTypedArray`[index];
      }

      return result;
   }

   @JvmStatic
   public fun ShortArray.toTypedArray(): Array<Short> {
      val result: Array<java.lang.Short> = new java.lang.Short[`$this$toTypedArray`.length];
      var index: Int = 0;

      for (int var3 = $this$toTypedArray.length; index < var3; index++) {
         result[index] = `$this$toTypedArray`[index];
      }

      return result;
   }

   @JvmStatic
   public fun IntArray.toTypedArray(): Array<Int> {
      val result: Array<Int> = new Integer[`$this$toTypedArray`.length];
      var index: Int = 0;

      for (int var3 = $this$toTypedArray.length; index < var3; index++) {
         result[index] = `$this$toTypedArray`[index];
      }

      return result;
   }

   @JvmStatic
   public fun LongArray.toTypedArray(): Array<Long> {
      val result: Array<java.lang.Long> = new java.lang.Long[`$this$toTypedArray`.length];
      var index: Int = 0;

      for (int var3 = $this$toTypedArray.length; index < var3; index++) {
         result[index] = `$this$toTypedArray`[index];
      }

      return result;
   }

   @JvmStatic
   public fun FloatArray.toTypedArray(): Array<Float> {
      val result: Array<java.lang.Float> = new java.lang.Float[`$this$toTypedArray`.length];
      var index: Int = 0;

      for (int var3 = $this$toTypedArray.length; index < var3; index++) {
         result[index] = `$this$toTypedArray`[index];
      }

      return result;
   }

   @JvmStatic
   public fun DoubleArray.toTypedArray(): Array<Double> {
      val result: Array<java.lang.Double> = new java.lang.Double[`$this$toTypedArray`.length];
      var index: Int = 0;

      for (int var3 = $this$toTypedArray.length; index < var3; index++) {
         result[index] = `$this$toTypedArray`[index];
      }

      return result;
   }

   @JvmStatic
   public fun BooleanArray.toTypedArray(): Array<Boolean> {
      val result: Array<java.lang.Boolean> = new java.lang.Boolean[`$this$toTypedArray`.length];
      var index: Int = 0;

      for (int var3 = $this$toTypedArray.length; index < var3; index++) {
         result[index] = `$this$toTypedArray`[index];
      }

      return result;
   }

   @JvmStatic
   public fun CharArray.toTypedArray(): Array<Char> {
      val result: Array<Character> = new Character[`$this$toTypedArray`.length];
      var index: Int = 0;

      for (int var3 = $this$toTypedArray.length; index < var3; index++) {
         result[index] = `$this$toTypedArray`[index];
      }

      return result;
   }

   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.toSortedSet(): SortedSet<T> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet()) as SortedSet<T>;
   }

   @JvmStatic
   public fun ByteArray.toSortedSet(): SortedSet<Byte> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet<>());
   }

   @JvmStatic
   public fun ShortArray.toSortedSet(): SortedSet<Short> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet<>());
   }

   @JvmStatic
   public fun IntArray.toSortedSet(): SortedSet<Int> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet<>());
   }

   @JvmStatic
   public fun LongArray.toSortedSet(): SortedSet<Long> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet<>());
   }

   @JvmStatic
   public fun FloatArray.toSortedSet(): SortedSet<Float> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet<>());
   }

   @JvmStatic
   public fun DoubleArray.toSortedSet(): SortedSet<Double> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet<>());
   }

   @JvmStatic
   public fun BooleanArray.toSortedSet(): SortedSet<Boolean> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet<>());
   }

   @JvmStatic
   public fun CharArray.toSortedSet(): SortedSet<Char> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet<>());
   }

   @JvmStatic
   public fun <T> Array<out T>.toSortedSet(comparator: Comparator<in T>): SortedSet<T> {
      return ArraysKt.toCollection(`$this$toSortedSet`, new TreeSet(comparator)) as SortedSet<T>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.sumOf(selector: (T) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (Object element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.sumOf(selector: (Byte) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (byte element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.sumOf(selector: (Short) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (short element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.sumOf(selector: (Int) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (int element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.sumOf(selector: (Long) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (long element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.sumOf(selector: (Float) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (float element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.sumOf(selector: (Double) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (double element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.sumOf(selector: (Boolean) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (boolean element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigDecimal")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.sumOf(selector: (Char) -> BigDecimal): BigDecimal {
      var var10000: BigDecimal = BigDecimal.valueOf(0L);
      var sum: BigDecimal = var10000;

      for (char element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigDecimal);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.sumOf(selector: (T) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (Object element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.sumOf(selector: (Byte) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (byte element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.sumOf(selector: (Short) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (short element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.sumOf(selector: (Int) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (int element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.sumOf(selector: (Long) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (long element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.sumOf(selector: (Float) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (float element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.sumOf(selector: (Double) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (double element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.sumOf(selector: (Boolean) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (boolean element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfBigInteger")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.sumOf(selector: (Char) -> BigInteger): BigInteger {
      var var10000: BigInteger = BigInteger.valueOf(0L);
      var sum: BigInteger = var10000;

      for (char element : $this$sumOf) {
         var10000 = sum.add(selector.invoke(element) as BigInteger);
         sum = var10000;
      }

      return sum;
   }

   open fun ArraysKt___ArraysJvmKt() {
   }
}
