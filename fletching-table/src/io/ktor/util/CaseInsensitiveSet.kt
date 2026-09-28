package io.ktor.util

import io.ktor.utils.io.InternalAPI
import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.markers.KMutableSet

@InternalAPI
public class CaseInsensitiveSet : java.util.Set<java.lang.String>, KMutableSet {
   private final val backingMap: CaseInsensitiveMap<Boolean> = new CaseInsensitiveMap()

   public open val size: Int
      public open get() {
         return this.backingMap.size();
      }


   public constructor(initial: Iterable<String>) : this() {
      kotlin.collections.CollectionsKt.addAll(this as MutableCollection<java.lang.String>, initial);
   }

   public open fun add(element: String): Boolean {
      if (this.backingMap.containsKey(element)) {
         return false;
      } else {
         (this.backingMap as java.util.Map).put(element, true);
         return true;
      }
   }

   public open fun remove(element: String): Boolean {
      return this.backingMap.remove((Object)element) == true;
   }

   public override fun addAll(elements: Collection<String>): Boolean {
      var added: Boolean = false;

      for (java.lang.String element : elements) {
         if (this.add(element)) {
            added = true;
         }
      }

      return added;
   }

   public override fun clear() {
      this.backingMap.clear();
   }

   public override fun removeAll(elements: Collection<String>): Boolean {
      return this.backingMap.keySet().removeAll(kotlin.collections.CollectionsKt.toSet(elements));
   }

   public override fun retainAll(elements: Collection<String>): Boolean {
      return this.backingMap.keySet().retainAll(kotlin.collections.CollectionsKt.toSet(elements));
   }

   public open operator fun contains(element: String): Boolean {
      return this.backingMap.containsKey(element);
   }

   public override fun containsAll(elements: Collection<String>): Boolean {
      return this.backingMap.keySet().containsAll(elements);
   }

   public override fun isEmpty(): Boolean {
      return this.backingMap.isEmpty();
   }

   public override operator fun iterator(): MutableIterator<String> {
      return this.backingMap.keySet().iterator();
   }

   override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this as MutableCollection<*>, array);
   }

   override fun toArray(): Array<Any> {
      return CollectionToArray.toArray(this as MutableCollection<*>);
   }
}
