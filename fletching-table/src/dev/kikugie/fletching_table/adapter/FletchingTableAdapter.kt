package dev.kikugie.fletching_table.adapter

import dev.kikugie.fletching_table.extension.FletchingTableExtension
import org.gradle.api.Project
import org.gradle.api.provider.Property

public abstract class FletchingTableAdapter {
   protected final val project: Project
   protected final val host: FletchingTableExtension
   public abstract val applyMixinConfig: Property<Boolean>

   open fun FletchingTableAdapter(project: Project, host: FletchingTableExtension) {
      this.project = project;
      this.host = host;
      this.getApplyMixinConfig().convention(true);
   }
}
