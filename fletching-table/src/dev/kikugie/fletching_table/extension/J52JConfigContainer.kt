package dev.kikugie.fletching_table.extension

import dev.kikugie.fletching_table.transformer.language.JsonConverter
import org.gradle.api.Named
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

public abstract class J52JConfigContainer : Named {
   public abstract val prettyPrint: Property<Boolean>
   public abstract val converter: Property<JsonConverter>
   public abstract val patterns: MapProperty<String, String>

   open fun J52JConfigContainer() {
      this.getPrettyPrint().convention(false);
      this.getConverter().convention(JSON_CONVERTER);
   }

   public fun extension(format: String, vararg patterns: String) {
      for (java.lang.String it : patterns) {
         this.getPatterns().put(it, format);
      }
   }

   public companion object {
      public final val JSON_CONVERTER: JsonConverter
   }
}
