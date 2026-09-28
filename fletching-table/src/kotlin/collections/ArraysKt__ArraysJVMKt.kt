package kotlin.collections

import java.nio.charset.Charset
import java.util.Arrays
import kotlin.internal.InlineOnly

internal class ArraysKt__ArraysJVMKt {
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.toString(charset: Charset): String {
      return new java.lang.String(`$this$toString`, charset);
   }

   @JvmStatic
   internal fun <T> arrayOfNulls(reference: Array<T>, size: Int): Array<T> {
      val var10000: Any = java.lang.reflect.Array.newInstance(reference.getClass().getComponentType(), size);
      return (T[])(var10000 as Array<Any>);
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   internal fun copyOfRangeToIndexCheck(toIndex: Int, size: Int) {
      if (toIndex > size) {
         throw new IndexOutOfBoundsException("toIndex ($toIndex) is greater than size ($size).");
      }
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "contentDeepHashCode")
   @JvmStatic
   internal fun <T> Array<out T>?.contentDeepHashCodeImpl(): Int {
      return Arrays.deepHashCode(`$this$contentDeepHashCodeImpl`);
   }

   open fun ArraysKt__ArraysJVMKt() {
   }
}
