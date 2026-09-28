package dev.kikugie.fletching_table.extension.dependency.data

import kotlin.enums.EnumEntries
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.internal.EnumsKt

@Serializable
public data class ModVersion(id: String,
   project: String,
   version: String,
   fileUrl: String = "%CACHED%",
   releaseType: dev.kikugie.fletching_table.extension.dependency.data.ModVersion.ReleaseType,
   modLoaders: Set<String>,
   gameVersions: Set<String>,
   dependencies: List<ModDependency> = CollectionsKt.emptyList()
) {
   public final val id: String
   public final val project: String
   public final val version: String

   @Transient
   public final val fileUrl: String

   @SerialName("release_type")
   public final val releaseType: dev.kikugie.fletching_table.extension.dependency.data.ModVersion.ReleaseType

   @SerialName("mod_loaders")
   public final val modLoaders: Set<String>

   @SerialName("game_versions")
   public final val gameVersions: Set<String>

   public final val dependencies: List<ModDependency>

   init {
      this.id = id;
      this.project = project;
      this.version = version;
      this.fileUrl = fileUrl;
      this.releaseType = releaseType;
      this.modLoaders = modLoaders;
      this.gameVersions = gameVersions;
      this.dependencies = dependencies;
   }

   public fun asDependency(provider: String): String {
      val var10000: java.lang.String;
      if (provider == "modrinth") {
         var10000 = "maven.modrinth:${this.project}:${this.id}";
      } else {
         if (!(provider == "curseforge")) {
            throw new IllegalArgumentException("Unsupported provider '$provider'");
         }

         var10000 = "curse.maven:${this.project}:${this.id}";
      }

      return var10000;
   }

   public operator fun component1(): String {
      return this.id;
   }

   public operator fun component2(): String {
      return this.project;
   }

   public operator fun component3(): String {
      return this.version;
   }

   public operator fun component4(): String {
      return this.fileUrl;
   }

   public operator fun component5(): dev.kikugie.fletching_table.extension.dependency.data.ModVersion.ReleaseType {
      return this.releaseType;
   }

   public operator fun component6(): Set<String> {
      return this.modLoaders;
   }

   public operator fun component7(): Set<String> {
      return this.gameVersions;
   }

   public operator fun component8(): List<ModDependency> {
      return this.dependencies;
   }

   public fun copy(
      id: String = this.id,
      project: String = this.project,
      version: String = this.version,
      fileUrl: String = this.fileUrl,
      releaseType: dev.kikugie.fletching_table.extension.dependency.data.ModVersion.ReleaseType = this.releaseType,
      modLoaders: Set<String> = this.modLoaders,
      gameVersions: Set<String> = this.gameVersions,
      dependencies: List<ModDependency> = this.dependencies
   ): ModVersion {
      return new ModVersion(id, project, version, fileUrl, releaseType, modLoaders, gameVersions, dependencies);
   }

   public override fun toString(): String {
      return "ModVersion(id=${this.id}, project=${this.project}, version=${this.version}, fileUrl=${this.fileUrl}, releaseType=${this.releaseType}, modLoaders=${this.modLoaders}, gameVersions=${this.gameVersions}, dependencies=${this.dependencies})";
   }

   public override fun hashCode(): Int {
      return (
               (
                        (
                                 (((this.id.hashCode() * 31 + this.project.hashCode()) * 31 + this.version.hashCode()) * 31 + this.fileUrl.hashCode()) * 31
                                    + this.releaseType.hashCode()
                              )
                              * 31
                           + this.modLoaders.hashCode()
                     )
                     * 31
                  + this.gameVersions.hashCode()
            )
            * 31
         + this.dependencies.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ModVersion) {
         return false;
      } else {
         val var2: ModVersion = other as ModVersion;
         if (!(this.id == (other as ModVersion).id)) {
            return false;
         } else if (!(this.project == var2.project)) {
            return false;
         } else if (!(this.version == var2.version)) {
            return false;
         } else if (!(this.fileUrl == var2.fileUrl)) {
            return false;
         } else if (this.releaseType != var2.releaseType) {
            return false;
         } else if (!(this.modLoaders == var2.modLoaders)) {
            return false;
         } else if (!(this.gameVersions == var2.gameVersions)) {
            return false;
         } else {
            return this.dependencies == var2.dependencies;
         }
      }
   }

   public companion object {
      public fun serializer(): KSerializer<ModVersion> {
         return ModVersion.$serializer.INSTANCE;
      }
   }

   @Serializable
   public enum class ReleaseType {
      STABLE,
      BETA,
      ALPHA

      public final val isStable: Boolean
         public final get() {
            return this === STABLE;
         }

      @JvmStatic
      public ModVersion.ReleaseType.Companion Companion = new ModVersion.ReleaseType.Companion(null);
      @JvmStatic
      private Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt.lazy(
         LazyThreadSafetyMode.PUBLICATION,
         () -> EnumsKt.createSimpleEnumSerializer("dev.kikugie.fletching_table.extension.dependency.data.ModVersion.ReleaseType", values())
      );

      @JvmStatic
      fun getEntries(): EnumEntries<ModVersion.ReleaseType> {
         return $ENTRIES;
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.data.ModVersion.ReleaseType> {
            return this.get$cachedSerializer();
         }
      }
   }
}
