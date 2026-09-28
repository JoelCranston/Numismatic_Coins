package dev.kikugie.fletching_table.transformer

import dev.kikugie.fletching_table.transformer.accessconverter.Aw2AtConversionKt
import dev.kikugie.fletching_table.transformer.accessconverter.v2.AwLineParser
import java.io.Reader
import java.io.StringReader
import java.nio.file.Path

internal class Aw2AtFileTransformer(input: Reader) : TransformerReader(input) {
   protected open fun run(reader: Reader, args: dev.kikugie.fletching_table.transformer.Aw2AtFileTransformer.TransformArgs): Reader {
      return new StringReader(Aw2AtConversionKt.collect(AwLineParser.INSTANCE.parse(reader).convert()));
   }

   public data class TransformArgs(file: Path) {
      public final val file: Path

      init {
         this.file = file;
      }

      public operator fun component1(): Path {
         return this.file;
      }

      public fun copy(file: Path = this.file): dev.kikugie.fletching_table.transformer.Aw2AtFileTransformer.TransformArgs {
         return new Aw2AtFileTransformer.TransformArgs(file);
      }

      public override fun toString(): String {
         return "TransformArgs(file=${this.file})";
      }

      public override fun hashCode(): Int {
         return this.file.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is Aw2AtFileTransformer.TransformArgs) {
            return false;
         } else {
            return this.file == (other as Aw2AtFileTransformer.TransformArgs).file;
         }
      }
   }
}
