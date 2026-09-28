package kotlin.collections.builders

import java.io.InvalidObjectException
import java.io.NotSerializableException
import java.io.ObjectInputStream
import java.io.Serializable
import java.util.Arrays
import java.util.ConcurrentModificationException
import java.util.NoSuchElementException
import java.util.RandomAccess
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMutableList
import kotlin.jvm.internal.markers.KMutableListIterator

@SourceDebugExtension(["SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,722:1\n1#2:723\n*E\n"])
internal class ListBuilder<E>(initialCapacity: Int = 10) : AbstractMutableList<E>, java.util.List<E>, RandomAccess, Serializable, KMutableList {
   private final var backing: Array<Any>
   private final var length: Int
   private final var isReadOnly: Boolean

   public open val size: Int
      public open get() {
         return this.length;
      }


   init {
      this.backing = (E[])ListBuilderKt.arrayOfUninitializedElements(initialCapacity);
   }

   public fun build(): List<Any> {
      this.checkIsMutable();
      this.isReadOnly = true;
      return if (this.length > 0) this else Empty;
   }

   private fun writeReplace(): Any {
      if (this.isReadOnly) {
         return new SerializedCollection(this, 0);
      } else {
         throw new NotSerializableException("The list cannot be serialized while it is being built.");
      }
   }

   public override fun isEmpty(): Boolean {
      return this.length == 0;
   }

   public override operator fun get(index: Int): Any {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
      return this.backing[index];
   }

   public override operator fun set(index: Int, element: Any): Any {
      this.checkIsMutable();
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
      val old: Any = this.backing[index];
      this.backing[index] = (E)element;
      return (E)old;
   }

   public override fun indexOf(element: Any): Int {
      for (int i = 0; i < this.length; i++) {
         if (this.backing[i] == element) {
            return i;
         }
      }

      return -1;
   }

   public override fun lastIndexOf(element: Any): Int {
      for (int i = this.length - 1; i >= 0; i--) {
         if (this.backing[i] == element) {
            return i;
         }
      }

      return -1;
   }

   public override operator fun iterator(): MutableIterator<Any> {
      return this.listIterator(0);
   }

   public override fun listIterator(): MutableListIterator<Any> {
      return this.listIterator(0);
   }

   public override fun listIterator(index: Int): MutableListIterator<Any> {
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
      return new ListBuilder.Itr<>(this, index);
   }

   public override fun add(element: Any): Boolean {
      this.checkIsMutable();
      this.addAtInternal(this.length, (E)element);
      return true;
   }

   public override fun add(index: Int, element: Any) {
      this.checkIsMutable();
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
      this.addAtInternal(index, (E)element);
   }

   public override fun addAll(elements: Collection<Any>): Boolean {
      this.checkIsMutable();
      val n: Int = elements.size();
      this.addAllInternal(this.length, elements, n);
      return n > 0;
   }

   public override fun addAll(index: Int, elements: Collection<Any>): Boolean {
      this.checkIsMutable();
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
      val n: Int = elements.size();
      this.addAllInternal(index, elements, n);
      return n > 0;
   }

   public override fun clear() {
      this.checkIsMutable();
      this.removeRangeInternal(0, this.length);
   }

   public override fun removeAt(index: Int): Any {
      this.checkIsMutable();
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
      return this.removeAtInternal(index);
   }

   public override fun remove(element: Any): Boolean {
      this.checkIsMutable();
      val i: Int = this.indexOf(element);
      if (i >= 0) {
         this.removeAt(i);
      }

      return i >= 0;
   }

   public override fun removeAll(elements: Collection<Any>): Boolean {
      this.checkIsMutable();
      return this.retainOrRemoveAllInternal(0, this.length, elements, false) > 0;
   }

   public override fun retainAll(elements: Collection<Any>): Boolean {
      this.checkIsMutable();
      return this.retainOrRemoveAllInternal(0, this.length, elements, true) > 0;
   }

