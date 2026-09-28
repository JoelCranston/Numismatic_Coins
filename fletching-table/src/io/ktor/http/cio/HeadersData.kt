package io.ktor.http.cio

import io.ktor.http.cio.HeadersData.headersStarts.1
import java.util.ArrayList

private class HeadersData {
   private final var arrays: MutableList<IntArray> = (new ArrayList()) as java.util.List

   public fun arraysCount(): Int {
      return this.arrays.size();
   }

   public fun prepare(subArraysCount: Int) {
      for (int var2 = 0; var2 < subArraysCount; var2++) {
         this.arrays.add((int[])HttpHeadersMapKt.access$getIntArrayPool$p().borrow());
      }
   }

   public fun at(index: Int): Int {
      return this.arrays.get(index / 768)[index % 768];
   }

   public fun set(index: Int, value: Int) {
      this.arrays.get(index / 768)[index % 768] = value;
   }

   public fun headersStarts(): Sequence<Int> {
      return SequencesKt.sequence(new 1(this, null));
   }

   public fun release() {
      for (int[] array : this.arrays) {
         HttpHeadersMapKt.access$getIntArrayPool$p().recycle(array);
      }

      this.arrays.clear();
   }
}
