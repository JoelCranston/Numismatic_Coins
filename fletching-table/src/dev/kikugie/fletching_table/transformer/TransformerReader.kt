package dev.kikugie.fletching_table.transformer

import java.io.FilterReader
import java.io.Reader

internal abstract class TransformerReader<T> : FilterReader {
   private final val input: Reader

   open fun TransformerReader(input: Reader) {
      super(input);
      this.input = input;
   }

   public fun setArgs(args: Any) {
      if (this.in == this.input) {
         this.in = this.run(this.input, (T)args);
      }
   }

   protected abstract fun run(reader: Reader, args: Any): Reader {
   }
}
