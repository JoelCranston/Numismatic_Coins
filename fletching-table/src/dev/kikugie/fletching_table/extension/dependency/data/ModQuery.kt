package dev.kikugie.fletching_table.extension.dependency.data

import dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Project
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
public data class ModQuery(id: String, provider: String, versions: Set<String> = SetsKt.emptySet(), loaders: Set<String> = SetsKt.emptySet()) {
   public final val id: String
   public final val provider: String
   public final val versions: Set<String>
   public final val loaders: Set<String>

   init {
      this.id = id;
      this.provider = provider;
      this.versions = versions;
      this.loaders = loaders;
   }

   public fun of(dependency: Project, version: ModVersion? = null): ModQuery {
      var var10001: java.lang.String;
      var var10003: java.util.Set;
      label19: {
         var10001 = dependency.getProject();
         if (version != null) {
            var10003 = version.getGameVersions();
            if (var10003 != null) {
               break label19;
            }
         }

         var10003 = this.versions;
      }

      if (version != null) {
         val var10004: java.util.Set = version.getModLoaders();
         if (var10004 != null) {
            return copy$default(this, var10001, null, var10003, var10004, 2, null);
         }
      }

      return copy$default(this, var10001, null, var10003, this.loaders, 2, null);
   }

   public fun of(id: String, version: ModVersion? = null): ModQuery {
      var var10003: java.util.Set;
      label19: {
         if (version != null) {
            var10003 = version.getGameVersions();
            if (var10003 != null) {
               break label19;
            }
         }

         var10003 = this.versions;
      }

      if (version != null) {
         val var10004: java.util.Set = version.getModLoaders();
         if (var10004 != null) {
            return copy$default(this, id, null, var10003, var10004, 2, null);
         }
      }

      return copy$default(this, id, null, var10003, this.loaders, 2, null);
   }

   public operator fun component1(): String {
      return this.id;
   }

   public operator fun component2(): String {
      return this.provider;
   }

   public operator fun component3(): Set<String> {
      return this.versions;
   }

   public operator fun component4(): Set<String> {
      return this.loaders;
   }

   public fun copy(id: String = this.id, provider: String = this.provider, versions: Set<String> = this.versions, loaders: Set<String> = this.loaders): ModQuery {
      return new ModQuery(id, provider, versions, loaders);
   }

   public override fun toString(): String {
      return "ModQuery(id=${this.id}, provider=${this.provider}, versions=${this.versions}, loaders=${this.loaders})";
   }

   public override fun hashCode(): Int {
      return ((this.id.hashCode() * 31 + this.provider.hashCode()) * 31 + this.versions.hashCode()) * 31 + this.loaders.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ModQuery) {
         return false;
      } else {
         val var2: ModQuery = other as ModQuery;
         if (!(this.id == (other as ModQuery).id)) {
            return false;
         } else if (!(this.provider == var2.provider)) {
            return false;
         } else if (!(this.versions == var2.versions)) {
            return false;
         } else {
            return this.loaders == var2.loaders;
         }
      }
   }

   public companion object {
      public fun serializer(): KSerializer<ModQuery> {
         return ModQuery.$serializer.INSTANCE;
      }
   }
}
