package kotlin.collections

import kotlin.collections.ReversedListReadOnly.listIterator.1

private open class ReversedListReadOnly<T>(delegate: List<Any>) : AbstractList<T> {
   private final val delegate: List<Any>

   public open val size: Int
      public open get() {
         return this.delegate.size();
      }


   init {
      this.delegate = delegate;
   }

   public override operator fun get(index: Int): Any {
      return this.delegate.get(CollectionsKt__ReversedViewsKt.access$reverseElementIndex(this, index));
   }

   public override operator fun iterator(): Iterator<Any> {
      return this.listIterator(0);
   }

   public override fun listIterator(): ListIterator<Any> {
      return this.listIterator(0);
   }

   public override fun listIterator(index: Int): ListIterator<Any> {
      return new 1(this, index);
   }
}
