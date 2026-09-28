package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer

@PublishedApi
internal abstract class CollectionSerializer<E, C extends java.util.Collection<? extends E>, B> : CollectionLikeSerializer<E, C, B> {
   open fun CollectionSerializer(element: KSerializer<E>) {
      super(element, null);
   }

   protected open fun Any.collectionSize(): Int {
      return `$this$collectionSize`.size();
   }

   protected open fun Any.collectionIterator(): Iterator<Any> {
      return `$this$collectionIterator`.iterator();
   }
}
