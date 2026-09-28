package dev.kikugie.fletching_table.adapter

import dev.kikugie.fletching_table.adapter.FTNeoforgeAdapter.1
import dev.kikugie.fletching_table.extension.FletchingTableExtension
import org.gradle.api.Project

public abstract class FTNeoforgeAdapter : FletchingTableAdapter {
   open fun FTNeoforgeAdapter(project: Project, host: FletchingTableExtension) {
      super(project, host);
      project.afterEvaluate(new 1(this, host));
   }
}
