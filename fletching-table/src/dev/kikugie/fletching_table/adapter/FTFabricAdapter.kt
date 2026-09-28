package dev.kikugie.fletching_table.adapter

import dev.kikugie.fletching_table.adapter.FTFabricAdapter.2
import dev.kikugie.fletching_table.extension.FletchingTableExtension
import org.gradle.api.Project
import org.gradle.api.provider.MapProperty

public abstract class FTFabricAdapter : FletchingTableAdapter {
   public abstract val entrypointMappings: MapProperty<String, String>

   open fun FTFabricAdapter(project: Project, host: FletchingTableExtension) {
      super(project, host);
      val var3: MapProperty = this.getEntrypointMappings();
      var3.put("main", "net.fabricmc.api.ModInitializer");
      var3.put("client", "net.fabricmc.api.ClientModInitializer");
      var3.put("server", "net.fabricmc.api.DedicatedServerModInitializer");
      var3.put("preLaunch", "net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint");
      project.afterEvaluate(new 2(this, host));
   }
}
