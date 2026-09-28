package kotlin.collections

import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMappedMarker

@SinceKotlin(version = "1.1")
@SourceDebugExtension(["SMAP\nAbstractCollection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractCollection.kt\nkotlin/collections/AbstractCollection\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,50:1\n1761#2,3:51\n1740#2,3:54\n*S KotlinDebug\n*F\n+ 1 AbstractCollection.kt\nkotlin/collections/AbstractCollection\n*L\n19#1:51,3\n22#1:54,3\n*E\n"])
public abstract class AbstractCollection<E> : java.util.Collection<E>, KMappedMarker {
   public abstract val size: Int

   open fun AbstractCollection() {
   }

   public abstract override operator fun iterator(): Iterator<Any> {
   }

   public override operator fun contains(element: Any): Boolean {
      val `$this$any$iv`: java.lang.Iterable = this;
      var var10000: Boolean;
      if (this is java.util.Collection && this.isEmpty()) {
         var10000 = false;
      } else {
         val var4: java.util.Iterator = `$this$any$iv`.iterator();

         while (true) {
            if (!var4.hasNext()) {
               var10000 = false;
               break;
            }

            if (var4.next() == element) {
               var10000 = true;
               break;
            }
         }
      }

      return var10000;
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

   public override fun isEmpty(): Boolean {
      return this.size() == 0;
   }

   public override fun toString(): String {
      return CollectionsKt.joinToString$default(this, ", ", "[", "]", 0, null, AbstractCollection::toString$lambda$0, 24, null);
   }

   protected override fun toArray(): Array<Any?> {
      return CollectionToArray.toArray(this);
   }

   protected override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this, array);
   }

   override fun add(element: E): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun remove(element: Any): Boolean {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   override fun addAll(elements: MutableCollection<E>): Boolean {
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

   @JvmStatic
   fun `toString$lambda$0`(`this$0`: AbstractCollection, it: Any): java.lang.CharSequence {
      return if (it === `this$0`) "(this Collection)" else java.lang.String.valueOf(it);
   }
}
