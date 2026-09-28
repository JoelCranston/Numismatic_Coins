package io.ktor.utils.io.core.internal

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCharArraySequence.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CharArraySequence.kt\nio/ktor/utils/io/core/internal/CharArraySequence\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,33:1\n1#2:34\n*E\n"])
internal class CharArraySequence(array: CharArray, offset: Int, length: Int) : java.lang.CharSequence {
   private final val array: CharArray
   private final val offset: Int
   public final val length: Int

   init {
      this.array = array;
      this.offset = offset;
      this.length = length;
   }

   public operator fun get(index: Int): Char {
      if (index >= this.length) {
         this.indexOutOfBounds(index);
         throw new KotlinNothingValueException();
      } else {
         return this.array[index + this.offset];
      }
   }

   public override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
      if (startIndex < 0) {
         throw new IllegalArgumentException(("startIndex shouldn't be negative: $startIndex").toString());
      } else if (startIndex > this.length) {
         throw new IllegalArgumentException(("startIndex is too large: $startIndex > ${this.length}").toString());
      } else if (startIndex + endIndex > this.length) {
         throw new IllegalArgumentException(("endIndex is too large: $endIndex > ${this.length}").toString());
      } else if (endIndex < startIndex) {
         throw new IllegalArgumentException(("endIndex should be greater or equal to startIndex: $startIndex > $endIndex").toString());
      } else {
         return new CharArraySequence(this.array, this.offset + startIndex, endIndex - startIndex);
      }
   }

   private fun indexOutOfBounds(index: Int): Nothing {
      throw new IndexOutOfBoundsException("String index out of bounds: $index > ${this.length}");
   }
}
