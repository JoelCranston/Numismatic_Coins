package dev.kikugie.fletching_table.adapter

import dev.kikugie.fletching_table.extension.FletchingTableExtension
import org.gradle.api.Project
import org.gradle.api.provider.Property

public abstract class FTLexforgeAdapter : FletchingTableAdapter {
   public abstract val applyMixinConfig: Property<Boolean>

   open fun FTLexforgeAdapter(project: Project, host: FletchingTableExtension) {
      super(project, host);
      this.getApplyMixinConfig().value(false).disallowChanges();
   }
}
