package dev.kikugie.fletching_table.transformer

import dev.kikugie.fletching_table.transformer.language.JsonConverter
import java.io.Reader
import java.io.StringReader
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@SourceDebugExtension(["SMAP\nJ52JFileTransformer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 J52JFileTransformer.kt\ndev/kikugie/fletching_table/transformer/J52JFileTransformer\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,15:1\n205#2:16\n*S KotlinDebug\n*F\n+ 1 J52JFileTransformer.kt\ndev/kikugie/fletching_table/transformer/J52JFileTransformer\n*L\n11#1:16\n*E\n"])
internal class J52JFileTransformer(input: Reader) : TransformerReader(input) {
   protected open fun run(reader: Reader, args: dev.kikugie.fletching_table.transformer.J52JFileTransformer.TransformArgs): Reader {
      val json: JsonElement = args.getConverter().read(reader);
      val `this_$iv`: Json = args.getWriter();
      `this_$iv`.getSerializersModule();
      return new StringReader(`this_$iv`.encodeToString(JsonElement.Companion.serializer(), json));
   }

   public data class TransformArgs(writer: Json, converter: JsonConverter) {
      public final val writer: Json
      public final val converter: JsonConverter

      init {
         this.writer = writer;
         this.converter = converter;
      }

      public operator fun component1(): Json {
         return this.writer;
      }

      public operator fun component2(): JsonConverter {
         return this.converter;
      }

      public fun copy(writer: Json = this.writer, converter: JsonConverter = this.converter): dev.kikugie.fletching_table.transformer.J52JFileTransformer.TransformArgs {
         return new J52JFileTransformer.TransformArgs(writer, converter);
      }

      public override fun toString(): String {
         return "TransformArgs(writer=${this.writer}, converter=${this.converter})";
      }

      public override fun hashCode(): Int {
         return this.writer.hashCode() * 31 + this.converter.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is J52JFileTransformer.TransformArgs) {
            return false;
         } else {
            val var2: J52JFileTransformer.TransformArgs = other as J52JFileTransformer.TransformArgs;
            if (!(this.writer == (other as J52JFileTransformer.TransformArgs).writer)) {
               return false;
            } else {
               return this.converter == var2.converter;
            }
         }
      }
   }
}
