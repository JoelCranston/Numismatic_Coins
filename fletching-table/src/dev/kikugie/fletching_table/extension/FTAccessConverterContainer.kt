package dev.kikugie.fletching_table.extension

import org.gradle.api.Named
import org.gradle.api.provider.MapProperty

public abstract class FTAccessConverterContainer : Named {
   internal abstract val converters: MapProperty<String, String>

   @JvmOverloads
   public fun add(from: String, to: String = "META-INF/accesstransformer.cfg") {
      if ((this.getConverters$fletching_table_two().keySet().getOrElse(SetsKt.emptySet()) as java.util.Set).contains(to)) {
         throw new IllegalArgumentException(("Access transformer $to is already registered").toString());
      } else {
         this.getConverters$fletching_table_two().put(to, from);
      }
   }

   @JvmOverloads
   fun add(from: java.lang.String) {
      add$default(this, from, null, 2, null);
   }
}
