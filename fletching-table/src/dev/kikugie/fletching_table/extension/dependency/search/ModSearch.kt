package dev.kikugie.fletching_table.extension.dependency.search

import dev.kikugie.fletching_table.extension.dependency.FTModSpec
import dev.kikugie.fletching_table.extension.dependency.data.ModQuery
import dev.kikugie.fletching_table.extension.dependency.data.ModVersion
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Version
import io.ktor.client.HttpClient

public interface ModSearch {
   public val client: HttpClient

   public abstract suspend fun get(query: ModQuery, spec: FTModSpec): List<ModVersion> {
   }

   public abstract suspend fun get(dependency: Version, spec: FTModSpec): ModVersion {
   }
}
