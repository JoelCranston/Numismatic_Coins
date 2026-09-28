package kotlin.collections

import java.util.NoSuchElementException
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension

@SinceKotlin(version = "1.4")
@SourceDebugExtension(["SMAP\nArrayDeque.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayDeque.kt\nkotlin/collections/ArrayDeque\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,660:1\n476#1,53:663\n476#1,53:716\n37#2,2:661\n*S KotlinDebug\n*F\n+ 1 ArrayDeque.kt\nkotlin/collections/ArrayDeque\n*L\n471#1:663,53\n473#1:716,53\n46#1:661,2\n*E\n"])
public class ArrayDeque<E> : AbstractMutableList<E> {
   private final var head: Int
   private final var elementData: Array<Any?>

   public open var size: Int
      private set

   public constructor(initialCapacity: Int)  {
      val var10001: Array<Any>;
      if (initialCapacity == 0) {
         var10001 = emptyElementData;
      } else {
         if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: $initialCapacity");
         }

         var10001 = new Object[initialCapacity];
      }

      this.elementData = var10001;
   }

   public constructor()  {
      this.elementData = emptyElementData;
   }

   public constructor(elements: Collection<Any>)  {
      this.elementData = elements.toArray(new Object[0]);
      this.size = this.elementData.length;
      if (this.elementData.length == 0) {
         this.elementData = emptyElementData;
      }
   }

   private fun ensureCapacity(minCapacity: Int) {
      if (minCapacity < 0) {
         throw new IllegalStateException("Deque is too big.");
      } else if (minCapacity > this.elementData.length) {
         if (this.elementData === emptyElementData) {
            this.elementData = new Object[RangesKt.coerceAtLeast(minCapacity, 10)];
         } else {
            this.copyElements(AbstractList.Companion.newCapacity$kotlin_stdlib(this.elementData.length, minCapacity));
         }
      }
   }

   private fun copyElements(newCapacity: Int) {
      val newElements: Array<Any> = new Object[newCapacity];
      ArraysKt.copyInto(this.elementData, newElements, 0, this.head, this.elementData.length);
      ArraysKt.copyInto(this.elementData, newElements, this.elementData.length - this.head, 0, this.head);
      this.head = 0;
      this.elementData = newElements;
   }

   @InlineOnly
   private inline fun internalGet(internalIndex: Int): Any {
      return (E)this.elementData[internalIndex];
   }

   private fun positiveMod(index: Int): Int {
      return if (index >= this.elementData.length) index - this.elementData.length else index;
   }

   private fun negativeMod(index: Int): Int {
      return if (index < 0) index + this.elementData.length else index;
   }

   @InlineOnly
   private inline fun internalIndex(index: Int): Int {
      return this.positiveMod(this.head + index);
   }

   private fun incremented(index: Int): Int {
      return if (index == ArraysKt.getLastIndex(this.elementData)) 0 else index + 1;
   }

   private fun decremented(index: Int): Int {
      return if (index == 0) ArraysKt.getLastIndex(this.elementData) else index - 1;
   }

   public override fun isEmpty(): Boolean {
      return this.size() == 0;
   }

   public fun first(): Any {
      if (this.isEmpty()) {
         throw new NoSuchElementException("ArrayDeque is empty.");
      } else {
         return (E)this.elementData[this.head];
      }
   }

   public fun firstOrNull(): Any? {
      return (E)(if (this.isEmpty()) null else this.elementData[this.head]);
   }

   public fun last(): Any {
      if (this.isEmpty()) {
         throw new NoSuchElementException("ArrayDeque is empty.");
      } else {
         return (E)this.elementData[this.positiveMod(this.head + CollectionsKt.getLastIndex(this))];
      }
   }

   public fun lastOrNull(): Any? {
      return (E)(if (this.isEmpty()) null else this.elementData[this.positiveMod(this.head + CollectionsKt.getLastIndex(this))]);
   }

   public override fun addFirst(element: Any) {
      this.registerModification();
      this.ensureCapacity(this.size() + 1);
      this.head = this.decremented(this.head);
      this.elementData[this.head] = element;
      this.size = this.size() + 1;
   }

   public override fun addLast(element: Any) {
      this.registerModification();
      this.ensureCapacity(this.size() + 1);
      this.elementData[this.positiveMod(this.head + this.size())] = element;
      this.size = this.size() + 1;
   }

   public override fun removeFirst(): Any {
      if (this.isEmpty()) {
         throw new NoSuchElementException("ArrayDeque is empty.");
      } else {
         this.registerModification();
         val element: Any = this.elementData[this.head];
         this.elementData[this.head] = null;
         this.head = this.incremented(this.head);
         this.size = this.size() - 1;
         return (E)element;
      }
   }

   public fun removeFirstOrNull(): Any? {
      return if (this.isEmpty()) null else this.removeFirst();
   }

   public override fun removeLast(): Any {
      if (this.isEmpty()) {
         throw new NoSuchElementException("ArrayDeque is empty.");
      } else {
         this.registerModification();
         val internalLastIndex: Int = this.positiveMod(this.head + CollectionsKt.getLastIndex(this));
         val element: Any = this.elementData[internalLastIndex];
         this.elementData[internalLastIndex] = null;
         this.size = this.size() - 1;
         return (E)element;
      }
   }

   public fun removeLastOrNull(): Any? {
      return if (this.isEmpty()) null else this.removeLast();
   }

   public override fun add(element: Any): Boolean {
      this.addLast((E)element);
      return true;
   }

   public override fun add(index: Int, element: Any) {
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.size());
      if (index == this.size()) {
         this.addLast((E)element);
      } else if (index == 0) {
         this.addFirst((E)element);
      } else {
         this.registerModification();
         this.ensureCapacity(this.size() + 1);
         val internalIndex: Int = this.positiveMod(this.head + index);
         if (index < this.size() + 1 shr 1) {
            val tail: Int = this.decremented(internalIndex);
            val decrementedHead: Int = this.decremented(this.head);
            if (tail >= this.head) {
               this.elementData[decrementedHead] = this.elementData[this.head];
               ArraysKt.copyInto(this.elementData, this.elementData, this.head, this.head + 1, tail + 1);
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, this.head - 1, this.head, this.elementData.length);
               this.elementData[this.elementData.length - 1] = this.elementData[0];
               ArraysKt.copyInto(this.elementData, this.elementData, 0, 1, tail + 1);
            }

            this.elementData[tail] = element;
            this.head = decrementedHead;
         } else {
            val var6: Int = this.positiveMod(this.head + this.size());
            if (internalIndex < var6) {
               ArraysKt.copyInto(this.elementData, this.elementData, internalIndex + 1, internalIndex, var6);
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, var6);
               this.elementData[0] = this.elementData[this.elementData.length - 1];
               ArraysKt.copyInto(this.elementData, this.elementData, internalIndex + 1, internalIndex, this.elementData.length - 1);
            }

            this.elementData[internalIndex] = element;
         }

         this.size = this.size() + 1;
      }
   }

   private fun copyCollectionElements(internalIndex: Int, elements: Collection<Any>) {
      val iterator: java.util.Iterator = elements.iterator();
      var index: Int = internalIndex;

      for (int var5 = this.elementData.length; index < var5 && iterator.hasNext(); index++) {
         this.elementData[index] = iterator.next();
      }

      index = 0;

      for (int var7 = this.head; index < var7 && iterator.hasNext(); index++) {
         this.elementData[index] = iterator.next();
      }

      this.size = this.size() + elements.size();
   }

   public override fun addAll(elements: Collection<Any>): Boolean {
      if (elements.isEmpty()) {
         return false;
      } else {
         this.registerModification();
         this.ensureCapacity(this.size() + elements.size());
         this.copyCollectionElements(this.positiveMod(this.head + this.size()), elements);
         return true;
      }
   }

   public override fun addAll(index: Int, elements: Collection<Any>): Boolean {
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(index, this.size());
      if (elements.isEmpty()) {
         return false;
      } else if (index == this.size()) {
         return this.addAll(elements);
      } else {
         this.registerModification();
         this.ensureCapacity(this.size() + elements.size());
         val tail: Int = this.positiveMod(this.head + this.size());
         val internalIndex: Int = this.positiveMod(this.head + index);
         val elementsSize: Int = elements.size();
         if (index < this.size() + 1 shr 1) {
            var shiftedInternalIndex: Int = this.head - elementsSize;
            if (internalIndex >= this.head) {
               if (shiftedInternalIndex >= 0) {
                  ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, this.head, internalIndex);
               } else {
                  shiftedInternalIndex += this.elementData.length;
                  val shiftToFront: Int = internalIndex - this.head;
                  val shiftToBack: Int = this.elementData.length - shiftedInternalIndex;
                  if (this.elementData.length - shiftedInternalIndex >= shiftToFront) {
                     ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, this.head, internalIndex);
                  } else {
                     ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, this.head, this.head + shiftToBack);
                     ArraysKt.copyInto(this.elementData, this.elementData, 0, this.head + shiftToBack, internalIndex);
                  }
               }
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, shiftedInternalIndex, this.head, this.elementData.length);
               if (elementsSize >= internalIndex) {
                  ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - elementsSize, 0, internalIndex);
               } else {
                  ArraysKt.copyInto(this.elementData, this.elementData, this.elementData.length - elementsSize, 0, elementsSize);
                  ArraysKt.copyInto(this.elementData, this.elementData, 0, elementsSize, internalIndex);
               }
            }

            this.head = shiftedInternalIndex;
            this.copyCollectionElements(this.negativeMod(internalIndex - elementsSize), elements);
         } else {
            val var9: Int = internalIndex + elementsSize;
            if (internalIndex < tail) {
               if (tail + elementsSize <= this.elementData.length) {
                  ArraysKt.copyInto(this.elementData, this.elementData, var9, internalIndex, tail);
               } else if (var9 >= this.elementData.length) {
                  ArraysKt.copyInto(this.elementData, this.elementData, var9 - this.elementData.length, internalIndex, tail);
               } else {
                  val var10: Int = tail + elementsSize - this.elementData.length;
                  ArraysKt.copyInto(this.elementData, this.elementData, 0, tail - (tail + elementsSize - this.elementData.length), tail);
                  ArraysKt.copyInto(this.elementData, this.elementData, var9, internalIndex, tail - var10);
               }
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, elementsSize, 0, tail);
               if (var9 >= this.elementData.length) {
                  ArraysKt.copyInto(this.elementData, this.elementData, var9 - this.elementData.length, internalIndex, this.elementData.length);
               } else {
                  ArraysKt.copyInto(this.elementData, this.elementData, 0, this.elementData.length - elementsSize, this.elementData.length);
                  ArraysKt.copyInto(this.elementData, this.elementData, var9, internalIndex, this.elementData.length - elementsSize);
               }
            }

            this.copyCollectionElements(internalIndex, elements);
         }

         return true;
      }
   }

   public override operator fun get(index: Int): Any {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.size());
      return (E)this.elementData[this.positiveMod(this.head + index)];
   }

   public override operator fun set(index: Int, element: Any): Any {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.size());
      val internalIndex: Int = this.positiveMod(this.head + index);
      val oldElement: Any = this.elementData[internalIndex];
      this.elementData[internalIndex] = element;
      return (E)oldElement;
   }

   public override operator fun contains(element: Any): Boolean {
      return this.indexOf(element) != -1;
   }

   public override fun indexOf(element: Any): Int {
      val tail: Int = this.positiveMod(this.head + this.size());
      if (this.head < tail) {
         for (int index = this.head; index < tail; index++) {
            if (element == this.elementData[index]) {
               return index - this.head;
            }
         }
      } else if (this.head >= tail) {
         var indexx: Int = this.head;

         for (int var4 = this.elementData.length; indexx < var4; indexx++) {
            if (element == this.elementData[indexx]) {
               return indexx - this.head;
            }
         }

         for (int indexx = 0; indexx < tail; indexx++) {
            if (element == this.elementData[indexx]) {
               return indexx + this.elementData.length - this.head;
            }
         }
      }

      return -1;
   }

   public override fun lastIndexOf(element: Any): Int {
      val tail: Int = this.positiveMod(this.head + this.size());
      if (this.head < tail) {
         var index: Int = tail - 1;
         val var4: Int = this.head;
         if (this.head <= index) {
            while (true) {
               if (element == this.elementData[index]) {
                  return index - this.head;
               }

               if (index == var4) {
                  break;
               }

               index--;
            }
         }
      } else if (this.head > tail) {
         for (int index = tail - 1; -1 < index; index--) {
            if (element == this.elementData[var5]) {
               return var5 + this.elementData.length - this.head;
            }
         }

         var indexx: Int = ArraysKt.getLastIndex(this.elementData);
         val var7: Int = this.head;
         if (this.head <= indexx) {
            while (true) {
               if (element == this.elementData[indexx]) {
                  return indexx - this.head;
               }

               if (indexx == var7) {
                  break;
               }

               indexx--;
            }
         }
      }

      return -1;
   }

   public override fun remove(element: Any): Boolean {
      val index: Int = this.indexOf(element);
      if (index == -1) {
         return false;
      } else {
         this.removeAt(index);
         return true;
      }
   }

   public override fun removeAt(index: Int): Any {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(index, this.size());
      if (index == CollectionsKt.getLastIndex(this)) {
         return this.removeLast();
      } else if (index == 0) {
         return this.removeFirst();
      } else {
         this.registerModification();
         val internalIndex: Int = this.positiveMod(this.head + index);
         val element: Any = this.elementData[internalIndex];
         if (index < this.size() shr 1) {
            if (internalIndex >= this.head) {
               ArraysKt.copyInto(this.elementData, this.elementData, this.head + 1, this.head, internalIndex);
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, 1, 0, internalIndex);
               this.elementData[0] = this.elementData[this.elementData.length - 1];
               ArraysKt.copyInto(this.elementData, this.elementData, this.head + 1, this.head, this.elementData.length - 1);
            }

            this.elementData[this.head] = null;
            this.head = this.incremented(this.head);
         } else {
            val internalLastIndex: Int = this.positiveMod(this.head + CollectionsKt.getLastIndex(this));
            if (internalIndex <= internalLastIndex) {
               ArraysKt.copyInto(this.elementData, this.elementData, internalIndex, internalIndex + 1, internalLastIndex + 1);
            } else {
               ArraysKt.copyInto(this.elementData, this.elementData, internalIndex, internalIndex + 1, this.elementData.length);
               this.elementData[this.elementData.length - 1] = this.elementData[0];
               ArraysKt.copyInto(this.elementData, this.elementData, 0, 1, internalLastIndex + 1);
            }

            this.elementData[internalLastIndex] = null;
         }

         this.size = this.size() - 1;
         return (E)element;
      }
   }

   public override fun removeAll(elements: Collection<Any>): Boolean {
      val `this_$iv`: ArrayDeque = this;
      val var10000: Boolean;
      if (!this.isEmpty() && this.elementData.length != 0) {
         val `tail$iv`: Int = this.positiveMod(this.head + this.size());
         var `newTail$iv`: Int = this.head;
         var `modified$iv`: Boolean = false;
         if (this.head < `tail$iv`) {
            for (int index$iv = this.head; index$iv < tail$iv; index$iv++) {
               val var15: Any = `this_$iv`.elementData[var13];
               if (!elements.contains(`this_$iv`.elementData[var13])) {
                  `this_$iv`.elementData[`newTail$iv`++] = var15;
               } else {
                  `modified$iv` = true;
               }
            }

            ArraysKt.fill(`this_$iv`.elementData, null, `newTail$iv`, `tail$iv`);
         } else {
            var `index$ivx`: Int = this.head;

            for (int element$iv = this.elementData.length; index$ivx < element$iv; index$ivx++) {
               val `element$ivx`: Any = `this_$iv`.elementData[`index$ivx`];
               `this_$iv`.elementData[`index$ivx`] = null;
               if (!elements.contains(`element$ivx`)) {
                  `this_$iv`.elementData[`newTail$iv`++] = `element$ivx`;
               } else {
                  `modified$iv` = true;
               }
            }

            `newTail$iv` = `this_$iv`.positiveMod(`newTail$iv`);

            for (int index$ivxx = 0; index$ivxx < tail$iv; index$ivxx++) {
               val var14: Any = `this_$iv`.elementData[`index$ivxx`];
               `this_$iv`.elementData[`index$ivxx`] = null;
               if (!elements.contains(var14)) {
                  `this_$iv`.elementData[`newTail$iv`] = var14;
                  `newTail$iv` = `this_$iv`.incremented(`newTail$iv`);
               } else {
                  `modified$iv` = true;
               }
            }
         }

         if (`modified$iv`) {
            `this_$iv`.registerModification();
            `this_$iv`.size = `this_$iv`.negativeMod(`newTail$iv` - `this_$iv`.head);
         }

         var10000 = `modified$iv`;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   public override fun retainAll(elements: Collection<Any>): Boolean {
      val `this_$iv`: ArrayDeque = this;
      val var10000: Boolean;
      if (!this.isEmpty() && this.elementData.length != 0) {
         val `tail$iv`: Int = this.positiveMod(this.head + this.size());
         var `newTail$iv`: Int = this.head;
         var `modified$iv`: Boolean = false;
         if (this.head < `tail$iv`) {
            for (int index$iv = this.head; index$iv < tail$iv; index$iv++) {
               val var15: Any = `this_$iv`.elementData[var13];
               if (elements.contains(`this_$iv`.elementData[var13])) {
                  `this_$iv`.elementData[`newTail$iv`++] = var15;
               } else {
                  `modified$iv` = true;
               }
            }

            ArraysKt.fill(`this_$iv`.elementData, null, `newTail$iv`, `tail$iv`);
         } else {
            var `index$ivx`: Int = this.head;

            for (int element$iv = this.elementData.length; index$ivx < element$iv; index$ivx++) {
               val `element$ivx`: Any = `this_$iv`.elementData[`index$ivx`];
               `this_$iv`.elementData[`index$ivx`] = null;
               if (elements.contains(`element$ivx`)) {
                  `this_$iv`.elementData[`newTail$iv`++] = `element$ivx`;
               } else {
                  `modified$iv` = true;
               }
            }

            `newTail$iv` = `this_$iv`.positiveMod(`newTail$iv`);

            for (int index$ivxx = 0; index$ivxx < tail$iv; index$ivxx++) {
               val var14: Any = `this_$iv`.elementData[`index$ivxx`];
               `this_$iv`.elementData[`index$ivxx`] = null;
               if (elements.contains(var14)) {
                  `this_$iv`.elementData[`newTail$iv`] = var14;
                  `newTail$iv` = `this_$iv`.incremented(`newTail$iv`);
               } else {
                  `modified$iv` = true;
               }
            }
         }

         if (`modified$iv`) {
            `this_$iv`.registerModification();
            `this_$iv`.size = `this_$iv`.negativeMod(`newTail$iv` - `this_$iv`.head);
         }

         var10000 = `modified$iv`;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   private inline fun filterInPlace(predicate: (Any) -> Boolean): Boolean {
      if (!this.isEmpty() && this.elementData.length != 0) {
         val tail: Int = this.positiveMod(this.head + this.size());
         var newTail: Int = this.head;
         var modified: Boolean = false;
         if (this.head < tail) {
            for (int index = this.head; index < tail; index++) {
               val element: Any = this.elementData[index];
               if (predicate.invoke(this.elementData[index]) as java.lang.Boolean) {
                  this.elementData[newTail++] = element;
               } else {
                  modified = true;
               }
            }

            ArraysKt.fill(this.elementData, null, newTail, tail);
         } else {
            var indexx: Int = this.head;

            for (int var11 = this.elementData.length; indexx < var11; indexx++) {
               val element: Any = this.elementData[indexx];
               this.elementData[indexx] = null;
               if (predicate.invoke(element) as java.lang.Boolean) {
                  this.elementData[newTail++] = element;
               } else {
                  modified = true;
               }
            }

            newTail = this.positiveMod(newTail);

            for (int indexx = 0; indexx < tail; indexx++) {
               val var12: Any = this.elementData[indexx];
               this.elementData[indexx] = null;
               if (predicate.invoke(var12) as java.lang.Boolean) {
                  this.elementData[newTail] = var12;
                  newTail = this.incremented(newTail);
               } else {
                  modified = true;
               }
            }
         }

         if (modified) {
            this.registerModification();
            this.size = this.negativeMod(newTail - this.head);
         }

         return modified;
      } else {
         return false;
      }
   }

   public override fun clear() {
      if (!this.isEmpty()) {
         this.registerModification();
         this.nullifyNonEmpty(this.head, this.positiveMod(this.head + this.size()));
      }

      this.head = 0;
      this.size = 0;
   }

   public override fun <T> toArray(array: Array<T>): Array<T> {
      val dest: Array<Any> = if (array.length >= this.size()) array else ArraysKt.arrayOfNulls(array, this.size());
      val tail: Int = this.positiveMod(this.head + this.size());
      if (this.head < tail) {
         ArraysKt.copyInto$default(this.elementData, dest, 0, this.head, tail, 2, null);
      } else if (!this.isEmpty()) {
         ArraysKt.copyInto(this.elementData, dest, 0, this.head, this.elementData.length);
         ArraysKt.copyInto(this.elementData, dest, this.elementData.length - this.head, 0, tail);
      }

      return (T[])CollectionsKt.terminateCollectionToArray(this.size(), dest);
   }

   public override fun toArray(): Array<Any?> {
      return this.toArray(new Object[this.size()]);
   }

   protected override fun removeRange(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, this.size());
      val length: Int = toIndex - fromIndex;
      if (toIndex - fromIndex != 0) {
         if (length == this.size()) {
            this.clear();
         } else if (length == 1) {
            this.removeAt(fromIndex);
         } else {
            this.registerModification();
            if (fromIndex < this.size() - toIndex) {
               this.removeRangeShiftPreceding(fromIndex, toIndex);
               val tail: Int = this.positiveMod(this.head + length);
               this.nullifyNonEmpty(this.head, tail);
               this.head = tail;
            } else {
               this.removeRangeShiftSucceeding(fromIndex, toIndex);
               val var5: Int = this.positiveMod(this.head + this.size());
               this.nullifyNonEmpty(this.negativeMod(var5 - length), var5);
            }

            this.size = this.size() - length;
         }
      }
   }

   private fun removeRangeShiftPreceding(fromIndex: Int, toIndex: Int) {
      var copyFromIndex: Int = this.positiveMod(this.head + (fromIndex - 1));
      var copyToIndex: Int = this.positiveMod(this.head + (toIndex - 1));
      var var9: Int = fromIndex;

      while (copyCount > 0) {
         val var10: Int = Math.min(var9, Math.min(copyFromIndex + 1, copyToIndex + 1));
         ArraysKt.copyInto(this.elementData, this.elementData, copyToIndex - var10 + 1, copyFromIndex - var10 + 1, copyFromIndex + 1);
         copyFromIndex = this.negativeMod(copyFromIndex - var10);
         copyToIndex = this.negativeMod(copyToIndex - var10);
         var9 -= var10;
      }
   }

   private fun removeRangeShiftSucceeding(fromIndex: Int, toIndex: Int) {
      var copyFromIndex: Int = this.positiveMod(this.head + toIndex);
      var copyToIndex: Int = this.positiveMod(this.head + fromIndex);
      var copyCount: Int = this.size() - toIndex;

      while (copyCount > 0) {
         val segmentLength: Int = Math.min(copyCount, Math.min(this.elementData.length - copyFromIndex, this.elementData.length - copyToIndex));
         ArraysKt.copyInto(this.elementData, this.elementData, copyToIndex, copyFromIndex, copyFromIndex + segmentLength);
         copyFromIndex = this.positiveMod(copyFromIndex + segmentLength);
         copyToIndex = this.positiveMod(copyToIndex + segmentLength);
         copyCount -= segmentLength;
      }
   }

   private fun nullifyNonEmpty(internalFromIndex: Int, internalToIndex: Int) {
      if (internalFromIndex < internalToIndex) {
         ArraysKt.fill(this.elementData, null, internalFromIndex, internalToIndex);
      } else {
         ArraysKt.fill(this.elementData, null, internalFromIndex, this.elementData.length);
         ArraysKt.fill(this.elementData, null, 0, internalToIndex);
      }
   }

   private fun registerModification() {
      this.modCount++;
   }

   internal fun <T> testToArray(array: Array<T>): Array<T> {
      return (T[])this.toArray(array);
   }

   internal fun testToArray(): Array<Any?> {
      return this.toArray();
   }

   internal fun testRemoveRange(fromIndex: Int, toIndex: Int) {
      this.removeRange(fromIndex, toIndex);
   }

   internal fun internalStructure(structure: (Int, Array<Any?>) -> Unit) {
      structure.invoke(
         if (!this.isEmpty() && this.head >= this.positiveMod(this.head + this.size())) this.head - this.elementData.length else this.head, this.toArray()
      );
   }

   internal companion object {
      private final val emptyElementData: Array<Any?>
      private const val defaultMinCapacity: Int
   }
}
