package io.ktor.http.cio.internals

import io.ktor.utils.io.pool.ObjectPool
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCharArrayBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CharArrayBuilder.kt\nio/ktor/http/cio/internals/CharArrayBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,225:1\n1#2:226\n*E\n"])
internal class CharArrayBuilder(pool: ObjectPool<CharArray> = CharArrayPoolKt.getCharArrayPool()) : java.lang.CharSequence, Appendable {
   public final val pool: ObjectPool<CharArray>
   private final var buffers: MutableList<CharArray>?
   private final var current: CharArray?
   private final var stringified: String?
   private final var released: Boolean
   private final var remaining: Int

   public open var length: Int
      private set

   init {
      this.pool = pool;
   }

   public open operator fun get(index: Int): Char {
      if (index < 0) {
         throw new IllegalArgumentException(("index is negative: $index").toString());
      } else if (index >= this.length()) {
         throw new IllegalArgumentException(("index $index is not in range [0, ${this.length()})").toString());
      } else {
         return this.getImpl(index);
      }
   }

   private fun getImpl(index: Int): Char {
      val var10000: CharArray = this.bufferForIndex(index);
      val var10002: CharArray = this.current;
      return var10000[index % var10002.length];
   }

   public override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
      if (startIndex > endIndex) {
         throw new IllegalArgumentException(("startIndex ($startIndex) should be less or equal to endIndex ($endIndex)").toString());
      } else if (startIndex < 0) {
         throw new IllegalArgumentException(("startIndex is negative: $startIndex").toString());
      } else if (endIndex > this.length()) {
         throw new IllegalArgumentException(("endIndex ($endIndex) is greater than length (${this.length()})").toString());
      } else {
         return new CharArrayBuilder.SubSequenceImpl((int)this, startIndex, endIndex);
      }
   }

   public override fun toString(): String {
      var var10000: java.lang.String = this.stringified;
      if (this.stringified == null) {
         val var1: java.lang.String = this.copy(0, this.length()).toString();
         this.stringified = var1;
         var10000 = var1;
      }

      return var10000;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (other !is java.lang.CharSequence) {
         return false;
      } else {
         return this.length() == (other as java.lang.CharSequence).length() && this.rangeEqualsImpl(0, other as java.lang.CharSequence, 0, this.length());
      }
   }

   public override fun hashCode(): Int {
      return if (this.stringified != null) this.stringified.hashCode() else this.hashCodeImpl(0, this.length());
   }

   public override fun append(value: Char): Appendable {
      val var10000: CharArray = this.nonFullBuffer();
      val var10001: CharArray = this.current;
      var10000[var10001.length - this.remaining] = value;
      this.stringified = null;
      this.remaining--;
      this.length = this.length() + 1;
      return this;
   }

   public override fun append(value: CharSequence?, startIndex: Int, endIndex: Int): Appendable {
      if (value == null) {
         return this;
      } else {
         var current: Int = startIndex;

         while (current < endIndex) {
            val buffer: CharArray = this.nonFullBuffer();
            val offset: Int = buffer.length - this.remaining;
            val bytesToCopy: Int = Math.min(endIndex - current, this.remaining);

            for (int i = 0; i < bytesToCopy; i++) {
               buffer[offset + i] = value.charAt(current + i);
            }

            current += bytesToCopy;
            this.remaining -= bytesToCopy;
         }

         this.stringified = null;
         this.length = this.length() + (endIndex - startIndex);
         return this;
      }
   }

   public override fun append(value: CharSequence?): Appendable {
      return if (value == null) this else this.append(value, 0, value.length());
   }

   public fun release() {
      val list: java.util.List = this.buffers;
      if (this.buffers != null) {
         this.current = null;
         var i: Int = 0;

         for (int var3 = list.size(); i < var3; i++) {
            this.pool.recycle((char[])list.get(i));
         }
      } else {
         if (this.current != null) {
            this.pool.recycle(this.current);
         }

         this.current = null;
      }

      this.released = true;
      this.buffers = null;
      this.stringified = null;
      this.length = 0;
      this.remaining = 0;
   }

   private fun copy(startIndex: Int, endIndex: Int): CharSequence {
      if (startIndex == endIndex) {
         return "";
      } else {
         val builder: StringBuilder = new StringBuilder(endIndex - startIndex);

         for (int base = startIndex - startIndex % 2048; base < endIndex; base += 2048) {
            val var9: CharArray = this.bufferForIndex(base);
            val innerStartIndex: Int = Math.max(0, startIndex - base);
            val innerEndIndex: Int = Math.min(endIndex - base, 2048);

            for (int innerIndex = innerStartIndex; innerIndex < innerEndIndex; innerIndex++) {
               builder.append(var9[innerIndex]);
            }
         }

         return builder;
      }
   }

   private fun bufferForIndex(index: Int): CharArray {
      val list: java.util.List = this.buffers;
      if (this.buffers == null) {
         if (index >= 2048) {
            this.throwSingleBuffer(index);
            throw new KotlinNothingValueException();
         } else if (this.current == null) {
            this.throwSingleBuffer(index);
            throw new KotlinNothingValueException();
         } else {
            return this.current;
         }
      } else {
         val var10002: CharArray = this.current;
         return list.get(index / var10002.length) as CharArray;
      }
   }

   private fun throwSingleBuffer(index: Int): Nothing {
      if (this.released) {
         throw new IllegalStateException("Buffer is already released");
      } else {
         throw new IndexOutOfBoundsException("$index is not in range [0; ${this.currentPosition()})");
      }
   }

   private fun nonFullBuffer(): CharArray {
      val var10000: CharArray;
      if (this.remaining == 0) {
         var10000 = this.appendNewArray();
      } else {
         var10000 = this.current;
      }

      return var10000;
   }

   private fun appendNewArray(): CharArray {
      val newBuffer: CharArray = this.pool.borrow();
      val existing: CharArray = this.current;
      this.current = newBuffer;
      this.remaining = newBuffer.length;
      this.released = false;
      if (existing != null) {
         var var10000: java.util.List = this.buffers;
         if (this.buffers == null) {
            val var4: ArrayList = new ArrayList();
            this.buffers = var4;
            var4.add(existing);
            var10000 = var4;
         }

         var10000.add(newBuffer);
      }

      return newBuffer;
   }

   private fun rangeEqualsImpl(start: Int, other: CharSequence, otherStart: Int, length: Int): Boolean {
      for (int i = 0; i < length; i++) {
         if (this.getImpl(start + i) != other.charAt(otherStart + i)) {
            return false;
         }
      }

      return true;
   }

   private fun hashCodeImpl(start: Int, end: Int): Int {
      var hc: Int = 0;

      for (int i = start; i < end; i++) {
         hc = 31 * hc + this.getImpl(i);
      }

      return hc;
   }

   private fun currentPosition(): Int {
      val var10000: CharArray = this.current;
      return var10000.length - this.remaining;
   }

   fun CharArrayBuilder() {
      this(null, 1, null);
   }

   @SourceDebugExtension(["SMAP\nCharArrayBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CharArrayBuilder.kt\nio/ktor/http/cio/internals/CharArrayBuilder$SubSequenceImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,225:1\n1#2:226\n*E\n"])
   private inner class SubSequenceImpl(start: Int, end: Int) : java.lang.CharSequence {
      public final val start: Int
      public final val end: Int
      private final var stringified: String?

      public open val length: Int
         public open get() {
            return this.end - this.start;
         }


      init {
         this.this$0 = `this$0`;
         this.start = start;
         this.end = end;
      }

      public open operator fun get(index: Int): Char {
         val withOffset: Int = index + this.start;
         if (index < 0) {
            throw new IllegalArgumentException(("index is negative: $index").toString());
         } else if (withOffset >= this.end) {
            throw new IllegalArgumentException(("index ($index) should be less than length (${this.length()})").toString());
         } else {
            return CharArrayBuilder.access$getImpl(this.this$0, withOffset);
         }
      }

      public override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
         if (startIndex < 0) {
            throw new IllegalArgumentException(("start is negative: $startIndex").toString());
         } else if (startIndex > endIndex) {
            throw new IllegalArgumentException(("start ($startIndex) should be less or equal to end ($endIndex)").toString());
         } else if (endIndex > this.end - this.start) {
            throw new IllegalArgumentException(("end should be less than length (${this.length()})").toString());
         } else {
            return if (startIndex == endIndex) "" else this.this$0.new SubSequenceImpl((int)this.this$0, this.start + startIndex, this.start + endIndex);
         }
      }

      public override fun toString(): String {
         var var10000: java.lang.String = this.stringified;
         if (this.stringified == null) {
            val var1: java.lang.String = CharArrayBuilder.access$copy(this.this$0, this.start, this.end).toString();
            this.stringified = var1;
            var10000 = var1;
         }

         return var10000;
      }

      public override operator fun equals(other: Any?): Boolean {
         if (other !is java.lang.CharSequence) {
            return false;
         } else {
            return (other as java.lang.CharSequence).length() == this.length()
               && CharArrayBuilder.access$rangeEqualsImpl(this.this$0, this.start, other as java.lang.CharSequence, 0, this.length());
         }
      }

      public override fun hashCode(): Int {
         return if (this.stringified != null) this.stringified.hashCode() else CharArrayBuilder.access$hashCodeImpl(this.this$0, this.start, this.end);
      }
   }
}
