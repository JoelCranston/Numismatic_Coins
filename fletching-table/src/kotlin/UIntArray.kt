package kotlin

import java.util.Arrays
import java.util.NoSuchElementException
import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMappedMarker

@JvmInline
@SinceKotlin(version = "1.3")
@ExperimentalUnsignedTypes
@SourceDebugExtension(["SMAP\nUIntArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UIntArray.kt\nkotlin/UIntArray\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,82:1\n1740#2,3:83\n*S KotlinDebug\n*F\n+ 1 UIntArray.kt\nkotlin/UIntArray\n*L\n58#1:83,3\n*E\n"])
public inline class UIntArray : java.util.Collection<UInt>, KMappedMarker {
   @PublishedApi
   internal final val storage: IntArray

   public open val size: Int
      public open get() {
         return var0.length;
      }


   @JvmStatic
   fun `constructor-impl`(size: Int): IntArray {
      return constructor-impl(new int[size]);
   }

   @JvmStatic
   public operator fun get(index: Int): UInt {
      return UInt.constructor-impl(var0[index]);
   }

   @JvmStatic
   public operator fun set(index: Int, value: UInt) {
      var0[index] = var2;
   }

   fun getSize(): Int {
      return getSize-impl(this.storage);
   }

   @JvmStatic
   public open operator fun iterator(): kotlin.collections.Iterator<UInt> {
      return new UIntArray.Iterator(var0);
   }

   override fun iterator(): MutableIterator<UInt> {
      return iterator-impl(this.storage);
   }

   @JvmStatic
   public open operator fun contains(element: UInt): Boolean {
      return ArraysKt.contains(var0, var1);
   }

   fun `contains-WZ4Q5Ns`(element: Int): Boolean {
      return contains-WZ4Q5Ns(this.storage, element);
   }

   @JvmStatic
   public open fun containsAll(elements: Collection<UInt>): Boolean {
      val `$this$all$iv`: java.lang.Iterable = elements;
      var var10000: Boolean;
      if ((elements as java.util.Collection).isEmpty()) {
         var10000 = true;
      } else {
         val var4: java.util.Iterator = `$this$all$iv`.iterator();

         while (true) {
            if (!var4.hasNext()) {
               var10000 = true;
               break;
            }

            val `element$iv`: Any = var4.next();
            if (`element$iv` !is UInt || !ArraysKt.contains(var0, (`element$iv` as UInt).unbox-impl())) {
               var10000 = false;
               break;
            }
         }
      }

      return var10000;
   }

   override fun containsAll(elements: MutableCollection<*>): Boolean {
      return containsAll-impl(this.storage, elements);
   }

   @JvmStatic
   public open fun isEmpty(): Boolean {
      return var0.length == 0;
   }

   override fun isEmpty(): Boolean {
      return isEmpty-impl(this.storage);
   }

   @JvmStatic
   fun `toString-impl`(var0: IntArray): java.lang.String {
      return "UIntArray(storage=${Arrays.toString(var0)})";
   }

   public override fun toString(): String {
      return toString-impl(this.storage);
   }

   @JvmStatic
   fun `hashCode-impl`(var0: IntArray): Int {
      return Arrays.hashCode(var0);
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.storage);
   }

   @JvmStatic
   fun `equals-impl`(var0: IntArray, other: Any): Boolean {
      if (other !is UIntArray) {
         return false;
      } else {
         return var0 == (other as UIntArray).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.storage, other);
   }

   fun `add-WZ4Q5Ns`(var1: Int): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun remove(element: Any): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun addAll(elements: MutableCollection<UInt>): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun removeAll(elements: MutableCollection<*>): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun retainAll(elements: MutableCollection<*>): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @PublishedApi
   @JvmStatic
   fun `constructor-impl`(storage: IntArray): IntArray {
      return storage;
   }

   @JvmStatic
   fun `equals-impl0`(p1: IntArray, p2: IntArray): Boolean {
      return p1 == p2;
   }

   override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this as MutableCollection<*>, array);
   }

   override fun toArray(): Array<Any> {
      return CollectionToArray.toArray(this as MutableCollection<*>);
   }

   private class Iterator(array: IntArray) : java.util.Iterator<UInt>, KMappedMarker {
      private final val array: IntArray
      private final var index: Int

      init {
         this.array = array;
      }

      public override operator fun hasNext(): Boolean {
         return this.index < this.array.length;
      }

      public open operator fun next(): UInt {
         if (this.index < this.array.length) {
            return UInt.constructor-impl(this.array[this.index++]);
         } else {
            throw new NoSuchElementException(java.lang.String.valueOf(this.index));
         }
      }

      override fun remove() {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }
}
