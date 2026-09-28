package dev.kikugie.fletching_table.extension

import dev.kikugie.fletching_table.extension.LanguageConfigContainer.sam.dev_kikugie_fletching_table_transformer_language_JsonConverter.0
import dev.kikugie.fletching_table.transformer.language.JsonConverter
import java.io.Reader
import java.util.Arrays
import kotlinx.serialization.json.JsonElement
import org.gradle.api.Named
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

public abstract class LanguageConfigContainer : Named {
   public abstract val patterns: ListProperty<String>
   public abstract val sortKeys: Property<Boolean>
   public abstract val prettyPrint: Property<Boolean>
   public abstract val flatteningMode: Property<String>
   public abstract val formatConverters: MapProperty<String, JsonConverter>

   open fun LanguageConfigContainer() {
      this.getSortKeys().convention(false);
      this.getPrettyPrint().convention(false);
      this.getFlatteningMode().convention("join");
      this.registerConverter(JSON_CONVERTER, "json", "json5");
      this.registerConverter(YAML_CONVERTER, "yml", "yaml");
      this.registerConverter(TOML_CONVERTER, "toml");
   }

   public fun registerConverter(vararg extensions: String, builder: (Reader) -> JsonElement) {
      this.registerConverter(new 0(builder), Arrays.copyOf(extensions, extensions.length));
   }

   public fun registerConverter(converter: JsonConverter, vararg extensions: String) {
      for (java.lang.String it : extensions) {
         this.getFormatConverters().put(it, converter);
      }
   }

   public companion object {
      public final val JSON_CONVERTER: JsonConverter
      public final val YAML_CONVERTER: JsonConverter
      public final val TOML_CONVERTER: JsonConverter
   }
}
