package dev.kikugie.fletching_table.transformer

import dev.kikugie.fletching_table.transformer.language.JsonConverter
import dev.kikugie.fletching_table.transformer.language.JsonLanguageVisitor
import dev.kikugie.fletching_table.transformer.language.visitor.UtilKt
import java.io.Reader
import java.io.StringReader
import java.util.Comparator
import kotlin.enums.EnumEntries
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.internal.LinkedHashMapSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@SourceDebugExtension(["SMAP\nLanguageFileTransformer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LanguageFileTransformer.kt\ndev/kikugie/fletching_table/transformer/LanguageFileTransformer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,36:1\n1#2:37\n205#3:38\n*S KotlinDebug\n*F\n+ 1 LanguageFileTransformer.kt\ndev/kikugie/fletching_table/transformer/LanguageFileTransformer\n*L\n16#1:38\n*E\n"])
internal class LanguageFileTransformer(input: Reader) : TransformerReader(input) {
   protected open fun run(reader: Reader, args: dev.kikugie.fletching_table.transformer.LanguageFileTransformer.TransformArgs): Reader {
      val var10000: JsonConverter = args.getConverter();
      if (var10000 == null) {
         return reader;
      } else {
         var flattened: java.util.Map = MapsKt.toMap(UtilKt.accept(var10000.read(reader), JsonLanguageVisitor.Companion.create(args)));
         if (args.getSorter() != null) {
            flattened = MapsKt.toMap(MapsKt.toSortedMap(flattened, args.getSorter()));
         }

         val `this_$iv`: Json = args.getWriter();
         `this_$iv`.getSerializersModule();
         return new StringReader(
            `this_$iv`.encodeToString(
               (new LinkedHashMapSerializer<>(StringSerializer.INSTANCE, JsonElement.Companion.serializer())) as SerializationStrategy<? super java.util.Map<java.lang.String, ? extends JsonElement>>,
               flattened
            )
         );
      }
   }

   public enum class ArrayBehaviour {
      PRESERVE,
      JOIN,
      LINES
      @JvmStatic
      fun getEntries(): EnumEntries<LanguageFileTransformer.ArrayBehaviour> {
         return $ENTRIES;
      }
   }

   public data class TransformArgs(flattening: dev.kikugie.fletching_table.transformer.LanguageFileTransformer.ArrayBehaviour,
      writer: Json,
      sorter: Comparator<String>?,
      converters: Map<String, JsonConverter>
   ) {
      public final val flattening: dev.kikugie.fletching_table.transformer.LanguageFileTransformer.ArrayBehaviour
      public final val writer: Json
      public final val sorter: Comparator<String>?
      public final val converters: Map<String, JsonConverter>
      private final lateinit var format: String

      public final val converter: JsonConverter?
         public final get() {
            var var10001: java.lang.String = this.format;
            if (this.format == null) {
               Intrinsics.throwUninitializedPropertyAccessException("format");
               var10001 = null;
            }

            return this.converters.get(var10001);
         }


      init {
         this.flattening = flattening;
         this.writer = writer;
         this.sorter = sorter;
         this.converters = converters;
      }

      public fun with(extension: String): dev.kikugie.fletching_table.transformer.LanguageFileTransformer.TransformArgs {
         val var2: LanguageFileTransformer.TransformArgs = copy$default(this, null, null, null, null, 15, null);
         var2.format = extension;
         return var2;
      }

      public operator fun component1(): dev.kikugie.fletching_table.transformer.LanguageFileTransformer.ArrayBehaviour {
         return this.flattening;
      }

      public operator fun component2(): Json {
         return this.writer;
      }

      public operator fun component3(): Comparator<String>? {
         return this.sorter;
      }

      public operator fun component4(): Map<String, JsonConverter> {
         return this.converters;
      }

      public fun copy(
         flattening: dev.kikugie.fletching_table.transformer.LanguageFileTransformer.ArrayBehaviour = this.flattening,
         writer: Json = this.writer,
         sorter: Comparator<String>? = this.sorter,
         converters: Map<String, JsonConverter> = this.converters
      ): dev.kikugie.fletching_table.transformer.LanguageFileTransformer.TransformArgs {
         return new LanguageFileTransformer.TransformArgs(flattening, writer, sorter, converters);
      }

      public override fun toString(): String {
         return "TransformArgs(flattening=${this.flattening}, writer=${this.writer}, sorter=${this.sorter}, converters=${this.converters})";
      }

      public override fun hashCode(): Int {
         return ((this.flattening.hashCode() * 31 + this.writer.hashCode()) * 31 + (if (this.sorter == null) 0 else this.sorter.hashCode())) * 31
            + this.converters.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is LanguageFileTransformer.TransformArgs) {
            return false;
         } else {
            val var2: LanguageFileTransformer.TransformArgs = other as LanguageFileTransformer.TransformArgs;
            if (this.flattening != (other as LanguageFileTransformer.TransformArgs).flattening) {
               return false;
            } else if (!(this.writer == var2.writer)) {
               return false;
            } else if (!(this.sorter == var2.sorter)) {
               return false;
            } else {
               return this.converters == var2.converters;
            }
         }
      }
   }
}
