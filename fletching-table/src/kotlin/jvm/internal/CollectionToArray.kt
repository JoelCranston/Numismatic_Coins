@file:JvmName(name = "CollectionToArray")

@file:SourceDebugExtension(["SMAP\nCollectionToArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionToArray.kt\nkotlin/jvm/internal/CollectionToArray\n*L\n1#1,88:1\n63#1,22:89\n63#1,22:111\n*S KotlinDebug\n*F\n+ 1 CollectionToArray.kt\nkotlin/jvm/internal/CollectionToArray\n*L\n22#1:89,22\n37#1:111,22\n*E\n"])

package kotlin.jvm.internal

import java.util.Arrays

private final val EMPTY: Array<Any?>
private const val MAX_SIZE: Int = 2147483645
private Object[] EMPTY = new Object[0];

@Deprecated(message = "This function will be made internal in a future release")
@DeprecatedSinceKotlin(warningSince = "1.9", errorSince = "2.1")
@JvmName(name = "toArray")
public fun collectionToArray(collection: Collection<*>): Array<Any?> {
   val `size$iv`: Int = collection.size();
   var var10000: Array<Any>;
   if (`size$iv` == 0) {
      var10000 = EMPTY;
   } else {
      val var11: java.util.Iterator = collection.iterator();
      if (!var11.hasNext()) {
         var10000 = EMPTY;
      } else {
         var var12: Array<Any> = new Object[`size$iv`];
         var var13: Int = 0;

         while (true) {
            var12[var13++] = var11.next();
            if (var13 >= var12.length) {
               if (!var11.hasNext()) {
                  var10000 = var12;
                  break;
               }

               var `newSize$iv`: Int = var13 * 3 + 1 ushr 1;
               if (var13 * 3 + 1 ushr 1 <= var13) {
                  if (var13 >= 2147483645) {
                     throw new OutOfMemoryError();
                  }

                  `newSize$iv` = 2147483645;
               }

               var10000 = Arrays.copyOf(var12, `newSize$iv`);
               var12 = var10000;
            } else if (!var11.hasNext()) {
               var10000 = Arrays.copyOf(var12, var13);
               break;
            }
         }
      }
   }

   return var10000;
}

@Deprecated(message = "This function will be made internal in a future release")
@DeprecatedSinceKotlin(warningSince = "1.9", errorSince = "2.1")
@JvmName(name = "toArray")
public fun collectionToArray(collection: Collection<*>, a: Array<Any?>?): Array<Any?> {
   if (a == null) {
      throw new NullPointerException();
   } else {
      val `size$iv`: Int = collection.size();
      var var10000: Array<Any>;
      if (`size$iv` == 0) {
         if (a.length > 0) {
            a[0] = null;
         }

         var10000 = a;
      } else {
         val `iter$iv`: java.util.Iterator = collection.iterator();
         if (!`iter$iv`.hasNext()) {
            if (a.length > 0) {
               a[0] = null;
            }

            var10000 = a;
         } else {
            if (`size$iv` <= a.length) {
               var10000 = a;
            } else {
               var10000 = (Object[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), `size$iv`);
               var10000 = var10000;
            }

            var `result$iv`: Array<Any> = var10000;
            var var13: Int = 0;

            while (true) {
               `result$iv`[var13++] = `iter$iv`.next();
               if (var13 >= `result$iv`.length) {
                  if (!`iter$iv`.hasNext()) {
                     var10000 = `result$iv`;
                     break;
                  }

                  var var14: Int = var13 * 3 + 1 ushr 1;
                  if (var13 * 3 + 1 ushr 1 <= var13) {
                     if (var13 >= 2147483645) {
                        throw new OutOfMemoryError();
                     }

                     var14 = 2147483645;
                  }

                  var10000 = Arrays.copyOf(`result$iv`, var14);
                  `result$iv` = var10000;
               } else if (!`iter$iv`.hasNext()) {
                  if (`result$iv` === a) {
                     a[var13] = null;
                     var10000 = a;
                  } else {
                     var10000 = Arrays.copyOf(`result$iv`, var13);
                  }
                  break;
               }
            }
         }
      }

      return var10000;
   }
}

private inline fun toArrayImpl(collection: Collection<*>, empty: () -> Array<Any?>, alloc: (Int) -> Array<Any?>, trim: (Array<Any?>, Int) -> Array<Any?>): Array<
      Any?
   > {
   val size: Int = collection.size();
   if (size == 0) {
      return empty.invoke() as Array<Any>;
   } else {
      val iter: java.util.Iterator = collection.iterator();
      if (!iter.hasNext()) {
         return empty.invoke() as Array<Any>;
      } else {
         var result: Array<Any> = alloc.invoke(size) as Array<Any>;
         var i: Int = 0;

         while (true) {
            result[i++] = iter.next();
            if (i >= result.length) {
               if (!iter.hasNext()) {
                  return result;
               }

               var newSize: Int = i * 3 + 1 ushr 1;
               if (i * 3 + 1 ushr 1 <= i) {
                  if (i >= 2147483645) {
                     throw new OutOfMemoryError();
                  }

                  newSize = 2147483645;
               }

               val var10000: Array<Any> = Arrays.copyOf(result, newSize);
               result = var10000;
            } else if (!iter.hasNext()) {
               return trim.invoke(result, i) as Array<Any>;
            }
         }
      }
   }
}