   public override fun subList(fromIndex: Int, toIndex: Int): MutableList<Any> {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, this.length);
      return new ListBuilder.BuilderSubList<>(this.backing, fromIndex, toIndex - fromIndex, null, this);
   }

   public override fun <T> toArray(array: Array<T>): Array<T> {
      if (array.length < this.length) {
         val var10000: Array<Any> = Arrays.copyOfRange(this.backing, 0, this.length, (Class<? extends Object[]>)array.getClass());
         return (T[])var10000;
      } else {
         ArraysKt.copyInto(this.backing, (E[])array, 0, 0, this.length);
         return (T[])CollectionsKt.terminateCollectionToArray(this.length, array);
      }
   }

   public override fun toArray(): Array<Any?> {
      return ArraysKt.copyOfRange(this.backing, 0, this.length);
   }

   public override operator fun equals(other: Any?): Boolean {
      return other === this || other is java.util.List && this.contentEquals(other as MutableList<*>);
   }

   public override fun hashCode(): Int {
      return ListBuilderKt.access$subarrayContentHashCode(this.backing, 0, this.length);
   }

   public override fun toString(): String {
      return ListBuilderKt.access$subarrayContentToString(this.backing, 0, this.length, this);
   }

   private fun registerModification() {
      this.modCount++;
   }

   private fun checkIsMutable() {
      if (this.isReadOnly) {
         throw new UnsupportedOperationException();
      }
   }

   private fun ensureExtraCapacity(n: Int) {
      this.ensureCapacityInternal(this.length + n);
   }

   private fun ensureCapacityInternal(minCapacity: Int) {
      if (minCapacity < 0) {
         throw new OutOfMemoryError();
      } else {
         if (minCapacity > this.backing.length) {
            this.backing = ListBuilderKt.copyOfUninitializedElements(
               this.backing, AbstractList.Companion.newCapacity$kotlin_stdlib(this.backing.length, minCapacity)
            );
         }
      }
   }

   private fun contentEquals(other: List<*>): Boolean {
      return ListBuilderKt.access$subarrayContentEquals(this.backing, 0, this.length, other);
   }

   private fun insertAtInternal(i: Int, n: Int) {
      this.ensureExtraCapacity(n);
      ArraysKt.copyInto(this.backing, this.backing, i + n, i, this.length);
      this.length += n;
   }

   private fun addAtInternal(i: Int, element: Any) {
      this.registerModification();
      this.insertAtInternal(i, 1);
      this.backing[i] = (E)element;
   }

   private fun addAllInternal(i: Int, elements: Collection<Any>, n: Int) {
      this.registerModification();
      this.insertAtInternal(i, n);
      var j: Int = 0;

      for (java.util.Iterator it = elements.iterator(); j < n; j++) {
         this.backing[i + j] = (E)it.next();
      }
   }

   private fun removeAtInternal(i: Int): Any {
      this.registerModification();
      val old: Any = this.backing[i];
      ArraysKt.copyInto(this.backing, this.backing, i, i + 1, this.length);
      ListBuilderKt.resetAt(this.backing, this.length - 1);
      this.length += -1;
      return (E)old;
   }

   private fun removeRangeInternal(rangeOffset: Int, rangeLength: Int) {
      if (rangeLength > 0) {
         this.registerModification();
      }

      ArraysKt.copyInto(this.backing, this.backing, rangeOffset, rangeOffset + rangeLength, this.length);
      ListBuilderKt.resetRange(this.backing, this.length - rangeLength, this.length);
      this.length -= rangeLength;
   }

   private fun retainOrRemoveAllInternal(rangeOffset: Int, rangeLength: Int, elements: Collection<Any>, retain: Boolean): Int {
      var i: Int = 0;
      var j: Int = 0;

      while (i < rangeLength) {
         if (elements.contains(this.backing[rangeOffset + i]) == retain) {
            this.backing[rangeOffset + j++] = this.backing[rangeOffset + i++];
         } else {
            i++;
         }
      }

      val removed: Int = rangeLength - j;
      ArraysKt.copyInto(this.backing, this.backing, rangeOffset + j, rangeOffset + rangeLength, this.length);
      ListBuilderKt.resetRange(this.backing, this.length - removed, this.length);
      if (removed > 0) {
         this.registerModification();
      }

      this.length -= removed;
      return removed;
   }

   fun ListBuilder() {
      this(0, 1, null);
   }

   @JvmStatic
   fun {
      val var0: ListBuilder = new ListBuilder(0);
      var0.isReadOnly = true;
      Empty = var0;
   }

   public class BuilderSubList<E>(vararg backing: Any,
         offset: Int,
         length: Int,
         parent: kotlin.collections.builders.ListBuilder.BuilderSubList<Any>?,
         root: ListBuilder<Any>
      )
      : AbstractMutableList<E>,
      java.util.List<E>,
      RandomAccess,
      Serializable,
      KMutableList {
      private final var backing: Array<Any>
      private final val offset: Int
      private final var length: Int
      private final val parent: kotlin.collections.builders.ListBuilder.BuilderSubList<Any>?
      private final val root: ListBuilder<Any>

      public open val size: Int
         public open get() {
            this.checkForComodification();
            return this.length;
         }


      private final val isReadOnly: Boolean
         private final get() {
            return ListBuilder.access$isReadOnly$p(this.root);
         }


      init {
         this.backing = (E[])backing;
         this.offset = offset;
         this.length = length;
         this.parent = parent;
         this.root = root;
         this.modCount = ListBuilder.access$getModCount$p$s-2084097795(this.root);
      }

      private fun writeReplace(): Any {
         if (this.isReadOnly()) {
            return new SerializedCollection(this, 0);
         } else {
            throw new NotSerializableException("The list cannot be serialized while it is being built.");
         }
      }

      private fun readObject(input: ObjectInputStream) {
         throw new InvalidObjectException("Deserialization is supported via proxy only");
      }

      public override fun isEmpty(): Boolean {
         this.checkForComodification();
         return this.length == 0;
      }

      public override operator fun get(index: Int): Any {
         this.checkForComodification();
         AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
         return this.backing[this.offset + index];
      }

      public override operator fun set(index: Int, element: Any): Any {
         this.checkIsMutable();
         this.checkForComodification();
         AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
         val old: Any = this.backing[this.offset + index];
         this.backing[this.offset + index] = (E)element;
         return (E)old;
      }

      public override fun indexOf(element: Any): Int {
         this.checkForComodification();

         for (int i = 0; i < this.length; i++) {
            if (this.backing[this.offset + i] == element) {
               return i;
            }
         }

         return -1;
      }

      public override fun lastIndexOf(element: Any): Int {
         this.checkForComodification();

         for (int i = this.length - 1; i >= 0; i--) {
            if (this.backing[this.offset + i] == element) {
               return i;
            }
         }

         return -1;
      }

      public override operator fun iterator(): MutableIterator<Any> {
         return this.listIterator(0);
      }

      public override fun listIterator(): MutableListIterator<Any> {
         return this.listIterator(0);
      }

      public override fun listIterator(index: Int): MutableListIterator<Any> {
         this.checkForComodification();
         AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
         return new ListBuilder.BuilderSubList.Itr<>(this, index);
      }

      public override fun add(element: Any): Boolean {
         this.checkIsMutable();
         this.checkForComodification();
         this.addAtInternal(this.offset + this.length, (E)element);
         return true;
      }

      public override fun add(index: Int, element: Any) {
         this.checkIsMutable();
         this.checkForComodification();
         AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
         this.addAtInternal(this.offset + index, (E)element);
      }

      public override fun addAll(elements: Collection<Any>): Boolean {
         this.checkIsMutable();
         this.checkForComodification();
         val n: Int = elements.size();
         this.addAllInternal(this.offset + this.length, elements, n);
         return n > 0;
      }

      public override fun addAll(index: Int, elements: Collection<Any>): Boolean {
         this.checkIsMutable();
         this.checkForComodification();
         AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.length);
         val n: Int = elements.size();
         this.addAllInternal(this.offset + index, elements, n);
         return n > 0;
      }

      public override fun clear() {
         this.checkIsMutable();
         this.checkForComodification();
         this.removeRangeInternal(this.offset, this.length);
      }

      public override fun removeAt(index: Int): Any {
         this.checkIsMutable();
         this.checkForComodification();
         AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.length);
         return this.removeAtInternal(this.offset + index);
      }

      public override fun remove(element: Any): Boolean {
         this.checkIsMutable();
         this.checkForComodification();
         val i: Int = this.indexOf(element);
         if (i >= 0) {
            this.removeAt(i);
         }

         return i >= 0;
      }

      public override fun removeAll(elements: Collection<Any>): Boolean {
         this.checkIsMutable();
         this.checkForComodification();
         return this.retainOrRemoveAllInternal(this.offset, this.length, elements, false) > 0;
      }

      public override fun retainAll(elements: Collection<Any>): Boolean {
         this.checkIsMutable();
         this.checkForComodification();
         return this.retainOrRemoveAllInternal(this.offset, this.length, elements, true) > 0;
      }

      public override fun subList(fromIndex: Int, toIndex: Int): MutableList<Any> {
         AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, this.length);
         return new ListBuilder.BuilderSubList<>(this.backing, this.offset + fromIndex, toIndex - fromIndex, this, this.root);
      }

      public override fun <T> toArray(array: Array<T>): Array<T> {
         this.checkForComodification();
         if (array.length < this.length) {
            val var10000: Array<Any> = Arrays.copyOfRange(this.backing, this.offset, this.offset + this.length, (Class<? extends Object[]>)array.getClass());
            return (T[])var10000;
         } else {
            ArraysKt.copyInto(this.backing, (E[])array, 0, this.offset, this.offset + this.length);
            return (T[])CollectionsKt.terminateCollectionToArray(this.length, array);
         }
      }

      public override fun toArray(): Array<Any?> {
         this.checkForComodification();
         return ArraysKt.copyOfRange(this.backing, this.offset, this.offset + this.length);
      }

      public override operator fun equals(other: Any?): Boolean {
         this.checkForComodification();
         return other === this || other is java.util.List && this.contentEquals(other as MutableList<*>);
      }

      public override fun hashCode(): Int {
         this.checkForComodification();
         return ListBuilderKt.access$subarrayContentHashCode(this.backing, this.offset, this.length);
      }

      public override fun toString(): String {
         this.checkForComodification();
         return ListBuilderKt.access$subarrayContentToString(this.backing, this.offset, this.length, this);
      }

      private fun registerModification() {
         this.modCount++;
      }

      private fun checkForComodification() {
         if (ListBuilder.access$getModCount$p$s-2084097795(this.root) != this.modCount) {
            throw new ConcurrentModificationException();
         }
      }

      private fun checkIsMutable() {
         if (this.isReadOnly()) {
            throw new UnsupportedOperationException();
         }
      }

      private fun contentEquals(other: List<*>): Boolean {
         return ListBuilderKt.access$subarrayContentEquals(this.backing, this.offset, this.length, other);
      }

      private fun addAtInternal(i: Int, element: Any) {
         this.registerModification();
         if (this.parent != null) {
            this.parent.addAtInternal(i, (E)element);
         } else {
            ListBuilder.access$addAtInternal(this.root, i, element);
         }

         this.backing = (E[])ListBuilder.access$getBacking$p(this.root);
         val var3: Int = this.length++;
      }

      private fun addAllInternal(i: Int, elements: Collection<Any>, n: Int) {
         this.registerModification();
         if (this.parent != null) {
            this.parent.addAllInternal(i, elements, n);
         } else {
            ListBuilder.access$addAllInternal(this.root, i, elements, n);
         }

         this.backing = (E[])ListBuilder.access$getBacking$p(this.root);
         this.length += n;
      }

      private fun removeAtInternal(i: Int): Any {
         this.registerModification();
         val old: Any = if (this.parent != null) this.parent.removeAtInternal(i) else ListBuilder.access$removeAtInternal(this.root, i);
         this.length += -1;
         return (E)old;
      }

      private fun removeRangeInternal(rangeOffset: Int, rangeLength: Int) {
         if (rangeLength > 0) {
            this.registerModification();
         }

         if (this.parent != null) {
            this.parent.removeRangeInternal(rangeOffset, rangeLength);
         } else {
            ListBuilder.access$removeRangeInternal(this.root, rangeOffset, rangeLength);
         }

         this.length -= rangeLength;
      }

      private fun retainOrRemoveAllInternal(rangeOffset: Int, rangeLength: Int, elements: Collection<Any>, retain: Boolean): Int {
         val removed: Int = if (this.parent != null)
            this.parent.retainOrRemoveAllInternal(rangeOffset, rangeLength, elements, retain)
            else
            ListBuilder.access$retainOrRemoveAllInternal(this.root, rangeOffset, rangeLength, elements, retain);
         if (removed > 0) {
            this.registerModification();
         }

         this.length -= removed;
         return removed;
      }

      @SourceDebugExtension(["SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder$BuilderSubList$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,722:1\n1#2:723\n*E\n"])
      private class Itr<E>(list: kotlin.collections.builders.ListBuilder.BuilderSubList<Any>, index: Int) : java.util.ListIterator<E>, KMutableListIterator {
         private final val list: kotlin.collections.builders.ListBuilder.BuilderSubList<Any>
         private final var index: Int
         private final var lastIndex: Int
         private final var expectedModCount: Int

         init {
            this.list = list;
            this.index = index;
            this.lastIndex = -1;
            this.expectedModCount = ListBuilder.BuilderSubList.access$getModCount$p$s1462993667(this.list);
         }

         public override fun hasPrevious(): Boolean {
            return this.index > 0;
         }

         public override operator fun hasNext(): Boolean {
            return this.index < ListBuilder.BuilderSubList.access$getLength$p(this.list);
         }

         public override fun previousIndex(): Int {
            return this.index - 1;
         }

         public override fun nextIndex(): Int {
            return this.index;
         }

         public override fun previous(): Any {
            this.checkForComodification();
            if (this.index <= 0) {
               throw new NoSuchElementException();
            } else {
               this.index += -1;
               this.lastIndex = this.index;
               return (E)ListBuilder.BuilderSubList.access$getBacking$p(this.list)[ListBuilder.BuilderSubList.access$getOffset$p(this.list) + this.lastIndex];
            }
         }

         public override operator fun next(): Any {
            this.checkForComodification();
            if (this.index >= ListBuilder.BuilderSubList.access$getLength$p(this.list)) {
               throw new NoSuchElementException();
            } else {
               this.lastIndex = this.index++;
               return (E)ListBuilder.BuilderSubList.access$getBacking$p(this.list)[ListBuilder.BuilderSubList.access$getOffset$p(this.list) + this.lastIndex];
            }
         }

         public override fun set(element: Any) {
            this.checkForComodification();
            if (this.lastIndex == -1) {
               throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
            } else {
               this.list.set(this.lastIndex, (E)element);
            }
         }

         public override fun add(element: Any) {
            this.checkForComodification();
            this.list.add(this.index++, (E)element);
            this.lastIndex = -1;
            this.expectedModCount = ListBuilder.BuilderSubList.access$getModCount$p$s1462993667(this.list);
         }

         public override fun remove() {
            this.checkForComodification();
            if (this.lastIndex == -1) {
               throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
            } else {
               this.list.removeAt(this.lastIndex);
               this.index = this.lastIndex;
               this.lastIndex = -1;
               this.expectedModCount = ListBuilder.BuilderSubList.access$getModCount$p$s1462993667(this.list);
            }
         }

         private fun checkForComodification() {
            if (ListBuilder.access$getModCount$p$s-2084097795(ListBuilder.BuilderSubList.access$getRoot$p(this.list)) != this.expectedModCount) {
               throw new ConcurrentModificationException();
            }
         }
      }
   }

   private companion object {
      private final val Empty: ListBuilder<Nothing>
   }

   @SourceDebugExtension(["SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,722:1\n1#2:723\n*E\n"])
   private class Itr<E>(list: ListBuilder<Any>, index: Int) : java.util.ListIterator<E>, KMutableListIterator {
      private final val list: ListBuilder<Any>
      private final var index: Int
      private final var lastIndex: Int
      private final var expectedModCount: Int

      init {
         this.list = list;
         this.index = index;
         this.lastIndex = -1;
         this.expectedModCount = ListBuilder.access$getModCount$p$s-2084097795(this.list);
      }

      public override fun hasPrevious(): Boolean {
         return this.index > 0;
      }

      public override operator fun hasNext(): Boolean {
         return this.index < ListBuilder.access$getLength$p(this.list);
      }

      public override fun previousIndex(): Int {
         return this.index - 1;
      }

      public override fun nextIndex(): Int {
         return this.index;
      }

      public override fun previous(): Any {
         this.checkForComodification();
         if (this.index <= 0) {
            throw new NoSuchElementException();
         } else {
            this.index += -1;
            this.lastIndex = this.index;
            return (E)ListBuilder.access$getBacking$p(this.list)[this.lastIndex];
         }
      }

      public override operator fun next(): Any {
         this.checkForComodification();
         if (this.index >= ListBuilder.access$getLength$p(this.list)) {
            throw new NoSuchElementException();
         } else {
            this.lastIndex = this.index++;
            return (E)ListBuilder.access$getBacking$p(this.list)[this.lastIndex];
         }
      }

      public override fun set(element: Any) {
         this.checkForComodification();
         if (this.lastIndex == -1) {
            throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
         } else {
            this.list.set(this.lastIndex, (E)element);
         }
      }

      public override fun add(element: Any) {
         this.checkForComodification();
         this.list.add(this.index++, (E)element);
         this.lastIndex = -1;
         this.expectedModCount = ListBuilder.access$getModCount$p$s-2084097795(this.list);
      }

      public override fun remove() {
         this.checkForComodification();
         if (this.lastIndex == -1) {
            throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
         } else {
            this.list.removeAt(this.lastIndex);
            this.index = this.lastIndex;
            this.lastIndex = -1;
            this.expectedModCount = ListBuilder.access$getModCount$p$s-2084097795(this.list);
         }
      }

      private fun checkForComodification() {
         if (ListBuilder.access$getModCount$p$s-2084097795(this.list) != this.expectedModCount) {
            throw new ConcurrentModificationException();
         }
      }
   }
}
