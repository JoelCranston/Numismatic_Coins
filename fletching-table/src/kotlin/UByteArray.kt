package kotlin

import java.util.Arrays
import java.util.NoSuchElementException
import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMappedMarker

@JvmInline
@SinceKotlin(version = "1.3")
@ExperimentalUnsignedTypes
@SourceDebugExtension(["SMAP\nUByteArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UByteArray.kt\nkotlin/UByteArray\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,82:1\n1740#2,3:83\n*S KotlinDebug\n*F\n+ 1 UByteArray.kt\nkotlin/UByteArray\n*L\n58#1:83,3\n*E\n"])
public inline class UByteArray : java.util.Collection<UByte>, KMappedMarker {
   @PublishedApi
   internal final val storage: ByteArray

   public open val size: Int
      public open get() {
         return var0.length;
      }


   @JvmStatic
   fun `constructor-impl`(size: Int): ByteArray {
      return constructor-impl(new byte[size]);
   }

   @JvmStatic
   public operator fun get(index: Int): UByte {
      return UByte.constructor-impl(var0[index]);
   }

   @JvmStatic
   public operator fun set(index: Int, value: UByte) {
      var0[index] = var2;
   }

   fun getSize(): Int {
      return getSize-impl(this.storage);
   }

   @JvmStatic
   public open operator fun iterator(): kotlin.collections.Iterator<UByte> {
      return new UByteArray.Iterator(var0);
   }

   override fun iterator(): MutableIterator<UByte> {
      return iterator-impl(this.storage);
   }

   @JvmStatic
   public open operator fun contains(element: UByte): Boolean {
      return ArraysKt.contains(var0, var1);
   }

   fun `contains-7apg3OU`(element: Byte): Boolean {
      return contains-7apg3OU(this.storage, element);
   }

   @JvmStatic
   public open fun containsAll(elements: Collection<UByte>): Boolean {
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
            if (`element$iv` !is UByte || !ArraysKt.contains(var0, (`element$iv` as UByte).unbox-impl())) {
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
   fun `toString-impl`(var0: ByteArray): java.lang.String {
      return "UByteArray(storage=${Arrays.toString(var0)})";
   }

   public override fun toString(): String {
      return toString-impl(this.storage);
   }

   @JvmStatic
   fun `hashCode-impl`(var0: ByteArray): Int {
      return Arrays.hashCode(var0);
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.storage);
   }

   @JvmStatic
   fun `equals-impl`(var0: ByteArray, other: Any): Boolean {
      if (other !is UByteArray) {
         return false;
      } else {
         return var0 == (other as UByteArray).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.storage, other);
   }

   fun `add-7apg3OU`(var1: Byte): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun remove(element: Any): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun addAll(elements: MutableCollection<UByte>): Boolean {
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
   fun `constructor-impl`(storage: ByteArray): ByteArray {
      return storage;
   }

   @JvmStatic
   fun `equals-impl0`(p1: ByteArray, p2: ByteArray): Boolean {
      return p1 == p2;
   }

   override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this as MutableCollection<*>, array);
   }

   override fun toArray(): Array<Any> {
      return CollectionToArray.toArray(this as MutableCollection<*>);
   }

   private class Iterator(array: ByteArray) : java.util.Iterator<UByte>, KMappedMarker {
      private final val array: ByteArray
      private final var index: Int

      init {
         this.array = array;
      }

      public override operator fun hasNext(): Boolean {
         return this.index < this.array.length;
      }

      public open operator fun next(): UByte {
         if (this.index < this.array.length) {
            return UByte.constructor-impl(this.array[this.index++]);
         } else {
            throw new NoSuchElementException(java.lang.String.valueOf(this.index));
         }
      }

      override fun remove() {
         throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
   }
}
