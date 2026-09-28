package kotlin.collections

import kotlin.jvm.internal.ArrayIteratorKt
import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMappedMarker

@SourceDebugExtension(["SMAP\nCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collections.kt\nkotlin/collections/ArrayAsCollection\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,527:1\n1740#2,3:528\n*S KotlinDebug\n*F\n+ 1 Collections.kt\nkotlin/collections/ArrayAsCollection\n*L\n65#1:528,3\n*E\n"])
private class ArrayAsCollection<T>(vararg values: Any, isVarargs: Boolean) : java.util.Collection<T>, KMappedMarker {
   public final val values: Array<out Any>
   public final val isVarargs: Boolean

   public open val size: Int
      public open get() {
         return this.values.length;
      }


   init {
      this.values = (T[])values;
      this.isVarargs = isVarargs;
   }

   public override fun isEmpty(): Boolean {
      return this.values.length == 0;
   }

   public override operator fun contains(element: Any): Boolean {
      return ArraysKt.contains(this.values, element);
   }

   public override fun containsAll(elements: Collection<Any>): Boolean {
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

            if (!this.contains(var4.next())) {
               var10000 = false;
               break;
            }
         }
      }

      return var10000;
   }

   public override operator fun iterator(): Iterator<Any> {
      return ArrayIteratorKt.iterator(this.values);
   }

   public override fun toArray(): Array<out Any?> {
      return CollectionsKt.copyToArrayOfAny(this.values, this.isVarargs);
   }

   override fun add(element: T): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun remove(element: Any): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun addAll(elements: MutableCollection<T>): Boolean {
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

   override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this, array);
   }
}
