package dev.kikugie.fletching_table.extension.dependency.data

import java.lang.annotation.Annotation
import kotlin.enums.EnumEntries
import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SealedClassSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public sealed interface ModDependency {
   @SerialName("dependency_type")
   public val dependencyType: dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType

   public companion object {
      public fun serializer(): KSerializer<ModDependency> {
         return new SealedClassSerializer<>(
            "dev.kikugie.fletching_table.extension.dependency.data.ModDependency",
            ModDependency::class,
            new KClass[]{ModDependency.Project::class, ModDependency.Version::class},
            new KSerializer[]{ModDependency.Project.$serializer.INSTANCE, ModDependency.Version.$serializer.INSTANCE},
            new Annotation[0]
         );
      }
   }

   public enum class DependencyType {
      REQUIRED,
      OPTIONAL,
      EMBEDDED
      @JvmStatic
      fun getEntries(): EnumEntries<ModDependency.DependencyType> {
         return $ENTRIES;
      }
   }

   @Serializable
   public data class Project(project: String, dependencyType: dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType) :
      ModDependency {
      public final val project: String

      @SerialName("dependency_type")
      public open val dependencyType: dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType

      init {
         this.project = project;
         this.dependencyType = dependencyType;
      }

      public operator fun component1(): String {
         return this.project;
      }

      public operator fun component2(): dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType {
         return this.dependencyType;
      }

      public fun copy(
         project: String = this.project,
         dependencyType: dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType = this.dependencyType
      ): dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Project {
         return new ModDependency.Project(project, dependencyType);
      }

      public override fun toString(): String {
         return "Project(project=${this.project}, dependencyType=${this.dependencyType})";
      }

      public override fun hashCode(): Int {
         return this.project.hashCode() * 31 + this.dependencyType.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ModDependency.Project) {
            return false;
         } else {
            val var2: ModDependency.Project = other as ModDependency.Project;
            if (!(this.project == (other as ModDependency.Project).project)) {
               return false;
            } else {
               return this.dependencyType === var2.dependencyType;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Project> {
            return ModDependency.Project.$serializer.INSTANCE;
         }
      }
   }

   @Serializable
   public data class Version(version: String, dependencyType: dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType) :
      ModDependency {
      public final val version: String

      @SerialName("dependency_type")
      public open val dependencyType: dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType

      init {
         this.version = version;
         this.dependencyType = dependencyType;
      }

      public operator fun component1(): String {
         return this.version;
      }

      public operator fun component2(): dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType {
         return this.dependencyType;
      }

      public fun copy(
         version: String = this.version,
         dependencyType: dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType = this.dependencyType
      ): dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Version {
         return new ModDependency.Version(version, dependencyType);
      }

      public override fun toString(): String {
         return "Version(version=${this.version}, dependencyType=${this.dependencyType})";
      }

      public override fun hashCode(): Int {
         return this.version.hashCode() * 31 + this.dependencyType.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ModDependency.Version) {
            return false;
         } else {
            val var2: ModDependency.Version = other as ModDependency.Version;
            if (!(this.version == (other as ModDependency.Version).version)) {
               return false;
            } else {
               return this.dependencyType === var2.dependencyType;
            }
         }
      }

      public companion object {
         public fun serializer(): KSerializer<dev.kikugie.fletching_table.extension.dependency.data.ModDependency.Version> {
            return ModDependency.Version.$serializer.INSTANCE;
         }
      }
   }
}
