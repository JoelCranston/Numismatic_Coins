package kotlin.collections

import java.util.ArrayList
import java.util.Arrays
import kotlin.collections.unsigned.UArraysKt
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nArrays.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Arrays.kt\nkotlin/collections/ArraysKt__ArraysKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,165:1\n1#2:166\n*E\n"])
internal class ArraysKt__ArraysKt : ArraysKt__ArraysJVMKt {
   @JvmStatic
   public fun <T> Array<out Array<out T>>.flatten(): List<T> {
      val var2: Array<Any> = `$this$flatten` as Array<Any>;
      var var3: Int = 0;

      for (Object var6 : var2) {
         var3 += (var6 as Array<Any>).length;
      }

      val result: ArrayList = new ArrayList(var3);

      for (Object[] element : (Object[])$this$flatten) {
         CollectionsKt.addAll(result, var14);
      }

      return result;
   }

   @JvmStatic
   public fun <T, R> Array<out Pair<T, R>>.unzip(): Pair<List<T>, List<R>> {
      val listT: ArrayList = new ArrayList(`$this$unzip`.length);
      val listR: ArrayList = new ArrayList(`$this$unzip`.length);

      for (Pair pair : $this$unzip) {
         listT.add(pair.getFirst());
         listR.add(pair.getSecond());
      }

      return TuplesKt.to(listT, listR);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun Array<*>?.isNullOrEmpty(): Boolean {
      contract {
         returns(false) implies (this != null)
      }

      return `$this$isNullOrEmpty` == null || `$this$isNullOrEmpty`.length == 0;
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun <C, R> C.ifEmpty(defaultValue: () -> R): R where C : Array<*>, C : R {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return (R)(if (`$this$ifEmpty`.length == 0) defaultValue.invoke() else `$this$ifEmpty`);
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "contentDeepEquals")
   @JvmStatic
   internal fun <T> Array<out T>?.contentDeepEqualsImpl(other: Array<out T>?): Boolean {
      if (`$this$contentDeepEqualsImpl` === other) {
         return true;
      } else if (`$this$contentDeepEqualsImpl` != null && other != null && `$this$contentDeepEqualsImpl`.length == other.length) {
         var i: Int = 0;

         for (int var3 = $this$contentDeepEqualsImpl.length; i < var3; i++) {
            val v1: Any = `$this$contentDeepEqualsImpl`[i];
            val v2: Any = other[i];
            if (v1 != other[i]) {
               if (v1 == null || v2 == null) {
                  return false;
               }

               if (v1 is Array<Any> && v2 is Array<Any>) {
                  if (!ArraysKt.contentDeepEquals(v1 as Array<Any>, v2 as Array<Any>)) {
                     return false;
                  }
               } else if (v1 is ByteArray && v2 is ByteArray) {
                  if (!Arrays.equals(v1 as ByteArray, v2 as ByteArray)) {
                     return false;
                  }
               } else if (v1 is ShortArray && v2 is ShortArray) {
                  if (!Arrays.equals(v1 as ShortArray, v2 as ShortArray)) {
                     return false;
                  }
               } else if (v1 is IntArray && v2 is IntArray) {
                  if (!Arrays.equals(v1 as IntArray, v2 as IntArray)) {
                     return false;
                  }
               } else if (v1 is LongArray && v2 is LongArray) {
                  if (!Arrays.equals(v1 as LongArray, v2 as LongArray)) {
                     return false;
                  }
               } else if (v1 is FloatArray && v2 is FloatArray) {
                  if (!Arrays.equals(v1 as FloatArray, v2 as FloatArray)) {
                     return false;
                  }
               } else if (v1 is DoubleArray && v2 is DoubleArray) {
                  if (!Arrays.equals(v1 as DoubleArray, v2 as DoubleArray)) {
                     return false;
                  }
               } else if (v1 is CharArray && v2 is CharArray) {
                  if (!Arrays.equals(v1 as CharArray, v2 as CharArray)) {
                     return false;
                  }
               } else if (v1 is BooleanArray && v2 is BooleanArray) {
                  if (!Arrays.equals(v1 as BooleanArray, v2 as BooleanArray)) {
                     return false;
                  }
               } else if (v1 is UByteArray && v2 is UByteArray) {
                  if (!UArraysKt.contentEquals-kV0jMPg((v1 as UByteArray).unbox-impl(), (v2 as UByteArray).unbox-impl())) {
                     return false;
                  }
               } else if (v1 is UShortArray && v2 is UShortArray) {
                  if (!UArraysKt.contentEquals-FGO6Aew((v1 as UShortArray).unbox-impl(), (v2 as UShortArray).unbox-impl())) {
                     return false;
                  }
               } else if (v1 is UIntArray && v2 is UIntArray) {
                  if (!UArraysKt.contentEquals-KJPZfPQ((v1 as UIntArray).unbox-impl(), (v2 as UIntArray).unbox-impl())) {
                     return false;
                  }
               } else if (v1 is ULongArray && v2 is ULongArray) {
                  if (!UArraysKt.contentEquals-lec5QzE((v1 as ULongArray).unbox-impl(), (v2 as ULongArray).unbox-impl())) {
                     return false;
                  }
               } else if (!(v1 == v2)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   @SinceKotlin(version = "1.3")
   @PublishedApi
   @JvmName(name = "contentDeepToString")
   @JvmStatic
   internal fun <T> Array<out T>?.contentDeepToStringImpl(): String {
      if (`$this$contentDeepToStringImpl` == null) {
         return "null";
      } else {
         val var2: StringBuilder = new StringBuilder(RangesKt.coerceAtMost(`$this$contentDeepToStringImpl`.length, 429496729) * 5 + 2);
         contentDeepToStringInternal$ArraysKt__ArraysKt(`$this$contentDeepToStringImpl`, var2, new ArrayList<>());
         return var2.toString();
      }
   }

   @JvmStatic
   private fun <T> Array<out T>.contentDeepToStringInternal(result: StringBuilder, processed: MutableList<Array<*>>) {
      if (processed.contains(`$this$contentDeepToStringInternal`)) {
         result.append("[...]");
      } else {
         processed.add(`$this$contentDeepToStringInternal`);
         result.append('[');
         var i: Int = 0;

         for (int var4 = $this$contentDeepToStringInternal.length; i < var4; i++) {
            if (i != 0) {
               result.append(", ");
            }

            val element: Any = `$this$contentDeepToStringInternal`[i];
            if (`$this$contentDeepToStringInternal`[i] == null) {
               result.append("null");
            } else if (element is Array<Any>) {
               contentDeepToStringInternal$ArraysKt__ArraysKt(element as Array<Any>, result, processed);
            } else if (element is ByteArray) {
               val var10001: java.lang.String = Arrays.toString(element as ByteArray);
               result.append(var10001);
            } else if (element is ShortArray) {
               val var7: java.lang.String = Arrays.toString(element as ShortArray);
               result.append(var7);
            } else if (element is IntArray) {
               val var8: java.lang.String = Arrays.toString(element as IntArray);
               result.append(var8);
            } else if (element is LongArray) {
               val var9: java.lang.String = Arrays.toString(element as LongArray);
               result.append(var9);
            } else if (element is FloatArray) {
               val var10: java.lang.String = Arrays.toString(element as FloatArray);
               result.append(var10);
            } else if (element is DoubleArray) {
               val var11: java.lang.String = Arrays.toString(element as DoubleArray);
               result.append(var11);
            } else if (element is CharArray) {
               val var12: java.lang.String = Arrays.toString(element as CharArray);
               result.append(var12);
            } else if (element is BooleanArray) {
               val var13: java.lang.String = Arrays.toString(element as BooleanArray);
               result.append(var13);
            } else if (element is UByteArray) {
               result.append(UArraysKt.contentToString-2csIQuQ(if (element as UByteArray != null) (element as UByteArray).unbox-impl() else null));
            } else if (element is UShortArray) {
               result.append(UArraysKt.contentToString-d-6D3K8(if (element as UShortArray != null) (element as UShortArray).unbox-impl() else null));
            } else if (element is UIntArray) {
               result.append(UArraysKt.contentToString-XUkPCBk(if (element as UIntArray != null) (element as UIntArray).unbox-impl() else null));
            } else if (element is ULongArray) {
               result.append(UArraysKt.contentToString-uLth9ew(if (element as ULongArray != null) (element as ULongArray).unbox-impl() else null));
            } else {
               result.append(element.toString());
            }
         }

         result.append(']');
         processed.remove(CollectionsKt.getLastIndex(processed));
      }
   }

   open fun ArraysKt__ArraysKt() {
   }
}
