package kotlin.collections

import kotlin.collections.ReversedList.listIterator.1

private class ReversedList<T>(delegate: MutableList<Any>) : AbstractMutableList<T> {
   private final val delegate: MutableList<Any>

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

   public override fun clear() {
      this.delegate.clear();
   }

   public override fun removeAt(index: Int): Any {
      return this.delegate.remove(CollectionsKt__ReversedViewsKt.access$reverseElementIndex(this, index));
   }

   public override operator fun set(index: Int, element: Any): Any {
      return this.delegate.set(CollectionsKt__ReversedViewsKt.access$reverseElementIndex(this, index), (T)element);
   }

   public override fun add(index: Int, element: Any) {
      this.delegate.add(CollectionsKt__ReversedViewsKt.access$reversePositionIndex(this, index), (T)element);
   }

   public override operator fun iterator(): MutableIterator<Any> {
      return this.listIterator(0);
   }

   public override fun listIterator(): MutableListIterator<Any> {
      return this.listIterator(0);
   }

   public override fun listIterator(index: Int): MutableListIterator<Any> {
      return new 1(this, index);
   }
}
