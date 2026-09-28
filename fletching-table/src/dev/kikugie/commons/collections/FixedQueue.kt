package dev.kikugie.commons.collections

import java.util.Arrays
import java.util.NoSuchElementException
import java.util.Queue
import kotlin.jvm.internal.CollectionToArray
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMutableIterator

@SourceDebugExtension(["SMAP\nFixedQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FixedQueue.kt\ndev/kikugie/commons/collections/FixedQueue\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,124:1\n1740#2,3:125\n1869#2,2:128\n*S KotlinDebug\n*F\n+ 1 FixedQueue.kt\ndev/kikugie/commons/collections/FixedQueue\n*L\n41#1:125,3\n92#1:128,2\n*E\n"])
public class FixedQueue<T>(capacity: Int) : Queue<T> {
   public final val capacity: Int
   private final val array: Array<Any?>
   private final var head: Int

   public open var size: Int
      private set

   init {
      this.capacity = capacity;
      this.array = new Object[this.capacity];
   }

   public operator fun get(index: Int): Any {
      if (0 > index || index >= this.size()) {
         throw new IndexOutOfBoundsException("Index $index out of bounds for length ${this.size()}");
      } else {
         return (T)this.array[(this.head + index) % this.capacity];
      }
   }

   public override fun toString(): String {
      val var10000: Int = this.size();
      val var10001: Int = this.capacity;
      val var10002: java.lang.String = Arrays.toString(this.array);
      return "FixedQueue(size=$var10000, capacity=$var10001, values=$var10002)";
   }

   public override fun isEmpty(): Boolean {
      return this.size() == 0;
   }

   public override operator fun contains(element: Any?): Boolean {
      return kotlin.collections.ArraysKt.contains(this.array, element);
   }

   public override fun containsAll(elements: Collection<Any?>): Boolean {
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

   public override fun clear() {
      kotlin.collections.ArraysKt.fill$default(this.array, null, 0, 0, 6, null);
      this.head = 0;
      this.size = 0;
   }

   public override fun add(e: Any): Boolean {
      if (this.size() >= this.capacity) {
         throw new IndexOutOfBoundsException("Queue is full");
      } else {
         val var10000: Array<Any> = this.array;
         val var10001: Int = this.head;
         val var5: Int = this.size();
         this.size = var5 + 1;
         var10000[(var10001 + var5) % this.capacity] = e;
         return true;
      }
   }

   public override fun offer(e: Any): Boolean {
      val var10000: Boolean;
      if (this.size() >= this.capacity) {
         var10000 = false;
      } else {
         val var6: Array<Any> = this.array;
         val var10001: Int = this.head;
         val var5: Int = this.size();
         this.size = var5 + 1;
         var6[(var10001 + var5) % this.capacity] = e;
         var10000 = true;
      }

      return var10000;
   }

   public override fun remove(): Any {
      if (this.isEmpty()) {
         throw new NoSuchElementException("Queue is empty");
      } else {
         val var1: Any = this.array[this.head];
         this.array[this.head] = null;
         this.head = (this.head + 1) % this.capacity;
         this.size = this.size() + -1;
         this.size();
         return (T)var1;
      }
   }

   public override fun poll(): Any? {
      val var10000: Any;
      if (this.isEmpty()) {
         var10000 = null;
      } else {
         val var1: Any = this.array[this.head];
         this.array[this.head] = null;
         this.head = (this.head + 1) % this.capacity;
         this.size = this.size() + -1;
         this.size();
         var10000 = var1;
      }

      return (T)var10000;
   }

   public override fun element(): Any {
      if (this.isEmpty()) {
         throw new NoSuchElementException("Queue is empty");
      } else {
         return (T)this.array[this.head];
      }
   }

   public override fun peek(): Any? {
      return (T)(if (this.isEmpty()) null else this.array[this.head]);
   }

   public override fun addAll(elements: Collection<Any>): Boolean {
      if (this.size() + elements.size() > this.capacity) {
         throw new IndexOutOfBoundsException("Not enough space in queue");
      } else {
         val `$this$forEach$iv`: java.lang.Iterable;
         for (Object element$iv : $this$forEach$iv) {
            this.add((T)`element$iv`);
         }

         return true;
      }
   }

   public open operator fun iterator(): dev.kikugie.commons.collections.FixedQueue.FixedQueueIterator {
      return new FixedQueue.FixedQueueIterator(this);
   }

   @Deprecated(message = "Unsupported operation", level = DeprecationLevel.ERROR)
   public override fun remove(element: Any): Boolean {
      throw new UnsupportedOperationException("Removal of arbitrary elements is not supported");
   }

   @Deprecated(message = "Unsupported operation", level = DeprecationLevel.ERROR)
   public override fun removeAll(elements: Collection<Any>): Boolean {
      throw new UnsupportedOperationException("Removal of arbitrary elements is not supported");
   }

   @Deprecated(message = "Unsupported operation", level = DeprecationLevel.ERROR)
   public override fun retainAll(elements: Collection<Any>): Boolean {
      throw new UnsupportedOperationException("Removal of arbitrary elements is not supported");
   }

   override fun <T> toArray(array: Array<T>): Array<T> {
      return (T[])CollectionToArray.toArray(this, array);
   }

   override fun toArray(): Array<Any> {
      return CollectionToArray.toArray(this);
   }

   public inner class FixedQueueIterator : java.util.Iterator<T>, KMutableIterator {
      private final var index: Int

      public override operator fun hasNext(): Boolean {
         return !this.this$0.isEmpty() && this.index < this.this$0.size();
      }

      public override operator fun next(): Any {
         if (this.hasNext()) {
            return (T)FixedQueue.access$getArray$p(this.this$0)[(FixedQueue.access$getHead$p(this.this$0) + this.index++) % this.this$0.getCapacity()];
         } else {
            throw new NoSuchElementException("Finished iterating queue");
         }
      }

      @Deprecated(message = "Unsupported operation", level = DeprecationLevel.ERROR)
      public override fun remove() {
         throw new UnsupportedOperationException("Removal of arbitrary elements is not supported");
      }
   }
}
